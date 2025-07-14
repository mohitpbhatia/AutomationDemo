package apiAssignment;

import java.util.Map;

public class AssignmentSol_2a {

	private String name;
	private Map<String, Object> data;

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}

/*
 * { "name": "Apple MacBook Pro 16", "data": { "year": 2019, "price": 1849.99,
 * "CPUmodel": "Intel Core i9", "Harddisksize": "1 TB" } }
 */
