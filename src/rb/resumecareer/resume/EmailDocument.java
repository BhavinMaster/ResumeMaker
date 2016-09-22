package rb.resumecareer.resume;

import java.io.File;
import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.User;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailDocument extends Activity implements OnClickListener {

	EditText et_address, et_subject, et_message;
	String address, subject, message, namepdf;
	Button bt_send, bt_attach;
	TextView tv_attach;
	int idclick, id;
	final DatabaseHandler db = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		Intent in = getIntent();

		id = in.getIntExtra("id", 0);
		idclick = in.getIntExtra("idclick", 0);
		initializeViews();
		bt_send.setOnClickListener(this);
		bt_attach.setOnClickListener(this);
	}

	private void initializeViews() {
		et_address = (EditText) findViewById(R.id.et_address_id);
		et_subject = (EditText) findViewById(R.id.et_subject_id);
		et_message = (EditText) findViewById(R.id.et_message_id);
		bt_send = (Button) findViewById(R.id.bt_send_id);
		bt_attach = (Button) findViewById(R.id.bt_attach_id);
		tv_attach = (TextView) findViewById(R.id.tv_attach_id);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_attach_id:
			openGallery();
			break;

		case R.id.bt_send_id:
			address = et_address.getText().toString();
			subject = et_subject.getText().toString();
			message = et_message.getText().toString();

			if (id == 0) {
				id = idclick;
			}
			if (id != 0) {
				List<User> user = db.getUserpdf(id);

				for (User c : user) {
					namepdf = c.getName();
				}
				db.close();
			}
			try {
				File f = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/ResumeMaker/", namepdf + ".pdf");
				if (f.exists()) {
					bt_send.setEnabled(true);
					String emailAddresses[] = { address };

					Intent emailIntent = new Intent(
							android.content.Intent.ACTION_SEND);

					emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
							emailAddresses);
					emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
							subject);

					emailIntent.setType("message/rfc822");
					emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
							message);

					emailIntent.addFlags(Intent.FLAG_FROM_BACKGROUND);
					emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
					startActivity(emailIntent);
				} else {
					bt_send.setEnabled(false);
					tv_attach.setText("File Not found");
					Toast.makeText(getApplicationContext(), "No File Found",
							Toast.LENGTH_SHORT).show();
				}
			} catch (ActivityNotFoundException e) {
				Toast t = Toast.makeText(getApplicationContext(),
						"No Mail Client Found..", Toast.LENGTH_SHORT);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
			}
			break;

		}

	}

	private void openGallery() {
		if (id == 0) {
			id = idclick;
		}
		if (id != 0) {
			List<User> user = db.getUserpdf(id);

			for (User c : user) {
				namepdf = c.getName();
			}
		}
		File f = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/ResumeMaker/", namepdf + ".pdf");
		if (f.exists()) {
			tv_attach.setText(namepdf+".pdf");
			/*Intent intent = new Intent();
			//intent.setType("application/*");
			//intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivity(Intent.createChooser(intent, "Send mail..."));*/
		} else {
			tv_attach.setText("File Not found");
		}
	}
}
