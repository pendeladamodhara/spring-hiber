package com.example.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserDto;
import com.example.entity.User;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public List<User> getUser() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> list = session.createCriteria(User.class).list();
		return list;
	}

	@Override
	public User findById(int id) {
		Session session = sessionFactory.openSession();
		User user = session.get(User.class, id);
		System.out.println(user);
		return user;
	}

	@Override
	public User update(User val, int id) {

		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		user.setCountry(val.getCountry());
		user.setName(val.getName());
		session.update(user);
		return user;
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = findById(id);
		session.delete(user);
	}

	@Override
	public void updateImage(MultipartFile file, int id) throws IOException {
		byte[] bFile = file.getBytes();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User set userimage=:image where id=:userid");

		query.setParameter("image", bFile);
		query.setParameter("userid", id);
		query.executeUpdate();

	}

	@Override
	public List<User> search(UserDto user) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder sb = new StringBuilder("from User u ");

		System.out.println("search reaching");
		if (user.getId() >= 0 && user.getName() == null) {
			sb.append(" where u.id=:userid");
		} else if (user.getId() == 0 && user.getName() != null) {
			sb.append(" where u.name=:uname");
		} else {
			if (user.getId() >= 0 && user.getName() != null) {
				sb.append("where u.id=:userid and u.name=:uname");
			}
		}

		Query query = session.createQuery(sb.toString());
		if (user.getId() >= 0 && user.getName() == null)
			query.setParameter("userid", user.getId());
		if (user.getId() == 0 && user.getName() != null)
			query.setParameter("uname", user.getName());

		if (user.getId() > 0 && user.getName() != null) {
			query.setParameter("userid", user.getId());
			query.setParameter("uname", user.getName());
		}
		List<User> u = query.getResultList();
		System.out.println(u);
		return u;
	}
}
