package Object.Plant;

public enum Growth_phase {
	SEEDS(0), 
	SEEDLINGS(1), 
	MATURE_TREE(2);
	
	int i;
	Growth_phase(int i)
	{
		this.i = i;
	}
}
