package source;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UserListTest {

    private ArrayList<User> users;
    private UserList userList;
    private HashMap<UUID, User> Users;
    private HashMap<UUID, String> passwordMap;

    @BeforeAll
    public void oneTimeSetup() {
        UUID ID1 = UUID.randomUUID();
        UUID ID2 = UUID.randomUUID();
        UUID ID3 = UUID.randomUUID();
        AccountType user1type = null;
        user1type = user1type.fromString("AUTHOR");
        AccountType user2type = null;
        user1type = user2type.fromString("DRAGON");
        AccountType user3type = null;
        user1type = user3type.fromString("STUDENT");
        Date date1 = new Date();
        Date date2 = new Date(13,32,2025);
        Date date3 = new Date(11,17,2020);
        ArrayList<UUID> createdCourses = new ArrayList<UUID>();
        ArrayList<EnrolledCourse> EC = new ArrayList<EnrolledCourse>();
        User user1 = new User(ID1, user1type, "Johnny", "Test", "PS69",
        "u1@email.com", "$#%&*//", date1, createdCourses, null);
        User user2 = new User(ID2, user2type, "Mifune", "Shisui", "Shinobi",
        "hiddenleaf@email.com", "54*&#$!", date2, null, null);
        User user3 = new User(ID3, user3type, "Itachi", "Uchiha", "KageSmart",
        "akatsuki@email.com", "Sasuke", date3, null, EC);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
    @BeforeEach
    public void setup() {
        userList = userList.getInstance(users);
    }
    @AfterAll
    public void tearDown() {
        userList.clear();
    }
    @Test
    public void testAdd() {
        User user4 = new User(null, null, "Mifune", "Shisui", "NinjaWay",
        "hiddenleaf@email.com", "54*&#$!", null, null, null);
        User user5 = new User(null, null, "Itachi", "Uchiha", "HiddenSand",
        "akatsuki@email.com", "Sasuke", null, null, null);
        ArrayList<User> newUsers = new ArrayList<User>();
        newUsers.add(user4);
        newUsers.add(user5);
        userList.add(newUsers);
        assertEquals(user5, userList.getUser("HiddenSand"));
    }
    @Test
    public void testAddUSer() {
        User user6 = new User(null, null, "NAruto", "Uzumaki", "BorutoDad",
        "hiddenleef@email.com", "54*&#$!", null, null, null);
        userList.addUser(user6);
        assertEquals(user6, userList.getUser("BorutoDad"));
    }
    @Test
    public void testToArray() {
        User[] userArray = new User[users.size()];
        userArray = userList.toArray();
        assertEquals(users.get(0), userArray[0]);
    }

}
