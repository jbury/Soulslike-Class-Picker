package us.jbury.soulslikeclasspicker.core;

public abstract class BaseSoulslikeClassChoice implements SoulslikeClassChoice {

	protected final String className;
	protected final int level;
	protected final SoulslikeClass wastedStatsBreakdown;
	protected final int totalWastedStats;

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

	public String getClassName(){
		return this.className;
	}

	public int getLevel(){
		return this.level;
	}

	public int getTotalWastedStats(){
		return this.totalWastedStats;
	}

	public SoulslikeClass getWastedStatsBreakdown(){
		return this.wastedStatsBreakdown;
	}

	public abstract int compareTo(SoulslikeClassChoice that);
}
