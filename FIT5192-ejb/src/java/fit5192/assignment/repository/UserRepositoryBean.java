/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository;

import fit5192.assignment.repository.entities.Users;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Think
 */
@Stateful
public class UserRepositoryBean implements UserRepository {

    private static final String PERSISTENCE_UNIT = "FIT5192-ejbPU";
    
    @PersistenceContext
    private EntityManager entityManager;
    
    
    private static String getDigestStr(byte[] origBytes) { 
        String tempStr = null; 
        StringBuilder stb = new StringBuilder(); 
        for (int i = 0; i < origBytes.length; i++) {  
            tempStr = Integer.toHexString(origBytes[i] & 0xff); 
            if (tempStr.length() == 1) { 
                stb.append("0"); 
            } 
            stb.append(tempStr);

        } 
        return stb.toString(); 
    }
    
    
    @Override
    public String searchByNameAPassword(String username, String password) {
        
        String password_sha256 = "";
        MessageDigest md = null; 
        try { 
            md = MessageDigest.getInstance("SHA-256"); 
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
        } 
        if (null != md) { 
            byte[] origBytes = password.getBytes(); 
            md.update(origBytes); 
            byte[] digestRes = md.digest(); 
            password_sha256 = getDigestStr(digestRes); 

        }                      
             
        System.out.println("++++++++++++++++++++");
        Query query = this.entityManager.createQuery("select u from Users u where u.email = :email and u.password = :password");
        query.setParameter("email", username);
        query.setParameter("password", password_sha256 );
        List<Users> users = query.getResultList();
        
//        System.out.println("email====" + users.get(0).getEmail());
//        System.out.println("passsword=====" + users.get(0).getPassword());
        if(users.size()!=0)
        {
            return "1";
        }
        else
        {
            return "0";
        }
    }

    @Override
    public String searchByType(String username, String password) {
//        Query query = this.entityManager.createQuery("select u from User u where u.email = :email and u.password = :password");
//        query.setParameter("email", username);
//        query.setParameter("password", password);
//        List<Users> users = query.getResultList();
//        if(users.size() != 0)
//        {
//            //String type = users.get(0).
//        }
        return null;
    }
    
}
