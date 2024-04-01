package user;

import financialtransactions.TransactionManager;

public class BaseUser {
    String username;
    Authentication auth;
    TransactionManager manager = null;

    public BaseUser(String username, String password) {
        this.username = username;
        this.auth = new Authentication(username, password);
    }

    public String getUsername() {
        return username;
    }

    public boolean setTransactionManager(TransactionManager manager){
        if(this.manager == null){
            this.manager = manager;
            return true;
        }
        return false;
    }

    public TransactionManager getTransactionManager(){
        return this.manager;
    }

    public Authentication getAuthentication(){
        return this.auth;
    }
}
