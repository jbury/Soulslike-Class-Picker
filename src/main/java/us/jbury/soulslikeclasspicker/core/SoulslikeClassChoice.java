package us.jbury.soulslikeclasspicker.core;

public interface SoulslikeClassChoice extends Comparable<SoulslikeClassChoice>{
	public String getNameWithBuffer();

	public String getClassName();
	public int getLevel();
	public int getTotalWastedStats();
	public SoulslikeClass getWastedStatsBreakdown();
}
