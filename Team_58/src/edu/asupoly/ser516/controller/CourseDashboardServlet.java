package edu.asupoly.ser516.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.asupoly.ser516.model.CourseDAOBean;
import edu.asupoly.ser516.model.CourseVO;
import edu.asupoly.ser516.model.QuizDAOBean;
import edu.asupoly.ser516.model.QuizVO;
import edu.asupoly.ser516.model.UserVO;
/**
 * Class CourseDashboardServlet is a controller 
 * that routes the User to Course Dashboard Page from Professor Home Page.
 * 
 * @author narenkumarKonchada
 * @version 1.3
 * @date 02/20/2019
 **/

public class CourseDashboardServlet extends HttpServlet {
	
	// This servlet will not make any Get requests.
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	/**
	 * This method is to get QuizList 
	 *@param request  Request made to server
	 *@param response  Responses from server
	 *
	 * @throws IOException
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException {
		
		
		HttpSession session = request.getSession();
		HashMap<Integer, String> courseMap = (HashMap<Integer,String>) session.getAttribute("CourseHashMap");
		int courseId = Integer.parseInt(request.getParameter("Course"));
		String courseName = courseMap.get(courseId);
		session.setAttribute("courseName",courseName);
		session.setAttribute("courseId", courseId);
		
		try {
			QuizDAOBean quizBean = new QuizDAOBean();
			List<QuizVO> quizList = quizBean.getQuizzesForCourse(courseId);
			HashMap<Integer, String> quiz = new HashMap<>();
			for(int i=0;i<quizList.size();i++)
				quiz.put(quizList.get(i).getQuizId(), quizList.get(i).getQuizTitle());
			session.setAttribute("QuizHashMap", quiz);
			response.sendRedirect(request.getContextPath()+"/courseDashboard.ftl");  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}