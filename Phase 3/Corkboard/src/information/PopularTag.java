package information;

public class PopularTag {
	private String tag;
	private int pushpin_num;
	private int unique_board_num;
	
	public PopularTag(String tag, int pushpin_num, int unique_board_num) {
		super();
		this.tag = tag;
		this.pushpin_num = pushpin_num;
		this.unique_board_num = unique_board_num;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getPushpin_num() {
		return pushpin_num;
	}

	public void setPushpin_num(int pushpin_num) {
		this.pushpin_num = pushpin_num;
	}

	public int getUnique_board_num() {
		return unique_board_num;
	}

	public void setUnique_board_num(int unique_board_num) {
		this.unique_board_num = unique_board_num;
	}	

}
