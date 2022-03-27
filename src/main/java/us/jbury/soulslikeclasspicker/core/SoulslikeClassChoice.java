package us.jbury.soulslikeclasspicker.core;

import us.jbury.soulslikeclasspicker.eldenring.EldenRingClass;

public abstract class SoulslikeClassChoice implements Comparable<SoulslikeClassChoice> {
	public final String className;
	public final int level;
	public final SoulslikeClass wastedStatsBreakdown;
	public final int totalWastedStats;

	public SoulslikeClassChoice(String className, int level, SoulslikeClass wastedStatsBreakdown, int wastedStats){
		this.className = className;
		this.level = level;
		this.wastedStatsBreakdown = wastedStatsBreakdown;
		this.totalWastedStats = wastedStats;
	}

	public String getNameWithBuffer(){
		return this.wastedStatsBreakdown.getNameWithBuffer();
	}

	public abstract int compareTo(SoulslikeClassChoice that);
}
