import dal.IUserDAO;
import dal.UserDAOImpls185099;
import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {

       /* Scanner scan = new Scanner(System.in);
        System.out.println("id: ");
        int id = scan.nextInt();
        System.out.println("indhold: ");
        String indhold = scan.next();

        Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185099?" + "user=s185099&password=zhKW0aeedrH5Jvd9UDGJp");
        Statement statement = connection.createStatement();
        String sqlScan = "INSERT INTO test(id,indhold) VALUES ("+id+",'"+indhold+"')";
        //String sqlScan = "DELETE FROM test";

        statement.executeUpdate(sqlScan);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM test");
        System.out.println("Got resultset from database:");

        while (resultSet.next()){
            System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2));
        }*/

       UserDTO user = new UserDTO();
       ArrayList<String> roles = new ArrayList<>();
       roles.add("Student");
       roles.add("Araber");
       user.setRoles(roles);
       user.setIni("MA");
       user.setUserName("Mohammed Abu Baker");
       user.setUserId(6);
       UserDAOImpls185099 dao = new UserDAOImpls185099();

        try {
            List<UserDTO> userList = dao.getUserList();
            UserDTO userFromList = userList.get(1);

            System.out.println("roles: ");
            System.out.println(" ");
            for (int i = 0; i <userFromList.getRoles().size() ; i++) {
                System.out.println(userFromList.getRoles().get(i));
            }
            System.out.println(" ");
            System.out.println("Username: " + userFromList.getUserName());
            System.out.println("UserId: " + userFromList.getUserId());
            System.out.println("User initials: " + userFromList.getIni());

        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }

    }


}

