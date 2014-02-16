package com.elementalgeeks.bootcampsw.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.elementalgeeks.bootcampsw.App;
import com.elementalgeeks.bootcampsw.R;
import com.elementalgeeks.bootcampsw.data.Event;

public class EventsListFragment extends ListFragment {
	private App app; 
	private SimpleAdapter adapter;
	private ProgressBar progressBar;
	private final static String DATE_KEY = "date";
	private final static String PLACE_KEY = "place";		
	private List<Map<String, String>> events = new ArrayList<Map<String, String>>();
	
	@Override
	public void onCreate(Bundle savedInstnace) {
		super.onCreate(savedInstnace);
		app = (App)getActivity().getApplicationContext();
		app.apiRequest(this);
	}
	
	public void showEventsOnList() {
		ArrayList<Event> allTheEvents = app.getEvents();
		Map<String, String> event;
		for (Event e : allTheEvents) {
			event = new HashMap<String, String>(2);
			event.put(PLACE_KEY, e.getCity() + ", " + e.getCountry());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMM", getResources().getConfiguration().locale);            
			event.put(DATE_KEY, dateFormat.format(e.getDate()));
			events.add(event);
		}		
		progressBar.setVisibility(View.GONE);
		getListView().setVisibility(View.VISIBLE);		
		adapter.notifyDataSetChanged();		
	}
	
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
		adapter = new SimpleAdapter(inflater.getContext(), events,
                android.R.layout.simple_list_item_2, 
                new String[] {PLACE_KEY, DATE_KEY }, 
                new int[] {android.R.id.text1, android.R.id.text2 });
		
		setListAdapter(adapter);
 
		View view = inflater.inflate(R.layout.fragment_events_list, container, false);
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
		return view;
	}  
	
	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		super.onListItemClick(l, v, pos, id);
		ArrayList<Event> allTheEvents = app.getEvents();
		Event clicked = allTheEvents.get(pos);
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
