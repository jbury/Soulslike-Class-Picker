package us.jbury.soulslikeclasspicker.core;

import java.util.Collections;
import java.util.List;

public abstract class SoulslikeClassPickerHelper {

	public List<SoulslikeClass> getClassList() {
		return Collections.unmodifiableList(getInternalClassList());
	}

	public abstract List<Stat> getStatsList();

	protected abstract List<SoulslikeClass> getInternalClassList();

	public abstract SoulslikeClassBuilder getNewBuilder();

	public abstract SoulslikeClassChoice getNewClassChoice(String className, int level,
		SoulslikeClass wastedStatsBreakdown, int wastedStats);
}
