package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.models.TweetsArrayAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);


        // find the lsit view
        lvTweets = (ListView) findViewById(R.id.lvTweets);

        // Create the arrayList
        tweets = new ArrayList<>();

        // Construct the adapter
        aTweets = new TweetsArrayAdapter(this, tweets);

        // Connect listview to adapter
        lvTweets.setAdapter(aTweets);

        client = TwitterApplication.getRestClient(); //singleton client

        populateTimeLine();


    }

    // Send
    private void populateTimeLine(){

        client.getHomeTimeLine(new JsonHttpResponseHandler() {

            // SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                Log.d("Debug: ",response.toString());

                // JSON here
                // DESERIALIZE JSON
                // CREATE MODELS and add them adapter
                // LOAD DATA INTO LISTVIEW

                ArrayList<Tweet> tweets = Tweet.fromJSONArray();
                aTweets.addAll(Tweet.fromJSON);


            }

            // FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                 Log.d("Debug: ",errorResponse.toString());
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
