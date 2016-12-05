package carrental.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import carrental.domain.PassportInfo;
import carrental.domain.User;
import carrental.repository.PassportInfoDao;
import carrental.repository.UserDao;
import carrental.service.PassportInfoService;

@Controller
public class PassportInfoManager {
	private @Autowired PassportInfoDao passportInfoDao;
	private @Autowired UserDao userDao;
	private @Autowired PassportInfoService passportInfoService;

	private Integer getUserId(Principal principal){
		String email = principal.getName();
		User user = userDao.find(email);
		return user.getUserId();
	}
	
	@RequestMapping(value = "user/passportInfo", method = GET)
	public String getPassportInfoForm(Principal principal,
			Model m) {
		getPassportInfoForm(getUserId(principal), m);
		return "passportInfo";
	}
	
	@RequestMapping(value = "admin/passportInfo", method = GET)
	public String getPassportInfoFormForAdmin(@RequestParam("userId") Integer userId,
			Model m) {
		getPassportInfoForm(userId, m);
		m.addAttribute(userDao.find(userId));
		return "passportInfo";
	}
	
	private void getPassportInfoForm(Integer userId, Model m){
		PassportInfo passportInfo = passportInfoDao.find(userId);
		m.addAttribute(passportInfo == null ? new PassportInfo() : passportInfo);
		if ( passportInfo!= null ){
			m.addAttribute("series", passportInfo.getSeries());
			m.addAttribute("number", passportInfo.getNumber());
			m.addAttribute("additionalInfo", passportInfo.getAdditionalInfo());
			LocalDate issuedDate = new LocalDate(passportInfo.getIssuedMillis());
			m.addAttribute("issuedDay", issuedDate.getDayOfMonth());
			m.addAttribute("issuedMonth", issuedDate.getMonthOfYear());
			m.addAttribute("issuedYear", issuedDate.getYear());
		}
	}

	@RequestMapping(value = "user/passportInfo", method = POST)
	public String postPassportInfoForm(Principal principal,
			@Valid PassportInfo passportInfo){
		passportInfoService.save(passportInfo, getUserId(principal));
		return "redirect:/user/passportInfo";
	}
	
	@RequestMapping(value = "admin/passportInfo", method = POST)
	public String postPassportInfoFormForAdmin(@Valid PassportInfo passportInfo){
		passportInfoService.save(passportInfo);
		return "redirect:/admin/passportInfo?userId=" + passportInfo.getUserId();
	}

}
