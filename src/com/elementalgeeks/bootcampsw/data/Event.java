package com.elementalgeeks.bootcampsw.data;

import java.util.Date;

public class Event {
	private Date date;
	private double lat,lng;
	private String id, city, country, website;	

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String toString(){
		return city + "," + country;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/*

	private static final String KEY_ID = "id";
	private static final String KEY_LAT = "lat";
	private static final String KEY_LNG = "lng";	
	private static final String KEY_DATE = "date";
	private static final String KEY_CITY = "city";	
	private static final String KEY_COUNTRY = "country";

	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
        try {
            obj.put(KEY_ID, id);
            obj.put(KEY_CITY, city);
            obj.put(KEY_COUNTRY, country);
            obj.put(KEY_LAT, lat);
            obj.put(KEY_LNG, lng);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            obj.put(KEY_DATE, dateFormat.format(date));            
        } catch (JSONException e) {}
		return obj;
	}
	
	public void fromJSONString(String strjson) {		
		try {
			JSONObject obj = new JSONObject(strjson);
			this.id = obj.getString(KEY_ID);
			this.city = obj.getString(KEY_CITY);
			this.country = obj.getString(KEY_COUNTRY);
			this.lat = obj.getDouble(KEY_LAT);
			this.lng = obj.getDouble(KEY_LNG);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  			
			try {  
				this.date = dateFormat.parse(obj.getString(KEY_DATE));  
			} catch (ParseException pe) {}			            						
		} catch (JSONException e) {}		
	}
	*/
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
}
