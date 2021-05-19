package engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Assets{


    final public static String RUSSIAN_LETTERS="абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\\\/?-+=()*&.;:,{}\\\"´`'<>";

    public static HashMap<String,Texture> textures=new HashMap<>();
    public static List<String> textures_ids=new ArrayList<>();


    public static boolean loadTexture(String path,String id){
        FileHandle file=Gdx.files.internal(path);
        if(!file.exists())
            return false;
        Texture texture=new Texture(file);
        textures.put(id,texture);
        textures_ids.add(id);
        return true;
    }

    public static boolean loadTexture(Texture texture,String id){
        textures.put(id,texture);
        textures_ids.add(id);
        return true;
    }

    public static Texture getTexture(String id){
        return textures.get(id);
    }

    public static void disposeTextures(){
        for(String id:textures_ids)
            textures.get(id).dispose();
        textures.clear();
    }



    public static HashMap<String,BitmapFont> ttfs=new HashMap<>();
    public static List<String> ttfs_ids=new ArrayList<>();

    public static void loadTTF(String path,String id,int size,Color color){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal(path));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=size;
        parameter.color=color;
        parameter.characters=RUSSIAN_LETTERS;
        BitmapFont ttf=generator.generateFont(parameter);
        ttfs.put(id,ttf);
        ttfs_ids.add(id);
        generator.dispose();
    }

    public static BitmapFont getTTF(String id){
        return ttfs.get(id);
    }

    public static void disposeTTFs(){
        for(String id:ttfs_ids)
            ttfs.get(id).dispose();
        ttfs.clear();
    }



    public static HashMap<String,TextureRegion> texture_regions=new HashMap<>();
    public static List<String> texture_regions_ids=new ArrayList<>();

    public static void loadTextureReg(String path,String id){
        Texture texture=new Texture(Gdx.files.internal(path));
        TextureRegion texture_region=new TextureRegion(texture);
        texture_regions.put(id,texture_region);
        texture_regions_ids.add(id);
    }

    public static void loadTextureReg(String path,String id,int x,int y,int w,int h){
        Texture texture=new Texture(Gdx.files.internal(path));
        TextureRegion texture_region=new TextureRegion(texture,x,y,w,h);
        texture_regions.put(id,texture_region);
        texture_regions_ids.add(id);
    }

    public static void loadTextureReg(Texture texture,String id){
        TextureRegion texture_region=new TextureRegion(texture);
        texture_regions.put(id,texture_region);
        texture_regions_ids.add(id);
    }

    public static void loadTextureReg(Texture texture,String id,int x,int y,int w,int h){
        TextureRegion texture_region=new TextureRegion(texture,x,y,w,h);
        texture_regions.put(id,texture_region);
        texture_regions_ids.add(id);
    }

    public static TextureRegion getTextureReg(String id){
        return texture_regions.get(id);
    }

    public static void disposeTextureRegs(){
        for(String id:texture_regions_ids)
            texture_regions.get(id).getTexture().dispose();
        texture_regions.clear();
    }



    public static void dispose(){
        disposeTextures();
        disposeTextureRegs();
        disposeTTFs();
    }



}
