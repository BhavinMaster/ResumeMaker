package rb.resumecareer.resumePojo;

public class SkillPojo {

	int id,sid;
	String skill;
	public SkillPojo() {
		super();
	}
	public SkillPojo(int sid, String skill,int id) {
		super();
		this.sid = sid;
		this.skill = skill;
		this.id=id;
	}
	
	public SkillPojo(String skill,int id) {
		super();
		this.id = id;
		this.skill = skill;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	
}
