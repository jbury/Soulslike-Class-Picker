package us.jbury.soulslikeclasspicker.core;

public interface SoulsLikeClassBuilder {

	SoulsLikeClassBuilder withClassName(String className);

	SoulsLikeClassBuilder withLevel(int level);

	SoulsLikeClassBuilder withStat(Stat s, int statValue);

	SoulsLikeClass build();
}
