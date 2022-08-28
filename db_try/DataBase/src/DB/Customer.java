package DB;

public class Customer {

	 private double CID;
	 private String Name;
	 private String PhoneNumber;
	 
	  
	 
	 public Customer (double CID, String Name, String PhoneNumber) {
		 this.CID = CID;
		 this.Name = Name;
		 this.PhoneNumber = PhoneNumber;

	 }
	
	public String getName() {
		return Name;
	}
	
	public double getCID() {
		return CID;
	}
	
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	public void setPhoneNumber(String PhoneNumber) {
		this.PhoneNumber =PhoneNumber;
	}
	public void setCID(double CID) {
		this.CID = CID;
	}
}


