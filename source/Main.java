package source;

public class Main{
    public static void main(String[] args){
        UserList userList = UserDataProcessor.loadData();
        
        //System.out.println(userList.dbgToString());

        UserDataProcessor.saveData(userList);
    }
}