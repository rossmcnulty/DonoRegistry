package net.gnomeffinway.donoregistry.commands;

import net.gnomeffinway.donoregistry.DonoRegistry;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class DonoRegistryCommand {
	protected DonoRegistry plugin;

	protected final String COULD_NOT_FIND_PLAYER = ChatColor.RED + "Could not find that player!";
	protected final String COULD_NOT_FIND_DONOR = ChatColor.RED + "Could not find that donor!";
	protected final String DONOR_LIST_EMPTY = ChatColor.RED + "Donor list is empty!";
	protected final String RANK_NOT_VALID = ChatColor.RED + "Rank entered is incorrect!";
	protected final String RECORD_ALREADY_EXISTS = ChatColor.RED + "That donor has already been added!";
	protected final String COULD_NOT_FIND_REGION = ChatColor.RED + "Region entered doesn't exist!";
	protected final String COULD_NOT_FIND_WARP = ChatColor.RED + "Warp entered doesn't exist!";
	protected final String COULD_NOT_FIND_NPC = ChatColor.RED + "NPC entered doesn't exist!";

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
		if(sender.hasPermission("donoregistry.info")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " info <player>");
		}
		if(sender.hasPermission("donoregistry.list")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " list");
		}
		if(sender.hasPermission("donoregistry.new")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " new <player> <rank>");
		}
		if(sender.hasPermission("donoregistry.set")) {
			sender.sendMessage(ChatColor.GRAY + "/" + label + " set <player> <param> <value>");
		}
	}
}
