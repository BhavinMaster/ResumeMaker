package rb.resumecareer.resume;

import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.CareerPojo;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class CareerObjective extends Activity {

	Button save;
	ImageButton tips, sumTips;
	int id;
	EditText objective, summary;
	final DatabaseHandler db = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_career_objective);

		initialize();
		Intent in = getIntent();
		id = in.getIntExtra("id", 0);
		int idclick = in.getIntExtra("idclick", 0);
		if (idclick != 0) {
			List<CareerPojo> retrivedata = db.getAllCareer(idclick);

			for (CareerPojo p : retrivedata) {

				objective.setText(p.getObjective().toString());
				summary.setText(p.getSummary().toString());

			}
			if (!objective.getText().toString().isEmpty()
					|| !summary.getText().toString().isEmpty()) {
				save.setText("Update");
			}
			id = idclick;
		}

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ob = objective.getText().toString();
				if (save.getText().toString().equalsIgnoreCase("save")) {
					if(ob.isEmpty())
					{
						objective.setError(Html
								.fromHtml("<font color='red'>Please Enter Objective</font>"));
					}
					else
					{
					int val = db
							.addCareerDetail(new CareerPojo(id, objective
									.getText().toString(), summary.getText()
									.toString()));
					Toast.makeText(getApplicationContext(),
							"Career Detail Saved", Toast.LENGTH_SHORT).show();
					if (val >= 1) {
						save.setText("Update");
					}
					}
				} else {
					if(ob.isEmpty())
					{
						objective.setError(Html
								.fromHtml("<font color='red'>Please Enter Objective</font>"));
					}
					else
					{
					int i = db
							.updateCareerDetail(new CareerPojo(id, objective
									.getText().toString(), summary.getText()
									.toString()));
					if (i >= 1) {
						Toast.makeText(getApplicationContext(), " Updated",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), " Not Updated",
								Toast.LENGTH_SHORT).show();
					}
					}
				}
				db.close();
			}

		});

		tips.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ListView careertips;
				final Dialog dialog = new Dialog(CareerObjective.this);
				dialog.setContentView(R.layout.activity_career_tips);
				dialog.setTitle("Select Objective");
				dialog.show();

				careertips = (ListView) dialog.findViewById(R.id.listtipCareer);

				String[] careerValue = {
						"To succeed in an environment of growth and excellence and earn a job which provides me job Satisfaction and self development and help me achieve personal as well as organization goals.",
						"To seek challenging assignment and responsibility, with an opportunity for growth and career advancement as successful achievements.",
						"To excel in my field through hard work, research, skills and perseverance. To serve my parents, and my country with the best of my abilities.",
						"To live honest and hard life to work in a highly challenging competitive environment for the enhancement of my creative abilities and optimum profitability of the organization.",
						"Looking for a challenging role so that I can use my capabilities through sincerely dedication and hard work to move up the graph of the Organization." };

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						CareerObjective.this,
						android.R.layout.simple_list_item_1, careerValue);

				careertips.setAdapter(adapter);

				careertips.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub

						String str = (String) parent
								.getItemAtPosition(position);

						objective.setText(str);
						dialog.dismiss();

					}
				});

			}
		});

		sumTips.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ListView summarytips;
				final Dialog dialog = new Dialog(CareerObjective.this);
				dialog.setContentView(R.layout.activity_career_tips);
				dialog.setTitle("Select Objective");
				dialog.show();

				summarytips = (ListView) dialog
						.findViewById(R.id.listtipCareer);

				String[] careerValue = {
						"Highly motivated Technical Support professional. Strong verbal, listening and written skills. Comfortable in interacting"
+"with all levels of the organization and public. Able to negotiate and problem solve quickly, accurately, and efficiently."
+"Adept at multitasking to achieve individual and team goals. Diverse background includes sales, customer service"
+"and supervision. Committed to quality and excellence.  ",
						"Self-motivated individual with over 10 years experience in technical and retail environments. Adept at"
								+ "prioritizing and completing tasks to meet customers’ needs. Safety-minded and a good communicator with"
								+ "strong computer skills. " };
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						CareerObjective.this,
						android.R.layout.simple_list_item_1, careerValue);

				summarytips.setAdapter(adapter);

				summarytips.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub

						String str = (String) parent
								.getItemAtPosition(position);

						summary.setText(str);
						dialog.dismiss();

					}
				});

			}
		});
	}

	protected void initialize() {
		save = (Button) findViewById(R.id.btnCareerSave);

		objective = (EditText) findViewById(R.id.edtCareerObjective);
		summary = (EditText) findViewById(R.id.edtCareerSummary);

		tips = (ImageButton) findViewById(R.id.imgtips);
		sumTips = (ImageButton) findViewById(R.id.imgtipssum);
	}
}
