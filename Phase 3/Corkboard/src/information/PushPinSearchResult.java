package information;

import java.sql.ResultSet;

public class PushPinSearchResult {
	private ResultSet PPresult;
	public PushPinSearchResult(ResultSet PPresult) {
		//super();
		this.PPresult = PPresult;
	}
	
	public ResultSet getPPresult() {
		return PPresult;
	}
}
