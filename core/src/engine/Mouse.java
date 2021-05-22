package engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Mouse implements InputProcessor{

    private boolean left,right;
    private int scrolled;

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

    public int scrolled(){
        int amount=scrolled;
        scrolled=0;
        return amount;
    }

    public boolean isLeftPressed(){
        return left;
    }

    public boolean isRightPressed(){
        return right;
    }

    public boolean touchDragged(int screenX,int screenY,int pointer){
        return false;
    }

    public boolean mouseMoved(int screenX,int screenY){
        return false;
    }

    public boolean scrolled(int amount){
        scrolled=amount;
        return true;
    }

}
