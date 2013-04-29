package net.umc.ludumdare.states;

import java.util.ArrayList;

import net.umc.ludumdare.MainGame;
import net.umc.ludumdare.common.CloudPlatform;
import net.umc.ludumdare.common.Level;
import net.umc.ludumdare.common.Sprite;
import net.umc.ludumdare.tools.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState{

	//game variables
    int stateID = 1;
    private int screenWidth, screenHeight;
    private static ArrayList<Level> levels;
    private static Level level;
    private boolean pause;
    private Input input;
    
    //main character variables
    private static Sprite mainChar;
    private static boolean jumping = false;	//ascending
    private static boolean falling = false;	//descending
    private int jumpTimerMax = 400;
    private int fallTimerMax = 400;
    private int jDelayTimerMax = 25;
    private int jumpTimer = 0;
    private int fallTimer = 0;
    private int jDelayTimer = jDelayTimerMax;
    private float jScale;
    private int cloudY;

    
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
    	Music musicPlayer = ResourceManager.getMusic("themeMusic");
    	musicPlayer.loop();
    	musicPlayer.setVolume(.25f);
    	
    	//create all levels
    	levels = new ArrayList<Level>();
    	levels.add(new Level("levelone", 0));
    	levels.add(new Level("leveltwo", 1));
    	levels.add(new Level("yellowlevel", 2));
    	levels.add(new Level("greenlevel", 3));
    	levels.add(new Level("bluelevel", 4));
    	levels.add(new Level("indigolevel", 5));
    	levels.add(new Level("violetlevel", 6));
    	levels.add(new Level("bonuslevel", 7));
    	level = levels.get(0);
    	
    	//init first level
    	level.init();
    	
    	cloudY = -2048;
    	pause = false;
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	//draw some background
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY - 2048);
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY);
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY + 2048);
    	g.translate((screenWidth - 448) / 2, screenHeight - level.getMainChar().getY() - 128);
    	level.render(gc, g);
    	if (jumping) {
    		jScale = 1 + ((float)jumpTimer/jumpTimerMax);
    		level.getMainChar().render(gc, g, jScale);
    		//System.out.println(jScale);
    	}
    	else if (falling)
    		level.getMainChar().render(gc, g, jScale - ((float)fallTimer/fallTimerMax)*(jScale-1));
    	else
    		level.getMainChar().render(gc, g);
    	g.resetTransform();
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	input = gc.getInput();
    	if(input.isKeyPressed(Input.KEY_ENTER)){
    		pause = !pause;
    	}
    	if (pause) return;
    	
    	cloudY = cloudY >= 2048 ? -2048 : cloudY + 2;
    	
    	mainChar = level.getMainChar();
    	level.update(gc, sbg, delta);
    	

    	//goal reached
    	if((mainChar.getY()+64 < 128) || (input.isKeyPressed(Input.KEY_F))){
    		if(levels.indexOf(level) < 6){
    			levels.get(levels.indexOf(level)+1).init();
    			level = levels.get(levels.indexOf(level)+1);
    		}else{
    			ResourceManager.updateGlobal("win", "true");
    			System.out.println("WIN STATE");
    			sbg.enterState(MainGame.WINSTATE);
    		}
    	}
    	
    	if(input.isKeyPressed(Input.KEY_ESCAPE) && ResourceManager.getGlobalBoolean("win")){
    		sbg.enterState(MainGame.STATSSTATE);
    	}
    	
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
    	if (( (!input.isKeyDown(Input.KEY_SPACE) && !input.isKeyDown(Input.KEY_UP)) && jumping) || jumpTimer>=jumpTimerMax) {
    		//start falling
    		//System.out.println("FALLING!");
    		falling = true;
    		jumpTimer = 0;
    		fallTimer = 0;
    		jumping = false;
    	}
    	if ((input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_UP)) && !falling && (jDelayTimer>=jDelayTimerMax) && !(jumpTimer>=jumpTimerMax)) {
    		//start jumping
    		//System.out.println("JUMPING!");
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
    	
    	if ((!jumping && !falling) && !level.getPlatform()[panelX][panelY] && !onPlatform()) {
    		//pause = true;
    		level.init();
    	}
    }
    
    public static boolean getJumping() { return jumping; }
    public static boolean getFalling() { return falling; }
    
    private boolean onPlatform() {
    	for(CloudPlatform platform: level.getPlatforms()) {
    		if (Level.checkHeroCollision(mainChar, platform)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static void setLevel(int levelCode) {
    	level = levels.get(levelCode);
    	mainChar = level.getMainChar();
    	level.init();
    }
}
