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

public class UserListTest {
   
    private static ArrayList<User> users;
    private static UserList userList;

    @BeforeAll
    public static void oneTimeSetup() {
        userList = UserDataProcessor.loadData();
        users = new ArrayList<>(Arrays.asList(userList.toArray()));
    }
   
    @AfterAll
    public static void oneTimeTearDown() {
        userList.clear();
        userList.add(users);
        UserDataProcessor.saveData(userList);
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

    @AfterEach
    public void tearDown() {
        users.clear();
    }

    @Test
    public void testGetInstance() {
        assertEquals(userList, userList.getInstance(users));
    }

    @Test
    public void testToArraySize() {
        User[] userListArray = userList.toArray();
        assertEquals(2, userListArray.length);
    }

    @Test
    public void testToArrayContent() {
        User[] userListArray = userList.toArray();
        assertNotNull(userListArray);
    }

    @Test
    public void testNumUsers() {
        assertEquals(2, userList.numUsers());
    }

    @Test
    public void testClear() {
        userList.clear();
        assertEquals(0, userList.numUsers());
    }
}
