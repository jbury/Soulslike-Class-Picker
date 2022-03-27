package us.jbury.soulslikeclasspicker.darksouls1;

import us.jbury.soulslikeclasspicker.core.SoulslikeClass;
import us.jbury.soulslikeclasspicker.core.SoulslikeClassChoice;

public class DarkSouls1ClassChoice extends SoulslikeClassChoice {

	public DarkSouls1ClassChoice(String className, int level,
		SoulslikeClass wastedStatsBreakdown, int wastedStats) {
		super(className, level, wastedStatsBreakdown, wastedStats);
	}

	@Override
	public int compareTo(SoulslikeClassChoice that) {
		return this.totalWastedStats - that.totalWastedStats;
	}
}
