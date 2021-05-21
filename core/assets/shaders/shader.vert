attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;
uniform float u_timer;

varying vec4 v_color;
varying vec2 v_texCoords;



void main(){
    v_texCoords=a_texCoord0;
    v_color=a_color;

    gl_Position=u_projTrans*a_position+vec4(0.0,sin(a_position.x+u_timer)/16.0,0.0,0.0);
}