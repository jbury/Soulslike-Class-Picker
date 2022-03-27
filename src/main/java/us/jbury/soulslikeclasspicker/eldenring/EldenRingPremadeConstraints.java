package us.jbury.soulslikeclasspicker.eldenring;

import java.util.HashMap;
import java.util.Map;
import us.jbury.soulslikeclasspicker.core.Stat;
import us.jbury.soulslikeclasspicker.eldenring.EldenRingClass.EldenRingStat;

public class EldenRingPremadeConstraints {
	public static Map<Stat, Integer> pureStrengthConstraints(){
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		constraints.put(EldenRingStat.intelligence, 0);
		//For a tradeoff of 6 points (by starting samurai) we can wield WAY more possible weapons.
		constraints.put(EldenRingStat.dexterity, 15);
		constraints.put(EldenRingStat.faith, 0);
		constraints.put(EldenRingStat.arcane, 0);

		return constraints;
	}

	public static Map<Stat, Integer> pureIntConstraints(){
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		constraints.put(EldenRingStat.strength, 0);
		constraints.put(EldenRingStat.dexterity, 0);
		constraints.put(EldenRingStat.faith, 0);
		constraints.put(EldenRingStat.arcane, 0);

		return constraints;
	}

	public static Map<Stat, Integer> pureFaithConstraints(){
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		constraints.put(EldenRingStat.strength, 0);
		constraints.put(EldenRingStat.dexterity, 0);
		constraints.put(EldenRingStat.intelligence, 0);
		constraints.put(EldenRingStat.arcane, 0);

		return constraints;
	}

	public static Map<Stat, Integer> arcaneDragonConstraints(){
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		constraints.put(EldenRingStat.strength, 11);


		constraints.put(EldenRingStat.dexterity,11);

		constraints.put(EldenRingStat.intelligence, 0);
		constraints.put(EldenRingStat.faith, 24);

		return constraints;
	}

	public static Map<Stat, Integer> arcaneBloodConstraints(){
		Map<Stat, Integer> constraints = new HashMap<Stat, Integer>();

		//24(16 2h) sacred spear, Marais Executioner's Sword
		constraints.put(EldenRingStat.strength, 12); //18 2h

		//14 Mohgwyn's Sacred Spear, Marais Executioner's Sword
		//18 Regalia of Echoaid, Rivers of Blood
		//21 Eleonora's Poleblade (twinblade)
		//22 clinging bone
		//35 Margott's cursed sword
		constraints.put(EldenRingStat.dexterity,35);

		constraints.put(EldenRingStat.intelligence, 0);

		//this is somehow the best edgecase possible - optimal build here is to see that faith is a
		//stat that we want _some_ points in, and compare to all the other least-wasted-point options
		//(wasting 9 points in this case) with no arcane or strength differences...But it has a
		//correct answer!  Prophet, because while we don't _want_ more than 14 points of faith,
		//it is better to have two wasted points allocated to faith (which we use a _little_) instead
		//of to int (which we don't use at all).
		constraints.put(EldenRingStat.faith, 14);

		return constraints;
	}
}
