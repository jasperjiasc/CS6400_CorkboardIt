package information;

public class PushPin {
	private int pushpin_id;
	private int corkboard_id;
	private int user_id;
	private String pp_time;
	private String description;
	private String url;
	private String tag;
	private String owner_name;
	private String corkboard_name;
	

	public PushPin(int pushpin_id, int corkboard_id, String pp_time, String description, String url, String owner, String corkboard, int user_id) {
		super();
		this.pushpin_id = pushpin_id;
		this.corkboard_id = corkboard_id;
		this.pp_time = pp_time;
		this.description = description;
		this.url = url;
		this.owner_name = owner;
		this.corkboard_name = corkboard;
		this.setUser_id(user_id);
	}
	
	public int getPushpin_id() {
		return pushpin_id;
	}
	public void setPushpin_id(int pushpin_id) {
		this.pushpin_id = pushpin_id;
	}
	public int getCorkboard_id() {
		return corkboard_id;
	}
	public void setCorkboard_id(int corkboard_id) {
		this.corkboard_id = corkboard_id;
	}
	public String getPp_time() {
		return pp_time;
	}
	public void setPp_time(String pp_time) {
		this.pp_time = pp_time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getCorkboard_name() {
		return corkboard_name;
	}

	public void setCorkboard_name(String corkboard_name) {
		this.corkboard_name = corkboard_name;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}	
}
