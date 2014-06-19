package com.example.facebookconnectionapp;

import java.util.Arrays;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.i("Facebook Activity", "Before opening session");

		Session.openActiveSession(this, true, new Session.StatusCallback() {

			@Override
			public void call(Session session, SessionState state, Exception exception) {
				// TODO Auto-generated method stub

				Log.i("Facebook Activity", "Checking if session open");

				if(session.isOpened()){

					Log.i("Facebook Activity", "Session is open");
					Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

						@Override
						public void onCompleted(GraphUser user, Response response) {
							// TODO Auto-generated method stub

							Log.i("Facebook Activity", "Inside GraphCallback");

							TextView textView = (TextView) findViewById(R.id.textView1);
							textView.setText(user.getUsername());
						}
					});
					
					Log.i("Facebook Activity", "Before granting permission");

					session.requestNewPublishPermissions(new Session.NewPermissionsRequest(new MainActivity(), Arrays.asList("publish_actions")));

					Request.executeStatusUpdateRequestAsync(session, "Testing from AVD", new Request.Callback() {

						@Override
						public void onCompleted(Response response) {
							// TODO Auto-generated method stub

							Log.i("Facebook Activity posting", response.toString());

						}
					});
				} else {

					Log.i("Facebook Activity", "Session not open");
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}

}
