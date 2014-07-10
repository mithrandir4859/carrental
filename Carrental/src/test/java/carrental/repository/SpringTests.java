package carrental.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import carrental.domain.User;
import carrental.domain.UserStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTests {

	@Autowired
	UserDao userDao;
	
	@PersistenceContext
	EntityManager em;

	User user = new User("somemail@gmail.com", "somepassword", "Yurii", "Andrieiev", "80930987499", UserStatus.USER, null);
	
	@Test
	public void testSayHello() {
		userDao.create(user);
		System.out.println(user.getId());
//		userDao.find(user.getId());
	}
	
	@Test
	@Transactional
	public void testEm(){
//		em.getTransaction().begin();
		em.persist(user);
		em.flush();
//		em.getTransaction().commit();
	}
}