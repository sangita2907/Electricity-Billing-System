package com.electricityBillingSystem.dao;

import com.electricityBillingSystem.beans.User;
import com.electricityBillingSystem.beans.UserRequest;
import com.electricityBillingSystem.exceptions.InvalidUseridPasswordException;
import com.electricityBillingSystem.util.HibernateUtil;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class SystemDAO implements ISystemDAO{

	@Override
	public User validateUserCredentials(String userId, String userPassword) throws InvalidUseridPasswordException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(criteriaBuilder.equal(root.get("id"), userId),
				criteriaBuilder.equal(root.get("phoneNumber"), userPassword));

		Query<User> userQuery = session.createQuery(query);
		User user = null;
		try {
			user = userQuery.getSingleResult();
		} catch (NoResultException e) {
			throw new InvalidUseridPasswordException();
		}
		return user;
	}

	@Override
	public void addConnectionRequest(UserRequest userReq) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		session.save(userReq);
		session.getTransaction().commit();
	}

}
