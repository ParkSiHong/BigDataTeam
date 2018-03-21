package bigdata.project.dto;

import java.io.Serializable;

public class ParkingLot implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 6991808292519155712L;

	private String primaryNum;
	private String parkingName;
	private String parkingType;
	private int sectionDount;
	private String runDay;
	private String tel;
	private String new_address;
	private String old_address;
	private double latitude;
	private double longtitude;
	
	private int parkingBasicFee;
	private int parkingBasicTime;
	private int addUnitFee;
	private int addUnitTime;

	
	public String getPrimaryNum() {
		return primaryNum;
	}


	public void setPrimaryNum(String primaryNum) {
		this.primaryNum = primaryNum;
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


	public String getNew_address() {
		return new_address;
	}


	public void setNew_address(String new_address) {
		this.new_address = new_address;
	}


	public String getOld_address() {
		return old_address;
	}


	public void setOld_address(String old_address) {
		this.old_address = old_address;
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


	@Override
	public String toString() {
		return "ParkingLot [primaryNum=" + primaryNum + ", parkingName=" + parkingName + ", parkingType=" + parkingType
				+ ", runDay=" + runDay + ", parkingBasicFee=" + parkingBasicFee + ", parkingBasicTime="
				+ parkingBasicTime + "]";
	}
	
	

	
	

}
