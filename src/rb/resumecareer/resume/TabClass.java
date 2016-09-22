package rb.resumecareer.resume;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class TabClass extends TabActivity implements OnTabChangeListener {

	TabHost tabHost;
	View personal, career, education, skill, project, experience, achievament,
			reference, signature, photo, view, email;
	ImageView imgpersonal1, imgcareer1, imgeducation1, imgskill1, imgproject1,
			imgexperience1, imgachievement1, imgreference1, imgsignature1,
			imgPhoto, imgview1, imgemail1;
	TextView txtpersonal1, txtcareer1, txteducation1, txtskill1, txtproject1,
			txtexperience1, txtachievement1, txtreference1, txtsignature1,
			txtPhoto1, txtview1, txtemail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_tab);
		ActionBar bar = getActionBar();
		bar.hide();
		tabHost = getTabHost();
		TabHost.TabSpec spec;
		Resources res = getResources();

		personal = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
		career = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
		education = LayoutInflater.from(this)
				.inflate(R.layout.tab_layout, null);
		skill = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
		project = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);

		experience = LayoutInflater.from(this).inflate(R.layout.tab_layout,
				null);
		achievament = LayoutInflater.from(this).inflate(R.layout.tab_layout,
				null);
		reference = LayoutInflater.from(this)
				.inflate(R.layout.tab_layout, null);
		signature = LayoutInflater.from(this)
				.inflate(R.layout.tab_layout, null);
		photo = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);
		view = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);

		email = LayoutInflater.from(this).inflate(R.layout.tab_layout, null);

		imgpersonal1 = (ImageView) personal.findViewById(R.id.tabImage);
		txtpersonal1 = (TextView) personal.findViewById(R.id.tabText);

		imgcareer1 = (ImageView) career.findViewById(R.id.tabImage);
		txtcareer1 = (TextView) career.findViewById(R.id.tabText);

		imgeducation1 = (ImageView) education.findViewById(R.id.tabImage);
		txteducation1 = (TextView) education.findViewById(R.id.tabText);

		imgskill1 = (ImageView) skill.findViewById(R.id.tabImage);
		txtskill1 = (TextView) skill.findViewById(R.id.tabText);

		imgproject1 = (ImageView) project.findViewById(R.id.tabImage);
		txtproject1 = (TextView) project.findViewById(R.id.tabText);

		imgexperience1 = (ImageView) experience.findViewById(R.id.tabImage);
		txtexperience1 = (TextView) experience.findViewById(R.id.tabText);

		imgachievement1 = (ImageView) achievament.findViewById(R.id.tabImage);
		txtachievement1 = (TextView) achievament.findViewById(R.id.tabText);

		imgreference1 = (ImageView) reference.findViewById(R.id.tabImage);
		txtreference1 = (TextView) reference.findViewById(R.id.tabText);

		imgsignature1 = (ImageView) signature.findViewById(R.id.tabImage);
		txtsignature1 = (TextView) signature.findViewById(R.id.tabText);

		imgPhoto = (ImageView) photo.findViewById(R.id.tabImage);
		txtPhoto1 = (TextView) photo.findViewById(R.id.tabText);

		imgview1 = (ImageView) view.findViewById(R.id.tabImage);
		txtview1 = (TextView) view.findViewById(R.id.tabText);

		imgemail1 = (ImageView) email.findViewById(R.id.tabImage);
		txtemail = (TextView) email.findViewById(R.id.tabText);

		tabHost.setOnTabChangedListener(this);

		Intent intent;
		Intent in = getIntent();
		int id = in.getIntExtra("id", 0);
		int idclick = in.getIntExtra("idclick", 0);
		// =======================Personal Tab==========================

		intent = new Intent().setClass(this, PersonalDetail.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Personal").setIndicator(personal)
				.setContent(intent);
		txtpersonal1.setText("Personal");
		imgpersonal1.setImageResource(R.drawable.personal);
		tabHost.addTab(spec);

		// =======================Career Tab==========================

		intent = new Intent().setClass(this, CareerObjective.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Career").setIndicator(career)
				.setContent(intent);

		txtcareer1.setText("Career");
		imgcareer1.setImageResource(R.drawable.career);
		tabHost.addTab(spec);

		// =======================Education Tab==========================

		intent = new Intent().setClass(this, Education.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Education").setIndicator(education)
				.setContent(intent);

		imgeducation1.setImageResource(R.drawable.gradu);
		txteducation1.setText("Education");
		tabHost.addTab(spec);

		// =======================Skill Tab==========================

		intent = new Intent().setClass(this, Skill.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Skill").setIndicator(skill)
				.setContent(intent);

		imgskill1.setImageResource(R.drawable.skill);
		txtskill1.setText("Skill");
		tabHost.addTab(spec);

		// =======================Project Tab==========================

		intent = new Intent().setClass(this, Project.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Project").setIndicator(project)
				.setContent(intent);

		txtproject1.setText("Project");
		imgproject1.setImageResource(R.drawable.project);
		tabHost.addTab(spec);

		// =======================Experience Tab==========================
		intent = new Intent().setClass(this, Experience.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Experience").setIndicator(experience)
				.setContent(intent);

		txtexperience1.setText("Experience");
		imgexperience1.setImageResource(R.drawable.experience);
		tabHost.addTab(spec);

		// =======================Achievement Tab==========================
		intent = new Intent().setClass(this, Achievement.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Achievement").setIndicator(achievament)
				.setContent(intent);

		imgachievement1.setImageResource(R.drawable.achievement);
		txtachievement1.setText(" Achievement  ");
		tabHost.addTab(spec);

		// =======================Reference Tab==========================
		intent = new Intent().setClass(this, Reference.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Reference").setIndicator(reference)
				.setContent(intent);

		txtreference1.setText("Reference");
		imgreference1.setImageResource(R.drawable.reference);
		tabHost.addTab(spec);

		// =======================Signature Tab==========================
		intent = new Intent().setClass(this, Signature.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Signature").setIndicator(signature)
				.setContent(intent);
		imgsignature1.setImageResource(R.drawable.signature);
		txtsignature1.setText("Signature");
		tabHost.addTab(spec);

		// =======================Photo Tab==========================
		int i1 = 1;
		intent = new Intent().setClass(TabClass.this, PhotoUpload.class);
		intent.putExtra("id", id);
		intent.putExtra("photovalue", i1);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Photo").setIndicator(photo)
				.setContent(intent);
		txtPhoto1.setText("Photo");
		imgPhoto.setImageResource(R.drawable.cam);
		tabHost.addTab(spec);

		// =======================View Tab==========================
		intent = new Intent().setClass(this, ViewResume.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("View").setIndicator(view).setContent(intent);

		txtview1.setText("View");
		imgview1.setImageResource(R.drawable.view);
		tabHost.addTab(spec);

		// =======================Email Tab==========================
		intent = new Intent().setClass(this, EmailDocument.class);
		intent.putExtra("id", id);
		intent.putExtra("idclick", idclick);
		spec = tabHost.newTabSpec("Email").setIndicator(email).setContent(intent);

		txtemail.setText("Email");
		imgemail1.setImageResource(R.drawable.email);
		tabHost.addTab(spec);

		LayoutParams params = tabHost.getTabWidget().getChildAt(0)
				.getLayoutParams();
		params.width = 80;
		tabHost.getTabWidget().getChildAt(0).setLayoutParams(params);

	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub

		txtpersonal1.setTextColor(Color.BLACK);
		txtpersonal1.setTypeface(null, Typeface.BOLD);
		imgpersonal1.setImageResource(R.drawable.personal);

		txtcareer1.setTextColor(Color.BLACK);
		txtcareer1.setTypeface(null, Typeface.BOLD);
		imgcareer1.setImageResource(R.drawable.career);

		txteducation1.setTextColor(Color.BLACK);
		txteducation1.setTypeface(null, Typeface.BOLD);
		imgeducation1.setImageResource(R.drawable.gradu);

		txtskill1.setTextColor(Color.BLACK);
		txtskill1.setTypeface(null, Typeface.BOLD);
		imgskill1.setImageResource(R.drawable.skill);

		txtproject1.setTextColor(Color.BLACK);
		txtproject1.setTypeface(null, Typeface.BOLD);
		imgproject1.setImageResource(R.drawable.project);

		txtachievement1.setTextColor(Color.BLACK);
		txtachievement1.setTypeface(null, Typeface.BOLD);
		imgachievement1.setImageResource(R.drawable.achievement);

		txtexperience1.setTextColor(Color.BLACK);
		txtexperience1.setTypeface(null, Typeface.BOLD);
		imgexperience1.setImageResource(R.drawable.experience);

		txtreference1.setTextColor(Color.BLACK);
		txtreference1.setTypeface(null, Typeface.BOLD);
		imgreference1.setImageResource(R.drawable.reference);

		txtsignature1.setTextColor(Color.BLACK);
		txtsignature1.setTypeface(null, Typeface.BOLD);
		imgsignature1.setImageResource(R.drawable.signature);

		txtPhoto1.setTextColor(Color.BLACK);
		txtPhoto1.setTypeface(null, Typeface.BOLD);
		imgPhoto.setImageResource(R.drawable.cam);

		txtview1.setTextColor(Color.BLACK);
		txtview1.setTypeface(null, Typeface.BOLD);
		imgview1.setImageResource(R.drawable.view);
		
		txtemail.setTextColor(Color.BLACK);
		txtemail.setTypeface(null,Typeface.BOLD);
		imgemail1.setImageResource(R.drawable.email);

		onTabSelectionChange(tabId);
	}

	private void onTabSelectionChange(String tabId) {
		if (tabId.equals("Personal")) {
			txtpersonal1.setTextColor(Color.rgb(255, 255, 255));
			txtpersonal1.setTypeface(null, Typeface.BOLD);
			imgpersonal1.setImageResource(R.drawable.neg_personal);

		} else if (tabId.equals("Career")) {
			txtcareer1.setTextColor(Color.rgb(255, 255, 255));
			txtcareer1.setTypeface(null, Typeface.BOLD);
			imgcareer1.setImageResource(R.drawable.neg_career);

		} else if (tabId.equals("Education")) {
			txteducation1.setTextColor(Color.rgb(255, 255, 255));
			txteducation1.setTypeface(null, Typeface.BOLD);
			imgeducation1.setImageResource(R.drawable.neg_graduate);

		} else if (tabId.equals("Skill")) {
			txtskill1.setTextColor(Color.rgb(255, 255, 255));
			txtskill1.setTypeface(null, Typeface.BOLD);
			imgskill1.setImageResource(R.drawable.neg_skill);

		} else if (tabId.equals("Project")) {
			txtproject1.setTextColor(Color.rgb(255, 255, 255));
			txtproject1.setTypeface(null, Typeface.BOLD);
			imgproject1.setImageResource(R.drawable.neg_project);

		} else if (tabId.equals("Experience")) {
			txtexperience1.setTextColor(Color.rgb(255, 255, 255));
			txtexperience1.setTypeface(null, Typeface.BOLD);
			imgexperience1.setImageResource(R.drawable.neg_exp);

		} else if (tabId.equals("Achievement")) {
			txtachievement1.setTextColor(Color.rgb(255, 255, 255));
			txtachievement1.setTypeface(null, Typeface.BOLD);
			imgachievement1.setImageResource(R.drawable.neg_achievement);

		} else if (tabId.equals("Reference")) {
			txtreference1.setTextColor(Color.rgb(255, 255, 255));
			txtreference1.setTypeface(null, Typeface.BOLD);
			imgreference1.setImageResource(R.drawable.neg_reference);

		} else if (tabId.equals("Photo")) {
			txtPhoto1.setTextColor(Color.rgb(255, 255, 255));
			txtPhoto1.setTypeface(null, Typeface.BOLD);
			imgPhoto.setImageResource(R.drawable.neg_cam);

		} else if (tabId.equals("Signature")) {
			txtsignature1.setTextColor(Color.rgb(255, 255, 255));
			txtsignature1.setTypeface(null, Typeface.BOLD);
			imgsignature1.setImageResource(R.drawable.neg_signature);

		} else if (tabId.equals("View")) {
			txtview1.setTextColor(Color.rgb(255, 255, 255));
			txtview1.setTypeface(null, Typeface.BOLD);
			imgview1.setImageResource(R.drawable.neg_view);
		}else if (tabId.equals("Email")) {
			txtemail.setTextColor(Color.rgb(255, 255, 255));
			txtemail.setTypeface(null, Typeface.BOLD);
			imgemail1.setImageResource(R.drawable.neg_email);
		}

	}
}
