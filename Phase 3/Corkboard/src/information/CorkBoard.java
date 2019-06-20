package information;

import java.sql.Timestamp;

public class CorkBoard {
	//private Timestamp corkBoardUpdatedTime;
	//private String email;
	private int board_id;
	private int user_id;
	private String title;
	private String last_datetime;
	private String owner;
	private String visibility;
	private String password;
	


//	public CorkBoard(String email, Timestamp corkBoardUpdatedTime) {
//		setEmail(email);
//		setCorkBoardUpdatedTime(corkBoardUpdatedTime);
//	}
	
//	public Timestamp getCorkBoardUpdatedTime() {
//		return corkBoardUpdatedTime;
//	}
//	public void setCorkBoardUpdatedTime(Timestamp corkBoardUpdatedTime) {
//		this.corkBoardUpdatedTime = corkBoardUpdatedTime;
//	}

	public CorkBoard(int board_id, int user_id, String title, String last_datetime, String owner, String visibility,
			String password) {
		super();
		this.board_id = board_id;
		this.user_id = user_id;
		this.title = title;
		this.last_datetime = last_datetime;
		this.owner = owner;
		this.visibility = visibility;
		this.password = password;
	}

	public CorkBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLast_datetime() {
		return last_datetime;
	}

	public void setLast_datetime(String last_datetime) {
		this.last_datetime = last_datetime;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

//	public String getEmail() {
//		return email;
//	}
//	
//	public void setEmail(String email) {
//		this.email = email;
//	}
	
	public String getTitle() {
		return title;
	}
	
	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
		
}
