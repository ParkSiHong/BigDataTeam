package bigdata.project.dto;

import java.io.Serializable;
import java.sql.Date;

public class Client implements Serializable{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   
private String id;
   private String pwd;
   private String name;
   private Date regDate;
   private String gender;
   private String homeaddress;
   private String jobaddress;
   
@Override
public String toString() {
   return "Client [id=" + id + ", pwd=" + pwd + ", name=" + name + ", regDate=" + regDate + ", gender=" + gender
         + ", homeaddress=" + homeaddress + ", jobaddress=" + jobaddress + "]";
}
public String getId() {
   return id;
}
public void setId(String id) {
   this.id = id;
}
public String getPwd() {
   return pwd;
}
public void setPwd(String pwd) {
   this.pwd = pwd;
}
public String getName() {
   return name;
}
public void setName(String name) {
   this.name = name;
}
public Date getRegDate() {
   return regDate;
}
public void setRegDate(Date regDate) {
   this.regDate = regDate;
}
public String getGender() {
   return gender;
}
public void setGender(String gender) {
   this.gender = gender;
}
public String getHomeaddress() {
   return homeaddress;
}
public void setHomeaddress(String homeaddress) {
   this.homeaddress = homeaddress;
}
public String getJobaddress() {
   return jobaddress;
}
public void setJobaddress(String jobaddress) {
   this.jobaddress = jobaddress;
}
public static long getSerialversionuid() {
   return serialVersionUID;
}
   
   
}