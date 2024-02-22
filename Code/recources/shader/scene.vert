#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 color;

out vec3 passColor;

uniform mat4 View;
uniform mat4 Translate;
void main()
{
	gl_Position = View * (Translate * vec4(position, 1.0f));
	passColor = color;
}