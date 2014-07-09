package carrental.repository;

import carrental.domain.User;

public interface UserDao extends GenericCrudDao<User>{
	User find(String email, String password);
	User find(String email);
	void changePassword(Integer userId, String oldPassword, String newPassword) throws IllegalArgumentException;
}