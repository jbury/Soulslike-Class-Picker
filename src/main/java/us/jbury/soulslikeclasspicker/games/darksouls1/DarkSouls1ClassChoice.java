package us.jbury.soulslikeclasspicker.games.darksouls1;

import us.jbury.soulslikeclasspicker.core.BaseSoulsLikeClassChoice;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClass;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClassChoice;

public class DarkSouls1ClassChoice extends BaseSoulsLikeClassChoice {

	public DarkSouls1ClassChoice(String className, int level,
		SoulsLikeClass wastedStatsBreakdown, int wastedStats) {
		super(className, level, wastedStatsBreakdown, wastedStats);
	}

	@Override
	public int compareTo(SoulsLikeClassChoice that) {
		return this.totalWastedStats - that.getTotalWastedStats();
	}
}
