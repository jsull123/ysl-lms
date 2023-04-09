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

public class TestUserDataProcessor {

    private static ArrayList<User> users;
    private static UserList userList;

    @BeforeAll
    public static void oneTimeSetup() {
        userList = UserDataProcessor.loadData();
        users = new ArrayList<>(Arrays.asList(userList.toArray()));
    }

    @AfterEach
    public static void tearDown() {
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
        userList.clear();
	}

    @Test
    public static void testUserNum() {
        userList.addUser(new User(
        UUID.randomUUID(),
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
        assertEquals(3, userList.numUsers());
    }

    @AfterAll
    public static void oneTimeTearDown() {
        userList.clear();
        userList.add(users);
        UserDataProcessor.saveData(userList);
    }

}
