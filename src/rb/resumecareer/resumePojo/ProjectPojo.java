package rb.resumecareer.resumePojo;

public class ProjectPojo {

	int id,proj_id,month,year;
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
	String title,duration,role,teamSize,expertise;
	public ProjectPojo() {
		super();
	}
	public ProjectPojo(int proj_id, String title, String duration, String role,
			String teamSize, String expertise,int id,int m ,int y) {
		super();
		this.proj_id=proj_id;
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.role = role;
		this.teamSize = teamSize;
		this.expertise = expertise;
		this.month = m;
		this.year = y;
	}
	
	
	public ProjectPojo(String title, String duration, String role,
			String teamSize, String expertise,int id,int m ,int y) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.role = role;
		this.teamSize = teamSize;
		this.expertise = expertise;
		this.month = m;
		this.year = y;
	}
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(String teamSize) {
		this.teamSize = teamSize;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	
	
}
