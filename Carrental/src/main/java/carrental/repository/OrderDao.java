package carrental.repository;

import java.util.List;

import carrental.domain.Order;
import carrental.domain.OrderStatus;


public interface OrderDao extends GenericCrudDao<Order>{
	void update(Integer orderId, OrderStatus orderStatus);
	List<Order> findByUser(Integer userId);
	List<Order> findByVehicle(Integer vehicleId);
}
