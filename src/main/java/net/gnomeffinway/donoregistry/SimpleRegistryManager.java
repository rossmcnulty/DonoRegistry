package net.gnomeffinway.donoregistry;

import java.util.List;

import net.gnomeffinway.donoregistry.api.RegistryManager;

public class SimpleRegistryManager implements RegistryManager {
	private DonoRegistry plugin;

	public SimpleRegistryManager(DonoRegistry plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public List<DonorRecord> getRecords() {
		return plugin.getDatabase().find(DonorRecord.class).findList();
	}

	@Override
	public DonorRecord getRecord(String playerName) {
		return plugin.getDatabase().find(DonorRecord.class).where().ieq("target", playerName).findUnique();
	}

	@Override
	public int addRecord(String targetName, String region, int npcs, String warp, String others, Rank rank) {
		int newDonorId = getRecords().size()+1;
		
		//Should alphabetize this for organization

		DonorRecord record = new DonorRecord();
		record.setTarget(targetName);
		record.setRegion(region);
		record.setNPCs(npcs);
		record.setOthers(others);
		record.setWarp(warp);
		record.setTime(System.currentTimeMillis());
		record.setRank(rank);

		plugin.getDatabase().save(record);

		return newDonorId;
	}
	
	@Override
	public void delRecord(String targetName) {
		DonorRecord record = plugin.getDatabase().find(DonorRecord.class).where().ieq("target", targetName).findUnique();
		plugin.getDatabase().delete(record);
	}

}
