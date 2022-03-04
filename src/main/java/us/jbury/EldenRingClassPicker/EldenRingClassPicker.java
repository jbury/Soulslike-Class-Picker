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

		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
		CLASSES = gson.fromJson(reader, new TypeToken<ArrayList<EldenRingClass>>(){}.getType());
	}

	public List<ClassChoice> pickClass(Map<Stat, Integer> constraints){
		List<ClassChoice> choices = new ArrayList<ClassChoice>();

		Map<EldenRingClass, Integer> wastedStatsPerClass = new HashMap<EldenRingClass, Integer>();
		Map<EldenRingClass, EldenRingClass> classToWastedStatsMap = new HashMap<EldenRingClass, EldenRingClass>();
		for(EldenRingClass c : CLASSES){
			EldenRingClassBuilder builder = new EldenRingClassBuilder().withClassName(c.className);
			int totalWasted = 0;
			for(Stat s : Stat.values()){
				Integer constraint = constraints.get(s);
				if(constraint != null && c.getStat(s) - constraint > 0) {
					builder.withStat(s, c.getStat(s) - constraint);
					totalWasted += c.getStat(s) - constraint;
				}
			}
			choices.add(new ClassChoice(c.className, builder.build(), totalWasted));
		}
		Collections.sort(choices);

		return choices;
	}

	public static void main(String[] args) throws IOException {
		EldenRingClassPicker picker = new EldenRingClassPicker();

		//Map of Stat to maximum desired value OF that stat in your end build.
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		//This is for a pure Strength build that caps Dexterity at 18
		constraints.put(Stat.dexterity, 18);
		constraints.put(Stat.faith, 0);
		constraints.put(Stat.arcane, 0);
		constraints.put(Stat.intelligence, 0);

		List<ClassChoice> choices = picker.pickClass(constraints);

		for(ClassChoice c : choices) {
			System.out.println(c.className + " (Wasted " + c.totalWastedStats + "): " + new Gson().toJson(c.wastedStatsBreakdown));
		}
	}
}
