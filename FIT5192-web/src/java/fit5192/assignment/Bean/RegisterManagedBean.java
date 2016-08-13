/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;

import fit5192.assignment.repository.CustomerRepository;
import fit5192.assignment.repository.GroupRepository;
import fit5192.assignment.repository.SalesPersonRepository;
import fit5192.assignment.repository.entities.Customer;
import fit5192.assignment.repository.entities.SalesPerson;
import fit5192.assignment.repository.entities.Users;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Think
 */
@Named(value = "registerManagedBean")
@RequestScoped
public class RegisterManagedBean implements Serializable{

    /**
     * Creates a new instance of RegisterManagedBean
     */
    @EJB
    private CustomerRepository customerRepository;
    
    @EJB
    private SalesPersonRepository salesPersonRepository;
    
    @EJB
    private GroupRepository groupRepository;
    
    private String type;
    
    private SalesPerson salesPerson;
    
    private Customer customer;
    
    private Users users;
    
    private String checkpassword;

    
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SalesPerson getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(SalesPerson salesPerson) {
        this.salesPerson = salesPerson;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getCheckpassword() {
        return checkpassword;
    }

    public void setCheckpassword(String checkpassword) {
        this.checkpassword = checkpassword;
    } 
    
    
    public RegisterManagedBean() {
    
        this.salesPerson = new SalesPerson();
    
        this.customer = new Customer();
        
        this.users = new Users();
        
        System.out.println("000000000000");

    }
    
    public String classifyPerson()
    {
        if(this.type.equals("SalesPerson"))
        {
            return "SalesPerson";
        }
        else if(this.type.equals("Customer"))
        {
            return "Customer";
        }
        else
        {
            return null;
        }       
    }
    
    
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
    
    public String addOrcheck()
    {
        System.out.println("uid====" + users.getUid());
        System.out.println("lastname====" + users.getLastname());
        System.out.println("password===" + users.getPassword());
        System.out.println("type===" + type);
        
        if(type.equals("Customer"))
        {     
            FacesContext ctx = FacesContext.getCurrentInstance();
            
            String cid = users.getUid()+"";
            String cemail = users.getEmail()+"";
            try {
                if((this.customerRepository.searchByID(cid) != null) || (this.customerRepository.searchByEmail(cemail) != null) )
                {
                    //说明数据库有值
                    users.setLastname("");
                    users.setFirstname("");
                    users.setEmail("");
                    
                    ctx.addMessage("userForm:ID", new FacesMessage(FacesMessage.SEVERITY_WARN,"Repeated ID", "Please write another ID: "));
                    ctx.addMessage("userForm:email", new FacesMessage(FacesMessage.SEVERITY_WARN,"Repeated email", "Please write another email: "));
                    
                    return "Register";
                }
                else
                {
                    System.out.println("customer=============");
                    String password_sha256 = "";
                    MessageDigest md = null; 
                    try { 
                        md = MessageDigest.getInstance("SHA-256"); 
                    } catch (NoSuchAlgorithmException e) { 
                        e.printStackTrace(); 
                    } 
                    if (null != md) { 
                        byte[] origBytes = users.getPassword().getBytes(); 
                        md.update(origBytes); 
                        byte[] digestRes = md.digest(); 
                        password_sha256 = getDigestStr(digestRes); 
                        
                    }                                                     
                    
                    Customer newCustomer = new Customer();
                    newCustomer.setUid(users.getUid());
                    newCustomer.setLastname(users.getLastname());
                    newCustomer.setFirstname(users.getFirstname());
                    newCustomer.setPassword(password_sha256);
                    newCustomer.setAddress(users.getAddress());
                    newCustomer.setEmail(users.getEmail());
                    newCustomer.setPhonenumber(users.getPhonenumber());
                    
                    this.customerRepository.addCustomer(newCustomer);
                    this.groupRepository.addGroup("Customer", users.getEmail());
                    return "Login?faces-redirect=true";
                }
            } catch (Exception ex) {
                Logger.getLogger(RegisterManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(type.equals("SalesPerson"))
        {
                FacesContext ctx = FacesContext.getCurrentInstance();
                String sid = users.getUid()+"";
                String semail = users.getEmail()+"";
                try {
                    if((this.salesPersonRepository.searchByID(sid) != null) || (this.salesPersonRepository.searchByEmail(semail) != null) )
                        //if not null database has record
                    {
                        //说明数据库有值
                                            
                    users.setLastname("");
                    users.setFirstname("");
                    users.setEmail("");
                    
                    ctx.addMessage("userForm:ID", new FacesMessage(FacesMessage.SEVERITY_WARN,"Repeated ID", "Please write another ID: "));
                    ctx.addMessage("userForm:email", new FacesMessage(FacesMessage.SEVERITY_WARN,"Repeated email", "Please write another email: "));
                    
                    return "Register";
                    }
                    else
                    {
                        System.out.println("salesperson=============");
                        String password_sha256 = "";
                        MessageDigest md = null; 
                        try { 
                            md = MessageDigest.getInstance("SHA-256"); 
                        } catch (NoSuchAlgorithmException e) { 
                            e.printStackTrace(); 
                        } 
                        if (null != md) { 
                            byte[] origBytes = users.getPassword().getBytes(); 
                            md.update(origBytes); 
                            byte[] digestRes = md.digest(); 
                            password_sha256 = getDigestStr(digestRes); 

                        }                                                     

                        SalesPerson newsalesPerson = new SalesPerson();
                        newsalesPerson.setUid(users.getUid());
                        newsalesPerson.setLastname(users.getLastname());
                        newsalesPerson.setFirstname(users.getFirstname());
                        newsalesPerson.setPassword(password_sha256);
                        newsalesPerson.setAddress(users.getAddress());
                        newsalesPerson.setEmail(users.getEmail());
                        newsalesPerson.setPhonenumber(users.getPhonenumber());

                        this.salesPersonRepository.addSalesPerson(newsalesPerson);
                        this.groupRepository.addGroup("SalesPerson", users.getEmail());
                        return "Login?faces-redirect=true";
                    }
            } catch (Exception ex) {
                Logger.getLogger(RegisterManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        return "Register";
       
        
    }
    
    
    public String tips()
    {
         String cid = users.getUid()+"";
         String cemail = users.getEmail()+"";
         if((this.customerRepository.searchByID(cid) != null) || (this.customerRepository.searchByEmail(cemail) != null) )
         {
             return "User already exists! ";
         }
         return null;
    }
    
}
