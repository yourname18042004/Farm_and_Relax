package Game;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

public class Display {
	private long window;
	
	private int FPS;
	private float time;
	
	public void Init(int width, int height) { 
		GLFWErrorCallback.createPrint(System.err).set();

		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints(); 
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); 
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); 

		window = glfwCreateWindow(width, height, "Hello World!", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); 
		});

		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		}  

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);

		glfwShowWindow(window);
		
		GL.createCapabilities();

		glClearColor(0.1f, 0.806f, 1.0f, 0.0f);
		
		FPS = 0;
		time = 0;
	}
	
	public void SwapBuffer() {
		glfwSwapBuffers(window); 

		glfwPollEvents();
		
		time += Time.time.getPeriod_time();
		++FPS;
		if(time > 1) {
			String title = "FPS: " + FPS;
			GLFW.glfwSetWindowTitle(window, title);
			time = 0;
			FPS = 0;
		}
	}
	
	public void Close() {
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public long getWindow() {
		return window;
	}
}
