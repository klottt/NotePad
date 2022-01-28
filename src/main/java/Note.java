
public class Note {
	
	protected String name;
	public Note(String name, String title, String details) {
		super();
		this.name = name;
		this.title = title;
		this.details = details;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
