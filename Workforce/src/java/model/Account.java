/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Account implements Serializable, accountInterface {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  
    private String username;
     @OneToOne()
    @JoinColumn(name = "ROLE_ID")
    private Roles role_id;
     
    private String password;
    
  
    private Long id;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    
    public Account(){
        
    }
    
    public Account(String username, String password, Long id, String name, String surname, String ssn, String email){
        this.username = username;
        this.password = password;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;   
    }


    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    

   
  
    
}
