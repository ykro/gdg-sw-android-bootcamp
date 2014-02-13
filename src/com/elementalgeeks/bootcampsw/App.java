package com.elementalgeeks.bootcampsw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.elementalgeeks.bootcampsw.data.Event;
import com.elementalgeeks.bootcampsw.fragments.EventsListFragment;
import com.elementalgeeks.bootcampsw.fragments.EventsMapFragment;

public class App extends Application {
	private String TAG;
	private RequestQueue requestQueue;
	private ArrayList<Event> events = new ArrayList<Event>();
		
	@Override
	public void onCreate() {
		super.onCreate();
		TAG = getPackageName();
		requestQueue = Volley.newRequestQueue(this);
	}

	public void apiRequest(final Fragment caller){
	    Response.Listener<JSONArray> successListener = 
	    		new Response.Listener<JSONArray>() {
		            @Override
		            public void onResponse(JSONArray response) {
		            	for (int i = 0; i < response.length(); i++) {
		            		JSONObject JSONevent;
							try {
								JSONevent = response.getJSONObject(i);
								String website = "startupweekend.org";
								try {
									website = JSONevent.getString("website");
								} catch (JSONException ex) {
									Log.e(TAG,Log.getStackTraceString(ex));
								}

			            		Event e = new Event();
			            		e.setId(JSONevent.getString("id"));
			            		e.setCity(JSONevent.getString("city"));
			            		e.setCountry(JSONevent.getString("country"));				            		
			            		e.setWebsite(website);
			            		e.setLat(JSONevent.getJSONObject("location").getDouble("lat"));
			            		e.setLng(JSONevent.getJSONObject("location").getDouble("lng"));
			            		
			            		//http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html#timezone
			            		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
			            	            									    getResources().getConfiguration().locale);
			            		try {  
			            		    Date date = format.parse(JSONevent.getString("start_date"));  
			            		    e.setDate(date);  
			            		} catch (ParseException pe) {
			            			Log.e(TAG,Log.getStackTraceString(pe));
								}
			            		events.add(e);
							} catch (JSONException ex) {
								Log.e(TAG,Log.getStackTraceString(ex));
							}
		            	}
		            	
		            	if (caller instanceof EventsListFragment) {
		            		((EventsListFragment) caller).showEventsOnList();
		            	} else if (caller instanceof EventsMapFragment) {
		            		((EventsMapFragment) caller).addMarkersToMap();
		            	}
		            }
	    };

	    Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", getResources().getConfiguration().locale);
	    String sinceDate = formatter.format(calendar.getTime());	    
	    calendar.add(Calendar.DATE, 15);
	    String untilDate = formatter.format(calendar.getTime());
	    	    
	    String URL = "http://swoop.startupweekend.org/events?since=" + sinceDate + "&until=" + untilDate;
		JsonArrayRequest request = new JsonArrayRequest(URL, successListener, null);		
		requestQueue.add(request);			
	}

	public String getTAG() {
		return TAG;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}	
}
