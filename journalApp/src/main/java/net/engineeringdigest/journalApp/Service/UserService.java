package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    private static  final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public void saveNewEntry(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
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
