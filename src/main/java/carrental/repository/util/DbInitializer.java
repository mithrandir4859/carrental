package carrental.repository.util;

import java.io.InputStream;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class DbInitializer implements InitializingBean {

	@Autowired
	private DataSource dataSource;

	@Override
	public void afterPropertiesSet() throws Exception {
		InputStream queriesInputStream = FileLoader.getResourceAsStream("dbCreation.sql");
		Class.forName("com.mysql.jdbc.Driver");
		FileLoader.executeQueries(queriesInputStream, dataSource);
	}

}
