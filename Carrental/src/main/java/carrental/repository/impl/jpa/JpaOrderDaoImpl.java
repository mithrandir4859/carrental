package carrental.repository.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import carrental.domain.Order;
import carrental.domain.OrderStatus;
import carrental.repository.OrderDao;
import carrental.repository.impl.DefaultGenericCrudDao;

@Repository
public class JpaOrderDaoImpl extends DefaultGenericCrudDao<Order> implements OrderDao {

	@Override
	public List<Order> findByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findByVehicle(Integer vehicleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Integer orderId, OrderStatus orderStatus) {
		// TODO Auto-generated method stub

	}

}
