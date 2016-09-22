package rb.resumecareer.resumePojo;

public class ExperiencePojo {

	int id,exp_id;
	String company,position,period,location;
	int month,year;
	
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
	public ExperiencePojo() {
		super();
	}
	public ExperiencePojo(int exp_id, String company, String position,
			String period, String location,int id, int m ,int y) {
		super();
		this.exp_id=exp_id;
		this.id = id;
		this.month = m;
		this.year = y;
		this.company = company;
		this.position = position;
		this.period = period;
		this.location = location;
	}
	
	public ExperiencePojo(String company, String position,
			String period, String location,int id,int m ,int y) {
		super();
		this.id = id;
		this.company = company;
		this.position = position;
		this.period = period;
		this.location = location;
		this.month = m;
		this.year = y;
	
	}
	public int getExp_id() {
		return exp_id;
	}
	public void setExp_id(int exp_id) {
		this.exp_id = exp_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
