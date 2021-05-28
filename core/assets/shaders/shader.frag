#ifdef GL_ES
precision highp float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

varying sampler2D u_texture;

uniform float u_force;

void main(){
    vec2 uv=v_texCoords;
    vec4 color=vec4(v_color.r,v_color.g,v_color.b,u_force);
    gl_FragColor=color*texture2D(u_texture,uv);
}