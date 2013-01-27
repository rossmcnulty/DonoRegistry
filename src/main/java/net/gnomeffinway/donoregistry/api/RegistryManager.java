package net.gnomeffinway.donoregistry.api;

import java.util.List;

import net.gnomeffinway.donoregistry.Donor;
import net.gnomeffinway.donoregistry.DonorRecord;

public interface RegistryManager {
	
	public List<DonorRecord> getRecords();

	public DonorRecord getRecord(String playerName);
	
	public int addRecord(Donor donor);
	
	public void delRecord(String targetName);

}
