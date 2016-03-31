package carte;

import java.util.HashMap;

public class Primiera {
	private final static HashMap<Integer, Integer> valoriPrimiera = new HashMap<Integer, Integer>();	

	static {
		valoriPrimiera.put(7, 21);
		valoriPrimiera.put(6, 18);
		valoriPrimiera.put(1, 16);
		valoriPrimiera.put(5, 15);
		valoriPrimiera.put(4, 14);
		valoriPrimiera.put(3, 13);
		valoriPrimiera.put(2, 12);
		valoriPrimiera.put(10, 10);
		valoriPrimiera.put(9, 10);
		valoriPrimiera.put(8, 10);
	}

	public static int getPrimieraValue(Integer index) {		
		if(valoriPrimiera.containsKey(index)){
			return valoriPrimiera.get(index);
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(valoriPrimiera.keySet());
		System.out.println(valoriPrimiera);
		System.out.println(getPrimieraValue(5));
	}
}
