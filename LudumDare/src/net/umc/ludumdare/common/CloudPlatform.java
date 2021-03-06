package net.umc.ludumdare.common;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import net.umc.ludumdare.tools.ResourceManager;

public class CloudPlatform extends Sprite{
	
	private int leftConstraint, rightConstraint, topConstraint, bottomConstraint;
	public CloudPlatform(float x, float y) {
		super(new Image[] { ResourceManager.getImage("cloud_platform") }, x, y, 0, 0);
		setVelX(-2);
		leftConstraint = 0;
		rightConstraint = 448;
		topConstraint = 0;
		bottomConstraint = 100;
	}
	
	public CloudPlatform(float x, float y, float velX, float velY, int left, int right, int top, int bottom) {
		super(new Image[] { ResourceManager.getImage("cloud_platform") }, x, y, 0, 0);
		setVelX(velX);
		setVelY(velY);
		leftConstraint = left;
		rightConstraint = right;
		topConstraint = top;
		bottomConstraint = bottom;
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
		
		if (getVelY() < 0) {
			if (getVelY() + getY() <= topConstraint) {
				setVelY(getVelY() * -1);
			}
		} else {
			if (getVelY() + getY() >= bottomConstraint) {
				setVelY(getVelY() * -1);
			}
		}
		setX(getX() + getVelX());
		setY(getY() + getVelY());
	}
	
	public void render(GameContainer gc, Graphics g){
		if (getVelX() <= 0) {
			renderFlipped(gc, g, 1, false, false);
		} else{
			renderFlipped(gc, g, 1, true, false);
		}
	}
	
}
