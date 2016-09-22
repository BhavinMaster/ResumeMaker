package rb.resumecareer.resumePojo;

import java.sql.Blob;


public class User{

	//private variables
    int id;
    String name;
    byte[] b1;
	public User(int id, byte[] b1) {
		super();
		this.id = id;
		this.b1 = b1;
	}
	public byte[] getImgProfile() {
		return b1;
	}
	public void setImgProfile(byte[] b1) {
		this.b1=b1;
	}
	public User(String name,byte[] b1) {
		super();
		this.name = name;
		this.b1=b1;
	}

	public User(int id, String name,byte[] b1) {
		super();
		this.id = id;
		this.name = name;
		this.b1=b1;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
