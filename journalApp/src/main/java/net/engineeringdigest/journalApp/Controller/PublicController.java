package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/health")
    public  String healthCheck()
    {
        return "Health is Ok <h4> Srinivas lingampelli </h4>";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user)
    {
        userService.saveNewEntry(user);
    }

}
