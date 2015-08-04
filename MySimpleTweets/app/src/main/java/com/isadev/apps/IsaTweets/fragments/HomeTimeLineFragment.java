package com.isadev.apps.IsaTweets.fragments;

import android.os.Bundle;
import android.util.Log;

import com.isadev.apps.IsaTweets.TwitterApplication;
import com.isadev.apps.IsaTweets.TwitterClient;
import com.isadev.apps.IsaTweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Isaac on 7/30/2015.
 */
public class HomeTimeLineFragment extends TweetsListFragment{
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the client
        client = TwitterApplication.getRestClient();//Singleton client
        populateTimeline();
    }

    // send an API request to get a timeline json
    // fill the listview by creating the tweet objects from the json
    private void populateTimeline() {
        client.getHomeTimeLine(new JsonHttpResponseHandler() {
            //SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                // DESERIALIZE JSON
                // CREATE MODELS AND ADD THEM TO THE ADAPTER
                // LOAD THE MODEL DATA INTO LISTVIEW
                addAll(Tweet.fromJSONArray(json));
                //Log.d("DEBUG", aTweets.toString());
            }

            //FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }

}
