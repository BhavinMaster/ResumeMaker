package rb.resumecareer.resumePojo;

public class CareerPojo {
	
	int id;
	String objective,summary;
	public CareerPojo() {
		super();
	}
	public CareerPojo(int id, String objective, String summary) {
		super();
		this.id = id;
		this.objective = objective;
		this.summary = summary;
	}
	
	public CareerPojo(String objective, String summary,int id) {
		super();
		this.id = id;
		this.objective = objective;
		this.summary = summary;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
