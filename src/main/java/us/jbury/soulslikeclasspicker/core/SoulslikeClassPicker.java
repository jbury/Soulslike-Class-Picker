package us.jbury.soulslikeclasspicker.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import us.jbury.soulslikeclasspicker.eldenring.EldenRingClassPickerHelper;
import us.jbury.soulslikeclasspicker.premades.EldenRingPremadeConstraints;

public class SoulslikeClassPicker {

	private final UnmodifiableSoulslikeClassPickerHelper classPickerHelper;

	public SoulslikeClassPicker(UnmodifiableSoulslikeClassPickerHelper classPickerHelper) throws IOException {
		this.classPickerHelper = classPickerHelper;
	}

	public static void main(String[] args) throws IOException {
		SoulslikeClassPicker picker = new SoulslikeClassPicker(new EldenRingClassPickerHelper());

		//Map of Stat to maximum desired value OF that stat in your end build.
		Map<Stat, Integer> constraints = EldenRingPremadeConstraints.arcaneBloodConstraints();

		List<BaseSoulslikeClassChoice> choices = picker.pickClass(constraints);

		Gson gsonPrinter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		for (BaseSoulslikeClassChoice c : choices) {
			System.out.println(c.getNameWithBuffer() + " (Wasted " + c.totalWastedStats + "):\t" +
				gsonPrinter.toJson(c.wastedStatsBreakdown));
		}
	}

	//Just prints level and total stats for each class
	//For whenever I worry that some classes have free points :)
	public void listClassDetails() {
		for (SoulslikeClass c : this.classPickerHelper.getClassList()) {
			int totalStats = 0;
			for (Stat s : this.classPickerHelper.getInternalStatsList()) {
				totalStats += c.getStat(s);
			}
			System.out.println(c.getNameWithBuffer() + " - " + (totalStats - c.getLevel()) +
				"original points (Level: " + c.getLevel() +
				" \t(Total Stats: " + totalStats + ")");
		}
	}

	public List<BaseSoulslikeClassChoice> pickClass(Map<Stat, Integer> constraints) {
		List<BaseSoulslikeClassChoice> choices = new ArrayList<BaseSoulslikeClassChoice>();

		Map<SoulslikeClass, Integer> wastedStatsPerClass = new HashMap<SoulslikeClass, Integer>();
		Map<SoulslikeClass, SoulslikeClass> classToWastedStatsMap =
			new HashMap<SoulslikeClass, SoulslikeClass>();
		for (SoulslikeClass c : this.classPickerHelper.getClassList()) {
			SoulslikeClassBuilder builder = this.classPickerHelper.getNewBuilder()
				.withClassName(c.getName())
				.withLevel(c.getLevel());
			int totalWasted = 0;
			for (Stat s : this.classPickerHelper.getInternalStatsList()) {
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

}
