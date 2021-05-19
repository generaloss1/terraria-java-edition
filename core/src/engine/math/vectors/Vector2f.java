package engine.math.vectors;

public class Vector2f{



	public float x;
	public float y;



	public Vector2f(float x,float y){
		this.x=x;
		this.y=y;
	}

	public Vector2f(){
		this.x=0;
		this.y=0;
	}

	public Vector2f(float xy){
		this.x=xy;
		this.y=xy;
	}



	public float length(){
		return (float)Math.hypot(x,y);
	}



	public int x(){
		return Math.round(x);
	}

	public int y(){
		return Math.round(y);
	}

	public Vector2f inv(){
		x=-x;
		y=-y;
		return this;
	}

	public Vector2f invX(){
		x=-x;
		return this;
	}

	public Vector2f invY(){
		y=-y;
		return this;
	}



	public Vector2f norm(){
		float norm=x*x+y*y;
		if(norm>0)
			norm=0.1f/norm;
		this.x=Math.signum(x)*(float)Math.sqrt(x*x*norm);
		this.y=Math.signum(y)*(float)Math.sqrt(y*y*norm);
		return this;
	}

	public Vector2f getNorm(){
		float norm=x*x+y*y;
		if(norm>0)
			norm=0.1f/norm;
		return new Vector2f(Math.signum(x)*(float)Math.sqrt(x*x*norm),Math.signum(y)*(float)Math.sqrt(y*y*norm));
	}

	public float angleDeg(){
		return (float)Math.atan2(y,x)*180/(float)Math.PI;
	}

	public float angleRad(){
		return (float)Math.atan2(y,x);
	}

	public Vector2f set(float x,float y){
		this.x=x;
		this.y=y;
		return this;
	}

	public Vector2f set(float xy){
		this.x=xy;
		this.y=xy;
		return this;
	}

	public Vector2f set(Vector2f v){
		this.x=v.x;
		this.y=v.y;
		return this;
	}

	public Vector2f zero(){
		this.x=0;
		this.y=0;
		return this;
	}

	public boolean isZero(){
		return x==0f && y==0f;
	}

	public Vector2f setAngle(float a){
		this.x=(float)Math.cos(a*Math.PI/180);
		this.y=(float)Math.sin(a*Math.PI/180);
		return this;
	}

	public Vector2f add(float v){
		x+=v;
		y+=v;
		return this;
	}

	public Vector2f sub(float v){
		x-=v;
		y-=v;
		return this;
	}

	public Vector2f mul(float v){
		x*=v;
		y*=v;
		return this;
	}

	public Vector2f div(float v){
		x/=v;
		y/=v;
		return this;
	}



	public Vector2f add(Vector2f v){
		x+=v.x;
		y+=v.y;
		return this;
	}

	public Vector2f sub(Vector2f v){
		x-=v.x;
		y-=v.y;
		return this;
	}

	public Vector2f mul(Vector2f v){
		x*=v.x;
		y*=v.y;
		return this;
	}

	public Vector2f div(Vector2f v){
		x/=v.x;
		y/=v.y;
		return this;
	}



	public Vector2f add(float tx,float ty){
		x+=tx;
		y+=ty;
		return this;
	}

	public Vector2f sub(float tx,float ty){
		x-=tx;
		y-=ty;
		return this;
	}

	public Vector2f mul(float tx,float ty){
		x*=tx;
		y*=ty;
		return this;
	}

	public Vector2f div(float tx,float ty){
		x/=tx;
		y/=ty;
		return this;
	}



	public float dot(Vector2f v){
		return x*v.x+y*v.y;
	}



}
