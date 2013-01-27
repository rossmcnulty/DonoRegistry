package net.gnomeffinway.donoregistry.commands;

import net.gnomeffinway.donoregistry.DonoRegistry;
import net.gnomeffinway.donoregistry.Donor;
import net.gnomeffinway.donoregistry.Rank;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NewCommandExecutor extends DonoRegistryCommand implements CommandExecutor {

	DonoRegistry plugin;
	
	public NewCommandExecutor(DonoRegistry plugin) {
		super(plugin);
		this.plugin=plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 3){
			printArgsError(args);
			printHelp(sender, label);
			return true;
		}
		
		if(sender==null){
			return true;
		}
		
		String target=findTarget(args[1]);
		
		if(target == null) {
			sender.sendMessage(COULD_NOT_FIND_PLAYER);
			return true;
		}
		
		
		if(DonoRegistry.getManager().getRecord(target) != null) {
			sender.sendMessage(RECORD_ALREADY_EXISTS);
			return true;
		}
		
		Rank rank=Rank.fromString(args[2]);
		
		if(rank == null) {
			sender.sendMessage(RANK_NOT_VALID);
			return true;
		}

		
		Donor donor=new Donor(target,rank);
		
		DonoRegistry.getManager().addRecord(donor);
		
		sender.sendMessage(ChatColor.GOLD + "New record created for "+target);
		
		return true;
		
	}
	
}
