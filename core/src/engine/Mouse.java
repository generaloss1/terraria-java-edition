package engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Mouse implements InputProcessor{

    public boolean left,right;

    public Mouse(){
        Gdx.input.setInputProcessor(this);
    }

    public boolean keyDown(int keycode){
        return false;
    }

    public boolean keyUp(int keycode){
        return false;
    }

    public boolean keyTyped(char character){
        return false;
    }

    public boolean touchDown(int screenX,int screenY,int pointer,int button){
        if(button==Input.Buttons.LEFT){
            left=true;
            return true;
        }else if(button==Input.Buttons.RIGHT){
            right=true;
            return true;
        }
        return false;
    }

    public boolean touchUp(int screenX,int screenY,int pointer,int button){
        if(button==Input.Buttons.LEFT){
            left=false;
            return true;
        }else if(button==Input.Buttons.RIGHT){
            right=false;
            return true;
        }
        return false;
    }

    public boolean touchDragged(int screenX,int screenY,int pointer){
        return false;
    }

    public boolean mouseMoved(int screenX,int screenY){
        return false;
    }

    public boolean scrolled(int amount){
        return false;
    }

}
