package Game;

import static org.lwjgl.glfw.GLFW.*;
import static Game.Game.*;
import java.nio.DoubleBuffer;

import org.joml.*;
import org.lwjgl.system.MemoryStack;

public class Input {
	
	public static Input input;
	
	private boolean mouseFllow;
	private boolean press;
	
	private Vector2f positionMouse;
	private Vector2f eccentricity;
	
	private Display display;
	
	public Input(Display display) {
		this.display = display;
		positionMouse = new Vector2f();
		eccentricity = new Vector2f(0.0f, 0.0f);
		mouseFllow = false;
		press = false;
	}
	
	public static void init(Display display) {
		input = new Input(display);
	}
	
	public void update() {
		setPositionMouse();
		
		if(!press && keyPress(GLFW_KEY_LEFT_CONTROL) && keyPress(GLFW_KEY_F))
		{
			mouseFllow = !mouseFllow;
			if(mouseFllow) {
				glfwSetInputMode(display.getWindow(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
			}
			else {
				glfwSetInputMode(display.getWindow(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
			}
			press = true;
		}
		
		if(press && keyRelease(GLFW_KEY_LEFT_CONTROL) && keyRelease(GLFW_KEY_F)) {
			press = false;
		}
		
		if(mouseFllow) {
			eccentricity.x = (positionMouse.x - (float)WIDTH / 2.0f) / (float) WIDTH;
			eccentricity.y = (positionMouse.y - (float)HEIGHT / 2.0f) / (float) HEIGHT;
			glfwSetCursorPos(display.getWindow(), WIDTH / 2.0, HEIGHT / 2.0);
		}
	}
	
	public boolean getMouseFllow() {
		return mouseFllow;
	}
	
	public Vector2f getEccentricity()
	{
		return eccentricity;
	}
	
	public boolean keyPress(int key) {
		return glfwGetKey(display.getWindow(), key) == GLFW_PRESS;
	}
	
	public boolean keyRelease(int key) {
		return glfwGetKey(display.getWindow(), key) == GLFW_RELEASE;
	}
	
	public boolean buttonClick(int button) {
		return glfwGetMouseButton(display.getWindow(), button) == GLFW_PRESS;
	}
	
	private void setPositionMouse() {
		try(MemoryStack stack = MemoryStack.stackPush()){
			DoubleBuffer x = stack.callocDouble(1);
			DoubleBuffer y = stack.callocDouble(1);
			glfwGetCursorPos(display.getWindow(), x, y);
			positionMouse.x = (float) x.get();
			positionMouse.y = (float) y.get();
		}
	}
	
	public Vector2f getPositionMouse() {
		return positionMouse;
	}
	
}
