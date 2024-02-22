package Data_Structure;

import java.util.HashMap;
import Object.MyObject;

public class HandleObjects {
	private Part[][] parts;
	private int width;
	private int height;
	
	public class Part{
		public HashMap<String, Node<MyObject>> node;
		
		public Part(HashMap<String, Node<MyObject>> node) {
			this.node = node;
		}
		
		public HashMap<String, Node<MyObject>> getNodePart(){
			return node;
		}
	}
	
	public HandleObjects(int width, int height) {
		parts = new Part[width][height];
		this.width = width;
		this.height = height;
		
		load();
	}
	
	public void load() {
		for(int i = 0; i < width; ++i) {
			for(int j = 0; j < height; ++j) {
				parts[i][j] = new Part(new HashMap<String, Node<MyObject>>());
			}
		}
	}
	
	public void add(int i, int j, MyObject object) {
		
		parts[i][j].node.put(object.getClass().getSimpleName(), new Node<MyObject>(object));
	}
	
	
	public Node<MyObject> getNode(int i, int j, String key){
		try {
			parts[i][j].node.get(key);
		}
		catch(Exception e){
			return null;
		}
		return parts[i][j].node.get(key);
	}
	
	public Part getPart(int i, int j) {
		return parts[i][j];
	}
	
	public Part[][] getParts(){
		return parts;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
