package rb.resumecareer.resumePojo;

public class SignaturePojo {

	int id;
	String place,dt,sincerely;
	byte[] sign;
	public SignaturePojo() {
		super();
	}
	public SignaturePojo(int id, String place, String dt, String sincerely,
			byte[] sign) {
		super();
		this.id = id;
		this.place = place;
		this.dt = dt;
		this.sincerely = sincerely;
		this.sign = sign;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getSincerely() {
		return sincerely;
	}
	public void setSincerely(String sincerely) {
		this.sincerely = sincerely;
	}
	public byte[] getSign() {
		return sign;
	}
	public void setSign(byte[] sign) {
		this.sign = sign;
	}
	
}
