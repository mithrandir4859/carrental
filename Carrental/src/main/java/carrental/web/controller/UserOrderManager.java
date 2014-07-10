package carrental.web.controller;

import static carrental.controllers.LocalDateEditor.DATE_PATTERN;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTimeFieldType;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import carrental.domain.Order;
import carrental.domain.Vehicle;
import carrental.repository.VehicleDao;
import carrental.service.OrderService;

@Controller
@RequestMapping("user")
public class UserOrderManager {
	@Autowired	private VehicleDao vehicleDao;
	@Autowired private OrderService orderService;

	@RequestMapping(value = "/chooseOrderDates", method = GET)
	public String getChooseOrderDatesForm(Model m,
			@RequestParam(value = "startDay", required = false) Integer startDay,
			@RequestParam(value = "startMonth", required = false) Integer startMonth,
			@RequestParam(value = "startYear", required = false) Integer startYear,
			@RequestParam(value = "endDay", required = false) Integer endDay,
			@RequestParam(value = "endMonth", required = false) Integer endMonth,
			@RequestParam(value = "endYear", required = false) Integer endYear) {

		LocalDate now = LocalDate.now();
		Integer year = now.get(DateTimeFieldType.year());
		Integer month = now.get(DateTimeFieldType.monthOfYear());
		Integer day = now.get(DateTimeFieldType.dayOfMonth());

		m.addAttribute("currentYear", year);
		m.addAttribute("endYear", endYear == null ? year : endYear);
		m.addAttribute("endMonth", endMonth == null ? month : endMonth);
		m.addAttribute("endDay", endDay == null ? day : endDay);
		m.addAttribute("startYear", startYear == null ? year : startYear);
		m.addAttribute("startMonth", startMonth == null ? month : startMonth);
		m.addAttribute("startDay", startDay == null ? day : startDay);

		return "user/chooseOrderDates";
	}

	@RequestMapping(value = "/chooseOrderVehicle", method = GET)
	public String getChooseVehicleForm(Model m,
			@RequestParam("startDay") Integer startDay,
			@RequestParam("startMonth") Integer startMonth,
			@RequestParam("startYear") Integer startYear,
			@RequestParam("endDay") Integer endDay,
			@RequestParam("endMonth") Integer endMonth,
			@RequestParam("endYear") Integer endYear) {

		long startMillis = new LocalDate(startYear, startMonth, startDay).toDateTimeAtStartOfDay().getMillis();
		long endMillis = new LocalDate(endYear, endMonth, endDay).toDateTimeAtStartOfDay().getMillis();
		Interval interval = new Interval(startMillis, endMillis);
		List<Vehicle> vehicleList = vehicleDao.findAvailable(interval);
	
		m.addAttribute("startMillis", startMillis);
		m.addAttribute("endMillis", endMillis);
		m.addAttribute(vehicleList);

		return "user/chooseOrderVehicle";
	}

	@RequestMapping(value = "/submitOrder", method = GET)
	public String getOrderSubmitForm(Model m,
			@RequestParam("startMillis") Long startMillis,
			@RequestParam("endMillis") Long endMillis,
			@RequestParam("vehicleId") Integer vehicleId) {

		m.addAttribute(vehicleDao.find(vehicleId));
		m.addAttribute("startDate", new LocalDate(startMillis).toString(DATE_PATTERN));
		m.addAttribute("endDate", new LocalDate(endMillis).toString(DATE_PATTERN));
		m.addAttribute("startMillis", startMillis);
		m.addAttribute("endMillis", endMillis);

		return "user/submitOrder";
	}

	@RequestMapping(value = "/submitOrder", method = POST)
	public String postOrderSubmitForm(
			@RequestParam("vehicleId") Integer vehicleId,
			@RequestParam("startMillis") Long startMillis,
			@RequestParam("endMillis") Long endMillis,
			Principal principal
			) {
		orderService.submitOrder(vehicleId, startMillis, endMillis, principal);
		// logger.info("Order " + order.getOrderId() + " was created");
		return "redirect:/user/myOrders";
	}

	@RequestMapping("/myOrders")
	public String getMyOrders(Model m, Principal principal) {
		List<Order> orderList = new ArrayList<Order>();
		Map<Integer, Vehicle> vehicleMap = new HashMap<Integer, Vehicle>();
		orderService.fillOrderListAndVehicleMap(orderList, vehicleMap, principal);
		m.addAttribute("vehicleMap", vehicleMap);
		m.addAttribute(orderList);
		return "displaySelectedOrders";
	}

	@RequestMapping(value = "/cancelOrder", method = POST)
	public String cancelOrder(@RequestParam("orderId") Integer orderId) {
		orderService.cancelOrderByUser(orderId);
		// logger.info("Order " + orderId + " is cancelled by user");
		return "redirect:/user/myOrders";
	}
}
