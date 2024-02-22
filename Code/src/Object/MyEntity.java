package Object;

import org.joml.Vector3f;

public class MyEntity {
	protected Vector3f position;
	
	public MyEntity() {
		
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}	
}
