package net.gnomeffinway.donoregistry.commands;

import net.gnomeffinway.donoregistry.DonoRegistry;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class DonoRegistryCommand {
	protected DonoRegistry plugin;

	protected final String COULD_NOT_FIND_PLAYER = ChatColor.RED + "Could not find that player!";

	public DonoRegistryCommand(DonoRegistry plugin) {
		this.plugin = plugin;
	}

	protected String findTarget(String target) {
		OfflinePlayer search = plugin.getServer().getPlayer(target);

		if(search == null) {
			plugin.getServer().getOfflinePlayer(target);
		}

		if(search != null) {
			target = search.getName();
		}

		return target;
	}

	protected void printArgsError(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Invalid arguments passed to ");
		sb.append(this.getClass().getName());
		sb.append(": ");
		for(String arg : args) {
			sb.append(arg);
			sb.append(" ");
		}
		plugin.getLogger().severe(sb.toString());
	}

	protected void printHelp(CommandSender sender, String label) {
		if(sender.hasPermission("donoregistry.list")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " list");
		}
	}
}
