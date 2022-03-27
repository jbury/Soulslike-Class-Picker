package us.jbury.soulslikeclasspicker.darksouls1;

import us.jbury.soulslikeclasspicker.core.SoulslikeClassBuilder;
import us.jbury.soulslikeclasspicker.core.Stat;

public class DarkSouls1ClassBuilder implements
	SoulslikeClassBuilder {

	public String className;
	public int level, vitality, attunement, endurance, strength, dexterity, resistance, intelligence, faith;

	public DarkSouls1ClassBuilder withClassName(String className) {
		this.className = className;
		return this;
	}

	public DarkSouls1ClassBuilder withLevel(int level) {
		this.level = level;
		return this;
	}

	public DarkSouls1ClassBuilder withVitality(int vitality) {
		this.vitality = vitality;
		return this;
	}

	public DarkSouls1ClassBuilder withAttunement(int attunement) {
		this.attunement = attunement;
		return this;
	}

	public DarkSouls1ClassBuilder withEndurance(int endurance) {
		this.endurance = endurance;
		return this;
	}

	public DarkSouls1ClassBuilder withStrength(int strength) {
		this.strength = strength;
		return this;
	}

	public DarkSouls1ClassBuilder withDexterity(int dexterity) {
		this.dexterity = dexterity;
		return this;
	}

	public DarkSouls1ClassBuilder withResistance(int resistance) {
		this.resistance = resistance;
		return this;
	}

	public DarkSouls1ClassBuilder withIntelligence(int intelligence) {
		this.intelligence = intelligence;
		return this;
	}

	public DarkSouls1ClassBuilder withFaith(int faith) {
		this.faith = faith;
		return this;
	}

	public SoulslikeClassBuilder withStat(Stat stat, int value) {
		switch ((DarkSouls1Stat) stat) {
			case vitality:
				return this.withVitality(value);
			case attunement:
				return this.withAttunement(value);
			case endurance:
				return this.withEndurance(value);
			case strength:
				return this.withStrength(value);
			case dexterity:
				return this.withDexterity(value);
			case resistance:
				return this.withResistance(value);
			case intelligence:
				return this.withIntelligence(value);
			case faith:
				return this.withFaith(value);
		}
		throw new RuntimeException("Where did you even get that stat: " + stat.name());
	}

	public DarkSouls1Class build() {
		return new DarkSouls1Class(this.className, this.level, this.vitality, this.attunement,
			this.endurance, this.strength, this.dexterity, this.resistance, this.intelligence,
			this.faith);
	}
}
