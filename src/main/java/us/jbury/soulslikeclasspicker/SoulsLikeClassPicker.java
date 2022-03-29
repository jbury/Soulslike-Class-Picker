package us.jbury.soulslikeclasspicker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClass;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClassBuilder;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClassChoice;
import us.jbury.soulslikeclasspicker.core.Stat;
import us.jbury.soulslikeclasspicker.games.eldenring.EldenRingClassPickerHelper;
import us.jbury.soulslikeclasspicker.premades.EldenRingPremadeConstraints;

public class SoulsLikeClassPicker {

	private final SoulsLikeClassSummarizer summarizer;
	private final ClassPickerHelper classPickerHelper;

	public SoulsLikeClassPicker(ClassPickerHelper classPickerHelper) {
		this.classPickerHelper = classPickerHelper;
		this.summarizer = new SoulsLikeClassSummarizer(classPickerHelper);
	}

	public static void main(String[] args) throws IOException {
		SoulsLikeClassPicker picker = new SoulsLikeClassPicker(new EldenRingClassPickerHelper());

		//Map of Stat to maximum desired value OF that stat in your end build.
		Map<Stat, Integer> constraints = EldenRingPremadeConstraints.StengthFaithConstraints();

		List<SoulsLikeClassChoice> choices = picker.pickClass(constraints);

		Gson gsonPrinter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		for (SoulsLikeClassChoice c : choices) {
			System.out.println(
				c.getNameWithBuffer() + " (Wasted " + c.getTotalWastedStats() + "):\t" +
					gsonPrinter.toJson(c.getWastedStatsBreakdown()));
		}
	}

	public List<SoulsLikeClassChoice> pickClass(Map<Stat, Integer> constraints) {
		return this.pickClass(constraints, true);
	}

	public List<SoulsLikeClassChoice> pickClass(Map<Stat, Integer> constraints,
		boolean checkSummaries) {
		if (checkSummaries) {
			if (!this.summarizer.allClassesStartEqual()) {
				this.summarizer.printSummaries();
				System.exit(1);
			}
		}

		List<SoulsLikeClassChoice> choices = new ArrayList<SoulsLikeClassChoice>();

		Map<SoulsLikeClass, Integer> wastedStatsPerClass = new HashMap<SoulsLikeClass, Integer>();
		Map<SoulsLikeClass, SoulsLikeClass> classToWastedStatsMap =
			new HashMap<SoulsLikeClass, SoulsLikeClass>();
		for (SoulsLikeClass c : this.classPickerHelper.getClassList()) {
			SoulsLikeClassBuilder builder = this.classPickerHelper.getNewBuilder()
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

		public List<SoulsLikeClass> getClassList();

		public List<Stat> getStatsList();

		public SoulsLikeClassBuilder getNewBuilder();

		public SoulsLikeClassChoice getNewClassChoice(String className, int level,
			SoulsLikeClass wastedStatsBreakdown, int wastedStats);
	}
}
