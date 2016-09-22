package rb.resumecareer.resume;

import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.SkillPojo;
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

public class SkillDetail extends Activity {

	Button save;
	EditText skill;
	final DatabaseHandler db = new DatabaseHandler(this);
	int id, i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skill_detail);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#307994")));
		initialize();
		Intent in = getIntent();
		id = in.getIntExtra("id", 0);
		final int idclick = in.getIntExtra("idclick", 0);
				if (idclick != 0) {

			List<SkillPojo> retrivedata = db.getAllSkillDetail(idclick);

			for (SkillPojo p : retrivedata) {
				skill.setText(p.getSkill().toString());
			}
			if (!skill.getText().toString().isEmpty()) {
				save.setText("Update");
			}
			id = idclick;
		}

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String skill1 = skill.getText().toString();
				if (save.getText().toString().equalsIgnoreCase("save")) {
					if (skill1.isEmpty()) {
						skill.setError(Html
								.fromHtml("<font color='red'>Please Enter Skill</font>"));
					} else {
						i = db.addSkill(new SkillPojo(skill.getText()
								.toString(), id));
						Toast.makeText(getApplicationContext(), "Skill Saved",
								Toast.LENGTH_SHORT).show();
						db.close();
						finish();
					}
					
				}
				if (i >= 1) {
					save.setText("Update");
				} else {
					if (skill1.isEmpty()) {
						skill.setError(Html
								.fromHtml("<font color='red'>Please Enter Skill</font>"));
					} else {
						int iu = db.updateSkill(new SkillPojo(skill.getText()
								.toString(), id));
						db.close();
						finish();
						if (iu == 1) {
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
		save = (Button) findViewById(R.id.btnSkillSave);
		skill = (EditText) findViewById(R.id.edtSkill);
	}
}
