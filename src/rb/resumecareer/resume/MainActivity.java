package rb.resumecareer.resume;

import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements AnimationListener {

	Button btn;
	TextView txt;
	EditText edt;
	StringBuffer str = null;
	final DatabaseHandler db = new DatabaseHandler(this);
	Animation animFadein, a1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar();
		bar.hide();
		intialize();
		animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.bounce);

		a1 = AnimationUtils
				.loadAnimation(getApplicationContext(), R.anim.shake);
		a1.setAnimationListener(this);
		animFadein.setAnimationListener(this);

		txt.setVisibility(View.VISIBLE);
		// edt.setVisibility(View.VISIBLE);
		btn.setVisibility(View.VISIBLE);

		txt.startAnimation(animFadein);
		// edt.startAnimation(animFadein);
		btn.startAnimation(animFadein);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ed = edt.getText().toString();
				if (edt.getText().toString().isEmpty()) {
					edt.setError(Html
							.fromHtml("<font color='red'>Enter Your Name To Create Profile</font>"));
					edt.startAnimation(a1);
				} else {

					final List<String> data = db.getAllData();
					if (data.contains(ed)) {
						edt.setError(Html
								.fromHtml("<font color='red'>Try Different Name To Create Profile</font>"));
						edt.startAnimation(a1);
					} else {
						Intent intent = new Intent(getApplicationContext(),
								PhotoUpload.class);
						intent.putExtra("profilename", edt.getText().toString());
						startActivity(intent);
						finish();
					}
				}
				db.close();
			}
		});
	}

	public void intialize() {
		btn = (Button) findViewById(R.id.btnCreateProfile);
		edt = (EditText) findViewById(R.id.edtProfileName);
		txt = (TextView) findViewById(R.id.txtWelcomeResumeText);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}
}
