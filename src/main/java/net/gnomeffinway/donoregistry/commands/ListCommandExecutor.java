package net.gnomeffinway.donoregistry.commands;

import java.text.DecimalFormat;
import java.util.List;

import net.gnomeffinway.donoregistry.DonoRegistry;
import net.gnomeffinway.donoregistry.DonorRecord;
import net.gnomeffinway.donoregistry.util.Checks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListCommandExecutor extends DonoRegistryCommand implements CommandExecutor {
	
	DonoRegistry plugin;
	
	public ListCommandExecutor(DonoRegistry plugin) {
		super(plugin);
		this.plugin=plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender==null){
			return true;
		}
				
		List<DonorRecord> list = DonoRegistry.getManager().getRecords();
		
		if(list==null || list.size()==0){
			sender.sendMessage(DONOR_LIST_EMPTY);
			return true;
		}
		
		
		String message="";
		
		double pgs = Math.ceil(((double)list.size())/10);
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(0);
		int pages = Integer.parseInt(df.format(pgs));
					
		if(args.length==1) {
			message+=ChatColor.YELLOW + "Donor List (Page 1 of "+pages+")\n";
			message+=ChatColor.GOLD + "-------- " + ChatColor.AQUA + "Donors" + ChatColor.GOLD + " --------\n";
			int arg=0;
			if(arg+1>pages) {
				sender.sendMessage(PAGE_NOT_VALID);
				return true;
			}
			if(arg+1==pages) {
				for(int x=arg*10;x<list.size()-1;x++){
					DonorRecord record = list.get(x);
					
					message += ChatColor.RED + "#" + ChatColor.WHITE + record.getId() + ": " + ChatColor.GOLD + record.getTarget()+"\n";
				}
			} else
			for(int x=0;x<10;x++){
				DonorRecord record = list.get(x);
				
				message += ChatColor.RED + "#" + ChatColor.WHITE + record.getId() + ": " + ChatColor.GOLD + record.getTarget()+"\n";
			}
		} else {
			if(Checks.numCheck(args[1])) {
				message+=ChatColor.YELLOW + "Donor List (Page "+args[1] +" of "+pages+")\n";
				message+=ChatColor.GOLD + "-------- " + ChatColor.AQUA + "Donors" + ChatColor.GOLD + " --------\n";
				int arg=Integer.parseInt(args[1])-1;
				if(arg+1>pages) {
					sender.sendMessage(PAGE_NOT_VALID);
					return true;
				}
				if(arg+1==pages) {
					for(int x=arg*10;x<list.size()-1;x++){
						DonorRecord record = list.get(x);
						
						message += ChatColor.RED + "#" + ChatColor.WHITE + record.getId() + ": " + ChatColor.GOLD + record.getTarget()+"\n";
					}
				} else
				for(int x=arg*10;x<arg*10+10;x++){
					DonorRecord record = list.get(x);
					
					message += ChatColor.RED + "#" + ChatColor.WHITE + record.getId() + ": " + ChatColor.GOLD + record.getTarget()+"\n";
				}
			} else {
				printArgsError(args);
				printHelp(sender, label);
				return true;
			}

		}
		
		
		
		sender.sendMessage(message);
		
		return true;
	}
		        
}
