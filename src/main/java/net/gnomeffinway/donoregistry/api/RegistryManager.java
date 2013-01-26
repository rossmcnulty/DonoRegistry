package net.gnomeffinway.donoregistry.api;

import java.util.List;

import net.gnomeffinway.donoregistry.DonorRecord;
import net.gnomeffinway.donoregistry.Rank;


public interface RegistryManager {
	
	public List<DonorRecord> getRecords();

	public DonorRecord getRecord(String playerName);
	
	public int addRecord(String targetName, String region, int npcs, String warp, String others, Rank rank);
	
	public void delRecord(String targetName);

}
