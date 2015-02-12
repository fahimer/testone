package view;

import controller.Controller;
import model.accountInterface;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("acct")
@ConversationScoped
public class AcctManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB

    private Controller controller;
    private String username;
    private String password;
    private Exception transactionFailure;
    private boolean succes = false;
    private accountInterface account;
    private boolean admin = false;
    private String res;
    private String result;
    private boolean logout = false;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {// först så kollar man om konversationen var kortvarigt eller lång varit 
            //gör den nyvarnde conversationen långvarig
            conversation.begin();
        }
    }

    private void stopConversation() {//  om inte den är inte den är transisten så  gör man den transient kortvarig
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    public boolean getSucc() {
        return succes;
    }

    public boolean getAdmin() {
        return admin;
    }
      public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }

    /**
     * This return value is needed because of a JSF 2.2 bug. Note 3 on page 7-10
     * of the JSF 2.2 specification states that action handling methods may be
     * void. In JSF 2.2, however, a void action handling method plus an
     * if-element that evaluates to true in the faces-config navigation case
     * causes an exception.
     *
     * @return an empty string.
     */
    private String jsf22Bugfix() {
        return "";
    }
    
    public void init(){
        controller.init();
    }

    public String login() {
        try {
            startConversation();
            transactionFailure = null;
            admin = false;
            succes = false;
            logout = false;
            account = null;
            result = controller.Login(username, password);
            if (result.equals("Done")) {
                account = controller.getAccount(username);
            }
            if (account != null && account.getUsername().equals("santa")) {
                admin = true;
            } else if (account != null) {
                succes = true;
            }

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    public String newAccount() {
        try {
            startConversation();
            transactionFailure = null;
            succes = false;
            admin = false;
            logout = false;
            account = null;
            result = controller.newAccount(username, password,name, surname, ssn,email);
            
            if (result.equals("Done")) {
                account = controller.getAccount(username);
            }
            if (account != null && account.getUsername().equals("admin")) {//man kollar ifall man är admin är 
                admin = true;
            } else if (account != null) {
                succes = true;
            }

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    public String logout() {
        try {
            startConversation();
            transactionFailure = null;

            logout = true;

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return null;
    }
    public Float getBalance() {
        return null;
    }

    public accountInterface getAccount() {
        return account;
    }
    public String getRes() {
        return res;
    }

    public boolean getLogout() {
        return logout;
    }
    
    public String getResult(){
        return result;
    }
}
