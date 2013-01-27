package net.gnomeffinway.donoregistry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import net.citizensnpcs.Citizens;
import net.gnomeffinway.donoregistry.api.RegistryManager;
import net.gnomeffinway.donoregistry.commands.BaseCommandExecutor;

import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class DonoRegistry extends JavaPlugin {
	private static RegistryManager manager;
	private static WorldGuardPlugin wg;
	private static Essentials ess;
	private static Citizens citi;
	
	@Override
	public void onEnable() {
		initDatabase();

		manager = new SimpleRegistryManager(this);
		
		getCommand("donoregistry").setExecutor(new BaseCommandExecutor(this));
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		checkPlugins();
		
		getLogger().info("Finished loading " + getDescription().getFullName());
	}

	@Override
	public void onDisable() {
		getLogger().info("Finished unloading " + getDescription().getFullName());
	}
	
	public void checkPlugins() {
	     
		wg=(WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
        ess=(Essentials) getServer().getPluginManager().getPlugin("Essentials");
		citi=(Citizens) getServer().getPluginManager().getPlugin("Citizens");   
		
        if(wg != null) {
			getServer().getLogger().info("[DonoRegistry] WorldGuard hooked");
        }
        else {
        	getServer().getLogger().info("[DonoRegistry] WorldGuard not found");
			this.getPluginLoader().disablePlugin(this);
			return;
		}
        
        if(citi != null) {
			getServer().getLogger().info("[DonoRegistry] Citizens hooked");
        }
        else {
        	getServer().getLogger().info("[DonoRegistry] Citizens not found");
			this.getPluginLoader().disablePlugin(this);
			return;
		}
		
        if(ess != null) {
			getServer().getLogger().info("[DonoRegistry] Essentials hooked");
        }
        else {
        	getServer().getLogger().info("[DonoRegistry] Essentials not found");
			this.getPluginLoader().disablePlugin(this);
			return;
		}
		
	}
	
	public static WorldGuardPlugin getWorldGuard() {
		return wg;
	}
	
	public static Essentials getEssentials() {
		return ess;
	}
	
	public static Citizens getCitizens() {
		return citi;
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
