package us.jbury.soulslikeclasspicker.core;

import java.util.Collections;
import java.util.List;
import us.jbury.soulslikeclasspicker.SoulslikeClassPicker.ClassPickerHelper;

public abstract class UnmodifiableSoulslikeClassPickerHelper implements ClassPickerHelper {

	public final List<SoulslikeClass> getClassList() {
		return Collections.unmodifiableList(getInternalClassList());
	}

	public final List<Stat> getStatsList() {
		return Collections.unmodifiableList(getInternalStatsList());
	}

	protected abstract List<Stat> getInternalStatsList();

	protected abstract List<SoulslikeClass> getInternalClassList();

	public abstract SoulslikeClassBuilder getNewBuilder();

	public abstract SoulslikeClassChoice getNewClassChoice(String className, int level,
		SoulslikeClass wastedStatsBreakdown, int wastedStats);
}
