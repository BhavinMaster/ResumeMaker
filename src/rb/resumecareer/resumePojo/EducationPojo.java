package rb.resumecareer.resumePojo;

public class EducationPojo {

	int id, eid, month, year;
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

	String degree, college, university, result, yearofpassing, percentage;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public EducationPojo() {
		super();
	}

	public EducationPojo(String degree, String college, String university,
			String result, String percentage, String yearofpassing, int id, int m ,int y) {
		super();
		this.id = id;
		this.degree = degree;
		this.college = college;
		this.university = university;
		this.percentage = percentage;
		this.result = result;
		this.yearofpassing = yearofpassing;
		this.month = m;
		this.year = y;
	}

	public EducationPojo(int eid,String degree, String college, String university,
			String result, String percentage, String yearofpassing, int id,int m , int y) {
		super();
		this.eid=eid;
		this.id = id;
		this.degree = degree;
		this.college = college;
		this.university = university;
		this.percentage = percentage;
		this.result = result;
		this.yearofpassing = yearofpassing;
		this.month = m;
		this.year = y;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getYearofpassing() {
		return yearofpassing;
	}

	public void setYearofpassing(String yearofpassing) {
		this.yearofpassing = yearofpassing;
	}

}
