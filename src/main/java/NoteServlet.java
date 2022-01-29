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

	private static final String INSERT_USERS_SQL = "INSERT INTO NewNotes" + " (user, title, details) VALUES "
			+ " (?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select user,title,details from NewNotes where user =?";
	private static final String SELECT_ALL_USERS = "select * from NewNotes ";
	private static final String DELETE_USERS_SQL = "delete from NewNotes where user = ?;";
	private static final String UPDATE_USERS_SQL = "update NewNotes set title= ?, user = ?, details =? where user = ?;";

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getServletPath();
		try {
			switch (action) {
			case "/NoteServlet/delete":
				deleteNote(request, response);
				break;
			case "/NoteServlet/edit":
				showEditForm(request, response);
				break;
			case "/NoteServlet/update":
				updateNote(request, response);
				break;
			case "/NoteServlet/dashboard":
				listNotes(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// method to delete user
	private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String user = request.getParameter("user");
		// Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setString(1, user);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserServlet dashboard (note: remember to change the
		// url to your project name)
		response.sendRedirect("http://localhost:8090/NotePad2/NoteServlet/dashboard");
	}

	// method to update the user table base on the form data
	private void updateNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String ori = request.getParameter("ori");
		String title = request.getParameter("title");
		String user = request.getParameter("user");
		String details = request.getParameter("details");

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user);
			statement.setString(2, title);
			statement.setString(3, details);
			statement.setString(4, ori);
			int i = statement.executeUpdate();
		}
		// Step 3: redirect back to UserServlet (note: remember to change the url to
		// your project name)
		response.sendRedirect("http://localhost:8090/NotePad2/NoteServlet/dashboard");
	}

	// method to get parameter, query database for existing user data and redirect
	// to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get parameter passed in the URL
		String user = request.getParameter("user");
		Note existingUser = new Note("", "", "");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setString(1, user);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				user = rs.getString("user");
				String title = rs.getString("title");
				String userr = rs.getString("user");
				String details = rs.getString("details");
				existingUser = new Note(title, userr, details);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("Note", existingUser);
		request.getRequestDispatcher("/NoteEdit.jsp").forward(request, response);
	}

	private void listNotes(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Note> notes = new ArrayList<>();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
