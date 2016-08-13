/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Think
 */
@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type" , discriminatorType = DiscriminatorType.STRING)

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long uid;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1,max = 64)
    @Column(name = "last_name")
    protected String lastname;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "first_name")
    protected String firstname;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "email", unique = true)
    protected String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    protected String password;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "address")
    protected String address;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "phone_number")
    protected String phonenumber;
    
    
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "Type")
//    protected String type;

//    @Column(name = "Type" )
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
     
//     
//    @OneToMany(mappedBy = "uid")
//    private Collection<Sale> saleCollection;

    public Users(Long uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public Users() {
    }

    public Users(Long uid) {
        this.uid = uid;
    }
    
    
    public Users(Long uid, String lastname, String firstname, String email, String password, String address, String phonenumber) {
        this.uid = uid;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phonenumber = phonenumber;
    }

//    public Users(Long uid, String lastname, String firstname, String email, String password, String address, String phonenumber, String type) {
//        this.uid = uid;
//        this.lastname = lastname;
//        this.firstname = firstname;
//        this.email = email;
//        this.password = password;
//        this.address = address;
//        this.phonenumber = phonenumber;
//        this.type = type;
//    }

    
    
    
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }



    @Override
    public String toString() {
        return "fit5192.assignment.repository.entities.Customer[ id=" + uid + " ]";
    }
    
}
