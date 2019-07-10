package com.example.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserDto;
import com.example.entity.User;

public interface UserDao {

	public void addUser(User user);

	public List<User> getUser();

	public User findById(int id);

	public User update(User user, int id);

	public void delete(int id);

	public void updateImage(MultipartFile file, int id) throws IOException;

	public List<User> search(UserDto user);
}
