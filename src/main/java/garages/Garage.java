package garages;

public class Garage implements Comparable{

	private String name;

	public Garage(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (null == name) {
			throw new IllegalArgumentException("name is null");
		}

		this.name = name;
	}

	@Override
	public String toString() {
		return "Garage " + name;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
