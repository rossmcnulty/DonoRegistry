package net.gnomeffinway.donoregistry.commands;

import net.gnomeffinway.donoregistry.DonoRegistry;
import net.gnomeffinway.donoregistry.DonorRecord;
import net.gnomeffinway.donoregistry.Rank;
import net.gnomeffinway.donoregistry.util.Checks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetCommandExecutor extends DonoRegistryCommand implements CommandExecutor {
	
	DonoRegistry plugin;
	
	public SetCommandExecutor(DonoRegistry plugin) {
		super(plugin);
		this.plugin=plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 4){
			printArgsError(args);
			printHelp(sender, label);
			return true;
		}
		
		if(sender==null){
			return true;
		}
		
		DonorRecord record;
		
		
		if(!Checks.numCheck(args[1])) {
			String target = findTarget(args[1]);
			
			if(target == null) {
				sender.sendMessage(COULD_NOT_FIND_PLAYER);
				return true;
			}
			record=DonoRegistry.getManager().getRecord(target);
		} else {
			record=DonoRegistry.getManager().getRecord(Integer.parseInt(args[1]));
		}
				
		if(record == null){
			sender.sendMessage(COULD_NOT_FIND_DONOR);
			return true;
		}
	
		if(args[2].equalsIgnoreCase("npcs") || args[2].equalsIgnoreCase("npc")) {
			String npcs = args[3];
			String npcsSpaced="";
			String[] regex=npcs.split(",");
			int npc;
			
			for(int x=0;x<regex.length;x++) {
				try {
					npc=Integer.parseInt(regex[x]);
				} catch(NumberFormatException e) {
					sender.sendMessage(COULD_NOT_FIND_NPC);
					return true;
				}
				if(!Checks.npcCheck(npc)) {
					sender.sendMessage(COULD_NOT_FIND_NPC);
					return true;
				}
				if(x<regex.length-1)
					npcsSpaced+=regex[x]+", ";
				else
					npcsSpaced+=regex[x];
			}
			
			record.setNpcs(npcsSpaced);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + record.getTarget() + "'s record updated with "+npcsSpaced);

		}
		
		if(args[2].equalsIgnoreCase("others") || args[2].equalsIgnoreCase("other")) {
			String others=args[3];
			String othersSpaced="";
			String[] regex=others.split(",");
			String other="";
			
			for(int x=0; x<regex.length; x++) {
				other=findTarget(regex[x]);
				if(other == null) {
					sender.sendMessage(COULD_NOT_FIND_PLAYER);
					return true;
				}
				if(x<regex.length-1)
					othersSpaced+=regex[x]+", ";
				else
					othersSpaced+=regex[x];
			}
			
			record.setOthers(othersSpaced);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + record.getTarget() + "'s record updated with "+othersSpaced);
		}
		
		if(args[2].equalsIgnoreCase("rank")) {
			Rank rank=Rank.fromString(args[3]);
			
			if(rank == null) {
				sender.sendMessage(RANK_NOT_VALID);
				return true;
			}
			
			record.setRank(rank);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + record.getTarget() + "'s record updated with "+rank);
		}
		
		if(args[2].equalsIgnoreCase("region")) {
			String region=args[3];
			String regionCaps="";
			
			if(!Checks.regionCheck(region)) {
				sender.sendMessage(COULD_NOT_FIND_REGION);
				return true;
			}
			
			regionCaps=region.substring(0,1).toUpperCase() + region.substring(1,region.length());
			
			record.setRegion(regionCaps);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + record.getTarget() + "'s record updated with "+regionCaps);
		}
		
		if(args[2].equalsIgnoreCase("warp") || args[2].equalsIgnoreCase("warps")) {
			String warp=args[3];
			String warpCaps="";
			
			if(!Checks.warpCheck(warp)) {
				sender.sendMessage(COULD_NOT_FIND_WARP);
				return true;
			}
			
			warpCaps=warp.substring(0,1).toUpperCase() + warp.substring(1,warp.length());
			record.setWarp(warpCaps);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + record.getTarget() + "'s record updated with "+warpCaps);
		}	
		return true;
	}
		        
}

