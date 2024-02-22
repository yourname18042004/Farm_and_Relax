package Game;

import Graphics.Camera;
import Graphics.Mesh;
import Graphics.Scene;
import Graphics.SceneRender;
import Object.Plant.Non_Woody_Plant.Non_Crops_Plant.Grass;
import Object.Plant.Woody_Plant.Pine_tree;
import Object.Soil.Soil;

import static org.lwjgl.glfw.GLFW.*;

import java.util.Random;

import org.joml.Vector3f;

import Data_Structure.HandleObjects;

public class Game {
	
	private Display display;
	private Scene scene;
	private SceneRender sceneRender; 
	private Mesh mesh;
	private Mesh meshGrass;
	private Mesh meshTree;
	private Camera camera;
	
	private HandleObjects map;
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 600; 
	
	public Game() {
		init_game();
		update_game();	
		close_game();
	} 
	
	public void init_game() {
		display = new Display();
		display.Init(WIDTH, HEIGHT);
	
		Time.init();
		Input.init(display);
		
	    camera = new Camera(Input.input, new Vector3f(10.0f, 6.0f, 5.0f), 45.0f, 0.1f, 1000.0f);
	    
		
		float[] positions = new float[]{
				0.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f,
                
                0.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
        };
		float[] positionsGrass = new float[]{
				0.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f,
                0.0f, 0.1f, 1.0f,
                1.0f, 0.1f, 1.0f,
                
                0.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                0.0f, 0.1f, 0.0f,
                1.0f, 0.1f, 0.0f,
        };
		float[] positionsTree = new float[] {
				0.40f, 0.0f, 0.40f,
				0.40f, 0.0f, 0.60f,
				0.60f, 0.0f, 0.60f,
				0.60f, 0.0f, 0.40f,
				
				0.40f, 0.3f, 0.40f,
				0.40f, 0.3f, 0.60f,
				0.60f, 0.3f, 0.60f,
				0.60f, 0.3f, 0.40f,
				
				0.10f, 0.3f, 0.10f,
				0.10f, 0.3f, 0.90f,
				0.90f, 0.3f, 0.90f,
				0.90f, 0.3f, 0.10f,
				
				0.10f, 0.8f, 0.10f,
				0.10f, 0.8f, 0.90f,
				0.90f, 0.8f, 0.90f,
				0.90f, 0.8f, 0.10f
		};
        float[] colors = new float[]{
                0.498f, 0.260f, 0.268f,
                0.498f, 0.260f, 0.268f,
                0.498f, 0.317f, 0.211f,
                0.498f, 0.317f, 0.211f,
                
                0.498f, 0.260f, 0.268f,
                0.498f, 0.260f, 0.268f,
                0.498f, 0.317f, 0.211f,
                0.498f, 0.317f, 0.211f,
        };
        float[] colorsGrass = new float[]{
        		
        		0.207f, 0.721f, 0.345f,
                0.207f, 0.721f, 0.345f,
        		0.0f, 1.0f, 0.5f,
                0.0f, 1.0f, 0.5f,

                0.207f, 0.721f, 0.345f,
                0.207f, 0.721f, 0.345f,
                0.0f, 1.0f, 0.5f,
                0.0f, 1.0f, 0.5f,
        };
        
        float[] colorsTree = new float[]{
        		
        		0.498f, 0.260f, 0.268f,
                0.498f, 0.260f, 0.268f,
                0.498f, 0.317f, 0.211f,
                0.498f, 0.317f, 0.211f,
                
                0.498f, 0.260f, 0.268f,
                0.498f, 0.260f, 0.268f,
                0.498f, 0.317f, 0.211f,
                0.498f, 0.317f, 0.211f,
        		
        		0.207f, 0.721f, 0.345f,
                0.207f, 0.721f, 0.345f,
        		0.0f, 1.0f, 0.5f,
                0.0f, 1.0f, 0.5f,

                0.207f, 0.721f, 0.345f,
                0.207f, 0.721f, 0.345f,
                0.0f, 1.0f, 0.5f,
                0.0f, 1.0f, 0.5f,
        };
        int[] indices = new int[]{
                0, 1, 2, 
                3, 1, 2,
                4, 6, 5,
                5, 6, 7,
                2, 3, 6, 
                3, 6, 7, 
                1, 7, 3, 
                1, 7, 5, 
                0, 6, 2, 
                0, 6, 4
        };
        
        int[] indicesTree = new int[] {
        		0, 5, 1,
        		0, 5, 4, 
        		1, 6, 2,
        		1, 6, 5,
        		2, 7, 3,
        		2, 7, 6,
        		3, 4, 0,
        		3, 4, 7,
        		
        		8, 13, 9,
        		8, 13, 12, 
        		9, 14, 10,
        		9, 14, 13,
        		10, 15, 11,
        		10, 15, 14,
        		11, 12, 8,
        		11, 12, 15,
        		8, 10, 9,
        		8, 10, 11,
        		12, 14, 13,
        		12, 14, 15
        };
        
        mesh = new Mesh(positions, colors, indices);
        meshGrass = new Mesh(positionsGrass, colorsGrass, indices);
        meshTree = new Mesh(positionsTree, colorsTree, indicesTree);
        
        scene = new Scene();
        scene.addMesh(Soil.class.getSimpleName(), mesh);
        scene.addMesh(Grass.class.getSimpleName(), meshGrass);
        scene.addMesh(Pine_tree.class.getSimpleName(), meshTree);
        
        Random random = new Random();
        map = new HandleObjects(10, 10);
        for(int i = 0; i < map.getWidth(); ++i) {
        	for(int j = 0; j < map.getHeight(); ++j) {
        		if(random.nextBoolean())
        		{
        			map.add(i, j, new Grass(new Vector3f(i, 1.0f, j)));
        		}
        	}
        }
        
        for(int i = 0; i < map.getWidth(); ++i) {
        	for(int j = 0; j < map.getHeight(); ++j) {
        		if(random.nextBoolean()) {
        			map.add(i, j, new Pine_tree(new Vector3f(i, 1.0f, j)));
        		}
        		
        	}
        }
        
        for(int i = 0; i < map.getWidth(); ++i) {
        	for(int j = 0; j < map.getHeight(); ++j) {
        		map.add(i, j, new Soil(new Vector3f(i, 0.0f, j)));
        	}
        }
        
        sceneRender = new SceneRender(camera, scene, map);
	}
	
	public void update_game() {
		while(!Input.input.keyPress(GLFW_KEY_ESCAPE) && !glfwWindowShouldClose(display.getWindow())) {
			event();
			update();
			render();
		}
	}
	
	private void event(){
		Input.input.update();
	}
	
	private void update() {
		Time.time.time_update();
	}
	
	private void render() {
		sceneRender.render(scene, map);
		display.SwapBuffer();
	}
	
	public void close_game() {

		display.Close();
	}
}
