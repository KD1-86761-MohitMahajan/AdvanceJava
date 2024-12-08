package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getSessionFactory;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.TeamsDao;
import com.cdac.dao.TeamsDaoimpl;
import com.cdac.entitites.Teams;
import com.cdac.utils.HibernateUtils;

public class DisplayAgeAverage {

	public static void main(String[] args) {
	
		try(SessionFactory factory = HibernateUtils.getSessionFactory();
				Scanner sc=new Scanner(System.in)){
				
				TeamsDao teamsDao = new TeamsDaoimpl();
				System.out.println("Enter max age : ");
				int age = sc.nextInt();
				System.out.println("Enter batting avg : ");
				double average = sc.nextDouble();
				List<Teams> teams = teamsDao.getAllTeamsAgeAvg(age, average);
				for (Teams team : teams) {
					System.out.println(team);
				}
		}
		
		
	}
}
