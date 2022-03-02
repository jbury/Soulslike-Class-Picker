package us.jbury.EldenRingClassPicker;

public class ClassChoice implements Comparable<ClassChoice> {
	public final String className;
	public final EldenRingClass wastedStatsBreakdown;
	public final int totalWastedStats;

	public ClassChoice(String className, EldenRingClass wastedStatsBreakdown, int wastedStats){
		this.className = className;
		this.wastedStatsBreakdown = wastedStatsBreakdown;
		this.totalWastedStats = wastedStats;
	}

	@Override
	public int compareTo(ClassChoice that) {
		if(this.totalWastedStats == that.totalWastedStats){
			return this.className.compareTo(that.className);
		}

		return this.totalWastedStats - that.totalWastedStats;
	}
}
