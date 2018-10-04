package com.exercise.auth.web;

import com.exercise.auth.model.Email;
import com.exercise.auth.model.User;
import com.exercise.auth.repository.EmailRepository;
import com.exercise.auth.repository.UserRepository;
import com.exercise.auth.service.SecurityService;
import com.exercise.auth.service.UserService;
import com.exercise.auth.validator.UserValidator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
	UserRepository userrepository;
    
    @Autowired
    EmailRepository emailrepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.automaticlogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    
    @RequestMapping("/getCustomers")
	public ModelAndView getEmployeesCustomers() {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	System.out.println("Logged in UserName-->"+currentPrincipalName);
		//List<Email> emailList = (List<Email>) emailrepository.findAll();
    	List<Email> emailList = (List<Email>) emailrepository.findByUserName(currentPrincipalName);
		ModelAndView model = new ModelAndView("getCustomers");
		model.addObject("email", emailList);
		return model;
	}
    
    
    @RequestMapping("/sendMail")
   	public ModelAndView sendMail(@ModelAttribute("cus") User cus) {
   		List<User> users = (List<User>) userrepository.findAll();
   		ModelAndView model = new ModelAndView("sendMail");
   		model.addObject("users", users);
   		
   		System.out.println("==> For Loop Example.");
		for (int i = 0; i < users.size(); i++) {
			System.out.println("Users List -->"+users.get(i));
			User user = users.get(i);
			user.getUsername();
		}
		
		/*
		User user = userrepository.findByUsername(cus.getUsername());
		user.getEmail();
		user.setPassword(cus.getPassword());
		user.setPasswordConfirm(cus.getPasswordConfirm());
		userrepository.save(user);
   		*/
   		
   		return model;
   	}

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value = "/saveMail", method = RequestMethod.POST)
	public ModelAndView processRequest(@ModelAttribute("cus") User cus) {

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();

    	Email email = new Email();
    	System.out.println("UserName-->"+currentPrincipalName);
    	email.setName(currentPrincipalName);
    	System.out.println("Email-->"+cus.getEmail());
    	email.setMessage(cus.getEmail());
    	emailrepository.save(email);
    	List<User> users = (List<User>) userrepository.findAll();
   		ModelAndView model = new ModelAndView("sendMail");
   		model.addObject("users", users);
		return model;
	}
}
