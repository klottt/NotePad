

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class NoteServlet
 */
@WebServlet("/NoteServlet")
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL = "jdbc:mysql://localhost:3306/userdetails";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO NewNotes" + " (user, title, details) VALUES " + " (?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select user,title,details from NewNotes where name =?";
	private static final String SELECT_ALL_USERS = "select * from NewNotes ";
	private static final String DELETE_USERS_SQL = "delete from NewNotes where user = ?;";
	private static final String UPDATE_USERS_SQL = "update NewNotes set user = ?,title= ?, details =?, where user = ?;";
	
	protected Connection getConnection() {
		 Connection connection = null;
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 return connection;
		 }
			 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getServletPath();
		 	try {
		 		switch (action) {
		 			case "/insert":
		 				break;
		 			case "/delete":
		 				break;
		 			case "/edit":
		 				break;
		 			case "/update":
		 				break;
		 			default:
		 				listNotes(request, response);
		 				break;
		 		}
		 } catch (SQLException ex) {
			 throw new ServletException(ex);
		 }
		 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void listNotes(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException
	{
		List <Note> notes = new ArrayList <>();
		try (Connection connection = getConnection();
			 
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			 
			 ResultSet rs = preparedStatement.executeQuery();
			 
			 while (rs.next()) {
			 String user = rs.getString("user");
			 String title = rs.getString("title");
			 String details = rs.getString("details");
			 notes.add(new Note(user, title, details));
			 }
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }

			request.setAttribute("listNotes", notes);
			request.getRequestDispatcher("/noteManagement.jsp").forward(request, response);
			}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
