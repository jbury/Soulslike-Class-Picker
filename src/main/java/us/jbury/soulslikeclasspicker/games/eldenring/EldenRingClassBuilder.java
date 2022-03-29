package us.jbury.soulslikeclasspicker.games.eldenring;

import us.jbury.soulslikeclasspicker.core.SoulsLikeClassBuilder;
import us.jbury.soulslikeclasspicker.core.Stat;

public class EldenRingClassBuilder implements
	SoulsLikeClassBuilder {

	public String className;
	public int level, vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane;

	public EldenRingClassBuilder withClassName(String className) {
		this.className = className;
		return this;
	}

	public EldenRingClassBuilder withLevel(int level) {
		this.level = level;
		return this;
	}

	public EldenRingClassBuilder withVigor(int vigor) {
		this.vigor = vigor;
		return this;
	}

	public EldenRingClassBuilder withMind(int mind) {
		this.mind = mind;
		return this;
	}

	public EldenRingClassBuilder withEndurance(int endurance) {
		this.endurance = endurance;
		return this;
	}

	public EldenRingClassBuilder withStrength(int strength) {
		this.strength = strength;
		return this;
	}

	public EldenRingClassBuilder withDexterity(int dexterity) {
		this.dexterity = dexterity;
		return this;
	}

	public EldenRingClassBuilder withIntelligence(int intelligence) {
		this.intelligence = intelligence;
		return this;
	}

	public EldenRingClassBuilder withFaith(int faith) {
		this.faith = faith;
		return this;
	}

	public EldenRingClassBuilder withArcane(int arcane) {
		this.arcane = arcane;
		return this;
	}

	public SoulsLikeClassBuilder withStat(Stat stat, int value) {
		switch ((EldenRingStat) stat) {
			case vigor:
				return this.withVigor(value);
			case mind:
				return this.withMind(value);
			case endurance:
				return this.withEndurance(value);
			case strength:
				return this.withStrength(value);
			case dexterity:
				return this.withDexterity(value);
			case intelligence:
				return this.withIntelligence(value);
			case faith:
				return this.withFaith(value);
			case arcane:
				return this.withArcane(value);
		}
		throw new RuntimeException("Where did you even get that stat: " + stat.name());
	}

	public EldenRingClass build() {
		return new EldenRingClass(this.className, this.level, this.vigor, this.mind,
			this.endurance,
			this.strength, this.dexterity, this.intelligence, this.faith, this.arcane);
	}
}
