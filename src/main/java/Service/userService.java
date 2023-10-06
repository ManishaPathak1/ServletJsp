package Service;

import Model.User;
import dbConnection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userService {
    public void insertUser(User user) {
        String query = "insert into usertable(username,email,address,password)" + "values(?,?,?,?)";
        PreparedStatement preparedStatement = new DBConnection().getStatement(query);
        try {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String query = "delete from usertable where id=?";
        PreparedStatement preparedStatement = new DBConnection().getStatement(query);
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //loginuser
    public User getUserLogin(String username, String password) {
        User user = null;
        String query = "Select * from usertable where username=? and password=?";
        PreparedStatement preparedStatement = new DBConnection().getStatement(query);
        try {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName((rs.getString("username")));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getUserList() {
        //list of users:
        List<User> userList = new ArrayList<>();

        //query:
        String query = "select * from usertable";

        //prepared stm:
        PreparedStatement preparedStatement = new DBConnection().getStatement(query);

        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName((rs.getString("username")));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // GetRow data:
    public User getUserRow(int id) {

        User user = new User();
        String query = "select * from usertable where id=?";
        PreparedStatement preparedStatement = new DBConnection().getStatement(query);
        try {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                user.setId(rs.getInt("id"));
                user.setUserName((rs.getString("username")));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //sir teached
    public void editUser(int id, User user) throws SQLException{

        //step 1:
        String query = "update usertable set username=?, email=?, address=?, password=?" + "where id=?";

        //step 2:
        PreparedStatement preparedStatement = new DBConnection().getStatement(query);

        //step 3:
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getAddress());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setInt(5, id);
        preparedStatement.execute();
    }
// i did
//    public void editUser(User user) {
//        String query = "update into usertable(username,email,address,password)" + "values(?,?,?,?)";
//        PreparedStatement preparedStatement = new DBConnection().getStatement(query);
//        try {
//            preparedStatement.setString(1, user.getUserName());
//            preparedStatement.setString(2, user.getEmail());
//            preparedStatement.setString(3, user.getAddress());
//            preparedStatement.setString(4, user.getPassword());
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    //}
}

