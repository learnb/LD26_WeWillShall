package net.umc.ludumdare.common;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import net.umc.ludumdare.tools.ResourceManager;

public class ElectricBall extends Sprite{
	
	private int flipTimer, flipTimerMax;
	private boolean flipped;
	public ElectricBall(float x, float y) {
		super(new Image[] { ResourceManager.getImage("electricball") }, x, y, 0, 0);
		flipTimer = 0;
		flipTimerMax = 200;
		flipped = false;
	}
	
	public ElectricBall(float x, float y, int left, int right) {
		super(new Image[] { ResourceManager.getImage("electricball") }, x, y, 0, 0);
		flipTimer = 0;
		flipTimerMax = 200;
		flipped = false;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		flipTimer+=delta;
	}
	
	public void render(GameContainer gc, Graphics g){
		if (flipTimer>=flipTimerMax) {
			flipTimer = 0;
			if(flipped)
				renderFlipped(gc, g, 1, false, false);
			else
				renderFlipped(gc, g, 1, true, false);
		}
	}
	
}
