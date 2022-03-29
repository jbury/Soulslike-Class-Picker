package us.jbury.soulslikeclasspicker.games.eldenring;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import us.jbury.soulslikeclasspicker.core.SoulsLikeClass;
import us.jbury.soulslikeclasspicker.core.Stat;

public class EldenRingClass implements SoulsLikeClass {

	public final static int MAX_NAME_LENGTH = 10;
	@SerializedName("Class")
	@Expose(serialize = false)
	public final String className;
	@Expose(serialize = true)
	public final int level, vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane;

	EldenRingClass(String className, int level, int vigor, int mind, int endurance,
		int strength, int dexterity, int intelligence, int faith, int arcane) {
		this.className = className;
		this.level = level;
		this.vigor = vigor;
		this.mind = mind;
		this.endurance = endurance;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.faith = faith;
		this.arcane = arcane;
	}

	public int getStat(Stat s) {
		switch ((EldenRingStat) s) {
			case vigor:
				return this.vigor;
			case mind:
				return this.mind;
			case endurance:
				return this.endurance;
			case strength:
				return this.strength;
			case dexterity:
				return this.dexterity;
			case intelligence:
				return this.intelligence;
			case faith:
				return this.faith;
			case arcane:
				return this.arcane;
		}
		throw new RuntimeException("Where did you even get that stat: " + s.name());
	}

	public String getNameWithBuffer() {
		int bufferLength = MAX_NAME_LENGTH - this.className.length();
		return this.className + " ".repeat(bufferLength);
	}

	public int getLevel() {
		return this.level;
	}

	@Override
	public String getName() {
		return this.className;
	}
}
