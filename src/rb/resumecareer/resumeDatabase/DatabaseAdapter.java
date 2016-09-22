package rb.resumecareer.resumeDatabase;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {
	SQLiteDatabase database;
	DatabaseHandler dbHelper;

	public DatabaseAdapter(Context context) {
		dbHelper = new DatabaseHandler(context);

	}

	public void open() {
		database = dbHelper.getReadableDatabase();
	}

	public void close() {

		database.close();
		dbHelper.close();
	}

	// Retrieving all User name
	public ArrayList<String> getAllLabels() {

		Cursor mcursor = database.rawQuery("SELECT * FROM "
				+ DatabaseHandler.TABLE_USER_PROFILE, null);

		ArrayList<String> result = new ArrayList<String>();

		if (mcursor != null && mcursor.moveToNext()) {
			do {
				result.add(mcursor.getString(0));
				result.add(mcursor.getString(1));
			} while (mcursor.moveToNext());

		}
		mcursor.close();
		database.close();
		return result;
	}

	// Retrieving all Education
	public ArrayList<String> getAllEducationData(int id) {

		Cursor mcursor = database.rawQuery("SELECT * FROM "
				+ DatabaseHandler.TABLE_EDUCATION_DETAILS + " where id = ? order by year ASC, month ASC",
				new String[] { String.valueOf(id) });

		ArrayList<String> result = new ArrayList<String>();

		if (mcursor != null && mcursor.moveToNext()) {
			do {
				result.add(mcursor.getString(mcursor.getColumnIndex("id")));
				result.add(mcursor.getString(mcursor.getColumnIndex("eid")));
				result.add(mcursor.getString(mcursor.getColumnIndex("degree")));
			} while (mcursor.moveToNext());

		}
		mcursor.close();
		database.close();
		return result;
	}

	// Retrieving all Project
	public ArrayList<String> getAllProjectData(int id) {

		Cursor mcursor = database.rawQuery("SELECT * FROM "
				+ DatabaseHandler.TABLE_PROJECT_DETAILS + " where id = ? order by year ASC, month ASC",
				new String[] { String.valueOf(id) });

		ArrayList<String> result = new ArrayList<String>();

		if (mcursor != null && mcursor.moveToNext()) {
			do {
				result.add(mcursor.getString(mcursor.getColumnIndex("id")));
				result.add(mcursor.getString(mcursor.getColumnIndex("proj_id")));
				result.add(mcursor.getString(mcursor.getColumnIndex("title")));
			} while (mcursor.moveToNext());

		}
		mcursor.close();
		database.close();
		return result;
	}

	// Retrieving all Skill
	public ArrayList<String> getAllSkillData(int id) {

		Cursor mcursor = database.rawQuery("SELECT * FROM "
				+ DatabaseHandler.TABLE_SKILL_DETAILS + " where id = ?",
				new String[] { String.valueOf(id) });

		ArrayList<String> result = new ArrayList<String>();

		if (mcursor != null && mcursor.moveToNext()) {
			do {
				result.add(mcursor.getString(mcursor.getColumnIndex("id")));
				result.add(mcursor.getString(mcursor.getColumnIndex("sid")));
				result.add(mcursor.getString(mcursor.getColumnIndex("skill")));
			} while (mcursor.moveToNext());

		}
		mcursor.close();
		database.close();
		return result;
	}

	// Retrieving all Experience
	public ArrayList<String> getAllExperienceData(int id) {

		Cursor mcursor = database.rawQuery("SELECT * FROM "
				+ DatabaseHandler.TABLE_EXPERIENCE_DETAILS + " where id = ? order by year ASC,month ASC ",
				new String[] { String.valueOf(id) });

		ArrayList<String> result = new ArrayList<String>();

		if (mcursor != null && mcursor.moveToNext()) {
			do {
				result.add(mcursor.getString(mcursor.getColumnIndex("id")));
				result.add(mcursor.getString(mcursor.getColumnIndex("exp_id")));
				result.add(mcursor.getString(mcursor.getColumnIndex("company")));
			} while (mcursor.moveToNext());

		}
		mcursor.close();
		database.close();
		return result;
	}

	// Retrieving all Achievement
	public ArrayList<String> getAllAchievementData(int id) {

		Cursor mcursor = database.rawQuery("SELECT * FROM "
				+ DatabaseHandler.TABLE_ACHIEVEMENT_DETAILS + " where id = ? order by year ASC,month ASC",
				new String[] { String.valueOf(id) });

		ArrayList<String> result = new ArrayList<String>();

		if (mcursor != null && mcursor.moveToNext()) {
			do {
				result.add(mcursor.getString(mcursor.getColumnIndex("id")));
				result.add(mcursor.getString(mcursor.getColumnIndex("aid")));
				result.add(mcursor.getString(mcursor.getColumnIndex("name_of_achievement")));
			} while (mcursor.moveToNext());

		}
		mcursor.close();
		database.close();
		return result;
	}
	
	// Retrieving all Reference
		public ArrayList<String> getAllReferenceData(int id) {

			Cursor mcursor = database.rawQuery("SELECT * FROM "
					+ DatabaseHandler.TABLE_REFERENCE_DETAILS + " where id = ?",
					new String[] { String.valueOf(id) });

			ArrayList<String> result = new ArrayList<String>();

			if (mcursor != null && mcursor.moveToNext()) {
				do {
					result.add(mcursor.getString(mcursor.getColumnIndex("id")));
					result.add(mcursor.getString(mcursor.getColumnIndex("ref_id")));
					result.add(mcursor.getString(mcursor.getColumnIndex("reference_name")));
				} while (mcursor.moveToNext());

			}
			mcursor.close();
			database.close();
			return result;
		}
}