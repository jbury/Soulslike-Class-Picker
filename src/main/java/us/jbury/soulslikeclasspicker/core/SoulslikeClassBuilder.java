package us.jbury.soulslikeclasspicker.core;

public interface SoulslikeClassBuilder {
	SoulslikeClassBuilder withClassName(String className);
	SoulslikeClassBuilder withLevel(int level);
	SoulslikeClassBuilder withStat(Stat s, int statValue);

	SoulslikeClass build();
}
