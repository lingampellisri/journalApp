package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.JournalAppApplication;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class journelEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping()
    public List<JournalEntry> getAll()
    {
        return journalEntryService.getAll();
    }
    @PostMapping()
    public  JournalEntry createEntry(@RequestBody JournalEntry myEntry)
    {
        myEntry.setDate(LocalDate.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/id/{myId}")

    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId)
    {
        return   journalEntryService.findById(myId).orElse(null);
    }


    @DeleteMapping("/id/{myId}")

    public boolean deleteJournalEntryById(@PathVariable ObjectId myId)
    {
          return  journalEntryService.deleteById(myId);
    }



    @PutMapping ("/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry )
    {

        JournalEntry old= journalEntryService.findById(myId).orElse(null);
        if(old!=null)
        {
            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ?myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ?myEntry.getContent() : old.getContent());
        }

        journalEntryService.saveEntry(old);
        return old;

    }




}
