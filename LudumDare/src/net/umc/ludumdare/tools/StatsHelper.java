package net.umc.ludumdare.tools;

import net.umc.ludumdare.common.Constants;

public class StatsHelper {

	public static int getTriesByLevelNumber(int world) {
		return ResourceManager.getGlobalInt(String.format("Level%sTries", world));
	}
	
	public static void addTryToWorld(int world) {
		ResourceManager.updateGlobal("Level%sTries", (getTriesByLevelNumber(world) + 1) + Constants.EMPTY);
	}
	
}
