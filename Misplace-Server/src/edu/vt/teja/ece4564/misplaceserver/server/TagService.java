package edu.vt.teja.ece4564.misplaceserver.server;

import edu.vt.teja.ece4564.misplaceserver.Global;
import edu.vt.teja.ece4564.misplaceserver.TagEntity;
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
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		if(!req.getParameterMap().containsKey("action")){
			res.getWriter().println("TAGS: ");
			res.getWriter().println(Global.getKeyTags_.toString());
		}
		else if(req.getParameter("action").equals("add")){
			String time = req.getParameter("time");
			time = time.substring(0,4) + "-" + time.substring(4,6) + "-" + time.substring(6,8) + ", " + time.substring(8,10) + ":" + time.substring(10,12);   
			TagEntity newMessage = new TagEntity(req.getParameter("latitude"),
					req.getParameter("longitude"), time, req.getParameter("message"));
			
			List<TagEntity> messageList;
			if(Global.getKeyTags_.get(req.getParameter("tagid")) == null){
				messageList = new ArrayList<TagEntity>();
				messageList.add(newMessage);
			}
			else{
				messageList = Global.getKeyTags_.get(req.getParameter("tagid"));
				messageList.add(newMessage);
			}
			Global.getKeyTags_.put(req.getParameter("tagid"), messageList);
			
			res.getWriter().println("Added: " + newMessage);
		}
		else if(req.getParameter("action").equals("get")){
			res.getWriter().println(Global.getKeyTags_.get(req.getParameter("tagid").toString()));
		}
		else{
			res.getWriter().println(Global.getKeyTags_.toString());
		}
	}
	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("text/html");
		res.getWriter().println("Posting...");
		if(!req.getParameterMap().containsKey("action")){
			
			res.getWriter().println("TAGS: ");
			res.getWriter().println(Global.KEYTAGS_.toString());
		}
		else if(req.getParameter("action").equals("add")){
			//User currUser = Global.USERS_.get(Global.getIndexOfUser(req.getParameter("username")));
			
			res.getWriter().println("adding...");
			TagMessage.Tag newMessage = TagMessage.Tag.parseFrom(req.getInputStream());
			
			List<TagMessage.Tag> messageList;
			if(Global.KEYTAGS_.get(req.getParameter("tagid")) == null){
				messageList = new ArrayList<TagMessage.Tag>();
				messageList.add(newMessage);
			}
			else{
				messageList = Global.KEYTAGS_.get(req.getParameter("tagid"));
				messageList.add(newMessage);
			}
			Global.KEYTAGS_.put(req.getParameter("tagid"), messageList);
			
			res.getWriter().println("Added: " + newMessage);
		}
		else{
			res.getWriter().println("TAGS: ");
			res.getWriter().println(Global.KEYTAGS_.toString());
		}
	}
}
