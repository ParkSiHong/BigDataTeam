package bigdata.project.dto;

public class Bookmark {

	private String userId;
	private String primaryNum;

	private String parkingNum;
	private String parkingName;
	private String parkingType;
	private int sectionDount;
	private String runDay;
	private String tel;
	private String address;
	private double latitude;
	private double longtitude;

	private int parkingBasicFee;
	private int parkingBasicTime;
	private int addUnitFee;
	private int addUnitTime;

	public String getParkingNum() {
		return parkingNum;
	}

	public void setParkingNum(String parkingNum) {
		this.parkingNum = parkingNum;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public String getParkingType() {
		return parkingType;
	}

	public void setParkingType(String parkingType) {
		this.parkingType = parkingType;
	}

	public int getSectionDount() {
		return sectionDount;
	}

	public void setSectionDount(int sectionDount) {
		this.sectionDount = sectionDount;
	}

	public String getRunDay() {
		return runDay;
	}

	public void setRunDay(String runDay) {
		this.runDay = runDay;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public int getParkingBasicFee() {
		return parkingBasicFee;
	}

	public void setParkingBasicFee(int parkingBasicFee) {
		this.parkingBasicFee = parkingBasicFee;
	}

	public int getParkingBasicTime() {
		return parkingBasicTime;
	}

	public void setParkingBasicTime(int parkingBasicTime) {
		this.parkingBasicTime = parkingBasicTime;
	}

	public int getAddUnitFee() {
		return addUnitFee;
	}

	public void setAddUnitFee(int addUnitFee) {
		this.addUnitFee = addUnitFee;
	}

	public int getAddUnitTime() {
		return addUnitTime;
	}

	public void setAddUnitTime(int addUnitTime) {
		this.addUnitTime = addUnitTime;
	}

	//--------userID
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//---------primaryNum
	public String getPrimaryNum() {
		return primaryNum;
	}

	public void setPrimaryNum(String primaryNum) {
		this.primaryNum = primaryNum;
	}

	@Override
	public String toString() {
		return "Bookmark [userId=" + userId + ", primaryNum=" + primaryNum + "]";
	}
	
	
}