package rb.resumecareer.resumePojo;

public class PersonalDetailPojo {

	int id;
	String personName, gender, email, mobile, address, language, birthdate;

	// private User user;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public PersonalDetailPojo() {
		super();
	}
	
	public PersonalDetailPojo(int id, String personName, String gender,
			String birthdate, String address, String language, String mobile,
			String email) {
		super();
		this.id = id;
		this.personName = personName;
		this.gender = gender;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.language = language;
		this.birthdate = birthdate;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
