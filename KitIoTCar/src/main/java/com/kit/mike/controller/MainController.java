package com.kit.mike.controller;

import com.kit.mike.dao.AppUserDao;
import com.kit.mike.dao.DeviceDao;
import com.kit.mike.dao.TopicDao;
import com.kit.mike.entity.AppUser;
import com.kit.mike.entity.Device;
import com.kit.mike.entity.Topic;
import com.kit.mike.formbean.AppUserForm;
import com.kit.mike.utils.WebUtils;
import com.kit.mike.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private AppUserValidator appUserValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form target
        Object target = dataBinder.getTarget();

        if(target == null) {
            return;
        }
        System.out.println("target " + target);
        if(target.getClass() == AppUserForm.class) {
            dataBinder.setValidator(appUserValidator);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String viewRegister(Model model, //
                               @ModelAttribute("appUserForm")  @Validated AppUserForm appUserForm ,//
                               BindingResult result, //
                               final RedirectAttributes redirectAttributes) {
        AppUser newUser= null;
        try {
            newUser = appUserDao.createAppUser(appUserForm);
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());

            System.out.println("Error: " + e.getMessage());
            System.out.println("result: " + redirectAttributes.getFlashAttributes());
            return "redirect:/login?errorRe=true";
        }

        redirectAttributes.addFlashAttribute("flashUser", newUser);
        return "redirect:/registerSuccessful";
    }

    @RequestMapping("/registerSuccessful")
    public String registerSuccessful(Model model) {
        return "registerSuccessfulPage";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page");
        AppUserForm form = new AppUserForm();
        model.addAttribute("appUserForm", form);
        return "loginPage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) throws SQLException {

        // (1) (en)
        // After user login successfully.
        // (vi)
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        List<Device> devices = this.deviceDao.findDevice(userName);
        List<Topic> topicList = this.topicDao.findTopic(userName);
        AppUser appUser = this.appUserDao.findUserAccount(userName);
        System.out.println("User Name: " + userName);
        for(Device d : devices) {
            System.out.println(d.toString());

        }


        model.addAttribute("devicesList", devices);
        model.addAttribute("numsDevices", devices.size());
        model.addAttribute("topicList", topicList);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("fullName", appUser.getFullName());
        model.addAttribute("topicList", topicList);

        return "userInfoPage";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }
}
