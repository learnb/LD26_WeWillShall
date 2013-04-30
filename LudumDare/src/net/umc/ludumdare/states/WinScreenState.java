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

public class WinScreenState extends BasicGameState{

    int stateID = 0;
    int color = 255;
    int colorSpeed = -2;
    private int screenWidth, screenHeight;
    
    public WinScreenState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    public int getID() {
        return stateID;
    }
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	screenWidth = ResourceManager.getGlobalInt("SCREEN_WIDTH");
    	screenHeight = ResourceManager.getGlobalInt("SCREEN_HEIGHT");
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.setColor(Color.white);
    	g.setBackground(Color.white);
    	
    	//g.drawImage(ResourceManager.getImage("winscreen"), (screenWidth/2)-128, (screenHeight/2)-128);
    	g.drawImage(ResourceManager.getImage("winscreen"), (float)0, (float)0, (float)screenWidth, (float)screenHeight, (float)0, (float)0, (float)256, (float)256);
    	g.setColor(new Color(0, 0, 0, color));
    	g.drawString("Press 'Enter' to continue.", (screenWidth/2) - 110, 0);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	color += colorSpeed;
    	colorSpeed = color > 255 ? colorSpeed *= -1 : colorSpeed;
    	colorSpeed = color < 0 ? colorSpeed *= -1 : colorSpeed;
    
    	if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
    		ResourceManager.getSound("blop").play();
    		sbg.enterState(MainGame.STATSSTATE);
    	}
    }
	
}
