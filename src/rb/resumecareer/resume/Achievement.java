package rb.resumecareer.resume;

import java.util.ArrayList;

import rb.resumecareer.resumeDatabase.DatabaseAdapter;
import rb.resumecareer.resumeDatabase.DatabaseHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Achievement extends Activity {

	DatabaseAdapter dbAdapter;
	SQLiteDatabase dataBase;
	ListView listview;
	int value = 0, ide;
	Button bk;
	int idclick1;
	ArrayAdapter<String> adapter;
	ArrayList<String> data;
	ArrayList<String> view;
	final DatabaseHandler db = new DatabaseHandler(this);

	private AlertDialog.Builder build;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_achievement);

		Intent inte = getIntent();
		ide = inte.getIntExtra("id", 0);
		idclick1 = inte.getIntExtra("idclick", 0);

		bk = (Button) findViewById(R.id.btnAchievementAddMore);

		dbAdapter = new DatabaseAdapter(this);
		dbAdapter.open();

		try {
			listview = (ListView) findViewById(R.id.list_all_achievement);
			if (idclick1 != 0) {
				ide = idclick1;
				data = dbAdapter.getAllAchievementData(idclick1);
				view = new ArrayList<String>();
				for (int i = 2; i <= data.size(); i = i + 3) {
					view.add(data.get(i));
				}
				adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_activated_1, view);

				listview.setAdapter(adapter);
				adapter.setNotifyOnChange(true);

			}
			listview.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						final int arg2, long arg3) {
					// TODO Auto-generated method stub

					final String itemAt = listview.getItemAtPosition(arg2)
							.toString();
					build = new AlertDialog.Builder(Achievement.this);
					build.setTitle("Delete " + itemAt);
					build.setMessage("Do you want to delete ?");

					build.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									for (int i = 0; i <= data.size(); i++) {
										if (data.get(i).contains(itemAt)) {
											value = Integer.parseInt(data
													.get(--i));
											data.remove(i);
											data.remove(--i);
											data.remove(itemAt);
											view.remove(itemAt);
											break;
										}
									}
									db.deleteAchievementData(value);
									adapter.notifyDataSetChanged();
									Toast.makeText(getApplicationContext(),
											itemAt + " " + " is deleted.",
											Toast.LENGTH_SHORT).show();
									dialog.cancel();
								}
							});

					build.setNegativeButton("No",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
					AlertDialog alert = build.create();
					alert.show();

					return true;

				}
			});

			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					final String name = listview.getItemAtPosition(position)
							.toString();
					for (int i = 0; i <= data.size(); i++) {
						if (data.get(i).contains(name)) {
							value = Integer.parseInt(data.get(--i));
							break;
						}
					}

					Intent in = new Intent(getApplicationContext(),
							AchievementDetail.class);
					in.putExtra("idclick", value);
					startActivity(in);
				}
			});

			dbAdapter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		bk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Achievement.this,
						AchievementDetail.class);
				intent.putExtra("id", ide);
				startActivity(intent);

			}
		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (db != null) {
			db.close();
		}
		if (dataBase != null) {
			dataBase.close();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		super.onResume();
		DatabaseAdapter dbAdapter;
		dbAdapter = new DatabaseAdapter(this);
		dbAdapter.open();
		listview = (ListView) findViewById(R.id.list_all_achievement);

		data = dbAdapter.getAllAchievementData(ide);
		view = new ArrayList<String>();
		for (int i = 2; i <= data.size(); i = i + 3) {
			view.add(data.get(i));
		}
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_activated_1, view);

		listview.setAdapter(adapter);
		adapter.setNotifyOnChange(true);

	}

}
