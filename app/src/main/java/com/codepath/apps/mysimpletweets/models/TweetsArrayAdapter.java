package com.codepath.apps.mysimpletweets.models;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by rbhavsar on 3/7/2015.
 */
// Taking the tweet object and turning them into views
    // that will be displayed in lists
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    // override and setup custom template


}
