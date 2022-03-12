package us.jbury.EldenRingClassPicker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EldenRingClass {
	public final static int MAX_NAME_LENGTH = 10;

	public static enum Stat{
		vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane;
	}

	@SerializedName("Class")
	@Expose(serialize = false)
	public final String className;
	@Expose(serialize = true)
	public final int vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane;

	private EldenRingClass(String className, int vigor, int mind, int endurance, int strength, int dexterity, int intelligence, int faith, int arcane){
		this.className = className;
		this.vigor = vigor;
		this.mind = mind;
		this.endurance = endurance;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.faith = faith;
		this.arcane = arcane;
	}

	public int getStat(Stat s){
		switch(s){
			case vigor: return this.vigor;
			case mind: return this.mind;
			case endurance: return this.endurance;
			case strength: return this.strength;
			case dexterity: return this.dexterity;
			case intelligence: return this.intelligence;
			case faith: return this.faith;
			case arcane: return this.arcane;
		}
		throw new RuntimeException("Where did you even get that stat: " + s.name());
	}

	public String getNameWithBuffer(){
		int bufferLength = MAX_NAME_LENGTH - this.className.length();
		return this.className + " ".repeat(bufferLength);
	}

	public static class EldenRingClassBuilder {

		public String className;
		public int vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane;

		public EldenRingClassBuilder withClassName(String className) {
			this.className = className;
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

		public EldenRingClassBuilder withStat(Stat stat, int value) {
			switch (stat) {
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

		public EldenRingClass build(){
			return new EldenRingClass(this.className, this.vigor, this.mind, this.endurance,
				this.strength, this.dexterity, this.intelligence, this.faith, this.arcane);
		}
	}
}
