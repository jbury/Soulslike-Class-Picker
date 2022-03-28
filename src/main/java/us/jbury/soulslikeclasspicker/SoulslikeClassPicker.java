package us.jbury.soulslikeclasspicker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import us.jbury.soulslikeclasspicker.core.SoulslikeClass;
import us.jbury.soulslikeclasspicker.core.SoulslikeClassBuilder;
import us.jbury.soulslikeclasspicker.core.SoulslikeClassChoice;
import us.jbury.soulslikeclasspicker.core.Stat;
import us.jbury.soulslikeclasspicker.games.eldenring.EldenRingClassPickerHelper;
import us.jbury.soulslikeclasspicker.premades.EldenRingPremadeConstraints;

public class SoulslikeClassPicker {

	private final SoulslikeClassSummarizer summarizer;
	private final ClassPickerHelper classPickerHelper;

	public SoulslikeClassPicker(ClassPickerHelper classPickerHelper) {
		this.classPickerHelper = classPickerHelper;
		this.summarizer = new SoulslikeClassSummarizer(classPickerHelper);
	}

	public static void main(String[] args) throws IOException {
		SoulslikeClassPicker picker = new SoulslikeClassPicker(new EldenRingClassPickerHelper());

		//Map of Stat to maximum desired value OF that stat in your end build.
		Map<Stat, Integer> constraints = EldenRingPremadeConstraints.StengthFaithConstraints();

		List<SoulslikeClassChoice> choices = picker.pickClass(constraints);

		Gson gsonPrinter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		for (SoulslikeClassChoice c : choices) {
			System.out.println(
				c.getNameWithBuffer() + " (Wasted " + c.getTotalWastedStats() + "):\t" +
					gsonPrinter.toJson(c.getWastedStatsBreakdown()));
		}
	}

	public List<SoulslikeClassChoice> pickClass(Map<Stat, Integer> constraints) {
		return this.pickClass(constraints, true);
	}

	public List<SoulslikeClassChoice> pickClass(Map<Stat, Integer> constraints,
		boolean checkSummaries) {
		if (checkSummaries) {
			if (!this.summarizer.allClassesStartEqual()) {
				this.summarizer.printSummaries();
				System.exit(1);
			}
		}

		List<SoulslikeClassChoice> choices = new ArrayList<SoulslikeClassChoice>();

		Map<SoulslikeClass, Integer> wastedStatsPerClass = new HashMap<SoulslikeClass, Integer>();
		Map<SoulslikeClass, SoulslikeClass> classToWastedStatsMap =
			new HashMap<SoulslikeClass, SoulslikeClass>();
		for (SoulslikeClass c : this.classPickerHelper.getClassList()) {
			SoulslikeClassBuilder builder = this.classPickerHelper.getNewBuilder()
				.withClassName(c.getName())
				.withLevel(c.getLevel());
			int totalWasted = 0;
			for (Stat s : this.classPickerHelper.getStatsList()) {
				Integer constraint = constraints.get(s);
				if (constraint != null && c.getStat(s) - constraint > 0) {
					builder.withStat(s, c.getStat(s) - constraint);
					totalWasted += c.getStat(s) - constraint;
				}
			}
			choices.add(
				this.classPickerHelper.getNewClassChoice(c.getName(), c.getLevel(), builder.build(),
					totalWasted));
		}
		Collections.sort(choices);

		return choices;
	}

	public interface ClassPickerHelper {

		public List<SoulslikeClass> getClassList();

		public List<Stat> getStatsList();

		public SoulslikeClassBuilder getNewBuilder();

		public SoulslikeClassChoice getNewClassChoice(String className, int level,
			SoulslikeClass wastedStatsBreakdown, int wastedStats);
	}
}
