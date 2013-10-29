package edu.vt.teja.ece4564.misplaceserver.server;

import edu.vt.teja.ece4564.misplaceserver.Global;
import edu.vt.teja.ece4564.misplaceserver.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");
		if(!req.getParameterMap().containsKey("action")){
			res.getWriter().println("USERS: ");
			res.getWriter().println(Global.USERS_.toString());
		}
		else if(req.getParameter("action").equals("add") && 
				!Global.USERS_.toString().contains(req.getParameter("username"))){
			
			User newUser = new User(req.getParameter("firstname"), 
					req.getParameter("lastname"), 
					req.getParameter("username"), 
					req.getParameter("password"), 
					req.getParameter("devid"));
			
			Global.USERS_.add(newUser);
			res.getWriter().println("Added: " + newUser.toString());
		}
		else if (req.getParameter("action").equals("edit")){
			int index = Global.getIndexOfUser(req.getParameter("username"));
			if(index != -1){
				Global.USERS_.get(index).firstname = req.getParameter("firstname");
				Global.USERS_.get(index).lastname = req.getParameter("lastname");
				Global.USERS_.get(index).deviceid = req.getParameter("devid");
				Global.USERS_.get(index).password = req.getParameter("password");
				res.getWriter().println("Updated: " + Global.USERS_.get(index).toString());
			}
			else res.getWriter().println("User does not exist. Please add.");
		}
		else if (req.getParameter("action").equals("login")){
			int index = Global.getIndexOfUser(req.getParameter("username"));
			if(index != -1 && 
					Global.USERS_.get(index).username.equals(req.getParameter("username")) &&
					Global.USERS_.get(index).password.equals(req.getParameter("password")))
				res.getWriter().println("LOGIN SUCCESS!");
			else	res.getWriter().println("LOGIN FAILED!");
		}
		else{
			res.getWriter().println("USERS: ");
			res.getWriter().println(Global.USERS_.toString());
		}
	}
}
