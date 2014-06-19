package com.example.exampletweetdeep;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	String consumerKey = "Vfp4eH3AsK6uURiZupIA";
	String consumerSecret = "jkPoNMYtYRJdIPGMpr3Mk4jnQAZXaaECDt2BWDWYpE";
	String accessToken = "1569777366-xkqoONXjfjMG2Uy57yJSfV5Z6wOGn0YaYtPJZxK";
	String accessSecret = "Zo8VWOy5CUIWzjZsHiSZfaaBbHtbF9gM975EfK6E";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("Twitter activity", "First line");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.i("Twitter activity", "Before building configuration");

		ConfigurationBuilder confB = new ConfigurationBuilder();
		confB.setDebugEnabled(true);
		confB.setOAuthConsumerKey(consumerKey);
		confB.setOAuthConsumerSecret(consumerSecret);
		confB.setOAuthAccessToken(accessToken);
		confB.setOAuthAccessTokenSecret(accessSecret);

		Log.i("Twitter activity", "After building configuration");

		TwitterFactory tF = new TwitterFactory(confB.build());
		Twitter twitter = tF.getInstance();

		try {
			Status status = twitter.updateStatus("Testing from android");

			Log.i("Twitter activity", "Done updating status");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
