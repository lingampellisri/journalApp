package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public void saveEntry(User user)
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
