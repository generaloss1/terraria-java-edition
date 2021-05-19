#ifdef GL_ES
precision highp float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

varying sampler2D u_texture;

void main(){
    vec2 uv=v_texCoords;
    vec4 color=vec4(v_color.r,v_color.g,1.0-v_color.b,1.0);
    gl_FragColor=color*texture2D(u_texture,uv);
}