package us.jbury.EldenRingClassPicker;

import us.jbury.EldenRingClassPicker.EldenRingClass.Stat;

public class ClassChoice implements Comparable<ClassChoice> {
	public final String className;
	public final EldenRingClass wastedStatsBreakdown;
	public final int totalWastedStats;

	public ClassChoice(String className, EldenRingClass wastedStatsBreakdown, int wastedStats){
		this.className = className;
		this.wastedStatsBreakdown = wastedStatsBreakdown;
		this.totalWastedStats = wastedStats;
	}

	/*TODO: Take into account the stats actually requested, consider Strength and Mind to be valuable
	"off-stats" as well.  Might require a complete revamp of the approach being used - haven't thought
	that through just yet.  Maybe all stats get a priority or weight assigned for the "all things equal"
	case?*/
	@Override
	public int compareTo(ClassChoice that) {
		if(this.totalWastedStats == that.totalWastedStats){
			//All things equal, more Arcane is usually better as it improves item find
			return that.wastedStatsBreakdown.getStat(Stat.arcane) -
				this.wastedStatsBreakdown.getStat(Stat.arcane);
		}

		return this.totalWastedStats - that.totalWastedStats;
	}
}
