package rb.resumecareer.resume;

import java.util.Calendar;
import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.PersonalDetailPojo;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalDetail extends Activity {

	Button save;
	EditText name, address, language, contact, email;
	RadioButton male, female;
	RadioGroup rgender;
	ImageButton img;
	int val = 0;
	int id;
	final DatabaseHandler db = new DatabaseHandler(this);
	TextView txtDate;
	private int mYear, mMonth, mDay;
	String ge,titleCaseValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_detail);
		initialize();

		language.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					String[] words = language.getText().toString().replaceAll("^[,\\s]+", "").split("[,\\s]+");
					StringBuilder sb = new StringBuilder();
					if (words[0].length() > 0) {
					    sb.append(Character.toUpperCase(words[0].charAt(0)) + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
					    for (int i = 1; i < words.length; i++) {
					        sb.append(", ");
					        sb.append(Character.toUpperCase(words[i].charAt(0)) + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
					    }
					}
					titleCaseValue = sb.toString();
					language.setText(titleCaseValue);
				}
			}
		});
		img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				// Launch Date Picker Dialog
				DatePickerDialog dpd = new DatePickerDialog(
						PersonalDetail.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// Display Selected date in textbox
								txtDate.setText(dayOfMonth + "-"
										+ (monthOfYear + 1) + "-" + year);
							}
						}, mYear, mMonth, mDay);
				dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
				dpd.show();

			}
		});
		Intent in = getIntent();
		id = in.getIntExtra("id", 0);
		final int idclick = in.getIntExtra("idclick", 0);
		if (idclick != 0) {

			List<PersonalDetailPojo> retrivedata = db
					.getAllPersonalDetail(idclick);

			for (PersonalDetailPojo p : retrivedata) {

				ge=p.getGender().toString();
				if(ge.equals("Male"))
				{
					male.setChecked(true);
					female.setChecked(false);
				}
				else if(ge.equals("Female"))
				{
					female.setChecked(true);
					male.setChecked(false);
				} 	
				name.setText(p.getPersonName().toString());
				address.setText(p.getAddress().toString());
				language.setText(p.getLanguage().toString());
				contact.setText(p.getMobile().toString());
				email.setText(p.getEmail().toString());
				txtDate.setText(p.getBirthdate().toString());
			}
			if (!name.getText().toString().isEmpty()
					|| !txtDate.getText().toString().isEmpty()
					|| !address.getText().toString().isEmpty()
					|| !language.getText().toString().isEmpty()
					|| !contact.getText().toString().isEmpty()
					|| !email.getText().toString().isEmpty()) {
				save.setText("Update");
			}
			id = idclick;
		}

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method s
				final String gender;
				int sel = rgender.getCheckedRadioButtonId();
				if (sel == male.getId()) {
					gender = male.getText().toString();
				} else {
					gender = female.getText().toString();
				}

				if (save.getText().toString().equalsIgnoreCase("save")) {
					String name1 = name.getText().toString();
					String date1 = txtDate.getText().toString();
					String address1 = address.getText().toString();
					//String language1 = language.getText().toString();
					String contact1 = contact.getText().toString();
					String email1 = email.getText().toString();

					if (name1.isEmpty()) {
						name.setError(Html
								.fromHtml("<font color='red'>Enter Your Name</font>"));
					}
					if (date1.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Your birth Date");
						spannableString
								.setSpan(
										new ForegroundColorSpan(
												getResources()
														.getColor(
																android.R.color.holo_red_light)),
										0, spannableString.length(), 0);
						Toast t = Toast.makeText(getApplicationContext(),
								spannableString, Toast.LENGTH_SHORT);
						t.setGravity(Gravity.CENTER, 0, 0);
						t.show();
					}
					else if (address1.isEmpty()) {
						address.setError(Html
								.fromHtml("<font color='red'>Please Enter Address</font>"));
					}
					/*else if (language1.isEmpty()) {
						language.setError(Html
								.fromHtml("<font color='red'>Please Enter Language</font>"));
					}*/
					else if (contact1.isEmpty()) {
						contact.setError(Html
								.fromHtml("<font color='red'>Please Contact Number</font>"));
					}
					else if (email1.isEmpty()) {
						email.setError(Html
								.fromHtml("<font color='red'>Please Email-id</font>"));
					}
					else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email1)
							.matches())) {
						email.setError(Html
								.fromHtml("<font color='red'>Please Valid Email-id</font>"));
					}
					else if (contact1.length() < 10) {
						contact.setError(Html
								.fromHtml("<font color='red'>Please Valid Contact Number</font>"));
					} else {
						val = db.addPersonalDetail(new PersonalDetailPojo(id,
								name.getText().toString(), gender, txtDate
										.getText().toString(), address
										.getText().toString(), language
										.getText().toString(), contact
										.getText().toString(), email.getText()
										.toString()));
						Toast.makeText(getApplicationContext(), "Personal Detail Saved",
								Toast.LENGTH_SHORT).show();

					}
					if (val >= 1) {
						save.setText("Update");
					}
				} else {
					int i = db.updatePersonalDetail(new PersonalDetailPojo(id,
							name.getText().toString(), gender, txtDate
									.getText().toString(), address.getText()
									.toString(), language.getText().toString(),
							contact.getText().toString(), email.getText()
									.toString()));
					if (i == 1) {
						Toast.makeText(getApplicationContext(),
								name.getText().toString() + " Updated",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(),
								name.getText().toString() + " Not Updated",
								Toast.LENGTH_SHORT).show();
					}
				}
				db.close();
			}
		});

	}

	protected void initialize() {
		save = (Button) findViewById(R.id.btnPersonalSave);
		name = (EditText) findViewById(R.id.edtPersonalName);
		address = (EditText) findViewById(R.id.edtPersonalAddress);
		language = (EditText) findViewById(R.id.edtPersonallanguage);
		contact = (EditText) findViewById(R.id.edtPersonalContact);
		email = (EditText) findViewById(R.id.edtPersonalEmail);
		male = (RadioButton) findViewById(R.id.radioMale);
		female = (RadioButton) findViewById(R.id.radioFemale);
		rgender = (RadioGroup) findViewById(R.id.radioGroupGender);
		img = (ImageButton) findViewById(R.id.imgCalendar);
		txtDate = (TextView) findViewById(R.id.txtPersonalSetBdate);

	}
}
