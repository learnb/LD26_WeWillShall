package net.umc.ludumdare.tools;

import java.util.Hashtable;

import net.umc.ludumdare.common.Constants;
import net.umc.ludumdare.common.ResourceTypes;
import net.umc.ludumdare.data.DataService;
import net.umc.ludumdare.exceptions.GlobalSettingDoesNotExistException;
import net.umc.ludumdare.exceptions.ResourceDoesNotExistException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.tiled.TiledMap;

/*
 * Author:	Chris McCullough
 * Purpose:	Class for retrieving game resources by ID instead of having to explicitly load them.
 * 			Also allows for accessing global variables. 
 */
public class ResourceManager {

	private static Hashtable<String, Object> resources = new Hashtable<String, Object>();
	private static Hashtable<String, String> globals = new Hashtable<String, String>();
	private static Hashtable<String, UnicodeFont> fonts = new Hashtable<String, UnicodeFont>();
	
	public static String getGlobal(String key) {
		if (key == null || key == Constants.EMPTY) {
			throw new IllegalArgumentException("A valid String is required for argument 'key'.");
		}
		
		if (!globals.containsKey(key)) {
			loadGlobal(key);
			if (!globals.containsKey(key)) {
				try {
					throw new GlobalSettingDoesNotExistException(String.format("Key '%s' does not exist.", key));
				} catch (GlobalSettingDoesNotExistException e) {
					e.printStackTrace();
				}
			}
		}
		
		return globals.get(key);
	}
	
	public static int getGlobalInt(String key) {
		return Integer.parseInt(getGlobal(key));
	}
	
	public static double getGlobalDouble(String key) {
		return Double.parseDouble(getGlobal(key));
	}
	
	public static boolean getGlobalBoolean(String key) {
		String value = getGlobal(key).toUpperCase();
		if (value.equals("TRUE") || value == "T" || value == "1") {
			return true;
		}
		return false;
	}
	
	public static TiledMap getMap(String res) {
		return (TiledMap)getResource(res, ResourceTypes.MAP);
	}
	
	public static Image getImage(String res) {
		return (Image)getResource(res, ResourceTypes.IMAGE);
	}
	
	public static Sound getSound(String res) {
		return (Sound)getResource(res, ResourceTypes.SOUND);
	}
	
	public static Music getMusic(String res) {
		return (Music)getResource(res, ResourceTypes.MUSIC);
	}
	
	public static Font getFont(String res) {
		return getFontResource(res);
	}
	
	public static Object getResource(String res) {
		return getResource(res, ResourceTypes.UNDEFINED);
	}
	
	public static Font getFontResource(String res) {
		if (fonts.containsKey(res)) {
			return fonts.get(res);
		}
		else return null;
	}
	
	public static Object getResource(String res, ResourceTypes type) {
		if (res == null || res == Constants.EMPTY) {
			throw new IllegalArgumentException("A valid String is required for res name.");
		}
		
		if (!resources.containsKey(res)) {
			loadResource(res, type);
			if (!resources.containsKey(res)) {
				try {
					throw new ResourceDoesNotExistException(String.format("Resource '%s' does not exist.", res));
				} catch (ResourceDoesNotExistException e) {
					e.printStackTrace();
				}
			}
		}
		
		return resources.get(res);
	}	
	private static void loadResource(String res, ResourceTypes type) {
		if (type == ResourceTypes.IMAGE) {
			try {
				Image image = new Image(DataService.getResourcePath(res, type));
				if (image != null  && !resources.containsKey(res)) {
					resources.put(res, image);
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		else if (type == ResourceTypes.SOUND) {
			try {
				Sound sound = new Sound(DataService.getResourcePath(res, type));
				if (sound != null && !resources.containsKey(res)) {
					resources.put(res, sound);
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}		
		}
		else if (type == ResourceTypes.MUSIC) {
			try {
				Music music = new Music(DataService.getResourcePath(res, type));
				if (music != null && !resources.containsKey(res)) {
					resources.put(res, music);
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}		
		}
		else if (type == ResourceTypes.MAP) {
			try {
				TiledMap map = new TiledMap(DataService.getResourcePath(res, type));
				if (map != null && !resources.containsKey(res)) {
					resources.put(res, map);
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}		
		}
		else if (type == ResourceTypes.FONT) {
			try {
				Font font = new UnicodeFont(DataService.getResourcePath(res, type), 72, false, false);//DataService.getResourcePath(res, type));
				if (font != null && !resources.containsKey(res)) {
					resources.put(res, font);
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}		
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Font generateNewFont(String res, int size, Color color) {
		if (fonts.containsKey(res)) {
			return fonts.get(res);
		}
		
		UnicodeFont font;
		try {
			font = new UnicodeFont(DataService.getResourcePath("coolfont", ResourceTypes.FONT), size, false, false);
			font.addAsciiGlyphs();
			font.getEffects().add(new ColorEffect());  // Create a default white color effect
			font.loadGlyphs();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			return null;
		}
		fonts.put(res, font);
		return font;
	}
	
	public static void updateGlobal(String key, String value) {
		if (!globals.containsKey(key)) {
			loadGlobal(key);
		}
		
		DataService.updateGlobal(key, value);
		if (globals.containsKey(key)) {
			globals.remove(key);
		}
		globals.put(key, value);
	}
	
	public static void insertResource(String res, String type, String path) {
		DataService.insertResource(res, type, path);
	}
	
	public static void updateResource(String res, String type, String path) {
		DataService.updateResource(res, type, path);
	}
	
	public static void addGlobal(String key, String value) {
		updateGlobal(key, value);
	}
	
	private static void loadGlobal(String key) {
		String value = DataService.getGlobalByKey(key);
		if (value != null && value != Constants.EMPTY) {
			globals.put(key, value);
		}
	}
}