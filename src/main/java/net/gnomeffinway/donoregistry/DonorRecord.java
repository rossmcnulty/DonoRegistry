package net.gnomeffinway.donoregistry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity
@Table(name="dr_log")
public class DonorRecord {
	
	@Id
	@Column(name="id")
	private int id;

	@NotEmpty
	@Column(name="target")
	private String target;

	@NotEmpty
	@Column(name="region")
	private String region;

	@NotEmpty
	@Column(name="warp")
	private String warp;

	@NotNull
	@Column(name="time")
	private long time;

	@NotNull
	@Column(name="others")
	private String others;

	@NotNull
	@Column(name="npcs")
	private String npcs;

	@Enumerated
	@Column(name="rank")
	private Rank rank;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getWarp() {
		return warp;
	}

	public void setWarp(String warp) {
		this.warp = warp;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}
	
	public String getNpcs() {
		return npcs;
	}

	public void setNpcs(String npcs) {
		this.npcs = npcs;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
}