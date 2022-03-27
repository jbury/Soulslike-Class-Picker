package us.jbury.soulslikeclasspicker.core;

public abstract class BaseSoulslikeClassChoice implements Comparable<BaseSoulslikeClassChoice> {

	public final String className;
	public final int level;
	public final SoulslikeClass wastedStatsBreakdown;
	public final int totalWastedStats;

	public BaseSoulslikeClassChoice(String className, int level,
		SoulslikeClass wastedStatsBreakdown,
		int wastedStats) {
		this.className = className;
		this.level = level;
		this.wastedStatsBreakdown = wastedStatsBreakdown;
		this.totalWastedStats = wastedStats;
	}

	public String getNameWithBuffer() {
		return this.wastedStatsBreakdown.getNameWithBuffer();
	}

	public abstract int compareTo(BaseSoulslikeClassChoice that);
}
