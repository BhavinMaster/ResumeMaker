package rb.resumecareer.resume;

import java.util.Calendar;
import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.ExperiencePojo;
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
import android.widget.Toast;

public class ExperienceDetail extends Activity {

	EditText company, position, period, location;
	Button save;
	ImageView date1;
	String temp1, temp11;
	private int mYear, mMonth, mDay;
	final DatabaseHandler db = new DatabaseHandler(this);
	int id, y, m, d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_experinece_detail);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#307994")));
		initialize();
	
		period.setOnClickListener(new OnClickListener() {

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
		date1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						ExperienceDetail.this);
				alertDialog.setTitle("Select Action");
				alertDialog.setPositiveButton("At Present",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								period.setText("Presently Working");
								m = 13;
								y = 9999;
							}
						});

				alertDialog.setNegativeButton("Finish",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								// Launch Date Picker Dialog
								final DatePickerDialog dpd1 = new DatePickerDialog(
										ExperienceDetail.this,
										new DatePickerDialog.OnDateSetListener() {

											@Override
											public void onDateSet(
													DatePicker view, int year,
													int monthOfYear,
													int dayOfMonth) {
												// Display Selected date in
												// textbox

												y = year;
												m = monthOfYear + 1;
												d = dayOfMonth;
												temp1 = monthGet(monthOfYear + 1)
														+ " " + year;

											}
										}, mYear, mMonth, mDay);
								((ViewGroup) dpd1.getDatePicker())
										.findViewById(
												Resources
														.getSystem()
														.getIdentifier("day",
																"id", "android"))
										.setVisibility(View.GONE);

								DatePickerDialog dpd = new DatePickerDialog(
										ExperienceDetail.this,
										new DatePickerDialog.OnDateSetListener() {

											@Override
											public void onDateSet(
													DatePicker view, int year,
													int monthOfYear,
													int dayOfMonth) {
												// Display Selected date in
												// textbox
												dpd1.cancel();
												if (y > year) {
													Toast.makeText(
															getApplicationContext(),
															"Invalid year",
															Toast.LENGTH_SHORT)
															.show();
													period.setText("");
												} else if (y == year) {
													if (m > monthOfYear + 1) {
														Toast.makeText(
																getApplicationContext(),
																"Invalid year",
																Toast.LENGTH_SHORT)
																.show();
														period.setText("");
													} else {
														temp11 = monthGet(monthOfYear + 1);
														ExperienceDetail.this.period
																.setText(temp1
																		+ " - "
																		+ temp11
																		+ " "
																		+ year);
													}
												} else {
													temp11 = monthGet(monthOfYear + 1);
													ExperienceDetail.this.period
															.setText(temp1
																	+ " - "
																	+ temp11
																	+ " "
																	+ year);
												}
											}
										}, mYear, mMonth, mDay);
								((ViewGroup) dpd.getDatePicker()).findViewById(
										Resources.getSystem().getIdentifier(
												"day", "id", "android"))
										.setVisibility(View.GONE);
								dpd1.setTitle("From : ");
								dpd.setTitle("To :");

								dpd.show();
								dpd1.show();
							}
						});
				alertDialog.show();
			}

		});
		Intent in = getIntent();
		id = in.getIntExtra("id", 0);
		final int idclick = in.getIntExtra("idclick", 0);
		if (idclick != 0) {

			List<ExperiencePojo> retrivedata = db
					.getAllExperienceDetail(idclick);

			for (ExperiencePojo p : retrivedata) {

				company.setText(p.getCompany().toString());
				position.setText(p.getPosition().toString());
				period.setText(p.getPeriod().toString());
				location.setText(p.getLocation().toString());
			}
			if (!company.getText().toString().isEmpty()
					|| !position.getText().toString().isEmpty()
					|| !period.getText().toString().isEmpty()
					|| !location.getText().toString().isEmpty()) {
				save.setText("Update");
			}
			id = idclick;
		}
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String company1 = company.getText().toString();
				String position1 = position.getText().toString();
				String period1 = period.getText().toString();
				String location1 = location.getText().toString();
				int i = 0;
				if (save.getText().toString().equalsIgnoreCase("save")) {

					if (company1.isEmpty()) {
						company.setError(Html
								.fromHtml("<font color='red'>Please Enter Company Name</font>"));
					} else if (position1.isEmpty()) {
						position.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Position</font>"));
					} else if (period1.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Your Experience Year");
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
					} else if (location1.isEmpty()) {
						location.setError(Html
								.fromHtml("<font color='red'>Please Enter Location Of Company</font>"));
					} else {
						i = db.addExperienceDetail(new ExperiencePojo(company
								.getText().toString(), position.getText()
								.toString(), period.getText().toString(),
								location.getText().toString(), id,m ,y));
						Toast.makeText(getApplicationContext(),
								"Experience Detail Saved", Toast.LENGTH_SHORT)
								.show();
						db.close();
						finish();
					}

					if (i >= 1) {
						save.setText("Update");
					}
				} else {
					if (company1.isEmpty()) {
						company.setError(Html
								.fromHtml("<font color='red'>Please Enter Company Name</font>"));
					} else if (position1.isEmpty()) {
						position.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Position</font>"));
					} else if (period1.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Your Experience Year");
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
					} else if (location1.isEmpty()) {
						location.setError(Html
								.fromHtml("<font color='red'>Please Enter Location Of Company</font>"));
					} else {
						int iu = db.updateExperienceDetail(new ExperiencePojo(
								company.getText().toString(), position
										.getText().toString(), period.getText()
										.toString(), location.getText()
										.toString(), id,m,y));
						db.close();
						finish();
						if (iu == 1) {
							Toast.makeText(getApplicationContext(),
									"Experience Detail Updated",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getApplicationContext(),
									"Experience Detail Not Updated",
									Toast.LENGTH_SHORT).show();
						}

					}
				}

			}
		});
	}

	protected void initialize() {
		company = (EditText) findViewById(R.id.edtExperienceCompany);
		position = (EditText) findViewById(R.id.edtExperiencePosition);
		period = (EditText) findViewById(R.id.edtExperiencePeriod);
		location = (EditText) findViewById(R.id.edtExperienceLocation);
		save = (Button) findViewById(R.id.btnExperienceSave);
		date1 = (ImageView) findViewById(R.id.imgCalendarexperience);
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
