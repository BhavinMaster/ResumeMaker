package rb.resumecareer.resumeDatabase;

import java.util.ArrayList;

import rb.resumecareer.resume.R;
import rb.resumecareer.resumePojo.User;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class CustomDataAdapter extends ArrayAdapter<User> {
	Context context;
	ArrayList<User> user;
	ImageButton btn;
	TextView txt;

	SQLiteDatabase database;
	DatabaseHandler dbHelper;

	public void open() {
		database = dbHelper.getReadableDatabase();
	}

	public void close() {

		database.close();
		dbHelper.close();
	}

	public CustomDataAdapter(Context context, ArrayList<User> user) {
		super(context, R.layout.my_list, user);
		// TODO Auto-generated constructor stub
		dbHelper = new DatabaseHandler(context);
		this.context = context;
		this.user = user;
	}

	// Retrieving all User name
	public ArrayList<User> getAllLabels() {

		Cursor mcursor = database.rawQuery("SELECT * FROM user_profile", null);

		ArrayList<User> result = new ArrayList<User>();

		if (mcursor != null && mcursor.moveToNext()) {
			do {
				User u = new User();
				u.getId();
				u.getName();
				result.add(u);
			} while (mcursor.moveToNext());

		}
		mcursor.close();
		database.close();
		return result;
	}

	@Override
	public int getCount() {
		return user.size();
	}

	@Override
	public User getItem(int position) {

		return user.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		final User u1 = user.get(position);
		if (v == null) {
			LayoutInflater li = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.my_list, null);
		}
		btn = (ImageButton) v.findViewById(R.id.btnListViewDelete);
		txt = (TextView) v.findViewById(R.id.txtViewListViewItem);
		txt.setText(u1.getName());
		final CustomDataAdapter custom = this;
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				final AlertDialog.Builder build = new AlertDialog.Builder(context);
				build.setMessage("Do you want to delete ?");
				build.setTitle("Delete "+u1.getName());
				build.setIcon(R.drawable.del);
				build.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dbHelper.deletedata(u1.getId());
								custom.remove(custom.getItem(position));
								v.invalidate();
								v.refreshDrawableState();
								custom.notifyDataSetChanged();
								dialog.cancel();
							}
						});

				build.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

								dialog.dismiss();
							}
						});
				AlertDialog alert = build.create();

				alert.show();

			}
		});

		dbHelper.close();
		return v;

	}
}
