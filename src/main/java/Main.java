import dal.IUserDAO;
import dal.UserDAOImpls185099;
import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
       user.setRoles(Collections.singletonList("CD"));
       user.setIni("Sker der drenge");
       user.setUserName("Christoffer detlef");
       user.setUserId(5);
        UserDAOImpls185099 dao = new UserDAOImpls185099();
        try {
            dao.createUser(user);

        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }


    }


}

