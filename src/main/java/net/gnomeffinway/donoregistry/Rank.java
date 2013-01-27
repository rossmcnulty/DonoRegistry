package net.gnomeffinway.donoregistry;

public enum Rank {
	MINSTREL(1), BARON(2), DUKE(3), ARCHDUKE(4);

	private int value;

	Rank(int value) {
		this.value = value;
	}

	public int toInt() {
		return value;
	}

	public static Rank fromString(String value) {
		switch (value) {
		case "Minstrel":
			return MINSTREL;
		case "Baron":
			return BARON;
		case "Duke":
			return DUKE;
		case "Archduke":
			return ARCHDUKE;
		default:
			return null;
		}
	}
	
	public String toString() {
		switch (value) {
		case 1:
			return "Minstrel";
		case 2:
			return "Baron";
		case 3:
			return "Duke";
		case 4:
			return "Archduke";
		default:
			return null;
		}
	}
}