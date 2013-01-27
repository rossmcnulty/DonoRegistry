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
	public DonorRecord getRecord(int id) {
		return plugin.getDatabase().find(DonorRecord.class).where().idEq(id).findUnique();
	}

	@Override
	public int addRecord(Donor donor) {
		int newDonorId = getRecords().size()+1;
		
		DonorRecord record = new DonorRecord();
		record.setId(newDonorId);
		record.setTarget(donor.getName());
		record.setRegion(donor.getRegion());
		record.setNpcs(donor.getNPCs());
		record.setOthers(donor.getOthers());
		record.setWarp(donor.getWarp());
		record.setTime(System.currentTimeMillis());
		record.setRank(donor.getRank());
		
		plugin.getDatabase().save(record);

		return newDonorId;
	}
	
	@Override
	public void delRecord(String targetName) {
		DonorRecord record = plugin.getDatabase().find(DonorRecord.class).where().ieq("target", targetName).findUnique();
		plugin.getDatabase().delete(record);
	}

}
