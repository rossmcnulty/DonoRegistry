package net.gnomeffinway.donoregistry;

public class Donor {
	
	private Rank rank;
	private String name;
	private String npcs;
	private String others;
	private String warp;
	private String region;
	
	public Donor(String name, Rank rank){
		this.name=name;
		this.rank=rank;
		this.npcs="N/A";
		this.others="N/A";
		this.warp="N/A";
		this.region="N/A";
	}
	
	public Donor(String name, Rank rank, String region, String warp, String npcs, String others) {
		this.name=name;
		this.rank=rank;
		this.npcs=npcs;
		this.others=others;
		this.warp=warp;
		this.region=region;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank=rank;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region=region;
	}
	
	public String getWarp() {
		return warp;
	}
	
	public void setWarp(String warp) {
		this.warp=warp;
	}
	
	public String getNPCs() {
		return npcs;
	}
	
	public void setNPCs(String npcs) {
		this.npcs=npcs;
	}
	
	public void addNPC(String npc) {
		npcs+=","+npc;
	}
	
	public String getOthers() {
		return others;
	}
	
	public void setOthers(String others) {
		this.others=others;
	}
	
	public void addOther(String other) {
		others+=","+other;
	}
	
}
