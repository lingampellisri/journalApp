package net.engineeringdigest.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    private static  final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


//    private static  final Logger logger= LoggerFactory.getLogger(UserService.class);
//

    public void saveNewEntry(User user)
    {
       try{
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRoles(Arrays.asList("USER"));
           UserRepository.save(user);
           log.error("Error occured for {} :",user.getUserName());
           log.info("haaha INfo");
           log.warn("haaha INfo");
           log.debug("haaha INfo");
           log.error("haaha INfo");


       }
       catch (Exception e)
       {
//           logger.info("haaha INfo");
//           logger.warn("haaha INfo");
//           logger.debug("haaha INfo");
//           logger.error("haaha INfo");
//           logger.trace("haaha INfo");

            // it never runs this catch block  due to my own implementation of class

           log.info("haaha INfo");
           log.warn("haaha INfo");
           log.debug("haaha INfo");
           log.error("haaha INfo");
           log.trace("haaha INfo");
       }
    }

    public void saveAdminEntry(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        UserRepository.save(user);
    }

    public void saveUser(User user)
    {
        UserRepository.save(user);
    }

    public List<User> getAll()
    {
        return UserRepository.findAll();
    }

    public Optional<User> findById(ObjectId Id)
    {
        return UserRepository.findById(Id);
    }

    public  boolean deleteById(ObjectId Id)
    {
        UserRepository.deleteById(Id);
        return true;
    }

    public  User findByUserName (String userName)
    {
        return UserRepository.findByUserName(userName);
    }


}
