package Data_Structure;

public class Node <T>{
	private Node<T> front;
	private Node<T> next;

	private T value;
	
	public Node() {
		value = null;
		front = null;
		next = null;
	}
	
	public Node(T value) {
		this.value = value;
		front = null;
		next = null;
	}
	
	public Node<T> getFront() {
		return front;
	}
	public void setFront(Node<T> front) {
		this.front = front;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
	
}
