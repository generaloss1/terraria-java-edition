package engine;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class Camera2D{

	public OrthographicCamera cam;
	public float size,x,y,angle,width,height;
	private int windowModeWidth,windowModeHeight;
	public boolean fullscreen;



	public void update(SpriteBatch b){
		cam.update();
		b.setProjectionMatrix(cam.combined);
	}

	private void configure(float size){
		int w=Gdx.graphics.getWidth();
		int h=Gdx.graphics.getHeight();

		if(h<w){
			cam.setToOrtho(false,size,size*h/w);
			this.width=size;
			height=size*h/w;
		}else{
			cam.setToOrtho(false,size*w/h,size);
			this.width=size*w/h;
			height=size;
		}

		cam.translate(x,y);
		cam.rotate(angle);
	}

	public Camera2D(float size){
		cam=new OrthographicCamera();
		configure(size);
		this.size=size;
	}

	public Camera2D(){
		int size=Math.max(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cam=new OrthographicCamera();
		configure(size);
		this.size=size;
	}

	public void fullScreen(boolean fs){
		if(fs){
			if(!fullscreen){
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
				fullscreen=true;
				windowModeWidth=Math.round(width);
				windowModeHeight=Math.round(height);
			}
		}else{
			if(fullscreen){
				Gdx.graphics.setWindowedMode(windowModeWidth,windowModeHeight);
				fullscreen=false;
			}
		}
	}

	public void rotate(float a){
		cam.rotate(a);
		angle+=a;
	}

	public void setAngle(float a){
		cam.rotate(-angle+a);
		angle=a;
	}

	public void lookAt(float x,float y){
		cam.translate(-this.x+x,-this.y+y);
		this.x=x;
		this.y=y;
	}

	public void translatePosition(float x,float y){
		cam.translate(x,y);
		this.x+=x;
		this.y+=y;
	}

	public void resize(float w,float h){
		resize(Math.max(w,h));
	}

	public void resize(float size){
		this.size=size;
		configure(size);
	}

	public void resize(){
		configure(size);
	}

	public void translateScale(float lss){
		resize(size+lss);
	}

	public Matrix4 projectionMatrix(){
		return cam.combined;
	}

}
