package net.umc.ludumdare.states;

import net.umc.ludumdare.MainGame;
import net.umc.ludumdare.common.Sprite;
import net.umc.ludumdare.tools.ResourceManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState{

    int stateID = 1;
    private int screenWidth, screenHeight, menuSelector, cloudY;
    private Sprite mainChar;
    private SpriteSheet panels;
    
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
    	cloudY = -2048;
    	
		mainChar = new Sprite(new Image[] {ResourceManager.getImage("GUY"), ResourceManager.getImage("GUY2"), ResourceManager.getImage("GUY"), ResourceManager.getImage("GUY3")});
		mainChar.setX((screenWidth / 2)-32);
		mainChar.setY((screenWidth / 2)-32);
		mainChar.setVelY(0f);
		
		panels = new SpriteSheet(ResourceManager.getImage("panelss"), 64, 64);
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY - 2048);
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY);
    	g.drawImage(ResourceManager.getImage("cloudbg"), 0, cloudY + 2048);
		
    	for (int i = 1; i <= screenHeight / 64 + 1; i++) {
    		for (int j = 0; j < 7; j++) {
    			if (i % 3 == 0) {
    				g.drawImage(panels.getSubImage(j, 2), (screenWidth / 2) - 224 + (64 * j), 64 * (i-1));
    			}
    			else if (i % 2 == 0) {
    				g.drawImage(panels.getSubImage(j, 1), (screenWidth / 2) - 224 + (64 * j), 64 * (i-1));
    			}
    			else {
    				g.drawImage(panels.getSubImage(j, 0), (screenWidth / 2) - 224 + (64 * j), 64 * (i-1));
    			}
    		}
    	}
    	
    	g.drawImage(ResourceManager.getImage("title"), (screenWidth / 2) - 400, (screenHeight / 2) - 300);
    	
    	mainChar.render(gc, g);
    	g.setColor(Color.black);
    	ResourceManager.getFont("coolfont").drawString(0, 0, "Title", Color.yellow);
    	g.drawString("Play Game", (screenWidth / 2) - 40, (screenHeight / 2) - 30);
    	g.drawString("Options", (screenWidth / 2) - 32, (screenHeight / 2));
    	g.drawString("Exit", (screenWidth / 2) - 20, (screenHeight / 2) + 30);
    	g.drawRect((screenWidth / 2) - 50, (screenHeight / 2) - 30 + (menuSelector * 30), 104, 20);
    	
    
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	cloudY = cloudY >= 2048 ? -2048 : cloudY + 2;
    	
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
    	
    	mainChar.update(gc, sbg, delta);
    }
	
}
