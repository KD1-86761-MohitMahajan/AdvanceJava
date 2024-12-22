package com.cdac.tester;

import static com.cdac.utils.HibernateUtils.getSessionFactory;

import org.hibernate.SessionFactory;

import com.cdac.dao.TeamsDao;
import com.cdac.dao.TeamsDaoimpl;



public class DisplayAll {

	public static void main(String[] args) {
		try (SessionFactory sf = getSessionFactory()) {
			
			TeamsDao teamsDao = new TeamsDaoimpl();
			teamsDao.getAllTeams().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
