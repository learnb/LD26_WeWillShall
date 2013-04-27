package net.umc.ludumdare.states;

import net.umc.ludumdare.common.Level;
import net.umc.ludumdare.common.Sprite;
import net.umc.ludumdare.tools.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState{

	//game variables
    int stateID = 1;
    private int screenWidth, screenHeight;
    private Level level;
    private boolean pause;
    private Input input;
    
    //main character variables
    private Sprite mainChar;
    private boolean jumping = false;	//ascending
    private boolean falling = false;	//descending
    private int jumpTimerMax = 400;
    private int fallTimerMax = 400;
    private int jDelayTimerMax = 500;
    private int jumpTimer = 0;
    private int fallTimer = 0;
    private int jDelayTimer = jDelayTimerMax;

    
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
    	pause = false;
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.translate((screenWidth - 448) / 2, screenHeight - level.getMainChar().getY() - 128);
    	level.render(gc, g);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	if (pause) return;
    	
    	level.update(gc, sbg, delta);
    	input = gc.getInput();
    	mainChar = level.getMainChar();

    	
    	System.out.println("\n\njump: "+jumping+"\nfall:"+falling);
    	if(!jumping && !falling){
    		jDelayTimer+=delta;
    	}
    	if(jumping){	//increment jumpTimer
    		jumpTimer+=delta;
    	}
    	if(falling){	//increment fallTimer
    		fallTimer+=delta;
    	}
    	if(fallTimer>=fallTimerMax){
    		//stop falling
    		falling = false;
    		fallTimer=0;
    		jDelayTimer = 0;
    	}
    	
    	//main character controls
    	if (input.isKeyDown(Input.KEY_LEFT)) {
    		if (mainChar.getX() - 4 >= 0) {
    			mainChar.setX(mainChar.getX() - 4);
    		}
    	}
    	if (input.isKeyDown(Input.KEY_RIGHT)) {
    		if (mainChar.getX() + 64 + 4 <= 448) {
    			mainChar.setX(mainChar.getX() + 4);
    		}
    	}
    	if ((!input.isKeyDown(Input.KEY_SPACE) && jumping) || jumpTimer>=jumpTimerMax) {
    		//start falling
    		System.out.println("FALLING!");
    		falling = true;
    		jumpTimer = 0;
    		fallTimer = 0;
    		jumping = false;
    	}
    	if (input.isKeyDown(Input.KEY_SPACE) && !falling && (jDelayTimer>=jDelayTimerMax) && !(jumpTimer>=jumpTimerMax)) {
    		//start jumping
    		System.out.println("JUMPING!");
    		if(!jumping){
    			jumpTimer = 0;
    		}
    		jumping = true;
    		
    	}
    	
    	
    	if (mainChar.getY() <= 0) {
    		return;
    	}
    	
    	int panelX = (int)(mainChar.getX() + 32) / 64;
    	int panelY = (int)(mainChar.getY() + 48) / 64;
    	
    	if ((!jumping && !falling) && !level.getPlatform()[panelX][panelY]) {
    		//pause = true;
    		level.init();
    	}
    }
}
