package com.cdac.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.TeamsDao;
import com.cdac.dao.TeamsDaoimpl;
import com.cdac.entitites.Teams;
import com.cdac.utils.HibernateUtils;

public class TeamRegistration {

	public static void main(String[] args) {
		
		try(SessionFactory sf = HibernateUtils.getSessionFactory();
				Scanner sc = new Scanner(System.in)) {
			
			
			
			TeamsDao teamsDao = new TeamsDaoimpl();
			System.out.println("Enter user details - String Team Name, "
					+ "String Abbreviation, String Owner Name, Int Max Player Age, "
					+ "INT Batting Average , Int Wickets Taken,\r\n");
			
			
			Teams team = new Teams(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble(), sc.nextInt());
					
					System.out.println(teamsDao.signUpTeams(team));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
