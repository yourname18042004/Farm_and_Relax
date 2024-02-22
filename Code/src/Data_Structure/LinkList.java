package Data_Structure;

public class LinkList <T>{
	private Node<T> head;
	private Node<T> tail;
	
	public LinkList(){
		head = null;
		tail = null;
	}
	
	public boolean empty() {
		return head == null;
	}
	
	public void push(Node<T> node) {
		if(empty()) {
			head = node;
			tail = node;
		}
		else {
			node.setFront(tail);
			tail.setNext(node);
			tail = node;
		}
	}
	
	public void push(T value) {
		push(new Node<T>(value));
	}
	
	public void remove(Node<T> node) {
		node.getFront().setNext(node.getNext());
		node.getNext().setFront(node.getFront());
		node.setFront(null);
		node.setNext(null);
	}
	
	public Node<T> getHead(){
		return head;
	}
	
	public Node<T> getTail(){
		return tail;
	}
}
