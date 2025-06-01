package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


//    @Disabled
//    @Test
    @ParameterizedTest
    @CsvSource({
            "ram",
            "lax",
            "ma"

    })
    public  void testFindByUserName(String name)
    {
        assertEquals(2,2);
      assertNotNull(userRepository.findByUserName(name));

      User user=userRepository.findByUserName("lax");
      assertTrue(!user.getJournalEntries().isEmpty());

    }



    @ParameterizedTest
    @ValueSource (strings={
            "ram",
            "lax",
            "ma"

    })
    public  void testFindByUserNameValueSource(String name)
    {
        assertEquals(2,2);
        assertNotNull(userRepository.findByUserName(name));

        User user=userRepository.findByUserName("lax");
        assertTrue(!user.getJournalEntries().isEmpty());

    }




    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"

    })

    public  void test(int a ,int b,int expected)
    {
        assertEquals(expected,a+b);
    }
}
