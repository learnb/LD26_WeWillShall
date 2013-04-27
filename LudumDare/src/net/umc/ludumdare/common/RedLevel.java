package net.umc.ludumdare.common;

import java.util.ArrayList;

import net.umc.ludumdare.tools.ResourceManager;

public class RedLevel extends Level {

	public RedLevel(String mapID, String charID) {
		super(mapID, charID);
	}
	
	public void init(){
		//load resources
		super.init();
		Sprite enemy = new Sprite(ResourceManager.getImage("GUY"), 0, 1000, 0, 5);
		ArrayList<Sprite> enemies = new ArrayList<Sprite>();
		enemies.add(enemy);
		setEnemyList(enemies);
		
	}

}
