package bigdata.project.parking.service;

public class NextPage {
	
private String pageName;
	
	// ������ �̵� ���
	private boolean isRedirect; // true�� �����̷�Ʈ

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	@Override
	public String toString() {
		return "NextPage [pageName=" + pageName + ", isRedirect=" + isRedirect + "]";
	}

}
