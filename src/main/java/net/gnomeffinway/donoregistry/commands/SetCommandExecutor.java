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
		
		String target = findTarget(args[1]);
		
		if(target == null) {
			sender.sendMessage(COULD_NOT_FIND_PLAYER);
			return true;
		}
		
		DonorRecord record = DonoRegistry.getManager().getRecord(sender.getName());
		
		if(record == null){
			sender.sendMessage(COULD_NOT_FIND_DONOR);
			return true;
		}
	
		if(args[2].equalsIgnoreCase("npcs") || args[2].equalsIgnoreCase("npc")) {
			String npcs = args[3];
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
			}
			
			record.setNpcs(npcs);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + target + "'s record updated with "+npcs);

		}
		
		if(args[2].equalsIgnoreCase("others") || args[2].equalsIgnoreCase("other")) {
			String others=args[3];
			String[] regex=others.split(",");
			String other="";
			
			for(int x=0; x<regex.length; x++) {
				other=findTarget(regex[x]);
				if(other == null) {
					sender.sendMessage(COULD_NOT_FIND_PLAYER);
					return true;
				}
			}
			
			record.setOthers(others);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + target + "'s record updated with "+others);
		}
		
		if(args[2].equalsIgnoreCase("rank")) {
			Rank rank=Rank.fromString(args[3]);
			
			if(rank == null) {
				sender.sendMessage(RANK_NOT_VALID);
				return true;
			}
			
			record.setRank(rank);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + target + "'s record updated with "+rank);
		}
		
		if(args[2].equalsIgnoreCase("region")) {
			String region=args[3];
			
			if(!Checks.regionCheck(region)) {
				sender.sendMessage(COULD_NOT_FIND_REGION);
				return true;
			}
			
			record.setRegion(region);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + target + "'s record updated with "+region);
		}
		
		if(args[2].equalsIgnoreCase("warp") || args[2].equalsIgnoreCase("warps")) {
			String warp=args[3];
			
			if(!Checks.warpCheck(warp)) {
				sender.sendMessage(COULD_NOT_FIND_WARP);
				return true;
			}
			
			record.setWarp(warp);
			plugin.getDatabase().save(record);
			sender.sendMessage(ChatColor.GOLD + target + "'s record updated with "+warp);
		}	
		return true;
	}
		        
}

