package Object.Plant;

import java.util.List;

import Object.Agricultural.Agricultural;
import Object.MyObject;

public class Woody_plant extends MyObject implements Plant{

	protected Growth_phase Current_growth_phase;
	
	@Override
	public void Grow() {
		
	}

	@Override
	public List<Agricultural> Exploit() {
		return null;
	}

}
