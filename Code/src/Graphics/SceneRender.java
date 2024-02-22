package Graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import static Graphics.SetUniform.SetUniformMatrix4f;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

import Data_Structure.HandleObjects;
import Game.Game;
import Object.MyObject;

public class SceneRender {
	
	private Shader shaderProgram;
	private Camera camera;
	
	private Scene scene;
	private HandleObjects map;
	
    public SceneRender(Camera camera, Scene scene, HandleObjects map) {
    	
    	this.camera = camera;
    	this.setScene(scene);
    	this.setMap(map);
    	
        List<Shader.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new Shader.ShaderModuleData("recources/shader/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new Shader.ShaderModuleData("recources/shader/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new Shader(shaderModuleDataList);
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render(Scene scene, HandleObjects map) {
    	
    	glViewport(0, 0, Game.WIDTH, Game.HEIGHT);
		
		glEnable(GL_COLOR_BUFFER_BIT);
		glEnable(GL_DEPTH_TEST);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    	
        shaderProgram.bind();

        camera.updateCamera();
        
        SetUniformMatrix4f(shaderProgram.getProgramId(), "View", camera.getMatrixView());
        
        for(int i = 0; i < map.getWidth(); ++i) {
        	for(int j = 0; j < map.getHeight(); ++j) {
        		map.getPart(i, j).getNodePart().forEach((key, value)->{
        			if(value != null) {
        				Draw(scene.getMeshMap().get(key), value.getValue());
        			}    			
        		});	
        	}
        }
                
        glBindVertexArray(0);

        shaderProgram.unbind();
    }
    
	/*
	 * private void Draw(int i, int j, String nameClass) { if(map.getNode(i, j,
	 * nameClass) != null) {
	 * 
	 * Mesh mesh = scene.getMeshMap().get(nameClass);
	 * 
	 * glBindVertexArray(mesh.getVAO()); glDrawElements(GL_TRIANGLES,
	 * mesh.getNumVertices(), GL_UNSIGNED_INT, 0); } }
	 */
    
    private void Draw(Mesh mesh, MyObject object) {
    	
    	Matrix4f translate = new Matrix4f();
		translate.setTranslation(object.getPosition());
		SetUniformMatrix4f(shaderProgram.getProgramId(), "Translate", translate);
		
    	glBindVertexArray(mesh.getVAO());
        glDrawElements(GL_TRIANGLES, mesh.getNumVertices(), GL_UNSIGNED_INT, 0);
    }

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public HandleObjects getMap() {
		return map;
	}

	public void setMap(HandleObjects map) {
		this.map = map;
	}
}
