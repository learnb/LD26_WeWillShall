package net.umc.ludumdare.states;

import net.umc.ludumdare.MainGame;
import net.umc.ludumdare.tools.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState{

    int stateID = 1;
    private int screenWidth, screenHeight, menuSelector;
    
    public MainMenuState( int stateID ) 
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
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.drawString("Play Game", (screenWidth / 2) - 40, (screenHeight / 2) - 30);
    	g.drawString("Options", (screenWidth / 2) - 32, (screenHeight / 2));
    	g.drawString("Exit", (screenWidth / 2) - 20, (screenHeight / 2) + 30);
    	g.drawRect((screenWidth / 2) - 50, (screenHeight / 2) - 30 + (menuSelector * 30), 104, 20);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	if (input.isKeyPressed(Input.KEY_DOWN)) {
    		menuSelector = menuSelector == 2 ? 0 : menuSelector + 1;
    	}
    	else if (input.isKeyPressed(Input.KEY_UP)) {
    		menuSelector = menuSelector == 0 ? 2 : menuSelector - 1;
    	}
    	else if (input.isKeyPressed(Input.KEY_ENTER)) {
    		switch (menuSelector) {
    			case 0:
    				sbg.enterState(MainGame.GAMEPLAYSTATE);
    				break;
	    		case 1:
	    			sbg.enterState(MainGame.OPTIONS);
	    			break;
	    		case 2:
	    			gc.exit();
    		}
    	}
    }
	
}
