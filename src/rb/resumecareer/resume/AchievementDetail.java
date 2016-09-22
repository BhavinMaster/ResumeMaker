package rb.resumecareer.resume;

import java.util.Calendar;
import java.util.List;
import rb.resumecareer.resume.R;
import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.AchievementDetailPojo;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AchievementDetail extends Activity {

	Button save;
	EditText nameOfAchieve, yearOfAchieve, whatAchieve;
	ImageView date1;
	String temp1, temp11;
	private int mYear, mMonth, mDay;
	final DatabaseHandler db = new DatabaseHandler(this);
	int i, id,m,y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_achievement_detail);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#307994")));
	
		initialize();
		yearOfAchieve.setOnClickListener(new OnClickListener() {

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

				// Launch Date Picker Dialog

				DatePickerDialog dpd = new DatePickerDialog(
						AchievementDetail.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// Display Selected date in textbox
								m =monthOfYear;
								y = year;
								temp11 = monthGet(monthOfYear + 1);
								AchievementDetail.this.yearOfAchieve
										.setText(temp11 + " " + year);
							}
						}, mYear, mMonth, mDay);
				((ViewGroup) dpd.getDatePicker()).findViewById(
						Resources.getSystem().getIdentifier("day", "id",
								"android")).setVisibility(View.GONE);
				dpd.show();

			}

		});
		Intent in = getIntent();
		id = in.getIntExtra("id", 0);
		final int idclick = in.getIntExtra("idclick", 0);
		if (idclick != 0) {

			List<AchievementDetailPojo> retrivedata = db
					.getAllAchievementDetail(idclick);

			for (AchievementDetailPojo p : retrivedata) {

				nameOfAchieve.setText(p.getName_of_achievement().toString());
				yearOfAchieve.setText(p.getYear_of_achievement().toString());
				whatAchieve.setText(p.getWhat_you_achieved().toString());
			}
			if (!nameOfAchieve.getText().toString().isEmpty()
					|| !yearOfAchieve.getText().toString().isEmpty()
					|| !whatAchieve.getText().toString().isEmpty()) {
				save.setText("Update");
			}
			id = idclick;
		}
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String nameOfAchieve1 = nameOfAchieve.getText().toString();
				String yearOfAchieve1 = yearOfAchieve.getText().toString();
				String whatAchieve1 = whatAchieve.getText().toString();

				if (save.getText().toString().equalsIgnoreCase("save")) {
					if (nameOfAchieve1.isEmpty()) {
						nameOfAchieve.setError(Html
								.fromHtml("<font color='red'>Please Enter Name Of Achievement</font>"));
					} else if (yearOfAchieve1.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Year of Achievement");
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
					} else if (whatAchieve1.isEmpty()) {
						whatAchieve.setError(Html
								.fromHtml("<font color='red'>Please Enter What you Achieved</font>"));
					} else {
						i = db.addAchievementDetail(new AchievementDetailPojo(
								nameOfAchieve1, yearOfAchieve1, whatAchieve1,
								id,m ,y));
						Toast.makeText(getApplicationContext(),
								"Achievement Saved", Toast.LENGTH_SHORT).show();
						db.close();
						finish();
					}

					if (i >= 1) {
						save.setText("Update");
					}
				} else {
					if (nameOfAchieve1.isEmpty()) {
						nameOfAchieve.setError(Html
								.fromHtml("<font color='red'>Please Enter Name Of Achievement</font>"));
					} else if (yearOfAchieve1.isEmpty()) {
						SpannableString spannableString = new SpannableString(
								"Please Select Year of Achievement");
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
					} else if (whatAchieve1.isEmpty()) {
						whatAchieve.setError(Html
								.fromHtml("<font color='red'>Please Enter What you Achieved</font>"));
					} else {
						int iu = db
								.updateAchievementDetail(new AchievementDetailPojo(
										nameOfAchieve1, yearOfAchieve1,
										whatAchieve1, id,m,y));
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
		save = (Button) findViewById(R.id.btnAchievementSave);
		nameOfAchieve = (EditText) findViewById(R.id.edtAchievementNameOfAchievement);
		yearOfAchieve = (EditText) findViewById(R.id.edtAchievementYearOfAchievement);
		whatAchieve = (EditText) findViewById(R.id.edtAchievementwhatYouHaveAchieved);
		date1 = (ImageView) findViewById(R.id.imgCalendarachievement);
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
