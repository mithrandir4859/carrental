package carrental.repository.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import carrental.domain.OrderHistory;
import carrental.repository.OrderHistoryDao;
import carrental.repository.impl.DefaultGenericCrudDao;

@Repository
public class JpaOrderHistoryDaoImpl extends DefaultGenericCrudDao<OrderHistory> implements OrderHistoryDao {

	@Override
	public OrderHistory findLatest(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderHistory> findByOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
