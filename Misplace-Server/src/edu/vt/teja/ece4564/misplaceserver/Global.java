package edu.vt.teja.ece4564.misplaceserver;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;



public class Global {
	public static List<User> USERS_ = new ArrayList<User>();
	public static HashMap<User, List<TagMessage.Tag>> TAGS_= new HashMap<User, List<TagMessage.Tag>>();
	
	public static int getIndexOfUser(String username){
		for(int i = 0; i < Global.USERS_.size(); ++i){
			if(USERS_.get(i).username.equals(username))
				return i;
		}
		return -1;
	}
}
