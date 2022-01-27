package persistence;

import utilities.ConnectionManager;
import utilities.CustomLinkedList;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountRepo implements RepoCRUD<AccountModel> {
    @Override
    public Integer create(AccountModel accountModel) throws SQLException, IOException {
        String sql = "INSERT INTO accounts (balance, account_type, user_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setDouble(1, accountModel.getBalance());
        pstmt.setString(2, accountModel.getAccountType());
        pstmt.setInt(3, accountModel.getUserId());

        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

        return null;

    }

    @Override
    public AccountModel read(Integer id) throws SQLException, IOException {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            AccountModel accountModel = new AccountModel();
            accountModel.setAccountId(rs.getInt("account_id"));
            accountModel.setBalance(rs.getDouble("balance"));
            accountModel.setAccountType(rs.getString("account_type"));
            accountModel.setUserId(rs.getInt("user_id"));
            return accountModel;
        }
        return null;
    }

    @Override
    public AccountModel update(AccountModel accountModel) throws SQLException, IOException {
        String sql = "UPDATE accounts SET balance = ?, account_type = ? WHERE account_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setDouble(1, accountModel.getBalance());
        pstmt.setString(2, accountModel.getAccountType());
        pstmt.setInt(3, accountModel.getAccountId());
        pstmt.executeUpdate();

        return accountModel;

    }

    @Override
    public void delete(Integer id) throws SQLException, IOException {
        String sql = "DELETE FROM items WHERE item_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public CustomLinkedList<AccountModel> getAllItemsByUserId(Integer id) throws SQLException, IOException {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        CustomLinkedList<AccountModel> accountList = new CustomLinkedList<>();

        while (rs.next()) {
            AccountModel account = new AccountModel();
            account.setAccountId(rs.getInt("account_id"));
            account.setBalance(rs.getDouble("balance"));
            account.setAccountType(rs.getString("account_type"));
            account.setUserId(rs.getInt("user_id"));
            accountList.add(account);
        }
        return accountList;
    }
}