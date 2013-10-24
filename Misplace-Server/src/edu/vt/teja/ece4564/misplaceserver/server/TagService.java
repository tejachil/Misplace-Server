package edu.vt.teja.ece4564.misplaceserver.server;

import edu.vt.teja.ece4564.misplaceserver.Global;
import edu.vt.teja.ece4564.misplaceserver.TagMessage;
import edu.vt.teja.ece4564.misplaceserver.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class TagService extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");
		if(!req.getParameterMap().containsKey("action")){
			res.getWriter().println("TAGS: ");
			res.getWriter().println(Global.TAGS_.toString());
		}
		else if(req.getParameter("action").equals("add")){
			User currUser = Global.USERS_.get(Global.getIndexOfUser(req.getParameter("devid")));
			
			
			TagMessage.Tag newMessage = TagMessage.Tag.parseFrom(req.getInputStream());
			
			List<TagMessage.Tag> messageList;
			if(Global.TAGS_.get(currUser) == null){
				messageList = new ArrayList<TagMessage.Tag>();
				messageList.add(newMessage);
			}
			else{
				messageList = Global.TAGS_.get(currUser);
				messageList.add(newMessage);
			}
			Global.TAGS_.put(currUser, messageList);
			
			res.getWriter().println("Added: " + newMessage);
		}
		else{
			res.getWriter().println("TAGS: ");
			res.getWriter().println(Global.TAGS_.toString());
		}
	}
}
