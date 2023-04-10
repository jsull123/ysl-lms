package source;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestUserDataProcessor {

    private static ArrayList<User> users;
    private static UserList userList;

    @BeforeAll
    public static void oneTimeSetup() {
        userList = UserDataProcessor.loadData();
        users = new ArrayList<>(Arrays.asList(userList.toArray()));
    }

    @AfterEach
    public void tearDown() {
        users.clear();
    }

    @BeforeEach
	public void setup() { 
        userList.clear();
        userList.addUser(new User(
            UUID.randomUUID(),
            AccountType.fromInt(0), 
            "firstname",
            "lastname",
            "student123",
            "student123@gmail.com",
            "pass123",
            Date.fromString("12/02/2004"),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        userList.addUser(new User(
            UUID.randomUUID(),
            AccountType.fromInt(1), 
            "firstname",
            "lastname",
            "author123",
            "author123@gmail.com",
            "pass123",
            Date.fromString("10/15/1991"),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        UserDataProcessor.saveData(userList);
	}

    @AfterAll
    public static void oneTimeTearDown() {
        userList.clear();
        userList.add(users);
        UserDataProcessor.saveData(userList);
    }

    @Test
    public void testUserNum() {
        assertEquals(2, userList.numUsers());
    }

    @Test
    public void testAddUser() {
        UUID userID = UUID.randomUUID();
        userList.addUser(new User(
            userID,
            AccountType.fromInt(0), 
            "firstname",
            "lastname",
            "testStudent1",
            "testStudent1@gmail.com",
            "pass123",
            Date.fromString("04/22/1996"),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        assertEquals(userID, userList.getUser(userID).getID());
    }

    @Test
    public void testZeroUsers() {
        userList.clear();
        assertEquals(0, userList.numUsers());
    }

    @Test
    public void testNullUserID() {
        UUID userID = null;
        userList.addUser(new User(
            userID,
            AccountType.fromInt(0), 
            "firstname",
            "lastname",
            "testStudentnull",
            "testStudent1@gmail.com",
            "pass123",
            Date.fromString("06/01/1999"),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        assertNotNull(userList.getUser("testStudentnull").getID());
    }

    @Test
    public void testUsersWithSameID() {
        UUID userID = UUID.randomUUID();
        userList.addUser(new User(
            userID,
            AccountType.fromInt(0), 
            "firstname",
            "lastname",
            "testStudentSame1",
            "testStudent1@gmail.com",
            "pass123",
            Date.fromString("06/01/1995"),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        userList.addUser(new User(
            userID,
            AccountType.fromInt(0), 
            "firstname",
            "lastname",
            "testStudentSame2",
            "testStudent1@gmail.com",
            "pass123",
            Date.fromString("06/09/1995"),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        assertEquals(4, userList.numUsers());
    }

    @Test
    public void testUsersWithSameUserName() {
        String username = "sameUsername";
        UUID first = UUID.randomUUID();
        UUID second = UUID.randomUUID();
        userList.addUser(new User(
            first,
            AccountType.fromInt(0), 
            "firstname",
            "lastname",
            username,
            "testStudent1@gmail.com",
            "pass123",
            Date.fromString("06/01/1995"),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        userList.addUser(new User(
            second,
            AccountType.fromInt(0), 
            "firstname",
            "lastname",
            username,
            "testStudent1@gmail.com",
            "pass123",
            Date.fromString("06/09/1995"),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        assertEquals(4, userList.numUsers());
    }



}
