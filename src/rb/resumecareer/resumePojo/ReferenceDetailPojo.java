package rb.resumecareer.resumePojo;

public class ReferenceDetailPojo {

	int id,ref_id;
	String refName,refDetail,refContact,refEmail;
	public ReferenceDetailPojo() {
		super();
	}
	public ReferenceDetailPojo(int ref_id, String refName, String refDetail,
			String refContact, String refEmail,int id) {
		super();
		this.ref_id=ref_id;
		this.id = id;
		this.refName = refName;
		this.refDetail = refDetail;
		this.refContact = refContact;
		this.refEmail = refEmail;
	}
	
	public ReferenceDetailPojo(String refName, String refDetail,
			String refContact, String refEmail,int id) {
		super();
		this.id = id;
		this.refName = refName;
		this.refDetail = refDetail;
		this.refContact = refContact;
		this.refEmail = refEmail;
	}
	public int getRef_id() {
		return ref_id;
	}
	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public String getRefDetail() {
		return refDetail;
	}
	public void setRefDetail(String refDetail) {
		this.refDetail = refDetail;
	}
	public String getRefContact() {
		return refContact;
	}
	public void setRefContact(String refContact) {
		this.refContact = refContact;
	}
	public String getRefEmail() {
		return refEmail;
	}
	public void setRefEmail(String refEmail) {
		this.refEmail = refEmail;
	}
	
}
