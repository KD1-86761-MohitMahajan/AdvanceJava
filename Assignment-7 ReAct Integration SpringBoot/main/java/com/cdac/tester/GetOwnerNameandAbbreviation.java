package com.cdac.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.cdac.dao.TeamsDao;
import com.cdac.dao.TeamsDaoimpl;
import com.cdac.utils.HibernateUtils;

public class GetOwnerNameandAbbreviation {

	public static void main(String[] args) {
		try (SessionFactory sf = HibernateUtils.getSessionFactory();
				Scanner sc=new Scanner(System.in)) {
			//create user dao instance
			TeamsDao teamsDao = new TeamsDaoimpl();
			System.out.println("Enter max age : ");
			int age = sc.nextInt();
			System.out.println("Enter batting avg : ");
			double average = sc.nextDouble();
			
			teamsDao.displayOwnerNameAndAbbreviations(age, average).forEach(t->System.out.println(t.getOwner() + "\t" + t.getAbbreviation()));
			} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
