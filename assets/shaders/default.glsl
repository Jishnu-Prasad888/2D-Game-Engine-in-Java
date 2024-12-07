// Creating the vertex shader

//#type vertex

#version 460 core
layout (location = 0) in vec3 aPos;// Putting in a in apos to knwo that we are getting a attribute. WKT that it is an attibute as we are getting it from the layout
layout (location = 1) in vec4 aColor; // Color input

// passing a variable to the fragment shader
out vec4 fColor;

void main() {
    fColor = aColor;
    gl_Position = vec4(aPos,1.0);
}


// Creating the fragment shader
//#type fragment


#version 460 core

in vec4 fColor;
out vec4 fragColor;

void main(){
    fragColor = fColor; // passing the color from the vertex shadder into the fragment shader
}

