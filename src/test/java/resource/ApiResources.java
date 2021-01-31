package resource;

public enum ApiResources {
	
	AddPlaceApi("/maps/api/place/add/json"),
	GetPlaceApi("/maps/api/place/get/json/"),
	DeletePlaceApi("/maps/api/place/delete/json/");
	
	private String Resource;
	
   ApiResources (String resource) {
		this.Resource=resource;
	}

   public String getApiResource() {
	   return Resource;
   }
   
   
}
