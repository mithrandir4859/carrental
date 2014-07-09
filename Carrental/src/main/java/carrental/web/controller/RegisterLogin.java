package carrental.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import carrental.domain.User;
import carrental.domain.UserStatus;
import carrental.repository.UserDao;

@Controller
public class RegisterLogin {
	@Autowired private UserDao userDao;

	@RequestMapping(value = "anon/register", method = GET)
	public String getRegisterForm(Model m) {
		m.addAttribute(new User());
		return "anon/register";
	}

	@RequestMapping(value = "anon/register", method = POST)
	public String postRegisterForm(
			@Valid User user,
			BindingResult bindingResult,
			Model m,
			@RequestParam("passwordConfirm") String passwordConfirm) {
		if (bindingResult.hasErrors())
			return "anon/register";
		user.setUserStatus(UserStatus.USER);
		userDao.create(user);
		return "redirect:login?register";
	}

	// Spring Security see this :
	@RequestMapping(value = "/anon/login", method = GET)
	public ModelAndView getLoginForm(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "register", required = false) String register) {

		ModelAndView model = new ModelAndView();
		if (error != null)
			model.addObject("error", "Invalid username and password!");

		if (logout != null)
			model.addObject("msg", "You've been logged out successfully.");
		else if (register != null)
			model.addObject("msg", "Registered successfully, now you can login");

		model.setViewName("anon/login");
		return model;
	}

}
