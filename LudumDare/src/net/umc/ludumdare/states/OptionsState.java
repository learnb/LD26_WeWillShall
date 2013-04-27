package net.umc.ludumdare.states;

import net.umc.ludumdare.tools.ResourceManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionsState extends BasicGameState{

    int stateID = 1;
    private int screenWidth, screenHeight, menuSelector, resSelector, fullscreenSelector;
    private boolean fullscreen;
    
    public OptionsState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    public int getID() {
        return stateID;
    }
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	screenWidth = ResourceManager.getGlobalInt("SCREEN_WIDTH");
    	screenHeight = ResourceManager.getGlobalInt("SCREEN_HEIGHT");
    	menuSelector = 0;
    	if (screenWidth == 800 && screenHeight == 600) {
    		resSelector = 0;
    	}
    	else if (screenWidth == 1024 && screenHeight == 900) {
    		resSelector = 1;
    	}
    	else if (screenWidth == 1440 && screenHeight == 900) {
    		resSelector = 1;
    	}
    	fullscreen = ResourceManager.getGlobalBoolean("FULLSCREEN");
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.setColor(Color.white);
    	g.drawString("Resolution", 20, 20);
    		g.setColor(Color.yellow);
    	//g.drawString()
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	if (input.isKeyPressed(Input.KEY_DOWN)) {
    		menuSelector = menuSelector == 0 ? 1 : 0;
    	}
    	else if (input.isKeyPressed(Input.KEY_UP)) {
    		menuSelector = menuSelector == 0 ? 1 : 0;
    	}
    	
    }
	
}
