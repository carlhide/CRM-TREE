package application;

public class Controller<V> {
	
	private V view;
	
	public void setView(V view) {
		this.view = view;
	}
	
	public V getView() {
		return view;
	}

}
