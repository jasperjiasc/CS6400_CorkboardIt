package information;

import java.sql.ResultSet;

public class PopularSite {
	private ResultSet SiteStat;
	
	public PopularSite(ResultSet SiteStat) {
		super();
		this.SiteStat = SiteStat;
		}
	
	public ResultSet getSiteStat() {
		return SiteStat;
	}
}
