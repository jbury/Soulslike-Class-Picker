package us.jbury.soulslikeclasspicker.games.darksouls1;

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
import us.jbury.soulslikeclasspicker.core.SoulsLikeClass;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClassBuilder;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClassChoice;
import us.jbury.soulslikeclasspicker.core.Stat;
import us.jbury.soulslikeclasspicker.core.UnmodifiableSoulsLikeClassPickerHelper;

public class DarkSouls1ClassPickerHelper extends
	UnmodifiableSoulsLikeClassPickerHelper {

	private static final String DARK_SOULS_1_CLASSES_JSON = "DarkSouls1Classes.json";

	private final List<SoulsLikeClass> CLASSES;

	private final List<Stat> STATS;

	public DarkSouls1ClassPickerHelper() throws IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(DARK_SOULS_1_CLASSES_JSON);
		Reader reader = new InputStreamReader(is, "UTF-8");

		Gson gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.create();

		CLASSES = Collections.unmodifiableList(
			gson.fromJson(reader, new TypeToken<ArrayList<DarkSouls1Class>>() {
			}.getType()));

		STATS = List.of(DarkSouls1Stat.values());
	}

	@Override
	public List<Stat> getInternalStatsList() {
		return List.of(DarkSouls1Stat.values());
	}

	@Override
	protected List<SoulsLikeClass> getInternalClassList() {
		return this.CLASSES;
	}

	@Override
	public SoulsLikeClassBuilder getNewBuilder() {
		return new DarkSouls1ClassBuilder();
	}

	@Override
	public SoulsLikeClassChoice getNewClassChoice(String className, int level,
		SoulsLikeClass wastedStatsBreakdown, int wastedStats) {
		return new DarkSouls1ClassChoice(className, level, wastedStatsBreakdown, wastedStats);
	}
}
