package us.jbury.EldenRingClassPicker;

import com.google.gson.annotations.SerializedName;

public class EldenRingClass {
	public static enum Stat{
		vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane;
	}

	@SerializedName("Class")
	public final String className;
	public final int vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane;

	public EldenRingClass(String className, int vigor, int mind, int endurance, int strength, int dexterity, int intelligence, int faith, int arcane){
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
}
