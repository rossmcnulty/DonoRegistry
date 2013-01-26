package net.gnomeffinway.donoregistry.util;

public enum Sort {
	
	RANK(0),DATE(1);

	private int value;
	
	Sort(int value) {
		this.value=value;
	}
	
	public int toInt() {
		return value;
	}
	
	public static Sort fromInt(int value) {
		switch (value) {
		case 0:
				return RANK;
		case 1:
				return DATE;
		default:
				return null;
		}
	}
}