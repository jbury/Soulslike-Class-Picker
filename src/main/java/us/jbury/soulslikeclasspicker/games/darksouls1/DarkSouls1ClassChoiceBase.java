package us.jbury.soulslikeclasspicker.games.darksouls1;

import us.jbury.soulslikeclasspicker.core.BaseSoulslikeClassChoice;
import us.jbury.soulslikeclasspicker.core.SoulslikeClass;
import us.jbury.soulslikeclasspicker.core.SoulslikeClassChoice;

public class DarkSouls1ClassChoiceBase extends BaseSoulslikeClassChoice {

	public DarkSouls1ClassChoiceBase(String className, int level,
		SoulslikeClass wastedStatsBreakdown, int wastedStats) {
		super(className, level, wastedStatsBreakdown, wastedStats);
	}

	@Override
	public int compareTo(SoulslikeClassChoice that) {
		return this.totalWastedStats - that.getTotalWastedStats();
	}
}
