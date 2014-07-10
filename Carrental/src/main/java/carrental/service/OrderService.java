package carrental.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carrental.domain.Order;
import carrental.domain.OrderHistory;
import carrental.domain.OrderStatus;
import carrental.domain.Vehicle;
import carrental.repository.OrderDao;
import carrental.repository.OrderHistoryDao;
import carrental.repository.UserDao;
import carrental.repository.VehicleDao;

@Service
public class OrderService {
	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderHistoryDao orderHistoryDao;
	
	private Integer getUserId(Principal principal){
		String email = principal.getName();
		return userDao.find(email).getId();
	}
	
	public void fillOrderListAndVehicleMap(List<Order> orderList, Map<Integer, Vehicle> vehicleMap, Principal principal){
		fillOrderListAndVehicleMap(orderList, vehicleMap, getUserId(principal));
	}

	public void fillOrderListAndVehicleMap(List<Order> orderList, Map<Integer, Vehicle> vehicleMap, Integer userId){
		if (!orderList.isEmpty() || !vehicleMap.isEmpty())
			throw new IllegalArgumentException();
		orderList.addAll(orderDao.findByUser(userId));
		for (Order order : orderList) {
			Integer orderId = order.getOrderId();
			List<OrderHistory> orderHistories = new LinkedList<>();
			OrderHistory orderHistory = orderHistoryDao.findLatest(orderId);
			orderHistories.add(orderHistory);
			order.setOrderHistoryList(orderHistories);

			Integer vehicleId = order.getVehicleId();
			if (!vehicleMap.containsKey(vehicleId))
				vehicleMap.put(vehicleId, vehicleDao.find(vehicleId));
		}
	}
	
	public void cancelOrderByUser(Integer orderId){
		orderDao.update(orderId, OrderStatus.CANCELLED_BY_USER);
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setOrderId(orderId);
		orderHistory.setOrderStatus(OrderStatus.CANCELLED_BY_USER);
		orderHistory.setPayment(0);
		orderHistoryDao.create(orderHistory);
	}
	
	public void submitOrder(Integer vehicleId, Long startMillis, Long endMillis, Principal principal){
		submitOrder(vehicleId, startMillis, endMillis, getUserId(principal));
	}
	
	public void submitOrder(Integer vehicleId, Long startMillis, Long endMillis, Integer userId){
		Order order = new Order(userId, vehicleId, startMillis, endMillis,
				OrderStatus.WAITING_FOR_PHONE_CONFIRMATION, null);
		OrderHistory orderHistory = new OrderHistory();
		orderDao.create(order);
		orderHistory.setOrderId(order.getOrderId());
		orderHistory.setOrderStatus(order.getOrderStatus());
		orderHistory.setPayment(0);
		orderHistoryDao.create(orderHistory);
	}
	
	public void updateOrder(Integer orderId, OrderStatus orderStatus, Integer payment, Boolean hasReason, String reason){
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setOrderId(orderId);
		orderHistory.setOrderStatus(orderStatus);
		orderHistory.setPayment(payment);
		if (hasReason == Boolean.TRUE)
			orderHistory.setReason(reason);
		orderHistoryDao.create(orderHistory);
		orderDao.update(orderId, orderStatus);
	}
	
	public List<Order> getOrdersByStatuse(String...orderStatuses){
		EnumSet<OrderStatus> statuses = EnumSet.noneOf(OrderStatus.class);

		for (String strStatus : orderStatuses)
			try {
				statuses.add(OrderStatus.valueOf(strStatus));
			} catch (IllegalArgumentException ex) {
				// just skip bad parameter
			}

		List<Order> orderList = orderDao.list();
		List<Order> filteredOrderList = new ArrayList<>();
		for (Order order : orderList)
			if (statuses.contains(order.getOrderStatus()))
				filteredOrderList.add(order);
		return filteredOrderList;
	}

}
