package net.gnomeffinway.donoregistry.commands;

import net.gnomeffinway.donoregistry.DonoRegistry;
import net.gnomeffinway.donoregistry.DonorRecord;
import net.gnomeffinway.donoregistry.util.Checks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfoCommandExecutor extends DonoRegistryCommand implements CommandExecutor {
	
	DonoRegistry plugin;
	
	public InfoCommandExecutor(DonoRegistry plugin) {
		super(plugin);
		this.plugin=plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 2){
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
	
		String message = "";
				
		message += ChatColor.GOLD + "----- " + ChatColor.RED + "#"+ChatColor.WHITE+ record.getId()+ChatColor.AQUA +" "+ record.getTarget() + ChatColor.GOLD + " -----";
		message += "\n" + ChatColor.GOLD + "Rank: "+ChatColor.WHITE + record.getRank();
		message += "\n" + ChatColor.GOLD + "Region: " + ChatColor.WHITE + record.getRegion();
		message += "\n" + ChatColor.GOLD + "Warp: " + ChatColor.WHITE + record.getWarp();
		message += "\n" + ChatColor.GOLD + "Others: " + ChatColor.WHITE + record.getOthers();
		message += "\n" + ChatColor.GOLD + "NPCs: " + ChatColor.WHITE + record.getNpcs();
		
		sender.sendMessage(message);
		
		return true;
	}
		        
}
