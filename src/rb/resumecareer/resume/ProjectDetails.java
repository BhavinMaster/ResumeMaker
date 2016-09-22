package rb.resumecareer.resume;

import java.util.Calendar;
import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.ProjectPojo;
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
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ProjectDetails extends Activity {

	EditText title, duration, role, teamSize, expertise;
	Button save;
	ImageView date1;
	String temp1, temp11;
	private int mYear, mMonth, mDay;
	int i, id, y, m, d;
	final DatabaseHandler db = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_detail);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#307994")));
		initialize();
	
		duration.setOnClickListener(new OnClickListener() {
			
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
						ProjectDetails.this);
				alertDialog.setTitle("Select Action");
				alertDialog.setPositiveButton("Ongoing",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								m = 13;
								y = 9999;
							duration.setText("Ongoing");
							}
						});
				
				alertDialog.setNegativeButton("Finish",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
				// Launch Date Picker Dialog
				final DatePickerDialog dpd1 = new DatePickerDialog(
						ProjectDetails.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// Display Selected date in textbox
								y = year;
								m = monthOfYear + 1;
								d = dayOfMonth;
								temp1 = monthGet(monthOfYear + 1)+" " + year;

							}
						}, mYear, mMonth, mDay);
				((ViewGroup) dpd1.getDatePicker()).findViewById(
						Resources.getSystem().getIdentifier("day", "id",
								"android")).setVisibility(View.GONE);

				DatePickerDialog dpd = new DatePickerDialog(
						ProjectDetails.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// Display Selected date in textbox
								dpd1.cancel();
								temp11 = monthGet(monthOfYear + 1);
								if(y>year){
									Toast.makeText(getApplicationContext(),"Invalid year",
											  Toast.LENGTH_SHORT).show(); 
									duration.setText("");
								}
								else if(y==year)
								{
									if(m>monthOfYear+1){
										Toast.makeText(getApplicationContext(),"Invalid year",
												  Toast.LENGTH_SHORT).show();
										duration.setText("");
									}
								else
								{
									ProjectDetails.this.duration.setText(temp1
											+ " - " + temp11 + " " + year);
								}
								}
								else
								{
									ProjectDetails.this.duration.setText(temp1
											+ " - " + temp11 + " " + year);
								}
							}
						}, mYear, mMonth, mDay);
				((ViewGroup) dpd.getDatePicker()).findViewById(
						Resources.getSystem().getIdentifier("day", "id",
								"android")).setVisibility(View.GONE);
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

			List<ProjectPojo> retrivedata = db.getAllProjetDetail(idclick);

			for (ProjectPojo p : retrivedata) {

				title.setText(p.getTitle().toString());
				duration.setText(p.getDuration().toString());
				role.setText(p.getRole().toString());
				teamSize.setText(p.getTeamSize().toString());
				expertise.setText(p.getExpertise().toString());
			}
			if (!title.getText().toString().isEmpty()
					|| !duration.getText().toString().isEmpty()
					|| !role.getText().toString().isEmpty()
					|| !teamSize.getText().toString().isEmpty()
					|| !expertise.getText().toString().isEmpty()) {
				save.setText("Update");
			}
			id = idclick;
		}

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String title1 = title.getText().toString();
				String duration1 = duration.getText().toString();
				String role1 = role.getText().toString();
				String teamSize1 = teamSize.getText().toString();
				String expertise1 = expertise.getText().toString();

				if (save.getText().toString().equalsIgnoreCase("save")) {
					if (title1.isEmpty()) {
						title.setError(Html
								.fromHtml("<font color='red'>Please Enter Title Of The Project</font>"));
					} else if (duration1.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Your Project Duration");
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
					} else if (role1.isEmpty()) {
						role.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Key Role In Your Project Development</font>"));
					} else if (teamSize1.isEmpty()) {
						teamSize.setError(Html
								.fromHtml("<font color='red'>Please Enter No. of Member Involve In Your Project Development</font>"));
					} else if (expertise1.isEmpty()) {
						expertise.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Expertise You Have In Your Project</font>"));
					} else {
						i = db.addProjectDetail(new ProjectPojo(title.getText()
								.toString(), duration.getText().toString(),
								role.getText().toString(), teamSize.getText()
										.toString(), expertise.getText()
										.toString(), id,m,y));
						Toast.makeText(getApplicationContext(),
								"Project Detail Saved", Toast.LENGTH_SHORT)
								.show();
						db.close();
						finish();

					}
					
					if (i >= 1) {
						save.setText("Update");
					}
				} else {
					if (title1.isEmpty()) {
						title.setError(Html
								.fromHtml("<font color='red'>Please Enter Title Of The Project</font>"));
					} else if (duration1.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Your Project Duration");
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
					} else if (role1.isEmpty()) {
						role.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Key Role In Your Project Development</font>"));
					} else if (teamSize1.isEmpty()) {
						teamSize.setError(Html
								.fromHtml("<font color='red'>Please Enter No. of Member Involve In Your Project Development</font>"));
					} else if (expertise1.isEmpty()) {
						expertise.setError(Html
								.fromHtml("<font color='red'>Please Enter Your Expertise You Have In Your Project</font>"));
					} else {
						int iu = db.updateProjectDetail(new ProjectPojo(title
								.getText().toString(), duration.getText()
								.toString(), role.getText().toString(),
								teamSize.getText().toString(), expertise
										.getText().toString(), id,m,y));
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
		title = (EditText) findViewById(R.id.edtProjectTitle);
		duration = (EditText) findViewById(R.id.edtProjectDuration);
		role = (EditText) findViewById(R.id.edtProjectRole);
		teamSize = (EditText) findViewById(R.id.edtProjectTeamSize);
		expertise = (EditText) findViewById(R.id.edtProjectExpertise);
		save = (Button) findViewById(R.id.btnProjectSave);
		date1 = (ImageView) findViewById(R.id.imgCalendarproject);
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
