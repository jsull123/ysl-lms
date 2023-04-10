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
    public static void oneTimeSetup() {
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
    }
}
