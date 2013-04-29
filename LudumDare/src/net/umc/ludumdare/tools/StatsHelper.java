package net.umc.ludumdare.tools;

import net.umc.ludumdare.common.Constants;

public class StatsHelper {

	public static int getTriesByLevelNumber(int world) {
		return ResourceManager.getGlobalInt(String.format("Level%sTries", world));
	}
	
	public static void addTryToWorld(int world) {
		ResourceManager.updateGlobal("Level"+world+"Tries", Integer.toString(getTriesByLevelNumber(world) + 1));
	}
	
	public static void clearStats(){
		ResourceManager.updateGlobal("win", "false");
		for(int world=0; world<=7; world++){
			ResourceManager.updateGlobal("Level"+world+"Tries", "0");
			ResourceManager.updateGlobal("level"+world+"coin", "false");
		}
	}
	
}
