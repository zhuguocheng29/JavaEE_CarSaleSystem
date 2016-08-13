/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;

import fit5192.assignment.repository.CarRepository;
import fit5192.assignment.repository.CustomerRepository;
import fit5192.assignment.repository.SaleRepository;
import fit5192.assignment.repository.SalesPersonRepository;
import fit5192.assignment.repository.entities.Car;
import fit5192.assignment.repository.entities.Customer;
import fit5192.assignment.repository.entities.Sale;
import fit5192.assignment.repository.entities.SalesPerson;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Think
 */
@Named(value = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean implements Serializable{
    
    @EJB
    private CarRepository carRepository;
    
    @EJB
    private SalesPersonRepository SalesPersonRepository;
    
    @EJB
    private SaleRepository saleRepository;
    
    private String modelName;
    private String modelNo;
    private String make;
    private String type;
    private Car car;
    private String salesPersonType;
    private Sale sale;
    private Long saleid;
    

    public CustomerManagedBean() {
        car = new Car();
        //sale = new Sale();
    }

    public Long getSaleid() {
        return saleid;
    }

    public void setSaleid(Long saleid) {
        this.saleid = saleid;
    }



    
    
    public CarRepository getCarRepository() {
        return carRepository;
    }

    
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public SaleRepository getSaleRepository() {
        return saleRepository;
    }

    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    
    
    
    public SalesPersonRepository getSalesPersonRepository() {
        return SalesPersonRepository;
    }

    public void setSalesPersonRepository(SalesPersonRepository SalesPersonRepository) {
        this.SalesPersonRepository = SalesPersonRepository;
    }

    public String getSalesPersonType() {
        return salesPersonType;
    }

    public void setSalesPersonType(String salesPersonType) {
        this.salesPersonType = salesPersonType;
    }
    
    
    
    public List<Car> searchall()
    {
        System.out.println("search cars");
        List<Car> cars = this.carRepository.searchAllCarVaild();
        return cars;
    }
    public String getCustomerName()
    {
         ELContext context = FacesContext.getCurrentInstance().getELContext();
         LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
         Customer customer = loginManagedBean.getCustomer();
         System.out.println("customer name" + customer.getFirstname());
         return customer.getFirstname();
    }
    
    public List<Car> searchPara() throws Exception
    {
        String make = this.getMake();
        String carName = this.getModelName();
        String carNo = this.getModelNo();
        String type = this.getType();
        
        System.out.println("c_make is :" + make);
        System.out.println("c_CarName is :" + carName);
        System.out.println("c_CarNo is :" + carNo);
        System.out.println("c_type is :" + type);
        System.out.println("c_Boolean :" + type.equals("Please choose types of car:"));
 
        
        if((make != null) && (!carName.equals("")) && (!carNo.equals(""))&& (!type.equals("Please choose types of car:")))// 
        {
            List<Car> cars = this.carRepository.searchFourPara(make, carName, carNo, type);
            System.out.println("222222");
            return cars;
        }
        if(make != null  && (carName.equals("")) && (carNo.equals("")) && (type.equals("Please choose types of car:")))//
        {
            System.out.println("11111");
            List<Car> cars = this.carRepository.searchByMake(make);
           // System.out.println(cars);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByModelName(carName);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByModelNo(carNo);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByType(type);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            //Car car = new Car();
            return null;
        }
        else if((make != null)  && (carName != null) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAName(make,carName);
            return cars;
        }
        else if((make != null)  && (carName.equals("")) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeANo(make,carNo);
           return cars;
        }
        else if((make != null)  && (carName.equals("")) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAType(make,type);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameANo(carName,carNo);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameAType(carName,type);
            return cars;
        }
         else if((make.equals(""))  && (carName.equals("")) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNoAType(carNo,type);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameAModelNoAType(carName,carNo,type);
            return cars;
        }
        else if((make != null)  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAModelNameAModelNo(make,carName,carNo);
            return cars;
        }
        else
        {
            return null;
        }
    }
    
    
    public List<String> searchSalesPerson()
    {
        System.out.println("find salesPerson");
        List<String> lastname = this.SalesPersonRepository.searchByType();
        System.out.println("output lastname" + lastname);
        return lastname;
    }
    
    public String generateOrder()
    {   
        //        //check if buy
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
        Customer customer = loginManagedBean.getCustomer();
        
        List<Sale> sales = this.saleRepository.searchSale_valid(customer);
        System.out.println("===========================================");
        System.out.println("sales==========" + sales);
        
        String process = "";
        for(int i= 0; i<sales.size();i++)
        {
            process = sales.get(i).getSstate();
            System.out.println("process===========" + process);
            if(process.equals("Processing"))
            {
                break;
            }
        }
        if(process.equals("Processing"))
        {
                
                this.sale = sales.get(0);
                System.out.println("----------------------" + this.sale);
                return "/Customer/CustomerMyOrderProcess";
        }
        
        else
        {
        
            System.out.println("getVin====" + this.car.getVin());
            Car cat_get = this.carRepository.searchVIN(this.car.getVin()); 

            if(cat_get.getCstate().equals("Available"))
            {

                //generate date
                Date date = new Date(new java.util.Date().getTime());
                if(this.salesPersonType.equals("Please choose salesPerson:"))
                {
                    return "/Customer/Customer";
                }
                else
                {
                    SalesPerson salesPerson = this.SalesPersonRepository.searchByID(this.salesPersonType);
//
//                ELContext context = FacesContext.getCurrentInstance().getELContext();
//                LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
//                Customer customer = loginManagedBean.getCustomer();

                Car car = cat_get;

                String sstate = "Processing";

                this.sale = new Sale();

                if(salesPerson != null)
                {
                        sale.setSale_begin_date(date);
                        sale.setCid(customer);
                        sale.setSid(salesPerson);
                        sale.setVin(car);
                        sale.setSstate(sstate);
                        System.out.println("output custoemr sale====" + sale);
                        this.saleRepository.addSale(sale);

                        //get sale ID
                        this.saleid = this.saleRepository.searchById(car.getVin());
                        System.out.println("saleID=====" + saleid);
                                                
                        Car car_change = car;
                        car_change.setCstate("Not available");

                        System.out.println("change the customer state====" + car_change.getCstate());
                        this.carRepository.updateCarStateToNot(car_change);

                        return "/Customer/CustomerOrder";

                }
                else
                {
                        return "/Customer/Customer";
                }


                }

            }
            else
            {
                return "/Customer/Customer";
            }

        }
        
        
        
        
//        System.out.println("getVin====" + this.car.getVin());
//        Car cat_get = this.carRepository.searchVIN(this.car.getVin()); 
//        
//        if(cat_get.getCstate().equals("Available"))
//        {
//                    
//            //generate date
//            Date date = new Date(new java.util.Date().getTime());
//            if(this.salesPersonType.equals("Please choose salesPerson:"))
//            {
//                return "/Customer/Customer";
//            }
//            else
//            {
//                SalesPerson salesPerson = this.SalesPersonRepository.searchByID(this.salesPersonType);
////
////                ELContext context = FacesContext.getCurrentInstance().getELContext();
////                LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
////                Customer customer = loginManagedBean.getCustomer();
//
//                Car car = cat_get;
//
//                String sstate = "Processing";
//
//                this.sale = new Sale();
//
//                if(salesPerson != null)
//                {
//                        sale.setSale_begin_date(date);
//                        sale.setCid(customer);
//                        sale.setSid(salesPerson);
//                        sale.setVin(car);
//                        sale.setSstate(sstate);
//                        System.out.println("output custoemr sale====" + sale);
//                        this.saleRepository.addSale(sale);
//
//                        Car car_change = car;
//                        car_change.setCstate("Not available");
//
//                        System.out.println("change the customer state====" + car_change.getCstate());
//                        this.carRepository.updateCarStateToNot(car_change);
//
//                        return "/Customer/CustomerOrder";
//
//                }
//                else
//                {
//                        return "/Customer/Customer";
//                }
//
//
//                }
//
//        }
//        else
//        {
//            return "/Customer/Customer";
//        }

    }
    
    
    public String buySuccessful()
    {
        
               //generate date
               Date date = new Date(new java.util.Date().getTime());
               String sstate = "Completed";
               

               this.sale.setSstate(sstate);
               this.sale.setSale_finish_date(date);
               System.out.println("car.getVin---------"+car.getVin());
               System.out.println("car.sale++++++++++++++"+ sale.getVin().getVin());
               Long sale_update_id = this.saleRepository.searchById(sale.getVin().getVin());
               this.sale.setId(sale_update_id);
               System.out.println("输出sale" + this.sale.getId());
               
               System.out.println("salePersonnInformation====" + this.sale.getSale_begin_date());
               System.out.println("salesPersonBUYBUYBUY========" + this.sale.getSale_finish_date());
               this.saleRepository.UpdateSaleState(sale);
               return "/Customer/CustomerBuy";
        
        
        
    }
    
    public List<Sale> buySuccessful_list()
    {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
        Customer customer = loginManagedBean.getCustomer();
        
        List<Sale> sale = this.saleRepository.searchSale_buy(customer);
        return sale;
    }
    
    public List<Sale> order()
    {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
        Customer customer = loginManagedBean.getCustomer();
        if(customer != null)
        {
            List<Sale> sale = this.saleRepository.search_order(customer);
            return sale;
        }
        else
        {
            return null;
        }
    }
    
    public String HaveOrder()
    {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
        Customer customer = loginManagedBean.getCustomer();
        if(customer != null)
        {
            if(this.sale.getSstate().equals("Completed"))
            {
                return "/Customer/CustomerBuy";
            }
            else
            {
                return "/Customer/CustomerMyOrderProcess";
            }
            //for
        }
        else
        {
            return null;
        }
    }
    
    
    
    
    
    public List<Sale> order_process()
    {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
        Customer customer = loginManagedBean.getCustomer();
        if(customer != null)
        {
            List<Sale> sale = this.saleRepository.search_order_processing(customer);
            return sale;
        }
        else
        {
            return null;
        }
    }
    
    
    
    
    public String cancel()
    {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        LoginManagedBean loginManagedBean = (LoginManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "loginManagedBean");
        Customer customer = loginManagedBean.getCustomer();
        
        if(customer != null)
        {
            
            System.out.println("type salesPersonType-------- " + this.salesPersonType);
            SalesPerson salesPerson = this.SalesPersonRepository.searchByID(this.salesPersonType);           
            Car car_cancel = this.car;
            System.out.println("car====" + car_cancel);     
            String state = "Processing";
           
            Sale sale = new Sale(customer,car_cancel,salesPerson,state);
            sale.setSid(salesPerson);
            sale.setCid(customer);
            sale.setVin(car_cancel);
            sale.setSstate(state);
            
            System.out.println("salesPerson" + sale.getSid());
            
            this.saleRepository.remove(sale);
            this.carRepository.updateCarStateToNormal(car_cancel);
            
            
            return "/Customer/Customer";
        }
        else
        {
            return null;
        }
        

    }
    
}
