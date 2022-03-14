package us.jbury.EldenRingClassPicker;

import us.jbury.EldenRingClassPicker.EldenRingClass.Stat;

public class ClassChoice implements Comparable<ClassChoice> {
	public final String className;
	public final int level;
	public final EldenRingClass wastedStatsBreakdown;
	public final int totalWastedStats;

	public ClassChoice(String className, int level, EldenRingClass wastedStatsBreakdown, int wastedStats){
		this.className = className;
		this.level = level;
		this.wastedStatsBreakdown = wastedStatsBreakdown;
		this.totalWastedStats = wastedStats;
	}

	public String getNameWithBuffer(){
		return this.wastedStatsBreakdown.getNameWithBuffer();
	}

	/**
	 * compareTo that returns positive values when `this` is a "more wasteful" class choice than
	 * `that`.  First layer compares total wastedStats, as we want to minimize wasted stats first
	 * and foremost.  Second layer uses the higher wasted Arcane value as the tiebreaker, treating
	 * the class with the higher Arcane value as less wasteful.  If totalWastedStats _AND_ wasted
	 * Arcane are both equal, the final tiebreaker is Strength.
	 *
	 * TotalWasted stats is considered the most important value to minimize as we want to put
	 * as many stats as possible into stats we actually care about.  Arcane is considered the second
	 * most important value (to _maximize_, not minimize) as Arcane increases item find rate, which
	 * is generally useful to any and every build imaginable.  Finally, we look to Strength as a
	 * value to maximize as increasing strength increases the total encumbrance, allowing for more
	 * total armor/poise.
	 *
	 * Finally, maximize level, so we don't have to spend as much time getting runes.
	 */
	@Override
	public int compareTo(ClassChoice that) {
		// If there's a difference in totalWastedStats, that's our main sorting criteria
		if(this.totalWastedStats != that.totalWastedStats) {
			return this.totalWastedStats - that.totalWastedStats;
		}

		int thatArcaneMinusThisArcane = that.wastedStatsBreakdown.getStat(Stat.arcane) -
			this.wastedStatsBreakdown.getStat(Stat.arcane);
		// Use Arcane as first tiebreaker if totalWastedStats are equal
		if(thatArcaneMinusThisArcane != 0){
			return thatArcaneMinusThisArcane;
		} else { // Use Strength as second tiebreaker if Arcane is also equivalent
			int thatStrengthMinusThisStrength = that.wastedStatsBreakdown.getStat(Stat.strength) -
				this.wastedStatsBreakdown.getStat(Stat.strength);
			if(thatStrengthMinusThisStrength != 0){
				return thatStrengthMinusThisStrength;
			} else { //Somehow totalWastedStats, Arcane, and Strength are equal.  Pick highest level
				return that.level - this.level;
			}
		}
	}

}
