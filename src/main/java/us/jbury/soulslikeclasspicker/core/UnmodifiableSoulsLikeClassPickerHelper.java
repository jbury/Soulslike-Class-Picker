package us.jbury.soulslikeclasspicker.core;

import java.util.Collections;
import java.util.List;
import us.jbury.soulslikeclasspicker.SoulsLikeClassPicker.ClassPickerHelper;

public abstract class UnmodifiableSoulsLikeClassPickerHelper implements ClassPickerHelper {

	public final List<SoulsLikeClass> getClassList() {
		return Collections.unmodifiableList(getInternalClassList());
	}

	public final List<Stat> getStatsList() {
		return Collections.unmodifiableList(getInternalStatsList());
	}

	protected abstract List<Stat> getInternalStatsList();

	protected abstract List<SoulsLikeClass> getInternalClassList();

	public abstract SoulsLikeClassBuilder getNewBuilder();

	public abstract SoulsLikeClassChoice getNewClassChoice(String className, int level,
		SoulsLikeClass wastedStatsBreakdown, int wastedStats);
}
