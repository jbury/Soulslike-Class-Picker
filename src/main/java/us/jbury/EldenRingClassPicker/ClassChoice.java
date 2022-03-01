package us.jbury.EldenRingClassPicker;

public class ClassChoice implements Comparable<ClassChoice> {
	public final String className;
	public final int wastedStats;

	public ClassChoice(String className, int wastedStats){
		this.className = className;
		this.wastedStats = wastedStats;
	}

	@Override
	public int compareTo(ClassChoice that) {
		if(this.wastedStats == that.wastedStats){
			return this.className.compareTo(that.className);
		}

		return this.wastedStats - that.wastedStats;
	}
}
