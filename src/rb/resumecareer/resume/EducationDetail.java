package rb.resumecareer.resume;

import java.util.Calendar;
import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.EducationPojo;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EducationDetail extends Activity {

	Button save;
	EditText degree, college, university, year, result;
	ImageView iv;
	String temp1, temp11,pe;
	RadioGroup rgPercentage;
	RadioButton percentage, cgpa;
	final DatabaseHandler db = new DatabaseHandler(this);
	private int mYear, mMonth, mDay;
	int id , m ,y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_education_detail);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#307994")));
		initialize();
		year.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SpannableString spannableString = new SpannableString(
						"Click on Calendar image to Select Year");
				spannableString.setSpan(new ForegroundColorSpan(getResources()
						.getColor(android.R.color.holo_red_light)), 0,
						spannableString.length(), 0);
				Toast t = Toast.makeText(getApplicationContext(),
						spannableString, Toast.LENGTH_SHORT);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
			}
		});
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Launch Date Picker Dialog
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						EducationDetail.this);
				alertDialog.setTitle("Select Action");
				alertDialog.setPositiveButton("Pursuing",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								m = 13;
								y = 9999;
								year.setText("Pursuing");
							}
						});

				alertDialog.setNegativeButton("Finish",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								DatePickerDialog dpd = new DatePickerDialog(
										EducationDetail.this,
										new DatePickerDialog.OnDateSetListener() {

											@Override
											public void onDateSet(
													DatePicker view, int year,
													int monthOfYear,
													int dayOfMonth) {
												// Display Selected date in
												// textbox
												m = monthOfYear;
												y = year;
												temp11 = monthGet(monthOfYear + 1);
												EducationDetail.this.year
														.setText(temp11 + " "
																+ year);
											}
										}, mYear, mMonth, mDay);
								((ViewGroup) dpd.getDatePicker()).findViewById(
										Resources.getSystem().getIdentifier(
												"day", "id", "android"))
										.setVisibility(View.GONE);
								dpd.show();
							}
						});
				alertDialog.show();
			}
		});
		Intent in = getIntent();
		id = in.getIntExtra("id", 0);
		final int idclick = in.getIntExtra("idclick", 0);
		if (idclick != 0) {

			List<EducationPojo> retrivedata = db.getAllEducationDetail(idclick);

			for (EducationPojo p : retrivedata) {

				pe = p.getPercentage().toString();
				result.setText(p.getResult().toString());
				if (pe.equals("%")) {
					percentage.setChecked(true);
					cgpa.setChecked(false);
				} else if (pe.equals("CGPA")) {
					percentage.setChecked(false);
					cgpa.setChecked(true);
				}
				degree.setText(p.getDegree().toString());
				university.setText(p.getUniversity().toString());
				college.setText(p.getCollege().toString());
				year.setText(p.getYearofpassing().toString());
			}
			if (!degree.getText().toString().isEmpty()
					|| !university.getText().toString().isEmpty()
					|| !year.getText().toString().isEmpty()
					|| !college.getText().toString().isEmpty()) {
				save.setText("Update");
			}
			id = idclick;
		}

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String degree1 = degree.getText().toString();
				String college1 = college.getText().toString();
				String university1 = university.getText().toString();
				String result1 = result.getText().toString();
				String yr = year.getText().toString();
				final String per;
				int i = 0;
				int sel = rgPercentage.getCheckedRadioButtonId();
				if (sel == percentage.getId()) {
					per = "%";
				} else {
					per = cgpa.getText().toString();
				}
				if (save.getText().toString().equalsIgnoreCase("save")) {
					if (degree1.isEmpty()) {
						degree.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Degree</font>"));
					} else if (college1.isEmpty()) {
						college.setError(Html
								.fromHtml("<font color='red'>Please Enter Your College Name</font>"));
					} else if (university1.isEmpty()) {
						university.setError(Html
								.fromHtml("<font color='red'>Please Enter Your University</font>"));
					} else if (result1.isEmpty()) {
						result.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Result</font>"));
					} else if (yr.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Passing Year");
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
					} else {
						if (cgpa.isChecked()) {
							if (Double.parseDouble(result.getText().toString()) > 11) {
								SpannableString spannableString = new SpannableString(
										"Please Enter Valid CGPA");
								spannableString
										.setSpan(
												new ForegroundColorSpan(
														getResources()
																.getColor(
																		android.R.color.holo_red_light)),
												0, spannableString.length(), 0);
								Toast t = Toast.makeText(
										getApplicationContext(),
										spannableString, Toast.LENGTH_SHORT);
								t.setGravity(Gravity.CENTER, 0, 0);
								t.show();
							} else {
								i = db.addEducationDetail(new EducationPojo(
										degree.getText().toString(), college
												.getText().toString(),
										university.getText().toString(), result
												.getText().toString(), per,
										year.getText().toString(), id, m , y));
								Toast.makeText(getApplicationContext(),
										"Education Detail Saved",
										Toast.LENGTH_SHORT).show();
								db.close();
								finish();
							}
						} else if (percentage.isChecked()) {
							if (Double.parseDouble(result.getText().toString()) > 101
									|| Double.parseDouble(result.getText()
											.toString()) < 30) {
								SpannableString spannableString = new SpannableString(
										"Please Enter Valid Percentage");
								spannableString
										.setSpan(
												new ForegroundColorSpan(
														getResources()
																.getColor(
																		android.R.color.holo_red_light)),
												0, spannableString.length(), 0);
								Toast t = Toast.makeText(
										getApplicationContext(),
										spannableString, Toast.LENGTH_SHORT);
								t.setGravity(Gravity.CENTER, 0, 0);
								t.show();
							} else {
								i = db.addEducationDetail(new EducationPojo(
										degree.getText().toString(), college
												.getText().toString(),
										university.getText().toString(), result
												.getText().toString(), per,
										year.getText().toString(), id, m ,y));
								Toast.makeText(getApplicationContext(),
										"Education Detail Saved",
										Toast.LENGTH_SHORT).show();
								db.close();
								finish();
							}
						}
					}

					if (i >= 1) {
						save.setText("Update");
					}
				} else {
					if (degree1.isEmpty()) {
						degree.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Degree</font>"));
					} else if (college1.isEmpty()) {
						college.setError(Html
								.fromHtml("<font color='red'>Please Enter Your College Name</font>"));
					} else if (university1.isEmpty()) {
						university.setError(Html
								.fromHtml("<font color='red'>Please Enter Your University</font>"));
					} else if (result1.isEmpty()) {
						result.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Result</font>"));
					} else if (yr.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Passing Year");
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
					} else {
						if (cgpa.isChecked()) {
							if (Double.parseDouble(result.getText().toString()) > 11) {
								SpannableString spannableString = new SpannableString(
										"Please Enter Valid CGPA");
								spannableString
										.setSpan(
												new ForegroundColorSpan(
														getResources()
																.getColor(
																		android.R.color.holo_red_light)),
												0, spannableString.length(), 0);
								Toast t = Toast.makeText(
										getApplicationContext(),
										spannableString, Toast.LENGTH_SHORT);
								t.setGravity(Gravity.CENTER, 0, 0);
								t.show();
							} else {
								int iu = db
										.updateEducationDetail(new EducationPojo(
												degree.getText().toString(),
												college.getText().toString(),
												university.getText().toString(),
												result.getText().toString(),
												per, year.getText().toString(),
												id,m ,y));
								db.close();
								finish();
								if (iu >= 1) {
									Toast.makeText(getApplicationContext(),
											" Updated", Toast.LENGTH_SHORT)
											.show();
								} else {
									Toast.makeText(getApplicationContext(),
											" Not Updated", Toast.LENGTH_SHORT)
											.show();
								}
							}
						} else if (percentage.isChecked()) {
							if (Double.parseDouble(result.getText().toString()) > 101
									|| Double.parseDouble(result.getText()
											.toString()) < 30) {
								SpannableString spannableString = new SpannableString(
										"Please Enter Valid Percentage");
								spannableString
										.setSpan(
												new ForegroundColorSpan(
														getResources()
																.getColor(
																		android.R.color.holo_red_light)),
												0, spannableString.length(), 0);
								Toast t = Toast.makeText(
										getApplicationContext(),
										spannableString, Toast.LENGTH_SHORT);
								t.setGravity(Gravity.CENTER, 0, 0);
								t.show();
							} else {
								int iu = db
										.updateEducationDetail(new EducationPojo(
												degree.getText().toString(),
												college.getText().toString(),
												university.getText().toString(),
												result.getText().toString(),
												per, year.getText().toString(),
												id,m , y));
								db.close();
								finish();
								if (iu >= 1) {
									Toast.makeText(getApplicationContext(),
											" Updated", Toast.LENGTH_SHORT)
											.show();
								} else {
									Toast.makeText(getApplicationContext(),
											" Not Updated", Toast.LENGTH_SHORT)
											.show();
								}
							}
						}
					}

				}
			}
		});

	}

	protected void initialize() {
		save = (Button) findViewById(R.id.btnEducationSave);
		degree = (EditText) findViewById(R.id.edtEducationdegree);
		college = (EditText) findViewById(R.id.edtEducationCollege);
		university = (EditText) findViewById(R.id.edtEducationUniversity);
		year = (EditText) findViewById(R.id.edtEducationYear);
		result = (EditText) findViewById(R.id.edtEducationResult);
		iv = (ImageView) findViewById(R.id.imgCalendarEducation);
		rgPercentage = (RadioGroup) findViewById(R.id.radioGroupPercentage);
		percentage = (RadioButton) findViewById(R.id.radioPercentage);
		cgpa = (RadioButton) findViewById(R.id.radioCGPA);
	}

	protected String monthGet(int month) {
		String temp = null;
		if (month == 1) {
			temp = "JAN";
		} else if (month == 2) {
			temp = "FEB";
		} else if (month == 3) {
			temp = "MAR";
		} else if (month == 4) {
			temp = "APR";
		} else if (month == 5) {
			temp = "MAY";
		} else if (month == 6) {
			temp = "JUN";
		} else if (month == 7) {
			temp = "JUL";
		} else if (month == 8) {
			temp = "AUG";
		} else if (month == 9) {
			temp = "SEP";
		} else if (month == 10) {
			temp = "OCT";
		} else if (month == 11) {
			temp = "NOV";
		} else if (month == 12) {
			temp = "DEC";
		}

		return temp;

	}
}
