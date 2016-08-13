/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository;

import fit5192.assignment.repository.entities.Car;
import fit5192.assignment.repository.entities.Customer;
import fit5192.assignment.repository.entities.Sale;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Think
 */
@Remote
public interface SaleRepository {
    
    public void addSale(Sale sale);
    
    public void UpdateSaleState(Sale sale);
    
    public List<Sale> searchSale_buy(Customer customer);
    
    public List<Sale> search_order(Customer customer);
    
    public List<Sale> search_order_processing(Customer customer);
    
    public List<Sale> searchSale_valid(Customer customer);
    
    public Long searchById(String vin);
    
    public void remove(Sale sale);
}
