package carrental.repository.impl.jpa;

import org.springframework.stereotype.Repository;

import carrental.domain.User;
import carrental.repository.UserDao;
import carrental.repository.impl.DefaultGenericCrudDao;

@Repository
public class JpaUserDaoImpl extends DefaultGenericCrudDao<User> implements UserDao {

	@Override
	public User find(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User find(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(Integer userId, String oldPassword, String newPassword){
		// TODO Auto-generated method stub
		
	}

}
