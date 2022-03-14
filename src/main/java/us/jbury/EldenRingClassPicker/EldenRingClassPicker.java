package us.jbury.EldenRingClassPicker;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.jbury.EldenRingClassPicker.EldenRingClass.EldenRingClassBuilder;
import us.jbury.EldenRingClassPicker.EldenRingClass.Stat;


public class EldenRingClassPicker {
	private static final String ELDEN_RING_CLASSES_JSON = "EldenRingClasses.json";

	private final List<EldenRingClass> CLASSES;

	public EldenRingClassPicker() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(ELDEN_RING_CLASSES_JSON);
		Reader reader = new InputStreamReader(is, "UTF-8");

		 Gson gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.create();

		CLASSES = Collections.unmodifiableList(
			gson.fromJson(reader, new TypeToken<ArrayList<EldenRingClass>>(){}.getType()));
	}

	//Just prints level and total stats for each class
	//For whenever I worry that some classes have free points :)
	public void listClassDetails(){
		for(EldenRingClass c : CLASSES){
			int totalStats = 0;
			for(Stat s : Stat.values()){
				totalStats += c.getStat(s);
			}
			System.out.println(c.getNameWithBuffer() + " - Level: " + c.level +
				" \t(Total Stats: " + totalStats + ")");
		}
	}

	public List<ClassChoice> pickClass(Map<Stat, Integer> constraints){
		List<ClassChoice> choices = new ArrayList<ClassChoice>();

		Map<EldenRingClass, Integer> wastedStatsPerClass = new HashMap<EldenRingClass, Integer>();
		Map<EldenRingClass, EldenRingClass> classToWastedStatsMap =
			new HashMap<EldenRingClass, EldenRingClass>();
		for(EldenRingClass c : CLASSES){
			EldenRingClassBuilder builder = new EldenRingClassBuilder()
				.withClassName(c.className)
				.withLevel(c.level);
			int totalWasted = 0;
			for(Stat s : Stat.values()){
				Integer constraint = constraints.get(s);
				if(constraint != null && c.getStat(s) - constraint > 0) {
					builder.withStat(s, c.getStat(s) - constraint);
					totalWasted += c.getStat(s) - constraint;
				}
			}
			choices.add(new ClassChoice(c.className, c.level, builder.build(), totalWasted));
		}
		Collections.sort(choices);

		return choices;
	}

	public static void main(String[] args) throws IOException {
		EldenRingClassPicker picker = new EldenRingClassPicker();

		//Map of Stat to maximum desired value OF that stat in your end build.
		Map<Stat, Integer> constraints = PremadeConstraints.arcaneBloodConstraints();

		List<ClassChoice> choices = picker.pickClass(constraints);

		Gson gsonPrinter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		for(ClassChoice c : choices) {
			System.out.println(c.getNameWithBuffer() + " (Wasted " + c.totalWastedStats + "):\t" +
				gsonPrinter.toJson(c.wastedStatsBreakdown));
		}
	}


}
