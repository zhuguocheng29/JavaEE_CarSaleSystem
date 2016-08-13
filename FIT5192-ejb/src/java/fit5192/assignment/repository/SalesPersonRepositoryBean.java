/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository;

import fit5192.assignment.repository.entities.SalesPerson;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateful;
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
public class SalesPersonRepositoryBean implements SalesPersonRepository{

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
    public SalesPerson searchByID(String id) {
                //System.out.println("shujuku====" + id);
        long convert_id = Long.parseLong(id);
        
        Query query = this.entityManager.createQuery("select s from SalesPerson s where s.uid = :uid");
        query.setParameter("uid", convert_id);
        List<SalesPerson> salesPerson = query.getResultList();
        if(salesPerson.size()!=0)
        {
            //System.out.println("=========renrenn" + salesPerson.get(0));
           return salesPerson.get(0); 
        }
        else
        {
            return null;
        }
        
        
    }

    @Override
    public SalesPerson searchByEmail(String email) {
        
        //System.out.println("email====" + email);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(SalesPerson.class);
        Root<SalesPerson> p = query.from(SalesPerson.class);
        query.select(p).where(builder.equal(p.get("email").as(String.class), email));
        List<SalesPerson> salesPerson = entityManager.createQuery(query).getResultList();
        //System.out.println("customer====" + customer);
        if(salesPerson.size()!=0)
        {
           return salesPerson.get(0); 
        }
        else
        {
            return null;
        }
        
    }

    @Override
    public void addSalesPerson(SalesPerson salesPerson){
        
        System.out.println("tianjia=====" + salesPerson.getEmail());
        //System.out.println("11113333" + customer.getType());
        //entityManager.persist(customer);
        pattern1 = Pattern.compile("^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        matcher1 = pattern1.matcher(salesPerson.getEmail());
        
        
        pattern2 = Pattern.compile("6\\d{7}$|1[3,5,8]\\d{9}");
        matcher2 = pattern2.matcher(salesPerson.getPhonenumber());
        
        pattern3 = Pattern.compile("[^\\d]+$");
        matcher3 = pattern3.matcher(salesPerson.getFirstname());
        
        pattern4 = Pattern.compile("[^\\d]+$");
        matcher4 = pattern4.matcher(salesPerson.getLastname());
        
        if (matcher1.find() && matcher2.find() && matcher3.find() && matcher4.find()) {
            entityManager.persist(salesPerson);
        }
    }

    @Override
    public List<String> searchByType() {
        System.out.println("search type ======");
        Query query = this.entityManager.createQuery("select s from SalesPerson s");
        List<String> lastname = new ArrayList<String>(); 
        
        List<SalesPerson> salesPerson = query.getResultList();
        
        if(salesPerson.size()!=0)
        {
            for(int i=0; i<salesPerson.size();i++)
            {
                //lastname.add(salesPerson.get(i).getLastname());
                lastname.add(salesPerson.get(i).getUid().toString());
                System.out.println("lastname====" + lastname.get(i));
            }
            return lastname;
        }
        else
        {
            return null;
        }
        
    }
    
    
    
    
}
