/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;

import fit5192.assignment.repository.CarRepository;
import fit5192.assignment.repository.CustomerRepository;
import fit5192.assignment.repository.SalesPersonRepository;
import fit5192.assignment.repository.UserRepository;
import fit5192.assignment.repository.entities.Car;
import fit5192.assignment.repository.entities.Customer;
import fit5192.assignment.repository.entities.SalesPerson;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Think
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {
    @EJB
    private UserRepository userRepository;
    
    @EJB
    private CustomerRepository customerRepository;
    
    @EJB
    private SalesPersonRepository salesPersonRepository;
    
    @EJB
    private CarRepository carRepository;
    
    private Customer customer;
    
    private SalesPerson salesPerson;
    
    private String username;
    
    private String password;

    public LoginManagedBean() {
        customer = null;
        salesPerson = null;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public SalesPersonRepository getSalesPersonRepository() {
        return salesPersonRepository;
    }

    public void setSalesPersonRepository(SalesPersonRepository salesPersonRepository) {
        this.salesPersonRepository = salesPersonRepository;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SalesPerson getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(SalesPerson salesPerson) {
        this.salesPerson = salesPerson;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
     public String logout(){
         try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            return "/faces/index";

        } catch(Exception e) {
            System.out.print(e.toString());
        }
        return null;
     }

    
    
    
    
    public String login() throws ServletException
    {
        String login_username = this.username;
        String login_password = this.password;
        String type = this.userRepository.searchByNameAPassword(login_username, login_password);
        
        if(type.equals("1"))
        {
             //return "SalesPerson?faces-redirect=true";
            //judge who are customers and salesperson
             System.out.println("judge======================");
             FacesContext ctx = FacesContext.getCurrentInstance();
             HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
             request.login(login_username, login_password);
             if(request.isUserInRole("SalesPerson")){

                salesPerson = this.salesPersonRepository.searchByEmail(this.username);
                return "/SalesPerson/SalesPerson";
               //return "/Customer/Customer";
             }
             else if(request.isUserInRole("Customer")){
                customer = this.customerRepository.searchByEmail(this.username);
                return "/Customer/Customer";
             }
             else {
                return "";
             }

//            } catch(Exception e) {
//                ctx.addMessage("loginForm:message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Wrong", "Either Username or Password is wrong"));
//            }
      
        }
        else
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("loginForm:login_username", new FacesMessage(FacesMessage.SEVERITY_WARN,"Input wrong", "Please input right username and corresponding password !"));
            return "Login";
            //return "Login?faces-redirect=true";
        }         
        
    }
    

}
