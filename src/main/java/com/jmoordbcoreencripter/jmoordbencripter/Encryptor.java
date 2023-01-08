/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordbcoreencripter.jmoordbencripter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author avbravo
 */
public class Encryptor {

    /**
     *
     * @param EXPIRY_DAYS = 90;
     * @param status =0
     * @param grupo ="administrador" rol del usuario
     * @param password ="mipassword" esto es adicional colocar el password
     * @param user = "usuario"
     * @return
     */
    public static String encriptarJWT(int EXPIRY_DAYS, Integer status, String group, String password, String user) {
        String token = "";

        try {

            JSONObject jwtPayload = new JSONObject();
            jwtPayload.put("status", status);

            JSONArray audArray = new JSONArray();
            audArray.put(group);
            audArray.put(password);

            jwtPayload.put("sub", user);

            jwtPayload.put("aud", audArray);
            LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
            jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured

            token = new JWebToken(jwtPayload).toString();

        } catch (Exception e) {
            System.out.println("encriptarJWT() " + e.getLocalizedMessage());

        }
        return token;
    }

    /**
     * Desencripta el token devuelto en un objeto JWTInfo
     * @param token
     * @return 
     */
    public static JWTInfo desencriptarJWT(String token) {
        JWTInfo jWTInfo = new JWTInfo();
        try {
            JWebToken incomingToken = new JWebToken(token);

            List<String> audience = incomingToken.getAudience();
            String subject = incomingToken.getSubject();
          
            jWTInfo.setSubject(subject);
            
            jWTInfo.setGroup(audience.get(0));
            jWTInfo.setPassword(audience.get(1));

            if (incomingToken.isValid()) {
                jWTInfo.setIsValid(Boolean.TRUE);
            } else {
                //Token Expiro
                jWTInfo.setIsValid(Boolean.FALSE);

            }
        } catch (Exception e) {
            System.out.println("desencriptarJWT() " + e.getLocalizedMessage());
        }
        return jWTInfo;
    }
}
