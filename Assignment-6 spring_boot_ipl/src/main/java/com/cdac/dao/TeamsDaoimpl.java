package com.cdac.dao;

import static com.cdac.utils.HibernateUtils.getSessionFactory;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.entitites.Teams;
import com.cdac.utils.HibernateUtils;


public class TeamsDaoimpl implements TeamsDao {

@Override
public String signUpTeams(Teams teams) {
		String mesg="Team registration failed!!!!!!!!!!";
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		try {
			
			Serializable teamId = session.save(teams);
		
			tx.commit();
			mesg="User signed up ! , ID "+teamId;
		} catch (RuntimeException e) {
			
			if(tx != null)
				tx.rollback();
			
			throw e;
		}
		return mesg;
	}
	
	
	
	
}
