package net.gnomeffinway.donoregistry.commands;

import java.util.Iterator;
import java.util.List;

import net.gnomeffinway.donoregistry.DonoRegistry;
import net.gnomeffinway.donoregistry.DonorRecord;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommandExecutor extends DonoRegistryCommand implements CommandExecutor {
	
	DonoRegistry plugin;
	
	public ListCommandExecutor(DonoRegistry plugin) {
		super(plugin);
		this.plugin=plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player) || sender==null){
			sender.sendMessage(COULD_NOT_FIND_PLAYER);
			return true;
		}
				
		List<DonorRecord> list = DonoRegistry.getManager().getRecords();
		
		if(list==null){
			sender.sendMessage(COULD_NOT_FIND_PLAYER);
			return true;
		}
		
		sender.sendMessage(ChatColor.GOLD + "-------- " + ChatColor.AQUA + "Donors" + ChatColor.GOLD + " --------");
		
		String message="";
		
		Iterator<DonorRecord> iterator = list.iterator();
		while(iterator.hasNext()) {
			DonorRecord record = iterator.next();
			
			message += ChatColor.RED + "#" + ChatColor.WHITE + record.getId() + ": " + ChatColor.GRAY + record.getTarget();
		
		}
		
		sender.sendMessage(message);
		
		return true;
	}
		        
}
