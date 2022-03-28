package us.jbury.soulslikeclasspicker.games.eldenring;

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
import java.util.List;
import us.jbury.soulslikeclasspicker.core.SoulslikeClass;
import us.jbury.soulslikeclasspicker.core.SoulslikeClassBuilder;
import us.jbury.soulslikeclasspicker.core.SoulslikeClassChoice;
import us.jbury.soulslikeclasspicker.core.Stat;
import us.jbury.soulslikeclasspicker.core.UnmodifiableSoulslikeClassPickerHelper;


public class EldenRingClassPickerHelper extends UnmodifiableSoulslikeClassPickerHelper {

	private static final String ELDEN_RING_CLASSES_JSON = "EldenRingClasses.json";

	private final List<SoulslikeClass> CLASSES;

	private final List<Stat> STATS;

	public EldenRingClassPickerHelper() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(ELDEN_RING_CLASSES_JSON);
		Reader reader = new InputStreamReader(is, "UTF-8");

		Gson gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.create();

		CLASSES = Collections.unmodifiableList(
			gson.fromJson(reader, new TypeToken<ArrayList<EldenRingClass>>() {
			}.getType()));

		STATS = List.of(EldenRingStat.values());
	}

	@Override
	public List<Stat> getInternalStatsList() {
		return this.STATS;
	}

	@Override
	protected List<SoulslikeClass> getInternalClassList() {
		return this.CLASSES;
	}

	@Override
	public SoulslikeClassBuilder getNewBuilder() {
		return new EldenRingClassBuilder();
	}

	@Override
	public SoulslikeClassChoice getNewClassChoice(String className, int level,
		SoulslikeClass wastedStatsBreakdown, int wastedStats) {
		return new EldenRingClassChoice(className, level, wastedStatsBreakdown, wastedStats);
	}
}
