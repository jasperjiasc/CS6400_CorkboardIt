package information;

import java.sql.ResultSet;

public class BoardStat {
	private ResultSet BoardStat;
	public BoardStat(ResultSet CorkBoardStat) {
		super();
		this.BoardStat = CorkBoardStat;
		}
	
	public ResultSet getBoardStat() {
		return BoardStat;
	}
}
