package net.umc.ludumdare.common;

import org.newdawn.slick.Image;

import net.umc.ludumdare.tools.ResourceManager;

public class BirdBall extends Sprite{
	
	public BirdBall(float x, float y) {
		super(new Image[] { ResourceManager.getImage("birdball1"), ResourceManager.getImage("birdball2"), ResourceManager.getImage("birdball3"), ResourceManager.getImage("birdball2") }, x, y, 0, 0);
		setVelY(2);
	}
	
	public BirdBall(float x, float y, int left, int right) {
		super(new Image[] { ResourceManager.getImage("birdball1"), ResourceManager.getImage("birdball2"), ResourceManager.getImage("birdball3"), ResourceManager.getImage("birdball2") }, x, y, 0, 0);
		setVelY(2);
	}
	
}
