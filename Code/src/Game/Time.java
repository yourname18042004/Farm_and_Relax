package Game;

public class Time {
	
	private float current_time;
	private float period_time;
	private long time_system;
	
	public static Time time ;
	
	public Time() {
		current_time = 0;
		period_time = 0;
		time_system = 0;
	}
	
	public static void init()
	{
		time  = new Time();
	}
	
	public void time_update() {
		if(time_system == 0)
		{
			time_system = System.nanoTime();
		}
		
		long end = System.nanoTime();
		long start = time_system;
		time_system = System.nanoTime();
		
		period_time = (end - start) / 1000000000.0f;
		current_time += period_time;
	}

	public float getCurrent_time() {
		return current_time;
	}

	public float getPeriod_time() {
		return period_time;
	}

}
