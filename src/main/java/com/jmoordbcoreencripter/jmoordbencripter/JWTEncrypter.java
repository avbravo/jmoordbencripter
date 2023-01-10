/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordbcoreencripter.jmoordbencripter;


import com.jmoordbcoreencripter.internal.JWebToken;
import com.jmoordbcoreencripter.model.JWTEntity;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author avbravo
 */
public class JWTEncrypter {
// <editor-fold defaultstate="collapsed" desc="String encrypt(JWTEntity jWTEntity) ">

    /**
     * Devuelve un token encriptado en base a un JWTEntity
     * @param EXPIRY_DAYS = 90;
     * @param status =0
     * @param grupo ="administrador" rol del usuario
     * @param password ="mipassword" esto es adicional colocar el password
     * @param user = "usuario"
     * @return
     */
    public static String encrypt(JWTEntity jWTEntity) {
        String token = "";

        try {

            JSONObject jwtPayload = new JSONObject();
            jwtPayload.put("status", jWTEntity.getStatus());

            JSONArray audArray = new JSONArray();
            audArray.put(jWTEntity.getGroup());
            
           
            audArray.put(jWTEntity.getPassword());

            jwtPayload.put("sub", jWTEntity.getSubject());

            jwtPayload.put("aud", audArray);
            LocalDateTime ldt = LocalDateTime.now().plusDays(jWTEntity.getExpirationDays());
            jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured

            token = new JWebToken(jwtPayload,jWTEntity.getSecretKey()).toString();

        } catch (Exception e) {
            System.out.println("encrypt() " + e.getLocalizedMessage());

        }
        return token;
    }
        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="JWTEntity decrypt(String token,String SECRET_KEY) ">
     /**
     * Desencripta el token devuelto en un objeto JWTEntity
     * @param token
     * @return 
     */
    public static JWTEntity decrypt(String token,String SECRET_KEY) {
       JWTEntity jWTEntity = new JWTEntity();
        try {
            JWebToken incomingToken = new JWebToken(token);

            List<String> audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
          
           jWTEntity.setSubject(subject);
            
           jWTEntity.setGroup(audience.get(0));
           jWTEntity.setPassword(audience.get(1));
           
          // jWTEntity.setIdmicroserviceapp(audience.get(2));

            if (incomingToken.isValid(SECRET_KEY)) {
                jWTEntity.setIsValid(Boolean.TRUE);
            } else {
                //Token Expiro
                jWTEntity.setIsValid(Boolean.FALSE);

            }
        } catch (Exception e) {
            System.out.println("decrypt() " + e.getLocalizedMessage());
        }
      return jWTEntity;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="String encryptJWT(JWTEntity jWTEntity)">
 
    /**
     * No usa el atributo password para encriptar
     * @param jWTEntity
     * @return 
     */
    
    public static String encryptJWT(JWTEntity jWTEntity) {
        String token = "";

        try {

            JSONObject jwtPayload = new JSONObject();
            jwtPayload.put("status", jWTEntity.getStatus());

            JSONArray audArray = new JSONArray();
            audArray.put(jWTEntity.getGroup());
           

            jwtPayload.put("sub", jWTEntity.getSubject());

            jwtPayload.put("aud", audArray);
            LocalDateTime ldt = LocalDateTime.now().plusDays(jWTEntity.getExpirationDays());
            jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured

            token = new JWebToken(jwtPayload,jWTEntity.getSecretKey()).toString();

        } catch (Exception e) {
            System.out.println("encrypt() " + e.getLocalizedMessage());

        }
        return token;
    }
       // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="JWTEntity decryptJWT(String token,String SECRET_KEY)">
 
    /**
     * No usa el atributo password para encriptar
     * @param jWTEntity
     * @return 
     */
    public static JWTEntity decryptJWT(String token,String SECRET_KEY) {
       JWTEntity jWTEntity = new JWTEntity();
        try {
            JWebToken incomingToken = new JWebToken(token);

            List<String> audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
          
           jWTEntity.setSubject(subject);
            
           jWTEntity.setGroup(audience.get(0));
           jWTEntity.setPassword(audience.get(1));
           
          // jWTEntity.setIdmicroserviceapp(audience.get(2));

            if (incomingToken.isValid(SECRET_KEY)) {
                jWTEntity.setIsValid(Boolean.TRUE);
            } else {
                //Token Expiro
                jWTEntity.setIsValid(Boolean.FALSE);

            }
        } catch (Exception e) {
            System.out.println("decrypt() " + e.getLocalizedMessage());
        }
      return jWTEntity;
    }
       // </editor-fold>
    
    
   
}
