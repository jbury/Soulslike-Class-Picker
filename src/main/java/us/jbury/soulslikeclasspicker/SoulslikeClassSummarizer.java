package us.jbury.soulslikeclasspicker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import us.jbury.soulslikeclasspicker.core.SoulslikeClass;
import us.jbury.soulslikeclasspicker.core.Stat;
import us.jbury.soulslikeclasspicker.core.UnmodifiableSoulslikeClassPickerHelper;

public class SoulslikeClassSummarizer {

	private final List<ClassSummary> summaries;

	public SoulslikeClassSummarizer(UnmodifiableSoulslikeClassPickerHelper pickerHelper) {
		List<ClassSummary> intermediateSummaries = new ArrayList<ClassSummary>();
		for (SoulslikeClass c : pickerHelper.getClassList()) {
			int totalStats = 0;
			for (Stat s : pickerHelper.getStatsList()) {
				totalStats += c.getStat(s);
			}
			intermediateSummaries.add(new ClassSummary(c.getName(), c.getLevel(), totalStats));
		}
		Collections.sort(intermediateSummaries);

		this.summaries = Collections.unmodifiableList(intermediateSummaries);
	}

	public boolean allClassesStartEqual() {
		int startingPoints = -1;
		for (ClassSummary summary : this.summaries) {
			int startingStatsForClass = summary.totalStats - summary.level;

			if (startingPoints == -1) {
				startingPoints = startingStatsForClass;
			} else if (startingStatsForClass != startingPoints) {
				return false;
			}
		}

		return true;
	}

	public void printSummaries() {
		for (ClassSummary cs : this.summaries) {
			System.out.println(cs.toString());
		}
	}

	private class ClassSummary implements Comparable<ClassSummary> {

		public final String name;
		public final int level, totalStats;

		public ClassSummary(String name, int level, int totalStats) {
			this.name = name;
			this.level = level;
			this.totalStats = totalStats;
		}

		@Override
		public int compareTo(ClassSummary that) {
			int thisStart = this.totalStats - this.level;
			int thatStart = that.totalStats - that.level;

			return thisStart - thatStart;
		}

		public String toString() {
			return this.name + "\t\t-\t" + this.totalStats + "\t(Level: " + this.level +
				"\ttotalStats: " + this.totalStats + ")";
		}
	}
}
