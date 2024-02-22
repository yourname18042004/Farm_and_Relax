#version 330

out vec4 fragColor;

in vec3 passColor;

void main()
{
	fragColor = vec4(passColor, 1.0f);
}