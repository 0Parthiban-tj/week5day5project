package model;

import java.time.LocalDate;

public class Studentinfo {
	int id;
	String name;
	long mobile;
	LocalDate date;
	String collname;
	String from;
	String to;
	int cost;
	public Studentinfo(int id, String name,long mobile,LocalDate date, String collname, String from, String to, int cost) {
		super();
		this.id = id;
		this.name = name;
		this.mobile=mobile;
		this.date=date;
		this.collname = collname;
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id=id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.collname=name;
	}
	
	public long getmobile() {
		return mobile;
	}
	public void setmobile(long mobile) {
		this.mobile=mobile;
	}
	
	public LocalDate getdate() {
		return date;
	}
	public void setdate(LocalDate date) {
		this.date=date;
	}
	public String getcollname() {
		return collname;
	}
	public void setcollname(String collname) {
		this.collname=collname;
	}
	public String getfrom() {
		return from;
	}
	public void setfrom(String from) {
		this.from=from;
	}
	public String getto() {
		return to;
	}
	public void setto(String to) {
		this.to=to;
	}
	public int getcost() {
		return cost;
	}
	public void setcost(int cost) {
		this.cost=cost;
	}
	
}
