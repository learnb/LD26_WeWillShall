package net.umc.ludumdare.states;

import net.umc.ludumdare.MainGame;
import net.umc.ludumdare.tools.ResourceManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashScreenState extends BasicGameState{

    int stateID = 0;
    int color = 255;
    int colorSpeed = -2;
    
    public SplashScreenState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    public int getID() {
        return stateID;
    }
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.setColor(new Color(255, 255, 255, color));
    	g.drawString("Press 'Enter' to start.", 300, 0);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	color += colorSpeed;
    	colorSpeed = color > 255 ? colorSpeed *= -1 : colorSpeed;
    	colorSpeed = color < 0 ? colorSpeed *= -1 : colorSpeed;
    
    	if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
    		ResourceManager.getSound("blop").play();
    		sbg.enterState(MainGame.MAINMENUSTATE);
    	}
    }
	
}
