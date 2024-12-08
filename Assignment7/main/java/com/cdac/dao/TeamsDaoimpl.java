package com.cdac.dao;

import static com.cdac.utils.HibernateUtils.getSessionFactory;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.entitites.Teams;
import com.cdac.utils.HibernateUtils;


public class TeamsDaoimpl implements TeamsDao {

@Override
public String signUpTeams(Teams teams) {
		String mesg="Team registration failed!";
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		try {
			
			Serializable teamId = session.save(teams);
		
			tx.commit();
			mesg="Team signed up ! , ID "+teamId;
		} catch (RuntimeException e) {
			
			if(tx != null)
				tx.rollback();
			
			throw e;
		}
		return mesg;
	}
	
@Override
public List<Teams> getAllTeams() {
	String jpql = "select t from Teams t";
	List<Teams> teams = null;
	Session session = getSessionFactory().getCurrentSession();
	Transaction tx = session.beginTransaction();
	try {
		teams = session.createQuery(jpql, Teams.class).getResultList();
		tx.commit();
	} catch (RuntimeException e) {
		if (tx != null)
			tx.rollback();
		throw e;
	}

	return teams;
}

	
public List <Teams> getAllTeamsAgeAvg(int age, double avg){
List <Teams> teams = null;
	String jpql = "select t from Teams t where t.maxPlayerAge<:age and t.battingAverage>: average";
	Session session = getSessionFactory().getCurrentSession();
	Transaction tx = session.beginTransaction();
try {
	teams = session.createQuery(jpql, Teams.class).setParameter("age", age).setParameter("average", avg)
			.getResultList();
	tx.commit();
} catch (Exception e) {
	if (tx !=null)
		tx.rollback();
	throw e;
}

return teams;
}


@Override
public List<Teams> displayOwnerNameAndAbbreviations(int age, double avg) {
	List<Teams> teams=null;
	String jpql = "select new com.cdac.entitites.Teams(owner,abbreviation) from Teams t where t.maxPlayerAge<:age and t.battingAverage>:average";
	// 1. get Session from SessionFactory
	Session session = HibernateUtils.getSessionFactory().getCurrentSession();
	// 2. Begin Tx
	Transaction tx = session.beginTransaction();
	try {
		teams = session.createQuery(jpql, Teams.class)
				.setParameter("age", age)
				.setParameter("average", avg).getResultList();
		tx.commit();
	} catch (RuntimeException e) {
		// roll back the tx
		if (tx != null)
			tx.rollback();
		// re throw the same exception to the caller
		throw e;
	}

	return teams;
}

@Override
public String updateTeamMaxAge(String teamname ,int mAge) {
	String msg = "team updation failed..";
	String jpqlString = "select t from Teams t where t.name=:tName";
	Session session = HibernateUtils.getSessionFactory().getCurrentSession();
	Transaction tx = session.beginTransaction();
	try {
		Teams teams = session.createQuery(jpqlString, Teams.class).setParameter("tName", teamname).getSingleResult();
		teams.setMaxPlayerAge(mAge);
		msg = "team updated successfully..";
		tx.commit();
	} catch (RuntimeException e) {
		if (tx != null)
			tx.rollback();
		throw e;
	}
	return msg;
}

@Override
public String deleteTeam(Long teamid) {
	String msg = "team deletion operation failed...";
	Session session = HibernateUtils.getSessionFactory().getCurrentSession();
	Transaction tx = session.beginTransaction();
	try {
		Teams teams = session.get(Teams.class, teamid);
		if(teams != null) {
			session.delete(teams);
			msg = "team deleted successfully...";
		}
		tx.commit();
	} catch (RuntimeException e) {
		if (tx != null)
			tx.rollback();
		throw e;
	}
	return msg;
}	



}
