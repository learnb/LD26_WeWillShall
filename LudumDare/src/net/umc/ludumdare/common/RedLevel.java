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
		ElectricBall sb = new ElectricBall(128, 2000,0,0);
		ArrayList<Sprite> enemies = new ArrayList<Sprite>();
		enemies.add(enemy);
		enemies.add(sb);
		enemies.add(new ElectricBall(0, 3000, 0, 256));
		setEnemyList(enemies);
		
		CloudPlatform platform = new CloudPlatform(200, 1500, -2, 0, 0, 448, 0, 0);
		ArrayList<CloudPlatform> platforms = new ArrayList<CloudPlatform>();
		platforms.add(platform);
		setPlatforms(platforms);
	}

}
