package rb.resumecareer.resume;

import java.util.List;
import rb.resumecareer.resume.R;
import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.ReferenceDetailPojo;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReferenceDetail extends Activity {

	Button save;
	EditText name, detail, contact, email;
	final DatabaseHandler db = new DatabaseHandler(this);
	int i, id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reference_detail);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#307994")));
		initialize();
		Intent in = getIntent();
		id = in.getIntExtra("id", 0);
		final int idclick = in.getIntExtra("idclick", 0);
		if (idclick != 0) {

			List<ReferenceDetailPojo> retrivedata = db
					.getAllReferenceDetail(idclick);

			for (ReferenceDetailPojo p : retrivedata) {

				name.setText(p.getRefName().toString());
				detail.setText(p.getRefDetail().toString());
				contact.setText(p.getRefContact().toString());
				email.setText(p.getRefEmail().toString());
			}
			if (!name.getText().toString().isEmpty()
					|| !detail.getText().toString().isEmpty()
					|| !contact.getText().toString().isEmpty()
					|| !email.getText().toString().isEmpty()) {
				save.setText("Update");
			}
			id = idclick;
		}
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String name1 = name.getText().toString();
				String detail1 = detail.getText().toString();
				String contact1 = contact.getText().toString();
				String email1 = email.getText().toString();
				if (save.getText().toString().equalsIgnoreCase("save")) {
					if (name1.isEmpty()) {
						name.setError(Html
								.fromHtml("<font color='red'>Please Enter Reference Name</font>"));
					} else if (detail1.isEmpty()) {
						detail.setError(Html
								.fromHtml("<font color='red'>Please Enter Detail Of Your Reference</font>"));
					} else if (contact1.isEmpty()) {
						contact.setError(Html
								.fromHtml("<font color='red'>Please Enter Contact Number</font>"));
					} else if (email1.isEmpty()) {
						email.setError(Html
								.fromHtml("<font color='red'>Please Enter Email Id</font>"));
					} else if (!(android.util.Patterns.EMAIL_ADDRESS
							.matcher(email1).matches())) {
						email.setError(Html
								.fromHtml("<font color='red'>Please Enter Valid Email Id</font>"));
					} else if (contact1.length() < 10) {
						contact.setError(Html
								.fromHtml("<font color='red'>Please Enter Valid Mobile Number</font>"));
					} else {
						i = db.addReferenceDetail(new ReferenceDetailPojo(
								name1, detail1, contact1, email1, id));
						Toast.makeText(getApplicationContext(),
								"References Saved", Toast.LENGTH_SHORT).show();
						db.close();
						finish();
					}
					
					if (i >= 1) {
						save.setText("Update");
					}
				} else {
					if (name1.isEmpty()) {
						name.setError(Html
								.fromHtml("<font color='red'>Please Enter Reference Name</font>"));
					} else if (detail1.isEmpty()) {
						detail.setError(Html
								.fromHtml("<font color='red'>Please Enter Detail Of Your Reference</font>"));
					} else if (contact1.isEmpty()) {
						contact.setError(Html
								.fromHtml("<font color='red'>Please Enter Contact Number</font>"));
					} else if (email1.isEmpty()) {
						email.setError(Html
								.fromHtml("<font color='red'>Please Enter Email Id</font>"));
					} else if (!(android.util.Patterns.EMAIL_ADDRESS
							.matcher(email1).matches())) {
						email.setError(Html
								.fromHtml("<font color='red'>Please Enter Valid Email Id</font>"));
					} else if (contact1.length() < 10) {
						contact.setError(Html
								.fromHtml("<font color='red'>Please Enter Valid Mobile Number</font>"));
					} else {
						int iu = db
								.updateReferenceDetail(new ReferenceDetailPojo(
										name1, detail1, contact1, email1, id));
						db.close();
						finish();
						if (iu >= 1) {
							Toast.makeText(getApplicationContext(), " Updated",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getApplicationContext(),
									" Not Updated", Toast.LENGTH_SHORT).show();
						}
					}
					
				}
			}
		});

	}

	protected void initialize() {
		save = (Button) findViewById(R.id.btnSaveReference);

		name = (EditText) findViewById(R.id.edtReferenceName);
		contact = (EditText) findViewById(R.id.edtReferenceContact);
		detail = (EditText) findViewById(R.id.edtReferenceDetail);
		email = (EditText) findViewById(R.id.edtReferenceEmail);
	}
}
