package persistence;

import utilities.ConnectionManager;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepo implements RepoCRUD<UserModel>{

    @Override
    public Integer create(UserModel userModel) throws SQLException, IOException {
        String sql = "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, userModel.getEmail());
        pstmt.setString(2, userModel.getUsername());
        pstmt.setString(3, userModel.getPassword());

        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);

    }

    @Override
    public UserModel read(Integer id) throws SQLException, IOException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        UserModel user = new UserModel();
        if(rs.next()) {
            user.setUserId(rs.getInt("user_id"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } else {
            return null;
        }
    }

    @Override
    public UserModel update(UserModel userModel) throws SQLException, IOException {
        String sql = "UPDATE users SET email = ?, username = ?, password = ? WHERE user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, userModel.getEmail());
        pstmt.setString(2, userModel.getUsername());
        pstmt.setString(3, userModel.getPassword());
        pstmt.setInt(4, userModel.getUserId());

        pstmt.executeUpdate();

        String verify = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement vstmt = ConnectionManager.getConnection().prepareStatement(verify);
        pstmt.setInt(1, userModel.getUserId());
        ResultSet rs = vstmt.executeQuery();

        if(rs.next()) {
            UserModel verifiedUserModel = new UserModel();
            verifiedUserModel.setUserId(rs.getInt("user_id"));
            verifiedUserModel.setEmail(rs.getString("email"));
            verifiedUserModel.setUsername(rs.getString("username"));
            verifiedUserModel.setPassword(rs.getString("password"));
            return verifiedUserModel;
        }

        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException, IOException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public Boolean uniqueUsername (String username) throws SQLException, IOException {
        Boolean unique = true;
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            unique = false;
        }
        return unique;
    }

    public Boolean validEmail (String email) throws SQLException, IOException {
        Boolean valid = false;
        Boolean unique = true;
        Boolean form = false;
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            unique = false;
        }
        if(email.matches("^([0-9a-zA-Z.]+@[0-9a-zA-Z]+[.]+[a-zA-z]+){1,40}$")) {
            form = true;
        }
        if(form && unique) {
            valid = true;
        }
        return valid;
    }

    public UserModel authenticate(String username, String password) throws SQLException, IOException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next() && rs.getString("password").equals(password)) {
            return new UserModel(rs.getInt("user_id"), rs.getString("email"), rs.getString("username"), rs.getString("password"));
        }
        return null;
    }
}