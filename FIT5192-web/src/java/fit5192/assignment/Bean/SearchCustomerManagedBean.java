/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;

import fit5192.assignment.repository.CarRepository;
import fit5192.assignment.repository.CustomerRepository;
import fit5192.assignment.repository.entities.Customer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Think
 */
@Named(value = "searchCustomerManagedBean")
@SessionScoped
public class SearchCustomerManagedBean implements Serializable {
    
    @EJB
    private CustomerRepository customerRepository;   
    
    private String cid;
    
    private String cemail;
    
    private String lastname;
    
    private String firstname;
    
    private boolean show;

    public SearchCustomerManagedBean() {
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    
    
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
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
    
    public List<Long> get_id()
    {
        List<Customer> customers = this.customerRepository.getAllCustomer();
        List<Long> get_id = new ArrayList<>();
        for(int i = 0; i < customers.size();i++)
        {
            get_id.add(customers.get(i).getUid());
        }
        return get_id;
    }
    
    
    
    public String forwarding()
    {
        Customer customer_search = this.customerRepository.searchByID(this.cid);
        System.out.println("customer_search 111111" + customer_search );
        System.out.println("customer_search Id " + customer_search.getUid());
        
        if(customer_search == null)
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("salesPersonSearchCustomerForm:searchCustomer_ID", new FacesMessage(FacesMessage.SEVERITY_WARN,"ID is not the customer", "Please input correct customer ID!"));
            //ctx.addMessage("SearchCustomerForm:searchCustomer1_ID", new FacesMessage(FacesMessage.SEVERITY_WARN,"ID is not the customer", "Please input correct customer ID!"));
            return "/SalesPerson/SalesPersonSearchCustomer";
        }
        else
        {
            this.show = true;
            return "/SalesPerson/SalesPersonSearchCustomer";
        }

    }
    
    
    public List<Customer> searchPara()
    {
        
        String id = this.cid;
        String lastname = this.lastname;
        String firstname = this.firstname;
        String email = this.cemail;
        
        System.out.println("cid=====" + id);
        //Long id = Long.parseLong(id_new);
        
        System.out.println("lastname=====" + lastname);
        System.out.println("firstname=====" + firstname);
        System.out.println("email======" + email); 
        System.out.println("show======" + this.show);
//        if((id != null) &&(lastname.equals("")) && (firstname.equals("")) && (email.equals("")))
//        {
//            List<Customer> customers = this.customerRepository.s_searchByID(id);
//            return customers;
//        }
//        return null;
        
        List<Customer> customer_search = this.customerRepository.s_searchByID(id);
        System.out.println("customer_search Id" + customer_search.get(0).getUid());
        
        if(customer_search.size() == 0)
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("salesPersonSearchCustomerForm:searchCustomer_ID", new FacesMessage(FacesMessage.SEVERITY_WARN,"ID is not the customer", "Please input correct customer ID!"));
            ctx.addMessage("SearchCustomerForm:searchCustomer1_ID", new FacesMessage(FacesMessage.SEVERITY_WARN,"ID is not the customer", "Please input correct customer ID!"));

        }
        else
        {
            if((id != null) &&(lastname.equals("")) && (firstname.equals("")) && (email.equals("")))
            {
                List<Customer> customers = this.customerRepository.s_searchByID(id);
                return customers;
            }         
            else if((id.equals("Please choose customer:")) &&(lastname != null) && (firstname.equals("")) && (email.equals("")))
            {
                List<Customer> customers = this.customerRepository.searchByLastname(lastname);
                return customers;
            }
            else if((id.equals("Please choose customer:")) &&(lastname.equals("")) && (firstname != null) && (email.equals("")))
            {
                List<Customer> customers = this.customerRepository.searchByFirstname(firstname);
                return customers;
            }
            else if((id.equals("Please choose customer:")) &&(lastname.equals("")) && (firstname.equals("")) && (email != null))
            {
                List<Customer> customers = this.customerRepository.s_searchByEmail(email);
                return customers;
            }
            else if((id.equals("Please choose customer:")) &&(lastname != null) && (firstname != null) && (email.equals("")))
            {
                List<Customer> customers = this.customerRepository.searchByName(lastname, firstname);
                return customers;
            }
            else if((id != null) &&(lastname != null) && (firstname != null) && (email != null) )
            {
                List<Customer> customers = this.customerRepository.searchByAllPara(id, email, lastname, firstname);
                if(customers == null)
                {
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    ctx.addMessage("SearchCustomerForm:searchCustomer1_ID", new FacesMessage(FacesMessage.SEVERITY_WARN,"ID is not consistent with email", "Please correct ID and corresponding email !"));
                }
               else
                {
                    return customers;
                }

            }
   
        }
              
        return null;
        
    }
    
}
