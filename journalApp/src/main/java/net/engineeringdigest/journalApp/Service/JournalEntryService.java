package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

@Transactional
    public void saveEntry(JournalEntry journalEntry,String userName)
    {
       try {
           User user=userService.findByUserName(userName);
           journalEntry.setDate(LocalDate.now());
           JournalEntry saved= journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveUser(user);
       }
       catch (Exception e)
       {
           System.out.println(e);
           throw  new RuntimeException("Error Occured in SaveEntry Function",e);
       }
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

    @Transactional
    public  boolean deleteById(ObjectId Id,String userName)
    {
        boolean removed=false;
        try{

            User user=userService.findByUserName(userName);
             removed=user.getJournalEntries().removeIf(x->x.getId().equals(Id));
            if(removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(Id);
                return removed;
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("Error While Deleting By Id");
        }
        return removed;

    }




}
