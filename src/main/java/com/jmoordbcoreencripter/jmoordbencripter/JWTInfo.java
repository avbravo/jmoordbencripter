/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordbcoreencripter.jmoordbencripter;

/**
 *
 * @author avbravo
 */
public class JWTInfo {
    private String subject; // usuario
    private String password;
    private String group; //rol
    private Boolean isValid;

    public JWTInfo() {
    }

    public JWTInfo(String subject, String password, String group, Boolean isValid) {
        this.subject = subject;
        this.password = password;
        this.group = group;
        this.isValid = isValid;
    }
    
    

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    
    
    
    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    
    
   
    
    
}
