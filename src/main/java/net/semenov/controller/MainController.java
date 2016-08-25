package net.semenov.controller;

import net.semenov.model.User;
import net.semenov.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/main")
public class MainController {

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name = "userService")
    private UserService userService;


    @RequestMapping(value = "/users")
    public ModelAndView listOfUsers(@RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("list-of-users");

        List<User> users = userService.getAll();
        PagedListHolder<User> pagedListHolder = new PagedListHolder<User>(users);
        pagedListHolder.setPageSize(5);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) page = 1;

        modelAndView.addObject("page", page);
        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            modelAndView.addObject("users", pagedListHolder.getPageList());
        }

        modelAndView.setViewName("userspage");
        return modelAndView;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to show add page");

        // Create new User and add to model
        // This is the formBackingOBject
        model.addAttribute("personAttribute", new User());

        // This will resolve to /WEB-INF/jsp/addpage.jsp
        return "addpage";
    }


    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("personAttribute") User user) {
        logger.debug("Received request to add new user");

        // The "personAttribute" model has been passed to the controller from the JSP
        // We use the name "personAttribute" because the JSP uses that name

        // Call userService to do the actual adding
        userService.add(user);

        // This will resolve to /WEB-INF/jsp/addedpage.jsp
        return "addedpage";
    }


    @RequestMapping(value = "/users/delete")
    public String delete(@RequestParam(value = "id", required = true) Integer id,
                         Model model) {

        logger.debug("Received request to delete existing person");

        // Call userService to do the actual deleting
        userService.delete(id);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/deletedpage.jsp
        return "deletedpage";
    }


    @RequestMapping(value = "/users/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id", required = true) Integer id,
                          Model model) {
        logger.debug("Received request to show edit page");

        // Retrieve existing User and add to model
        // This is the formBackingOBject
        model.addAttribute("personAttribute", userService.get(id));

        // This will resolve to /WEB-INF/jsp/editpage.jsp
        return "editpage";
    }


    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("personAttribute") User user,
                           @RequestParam(value = "id", required = true) Integer id,
                           Model model) {
        logger.debug("Received request to update user");

        // The "personAttribute" model has been passed to the controller from the JSP
        // We use the name "personAttribute" because the JSP uses that name

        // We manually assign the id because we disabled it in the JSP page
        // When a field is disabled it will not be included in the ModelAttribute
        user.setId(id);

        // Delegate to userService for editing
        userService.edit(user);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/editedpage.jsp
        return "editedpage";
    }


    @RequestMapping(value = "/users/search", method = RequestMethod.GET)
    public String getSearch(@ModelAttribute("personAttribute") User user) {
        logger.debug("Received request to find user");


        return "search";
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public String getSearched(@ModelAttribute("personAttribute") User user, Model model) {
        logger.debug("Received request to find user");


        // Attach users to the Model
        model.addAttribute("users", userService.getByName(user.getName()));

        return "searched";
    }
}
