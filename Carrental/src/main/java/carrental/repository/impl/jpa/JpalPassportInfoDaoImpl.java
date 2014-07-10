package carrental.repository.impl.jpa;

import org.springframework.stereotype.Repository;

import carrental.domain.PassportInfo;
import carrental.repository.PassportInfoDao;
import carrental.repository.impl.DefaultGenericCrudDao;

@Repository
public class JpalPassportInfoDaoImpl extends DefaultGenericCrudDao<PassportInfo> implements PassportInfoDao {

}
