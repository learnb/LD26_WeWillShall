package net.umc.ludumdare.states;

import net.umc.ludumdare.MainGame;
import net.umc.ludumdare.tools.ResourceManager;
import net.umc.ludumdare.tools.StatsHelper;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StatsState extends BasicGameState{

    int stateID = 4;
    private int screenWidth, screenHeight, cloudY, midX;
    private Font titleFont, headerFont, statFont, messageFont;
    private final String KEEP_TRY = "Keep Try", LEVEL = "Level", NUMTRIES = "# of Tries", NUMCOINS = "Coins";
    private final String RED = "1 - Red", ORANGE = "2 - Orange", YELLOW = "3 - Yellow", GREEN = "4 - Green";
    private final String BLUE = "5 - Blue", INDIGO = "6 - Indigo", VIOLET = "7 - Violet", RAINBOW = "8 - Bonus";
    private final String COINFORMAT = "%s/1", LEVELSELECT = "Press the number key corresponding to the level you wish to enter.";
    public StatsState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    public int getID() {
        return stateID;
    }
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	screenWidth = ResourceManager.getGlobalInt("SCREEN_WIDTH");
    	midX = screenWidth / 2;
    	screenHeight = ResourceManager.getGlobalInt("SCREEN_HEIGHT");

    	cloudY = -2048; 
    	
    	titleFont = ResourceManager.generateNewFont("statTitle", 64, Color.black);
    	headerFont = ResourceManager.generateNewFont("statTitle", 48, Color.black);
    	statFont = ResourceManager.generateNewFont("statTitle", 36, Color.black);
    	messageFont = ResourceManager.generateNewFont("statTitle", 16, Color.black);
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.setBackground(Color.black);
    	g.setColor(Color.white);
    	
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY - 2048);
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY);
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY + 2048);
    	
    	titleFont.drawString(midX - (titleFont.getWidth("Keep Try!") / 2), 0, KEEP_TRY);
    	headerFont.drawString(midX - 200, 150, LEVEL);
    	headerFont.drawString(midX, 150, NUMTRIES);
    	headerFont.drawString(midX + 200, 150, NUMCOINS);
    	
    	statFont.drawString(midX - 150, 220, RED);
    	statFont.drawString(midX, 220, StatsHelper.getTriesByLevelNumber(0) + "");
    	statFont.drawString(midX + 150, 220, String.format(COINFORMAT, 0));
    	
    	statFont.drawString(midX - 150, 260, ORANGE);
    	statFont.drawString(midX, 260, StatsHelper.getTriesByLevelNumber(0) + "");
    	statFont.drawString(midX + 150, 260, String.format(COINFORMAT, 0));
    	
    	statFont.drawString(midX - 150, 300, YELLOW);
    	statFont.drawString(midX, 300, StatsHelper.getTriesByLevelNumber(0) + "");
    	statFont.drawString(midX + 150, 300, String.format(COINFORMAT, 0));
    	
    	statFont.drawString(midX - 150, 340, GREEN);
    	statFont.drawString(midX, 340, StatsHelper.getTriesByLevelNumber(0) + "");
    	statFont.drawString(midX + 340, 220, String.format(COINFORMAT, 0));
    	
    	statFont.drawString(midX - 150, 380, BLUE);
    	statFont.drawString(midX, 380, StatsHelper.getTriesByLevelNumber(0) + "");
    	statFont.drawString(midX + 150, 380, String.format(COINFORMAT, 0));
    	
    	statFont.drawString(midX - 150, 420, INDIGO);
    	statFont.drawString(midX, 420, StatsHelper.getTriesByLevelNumber(0) + "");
    	statFont.drawString(midX + 150, 420, String.format(COINFORMAT, 0));
    	
    	statFont.drawString(midX - 150, 460, VIOLET);
    	statFont.drawString(midX, 460, StatsHelper.getTriesByLevelNumber(0) + "");
    	statFont.drawString(midX + 150, 460, String.format(COINFORMAT, 0));
    	
    	statFont.drawString(midX - 150, 500, RAINBOW);
    	statFont.drawString(midX, 500, StatsHelper.getTriesByLevelNumber(0) + "");
    	statFont.drawString(midX + 500, 220, String.format(COINFORMAT, 0));
    	
    	messageFont.drawString(midX - messageFont.getWidth(LEVELSELECT),  screenHeight - 20, LEVELSELECT);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	if (input.isKeyPressed(Input.KEY_1) || input.isKeyPressed(Input.KEY_NUMPAD1)) {
    		// Enter level
    	}
    	else if (input.isKeyPressed(Input.KEY_2) || input.isKeyPressed(Input.KEY_NUMPAD2)) {
    		// Enter level
    	}
    	else if (input.isKeyPressed(Input.KEY_3) || input.isKeyPressed(Input.KEY_NUMPAD3)) {
    		// Enter level
    	}
    	else if (input.isKeyPressed(Input.KEY_4) || input.isKeyPressed(Input.KEY_NUMPAD4)) {
    		// Enter level
    	}
    	else if (input.isKeyPressed(Input.KEY_5) || input.isKeyPressed(Input.KEY_NUMPAD5)) {
    		// Enter level
    	}
    	else if (input.isKeyPressed(Input.KEY_6) || input.isKeyPressed(Input.KEY_NUMPAD6)) {
    		// Enter level
    	}
    	else if (input.isKeyPressed(Input.KEY_7) || input.isKeyPressed(Input.KEY_NUMPAD7)) {
    		// Enter level
    	}
    	else if (input.isKeyPressed(Input.KEY_8) || input.isKeyPressed(Input.KEY_NUMPAD8)) {
    		// Enter level
    	}
    	else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
    		sbg.enterState(MainGame.MAINMENUSTATE);
    	}
    	
    	cloudY = cloudY >= 2048 ? -2048 : cloudY + 2;
    }
	
}
