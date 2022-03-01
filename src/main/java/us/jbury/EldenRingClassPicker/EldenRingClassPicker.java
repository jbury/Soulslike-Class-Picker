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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

	public List<ClassChoice> pickClass(Set<Stat> wastingStats){
		List<ClassChoice> choices = new ArrayList<ClassChoice>();

		Map<EldenRingClass, Integer> wastedStatsPerClass = new HashMap<EldenRingClass, Integer>();
		for(EldenRingClass c : CLASSES){
			int wasted = 0;
			for(Stat s : Stat.values()){
				if(wastingStats.contains(s)){
					wasted += c.getStat(s);
				}
			}
			choices.add(new ClassChoice(c.className, wasted));
		}
		Collections.sort(choices);

		return choices;
	}

	public static void main(String[] args) throws IOException {
		EldenRingClassPicker picker = new EldenRingClassPicker();

		Set<Stat> constraints = new HashSet<Stat>();
		constraints.add(Stat.arcane);
		constraints.add(Stat.dexterity);
		constraints.add(Stat.strength);
		constraints.add(Stat.intelligence);

		List<ClassChoice> choices = picker.pickClass(constraints);

		for(ClassChoice c : choices) {
			System.out.println(c.className + " : " + c.wastedStats);
		}
	}
}
