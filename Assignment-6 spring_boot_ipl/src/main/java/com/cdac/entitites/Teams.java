package com.cdac.entitites;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "Teams")
public class Teams {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "team_id")
	private long teamid;
	
	@Column (name = "name",length = 100, unique = true)
	private String name;
	
	@Column (name = "abbreviation", length = 10, unique = true)
	private String abbreviation;
	
	@Column (name = "owner",length = 20, nullable = false)
	private String owner;
	
	@Column(name = "max_player_age")
	private int maxPlayerAge ;
	
	@Column(name = "batting_average")
	private double battingAverage;
	
	@Column (name = "wickets_taken")
	private int wicketsTaken;
	
	


	public Teams() {
		super();
	}




	




	public Teams(String name, String abbreviation, String owner, int maxPlayerAge, double battingAverage,
			int wicketsTaken) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.owner = owner;
		this.maxPlayerAge = maxPlayerAge;
		this.battingAverage = battingAverage;
		this.wicketsTaken = wicketsTaken;
	}









	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAbbreviation() {
		return abbreviation;
	}




	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}




	public String getOwner() {
		return owner;
	}




	public void setOwner(String owner) {
		this.owner = owner;
	}




	public int getMaxPlayerAge() {
		return maxPlayerAge;
	}




	public void setMaxPlayerAge(int maxPlayerAge) {
		this.maxPlayerAge = maxPlayerAge;
	}




	public double getBattingAverage() {
		return battingAverage;
	}




	public void setBattingAverage(double battingAverage) {
		this.battingAverage = battingAverage;
	}




	public int getWicketsTaken() {
		return wicketsTaken;
	}




	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}



	
	
	
}
