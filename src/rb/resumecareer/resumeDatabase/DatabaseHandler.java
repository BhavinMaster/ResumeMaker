package rb.resumecareer.resumeDatabase;

import java.util.ArrayList;
import java.util.List;

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
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	SQLiteDatabase db;
	String tempid;
	public static final int DATABASE_VERSION = 1;
	// Database Name
	public static final String DATABASE_NAME = "database";
	// User Profile table name
	public static final String TABLE_USER_PROFILE = "user_profile";
	// Personal Table Name
	private static final String TABLE_PERSONAL_DETAILS = "personal_detail";
	// Career Table Name
	private static final String TABLE_CAREER_DETAILS = "career_detail";
	// Education_Detail Table Name
	public static final String TABLE_EDUCATION_DETAILS = "education_detail";
	// Skill Detail Table Name
	public static final String TABLE_SKILL_DETAILS = "skill_detail";
	// Project Detail Table Name
	public static final String TABLE_PROJECT_DETAILS = "project_detail";
	// Experience Detail Table Name
	public static final String TABLE_EXPERIENCE_DETAILS = "experience_detail";
	// Achievement Detail Table Name
	public static final String TABLE_ACHIEVEMENT_DETAILS = "achievement_detail";
	// Reference Detail Table Name
	public static final String TABLE_REFERENCE_DETAILS = "reference_detail";
	// Reference Detail Table Name
	public static final String TABLE_SIGNATURE = "signature";

	// User Profile Table Columns names
	public static final String KEY_ID = "id";
	public static final String KEY_USER_NAME = "name";
	public static final String IMAGE_PROFILE = "img";

	// Personal Detail Table Columns names
	private static final String PERSON_KEY_ID = "pid";
	private static final String PERSON_NAME = "personName";
	private static final String GENDER = "gender";
	private static final String EMAIL = "email";
	private static final String MOBILE = "mobile";
	private static final String ADDRESS = "address";
	private static final String LANGUAGE = "language";
	private static final String BIRTHDATE = "birthdate";

	// Career Detail Table Columns names
	private static final String CAREER_KEY_ID = "cid";
	private static final String OBJECTIVE = "objective";
	private static final String SUMMARY = "summary";

	// Education Detail Table Columns names
	private static final String EDUCATION_KEY_ID = "eid";
	private static final String DEGREE = "degree";
	private static final String COLLEGE = "college";
	private static final String UNIVERSITY = "university";
	private static final String RESULT = "result";
	private static final String PERCENTAGE = "percentage";
	private static final String YEAR_OF_PASSING = "yearofpassing";
	private static final String EDUCATION_FOREIGN_KEY_ID = "id";

	// Skill Detail Table Columns names
	private static final String SKILL_KEY_ID = "sid";
	private static final String SKILL = "skill";
	private static final String SKILL_FOREIGN_KEY_ID = "id";

	// Project Detail Table Columns names
	private static final String PROJECT_KEY_ID = "proj_id";
	private static final String TITLE = "title";
	private static final String DURATION = "duration";
	private static final String ROLE = "role";
	private static final String TEAM_SIZE = "team_size";
	private static final String EXPERTISE = "expertise";
	private static final String PROJECT_FOREIGN_KEY_ID = "id";

	// Experience Detail Table Columns names
	private static final String EXPERIENCE_KEY_ID = "exp_id";
	private static final String COMPANY = "company";
	private static final String POSITION = "position";
	private static final String PERIOD = "peiod";
	private static final String MONTH = "month";
	private static final String YEAR = "year";
	private static final String LOCATION = "location";
	private static final String EXPERIENCE_FOREIGN_KEY_ID = "id";

	// Achievement Detail Table Columns names
	private static final String ACHIEVEMENT_KEY_ID = "aid";
	private static final String NAME_OF_ACHIEVEMENT = "name_of_achievement";
	private static final String YEAR_OF_ACHIEVEMENT = "year_of_achievement";
	private static final String WHAT_YOU_ACHIEVED = "what_you_achieved";
	private static final String ACHIEVEMENT_FOREIGN_KEY_ID = "id";

	// Reference Detail Table Columns names
	private static final String REFERENCE_KEY_ID = "ref_id";
	private static final String REFERENCE_NAME = "reference_name";
	private static final String REFERENCE_DETAIL = "reference_detail";
	private static final String REFERENCE_CONTACT = "reference_contact";
	private static final String REFERENCE_EMAIL = "reference_email";
	private static final String REFERENCE_FOREIGN_KEY_ID = "id";

	// User Signature Table Columns names
	private static final String SIGNATURE_KEY_ID = "sig_id";
	private static final String PLACE = "place";
	private static final String SIGNATURE_DATE = "signature_date";
	private static final String SINCERELY = "sincerely";
	private static final String MAKE_SIGNATURE = "make_signature";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// User Profile Table Creation
		String CREATE_USER_PROFILE_TABLE = "CREATE TABLE " + TABLE_USER_PROFILE
				+ "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ KEY_USER_NAME + " TEXT," + IMAGE_PROFILE + " BLOB" + ")";
		db.execSQL(CREATE_USER_PROFILE_TABLE);

		// Personal Detail Table Creation
		String CREATE_TABLE_PERSONAL_DETAILS = "CREATE TABLE "
				+ TABLE_PERSONAL_DETAILS + "(" + PERSON_KEY_ID + " INTEGER,"
				+ PERSON_NAME + " TEXT," + GENDER + " TEXT," + BIRTHDATE
				+ " TEXT," + ADDRESS + " TEXT," + LANGUAGE + " TEXT," + MOBILE
				+ " TEXT," + EMAIL + " TEXT," + "FOREIGN KEY(" + PERSON_KEY_ID
				+ ") REFERENCES " + TABLE_USER_PROFILE
				+ "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_PERSONAL_DETAILS);

		// Career Detail Table Creation
		String CREATE_TABLE_CAREER_DETAILS = "CREATE TABLE "
				+ TABLE_CAREER_DETAILS + "(" + CAREER_KEY_ID + " INTEGER,"
				+ OBJECTIVE + " TEXT," + SUMMARY + " TEXT," + "FOREIGN KEY("
				+ CAREER_KEY_ID + ") REFERENCES " + TABLE_USER_PROFILE
				+ "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_CAREER_DETAILS);

		// Education Detail Table Creation
		String CREATE_TABLE_EDUCATION_DETAILS = "CREATE TABLE "
				+ TABLE_EDUCATION_DETAILS + "(" + EDUCATION_KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DEGREE + " TEXT,"
				+ COLLEGE + " TEXT," + UNIVERSITY + " TEXT," + RESULT
				+ " TEXT," + PERCENTAGE + " TEXT," + YEAR_OF_PASSING + " TEXT,"
				+ MONTH + " INTEGER," + YEAR + " INTEGER,"
				+ EDUCATION_FOREIGN_KEY_ID + " INTEGER," + "FOREIGN KEY("
				+ EDUCATION_FOREIGN_KEY_ID + ") REFERENCES "
				+ TABLE_USER_PROFILE + "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_EDUCATION_DETAILS);

		// Skill Detail Table Creation
		String CREATE_TABLE_SKILL_DETAILS = "CREATE TABLE "
				+ TABLE_SKILL_DETAILS + "(" + SKILL_KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + SKILL + " TEXT,"
				+ SKILL_FOREIGN_KEY_ID + " INTEGER," + "FOREIGN KEY("
				+ SKILL_FOREIGN_KEY_ID + ") REFERENCES " + TABLE_USER_PROFILE
				+ "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_SKILL_DETAILS);

		// Project Detail Table Creation
		String CREATE_TABLE_PROJECT_DETAILS = "CREATE TABLE "
				+ TABLE_PROJECT_DETAILS + "(" + PROJECT_KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + TITLE + " TEXT,"
				+ DURATION + " TEXT," + ROLE + " TEXT," + TEAM_SIZE + " TEXT,"
				+ EXPERTISE + " TEXT," + PROJECT_FOREIGN_KEY_ID + " INTEGER,"
				+ MONTH + " INTEGER," + YEAR + " INTEGER," + "FOREIGN KEY("
				+ PROJECT_FOREIGN_KEY_ID + ") REFERENCES " + TABLE_USER_PROFILE
				+ "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_PROJECT_DETAILS);

		// Experience Detail Table Creation
		String CREATE_TABLE_EXPERIENCE_DETAILS = "CREATE TABLE "
				+ TABLE_EXPERIENCE_DETAILS + "(" + EXPERIENCE_KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COMPANY + " TEXT,"
				+ POSITION + " TEXT," + PERIOD + " TEXT," + LOCATION + " TEXT,"
				+ MONTH + " INTEGER," + YEAR + " INTEGER,"
				+ EXPERIENCE_FOREIGN_KEY_ID + " INTEGER," + "FOREIGN KEY("
				+ EXPERIENCE_FOREIGN_KEY_ID + ") REFERENCES "
				+ TABLE_USER_PROFILE + "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_EXPERIENCE_DETAILS);

		// Achievement Detail Table Creation
		String CREATE_TABLE_ACHIEVEMENT_DETAILS = "CREATE TABLE "
				+ TABLE_ACHIEVEMENT_DETAILS + "(" + ACHIEVEMENT_KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_OF_ACHIEVEMENT
				+ " TEXT," + YEAR_OF_ACHIEVEMENT + " TEXT," + WHAT_YOU_ACHIEVED
				+ " TEXT," + ACHIEVEMENT_FOREIGN_KEY_ID + " INTEGER," + MONTH
				+ " INTEGER," + YEAR + " INTEGER," + "FOREIGN KEY("
				+ ACHIEVEMENT_FOREIGN_KEY_ID + ") REFERENCES "
				+ TABLE_USER_PROFILE + "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_ACHIEVEMENT_DETAILS);

		// Reference Detail Table Creation
		String CREATE_TABLE_REFERENCE_DETAILS = "CREATE TABLE "
				+ TABLE_REFERENCE_DETAILS + "(" + REFERENCE_KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + REFERENCE_NAME
				+ " TEXT," + REFERENCE_DETAIL + " TEXT," + REFERENCE_CONTACT
				+ " TEXT," + REFERENCE_EMAIL + " TEXT,"
				+ REFERENCE_FOREIGN_KEY_ID + " INTEGER," + "FOREIGN KEY("
				+ REFERENCE_FOREIGN_KEY_ID + ") REFERENCES "
				+ TABLE_USER_PROFILE + "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_REFERENCE_DETAILS);

		// Signature Detail Table Creation
		String CREATE_TABLE_SIGNATURE = "CREATE TABLE " + TABLE_SIGNATURE + "("
				+ SIGNATURE_KEY_ID + " INTEGER," + PLACE + " TEXT,"
				+ SIGNATURE_DATE + " TEXT," + SINCERELY + " TEXT,"
				+ MAKE_SIGNATURE + " BLOB," + "FOREIGN KEY(" + SIGNATURE_KEY_ID
				+ ") REFERENCES " + TABLE_USER_PROFILE
				+ "(id) ON DELETE CASCADE " + ")";

		db.execSQL(CREATE_TABLE_SIGNATURE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAL_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAREER_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EDUCATION_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILL_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPERIENCE_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHIEVEMENT_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFERENCE_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGNATURE);
		// Create tables again
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			// Enable foreign key constraints
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}

	// Add User Profile Method
	public int addUser(User user) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USER_NAME, user.getName()); // User Name
		values.put(IMAGE_PROFILE, user.getImgProfile());
		return (int) db.insert(TABLE_USER_PROFILE, null, values);
	}

	// Add User Personal Detail Method
	public int addPersonalDetail(PersonalDetailPojo pdp) {

		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(PERSON_KEY_ID, pdp.getId());
		values.put(PERSON_NAME, pdp.getPersonName());
		values.put(GENDER, pdp.getGender());
		values.put(EMAIL, pdp.getEmail());
		values.put(MOBILE, pdp.getMobile());
		values.put(ADDRESS, pdp.getAddress());
		values.put(LANGUAGE, pdp.getLanguage());
		values.put(BIRTHDATE, pdp.getBirthdate());

		return (int) db.insert(TABLE_PERSONAL_DETAILS, null, values);
	}

	// Add User Career Detail Method
	public int addCareerDetail(CareerPojo career) {
		db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CAREER_KEY_ID, career.getId());
		values.put(OBJECTIVE, career.getObjective());
		values.put(SUMMARY, career.getSummary());

		return (int) db.insert(TABLE_CAREER_DETAILS, null, values);

	}

	// Add User Education Detail Method
	public int addEducationDetail(EducationPojo education) {
		db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(DEGREE, education.getDegree());
		values.put(COLLEGE, education.getCollege());
		values.put(UNIVERSITY, education.getUniversity());
		values.put(RESULT, education.getResult());
		values.put(PERCENTAGE, education.getPercentage());
		values.put(YEAR_OF_PASSING, education.getYearofpassing());
		values.put(EDUCATION_FOREIGN_KEY_ID, education.getId());
		values.put(MONTH, education.getMonth());
		values.put(YEAR, education.getYear());

		return (int) db.insert(TABLE_EDUCATION_DETAILS, null, values);

	}

	// Add User Skill Method
	public int addSkill(SkillPojo skillp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SKILL, skillp.getSkill());
		values.put(SKILL_FOREIGN_KEY_ID, skillp.getId());

		return (int) db.insert(TABLE_SKILL_DETAILS, null, values);

	}

	// Add User Project Detail Method
	public int addProjectDetail(ProjectPojo pp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(TITLE, pp.getTitle());
		values.put(DURATION, pp.getDuration());
		values.put(ROLE, pp.getRole());
		values.put(TEAM_SIZE, pp.getTeamSize());
		values.put(EXPERTISE, pp.getExpertise());
		values.put(PROJECT_FOREIGN_KEY_ID, pp.getId());
		values.put(MONTH, pp.getMonth());
		values.put(YEAR, pp.getYear());
		return (int) db.insert(TABLE_PROJECT_DETAILS, null, values);

	}

	// Add User Experience Detail Method
	public int addExperienceDetail(ExperiencePojo exp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(COMPANY, exp.getCompany());
		values.put(POSITION, exp.getPosition());
		values.put(PERIOD, exp.getPeriod());
		values.put(MONTH, exp.getMonth());
		values.put(YEAR, exp.getYear());
		values.put(LOCATION, exp.getLocation());
		values.put(EXPERIENCE_FOREIGN_KEY_ID, exp.getId());

		return (int) db.insert(TABLE_EXPERIENCE_DETAILS, null, values);

	}

	// Add User Achievement Detail Method
	public int addAchievementDetail(AchievementDetailPojo adp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(NAME_OF_ACHIEVEMENT, adp.getName_of_achievement());
		values.put(YEAR_OF_ACHIEVEMENT, adp.getYear_of_achievement());
		values.put(WHAT_YOU_ACHIEVED, adp.getWhat_you_achieved());
		values.put(ACHIEVEMENT_FOREIGN_KEY_ID, adp.getId());
		values.put(MONTH, adp.getMonth());
		values.put(YEAR, adp.getYear());

		return (int) db.insert(TABLE_ACHIEVEMENT_DETAILS, null, values);

	}

	// Add User Reference Detail Method
	public int addReferenceDetail(ReferenceDetailPojo rdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(REFERENCE_NAME, rdp.getRefName());
		values.put(REFERENCE_DETAIL, rdp.getRefDetail());
		values.put(REFERENCE_CONTACT, rdp.getRefContact());
		values.put(REFERENCE_EMAIL, rdp.getRefEmail());
		values.put(REFERENCE_FOREIGN_KEY_ID, rdp.getId());

		return (int) db.insert(TABLE_REFERENCE_DETAILS, null, values);

	}

	// Add User Signature Method
	public int addSignature(SignaturePojo sp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SIGNATURE_KEY_ID, sp.getId());
		values.put(PLACE, sp.getPlace());
		values.put(SIGNATURE_DATE, sp.getDt());
		values.put(SINCERELY, sp.getSincerely());
		values.put(MAKE_SIGNATURE, sp.getSign());
		return (int) db.insert(TABLE_SIGNATURE, null, values);

	}

	//

	public int updateUserProfile(User user) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, user.getId());
		values.put(IMAGE_PROFILE, user.getImgProfile());
		return (int) db.update(TABLE_USER_PROFILE, values, KEY_ID + " = ?",
				new String[] { Integer.toString(user.getId()) });
	}

	// Update User Personal Detail Method
	public int updatePersonalDetail(PersonalDetailPojo pdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PERSON_KEY_ID, pdp.getId());
		values.put(PERSON_NAME, pdp.getPersonName());
		values.put(GENDER, pdp.getGender());
		values.put(EMAIL, pdp.getEmail());
		values.put(MOBILE, pdp.getMobile());
		values.put(ADDRESS, pdp.getAddress());
		values.put(LANGUAGE, pdp.getLanguage());
		values.put(BIRTHDATE, pdp.getBirthdate());

		// updating row
		return db.update(TABLE_PERSONAL_DETAILS, values,
				PERSON_KEY_ID + " = ?",
				new String[] { Integer.toString(pdp.getId()) });
	}

	// Update User Career Detail Method
	public int updateCareerDetail(CareerPojo cp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CAREER_KEY_ID, cp.getId());
		values.put(OBJECTIVE, cp.getObjective());
		values.put(SUMMARY, cp.getSummary());

		// updating row
		return db.update(TABLE_CAREER_DETAILS, values, CAREER_KEY_ID + " = ?",
				new String[] { Integer.toString(cp.getId()) });
	}

	// Update User Education Detail Method
	public int updateEducationDetail(EducationPojo pdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(EDUCATION_KEY_ID, pdp.getId());
		values.put(DEGREE, pdp.getDegree());
		values.put(COLLEGE, pdp.getCollege());
		values.put(UNIVERSITY, pdp.getUniversity());
		values.put(RESULT, pdp.getResult());
		values.put(YEAR_OF_PASSING, pdp.getYearofpassing());
		values.put(MONTH, pdp.getMonth());
		values.put(YEAR, pdp.getYear());

		// updating row
		return db.update(TABLE_EDUCATION_DETAILS, values, EDUCATION_KEY_ID
				+ " = ?", new String[] { Integer.toString(pdp.getId()) });
	}

	// Update user skill detail
	public int updateSkill(SkillPojo pdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SKILL_KEY_ID, pdp.getId());
		values.put(SKILL, pdp.getSkill());

		// updating row
		return db.update(TABLE_SKILL_DETAILS, values, SKILL_KEY_ID + " = ?",
				new String[] { Integer.toString(pdp.getId()) });
	}

	// Update User Project Detail Method
	public int updateProjectDetail(ProjectPojo pdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PROJECT_KEY_ID, pdp.getId());
		values.put(TITLE, pdp.getTitle());
		values.put(DURATION, pdp.getDuration());
		values.put(ROLE, pdp.getRole());
		values.put(TEAM_SIZE, pdp.getTeamSize());
		values.put(EXPERTISE, pdp.getExpertise());
		values.put(MONTH, pdp.getMonth());
		values.put(YEAR, pdp.getYear());
		// updating row
		return db.update(TABLE_PROJECT_DETAILS, values,
				PROJECT_KEY_ID + " = ?",
				new String[] { Integer.toString(pdp.getId()) });
	}

	// Update User Experience Detail Method
	public int updateExperienceDetail(ExperiencePojo pdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(EXPERIENCE_KEY_ID, pdp.getId());
		values.put(COMPANY, pdp.getCompany());
		values.put(POSITION, pdp.getPosition());
		values.put(PERIOD, pdp.getPeriod());
		values.put(LOCATION, pdp.getLocation());
		values.put(MONTH, pdp.getMonth());
		values.put(YEAR, pdp.getYear());

		// updating row
		return db.update(TABLE_EXPERIENCE_DETAILS, values, EXPERIENCE_KEY_ID
				+ " = ?", new String[] { Integer.toString(pdp.getId()) });
	}

	// Update User Achievement Detail Method
	public int updateAchievementDetail(AchievementDetailPojo pdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(ACHIEVEMENT_KEY_ID, pdp.getId());
		values.put(NAME_OF_ACHIEVEMENT, pdp.getName_of_achievement());
		values.put(YEAR_OF_ACHIEVEMENT, pdp.getYear_of_achievement());
		values.put(WHAT_YOU_ACHIEVED, pdp.getWhat_you_achieved());
		values.put(MONTH, pdp.getMonth());
		values.put(YEAR, pdp.getYear());

		// updating row
		return db.update(TABLE_ACHIEVEMENT_DETAILS, values, ACHIEVEMENT_KEY_ID
				+ " = ?", new String[] { Integer.toString(pdp.getId()) });
	}

	// Update User Reference Detail Method
	public int updateReferenceDetail(ReferenceDetailPojo pdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(REFERENCE_KEY_ID, pdp.getId());
		values.put(REFERENCE_NAME, pdp.getRefName());
		values.put(REFERENCE_DETAIL, pdp.getRefDetail());
		values.put(REFERENCE_CONTACT, pdp.getRefContact());
		values.put(REFERENCE_EMAIL, pdp.getRefEmail());

		// updating row
		return db.update(TABLE_REFERENCE_DETAILS, values, REFERENCE_KEY_ID
				+ " = ?", new String[] { Integer.toString(pdp.getId()) });
	}

	public int updateSignatureDetail(SignaturePojo sdp) {
		db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SIGNATURE_KEY_ID, sdp.getId());
		values.put(PLACE, sdp.getPlace());
		values.put(SIGNATURE_DATE, sdp.getDt());
		values.put(SINCERELY, sdp.getSincerely());
		values.put(MAKE_SIGNATURE, sdp.getSign());

		// updating row
		return db.update(TABLE_SIGNATURE, values, SIGNATURE_KEY_ID + " = ?",
				new String[] { Integer.toString(sdp.getId()) });
	}

	// Retrieving User Name For Profile Creation
	public List<String> getAllData() {

		db = this.getReadableDatabase();

		String query = "select name from " + TABLE_USER_PROFILE;
		List<String> rdata1 = new ArrayList<String>();
		Cursor cursor = null;
		cursor = db.rawQuery(query, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				rdata1.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return rdata1;
	}

	// delete data from listview
	public void deletedata(int id) {
		db = this.getWritableDatabase();
		db.delete(DatabaseHandler.TABLE_USER_PROFILE, DatabaseHandler.KEY_ID
				+ "=?", new String[] { String.valueOf(id) });
	}

	// delete education data from listview
	public void deleteEducationData(int id) {
		db = this.getWritableDatabase();
		db.delete(DatabaseHandler.TABLE_EDUCATION_DETAILS,
				DatabaseHandler.EDUCATION_KEY_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	// delete skill data from listview
	public void deleteSkillData(int id) {
		db = this.getWritableDatabase();
		db.delete(DatabaseHandler.TABLE_SKILL_DETAILS,
				DatabaseHandler.SKILL_KEY_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	// delete Project data from listview
	public void deleteProjectData(int id) {
		db = this.getWritableDatabase();
		db.delete(DatabaseHandler.TABLE_PROJECT_DETAILS,
				DatabaseHandler.PROJECT_KEY_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	// delete Experience data from listview
	public void deleteExperienceData(int id) {
		db = this.getWritableDatabase();
		db.delete(DatabaseHandler.TABLE_EXPERIENCE_DETAILS,
				DatabaseHandler.EXPERIENCE_KEY_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	// delete Achievement data from listview
	public void deleteAchievementData(int id) {
		db = this.getWritableDatabase();
		db.delete(DatabaseHandler.TABLE_ACHIEVEMENT_DETAILS,
				DatabaseHandler.ACHIEVEMENT_KEY_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	// delete Reference data from listview
	public void deleteReferenceData(int id) {
		db = this.getWritableDatabase();
		db.delete(DatabaseHandler.TABLE_REFERENCE_DETAILS,
				DatabaseHandler.REFERENCE_KEY_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	// get all personal Detail
	public List<PersonalDetailPojo> getAllPersonalDetail(int id) {
		List<PersonalDetailPojo> pdata = new ArrayList<PersonalDetailPojo>();
		db = this.getWritableDatabase();

		String query = "select personName,gender,email,mobile,address,language,birthdate from "
				+ TABLE_PERSONAL_DETAILS + " where pid=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					PersonalDetailPojo pd = new PersonalDetailPojo();
					pd.setPersonName(cursor.getString(0));
					pd.setGender(cursor.getString(1));
					pd.setEmail(cursor.getString(2));
					pd.setMobile(cursor.getString(3));
					pd.setAddress(cursor.getString(4));
					pd.setLanguage(cursor.getString(5));
					pd.setBirthdate(cursor.getString(6));
					pdata.add(pd);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// get all Career Detail
	public List<CareerPojo> getAllCareer(int idclick) {
		// TODO Auto-generated method stub
		List<CareerPojo> careerdata = new ArrayList<CareerPojo>();
		db = this.getReadableDatabase();

		String query = "select objective,summary from " + TABLE_CAREER_DETAILS
				+ " where cid=" + idclick;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					CareerPojo careerPojo = new CareerPojo();
					careerPojo.setObjective(cursor.getString(0));
					careerPojo.setSummary(cursor.getString(1));
					careerdata.add(careerPojo);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return careerdata;
	}

	// get all Education Detail
	public List<EducationPojo> getAllEducationDetail(int id) {
		List<EducationPojo> pdata = new ArrayList<EducationPojo>();
		db = this.getWritableDatabase();

		String query = "select degree,college,university,result,percentage,yearofpassing from "
				+ TABLE_EDUCATION_DETAILS + " where eid=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					EducationPojo edu = new EducationPojo();
					edu.setDegree(cursor.getString(cursor
							.getColumnIndex("degree")));
					edu.setCollege(cursor.getString(cursor
							.getColumnIndex("college")));
					edu.setUniversity(cursor.getString(cursor
							.getColumnIndex("university")));
					edu.setResult(cursor.getString(cursor
							.getColumnIndex("result")));
					edu.setPercentage(cursor.getString(cursor
							.getColumnIndex("percentage")));
					edu.setYearofpassing(cursor.getString(cursor
							.getColumnIndex("yearofpassing")));
					pdata.add(edu);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// get all Skill Detail
	public List<SkillPojo> getAllSkillDetail(int id) {
		List<SkillPojo> pdata = new ArrayList<SkillPojo>();
		db = this.getWritableDatabase();

		String query = "select skill from " + TABLE_SKILL_DETAILS
				+ " where sid=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					SkillPojo edu = new SkillPojo();
					edu.setSkill(cursor.getString(cursor
							.getColumnIndex("skill")));
					pdata.add(edu);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// get all Project Detail
	public List<ProjectPojo> getAllProjetDetail(int id) {
		List<ProjectPojo> pdata = new ArrayList<ProjectPojo>();
		db = this.getWritableDatabase();

		String query = "select title,duration,role,team_size,expertise from "
				+ TABLE_PROJECT_DETAILS + " where proj_id=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					ProjectPojo edu = new ProjectPojo();
					edu.setTitle(cursor.getString(cursor
							.getColumnIndex("title")));
					edu.setDuration(cursor.getString(cursor
							.getColumnIndex("duration")));
					edu.setRole(cursor.getString(cursor.getColumnIndex("role")));
					edu.setTeamSize(cursor.getString(cursor
							.getColumnIndex("team_size")));
					edu.setExpertise(cursor.getString(cursor
							.getColumnIndex("expertise")));
					pdata.add(edu);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// get all Experience Detail
	public List<ExperiencePojo> getAllExperienceDetail(int id) {
		List<ExperiencePojo> pdata = new ArrayList<ExperiencePojo>();
		db = this.getWritableDatabase();

		String query = "select company,position,peiod,location from "
				+ TABLE_EXPERIENCE_DETAILS + " where exp_id=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					ExperiencePojo edu = new ExperiencePojo();
					edu.setCompany(cursor.getString(cursor
							.getColumnIndex("company")));
					edu.setPosition(cursor.getString(cursor
							.getColumnIndex("position")));
					edu.setPeriod(cursor.getString(cursor
							.getColumnIndex("peiod")));
					edu.setLocation(cursor.getString(cursor
							.getColumnIndex("location")));
					pdata.add(edu);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// get all Achievement Detail
	public List<AchievementDetailPojo> getAllAchievementDetail(int id) {
		List<AchievementDetailPojo> pdata = new ArrayList<AchievementDetailPojo>();
		db = this.getWritableDatabase();

		String query = "select name_of_achievement,year_of_achievement,what_you_achieved from "
				+ TABLE_ACHIEVEMENT_DETAILS + " where aid=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					AchievementDetailPojo edu = new AchievementDetailPojo();
					edu.setName_of_achievement(cursor.getString(cursor
							.getColumnIndex("name_of_achievement")));
					edu.setYear_of_achievement(cursor.getString(cursor
							.getColumnIndex("year_of_achievement")));
					edu.setWhat_you_achieved(cursor.getString(cursor
							.getColumnIndex("what_you_achieved")));
					pdata.add(edu);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// get all REFERENCE Detail
	public List<ReferenceDetailPojo> getAllReferenceDetail(int id) {
		List<ReferenceDetailPojo> pdata = new ArrayList<ReferenceDetailPojo>();
		db = this.getWritableDatabase();

		String query = "select reference_name,reference_detail,reference_contact,reference_email from "
				+ TABLE_REFERENCE_DETAILS + " where ref_id=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					ReferenceDetailPojo edu = new ReferenceDetailPojo();
					edu.setRefName(cursor.getString(cursor
							.getColumnIndex("reference_name")));
					edu.setRefDetail(cursor.getString(cursor
							.getColumnIndex("reference_detail")));
					edu.setRefContact(cursor.getString(cursor
							.getColumnIndex("reference_contact")));
					edu.setRefEmail(cursor.getString(cursor
							.getColumnIndex("reference_email")));
					pdata.add(edu);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// get all Education Detail for PDF
	public ArrayList<EducationPojo> getAllEducationDetailPdf(int id) {
		ArrayList<EducationPojo> pdata = new ArrayList<EducationPojo>();
		db = this.getReadableDatabase();

		String query = "select degree,college,university,result,percentage,yearofpassing from "
				+ TABLE_EDUCATION_DETAILS
				+ " where id="
				+ id
				+ " order by year ASC, month ASC";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {

			do {
				EducationPojo edu = new EducationPojo();
				edu.setDegree(cursor.getString(cursor.getColumnIndex("degree")));
				edu.setCollege(cursor.getString(cursor
						.getColumnIndex("college")));
				edu.setUniversity(cursor.getString(cursor
						.getColumnIndex("university")));
				edu.setResult(cursor.getString(cursor.getColumnIndex("result")));
				edu.setPercentage(cursor.getString(cursor
						.getColumnIndex("percentage")));
				edu.setYearofpassing(cursor.getString(cursor
						.getColumnIndex("yearofpassing")));
				pdata.add(edu);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// Retrieving Signature for pdf
	public ArrayList<SigImage> getAllUserSignature(int id) {

		db = this.getReadableDatabase();

		String selectQuery = "select make_signature from signature  where sig_id= "
				+ id;

		Cursor cursor = null;
		cursor = db.rawQuery(selectQuery, null);

		ArrayList<SigImage> result = new ArrayList<SigImage>();

		if (cursor != null && cursor.moveToNext()) {
			SigImage u = new SigImage();
			byte[] data = cursor.getBlob(cursor
					.getColumnIndex("make_signature"));
			Log.i("image Signature", String.valueOf(data));
			u.setSign(data);
			result.add(u);

		}
		cursor.close();
		return result;
	}

	// get all Skill Detail for PDF
	public ArrayList<SkillPojo> getAllSkillDetailPdf(int id) {
		ArrayList<SkillPojo> skdata = new ArrayList<SkillPojo>();
		db = this.getReadableDatabase();

		String query = "select skill from " + TABLE_SKILL_DETAILS
				+ " where id=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {

			do {
				SkillPojo sk = new SkillPojo();
				sk.setSkill(cursor.getString(cursor.getColumnIndex("skill")));

				skdata.add(sk);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return skdata;
	}

	// get all Project Detail for PDF
	public ArrayList<ProjectPojo> getAllProjectDetailPdf(int id) {
		ArrayList<ProjectPojo> pdata = new ArrayList<ProjectPojo>();
		db = this.getReadableDatabase();

		String query = "select title,duration,role,team_size,expertise from "
				+ TABLE_PROJECT_DETAILS + " where id=" + id
				+ " order by year ASC, month ASC";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					ProjectPojo edu = new ProjectPojo();
					edu.setTitle(cursor.getString(cursor
							.getColumnIndex("title")));
					edu.setDuration(cursor.getString(cursor
							.getColumnIndex("duration")));
					edu.setRole(cursor.getString(cursor.getColumnIndex("role")));
					edu.setTeamSize(cursor.getString(cursor
							.getColumnIndex("team_size")));
					edu.setExpertise(cursor.getString(cursor
							.getColumnIndex("expertise")));
					pdata.add(edu);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return pdata;
	}

	// get all Experience Detail for PDF
	public ArrayList<ExperiencePojo> getAllExperienceDetailPdf(int id) {
		ArrayList<ExperiencePojo> edata = new ArrayList<ExperiencePojo>();
		db = this.getReadableDatabase();

		String query = "select company,position,peiod,location from "
				+ TABLE_EXPERIENCE_DETAILS + " where id=" + id
				+ " order by year ASC, month ASC";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					ExperiencePojo ep = new ExperiencePojo();
					ep.setCompany(cursor.getString(cursor
							.getColumnIndex("company")));
					ep.setPosition(cursor.getString(cursor
							.getColumnIndex("position")));
					ep.setPeriod(cursor.getString(cursor
							.getColumnIndex("peiod")));
					ep.setLocation(cursor.getString(cursor
							.getColumnIndex("location")));
					edata.add(ep);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return edata;
	}

	// get all Achievement Detail for PDF
	public ArrayList<AchievementDetailPojo> getAllAchievementDetailPdf(int id) {
		ArrayList<AchievementDetailPojo> edata = new ArrayList<AchievementDetailPojo>();
		db = this.getReadableDatabase();

		String query = "select name_of_achievement,year_of_achievement,what_you_achieved from "
				+ TABLE_ACHIEVEMENT_DETAILS
				+ " where id="
				+ id
				+ " order by year ASC, month ASC";
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					AchievementDetailPojo ep = new AchievementDetailPojo();
					ep.setName_of_achievement(cursor.getString(cursor
							.getColumnIndex("name_of_achievement")));
					ep.setYear_of_achievement(cursor.getString(cursor
							.getColumnIndex("year_of_achievement")));
					ep.setWhat_you_achieved(cursor.getString(cursor
							.getColumnIndex("what_you_achieved")));
					edata.add(ep);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return edata;
	}

	public ArrayList<User> getUserpdf(int id) {

		db = this.getReadableDatabase();

		String selectQuery = "select * from user_profile where id=" + id;

		Cursor cursor = null;
		cursor = db.rawQuery(selectQuery, null);

		ArrayList<User> result = new ArrayList<User>();

		if (cursor != null && cursor.moveToNext()) {
			do {
				User u = new User();
				u.setName(cursor.getString(cursor.getColumnIndex("name")));
				byte[] data = cursor.getBlob(cursor.getColumnIndex("img"));
				Log.i("image user", String.valueOf(data));
				u.setImgProfile(data);
				result.add(u);
			} while (cursor.moveToNext());

		}
		cursor.close();
		return result;
	}

	// get all Reference Detail for PDF
	public ArrayList<ReferenceDetailPojo> getAllReferenceDetailPdf(int id) {
		ArrayList<ReferenceDetailPojo> refdata = new ArrayList<ReferenceDetailPojo>();
		db = this.getReadableDatabase();

		String query = "select reference_name,reference_detail,reference_contact,reference_email from "
				+ TABLE_REFERENCE_DETAILS + " where id=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					ReferenceDetailPojo rp = new ReferenceDetailPojo();
					rp.setRefName(cursor.getString(cursor
							.getColumnIndex("reference_name")));
					rp.setRefDetail(cursor.getString(cursor
							.getColumnIndex("reference_detail")));
					rp.setRefContact(cursor.getString(cursor
							.getColumnIndex("reference_contact")));
					rp.setRefEmail(cursor.getString(cursor
							.getColumnIndex("reference_email")));
					refdata.add(rp);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return refdata;
	}

	// get all Signature Detail for PDF
	public ArrayList<SignaturePojo> getAllSignatureDetailPdf(int id) {
		ArrayList<SignaturePojo> refdata = new ArrayList<SignaturePojo>();
		db = this.getReadableDatabase();

		String query = "select place,signature_date,sincerely,make_signature from "
				+ TABLE_SIGNATURE + " where sig_id=" + id;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor != null && cursor.moveToFirst()) {
			if (cursor.moveToFirst()) {
				do {
					SignaturePojo sp = new SignaturePojo();
					sp.setPlace(cursor.getString(cursor.getColumnIndex("place")));
					sp.setDt(cursor.getString(cursor
							.getColumnIndex("signature_date")));
					sp.setSincerely(cursor.getString(cursor
							.getColumnIndex("sincerely")));
					byte[] data1 = cursor.getBlob(cursor
							.getColumnIndex("make_signature"));
					sp.setSign(data1);
					refdata.add(sp);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return refdata;
	}
}