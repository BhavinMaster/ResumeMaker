package rb.resumecareer.resumePojo;

public class SigImage {
	int id;
	byte[] sign;
	public SigImage(int id, byte[] sign) {
		super();
		this.id = id;
		this.sign = sign;
	}
	public SigImage() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getSign() {
		return sign;
	}
	public void setSign(byte[] sign) {
		this.sign = sign;
	}
	
}
