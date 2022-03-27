package us.jbury.soulslikeclasspicker.games.darksouls1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import us.jbury.soulslikeclasspicker.core.SoulslikeClass;
import us.jbury.soulslikeclasspicker.core.Stat;

public class DarkSouls1Class implements SoulslikeClass {

	public final static int MAX_NAME_LENGTH = 10;
	@SerializedName("Class")
	@Expose(serialize = false)
	public final String className;
	@Expose(serialize = true)
	public final int level, vitality, attunement, endurance, strength, dexterity, resistance, intelligence, faith;

	DarkSouls1Class(String className, int level, int vitality, int attunement, int endurance,
		int strength, int dexterity, int resistance, int intelligence, int faith) {
		this.className = className;
		this.level = level;
		this.vitality = vitality;
		this.attunement = attunement;
		this.endurance = endurance;
		this.strength = strength;
		this.dexterity = dexterity;
		this.resistance = resistance;
		this.intelligence = intelligence;
		this.faith = faith;
	}

	@Override
	public int getStat(Stat s) {
		switch ((DarkSouls1Stat) s) {
			case vitality:
				return this.vitality;
			case attunement:
				return this.attunement;
			case endurance:
				return this.endurance;
			case strength:
				return this.strength;
			case dexterity:
				return this.dexterity;
			case resistance:
				return this.resistance;
			case intelligence:
				return this.intelligence;
			case faith:
				return this.faith;
		}
		throw new RuntimeException("Where did you even get that stat: " + s.name());
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public String getName() {
		return this.className;
	}

	@Override
	public String getNameWithBuffer() {
		int bufferLength = MAX_NAME_LENGTH - this.className.length();
		return this.className + " ".repeat(bufferLength);
	}
}
