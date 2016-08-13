/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository;

import fit5192.assignment.repository.entities.Customer;
import fit5192.assignment.repository.entities.SalesPerson;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Think
 */
@Remote
public interface SalesPersonRepository {
    
    public SalesPerson searchByID(String id);
    
    public SalesPerson searchByEmail(String email);
    
    public List<String> searchByType();
    
    public void addSalesPerson(SalesPerson salesPerson);
    
    
    
}
