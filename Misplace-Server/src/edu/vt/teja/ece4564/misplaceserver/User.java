package edu.vt.teja.ece4564.misplaceserver;

public class User {
	public String firstname;
	public String lastname;
	public String username;
	public String password;
	public String deviceid;
	
	public User(String first, String last, String user, String pass, String devID){
		firstname = first;
		lastname = last;
		username = user;
		password = pass;
		deviceid = devID;
	}
	
	
	public String toString(){
		return "{(" + lastname + ", " + firstname + ") (" + username + ") (" + deviceid + ")}";
	}
	
}
