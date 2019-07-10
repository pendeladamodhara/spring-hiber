package com.example.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.UserDao;
import com.example.dto.UserDto;
import com.example.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		return userDao.getUser();
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public User update(User user, int id) {
		// TODO Auto-generated method stub
		return userDao.update(user, id);
	}

	@Override
	public void updateImageforUser(MultipartFile file, int id) throws IOException {

		userDao.updateImage(file, id);

	}

	@Override
	public List<User> search(UserDto user) {
		List<User> u = userDao.search(user);
		return u;
	}

}
