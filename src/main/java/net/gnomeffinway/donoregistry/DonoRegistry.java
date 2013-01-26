package net.gnomeffinway.donoregistry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import net.gnomeffinway.donoregistry.api.RegistryManager;
import net.gnomeffinway.donoregistry.commands.BaseCommandExecutor;

import org.bukkit.plugin.java.JavaPlugin;

public class DonoRegistry extends JavaPlugin {
	private static RegistryManager manager;
	
	@Override
	public void onEnable() {
		initDatabase();

		manager = new SimpleRegistryManager(this);
		
		getCommand("donoregistry").setExecutor(new BaseCommandExecutor(this));
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		getLogger().info("Finished loading " + getDescription().getFullName());
	}

	@Override
	public void onDisable() {
		getLogger().info("Finished unloading " + getDescription().getFullName());
	}
	
	private void initDatabase() {
		try {
			getDatabase().find(DonorRecord.class).findRowCount();
		} catch (PersistenceException ex) {
			getLogger().info("Initializing database");
			this.installDDL();
		}
	}
	
	@Override
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(DonorRecord.class);
		return list;
	}

	public static RegistryManager getManager() {
		return manager;
	}
}
