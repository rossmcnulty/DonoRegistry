package net.gnomeffinway.donoregistry.commands;

import net.gnomeffinway.donoregistry.DonoRegistry;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommandExecutor extends DonoRegistryCommand implements CommandExecutor {
	private final String NO_PERMISSION = ChatColor.RED + "You do not have permission to use that command!";

	private CommandExecutor listCommand  = new ListCommandExecutor(plugin);

	public BaseCommandExecutor(DonoRegistry plugin) {
		super(plugin);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			printHelp(sender, label);
			return true;
		}

		if(args[0].equalsIgnoreCase("list")) {
			if(!sender.hasPermission("donoregistry.list")) {
				sender.sendMessage(NO_PERMISSION);
				return true;
			}
		listCommand.onCommand(sender, cmd, label, args);
		} else {
			printHelp(sender, label);
		}
		
		return true;
	}
}