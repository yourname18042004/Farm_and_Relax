package Object.Soil;

import org.joml.Vector3f;

import Object.MyObject;

public class Soil extends MyObject{
	protected Propertices_of_soil propertices;
	
	public Soil() {
		
	}
	
	public Soil(Vector3f position) {
		this.position = position;
	}
	
	public Propertices_of_soil getPropertices() {
		return propertices;
	}

	public void setPropertices(Propertices_of_soil propertices) {
		this.propertices = propertices;
	}
}
