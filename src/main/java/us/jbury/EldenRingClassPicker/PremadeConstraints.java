package us.jbury.EldenRingClassPicker;

import java.util.HashMap;
import java.util.Map;
import us.jbury.EldenRingClassPicker.EldenRingClass.Stat;

public class PremadeConstraints {
	public static Map<Stat, Integer> dualWieldingDexConstraints(){
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		constraints.put(Stat.strength, 12);
		constraints.put(Stat.intelligence, 0);
		constraints.put(Stat.arcane, 0);
		constraints.put(Stat.faith, 0);

		return constraints;
	}

	public static Map<Stat, Integer> pureFaithConstraints(){
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		constraints.put(Stat.strength, 0);
		constraints.put(Stat.dexterity, 0);
		constraints.put(Stat.intelligence, 0);
		constraints.put(Stat.arcane, 0);

		return constraints;
	}

	public static Map<Stat, Integer> arcaneBloodConstraints(){
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		//24(16 2h) sacred spear, Marais Executioner's Sword
		constraints.put(Stat.strength, 12); //18 2h

		//14 Mohgwyn's Sacred Spear, Marais Executioner's Sword
		//18 Regalia of Echoaid, Rivers of Blood
		//21 Eleonora's Poleblade (twinblade)
		//22 clinging bone
		//35 Margott's cursed sword
		constraints.put(Stat.dexterity, 35);

		constraints.put(Stat.intelligence, 0);
		constraints.put(Stat.faith, 0);

		return constraints;
	}
}
