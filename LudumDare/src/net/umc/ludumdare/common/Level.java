package net.umc.ludumdare.common;

import java.util.ArrayList;

import net.umc.ludumdare.states.GamePlayState;
import net.umc.ludumdare.tools.ResourceManager;
import net.umc.ludumdare.tools.StatsHelper;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Level {
	
	//private members
	private TiledMap levelMap;
	private boolean[][] platform;
	private float x, y;
	private Sprite mainChar;
	private ArrayList<Sprite> enemyList;
	private ArrayList<CloudPlatform> platforms;
	private String mapID;
	private ArrayList<String> enemyIDs;
	private int levelId;
	private Sprite coin;
	
	//constructors
	public Level(String mapID, ArrayList<String> enemyIDs){
		this.mapID = mapID;
		this.enemyIDs = enemyIDs;
		levelId = 0;
		setPlatforms(new ArrayList<CloudPlatform>());
	}
	
	public Level(String mapID, int levelId){
		this.mapID = mapID;
		this.levelId = levelId;
		setPlatforms(new ArrayList<CloudPlatform>());
	}
	
	public void init(){
		//load resources
		levelMap = ResourceManager.getMap(mapID);
		mainChar = new Sprite(new Image[] {ResourceManager.getImage("GUY"), ResourceManager.getImage("GUY2"), ResourceManager.getImage("GUY"), ResourceManager.getImage("GUY3")});
		mainChar.setX(192);
		mainChar.setY(levelMap.getHeight() * levelMap.getTileHeight() - 64);
		mainChar.setVelY(-5f);
		enemyList = new ArrayList<Sprite>();
		platforms = new ArrayList<CloudPlatform>();
		
		StatsHelper.addTryToWorld(levelId);
		
		if (enemyIDs != null) {
			for(String id : enemyIDs){
				enemyList.add(new Sprite(ResourceManager.getImage(id)));
			}
		}
		//scan map properties
		setPlatform(new boolean[levelMap.getWidth()][levelMap.getHeight()]);
		for(int row=0; row<levelMap.getWidth(); row++){
			for(int col=0; col<levelMap.getHeight(); col++){
				if(levelMap.getTileProperty(levelMap.getTileId(row, col, 0), "platform", "false").equals("true")){
					getPlatform()[row][col] = true;
				}
				if (levelMap.getLayerCount() == 2) {
					String type = levelMap.getTileProperty(levelMap.getTileId(row, col, 1), "type", "false");
					if (type == null || type.equals(Constants.EMPTY)) continue;
					if (type.equals("cloud")) {
						platforms.add(new CloudPlatform(row * 64, col * 64));
					}
					else if (type.equals("electric")) {
						enemyList.add(new ElectricBall(row * 64, col * 64));
					}
					else if (type.equals("snow")) {
						enemyList.add(new SnowBall(row * 64, col * 64));
					}
					else if (type.equals("bird")) {
						enemyList.add(new BirdBall(row * 64, col * 64));
					}
					
					//check for coin tile
					if(levelMap.getTileProperty(levelMap.getTileId(row, col, 1), "coin", "false").equals("true")){
						coin = new Sprite(new Image[] {ResourceManager.getImage("coin1"), ResourceManager.getImage("coin2"), ResourceManager.getImage("coin3"), ResourceManager.getImage("coin2")}, row*64, col*64, 0, 0);
					}
				}
			}
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta, GamePlayState myState){
		mainChar.update(gc, sbg, delta);
		for(Sprite e : enemyList){
			if (Math.abs(e.getY() - mainChar.getY()) <= 600) {
				e.update(gc, sbg, delta);
			}
			else{
				continue;
			}		
			if (!GamePlayState.getFalling() && !GamePlayState.getJumping() && checkHeroCollision(mainChar, e)){
				myState.setPause(true);
				init();
				myState.setPause(false);
			}
		}
		for(CloudPlatform p: platforms) {
			p.update(gc, sbg, delta);
		}
		if(!ResourceManager.getGlobalBoolean("level"+levelId+"coin")){
			if(levelId != 7){
				if(checkHeroCollision(mainChar, coin)){
					ResourceManager.updateGlobal("level"+levelId+"coin", "true");
				}
				coin.update(gc, sbg, delta);
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g){
		levelMap.render(0, 0, 0);
		for(Sprite e : enemyList){
			e.render(gc, g);
		}
		for(CloudPlatform p: platforms) {
			p.render(gc, g);
		}
		if(!ResourceManager.getGlobalBoolean("level"+levelId+"coin")){
			if(levelId != 7){
				coin.render(gc, g);
			}
		}
		//mainChar.render(gc, g);
	}
	
	
	public TiledMap getLevelMap() {
		return levelMap;
	}

	public void setLevelMap(TiledMap levelMap) {
		this.levelMap = levelMap;
	}

	public Sprite getMainChar() {
		return mainChar;
	}

	public void setMainChar(Sprite mainChar) {
		this.mainChar = mainChar;
	}

	public ArrayList<Sprite> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Sprite> enemyList) {
		this.enemyList = enemyList;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean[][] getPlatform() {
		return platform;
	}

	public void setPlatform(boolean[][] platform) {
		this.platform = platform;
	}
	
	public static boolean checkHeroCollision(Sprite mainChar, Sprite b) {
		if (mainChar.getX() + 60 < b.getX()) return false;
		if (mainChar.getX() + 4> b.getX() + 64) return false;
		if (mainChar.getY() + 64 < b.getY()) return false;
		if (mainChar.getY() + 48 > b.getY() + 64) return false;
		return true;
	}
	
	public static boolean checkCollision(Sprite a, Sprite b) {
		if (a.getX() + 64 < b.getX()) return false;
		if (a.getX() > b.getX() + 64) return false;
		if (a.getY() + 64 < b.getY()) return false;
		if (a.getY() > b.getY() + 64) return false;
		return true;
	}

	public ArrayList<CloudPlatform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(ArrayList<CloudPlatform> platforms) {
		this.platforms = platforms;
	}
}
