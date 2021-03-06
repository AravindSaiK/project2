<%-- 
  - Author(s): Yu-Ting Tsao
  - Date: 2019/2/20
  - Description: List all quizzes that can be taken by student.
  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.asu.ser516.team47.database.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="CSS/myquizzes.css" type="text/css" />
<script type="text/javascript" src="js/myquizzes.js"></script>
<title>My Quizzes</title>
</head>
<body>
	<div id="top">
		<a href="login.jsp" class="btn btn-default btn-sm"> <span
			class="glyphicon glyphicon-log-out"></span> Log out
		</a>
	</div>
	<div class="table-title">
		<h3>Quiz Summary</h3>
	</div>
	<div>
		<table class="table-fill" id="tableId">
			<thead>
				<tr>
					<th class="text-left">Quiz No.</th>
					<th class="text-left">Title</th>
					<th class="text-left">Quiz Instruction</th>
				</tr>
			</thead>
			<tbody class="table-hover">
				<%!String createQuizSelectTable() {
		List<Quiz> quizzes = new QuizDAOImpl().getAllQuizzes();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < quizzes.size(); i++) {
			int id = quizzes.get(i).getQuiz_id();
			String title = quizzes.get(i).getTitle();
			String instruction = quizzes.get(i).getInstructions();
			buf.append(String.format(
					"<tr onclick=\"window.location.href = '/instruction.jsp?quizId=%d';\">" +
					"<td id=\"quizId\">%d</td>" + 
					"<td class=\"text-left\">" + 
					"%s</td>" + 
					"<td>%s</td>" +
					"</tr>",
					id, id, title, instruction));
		}
		String html = buf.toString();
		return html;
	}%>
				<%=createQuizSelectTable()%>
			</tbody>
		</table>
	</div>
</body>
