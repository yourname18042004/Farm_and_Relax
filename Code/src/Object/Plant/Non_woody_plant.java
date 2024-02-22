package Object.Plant;

import java.util.List;

import Object.Agricultural.Agricultural;
import Object.Soil.Soil;

import org.joml.Vector3f;

import Object.MyObject;

public class Non_woody_plant extends MyObject implements Plant{
	protected float Current_growth_time;
	protected List<Float> list_time_growth_phase;
	protected Growth_phase Current_growth_phase;
	protected Soil soil;
	
	public Non_woody_plant() {
	}
	
	public Non_woody_plant(Vector3f position) {
		//super(position);
	}
	
	@Override
	public void Grow() {
		
	}

	@Override
	public List<Agricultural> Exploit() {
		return null;
	}

	public Soil getSoil() {
		return soil;
	}

	public void setSoil(Soil soil) {
		this.soil = soil;
	}
}
