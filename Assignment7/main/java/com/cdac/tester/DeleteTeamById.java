package com.cdac.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.TeamsDao;
import com.cdac.dao.TeamsDaoimpl;
import com.cdac.utils.HibernateUtils;

public class DeleteTeamById {

	public static void main(String[] args) {
		
		try(SessionFactory factory = HibernateUtils.getSessionFactory();
				Scanner sc=new Scanner(System.in)){
				
				TeamsDao tDao = new TeamsDaoimpl();
				System.out.print("Enter id to delete : ");
				String msg = tDao.deleteTeam(sc.nextLong());
				System.out.println("Message : " + msg);
		}
		
		
		}

	}


