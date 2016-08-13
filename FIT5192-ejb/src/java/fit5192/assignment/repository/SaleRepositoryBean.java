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
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Think
 */
@Stateful
public class SaleRepositoryBean  implements SaleRepository {

    private static final String PERSISTENCE_UNIT = "FIT5192-ejbPU";
    
   //   private final List<Car> cars;
    @PersistenceContext
    private EntityManager entityManager;

    public SaleRepositoryBean() {
    }
    
        
    
    @Override
    public void addSale(Sale sale) {
        System.out.println("database add car =====" +sale.getSstate());
        entityManager.persist(sale);
    }
    
    @Override
    public Long searchById(String vin)
    {
        System.out.println("database add====" + vin);
        Query query = this.entityManager.createQuery("select s from Sale s where s.vin.vin = :vin");
        query.setParameter("vin", vin);
        List<Sale> sales = query.getResultList();
        System.out.println("salessssssss" + sales);
        Long id = sales.get(0).getId();
        System.out.println("ididididid==========" + id);
        return id;
    }
    
    

    @Override
    public void UpdateSaleState(Sale sale) {
        System.out.println("database add car =====" +sale.getSstate());
        System.out.println("datdabase add vin====" + sale.getVin());
        System.out.println("database add vin ===" + sale.getSid());
        System.out.println("database add id =====" + sale.getId());
        System.out.println("database add date ====" + sale.getSale_finish_date());
        //entityManager.merge(sale);
        Query query = this.entityManager.createQuery("update Sale as s set s.sstate = :sstate where s.id =:id");
        query.setParameter("sstate", sale.getSstate());
        query.setParameter("id", sale.getId());
        query.executeUpdate();
        
        Query query1 = this.entityManager.createQuery("update Sale as s set s.sale_finish_date = :f_date where s.id =:id");
        query1.setParameter("f_date", sale.getSale_finish_date());
        query1.setParameter("id", sale.getId());
        query1.executeUpdate();
    }

    @Override
    public List<Sale> searchSale_buy(Customer customer) {
        System.out.println("customer id" + customer.getUid());
        Query query = this.entityManager.createQuery("select s from Sale s where s.cid = :cid and s.sstate='Completed'");
        query.setParameter("cid", customer);
        List<Sale> sale = query.getResultList();
        return sale;
    }
    
    
    @Override
    public List<Sale> searchSale_valid(Customer customer) {
        System.out.println("customer id" + customer.getUid());
        Query query = this.entityManager.createQuery("select s from Sale s where s.cid = :cid");
        query.setParameter("cid", customer);
        List<Sale> sale = query.getResultList();
        return sale;
    }
    
    
    @Override
    public List<Sale> search_order(Customer customer) {
        System.out.println("customer id" + customer.getUid());
        Query query = this.entityManager.createQuery("select s from Sale s where s.cid = :cid and s.sstate='Completed'");
        query.setParameter("cid", customer);
        List<Sale> sale = query.getResultList();
        
        Query query1 = this.entityManager.createQuery("select s from Sale s where s.cid = :cid and s.sstate='Processing'");
        query1.setParameter("cid", customer);
        List<Sale> sale1 = query1.getResultList();
        
        for(int i= 0; i< sale1.size();i++)
        {
            sale.add(sale1.get(i));
        }
        System.out.println("output sale--------" + sale);
        return sale;
    }
    
    @Override
    public List<Sale> search_order_processing(Customer customer){
        System.out.println("customer id" + customer.getUid());
        Query query1 = this.entityManager.createQuery("select s from Sale s where s.cid = :cid and s.sstate='Processing'");
        query1.setParameter("cid", customer);
        List<Sale> sale1 = query1.getResultList();
        System.out.println("sale+++++" + sale1);
        return sale1;
    
    }
    
    

    @Override
    public void remove(Sale sale) {
        System.out.println("output sale ====" + sale.getSstate());
       
        Query query = this.entityManager.createQuery("select s from Sale s where s.sstate =:state and s.vin =:vin and s.cid = :cid and s.sid = :sid");
        query.setParameter("state", sale.getSstate());
        query.setParameter("vin", sale.getVin());
        query.setParameter("cid", sale.getCid());
        query.setParameter("sid", sale.getSid());
        
        List<Sale> sales = query.getResultList();
        System.out.println("sales=========" + sales);
        this.entityManager.remove(sales.get(0));
    }
    
}
