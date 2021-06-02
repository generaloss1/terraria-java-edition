#ifdef GL_ES
precision highp float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

varying sampler2D u_texture;

uniform float u_time;

void main(){
    vec2 uv=v_texCoords;//vec2(v_texCoords.x,sin(v_texCoords.x+u_time/50.0));
    vec4 color=vec4(v_color.r,v_color.g,v_color.b,1.0);
    vec4 tex_color=texture2D(u_texture,uv);
    vec4 inv_color=vec4(1.0-color.r,1.0-color.g,1.0-color.b,1.0);
    gl_FragColor=inv_color*tex_color;
}