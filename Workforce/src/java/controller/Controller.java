package controller;

import model.Account;
import model.accountInterface;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 * A controller. All calls to the model that are executed because of an action
 * 
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Controller {

    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    private boolean done = true;

    public String Login(String username, String password) {
        accountInterface account = em.find(Account.class, username);
        if (account == null) {
            //throw new EntityNotFoundException("No such account");
            return "No such account";
        }


        if (!account.getPassword().equals(password)) {
            //throw new EntityNotFoundException("Wrong username or password");
            return "Wrong username or password";
        }
        return "Done";
    }

    public String newAccount(String username, String password,String name,String surname, String ssn,String email) {
        accountInterface account = em.find(Account.class, username);
        if (account != null) {
            //throw new EntityNotFoundException("Account already exist");
            return "Account already exist";
        }

       
        long b =2;
        account = new Account(username, password,b, name, surname,ssn,email);
        em.persist(account);

        return "Done";
    }

    public accountInterface getAccount(String name) {
        return em.find(Account.class, name);
    }


    public void init() {
        if (done) {
            long a =1;
            em.persist(new Account("admin", "admin",a,"admin","adminson","9205143218","email"));
            done = false;
        }
    }
}
