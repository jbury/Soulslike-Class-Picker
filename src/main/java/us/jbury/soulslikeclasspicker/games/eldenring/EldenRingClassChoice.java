package us.jbury.soulslikeclasspicker.games.eldenring;

import us.jbury.soulslikeclasspicker.core.BaseSoulsLikeClassChoice;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClass;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClassChoice;

public class EldenRingClassChoice extends BaseSoulsLikeClassChoice {

	public EldenRingClassChoice(String className, int level,
		SoulsLikeClass wastedStatsBreakdown,
		int wastedStats) {
		super(className, level, wastedStatsBreakdown, wastedStats);
	}

	/**
	 * compareTo that returns positive values when `this` is a "more wasteful" class choice than
	 * `that`.  First layer compares total wastedStats, as we want to minimize wasted stats first
	 * and foremost.  Second layer uses the higher wasted Arcane value as the tiebreaker, treating
	 * the class with the higher Arcane value as less wasteful.  If totalWastedStats _AND_ wasted
	 * Arcane are both equal, the final tiebreaker is Strength.
	 *
	 * TotalWasted stats is considered the most important value to minimize as we want to put as
	 * many stats as possible into stats we actually care about.  Arcane is considered the second
	 * most important value (to _maximize_, not minimize) as Arcane increases item find rate, which
	 * is generally useful to any and every build imaginable.  Finally, we look to Strength as a
	 * value to maximize as increasing strength increases the total encumbrance, allowing for more
	 * total armor/poise.
	 *
	 * Finally, maximize level, so we don't have to spend as much time getting runes.
	 */
	@Override
	public int compareTo(SoulsLikeClassChoice that) {
		// If there's a difference in totalWastedStats, that's our main sorting criteria
		if (this.totalWastedStats != that.getTotalWastedStats()) {
			return this.totalWastedStats - that.getTotalWastedStats();
		}

		// Use Arcane as first tiebreaker if totalWastedStats are equal
		int thatArcaneMinusThisArcane =
			that.getWastedStatsBreakdown().getStat(EldenRingStat.arcane) -
				this.wastedStatsBreakdown.getStat(EldenRingStat.arcane);
		if (thatArcaneMinusThisArcane != 0) {
			return thatArcaneMinusThisArcane;
		}

		// Use Strength as second tiebreaker if Arcane is also equivalent
		int thatStrengthMinusThisStrength =
			that.getWastedStatsBreakdown().getStat(EldenRingStat.strength) -
				this.wastedStatsBreakdown.getStat(EldenRingStat.strength);
		if (thatStrengthMinusThisStrength != 0) {
			return thatStrengthMinusThisStrength;
		}

		//Somehow totalWastedStats, Arcane, and Strength are equal.  Pick highest level for
		// convenience
		return that.getLevel() - this.level;
	}
}
