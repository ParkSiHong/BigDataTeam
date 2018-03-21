package bigdata.project.dto;

public class Count {
	
	private String userId;
	private int count;
	
	public Count() {
		super();
	}
	public Count(String userId, int count) {
		super();
		this.userId = userId;
		this.count = count;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Count [userId=" + userId + ", count=" + count + "]";
	}

	
}
