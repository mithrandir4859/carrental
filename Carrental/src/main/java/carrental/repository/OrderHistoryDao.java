package carrental.repository;

import java.util.List;

import carrental.domain.OrderHistory;

public interface OrderHistoryDao extends GenericCrudDao<OrderHistory> {
	OrderHistory findLatest(Integer orderId);
	List<OrderHistory> findByOrder(Integer orderId);
}
