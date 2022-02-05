public class Note {
	
	protected String user;
	public Note(String user, String title, String details) {
		super();
		this.user = user;
		this.title = title;
		this.details = details;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	protected String title;
	protected String details;

}
