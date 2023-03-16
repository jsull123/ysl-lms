package source;

public class Main{
    public static void main(String[] args){
        UserList userList = UserDataProcessor.loadData();

        String s = userList.dbgToString();
        System.out.println(userList.dbgToString());
    }
}