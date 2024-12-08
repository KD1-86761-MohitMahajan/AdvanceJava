package com.cdac.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.TeamsDao;
import com.cdac.dao.TeamsDaoimpl;
import com.cdac.utils.HibernateUtils;

public class UpdateTeamByName {

	public static void main(String[] args) {
		try (SessionFactory factory = HibernateUtils.getSessionFactory(); Scanner sc = new Scanner(System.in)) {

			TeamsDao tDao = new TeamsDaoimpl();
			System.out.println("Enter Team name : ");
			String tName = sc.nextLine();
			System.out.println("Enter Max Age  : ");
			int mAge = sc.nextInt();
			String msg = tDao.updateTeamMaxAge(tName, mAge);
			System.out.println(msg);
		}
	}
}
