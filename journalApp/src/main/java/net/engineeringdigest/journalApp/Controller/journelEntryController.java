package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.JournalAppApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class journelEntryController {

    private  HashMap<Long, JournalEntry> JournalEntries=new HashMap<>();


    @GetMapping()
    public List<JournalEntry> getAll()
    {
        return new ArrayList<>(JournalEntries.values());
    }
    @PostMapping
    public  boolean createEntry(@RequestBody JournalEntry myEntry)
    {
    JournalEntries.put(myEntry.getId(),myEntry);
    return true;
    }

}
