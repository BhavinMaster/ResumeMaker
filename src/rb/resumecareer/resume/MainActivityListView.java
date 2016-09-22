package rb.resumecareer.resume;

import java.util.ArrayList;

import rb.resumecareer.resumeDatabase.CustomDataAdapter;
import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.User;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivityListView extends Activity implements AnimationListener {

	SQLiteDatabase dataBase;
	ListView list;
	int i = 0;
	final DatabaseHandler db = new DatabaseHandler(this);
	ArrayList<User> user = null;
	CustomDataAdapter ca = null;
	Animation animFadein;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		ActionBar bar = getActionBar();
		bar.hide();
		animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.bounce);
		animFadein.setAnimationListener(this);
		list = (ListView) findViewById(android.R.id.list);
		list.setVisibility(View.VISIBLE);
		list.setAnimation(animFadein);
		dataBase = db.getReadableDatabase();
		showName();
		ca = new CustomDataAdapter(MainActivityListView.this, user);
		list.invalidate();
		list.refreshDrawableState();
		list.setAdapter(ca);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				User user = (User) parent.getItemAtPosition(position);
				int id1 = user.getId();

				Intent in = new Intent(getApplicationContext(), TabClass.class);
				in.putExtra("idclick", id1);
				startActivity(in);
			}
		});

		dataBase.close();

		Button newprofile = (Button) findViewById(R.id.back_2);
		newprofile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivityListView.this,
						MainActivity.class);
				startActivity(intent);
				ca.notifyDataSetChanged();
			}
		});

	}

	public void showName() {
		user = new ArrayList<User>();
		user.clear();
		String query = "SELECT * FROM user_profile";
		Cursor c = dataBase.rawQuery(query, null);
		if (c != null && c.getCount() != 0) {
			if (c.moveToFirst()) {
				do {
					User us = new User();
					us.setId(c.getInt(c.getColumnIndex("id")));
					us.setName(c.getString(c.getColumnIndex("name")));
					user.add(us);
				} while (c.moveToNext());
			}
		}

		c.close();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		list = (ListView) findViewById(android.R.id.list);
		dataBase = db.getReadableDatabase();
		showName();
		ca = new CustomDataAdapter(MainActivityListView.this, user);
		list.invalidate();
		list.refreshDrawableState();
		ca.notifyDataSetChanged();
		list.setAdapter(ca);
		db.close();

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

}