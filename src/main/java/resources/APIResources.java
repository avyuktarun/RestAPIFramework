package resources;

//An enum is a special "class" that represents a collection 
//of constants & methods (unchangeable variables, like final variables).
public enum APIResources {
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	APIResources(String resource) {
		this.resource=resource;
	}
	public String getResource() {
		return resource;
	}
	
}
