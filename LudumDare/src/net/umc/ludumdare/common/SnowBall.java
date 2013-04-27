package net.umc.ludumdare.common;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import net.umc.ludumdare.tools.ResourceManager;

public class SnowBall extends Sprite{
	
	private int leftConstraint, rightConstraint;
	public SnowBall(float x, float y) {
		super(new Image[] { ResourceManager.getImage("snowleft") }, x, y, 0, 0);
		setVelX(-2);
		leftConstraint = 0;
		rightConstraint = 448;
	}
	
	public SnowBall(float x, float y, int left, int right) {
		super(new Image[] { ResourceManager.getImage("snowleft") }, x, y, 0, 0);
		setVelX(-2);
		leftConstraint = left;
		rightConstraint = right;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		if (getVelX() < 0) {
			if (getVelX() + getX() <= leftConstraint) {
				setVelX(getVelX() * -1);
			}
		} else {
			if (getVelX() + getX() >= rightConstraint) {
				setVelX(getVelX() * -1);
			}
		}
		setX(getX() + getVelX());
	}
	
	public void render(GameContainer gc, Graphics g){
		if (getVelX() < 0) {
			renderFlipped(gc, g, 1, false, false);
		} else{
			renderFlipped(gc, g, 1, true, false);
		}
	}
	
}
