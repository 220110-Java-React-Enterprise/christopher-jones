package persistence;

public class AccountModel {


    private Integer accountId;
    private Double balance;

    private String accountType;
    private Integer userId;//FK to PK in UserModel

    public AccountModel() {
    }

    public AccountModel(Integer accountId, Double balance, String accountType, Integer userId) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
        this.userId = userId;
    }

    public AccountModel(Double balance, String accountType, Integer userId) {
        this.balance = balance;
        this.accountType = accountType;
        this.userId = userId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() { return accountId + " (" + accountType + "): " + "$" + String.format("%,.2f", balance);
    }
}
