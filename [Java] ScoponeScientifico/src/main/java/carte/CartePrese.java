package carte;

import java.util.ArrayList;
import java.util.List;

public class CartePrese extends GruppoCarte {

	public CartePrese() {
		super();
	}

	List<Carta> scope = new ArrayList<Carta>();
	
	public List<Carta> getScope() {
		return scope;
	}

	public void setScope(Carta scopa) {
		this.scope.add(scopa);
	}
}
