package us.jbury.soulslikeclasspicker.premades;

import java.util.HashMap;
import java.util.Map;
import us.jbury.soulslikeclasspicker.core.Stat;
import us.jbury.soulslikeclasspicker.games.darksouls1.DarkSouls1Stat;

public class DarkSouls1PremadeConstraints {

	private static Map<Stat, Integer> baseMap() {
		Map<Stat, Integer> constraints = new HashMap<>();
		constraints.put(DarkSouls1Stat.resistance, 0);
		return constraints;
	}

	public static Map<Stat, Integer> pureStrengthConstraints() {
		Map<Stat, Integer> constraints = baseMap();

		constraints.put(DarkSouls1Stat.dexterity, 0);
		constraints.put(DarkSouls1Stat.faith, 0);
		constraints.put(DarkSouls1Stat.intelligence, 0);

		return constraints;
	}

	public static Map<Stat, Integer> pureDexterityConstraints() {
		Map<Stat, Integer> constraints = baseMap();

		constraints.put(DarkSouls1Stat.strength, 0);
		constraints.put(DarkSouls1Stat.faith, 0);
		constraints.put(DarkSouls1Stat.intelligence, 0);

		return constraints;
	}

	public static Map<Stat, Integer> pureIntelligenceConstraints() {
		Map<Stat, Integer> constraints = baseMap();

		constraints.put(DarkSouls1Stat.strength, 0);
		constraints.put(DarkSouls1Stat.dexterity, 0);
		constraints.put(DarkSouls1Stat.faith, 0);

		return constraints;
	}

	public static Map<Stat, Integer> pureFaithConstraints() {
		Map<Stat, Integer> constraints = baseMap();

		constraints.put(DarkSouls1Stat.strength, 0);
		constraints.put(DarkSouls1Stat.dexterity, 0);
		constraints.put(DarkSouls1Stat.intelligence, 0);

		return constraints;
	}
}
