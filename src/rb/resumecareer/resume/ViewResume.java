package rb.resumecareer.resume;

import harmony.java.awt.Color;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.AchievementDetailPojo;
import rb.resumecareer.resumePojo.CareerPojo;
import rb.resumecareer.resumePojo.EducationPojo;
import rb.resumecareer.resumePojo.ExperiencePojo;
import rb.resumecareer.resumePojo.PersonalDetailPojo;
import rb.resumecareer.resumePojo.ProjectPojo;
import rb.resumecareer.resumePojo.ReferenceDetailPojo;
import rb.resumecareer.resumePojo.SigImage;
import rb.resumecareer.resumePojo.SignaturePojo;
import rb.resumecareer.resumePojo.SkillPojo;
import rb.resumecareer.resumePojo.User;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ViewResume extends Activity {

	ImageButton ipdf, idoc;
	String str = null;

	final DatabaseHandler db = new DatabaseHandler(this);
	String name, address, language, contact, email, txtDate, obj, sum, degree,
			college, university, result, yearofpassing, percentage, skill,
			namepdf, title, duration, role, teamSize, expertise, company,
			position, period, location, name_of_achievement,
			year_of_achievement, what_you_achieved, rerf_name, ref_detail,
			ref_contact, ref_email, place, dt, sinc;

	int idclick, id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		Intent in = getIntent();

		id = in.getIntExtra("id", 0);
		idclick = in.getIntExtra("idclick", 0);
		ipdf = (ImageButton) findViewById(R.id.imfPdf);
		idoc = (ImageButton) findViewById(R.id.imgDoc);

		idoc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub.
				if (id == 0) {
					id = idclick;
				}
				List<EducationPojo> checkep = db.getAllEducationDetailPdf(id);
				List<PersonalDetailPojo> checkper = db.getAllPersonalDetail(id);
				List<CareerPojo> cpr = db.getAllCareer(id);

				if (checkep.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"Enter Education Detail", Toast.LENGTH_LONG).show();
				} else if (checkper.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"Enter Personal Detail", Toast.LENGTH_LONG).show();

				} else if (cpr.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"Enter Career Objective Detail", Toast.LENGTH_LONG)
							.show();

				} else {
					createPDF2();
					openPdf();
				}
			}

			private void createPDF2() {
				// TODO Auto-generated method stub
				Bitmap bmp = null;
				if (id == 0) {
					id = idclick;
				}
				if (id != 0) {
					List<PersonalDetailPojo> retrivedata = db
							.getAllPersonalDetail(id);
					for (PersonalDetailPojo p : retrivedata) {

						name = p.getPersonName().toString();
						address = p.getAddress().toString();
						language = p.getLanguage().toString();
						contact = p.getMobile().toString();
						email = p.getEmail().toString();
						txtDate = p.getBirthdate().toString();
					}
					List<CareerPojo> cp = db.getAllCareer(id);
					for (CareerPojo c : cp) {
						obj = c.getObjective();
						sum = c.getSummary();
					}
					List<User> user = db.getUserpdf(id);

					for (User c : user) {
						namepdf = c.getName();
						byte[] bt = c.getImgProfile();
						if (bt == null) {
							bmp = null;
						} else {
							bmp = BitmapFactory.decodeByteArray(
									c.getImgProfile(), 0,
									c.getImgProfile().length);
						}
					}
				}
				Document doc = new Document(PageSize.A4);

				try {

					String path = Environment.getExternalStorageDirectory()
							.getAbsolutePath() + "/ResumeMaker";

					ArrayList<SigImage> si = db.getAllUserSignature(id);
					Bitmap bmp1 = null;
					for (SigImage si1 : si) {
						byte[] bt = si1.getSign();
						if (bt == null) {
							bmp1 = null;
						} else {
							bmp1 = BitmapFactory.decodeByteArray(si1.getSign(),
									0, si1.getSign().length);
						}
					}
					File dir = new File(path);
					if (!dir.exists())
						dir.mkdirs();

					File file;
					file = new File(dir, namepdf + ".pdf");
					FileOutputStream fOut = new FileOutputStream(file);
					PdfWriter.getInstance(doc, fOut);

					// open the document

					doc.open();
					Font boldFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
					Font colorF = new Font(Font.TIMES_ROMAN, 16, Font.BOLD
							| Font.UNDERLINE, harmony.java.awt.Color.darkGray);
					Font colorFU = new Font(Font.TIMES_ROMAN, 14, Font.BOLD
							| Font.UNDERLINE, harmony.java.awt.Color.BLACK);

					float[] columnWidthcv = { 1.5f };
					PdfPTable tablecv = new PdfPTable(columnWidthcv);
					tablecv.setHorizontalAlignment(Element.ALIGN_CENTER);
					tablecv.setWidthPercentage(100.0f);
					Phrase cv = new Phrase("CURRICULUM VITAE", colorF);
					PdfPCell cellcv = new PdfPCell(cv);
					cellcv.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellcv.setBorder(0);
					tablecv.addCell(cellcv);

					doc.add(tablecv);

					Paragraph blank1 = new Paragraph();
					addEmptyLine(blank1, 1);
					doc.add(blank1);

					float[] columnWidthspers = { 1.5f, 2f };
					Image myImg = null;
					if (bmp != null) {
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
						myImg = Image.getInstance(stream.toByteArray());
						myImg.scalePercent(50);
					} else {
						myImg = null;
					}

					// create PDF table with the given widths
					PdfPTable tablePersonal = new PdfPTable(columnWidthspers);

					// set table width a percentage of the page width
					tablePersonal.setWidthPercentage(100.0f);

					Phrase p = new Phrase("Personal Detail", boldFont);

					float[] columnWidthspers1 = { 1f, 2.5f };
					PdfPTable tableP = new PdfPTable(columnWidthspers1);

					// set table width a percentage of the page width
					tableP.setWidthPercentage(100.0f);

					PdfPCell pp1 = new PdfPCell(new Phrase("Name"));
					pp1.setBorder(Rectangle.RIGHT);

					tableP.addCell(pp1);
					PdfPCell pp2 = new PdfPCell(new Phrase(name));
					pp2.setBorder(0);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase("Address"));
					pp2.setBorder(Rectangle.RIGHT);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase(address));
					pp2.setBorder(0);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase("Language"));
					pp2.setBorder(Rectangle.RIGHT);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase(language));
					pp2.setBorder(0);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase("Contact"));
					pp2.setBorder(Rectangle.RIGHT);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase(contact));
					pp2.setBorder(0);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase("Email"));
					pp2.setBorder(Rectangle.RIGHT);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase(email));
					pp2.setBorder(0);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase("Birthdate"));
					pp2.setBorder(Rectangle.RIGHT);
					tableP.addCell(pp2);
					pp2 = new PdfPCell(new Phrase(txtDate));
					pp2.setBorder(0);
					tableP.addCell(pp2);

					PdfPCell cellp = new PdfPCell(p);
					cellp.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellp.setColspan(2);
					cellp.setBorder(0);
					Paragraph blank05 = new Paragraph();
					PdfPCell cellp22 = new PdfPCell(new Paragraph(blank05));
					addEmptyLine(blank05, 1);
					cellp22 = new PdfPCell(new Paragraph(blank05));
					cellp22.setColspan(2);
					headerCellStyle(cellp22);
					cellp22.setBackgroundColor(Color.WHITE);
					cellp22.setBorder(0);
					tablePersonal.addCell(cellp);
					tablePersonal.addCell(cellp22);
					tablePersonal.addCell(tableP);

					if (myImg == null) {
						cellp22 = new PdfPCell(new Phrase(" "));
					} else {
						cellp22 = new PdfPCell(myImg);
					}
					cellp22.setBorder(0);
					cellp22.setHorizontalAlignment(Element.ALIGN_RIGHT);
					tablePersonal.addCell(cellp22);

					tablePersonal.setHorizontalAlignment(Element.ALIGN_LEFT);
					tablePersonal.setHeaderRows(1);
					tablePersonal.setKeepTogether(true);
					doc.add(tablePersonal);

					Paragraph blank2 = new Paragraph();
					addEmptyLine(blank2, 1);
					doc.add(blank2);

					float[] columnWidthscareer = { 1f, 2.5f };

					// create PDF table with the given widths
					PdfPTable tableCareer = new PdfPTable(columnWidthscareer);

					// set table width a percentage of the page width
					tableCareer.setWidthPercentage(100.0f);

					PdfPCell cell1 = new PdfPCell(
							new Phrase("Career Objective"));
					cell1.setBackgroundColor(Color.LIGHT_GRAY);
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setColspan(2);
					tableCareer.addCell(cell1);

					Paragraph blank3 = new Paragraph();
					addEmptyLine(blank3, 1);
					cell1 = new PdfPCell(new Paragraph(blank3));
					cell1.setColspan(2);
					headerCellStyle(cell1);
					cell1.setBackgroundColor(Color.WHITE);
					cell1.setBorder(0);
					tableCareer.addCell(cell1);

					cell1 = new PdfPCell(new Phrase("OBJECTIVE ", colorFU));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setColspan(1);
					cell1.setBorder(0);
					tableCareer.addCell(cell1);

					Phrase pc2 = new Phrase("\t\t\t\t\t\t\t" + obj);

					PdfPCell cellpc2 = new PdfPCell(pc2);
					cellpc2.setBorder(0);
					tableCareer.addCell(cellpc2);

					if (sum.isEmpty()) {

					} else {

						PdfPCell cellsum = new PdfPCell(new Phrase("SUMMARY ",
								colorFU));
						cellsum.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellsum.setColspan(1);
						cellsum.setBorder(0);
						tableCareer.addCell(cellsum);

						Phrase pc22 = new Phrase("\t\t\t\t\t\t\t" + sum);

						cellpc2 = new PdfPCell(pc22);
						cellpc2.setBorder(0);
						tableCareer.addCell(cellpc2);
					}
					tableCareer.setHeaderRows(1);

					tableCareer.setKeepTogether(true);

					doc.add(tableCareer);

					Paragraph blank4 = new Paragraph();
					addEmptyLine(blank4, 2);
					doc.add(blank4);

					float[] columnWidthsEducation1 = { 2f, 3f, 3f, 2f, 2f };

					// create PDF table with the given widths
					PdfPTable tableEducation1 = new PdfPTable(
							columnWidthsEducation1);
					tableEducation1.setWidthPercentage(100.0f);
					cell1 = new PdfPCell(new Phrase("Education Details"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setColspan(5);
					cell1.setBackgroundColor(Color.LIGHT_GRAY);
					tableEducation1.addCell(cell1);
					Paragraph blank5 = new Paragraph();
					addEmptyLine(blank5, 2);
					cell1 = new PdfPCell(new Paragraph(blank5));
					cell1.setColspan(5);
					headerCellStyle(cell1);
					cell1.setBackgroundColor(Color.WHITE);
					tableEducation1.addCell(cell1);

					cell1 = new PdfPCell(new Phrase("Degree/Course"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setBackgroundColor(Color.lightGray);
					tableEducation1.addCell(cell1);
					cell1 = new PdfPCell(new Phrase("College/School"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setBackgroundColor(Color.lightGray);
					tableEducation1.addCell(cell1);
					cell1 = new PdfPCell(new Phrase("University/Board"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setBackgroundColor(Color.lightGray);
					tableEducation1.addCell(cell1);
					cell1 = new PdfPCell(new Phrase("Year Of Passing"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setBackgroundColor(Color.lightGray);
					tableEducation1.addCell(cell1);
					cell1 = new PdfPCell(new Phrase("% or CGPA"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setBackgroundColor(Color.lightGray);
					tableEducation1.addCell(cell1);
					tableEducation1.setHeaderRows(1);

					ArrayList<EducationPojo> ep = db
							.getAllEducationDetailPdf(id);
					for (EducationPojo pj : ep) {
						degree = pj.getDegree();
						college = pj.getCollege();
						university = pj.getUniversity();
						result = pj.getResult();
						yearofpassing = pj.getYearofpassing();
						percentage = pj.getPercentage();
						cell1 = new PdfPCell(new Phrase(degree));
						cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableEducation1.addCell(cell1);
						cell1 = new PdfPCell(new Phrase(college));
						cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableEducation1.addCell(cell1);
						cell1 = new PdfPCell(new Phrase(university));
						cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableEducation1.addCell(cell1);
						cell1 = new PdfPCell(new Phrase(yearofpassing));
						cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableEducation1.addCell(cell1);
						cell1 = new PdfPCell(new Phrase(result));
						cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
						tableEducation1.addCell(cell1);

					}
					cell1 = new PdfPCell();
					cell1.setBorder(0);
					cell1.setColspan(5);
					cell1.setPaddingBottom(20);
					tableEducation1.addCell(cell1);

					tableEducation1.setKeepTogether(true);

					doc.add(tableEducation1);

					// Skill Detail

					float[] columnWidthsSkill1 = { 5f };

					// create PDF table with the given widths
					PdfPTable tableSkill1 = new PdfPTable(columnWidthsSkill1);

					// set table width a percentage of the page width
					tableSkill1.setWidthPercentage(100.0f);
					tableSkill1.setHeaderRows(1);

					cell1 = new PdfPCell(new Phrase("Skill Details"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setColspan(1);
					cell1.setBackgroundColor(Color.LIGHT_GRAY);
					tableSkill1.addCell(cell1);
					Paragraph blank003 = new Paragraph();
					addEmptyLine(blank003, 1);
					cell1 = new PdfPCell(new Paragraph(blank003));
					cell1.setColspan(1);
					headerCellStyle(cell1);
					cell1.setBackgroundColor(Color.WHITE);
					cell1.setBorder(0);
					tableSkill1.addCell(cell1);

					ArrayList<SkillPojo> sk = db.getAllSkillDetailPdf(id);
					for (SkillPojo sk1 : sk) {

						skill = sk1.getSkill();
						cell1 = new PdfPCell(new Phrase("\u2022 " + skill));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell1.setBorder(0);
						tableSkill1.addCell(cell1);
						tableSkill1.setHeaderRows(1);
					}

					tableSkill1.setKeepTogether(true);
					if (tableSkill1.getRows().size() == 2) {
						tableSkill1.getRows().clear();

					} else {
						cell1 = new PdfPCell();
						cell1.setPaddingBottom(20);
						cell1.setColspan(5);
						cell1.setBorder(0);
						tableSkill1.addCell(cell1);
						tableSkill1.setHeaderRows(1);
						doc.add(tableSkill1);
					}

					// Project Detail

					float[] columnWidthsProject1 = { 5f };

					// create PDF table with the given widths
					PdfPTable tableProject1 = new PdfPTable(
							columnWidthsProject1);

					// set table width a percentage of the page width
					cell1 = new PdfPCell(new Phrase("Project Details"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setColspan(1);
					cell1.setBackgroundColor(Color.LIGHT_GRAY);
					tableProject1.addCell(cell1);

					Paragraph blank9 = new Paragraph();
					addEmptyLine(blank9, 2);
					cell1 = new PdfPCell(new Paragraph(blank9));
					cell1.setColspan(1);
					headerCellStyle(cell1);
					cell1.setBackgroundColor(Color.WHITE);
					tableProject1.addCell(cell1);
					tableProject1.setHeaderRows(1);
					tableProject1.setWidthPercentage(100.0f);

					ArrayList<ProjectPojo> pk = db.getAllProjectDetailPdf(id);
					for (ProjectPojo pk1 : pk) {
						title = pk1.getTitle();
						duration = pk1.getDuration();
						role = pk1.getRole();
						teamSize = pk1.getTeamSize();
						expertise = pk1.getExpertise();
						Phrase p2 = new Phrase("Title                :    "+ title 
								+ "\nDuration          :    " + duration
								+ "\nRole                :    " + role
								+ "\nTeam Size       :    " + teamSize
								+ "\nExpertise         :    " + expertise);

						cell1 = new PdfPCell(new Phrase(p2));
						tableProject1.addCell(cell1);

					}
					tableProject1.setKeepTogether(true);
					if (tableProject1.getRows().size() == 2) {
						tableProject1.getRows().clear();

					} else {
						cell1 = new PdfPCell();
						cell1.setPaddingBottom(20);
						cell1.setColspan(1);
						cell1.setBorder(0);
						tableProject1.addCell(cell1);
						tableProject1.setHeaderRows(1);
						doc.add(tableProject1);
					}

					float[] columnWidthsExperience1 = { 5f };

					// create PDF table with the given widths
					PdfPTable tableExperience1 = new PdfPTable(
							columnWidthsExperience1);

					// set table width a percentage of the page width
					tableExperience1.setWidthPercentage(100.0f);
					tableExperience1.setHeaderRows(1);

					cell1 = new PdfPCell(new Phrase("Experience Details"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setColspan(1);
					cell1.setBackgroundColor(Color.LIGHT_GRAY);
					tableExperience1.addCell(cell1);

					Paragraph blank15 = new Paragraph();
					addEmptyLine(blank15, 2);
					cell1 = new PdfPCell(new Paragraph(blank15));
					cell1.setColspan(1);
					headerCellStyle(cell1);
					cell1.setBackgroundColor(Color.WHITE);
					tableExperience1.addCell(cell1);
					tableExperience1.setHeaderRows(1);

					ArrayList<ExperiencePojo> exp = db
							.getAllExperienceDetailPdf(id);
					for (ExperiencePojo exp1 : exp) {
						company = exp1.getCompany();
						position = exp1.getPosition();
						period = exp1.getPeriod();
						location = exp1.getLocation();
						Phrase p2 = new Phrase("Company Name     :    "
								+ company + "\nPosition                  :    " + position
								+ "\nPeriod                    :    " + period
								+ "\nLocation                 :    " + location);

						cell1 = new PdfPCell(new Phrase(p2));
						tableExperience1.addCell(cell1);

					}
					tableExperience1.setKeepTogether(true);
					if (tableExperience1.getRows().size() == 2) {
						tableExperience1.getRows().clear();
					} else {
						cell1 = new PdfPCell();
						cell1.setPaddingBottom(20);
						cell1.setBorder(0);
						cell1.setColspan(1);
						tableExperience1.addCell(cell1);
						tableExperience1.setHeaderRows(1);
						doc.add(tableExperience1);
					}

					float[] columnWidthsAchievement1 = { 5f };

					// create PDF table with the given widths
					PdfPTable tableAchievement1 = new PdfPTable(
							columnWidthsAchievement1);

					// set table width a percentage of the page width
					tableAchievement1.setWidthPercentage(100.0f);
					tableAchievement1.setHeaderRows(1);

					cell1 = new PdfPCell(new Phrase("Achievement Details"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setColspan(1);
					cell1.setBackgroundColor(Color.LIGHT_GRAY);
					tableAchievement1.addCell(cell1);

					Paragraph blank20 = new Paragraph();
					addEmptyLine(blank20, 2);
					cell1 = new PdfPCell(new Paragraph(blank20));
					cell1.setColspan(1);
					headerCellStyle(cell1);
					cell1.setBackgroundColor(Color.WHITE);
					tableAchievement1.addCell(cell1);
					tableAchievement1.setHeaderRows(1);

					ArrayList<AchievementDetailPojo> ad = db
							.getAllAchievementDetailPdf(id);
					for (AchievementDetailPojo ad1 : ad) {
						name_of_achievement = ad1.getName_of_achievement();
						year_of_achievement = ad1.getYear_of_achievement();
						what_you_achieved = ad1.getWhat_you_achieved();
						Phrase p2 = new Phrase(
								"Name of Achievement     :    "
										+ name_of_achievement
										+ "\nYear of Achievement       :    "
										+ year_of_achievement
										+ "\nAchievement                    :    "
										+ what_you_achieved);

						cell1 = new PdfPCell(new Phrase(p2));
						tableAchievement1.addCell(cell1);
					}
					tableAchievement1.setKeepTogether(true);
					if (tableAchievement1.getRows().size() == 2) {
						tableAchievement1.getRows().clear();
					} else {
						cell1 = new PdfPCell();
						cell1.setBorder(0);
						cell1.setColspan(1);
						cell1.setPaddingBottom(20);
						tableAchievement1.addCell(cell1);
						doc.add(tableAchievement1);
					}

					float[] columnWidthsReference1 = { 5f };

					// create PDF table with the given widths
					PdfPTable tableReference1 = new PdfPTable(
							columnWidthsReference1);

					// set table width a percentage of the page width
					tableReference1.setWidthPercentage(100.0f);
					tableReference1.setHeaderRows(1);

					cell1 = new PdfPCell(new Phrase("Reference Details"));
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setColspan(1);
					cell1.setBackgroundColor(Color.LIGHT_GRAY);
					tableReference1.addCell(cell1);

					Paragraph blank21 = new Paragraph();
					addEmptyLine(blank21, 2);
					cell1 = new PdfPCell(new Paragraph(blank21));
					cell1.setColspan(1);
					headerCellStyle(cell1);
					cell1.setBackgroundColor(Color.WHITE);
					tableReference1.addCell(cell1);
					tableReference1.setHeaderRows(1);

					ArrayList<ReferenceDetailPojo> rd = db
							.getAllReferenceDetailPdf(id);
					for (ReferenceDetailPojo rd1 : rd) {
						rerf_name = rd1.getRefName();
						ref_detail = rd1.getRefDetail();
						ref_contact = rd1.getRefContact();
						ref_email = rd1.getRefEmail();
						Phrase p2 = new Phrase("Reference Name    :    "
								+ rerf_name + "\nPosition                  :    "
								+ ref_detail + "\nContact                   :    "
								+ ref_contact + "\nEmail                      :    " + ref_email);

						cell1 = new PdfPCell(new Phrase(p2));
						tableReference1.addCell(cell1);
						tableReference1.setHeaderRows(1);
					}
					if (tableReference1.getRows().size() == 2) {
						tableReference1.getRows().clear();
					} else {
						cell1 = new PdfPCell();
						cell1.setBorder(0);
						cell1.setColspan(1);
						cell1.setPaddingBottom(20);
						tableReference1.addCell(cell1);
						tableReference1.setKeepTogether(true);
						doc.add(tableReference1);
					}
					Paragraph pr = new Paragraph(
							"I, here by declare that the information given above is true to the best of my knowledge.");
					Font paraF = new Font(Font.COURIER);
					pr.setAlignment(Paragraph.ALIGN_LEFT);
					pr.setFont(paraF);
					// Inserting Signature in PDF
					Image myImg1 = null;
					if (bmp1 != null) {
						ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
						bmp1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
						myImg1 = Image.getInstance(stream1.toByteArray());
						myImg1.setAlignment(Image.RIGHT);
						myImg1.scalePercent(25);
					} else {
						myImg1 = null;
					}
					float[] columnWidthsSignature1 = { 10f, 10f };

					// create PDF table with the given widths
					PdfPTable tableSignature1 = new PdfPTable(
							columnWidthsSignature1);

					// set table width a percentage of the page width
					tableSignature1.setWidthPercentage(100.0f);
					tableSignature1.setHeaderRows(1);

					cell1 = new PdfPCell(new Paragraph(pr));
					cell1.setBorder(0);
					cell1.setColspan(2);
					cell1.setPaddingBottom(30);
					tableSignature1.addCell(cell1);

					ArrayList<SignaturePojo> sd = db
							.getAllSignatureDetailPdf(id);
					if (sd.isEmpty()) {

						cell1 = new PdfPCell(new Phrase("Place : "));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell1.setBorder(0);
						tableSignature1.addCell(cell1);

						cell1 = new PdfPCell(new Phrase(""));
						cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell1.setBorder(0);
						tableSignature1.addCell(cell1);

						cell1 = new PdfPCell(new Phrase("Date : "));
						cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell1.setBorder(0);
						tableSignature1.addCell(cell1);

						cell1 = new PdfPCell(new Phrase("Yours Truely \n"));
						cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell1.setBorder(0);

						tableSignature1.addCell(cell1);
						tableSignature1.setHeaderRows(1);
					} else {
						for (SignaturePojo sd1 : sd) {
							place = sd1.getPlace();
							dt = sd1.getDt();
							sinc = sd1.getSincerely();
							cell1 = new PdfPCell(new Phrase("Place : " + place));
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell1.setBorder(0);
							tableSignature1.addCell(cell1);

							cell1 = new PdfPCell(myImg1);
							cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cell1.setBorder(0);
							tableSignature1.addCell(cell1);

							cell1 = new PdfPCell(new Phrase("Date : " + dt));
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell1.setBorder(0);
							tableSignature1.addCell(cell1);

							cell1 = new PdfPCell(new Phrase("Yours Truely \n"
									+ sinc));
							cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cell1.setBorder(0);

							tableSignature1.addCell(cell1);
							tableSignature1.setHeaderRows(1);
						}
					}
					tableSignature1.setKeepTogether(true);
					doc.add(tableSignature1);

					Toast.makeText(getApplicationContext(), "Created...",
							Toast.LENGTH_LONG).show();

				} catch (DocumentException de) {
					de.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					doc.close();
				}
			}

		});

		ipdf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (id == 0) {
					id = idclick;
				}
				List<EducationPojo> checkep = db.getAllEducationDetailPdf(id);
				List<PersonalDetailPojo> checkper = db.getAllPersonalDetail(id);
				List<CareerPojo> cpr = db.getAllCareer(id);

				if (checkep.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"Enter Education Detail", Toast.LENGTH_LONG).show();
				} else if (checkper.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"Enter Personal Detail", Toast.LENGTH_LONG).show();

				} else if (cpr.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"Enter Career Objective Detail", Toast.LENGTH_LONG)
							.show();

				} else {
					createPDF();
					openPdf();
				}
			}
		});
	}

	public void createPDF() {
		Bitmap bmp = null;
		if (id == 0) {
			id = idclick;
		}
		if (id != 0) {
			List<PersonalDetailPojo> retrivedata = db.getAllPersonalDetail(id);
			for (PersonalDetailPojo p : retrivedata) {

				name = p.getPersonName().toString();
				address = p.getAddress().toString();
				language = p.getLanguage().toString();
				contact = p.getMobile().toString();
				email = p.getEmail().toString();
				txtDate = p.getBirthdate().toString();
			}
			List<CareerPojo> cp = db.getAllCareer(id);
			for (CareerPojo c : cp) {
				obj = c.getObjective();
				sum = c.getSummary();
			}
			List<User> user = db.getUserpdf(id);

			for (User c : user) {
				namepdf = c.getName();
				byte[] bt = c.getImgProfile();
				if (bt == null) {
					bmp = null;
				} else {
					bmp = BitmapFactory.decodeByteArray(c.getImgProfile(), 0,
							c.getImgProfile().length);
				}
			}
		}
		Document doc = new Document(PageSize.A4);
		Font font = new Font(Font.HELVETICA, 14, Font.BOLD, Color.WHITE);

		try {

			String path = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/ResumeMaker";

			ArrayList<SigImage> si = db.getAllUserSignature(id);
			Bitmap bmp1 = null;
			for (SigImage si1 : si) {
				byte[] bt = si1.getSign();
				if (bt == null) {
					bmp1 = null;
				} else {
					bmp1 = BitmapFactory.decodeByteArray(si1.getSign(), 0,
							si1.getSign().length);
				}
			}
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();

			File file;
			if (str == "doc") {
				file = new File(dir, namepdf + ".docx");
			} else {
				file = new File(dir, namepdf + ".pdf");
			}
			FileOutputStream fOut = new FileOutputStream(file);
			PdfWriter.getInstance(doc, fOut);

			// open the document

			doc.open();
			Font boldFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);

			Font normalFont = new Font(Font.TIMES_ROMAN, 10, Font.ITALIC);
			Font colorF = new Font(Font.TIMES_ROMAN, 16, Font.BOLD
					| Font.UNDERLINE, harmony.java.awt.Color.darkGray);

			Font colorFU = new Font(Font.TIMES_ROMAN, 16, Font.BOLD
					| Font.UNDERLINE, harmony.java.awt.Color.BLACK);

			float[] columnWidthcv = { 1.5f };
			PdfPTable tablecv = new PdfPTable(columnWidthcv);
			tablecv.setHorizontalAlignment(Element.ALIGN_CENTER);
			tablecv.setWidthPercentage(100.0f);
			Phrase cv = new Phrase("CURRICULUM VITAE", colorF);
			PdfPCell cellcv = new PdfPCell(cv);
			cellcv.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellcv.setBorder(0);
			tablecv.addCell(cellcv);

			doc.add(tablecv);

			Paragraph blank1 = new Paragraph();
			addEmptyLine(blank1, 1);
			doc.add(blank1);

			float[] columnWidthspers = { 1.5f, 2f };
			Image myImg = null;
			if (bmp != null) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				myImg = Image.getInstance(stream.toByteArray());
				myImg.scalePercent(50);
			} else {
				myImg = null;
			}

			// create PDF table with the given widths
			PdfPTable tablePersonal = new PdfPTable(columnWidthspers);

			// set table width a percentage of the page width
			tablePersonal.setWidthPercentage(100.0f);

			Phrase p = new Phrase("Personal Detail", boldFont);

			float[] columnWidthspers1 = { 1f, 2.5f };
			PdfPTable tableP = new PdfPTable(columnWidthspers1);

			// set table width a percentage of the page width
			tableP.setWidthPercentage(100.0f);

			PdfPCell pp1 = new PdfPCell(new Phrase("Name"));
			pp1.setBorder(Rectangle.RIGHT);

			tableP.addCell(pp1);
			PdfPCell pp2 = new PdfPCell(new Phrase(name));
			pp2.setBorder(0);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase("Address"));
			pp2.setBorder(Rectangle.RIGHT);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase(address));
			pp2.setBorder(0);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase("Language"));
			pp2.setBorder(Rectangle.RIGHT);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase(language));
			pp2.setBorder(0);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase("Contact"));
			pp2.setBorder(Rectangle.RIGHT);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase(contact));
			pp2.setBorder(0);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase("Email"));
			pp2.setBorder(Rectangle.RIGHT);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase(email));
			pp2.setBorder(0);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase("Birthdate"));
			pp2.setBorder(Rectangle.RIGHT);
			tableP.addCell(pp2);
			pp2 = new PdfPCell(new Phrase(txtDate));
			pp2.setBorder(0);
			tableP.addCell(pp2);
			PdfPCell cellp = new PdfPCell(p);
			cellp.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellp.setColspan(2);
			cellp.setBorder(0);
			Paragraph blank05 = new Paragraph();
			PdfPCell cellp22 = new PdfPCell(new Paragraph(blank05));
			addEmptyLine(blank05, 1);
			cellp22 = new PdfPCell(new Paragraph(blank05));
			cellp22.setColspan(2);
			headerCellStyle(cellp22);
			cellp22.setBackgroundColor(Color.WHITE);
			cellp22.setBorder(0);
			tablePersonal.addCell(cellp);
			tablePersonal.addCell(cellp22);

			tablePersonal.addCell(tableP);

			if (myImg == null) {
				cellp22 = new PdfPCell(new Phrase(" "));
			} else {
				cellp22 = new PdfPCell(myImg);
			}
			cellp22.setBorder(0);
			cellp22.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tablePersonal.addCell(cellp22);

			tablePersonal.setHorizontalAlignment(Element.ALIGN_LEFT);
			tablePersonal.setHeaderRows(1);
			tablePersonal.setKeepTogether(true);
			doc.add(tablePersonal);

			Paragraph blank2 = new Paragraph();
			addEmptyLine(blank2, 1);
			doc.add(blank2);

			float[] columnWidthscareer = { 1.5f };

			// create PDF table with the given widths
			PdfPTable tableCareer = new PdfPTable(columnWidthscareer);

			// set table width a percentage of the page width
			tableCareer.setWidthPercentage(100.0f);

			PdfPCell cell1 = new PdfPCell(new Phrase("Career Objective", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setColspan(1);
			headerCellStyle(cell1);
			tableCareer.addCell(cell1);

			Paragraph blank3 = new Paragraph();
			addEmptyLine(blank3, 1);
			cell1 = new PdfPCell(new Paragraph(blank3));
			cell1.setColspan(1);
			headerCellStyle(cell1);
			cell1.setBackgroundColor(Color.WHITE);
			cell1.setBorder(0);
			tableCareer.addCell(cell1);

			cell1 = new PdfPCell(new Phrase("OBJECTIVE ", colorFU));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setColspan(1);
			cell1.setBorder(0);
			tableCareer.addCell(cell1);

			Phrase pc2 = new Phrase("\t\t\t\t\t\t\t" + obj + "\n");

			PdfPCell cellpc2 = new PdfPCell(pc2);
			cellpc2.setBorder(0);
			tableCareer.addCell(cellpc2);

			if (sum.isEmpty()) {

			} else {

				PdfPCell cellsum = new PdfPCell(new Phrase("SUMMARY ", colorFU));
				cellsum.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellsum.setColspan(1);
				cellsum.setBorder(0);
				tableCareer.addCell(cellsum);

				Phrase pc22 = new Phrase("\n" + "\t\t\t\t\t\t\t" + sum);

				cellpc2 = new PdfPCell(pc22);
				cellpc2.setBorder(0);
				tableCareer.addCell(cellpc2);
			}
			tableCareer.setHeaderRows(1);

			tableCareer.setKeepTogether(true);

			doc.add(tableCareer);

			Paragraph blank4 = new Paragraph();
			addEmptyLine(blank4, 2);
			doc.add(blank4);

			float[] columnWidthsEducation1 = { 2f, 3f, 3f, 2f, 2f };

			// create PDF table with the given widths
			PdfPTable tableEducation1 = new PdfPTable(columnWidthsEducation1);
			tableEducation1.setWidthPercentage(100.0f);
			cell1 = new PdfPCell(new Phrase("Education Details", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setColspan(5);
			headerCellStyle(cell1);
			tableEducation1.addCell(cell1);
			Paragraph blank5 = new Paragraph();
			addEmptyLine(blank5, 2);
			cell1 = new PdfPCell(new Paragraph(blank5));
			cell1.setColspan(5);
			headerCellStyle(cell1);
			cell1.setBackgroundColor(Color.WHITE);
			tableEducation1.addCell(cell1);

			cell1 = new PdfPCell(new Phrase("Degree/Course"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableEducation1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("College/School"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableEducation1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("University/Board"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableEducation1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Year of Passing"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableEducation1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("% or CGPA"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableEducation1.addCell(cell1);
			tableEducation1.setHeaderRows(1);

			ArrayList<EducationPojo> ep = db.getAllEducationDetailPdf(id);
			for (EducationPojo pj : ep) {
				degree = pj.getDegree();
				college = pj.getCollege();
				university = pj.getUniversity();
				result = pj.getResult();
				yearofpassing = pj.getYearofpassing();
				percentage = pj.getPercentage();
				cell1 = new PdfPCell(new Phrase(degree));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableEducation1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(college));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableEducation1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(university));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableEducation1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(yearofpassing));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableEducation1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(result));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableEducation1.addCell(cell1);

			}
			cell1 = new PdfPCell();
			cell1.setBorder(0);
			cell1.setColspan(5);
			cell1.setPaddingBottom(20);
			tableEducation1.addCell(cell1);

			tableEducation1.setKeepTogether(true);

			doc.add(tableEducation1);

			// Skill Detail

			float[] columnWidthsSkill1 = { 5f };

			// create PDF table with the given widths
			PdfPTable tableSkill1 = new PdfPTable(columnWidthsSkill1);

			// set table width a percentage of the page width
			tableSkill1.setWidthPercentage(100.0f);
			tableSkill1.setHeaderRows(1);

			cell1 = new PdfPCell(new Phrase("Skill Details", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setColspan(1);
			headerCellStyle(cell1);
			tableSkill1.addCell(cell1);
			Paragraph blank003 = new Paragraph();
			addEmptyLine(blank003, 1);
			cell1 = new PdfPCell(new Paragraph(blank003));
			cell1.setColspan(1);
			headerCellStyle(cell1);
			cell1.setBackgroundColor(Color.WHITE);
			cell1.setBorder(0);
			tableSkill1.addCell(cell1);

			ArrayList<SkillPojo> sk = db.getAllSkillDetailPdf(id);
			for (SkillPojo sk1 : sk) {

				skill = sk1.getSkill();
				cell1 = new PdfPCell(new Phrase("\u2022 " + skill));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setBorder(0);
				tableSkill1.addCell(cell1);
				tableSkill1.setHeaderRows(1);
			}

			tableSkill1.setKeepTogether(true);
			if (tableSkill1.getRows().size() == 2) {
				tableSkill1.getRows().clear();

			} else {
				cell1 = new PdfPCell();
				cell1.setPaddingBottom(20);
				cell1.setColspan(5);
				cell1.setBorder(0);
				tableSkill1.addCell(cell1);
				tableSkill1.setHeaderRows(1);
				doc.add(tableSkill1);
			}

			// Project Detail

			float[] columnWidthsProject1 = { 4f, 4.5f, 2f, 1f, 2f };

			// create PDF table with the given widths
			PdfPTable tableProject1 = new PdfPTable(columnWidthsProject1);

			// set table width a percentage of the page width
			cell1 = new PdfPCell(new Phrase("Project Details", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setColspan(5);
			headerCellStyle(cell1);
			tableProject1.addCell(cell1);

			Paragraph blank9 = new Paragraph();
			addEmptyLine(blank9, 2);
			cell1 = new PdfPCell(new Paragraph(blank9));
			cell1.setColspan(5);
			headerCellStyle(cell1);
			cell1.setBackgroundColor(Color.WHITE);
			tableProject1.addCell(cell1);

			tableProject1.setWidthPercentage(100.0f);
			cell1 = new PdfPCell(new Phrase("Project Title"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableProject1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Duration"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableProject1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Role"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableProject1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Team Size"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableProject1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Expertise"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableProject1.addCell(cell1);
			tableProject1.setHeaderRows(1);

			// set table width a percentage of the page width

			ArrayList<ProjectPojo> pk = db.getAllProjectDetailPdf(id);
			for (ProjectPojo pk1 : pk) {
				title = pk1.getTitle();
				duration = pk1.getDuration();
				role = pk1.getRole();
				teamSize = pk1.getTeamSize();
				expertise = pk1.getExpertise();
				cell1 = new PdfPCell(new Phrase(title));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableProject1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(duration));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableProject1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(role));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableProject1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(teamSize));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableProject1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(expertise));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableProject1.addCell(cell1);

			}
			tableProject1.setKeepTogether(true);
			if (tableProject1.getRows().size() == 3) {
				tableProject1.getRows().clear();

			} else {
				cell1 = new PdfPCell();
				cell1.setPaddingBottom(20);
				cell1.setColspan(5);
				cell1.setBorder(0);
				tableProject1.addCell(cell1);
				tableProject1.setHeaderRows(1);
				doc.add(tableProject1);
			}

			float[] columnWidthsExperience1 = { 3f, 2f, 4f, 2f };

			// create PDF table with the given widths
			PdfPTable tableExperience1 = new PdfPTable(columnWidthsExperience1);

			// set table width a percentage of the page width
			tableExperience1.setWidthPercentage(100.0f);
			tableExperience1.setHeaderRows(1);

			cell1 = new PdfPCell(new Phrase("Experience Details", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setColspan(4);
			headerCellStyle(cell1);
			tableExperience1.addCell(cell1);

			Paragraph blank15 = new Paragraph();
			addEmptyLine(blank15, 2);
			cell1 = new PdfPCell(new Paragraph(blank15));
			cell1.setColspan(4);
			headerCellStyle(cell1);
			cell1.setBackgroundColor(Color.WHITE);
			tableExperience1.addCell(cell1);

			cell1 = new PdfPCell(new Phrase("Company Name"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableExperience1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Position"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableExperience1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Duration"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableExperience1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Location"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableExperience1.addCell(cell1);
			tableExperience1.setHeaderRows(1);

			ArrayList<ExperiencePojo> exp = db.getAllExperienceDetailPdf(id);
			for (ExperiencePojo exp1 : exp) {
				company = exp1.getCompany();
				position = exp1.getPosition();
				period = exp1.getPeriod();
				location = exp1.getLocation();

				cell1 = new PdfPCell(new Phrase(company));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableExperience1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(position));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableExperience1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(period));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableExperience1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(location));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableExperience1.addCell(cell1);

			}
			tableExperience1.setKeepTogether(true);
			if (tableExperience1.getRows().size() == 3) {
				tableExperience1.getRows().clear();
			} else {
				cell1 = new PdfPCell();
				cell1.setPaddingBottom(20);
				cell1.setBorder(0);
				cell1.setColspan(4);
				tableExperience1.addCell(cell1);
				tableExperience1.setHeaderRows(1);
				doc.add(tableExperience1);
			}

			float[] columnWidthsAchievement1 = { 3f, 2f, 2f };

			// create PDF table with the given widths
			PdfPTable tableAchievement1 = new PdfPTable(
					columnWidthsAchievement1);

			// set table width a percentage of the page width
			tableAchievement1.setWidthPercentage(100.0f);
			tableAchievement1.setHeaderRows(1);

			cell1 = new PdfPCell(new Phrase("Achievement Details", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setColspan(3);
			headerCellStyle(cell1);
			tableAchievement1.addCell(cell1);

			Paragraph blank20 = new Paragraph();
			addEmptyLine(blank20, 2);
			cell1 = new PdfPCell(new Paragraph(blank20));
			cell1.setColspan(3);
			headerCellStyle(cell1);
			cell1.setBackgroundColor(Color.WHITE);
			tableAchievement1.addCell(cell1);

			cell1 = new PdfPCell(new Phrase("Name Of Achievement"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableAchievement1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Year Of Achievement"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableAchievement1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Achievement"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableAchievement1.addCell(cell1);
			tableAchievement1.setHeaderRows(1);

			ArrayList<AchievementDetailPojo> ad = db
					.getAllAchievementDetailPdf(id);
			for (AchievementDetailPojo ad1 : ad) {
				name_of_achievement = ad1.getName_of_achievement();
				year_of_achievement = ad1.getYear_of_achievement();
				what_you_achieved = ad1.getWhat_you_achieved();
				cell1 = new PdfPCell(new Phrase(name_of_achievement));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableAchievement1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(year_of_achievement));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableAchievement1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(what_you_achieved));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableAchievement1.addCell(cell1);
				tableAchievement1.setHeaderRows(1);
			}
			tableAchievement1.setKeepTogether(true);
			if (tableAchievement1.getRows().size() == 3) {
				tableAchievement1.getRows().clear();
			} else {
				cell1 = new PdfPCell();
				cell1.setBorder(0);
				cell1.setColspan(3);
				cell1.setPaddingBottom(20);
				tableAchievement1.addCell(cell1);
				doc.add(tableAchievement1);
			}

			float[] columnWidthsReference1 = { 3f, 2f, 2f, 2f };

			// create PDF table with the given widths
			PdfPTable tableReference1 = new PdfPTable(columnWidthsReference1);

			// set table width a percentage of the page width
			tableReference1.setWidthPercentage(100.0f);
			tableReference1.setHeaderRows(1);

			cell1 = new PdfPCell(new Phrase("Reference Details", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setColspan(4);
			headerCellStyle(cell1);
			tableReference1.addCell(cell1);

			Paragraph blank21 = new Paragraph();
			addEmptyLine(blank21, 2);
			cell1 = new PdfPCell(new Paragraph(blank21));
			cell1.setColspan(4);
			headerCellStyle(cell1);
			cell1.setBackgroundColor(Color.WHITE);
			tableReference1.addCell(cell1);

			cell1 = new PdfPCell(new Phrase("Name"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableReference1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Detail"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableReference1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Contact"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableReference1.addCell(cell1);
			cell1 = new PdfPCell(new Phrase("Email"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(Color.lightGray);
			tableReference1.addCell(cell1);
			tableReference1.setHeaderRows(1);

			ArrayList<ReferenceDetailPojo> rd = db.getAllReferenceDetailPdf(id);
			for (ReferenceDetailPojo rd1 : rd) {
				rerf_name = rd1.getRefName();
				ref_detail = rd1.getRefDetail();
				ref_contact = rd1.getRefContact();
				ref_email = rd1.getRefEmail();
				cell1 = new PdfPCell(new Phrase(rerf_name));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableReference1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(ref_detail));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableReference1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(ref_contact));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableReference1.addCell(cell1);
				cell1 = new PdfPCell(new Phrase(ref_email));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableReference1.addCell(cell1);
				tableReference1.setHeaderRows(1);
			}
			if (tableReference1.getRows().size() == 3) {
				tableReference1.getRows().clear();
			} else {
				cell1 = new PdfPCell();
				cell1.setBorder(0);
				cell1.setColspan(4);
				cell1.setPaddingBottom(20);
				tableReference1.addCell(cell1);
				tableReference1.setKeepTogether(true);
				doc.add(tableReference1);
			}
			Paragraph pr = new Paragraph(
					" I, here by declare that the information given above is true to the best of my knowledge.");
			Font paraF = new Font(Font.COURIER);
			pr.setAlignment(Paragraph.ALIGN_LEFT);
			pr.setFont(paraF);
			// Inserting Signature in PDF
			Image myImg1 = null;
			if (bmp1 != null) {
				ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
				bmp1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
				myImg1 = Image.getInstance(stream1.toByteArray());
				myImg1.setAlignment(Image.RIGHT);
				myImg1.scalePercent(25);
			} else {
				myImg1 = null;
			}
			float[] columnWidthsSignature1 = { 10f, 10f };

			// create PDF table with the given widths
			PdfPTable tableSignature1 = new PdfPTable(columnWidthsSignature1);

			// set table width a percentage of the page width
			tableSignature1.setWidthPercentage(100.0f);
			tableSignature1.setHeaderRows(1);

			cell1 = new PdfPCell(new Paragraph(pr));
			cell1.setBorder(0);
			cell1.setColspan(2);
			cell1.setPaddingBottom(30);
			tableSignature1.addCell(cell1);

			ArrayList<SignaturePojo> sd = db.getAllSignatureDetailPdf(id);
			if (sd.isEmpty()) {

				cell1 = new PdfPCell(new Phrase("Place : "));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setBorder(0);
				tableSignature1.addCell(cell1);

				cell1 = new PdfPCell(new Phrase(""));
				cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell1.setBorder(0);
				tableSignature1.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("Date : "));
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell1.setBorder(0);
				tableSignature1.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("Sincerely \n"));
				cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell1.setBorder(0);

				tableSignature1.addCell(cell1);
				tableSignature1.setHeaderRows(1);
			} else {
				for (SignaturePojo sd1 : sd) {
					place = sd1.getPlace();
					dt = sd1.getDt();
					sinc = sd1.getSincerely();
					cell1 = new PdfPCell(new Phrase("Place : " + place));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell1.setBorder(0);
					tableSignature1.addCell(cell1);

					cell1 = new PdfPCell(myImg1);
					cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell1.setBorder(0);
					tableSignature1.addCell(cell1);

					cell1 = new PdfPCell(new Phrase("Date : " + dt));
					cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell1.setBorder(0);
					tableSignature1.addCell(cell1);

					cell1 = new PdfPCell(new Phrase("Sincerely \n" + sinc));
					cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell1.setBorder(0);

					tableSignature1.addCell(cell1);
					tableSignature1.setHeaderRows(1);
				}
			}
			tableSignature1.setKeepTogether(true);
			doc.add(tableSignature1);

			Toast.makeText(getApplicationContext(), "Created...",
					Toast.LENGTH_LONG).show();

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			doc.close();
		}
	}

	void openPdf() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/ResumeMaker";
		File file;
		file = new File(path, namepdf + ".pdf");
		intent.setDataAndType(Uri.fromFile(file), "application/pdf");
		startActivity(intent);

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public static void headerCellStyle(PdfPCell cell) {

		// alignment
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// padding
		cell.setPaddingTop(0f);
		cell.setPaddingBottom(7f);
		// background color
		cell.setBackgroundColor(new Color(0, 121, 182));
		// border
		cell.setBorder(0);
		cell.setBorderWidthBottom(2f);

	}
}
