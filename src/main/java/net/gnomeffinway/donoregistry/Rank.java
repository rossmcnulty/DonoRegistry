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

	public static Rank fromInt(int value) {
		switch (value) {
		case 1:
			return MINSTREL;
		case 2:
			return BARON;
		case 3:
			return DUKE;
		case 4:
			return ARCHDUKE;
		default:
			return null;
		}
	}
}