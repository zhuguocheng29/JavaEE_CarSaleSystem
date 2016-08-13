/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Think
 */
@Entity
@DiscriminatorValue("Customer")
public class Customer extends Users{

//    public Customer(Long uid, String email) {
//        super(uid, email);
//    }

    public Customer() {
        
    }

//    public Customer(Long uid) {
//        super(uid);
//    }
//
//    public Customer(Long uid, String lastname, String firstname, String email, String password, String address, String phonenumber) {
//        super(uid, lastname, firstname, email, password, address, phonenumber);
//    }
    
    
    
}
