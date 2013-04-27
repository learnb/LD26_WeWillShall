package net.umc.ludumdare;

import net.umc.ludumdare.states.GamePlayState;
import net.umc.ludumdare.states.MainMenuState;
import net.umc.ludumdare.states.OptionsState;
import net.umc.ludumdare.states.SplashScreenState;
import net.umc.ludumdare.tools.ResourceManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
 
public class MainGame extends StateBasedGame {
 
	public static final int SPLASHSCREENSTATE = 0;
    public static final int MAINMENUSTATE = 1;
    public static final int OPTIONS = 2;
    public static final int GAMEPLAYSTATE = 3;
    
    
    public MainGame()
    {
        super("Ludum Dare");
    }
 
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new MainGame());
 
         app.setDisplayMode(ResourceManager.getGlobalInt("SCREEN_WIDTH"),
        		 ResourceManager.getGlobalInt("SCREEN_HEIGHT"),
        		 ResourceManager.getGlobalBoolean("FULLSCREEN"));
         app.setTargetFrameRate(60);
         app.start();
    }
 
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
    	this.addState(new SplashScreenState(SPLASHSCREENSTATE));
    	this.addState(new MainMenuState(MAINMENUSTATE));
    	this.addState(new OptionsState(OPTIONS));
    	this.addState(new GamePlayState(GAMEPLAYSTATE));
    }
}