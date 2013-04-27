package net.umc.ludumdare.states;

import net.umc.ludumdare.common.Level;
import net.umc.ludumdare.tools.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState{

    int stateID = 1;
    private int screenWidth, screenHeight;
    private Level level;
    
    public GamePlayState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    public int getID() {
        return stateID;
    }
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	screenWidth = ResourceManager.getGlobalInt("SCREEN_WIDTH");
    	screenHeight = ResourceManager.getGlobalInt("SCREEN_HEIGHT");
    	level = new Level("redmap", "GUY");
    	level.init();
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.translate((screenWidth - 448) / 2, screenHeight - level.getMainChar().getY() - 128);
    	level.render(gc, g);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	level.update(gc, sbg, delta);
    	Input input = gc.getInput();
    	if (input.isKeyDown(Input.KEY_LEFT)) {
    		if (level.getMainChar().getX() - 4 >= 0) {
    			level.getMainChar().setX(level.getMainChar().getX() - 4);
    		}
    	}
    	else if (input.isKeyDown(Input.KEY_RIGHT)) {
    		if (level.getMainChar().getX() + 64 + 4 <= 448) {
    			level.getMainChar().setX(level.getMainChar().getX() + 4);
    		}
    	}
    }
	
}
