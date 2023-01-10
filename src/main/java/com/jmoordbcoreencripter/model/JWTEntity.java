/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordbcoreencripter.model;

/**
 *
 * @author avbravo
 */
public class JWTEntity {

    private String subject;
    private Integer status =0;
    private Boolean isValid;
    private String group;
    private String password;
    private int expirationDays=90;
    private String secretKey;

    public JWTEntity() {
    }

    public JWTEntity(String subject, Integer status, Boolean isValid, String group, String password, int expirationDays, String secretKey) {
        this.subject = subject;
        this.status = status;
     
        this.isValid = isValid;
        this.group = group;
        this.password = password;
        this.expirationDays = expirationDays;
        this.secretKey = secretKey;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public int getExpirationDays() {
        return expirationDays;
    }

    public void setExpirationDays(int expirationDays) {
        this.expirationDays = expirationDays;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JWTEntity{");
        sb.append("subject=").append(subject);
        sb.append(", status=").append(status);
        sb.append(", isValid=").append(isValid);
        sb.append(", group=").append(group);
        sb.append(", password=").append(password);
        sb.append(", expirationDays=").append(expirationDays);
        sb.append(", secretKey=").append(secretKey);
        sb.append('}');
        return sb.toString();
    }

    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="static class Builder">
    public static class Builder {
        

        private String subject;
        private Integer status =0;
        private Boolean isValid;
        private String group;
        private String password;
        private int expirationDays =90;
        private String secretKey;

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }
       
        public Builder isValid(Boolean isValid) {
            this.isValid= isValid;
            return this;
        }
        
        public Builder expirationDays(int expirationDays) {
            this.expirationDays= expirationDays;
            return this;
        }
        
        
        
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }
        public Builder group(String group) {
            this.group = group;
            return this;
        }
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        public Builder secretKey(String secretKey) {
            this.secretKey= secretKey;
            return this;
        }

       
        public JWTEntity build() {
            return new JWTEntity(subject, status, isValid, group, password, expirationDays, secretKey);                    
            
        }

        @Override
        public String toString() {
            return "Builder{" + "subject=" + subject + ", status=" + status + ", isValid=" + isValid + ", group=" + group + ", password=" + password + ", expirationDays=" + expirationDays + ", secretKey=" + secretKey + '}';
        }

        
        
    }

}
