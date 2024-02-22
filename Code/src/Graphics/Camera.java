package Graphics;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

import Game.Input;
import static Game.Game.*;

public class Camera {
	
	private Vector3f Position;
	private Vector3f Ori;
	private Vector3f Up;
	private Matrix4f View;
	private Matrix4f projection;

	private float speed;
	private float sensitivity;
	
	private Input input;
	
	public Camera(Input input, Vector3f Position, float FOVdeg, float near, float far) {
		
		this.input = input;
		
		Position = new Vector3f();
		Ori = new Vector3f(0.0f, 0.0f, -1.0f);
		Up = new Vector3f(0.0f, 1.0f, 0.0f);
		View = new Matrix4f();
		projection = new Matrix4f();
		
		this.Position = Position;
		
		speed = 0.1f;
		sensitivity = 10;
		
		projection.setPerspective((float) Math.toRadians(FOVdeg), (float)WIDTH / HEIGHT, near, far);
	}
	
	public void updateCamera() { 
		
		inputKey();
		inputMouse();
		
		Matrix4f view = new Matrix4f();
		
		Vector3f tmp = new Vector3f();
		Position.add(Ori, tmp);
		
		view.setLookAt(Position, tmp, Up);
		
		projection.mul(view, View);
	}
	
	private void inputKey() {
		Vector3f tmp = new Vector3f();
		Vector3f cross = new Vector3f();
		Vector3f up = new Vector3f();
		Ori.cross(Up, cross);
		cross.mul(speed);
		Up.mul(speed, up);
		Up.cross(cross, tmp);
		
		if(input.keyPress(GLFW_KEY_W)) {
			Position.add(tmp);
		}
		if(input.keyPress(GLFW_KEY_S)) {
			Position.sub(tmp);
		}
		if(input.keyPress(GLFW_KEY_D)) {
			Position.add(cross);
		}
		if(input.keyPress(GLFW_KEY_A)) {
			Position.sub(cross);
		}
		if(input.keyPress(GLFW_KEY_SPACE)) {
			Position.add(up);
		}
		if(input.keyPress(GLFW_KEY_LEFT_SHIFT)) {
			Position.sub(up);
		}
	}
	
	private void inputMouse() {
		if(input.getMouseFllow()) {
			Vector2f rot = new Vector2f();
			
			rot = input.getEccentricity().mul(sensitivity);
			
			Vector3f newOri = new Vector3f();
			Ori.cross(Up, newOri);
			newOri.normalize();
			
			Ori.rotateAxis((float)Math.toRadians(-rot.y),newOri.x, newOri.y, newOri.z, newOri);
			
			if(Math.abs(newOri.angle(Up)) > Math.toRadians(1)) {
				Ori = newOri;
			}
			
			Ori.rotateAxis((float)Math.toRadians(-rot.x),Up.x, Up.y, Up.z);
			Ori.normalize();
		}
	}
	
	public Matrix4f getMatrixView() {
		return View;
	}
	
}
