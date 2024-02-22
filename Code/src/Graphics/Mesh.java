package Graphics;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.*;

import static org.lwjgl.opengl.GL30.*;

public class Mesh {
	private int numVertices;
    private int vaoId;
    private List<Integer> vboIdList;
    
    public Mesh(float[] positions, float[] colors, int[] indices) {
    	try(MemoryStack stack = MemoryStack.stackPush()){
    		this.numVertices = indices.length;
    		vboIdList = new ArrayList<Integer>();
    		
    		vaoId = glGenVertexArrays();
    		glBindVertexArray(vaoId);
    		
    		int vbo = glGenBuffers();
    		vboIdList.add(vbo);
    		FloatBuffer positionsBuffer = stack.callocFloat(positions.length);
    		positionsBuffer.put(0, positions);
    		glBindBuffer(GL_ARRAY_BUFFER, vbo);
    		glBufferData(GL_ARRAY_BUFFER, positionsBuffer, GL_STATIC_DRAW);
    		glEnableVertexAttribArray(0);
    		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    		
    		vbo = glGenBuffers();
    		vboIdList.add(vbo);
    		FloatBuffer colorsBuffer = stack.callocFloat(colors.length);
    		colorsBuffer.put(0, colors);
    		glBindBuffer(GL_ARRAY_BUFFER, vbo);
    		glBufferData(GL_ARRAY_BUFFER, colorsBuffer, GL_STATIC_DRAW);
    		glEnableVertexAttribArray(1);
    		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
    		
    		vbo = glGenBuffers();
    		vboIdList.add(vbo);
    		IntBuffer indicesBuffer = stack.callocInt(indices.length);
    		indicesBuffer.put(0, indices);
    		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);
    		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
    		
    		
    		glBindBuffer(GL_ARRAY_BUFFER, 0);
    		glBindVertexArray(0);
    	}
    }
    
    public void cleanup() {
    	vboIdList.forEach(GL30::glDeleteBuffers);
    	glDeleteVertexArrays(vaoId);
    }
    
    public int getNumVertices() {
    	return numVertices;
    }
    
    public int getVAO() {
    	return vaoId;
    }
}
