package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.JournalAppApplication;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class journelEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesByUserName()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry>all= user.getJournalEntries();

        if(all!=null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping()
    public  ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        try {
            myEntry.setDate(LocalDate.now());
            journalEntryService.saveEntry(myEntry,userName);
            return  new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")

    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId)
    {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user=userService.findByUserName(userName);

        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
            Optional<JournalEntry> journalEntry= journalEntryService.findById(myId);
            if(journalEntry.isPresent())
            {
                return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/id/{myId}")

    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId myId)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();


      boolean removed=  journalEntryService.deleteById(myId, userName);
      if(removed) {
        return new ResponseEntity<>(HttpStatus.OK);
      }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping ("/id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user=userService.findByUserName(userName);


        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
        Optional<JournalEntry> journalEntry= journalEntryService.findById(myId);
        if(journalEntry.isPresent()) {
            JournalEntry old = journalEntry.get();
            old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);

            return new ResponseEntity<>(old, HttpStatus.OK);
                 }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




}
