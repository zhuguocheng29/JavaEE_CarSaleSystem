/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository;

import fit5192.assignment.repository.entities.Car;
import fit5192.assignment.repository.entities.Customer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Think
 */
@Stateful
public class CustomerRepositoryBean implements CustomerRepository {

    private static final String PERSISTENCE_UNIT = "FIT5192-ejbPU";
    private Pattern pattern1;
    private Matcher matcher1;
    
    private Pattern pattern2;
    private Matcher matcher2;
    
    private Pattern pattern3;
    private Matcher matcher3;
    
    private Pattern pattern4;
    private Matcher matcher4; 
    
   //   private final List<Car> cars;
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Customer searchByID(String id) {
        System.out.println("shujuku====" + id);
        long convert_id = Long.parseLong(id);
        System.out.println("id========" + convert_id);
        Query query = this.entityManager.createQuery("select c from Customer c where c.uid = :uid");
        query.setParameter("uid", convert_id);
        List<Customer> customer = query.getResultList();
        if(customer.size()!=0)
        {
           return customer.get(0); 
        }
        else
        {
            return null;
        }
    }
    
    
    
    
    

    
    @Override
    public List<Customer> s_searchByID(String id) {
        //System.out.println("shujuku====" + id);
        System.out.println("customer id" + id);
        long convert_id = Long.parseLong(id);
        System.out.println("id========" + convert_id);
        
        Query query = this.entityManager.createQuery("select c from Customer c where c.uid = :uid");
        query.setParameter("uid", convert_id);
        List<Customer> customer = query.getResultList();
        if(customer.size() != 0)
        {
            return customer;
        }
        else
        {
            return null;
        }
        
    }
    
    
    @Override
    public Customer searchByEmail(String email) {
        //System.out.println("email====" + email);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Customer.class);
        Root<Customer> p = query.from(Customer.class);
        query.select(p).where(builder.equal(p.get("email").as(String.class), email));
        List<Customer> customer = entityManager.createQuery(query).getResultList();
        //System.out.println("customer====" + customer);
        if(customer.size()!=0)
        {
           return customer.get(0); 
        }
        else
        {
            return null;
        }
    }
    
    
    @Override
    public List<Customer> s_searchByEmail(String email) {
        System.out.println("email====" + email);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Customer.class);
        Root<Customer> p = query.from(Customer.class);
        query.select(p).where(builder.equal(p.get("email").as(String.class), email));
        List<Customer> customer = entityManager.createQuery(query).getResultList();
        return customer;
    }
    
    
    @Override
    public void addCustomer(Customer customer) { 
        System.out.println("1111" + customer.getEmail());
        //System.out.println("11113333" + customer.getType());
        //entityManager.persist(customer);
        pattern1 = Pattern.compile("^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        matcher1 = pattern1.matcher(customer.getEmail());
        
        System.out.println("shujukuEJB======" + customer.getEmail());
        
        pattern2 = Pattern.compile("6\\d{7}$|1[3,5,8]\\d{9}");
        matcher2 = pattern2.matcher(customer.getPhonenumber());
        
        pattern3 = Pattern.compile("[^\\d]+$");
        matcher3 = pattern3.matcher(customer.getFirstname());
        
        pattern4 = Pattern.compile("[^\\d]+$");
        matcher4 = pattern4.matcher(customer.getLastname());
        
        if (matcher1.find() && matcher2.find() && matcher3.find() && matcher4.find()) {
            entityManager.persist(customer);
        }
    }

    @Override
    public List<Customer> searchByLastname(String lastname) {
        System.out.println("lastname=====" + lastname);
        Query query = this.entityManager.createQuery("select c from Customer c where c.lastname = :lastname");
        query.setParameter("lastname", lastname);
        List<Customer> customers = query.getResultList();
        System.out.println("customer lastname===" + customers);
        return customers;      
    }
    

    @Override
    public List<Customer> searchByFirstname(String firstname) {
        System.out.println("firstname=====" + firstname);
        Query query = this.entityManager.createQuery("select c from Customer c where c.firstname = :firstname");
        query.setParameter("firstname", firstname);
        List<Customer> customers = query.getResultList();
        System.out.println("customer firstname===" + customers);
        return customers; 
    }

    @Override
    public List<Customer> searchByAllPara(String id, String email, String lastname, String firstname) {
        
        System.out.println("firstname====" + id);
        System.out.println("firstname=====" + firstname);
        long convert_id = Long.parseLong(id);
        
        Query query1 = this.entityManager.createQuery("select c from Customer c where c.uid = :uid");
        query1.setParameter("uid", convert_id);
        List<Customer> customer_compare = query1.getResultList();
        Customer customer_new = customer_compare.get(0);
        
        if(customer_new.getEmail().equals(email))
        {
            Query query = this.entityManager.createQuery("select c from Customer c where c.firstname = :firstname and c.lastname = :lastname and c.uid = :uid and c.email = :email");
            query.setParameter("firstname", firstname);
            query.setParameter("lastname", lastname);
            query.setParameter("uid", convert_id);
            query.setParameter("email", email);
            List<Customer> customers = query.getResultList();
            System.out.println("customer firstname===" + customers);
            return customers;
        }
        else
        {
            return null;
        }

        
        
        
 
    }

    @Override
    public List<Customer> searchByName(String lastname, String firstname) {
        System.out.println("firstname=====" + firstname);
        Query query = this.entityManager.createQuery("select c from Customer c where c.firstname = :firstname and c.lastname = :lastname");
        query.setParameter("firstname", firstname);
        query.setParameter("lastname", lastname);
        List<Customer> customers = query.getResultList();
        System.out.println("customer firstname===" + customers);
        return customers;
    }

    @Override
    public void updateState() {
        
    }

    @Override
    public List<Customer> getAllCustomer() {
        System.out.println("1111111");
        Query query = this.entityManager.createQuery("select c from Customer c");
        List<Customer> customers = query.getResultList();
        System.out.println("customers" + customers);
        return customers;
    }
    
}
