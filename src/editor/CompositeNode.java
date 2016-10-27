package editor;

import java.util.ArrayList;

public class CompositeNode {
	private ArrayList<Integer> data;

	public CompositeNode() {
		data = new ArrayList<Integer>();
	}

	public void AddData(int index) {
		data.add(index);
	}

	public ArrayList<Integer> GetData() {
		return data;
	}
}
