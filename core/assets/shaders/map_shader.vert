attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;

varying vec4 v_color;
varying vec2 v_texCoords;

uniform float u_time;

void main(){
    v_texCoords=a_texCoord0;
    v_color=a_color;

    gl_Position=u_projTrans*a_position;//+vec4(0.0,sin(a_position.x+u_time/50.0),0.0,0.0)/50.0;
}