package information;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTML.Tag;

import appVersion1.PushPinSearchResults;

//import com.sun.org.apache.xml.internal.security.utils.UnsyncByteArrayOutputStream;



// Read data from mySQL
public class DataProvider {
	
	public static Connection _connection;
	
	public static void SetConnection() {
		Connection connection = null;
		try {
			connection = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/corkboard", "root", "MySQLPassword");
			if(connection != null) {
				System.out.println("Connected.");
				_connection = connection;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean LogInValid(String email, String password) {
		try {
			if(_connection == null) {
				SetConnection();
			}
			Statement statement = _connection.createStatement();
			String sql = "SELECT email, pin FROM regularuser";
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next()) {
				String recordEmail = rset.getString("email");
				String recordPin = rset.getString("pin");
				if(recordEmail.equals(email) && recordPin.equals(password)) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static int GetInteger(String targetColName, String keyName, String keyValue, String dataTable) {
		try {
			Statement statement = _connection.createStatement();
			String sql = "SELECT * FROM " + dataTable + " WHERE " + keyName + " = \"" + keyValue + "\"";
			System.out.println(sql);
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next()) {
				return rset.getInt(targetColName);
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String GetString(String targetColName, String keyName, int keyValue, String dataTable) {
		try {
			Statement statement = _connection.createStatement();
			String sql = "SELECT * FROM " + dataTable + " WHERE " + keyName + " = " + keyValue;
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next()) {
				return rset.getString(targetColName);
			}
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public static List<CorkBoard> getRecentUpdatedBoards(int user_id) {
		try {
			Statement statement = _connection.createStatement();
//			String sql = "SELECT C.last_dtime, C.title, C.visibility, U.first_name, U.last_name\r\n" + 
//					"FROM\r\n" + 
//					"Corkboard AS C\r\n" + 
//					"INNER JOIN regularuser AS U on C.user_id = U.user_id \r\n" + 
//					"WHERE C.user_id=" + user_id + "\r\n" + 
//					"ORDER BY C.last_dtime DESC";
			
			String sql = "SELECT C.corkboard_id, C.user_id, C.last_dtime, C.title, C.visibility, C.password, U.first_name, U.last_name from corkboard AS C INNER JOIN regularuser AS U ON C.user_id=U.user_id \r\n" + 
					"WHERE C.corkboard_id IN (select corkboard_id from watch where watch.user_id=" + user_id + ") " +
					"OR C.user_id IN (select follow.followed_user_id from follow where follow.user_id=" + user_id + ") " +
					"order by C.last_dtime DESC limit 4";
			ResultSet rset = statement.executeQuery(sql);
			
			List<CorkBoard> corkboards = new ArrayList<>();
			
			while(rset.next()) {
				int board_id = rset.getInt("corkboard_id");
				int owner_id = rset.getInt("user_id");
				String title = rset.getString("title");
				String owner = rset.getString("first_name") + " " + rset.getString("last_name");
				String last_datetime = rset.getString("last_dtime");
				String visibility = rset.getString("visibility");
				String password = rset.getString("password");
				
				CorkBoard corkboard = new CorkBoard(board_id, owner_id, title, last_datetime, owner, visibility, password);
				corkboards.add(corkboard);
			}
			return corkboards;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<CorkBoard> getmyCorkboards(int user_id){
		try {
			System.out.println("getmyCorkboards");
			Statement statement = _connection.createStatement();			
			String sql = "select corkboard_id,title,last_dtime,visibility,password from corkboard where user_id=" + user_id + " order by title limit 4";
			ResultSet rset = statement.executeQuery(sql);
			
			List<CorkBoard> corkboards = new ArrayList<>();
			
			while(rset.next()) {
				int board_id = rset.getInt("corkboard_id");
				String title = rset.getString("title");	
				String visibility = rset.getString("visibility");
				String password = rset.getString("password");
				String last_dtime = rset.getString("last_dtime");
				CorkBoard myCorkboard = new CorkBoard(board_id, user_id, title, last_dtime, null, visibility,password);
				corkboards.add(myCorkboard);
			}
			return corkboards;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

	public static int getPushpinNumberOnCorkboard(int bord_id) {
		try {
			Statement statement = _connection.createStatement();			
			String sql = "select count(pushpin_id) as pushpin_num from pushpin where corkboard_id=" + bord_id;
			ResultSet rset = statement.executeQuery(sql);
			
			int pushpin_num = 0;
			while(rset.next()) {
				pushpin_num = rset.getInt("pushpin_num");
			}
			return pushpin_num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}
	
	public static void GetFirstName() {
		try {
			Statement statement = _connection.createStatement();
			String sql = "SELECT first_name FROM regularuser";
			ResultSet rset = statement.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<PopularTag> getPopularTag() {
		try {
			Statement statement = _connection.createStatement();			
			String sql = "select PT.tag, count(PT.pushpin_id) as pp_num, count(distinct p.corkboard_id) as board_num\r\n" + 
					"from pushpin as P inner join ppin_tag as PT on P.pushpin_id=PT.pushpin_id group by tag \r\n" + 
					"order by pp_num desc limit 5";
			ResultSet rset = statement.executeQuery(sql);
			
			List<PopularTag> popularTags = new ArrayList<>();
			
			while(rset.next()) {
				String tag = rset.getString("tag");
				int pushpin_num = rset.getInt("pp_num");
				int board_num = rset.getInt("board_num");
				
				PopularTag popularTag = new PopularTag(tag, pushpin_num, board_num);
				popularTags.add(popularTag);
			}
			return popularTags;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public static PopularSite getPopularSite() {
		try {
			
			Statement statement = _connection.createStatement();			
			String sql = "select PopularSite, count(pushpin_id) as PushpinCount From (SELECT pushpin_id, substring_index((substring_index(url_image,\"/\",3)),\"/\",-1) \r\n" +
					 "AS popularSite FROM pushpin) as Subquery Group by popularSite Order By Pushpincount desc";
			ResultSet rs = statement.executeQuery(sql);
			PopularSite SiteStat = new PopularSite(rs);
			
			return SiteStat;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static BoardStat getBoardStat() {
		try {
			Statement statement = _connection.createStatement();			
			String sql = "SELECT PUB.user_id, Concat(PUB.first_name,' ',PUB.last_name) as Fullname,IFNULL(PublicCorkboardcount,0),IFNULL(Publicpushpincount,0),IFNULL(PrivateCorkboardcount,0),IFNULL(Privatepushpincount,0)\r\n" + 
					"FROM   \r\n" + 
					" ( SELECT U.user_id, first_name, last_name, PublicCorkboardcount, Publicpushpincount    \r\n" + 
					" FROM\r\n" + 
					" (SELECT Re.user_id, Re.first_name, Re.last_name\r\n" + 
					" FROM RegularUser as re\r\n" + 
					" ) AS U\r\n" + 
					" LEFT JOIN\r\n" + 
					" (SELECT R1.user_id,  count(C1.Corkboard_id) AS PublicCorkboardcount, COUNT(P1.pushpin_id) AS Publicpushpincount   \r\n" + 
					" FROM   \r\n" + 
					" RegularUser AS R1    \r\n" + 
					" LEFT JOIN Corkboard AS C1 on C1.user_id= R1.user_id   \r\n" + 
					" LEFT JOIN Pushpin AS P1 ON C1.Corkboard_id= P1.Corkboard_id   \r\n" + 
					" WHERE visibility='Public' OR visibility IS NULL   \r\n" + 
					" GROUP BY R1.user_id\r\n" + 
					" ) AS PUB\r\n" + 
					"ON PUB.user_id = U.user_id\r\n" + 
					" )   \r\n" + 
					" AS PUB   \r\n" + 
					" LEFT JOIN   \r\n" + 
					" (SELECT R.user_id, R.first_name, R.last_name, count(C.Corkboard_id) AS PrivateCorkboardcount, COUNT(P.pushpin_id) AS Privatepushpincount   \r\n" + 
					" FROM   \r\n" + 
					" RegularUser AS R   \r\n" + 
					" LEFT JOIN Corkboard AS C on C.user_id= R.user_id   \r\n" + 
					" LEFT JOIN Pushpin AS P ON C.Corkboard_id= P.Corkboard_id   \r\n" + 
					" WHERE visibility='Private' OR visibility IS NULL   \r\n" + 
					" GROUP BY user_id   \r\n" + 
					" )AS PRI   \r\n" + 
					" ON PUB.user_id=PRI.user_id   \r\n" + 
					" ORDER BY PublicCorkboardcount DESC,PrivateCorkboardcount DESC;";
			ResultSet rs = statement.executeQuery(sql);
			BoardStat ckStat = new BoardStat(rs);
			return ckStat;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static PushPinSearchResult getSearchRt(String PPsearch) {
		try {
			Statement statement = _connection.createStatement();
			String sql ="SELECT  DISTINCT(P.pushpin_id), P.Description, C.Title, Concat(R.first_name,' ',R.last_name) as Fullname\r\n" + 
					"FROM\r\n" + 
					"Pushpin AS P \r\n" + 
					"INNER JOIN ppin_tag AS T ON P.pushpin_id = T.pushpin_id\r\n" + 
					"INNER JOIN Corkboard AS C ON P.corkboard_id=C.corkboard_id\r\n" + 
					"INNER JOIN categorize as G ON C.corkboard_id = G.corkboard_id \r\n" + 
					"INNER JOIN RegularUser AS R on C.user_id= R.User_ID \r\n" + 
					"WHERE C.visibility = \"public\" and P.Description like '%"+PPsearch+"%' or C.visibility = \"public\" and \r\n" +  
					"T.tag like '%"+PPsearch+"%' or C.visibility = \"public\" and G.cat_name like '%"+PPsearch+"%'";
			
			
			
			
			ResultSet rs = statement.executeQuery(sql);
			PushPinSearchResult PPrs = new PushPinSearchResult(rs);
			
			System.out.println(sql);
			return PPrs;
					
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static List<String> getCategory() {
		try {
			Statement statement = _connection.createStatement();			
			String sql = "SELECT * FROM category;";
			ResultSet rset = statement.executeQuery(sql);
			
			List<String> category = new ArrayList<>();
			
			while(rset.next()) {
				String cat_name = rset.getString("cat_name");
				
				category.add(cat_name);
			}
			return category;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public static CorkBoard addCorkboard(CorkBoard c, String cat_name) {
		try {
			Statement statement = _connection.createStatement();	
			
			//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			//java.sql.Date sqlCurrentDate = new java.sql.Date(System.currentTimeMillis());
			java.sql.Timestamp sqlCurrentDate = new java.sql.Timestamp(System.currentTimeMillis());
			
			
			String sql = "INSERT INTO corkboard (user_id, creation_dtime, visibility, password, title) VALUES ( \r\n" +
					c.getUser_id() + ",'" + sqlCurrentDate + "','" + c.getVisibility() + "','" + c.getPassword() + "','" + c.getTitle() + "')";
			
			System.out.println(sql);
			statement.executeUpdate(sql);
			
			//get auto increased corkboard id
			int corkboard_id = 0;
			String sql2 = "SELECT corkboard_id FROM corkboard WHERE corkboard_id = @@Identity";			
			ResultSet rset = statement.executeQuery(sql2);			
			while(rset.next()) {
				corkboard_id = rset.getInt("corkboard_id");
			}
			
			String sql3 = "INSERT INTO categorize (corkboard_id, cat_name) VALUES ( \r\n" +
					corkboard_id + ",'" + cat_name + "')";
					System.out.println(sql3);
					statement.executeUpdate(sql3);
			
			CorkBoard ret_c = new CorkBoard(corkboard_id, c.getUser_id(), c.getTitle(), c.getLast_datetime(), c.getOwner(), c.getVisibility(), c.getPassword());
			
			return ret_c;
			
			//TODO: check adding result?
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public static PushPin getPushPin(int pp_id, String owner, String corkboard_name, int user_id) {
		try {
			
			Statement statement = _connection.createStatement();
			
			String sql = "select pushpin_id, corkboard_id, pp_dtime, description, url_image from pushpin where pushpin_id = " + pp_id;
			
			ResultSet rset = statement.executeQuery(sql);
			int pushpin_id = 0;
			int corkboard_id = 0;
			String pp_dtime = "";	
			String description = "";
			String url_image = "";
			
			PushPin pushpin = new PushPin(pushpin_id, corkboard_id, pp_dtime, description, url_image, owner, corkboard_name, user_id);
			while(rset.next()) {
			 pushpin_id = rset.getInt("pushpin_id");
			corkboard_id = rset.getInt("corkboard_id");
			 pp_dtime = rset.getString("pp_dtime");	
			 description = rset.getString("description");
			 url_image = rset.getString("url_image");
			 pushpin = new PushPin(pushpin_id, corkboard_id, pp_dtime, description, url_image, owner, corkboard_name, user_id);
			 //JOptionPane.showMessageDialog(null, "pushpin"+ pushpin_id + "  "+description);
			 
			}
			return pushpin;
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		 e.printStackTrace();
				return null;
			}
	}
	
	
	
	public static List<PushPin> getPushPins(int corkboardID, String owner, String corkboard_name, int user_id){
		try {
			System.out.println("get pushpins");
			Statement statement = _connection.createStatement();			
			String sql = "select pushpin_id, corkboard_id, pp_dtime, description, url_image from pushpin where corkboard_id=" + corkboardID + " limit 6";
			ResultSet rset = statement.executeQuery(sql);
			
			List<PushPin> pushPins = new ArrayList<>();
			
			while(rset.next()) {
				int pushpin_id = rset.getInt("pushpin_id");
				int corkboard_id = rset.getInt("corkboard_id");
				String pp_dtime = rset.getString("pp_dtime");	
				String description = rset.getString("description");
				String url_image = rset.getString("url_image");
				PushPin pushPin = new PushPin(pushpin_id, corkboard_id, pp_dtime, description, url_image, owner, corkboard_name, user_id);
				pushPins.add(pushPin);
			}
			return pushPins;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
		public static String addPushpin(PushPin pushPin, String tags) {
		try {
			Statement statement = _connection.createStatement();	
			
			//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			//java.sql.Date sqlCurrentDate = new java.sql.Date(System.currentTimeMillis());
			java.sql.Timestamp sqlCurrentDate = new java.sql.Timestamp(System.currentTimeMillis());
			
			String sql = "INSERT INTO pushpin (corkboard_id, url_image, description, pp_dtime) VALUES ( \r\n" + 
					pushPin.getCorkboard_id() + ", '" + pushPin.getUrl() + "', '" + pushPin.getDescription() +
					"', '" + sqlCurrentDate + "')";
			
			System.out.println(sql);
			statement.executeUpdate(sql);
			
			//get auto increased pushpin id
			int pushpin_id = 0;
			String sql2 = "SELECT pushpin_id FROM pushpin WHERE pushpin_id = @@Identity";			
			ResultSet rset = statement.executeQuery(sql2);			
			while(rset.next()) {
				pushpin_id = rset.getInt("pushpin_id");
			}
			
			String tagParsed[] = tags.split(",");
			for (String tag : tagParsed) {
				String sql3 = "INSERT INTO ppin_tag (pushpin_id, pp_dtime, tag) value ( \r\n" +
						pushpin_id + ", '" + sqlCurrentDate + "', '" + tag + "')";
				statement.executeUpdate(sql3);
			}
			
			String sql4 = "UPDATE corkboard.corkboard SET last_dtime = '" + sqlCurrentDate + "' WHERE (corkboard_id = " + pushPin.getCorkboard_id() + ")";
			System.out.println(sql4);
			statement.executeUpdate(sql4);
			
			return sqlCurrentDate.toString(); 			
			//TODO: check adding result?
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public static User getUserNameFromID(int user_id){
		try {
			System.out.println("getUserNameFromID");
			Statement statement = _connection.createStatement();			
			String sql = "select email, first_name, last_name from regularuser where user_id=" + user_id;
			ResultSet rset = statement.executeQuery(sql);			
			User user = new User(null, null, null);
			
			while(rset.next()) {
				String email = rset.getString("email");
				String first_name = rset.getString("first_name");
				String last_name = rset.getString("last_name");	

				user.setUserEmail(email);
				user.setFirst_name(first_name);
				user.setLast_name(last_name);
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public static void AddWatch(int user_id, int corkboard_id) throws SQLException {
		Statement statement = _connection.createStatement();	
    	String sql = "INSERT INTO watch (user_id, corkboard_id) VALUES ( \r\n" + 
				user_id + ", '" + corkboard_id + "')";
    	System.out.println(sql);
		statement.executeUpdate(sql);
	}
	
    public static void AddFollow(int user_id, int followed_id) throws SQLException {
    	Statement statement = _connection.createStatement();	
    	String sql = "INSERT INTO follow (user_id, followed_user_id) VALUES ( \r\n" + 
				user_id + ", '" + followed_id + "')";
    	System.out.println(sql);
		statement.executeUpdate(sql);
	}
	
	public static boolean Followed(int user_id, int followed_id) {
		try {
			if(_connection == null) {
				SetConnection();
			}
			Statement statement = _connection.createStatement();
			String sql = "SELECT followed_user_id FROM follow WHERE user_id = " + user_id;
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next()) {
				int followed_user_id = rset.getInt("followed_user_id");
				if(followed_user_id == followed_id) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Watched(int user_id, int corkboard_id) {
		try {
			if(_connection == null) {
				SetConnection();
			}
			Statement statement = _connection.createStatement();
			String sql = "SELECT corkboard_id FROM watch WHERE user_id = " + user_id;
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next()) {
				int record_corkboard_id = rset.getInt("corkboard_id");
				if(record_corkboard_id == corkboard_id) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static boolean Liked(int user_id, int pushpin_id) {
		try {
			if(_connection == null) {
				SetConnection();
			}
			Statement statement = _connection.createStatement();
			String sql = "SELECT pushpin_id FROM user_like WHERE user_id = " + user_id;
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next()) {
				int record_pushpin_id = rset.getInt("pushpin_id");
				if(record_pushpin_id == pushpin_id) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void Unlike(int user_id, int pushpin_id) throws SQLException {
    	Statement statement = _connection.createStatement();	
    	//String sql = "DELETE FROM `user_like` (user_id, pushpin_id) VALUES ( \r\n" + 
		//		user_id + ", '" + pushpin_id + "')";
    	String sql = "DELETE FROM `user_like` WHERE user_id = " + user_id + " AND pushpin_id = " + pushpin_id;
    	System.out.println(sql);
		statement.executeUpdate(sql);
	}

	public static void Like(int user_id, int pushpin_id) throws SQLException {
    	Statement statement = _connection.createStatement();	
    	String sql = "INSERT INTO `user_like` (user_id, pushpin_id) VALUES ( \r\n" + 
				user_id + ", '" + pushpin_id + "')";
    	System.out.println(sql);
		statement.executeUpdate(sql);
	}

	public static void AddComment(int user_id, int pushpin_id, String text) throws SQLException {
		java.sql.Timestamp sqlCurrentDate = new java.sql.Timestamp(System.currentTimeMillis());
		Statement statement = _connection.createStatement();	
    	String sql = "INSERT INTO comment (user_id, pushpin_id, c_dtime, content) VALUES ( \r\n" + 
				user_id + ", '" + pushpin_id + "', '" + sqlCurrentDate + "', '" + text + "')";
    	System.out.println(sql);
		statement.executeUpdate(sql);
	}

	public static String GetComments(int pushpin_id) {
		String comments = "";
		try {
			if(_connection == null) {
				SetConnection();
			}
			Statement statement = _connection.createStatement();
			String sql = "SELECT user_id, content FROM comment WHERE pushpin_id = " + pushpin_id;
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next()) {
				String comment = rset.getString("content");
				int user_id = rset.getInt("user_id");
				User user = getUserNameFromID(user_id);
				String userName = user.getFirst_name() + " " + user.getLast_name();
				comments = comments + "\n" + userName + ":   " + comment;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(comments);
		return comments;
	}

	public static String GetTags(int pushpin_id) {
		String tags = "";
		try {
			if(_connection == null) {
				SetConnection();
			}
			Statement statement = _connection.createStatement();
			String sql = "SELECT tag FROM ppin_tag WHERE pushpin_id = " + pushpin_id;
			ResultSet rset = statement.executeQuery(sql);
			int i = 0;
			while(rset.next()) {
				if(i > 0) {
					tags = tags + ", ";
				}
				String tag = rset.getString("tag");
				tags = tags + tag;
				i++;
			}
			return tags;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String GetLikedUserName(int pushpin_id) {
		String userNames = "";
		try {
			if(_connection == null) {
				SetConnection();
			}
			Statement statement = _connection.createStatement();
			String sql = "SELECT user_id FROM user_like WHERE pushpin_id = " + pushpin_id;
			ResultSet rset = statement.executeQuery(sql);
			int i = 0;
			while(rset.next() ) {
				if(i > 0 && i < 2) {
					userNames += ", ";
				}
				if(i < 2) {
					int user_id = rset.getInt("user_id");
					User user = getUserNameFromID(user_id);
					userNames = userNames + user.getFirst_name() + " " + user.getLast_name();
				}
				i++;
			}
			if(i > 2) {
				userNames = userNames + " and " + (i - 2) + " others.";
			}
			if(i == 0) {
				userNames = "0 people.";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userNames;
	}
	
	public static String GetUserNameFromPushpinID(int pushpin_id) {
		try {
			System.out.println("getUserNameFromID");
			Statement statement = _connection.createStatement();			
			String sql = "SELECT Concat(R.first_name,' ',R.last_name) as Fullname\n" + 
					"FROM\n" + 
					"Pushpin AS P\n" + 
					"INNER JOIN Corkboard AS C ON P.corkboard_id=C.corkboard_id\n" + 
					"INNER JOIN RegularUser AS R on C.user_id= R.User_ID\n" + 
					"WHERE P.pushpin_id = " + pushpin_id;
			ResultSet rset = statement.executeQuery(sql);			
			System.out.println(sql);
			while(rset.next()) {
				//return rset.getInt("R.user_id");
				return rset.getString("Fullname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;	
	}
	
	public static String GetCorkboardNameFromCorkboardID(int corkboard_id) {
		try {
			Statement statement = _connection.createStatement();
			String sql = "SELECT title FROM corkboard WHERE corkboard_id = " + corkboard_id;
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next() ) {
				return rset.getString("title");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String GetCategoryFromCorkBoardID(int corkboard_id) {
		try {
			Statement statement = _connection.createStatement();
			String sql = "SELECT cat_name FROM categorize WHERE corkboard_id = " + corkboard_id;
			ResultSet rset = statement.executeQuery(sql);
			while(rset.next() ) {
				return rset.getString("cat_name");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
