package by.epam.ivanchenko;


import by.epam.ivanchenko.entity.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
/* Если .PER_CLASS то методы в @BeforeAll и @AfterAll можно не делать статическими (для всех тестов - один и тот же объект)
*  Если .PER_METHOD(по умолчанию), то методы в @BeforeAll и @AfterAll должны быть статическими
*/

class UserServiceTest {

    private UserService userService;

    @BeforeAll
    void init() {
        System.out.println("Before all: " + this);
    }

    @BeforeEach
    void prepare() {
        userService = new UserService();
        System.out.println("\nBefore each: " + this);
    }



    @Test
    void usersEmptyIfNoUserAdded() {
        System.out.println("Test 1: " + this);
        var users = userService.getAll();
        assertTrue(users.isEmpty());
    }

    @Test
    void usersSizeIfUserAdded() {
        System.out.println("Test 2: " + this);
        userService.add(new User());
        userService.add(new User());

        var users = userService.getAll();
        assertEquals(2, users.size());
    }

    @AfterEach
    void deleteDataFromDB() {
        System.out.println("After each: " + this);
    }

    @AfterAll
     void closConnectionPool() {
        System.out.println("After all:" + this);
    }
}
