package com.elementalgeeks.bootcampsw.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.elementalgeeks.bootcampsw.App;
import com.elementalgeeks.bootcampsw.R;
import com.elementalgeeks.bootcampsw.data.Event;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventsMapFragment extends SupportMapFragment implements OnInfoWindowClickListener {
	private App app;
	private GoogleMap map;
	private Bundle savedInstanceState;
	private HashMap<Marker, Event> markers;	
	//public static final LatLng GUATEMALA = new LatLng(14.62, -90.56);

	@Override
	public void onCreate(Bundle savedInstnace) {
		super.onCreate(savedInstnace);
		markers = new HashMap<Marker, Event>();
		app = (App)getActivity().getApplicationContext();		
	}

	
	public void addMarkersToMap() {	
		ArrayList<Event> allTheEvents = app.getEvents();
		
		map.clear();
		markers.clear();
		
		for (Event e : allTheEvents) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMM", getResources().getConfiguration().locale);            
			LatLng location = new LatLng(e.getLat(), e.getLng());
			MarkerOptions options = new MarkerOptions()
	        						.position(location)
	        						.title(e.getCity() + ", " + e.getCountry())
	        						.snippet(dateFormat.format(e.getDate()));
									
			Marker m = map.addMarker(options);
			markers.put(m, e);
		}
	}

    	
	@Override
	public void onHiddenChanged(boolean hidden) {
	    super.onHiddenChanged(hidden);
	    if (!hidden) {
	        if (!app.getEvents().isEmpty() && markers.isEmpty()) {
	        	addMarkersToMap();
	        }
	    }
	}
	
    @Override
    public void onResume() {
        super.onResume();
        setupMap();
    }	
    
    public void setupMap() {
    	if (map == null) {
    		map = getMap();
            if (map != null) {
                if (savedInstanceState == null) {
                    //map.moveCamera(CameraUpdateFactory.newLatLngZoom(GUATEMALA, 10));
                    map.setMyLocationEnabled(true);
                    map.setOnInfoWindowClickListener(this);
                }
            }
        }    	
    }


	@Override
	public void onInfoWindowClick(Marker marker) {
		  Event clicked = markers.get(marker);
		  String website = clicked.getWebsite(); 
		  if (website != null && !website.equals("")) {
			  Intent intent = new Intent(Intent.ACTION_VIEW);
			  intent.setData(Uri.parse("http://" + website));
			  startActivity(intent);
		  } else {
			  Toast.makeText(getActivity(), getString(R.string.msg_website_empty), Toast.LENGTH_SHORT).show();  
		  }
	}
}
