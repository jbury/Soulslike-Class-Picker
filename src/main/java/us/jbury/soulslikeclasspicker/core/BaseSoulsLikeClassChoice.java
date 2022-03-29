package us.jbury.soulslikeclasspicker.core;

public abstract class BaseSoulsLikeClassChoice implements SoulsLikeClassChoice {

	protected final String className;
	protected final int level;
	protected final SoulsLikeClass wastedStatsBreakdown;
	protected final int totalWastedStats;

	public BaseSoulsLikeClassChoice(String className, int level,
		SoulsLikeClass wastedStatsBreakdown,
		int wastedStats) {
		this.className = className;
		this.level = level;
		this.wastedStatsBreakdown = wastedStatsBreakdown;
		this.totalWastedStats = wastedStats;
	}

	public String getNameWithBuffer() {
		return this.wastedStatsBreakdown.getNameWithBuffer();
	}

	public String getClassName() {
		return this.className;
	}

	public int getLevel() {
		return this.level;
	}

	public int getTotalWastedStats() {
		return this.totalWastedStats;
	}

	public SoulsLikeClass getWastedStatsBreakdown() {
		return this.wastedStatsBreakdown;
	}

	public abstract int compareTo(SoulsLikeClassChoice that);
}
