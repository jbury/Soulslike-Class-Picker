package us.jbury.soulslikeclasspicker.core;

import java.util.Collections;
import java.util.List;
import us.jbury.soulslikeclasspicker.eldenring.EldenRingClass;

public abstract class SoulslikeClassPickerHelper {
	public List<SoulslikeClass> getClassList(){
		return Collections.unmodifiableList(getInternalClassList());
	}

	public abstract List<Stat> getStatsList();
	protected abstract String getClassesJsonFilename();
	protected abstract List<SoulslikeClass> getInternalClassList();

	public abstract SoulslikeClassBuilder getNewBuilder();
	public abstract SoulslikeClassChoice getNewClassChoice(String className, int level,
		SoulslikeClass wastedStatsBreakdown, int wastedStats);
}
