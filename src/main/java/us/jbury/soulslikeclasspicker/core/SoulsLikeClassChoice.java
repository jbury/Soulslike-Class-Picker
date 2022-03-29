package us.jbury.soulslikeclasspicker.core;

public interface SoulsLikeClassChoice extends Comparable<SoulsLikeClassChoice> {

	public String getNameWithBuffer();

	public String getClassName();

	public int getLevel();

	public int getTotalWastedStats();

	public SoulsLikeClass getWastedStatsBreakdown();
}
