package net.engineeringdigest.journalApp.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {

    @GetMapping("health")
    public  String healthCheck()
    {
        return "Health is Ok <h4> Srinivas lingampelli </h4>";
    }
}
