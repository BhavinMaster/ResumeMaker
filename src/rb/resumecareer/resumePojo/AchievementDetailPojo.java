package rb.resumecareer.resumePojo;

public class AchievementDetailPojo {

	int id,aid,month,year;
	String name_of_achievement,year_of_achievement,what_you_achieved;
	public AchievementDetailPojo() {
		super();
	}
	public AchievementDetailPojo(int aid, String name_of_achievement,
			String year_of_achievement, String what_you_achieved,int id,int m ,int y) {
		super();
		this.aid=aid;
		this.id = id;
		this.name_of_achievement = name_of_achievement;
		this.year_of_achievement = year_of_achievement;
		this.what_you_achieved = what_you_achieved;
		this.month =m;
		this.year = y;
	}
	
	
	public AchievementDetailPojo(String name_of_achievement,
			String year_of_achievement, String what_you_achieved,int id,int m , int y) {
		super();
		this.id = id;
		this.name_of_achievement = name_of_achievement;
		this.year_of_achievement = year_of_achievement;
		this.what_you_achieved = what_you_achieved;
		this.month = m;
		this.year = y;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_of_achievement() {
		return name_of_achievement;
	}
	public void setName_of_achievement(String name_of_achievement) {
		this.name_of_achievement = name_of_achievement;
	}
	public String getYear_of_achievement() {
		return year_of_achievement;
	}
	public void setYear_of_achievement(String year_of_achievement) {
		this.year_of_achievement = year_of_achievement;
	}
	public String getWhat_you_achieved() {
		return what_you_achieved;
	}
	public void setWhat_you_achieved(String what_you_achieved) {
		this.what_you_achieved = what_you_achieved;
	}
	
}
