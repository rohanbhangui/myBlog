package com.example.myblog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddPostActivity extends Activity {
	
	public final static String POST_TITLE = "com.example.myfirstapp.POSTTITLE";
	public final static String POST_CONTENT = "com.example.myfirstapp.POSTCONTENT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_post);
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_post, menu);
		return true;
	}*/
	
	/** Called when the user clicks the Post button */
	public void postData (View view)
	{
		Intent intent = new Intent(this, HomeActivity.class);
		String postTitle = ((EditText) findViewById(R.id.post_title)).getText().toString();
		String postContent = ((EditText) findViewById(R.id.post_content)).getText().toString();
		
		intent.putExtra(POST_TITLE, postTitle);
		intent.putExtra(POST_CONTENT, postContent);
		
    	startActivity(intent);

	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
