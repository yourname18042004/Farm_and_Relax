package Graphics;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

public class SetUniform {
	public static void SetUniformMatrix4f(int shaderID, String uniformName, Matrix4f mat) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
            Integer location = glGetUniformLocation(shaderID, uniformName);
            
            glUniformMatrix4fv(location.intValue(), false, mat.get(stack.mallocFloat(16)));
        }
	}
}
