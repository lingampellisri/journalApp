package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.JournalAppApplication;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class journelEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping()
    public ResponseEntity<?> getAll()
    {
        List<JournalEntry>all= journalEntryService.getAll();

        if(all!=null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping()
    public  ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry)
    {
        try {
            myEntry.setDate(LocalDate.now());
            journalEntryService.saveEntry(myEntry);
            return  new ResponseEntity<>(myEntry, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")

    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId)
    {
        Optional<JournalEntry> journalEntry= journalEntryService.findById(myId);
        if(journalEntry.isPresent())
        {
            return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/id/{myId}")

    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId myId)
    {
       journalEntryService.deleteById(myId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping ("/id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry )
    {

        JournalEntry old= journalEntryService.findById(myId).orElse(null);
        if(old!=null)
        {
            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ?myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ?myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);

            return new ResponseEntity<>(old,HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




}
