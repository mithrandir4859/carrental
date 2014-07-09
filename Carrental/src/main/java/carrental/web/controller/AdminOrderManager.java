package carrental.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import carrental.domain.Order;
import carrental.domain.OrderStatus;
import carrental.domain.User;
import carrental.domain.Vehicle;
import carrental.repository.OrderDao;
import carrental.repository.OrderHistoryDao;
import carrental.repository.UserDao;
import carrental.repository.VehicleDao;
import carrental.service.OrderService;

@Controller
@RequestMapping("admin")
public class AdminOrderManager {
	private @Autowired
	OrderDao orderDao;
	private @Autowired
	VehicleDao vehicleDao;
	private @Autowired
	UserDao userDao;
	private @Autowired
	OrderHistoryDao orderHistoryDao;
	
	private @Autowired OrderService orderService;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String getOrdersWithSpecifiedStatuses(@RequestParam("orderStatus") String[] orderStatuses,
			Model m) {
		m.addAttribute(orderService.getOrdersByStatuse(orderStatuses));
		return "displaySelectedOrders";
	}

	@RequestMapping(value = "/editOrder", method = RequestMethod.GET)
	public String getEditOrderForm(Model m, @RequestParam("orderId") Integer orderId) {

		Order order = orderDao.find(orderId);
		order.setOrderHistoryList(orderHistoryDao.find(order.getOrderId()));
		
		Vehicle vehicle = vehicleDao.find(order.getVehicleId());
		User user = userDao.find(order.getUserId());

		m.addAttribute(order);
		m.addAttribute(vehicle);
		m.addAttribute(user);
		m.addAttribute("orderStatuses", OrderStatus.values());
		return "admin/editOrder";
	}

	@RequestMapping(value = "/editOrder", method = RequestMethod.POST)
	public String postEditOrderForm(Model m,
			@RequestParam("orderId") Integer orderId,
			@RequestParam("orderStatus") OrderStatus orderStatus,
			@RequestParam("payment") Integer payment,
			@RequestParam(value = "hasReason", required = false) Boolean hasReason,
			@RequestParam(value = "reason", required = false) String reason
			) {
		orderService.updateOrder(orderId, orderStatus, payment, hasReason, reason);
		return "redirect:/admin/editOrder?orderId=" + orderId;
	}

}
