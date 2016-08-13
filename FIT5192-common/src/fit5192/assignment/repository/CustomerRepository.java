/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository;

import fit5192.assignment.repository.entities.Customer;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Think
 */
@Remote
public interface CustomerRepository {
    
    public Customer searchByID(String id);
    
    public Customer searchByEmail(String email);
    
    public List<Customer> searchByLastname(String lastname);
    
    public List<Customer> searchByFirstname(String firstname);
    
    public List<Customer> searchByAllPara(String id, String email, String lastname, String firstname);
    
    public List<Customer> searchByName(String lastname, String firstname);
    
    public List<Customer> s_searchByID(String id);
    
    public List<Customer> s_searchByEmail(String email);
    
    public List<Customer> getAllCustomer();
    
    public void addCustomer(Customer customer);
    
    public void updateState();
}
