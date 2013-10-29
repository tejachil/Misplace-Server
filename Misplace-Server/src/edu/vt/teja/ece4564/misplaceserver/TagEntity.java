package edu.vt.teja.ece4564.misplaceserver;

public class TagEntity {
	public String latitude_;
	public String longitude_;
	public String time_;
	public String message_;
	
	public TagEntity(String latitude, String longitude, String time, String message){
		latitude_ = latitude;
		longitude_ = longitude;
		time_ = time;
		message_ = message;
	}
	
	public String toString(){
		return "\n\t(" + latitude_ + "," + longitude_ + ") " + time_ + " " + message_ + "\n";   
	}
}
