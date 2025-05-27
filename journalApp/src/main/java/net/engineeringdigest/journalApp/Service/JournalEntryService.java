package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;


    public void saveEntry(JournalEntry journalEntry,String userName)
    {
       User user=userService.findByUserName(userName);
       journalEntry.setDate(LocalDate.now());
       JournalEntry saved= journalEntryRepository.save(journalEntry);
       user.getJournalEntries().add(saved);
       userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry)
    {
     journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId Id)
    {
        return journalEntryRepository.findById(Id);
    }

    public  void deleteById(ObjectId Id,String userName)
    {

        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(Id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(Id);

    }


}
