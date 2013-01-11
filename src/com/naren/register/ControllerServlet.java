package com.naren.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hsqldb.Session;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        System.out.println("Inside no arg constructor");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of Servlet doGet method");
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside of Servlet doPost method");
		process(request,response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Model m =new Model();
		HttpSession session = request.getSession(true);
		String uri=request.getRequestURI();
		RequestDispatcher rd= null;	
		if(uri.contains("/register"))
		{
			RegBean rb = (RegBean)request.getAttribute("reg");
			System.out.println("User inputs are email:"+rb.getEmail()+"Password"+rb.getPwd()+"Repeated pass"+rb.getRpwd());
			String result = m.register(rb);
			if(result.contains(Constants.SUCCESS))
			{
				request.setAttribute("successmsg", Constants.SUCCESSMSG);
				rd = request.getRequestDispatcher("Success.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("errormsg", result);
				rd = request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
				
			}
		}
		if(uri.contains("/login"))
		{
			LogInBean lb = (LogInBean)request.getAttribute("login");
			System.out.println("The Email and Pwd from user log is\n" + "Email:" + lb.getEmail()+ "pwd:"+lb.getPwd());
			
			String result = m.authenticate(lb);
			if(result.contains(Constants.SUCCESS))
			{
				
				session.setAttribute("user", lb.getEmail());
				session.setAttribute("pwd", lb.getPwd());
				System.out.println("Session  was successfully created");
				rd = request.getRequestDispatcher("DictionaryView.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("errormsg", result);
				rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/DictionaryView"))
		{
			rd = request.getRequestDispatcher("DictionaryName.jsp");
			rd.forward(request, response);
		}
		if(uri.contains("/dictName"))
		{
			DictNameBean db = (DictNameBean)request.getAttribute("dname");
			String email = (String)session.getAttribute("user");
			String dname = (String)session.getAttribute(db.getDname());
			//String pwd = (String)session.getAttribute("pwd");
			String result = m.createDictionary(email, db);
			session.setAttribute("dname", db.getDname());
			if(result.contains(Constants.SUCCESS))
			{
				request.setAttribute("msg", Constants.SUCCESS);
				rd = request.getRequestDispatcher("DictActions.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("errormsg", result);
				rd = request.getRequestDispatcher("DictionaryName.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/addWord"))
		{
			System.out.println("inside of /addword");
			AddWordBean ab = (AddWordBean)request.getAttribute("word");
			String dname = (String)session.getAttribute("dname");
			String addwordres = m.addword(ab,dname);
			if(addwordres.contains(Constants.SUCCESS))
			{
	 			request.setAttribute("msg", Constants.SUCCESS);
				rd=request.getRequestDispatcher("DictActions.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("errmsg", addwordres);
				rd=request.getRequestDispatcher("WordDetails.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/search"))
		{
			System.out.println("inside of /search");
			AddWordBean ab = (AddWordBean)request.getAttribute("word");
			String dname = (String)session.getAttribute("dname");
			System.out.println(dname);
			String searchres = m.searchword(dname,ab);
			request.setAttribute("msg", searchres);
			System.out.println(searchres);
			rd=request.getRequestDispatcher("SearchDetails.jsp");
			rd.forward(request, response);
		}
		if(uri.contains("/edit"))
		{
			System.out.println("inside of /edit");
			AddWordBean ab = (AddWordBean)request.getAttribute("word");
			System.out.println(ab.getWord());
			String dname = (String)session.getAttribute("dname");
			System.out.println(dname);
			String editres = m.editword(dname,ab);
			if(editres.contains(Constants.SUCCESS))
			{
				request.setAttribute("msg", "The details u are enter it above will be modified to the specific word");
				rd=request.getRequestDispatcher("WordDetails.jsp");
				rd.forward(request, response);
			}
			else
			{	
				request.setAttribute("msg",	editres);
				rd=request.getRequestDispatcher("EditDetails.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/delete"))
		{
			System.out.println("inside of delete method");
			AddWordBean ab = (AddWordBean)request.getAttribute("word");
			System.out.println(ab.getWord());
			String dname = (String)session.getAttribute("dname");
			System.out.println(dname);
			String deleteres = m.deleteword(dname,ab);
			if(deleteres.contains(Constants.SUCCESS))
			{
				request.setAttribute("msg", "deleted");
				rd=request.getRequestDispatcher("DictActions.jsp");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("msg1", deleteres);
				rd=request.getRequestDispatcher("DeleteDetails.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/listwords"))
		{
			System.out.println("Inside of list words");
			String dname = (String)session.getAttribute("dname");
			System.out.println(dname);
			String listres=m.listword(dname);
			System.out.println(listres);
			request.setAttribute("msg", listres);
			rd=request.getRequestDispatcher("listwords.jsp");
			rd.forward(request, response);
		}
		/*if(uri.contains("/load"))
		{
			System.out.println("inside of load dictionary");
			String loadres = m.loadDict();
			request.setAttribute("msg", loadres);
			rd=request.getRequestDispatcher("Load.jsp");
			rd.forward(request, response);
		}*/
		if(uri.contains("/load1"))
		{
			System.out.println("Inside load1");
			DictNameBean db = (DictNameBean)request.getAttribute("dname");
			String email = (String)session.getAttribute("user");
						
			//session.setAttribute("dname", db.getDname());
			//String dname = (String)session.getAttribute(db.getDname());
			System.out.println(db.getDname());
			
			String load1res=m.loadcorrect(db.getDname());
			System.out.println(load1res);
			session.setAttribute("dname", db.getDname());
			if(load1res.contains(Constants.SUCCESS))
			{
				System.out.println("Before forwarding");
			
				m.createDictionary(email, db);
				rd=request.getRequestDispatcher("DictActions.jsp");
				rd.forward(request, response);
				
			}
			else
			{
				System.out.println("inside of load dictionary");
				String loadres = m.loadDict();
				request.setAttribute("msg", loadres);
				rd=request.getRequestDispatcher("Load.jsp");
				rd.forward(request, response);
			}
		}
		if(uri.contains("/logout"))
		{
			session.getAttribute("user");
			session.getAttribute("pwd");
			session.removeAttribute("user");
			session.removeAttribute("pwd");
			rd=request.getRequestDispatcher("HomePage.html");
			rd.forward(request, response);
		}
	}

}
