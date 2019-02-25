package dal;

import com.mysql.cj.protocol.Resultset;
import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAOImpls185099 implements IUserDAO {

    //Jeg var ikke sikker på hvad felterne 'ini' og 'roles' skulle repræsentere, så i min database er 'ini vores måde at hilse på, og 'roles' vores initialer.

    Connection connection;
    Statement statement;

    {
        try {
            connection = createConnection();
            statement = connection.createStatement();
        } catch (DALException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    private Connection createConnection() throws DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185099?" + "user=s185099&password=zhKW0aeedrH5Jvd9UDGJp");
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        UserDTO user = new UserDTO();
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM databaser1 WHERE userID =" + userId);
            System.out.println("Got resultset from database:");

            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2)+ " " + resultSet.getString(3) + " " + resultSet.getString(4));
                user.setUserId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setIni(resultSet.getString(3));
                user.setRoles(Collections.singletonList(resultSet.getString(4)));
            }

        connection.close();

        return user;}catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }



    @Override
    public List<UserDTO> getUserList() throws DALException {
        ArrayList<UserDTO> users = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM databaser1");
            while (resultSet.next()){
                UserDTO user = new UserDTO();
                user.setUserId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setIni(resultSet.getString(3));
                user.setRoles(Collections.singletonList(resultSet.getString(4)));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        try {
            String create = "INSERT INTO databaser1 (userID, username, ini, roles) VALUES (" + user.getUserId() + ",'" + user.getUserName() + "','" + user.getIni() + "','" + user.getRoles() + "')";
            statement.executeUpdate(create);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        try{
            String update = "UPDATE databaser1 SET userID ="+user.getUserId()+", username='"+user.getUserName()+"', ini ='"+user.getIni()+"', roles='"+user.getRoles()+"'  WHERE userID = "+user.getUserId();
            statement.executeUpdate(update);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        try{
            String delete = "DELETE FROM databaser1 WHERE userID ="+userId;
            statement.executeUpdate(delete);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
