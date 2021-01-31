package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.Add_Location;
import pojo.Location;

public class TestData {

	
	public Add_Location addLocation(String name,String language, String Address) {
		Add_Location add = new Add_Location();
		add.setAccuracy(50);
		add.setName(name);
		add.setPhone_number("999999998");
		add.setAddress(Address);
		add.setWebsite("www.google.com");
        add.setLanguage(language);
        
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        add.setLocation(l);
        List<String>a = new ArrayList<String>();
        a.add("shoe park");
        a.add("shop");
        add.setTypes(a);
        return add;
	}

	public String deletePlace(String placeId) {
		
		return "{\r\n\"place_id\":\""+placeId+"\"\r\n}";	
	}
	
}
