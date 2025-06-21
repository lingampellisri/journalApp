package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.Service.weatherService;
import net.engineeringdigest.journalApp.api.response.weatherResponse;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

@Autowired
  private   UserService userService;



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private weatherService weatherServicee;

    @GetMapping
        public List<User> getAllJournalEntriesOfUser()
    {
        return userService.getAll();
    }




    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

      User userInDb=userService.findByUserName(userName);

          userInDb.setUserName(user.getUserName());
          userInDb.setPassword(user.getPassword());
          userService.saveUser(userInDb);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping()

    public ResponseEntity<JournalEntry> deleteJournalEntryById( )
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/Greet")
    public ResponseEntity<?> greets( )
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        weatherResponse weatherResponsee=weatherServicee.getWeather("Mumbai");
        String greetings=" ";

        if(weatherResponsee!=null)
        {
            greetings=", Weather feels like "+weatherResponsee.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hi "+authentication.getName() +greetings,  HttpStatus.OK);
    }


}
