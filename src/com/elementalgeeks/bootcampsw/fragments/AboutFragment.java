package com.elementalgeeks.bootcampsw.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.elementalgeeks.bootcampsw.R;

public class AboutFragment extends Fragment {
	WebView webView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_about, container, false);
		webView = (WebView)view.findViewById(R.id.webView);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		webView.loadUrl("http://ykro.net/gdgabout.html");
		webView.setWebViewClient(new WebViewClient(){
			@Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        view.loadUrl(url);
		        return true;
		    }			
		});		
		
	}

}
