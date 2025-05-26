package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.JournalAppApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/_journal")
public class journelEntryController {



    @GetMapping()
    public List<JournalEntry> getAll()
    {
        return null;


    }
    @PostMapping()
    public  boolean createEntry(@RequestBody JournalEntry myEntry)
    {
        return false;
    }

    @GetMapping("/id/{myId}")

    public JournalEntry getJournalEntryById(@PathVariable Long myId)
    {
        return null;
    }


    @DeleteMapping("/id/{myId}")

    public JournalEntry deleteJournalEntryById(@PathVariable Long myId)
    {

        return null;
    }



    @PutMapping ("/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry myEntry )
    {
        return null;
    }




}
