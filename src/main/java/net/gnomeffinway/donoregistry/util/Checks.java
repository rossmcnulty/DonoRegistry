package net.gnomeffinway.donoregistry.util;

import net.citizensnpcs.Citizens;
import net.gnomeffinway.donoregistry.DonoRegistry;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Warps;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Checks {
	
	public static boolean npcCheck(int npc) {
		Citizens citi=DonoRegistry.getCitizens();
		
		if(citi.getNPCRegistry().getById(npc) !=null)
			return true;
		return false;
	}
	
	public static boolean regionCheck(String region) {
		WorldGuardPlugin wg=DonoRegistry.getWorldGuard();
		
		ProtectedRegion pr = wg.getGlobalRegionManager().get(wg.getServer().getWorld("world")).getRegionExact(region);
		
		if(pr != null)
			return true;
		
		return false;
	}
	
	public static boolean warpCheck(String warp) {
		Essentials ess=DonoRegistry.getEssentials();
		
		Warps warps=ess.getWarps();
		
		try {
			if(warps.getWarp(warp) != null)
				return true;
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
	
	public static boolean numCheck(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
}
