/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import fit5192.assignment.repository.CarRepository;
import fit5192.assignment.repository.entities.Car;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Think
 */
@Named(value = "addCarManagedBean")
@RequestScoped
public class AddCarManagedBean implements Serializable{
    
    @EJB
    private CarRepository carRepository;   
        
    //add car
    private String add_modelName;
    private String add_vin;
    private String add_modelNo;
    private String add_make;
    private String add_type;
    private String add_color;
    private String add_price;
    private String add_description;
    private String add_PreviewURL;
    private String add_Image;
    private String add_State;
    private Boolean add_show;
    

    public AddCarManagedBean() {
        
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public String getAdd_modelName() {
        return add_modelName;
    }

    public void setAdd_modelName(String add_modelName) {
        this.add_modelName = add_modelName;
    }

    public String getAdd_vin() {
        return add_vin;
    }

    public void setAdd_vin(String add_vin) {
        this.add_vin = add_vin;
    }

    public String getAdd_modelNo() {
        return add_modelNo;
    }

    public void setAdd_modelNo(String add_modelNo) {
        this.add_modelNo = add_modelNo;
    }

    public String getAdd_make() {
        return add_make;
    }

    public void setAdd_make(String add_make) {
        this.add_make = add_make;
    }

    public String getAdd_type() {
        return add_type;
    }

    public void setAdd_type(String add_type) {
        this.add_type = add_type;
    }

    public String getAdd_color() {
        return add_color;
    }

    public void setAdd_color(String add_color) {
        this.add_color = add_color;
    }

    public String getAdd_price() {
        return add_price;
    }

    public void setAdd_price(String add_price) {
        this.add_price = add_price;
    }

    public String getAdd_description() {
        return add_description;
    }

    public void setAdd_description(String add_description) {
        this.add_description = add_description;
    }

    public String getAdd_PreviewURL() {
        return add_PreviewURL;
    }

    public void setAdd_PreviewURL(String add_PreviewURL) {
        this.add_PreviewURL = add_PreviewURL;
    }

    public String getAdd_Image() {
        return add_Image;
    }

    public void setAdd_Image(String add_Image) {
        this.add_Image = add_Image;
    }

    public String getAdd_State() {
        return add_State;
    }

    public void setAdd_State(String add_State) {
        this.add_State = add_State;
    }

    public Boolean getAdd_show() {
        return add_show;
    }

    public void setAdd_show(Boolean add_show) {
        this.add_show = add_show;
    }
    
    
    
    public String SalesPersonaddCar()
    {
        Car car = new Car();       
        Car search_result = this.carRepository.s_searchVIN(add_vin);
        
        System.out.println("search_result =======" + search_result);
        if(search_result == null)
        {
            car.setVin(add_vin);
            car.setType(add_type);
            car.setThumbnail(add_Image);
            float price_new = Float.parseFloat(add_price);
            car.setPrice(price_new);
            car.setPreview_url(add_PreviewURL);
            car.setModelNo(add_modelNo);
            car.setModelName(add_modelName);
            car.setMake(add_make);
            car.setDescription(add_description);
            car.setCstate(add_State);
            car.setColor(add_color);

            this.add_show = true;

            System.out.println("Car add=====" + car);
            this.carRepository.addCar(car);
            return "/SalesPerson/SalesPersonAdd";
        }
        else
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("salesPersonAddForm:Add_vin", new FacesMessage(FacesMessage.SEVERITY_WARN,"Car had", "Car already has !: "));
            return "/SalesPerson/SalesPersonAdd";
        }
        
        
    }
    
    
    public Car show()
    {
        Car car = this.carRepository.s_searchVIN(add_vin);
        return car;
    }
    
    
    
    
    public String useAPI() throws UnsupportedEncodingException
    {
        URL url;
        try
        {
            String path = "https://api.edmunds.com/api/vehicle/v2/vins/"+this.add_vin+"?&fmt=json&api_key=m84maxzxqyjbgdb23pm8spkt";
            //String path = "https://api.edmunds.com/api/vehicle/v2/vins/4T1BK1EB6DU056165?&fmt=json&api_key=m84maxzxqyjbgdb23pm8spkt";
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            StringBuilder sb = new StringBuilder();
            String output;
            while((output = reader.readLine()) != null)
            {
                sb.append(output);
            }
            conn.disconnect();
            
            String required = sb.toString();
            
            JSONObject jsonObject = new JSONObject(required);
            
            System.out.println("sb.append=======" + sb);
            
            JSONObject jsonObject_modelName = jsonObject.getJSONObject("make");
            String Modelname = jsonObject_modelName.getString("name");
            System.out.println("modelName:===" + Modelname);
            this.add_modelName = Modelname;
            
            JSONObject jsonObject_modelNo = jsonObject.getJSONObject("model");
            String ModelNo = jsonObject_modelNo.getString("name");
            System.out.println("modelNo===" + ModelNo);
            this.add_modelNo = ModelNo;
            
            JSONObject jsonObject_make = jsonObject.getJSONObject("make");
            String make = jsonObject_modelName.getString("name");
            System.out.println("modelMake:===" + make);
            this.add_make = make;
            
            String type = jsonObject.getString("drivenWheels");
            System.out.println("type====" + type);
            this.add_type = type;
            
            JSONArray jsonArray_color1 = jsonObject.getJSONArray("options");
            JSONObject jsonObject_color2 = jsonArray_color1.getJSONObject(0);
            System.out.println("color2" + jsonObject_color2);
            JSONArray jjsonArray_color3 = jsonObject_color2.getJSONArray("options");
            System.out.println("color3" + jjsonArray_color3);
            JSONObject jsonObject_color4 = jjsonArray_color3.getJSONObject(0);
            String description = jsonObject_color4.getString("description");
            System.out.println("description====" + description);
            this.add_description = description;
                   
        }catch(Exception e)
        {
            
        }
        
        invokeSearch();
        invokeImage();
        
        return "/SalesPerson/SalesPersonAdd";
    }
    
    
       public  void invokeSearch() throws UnsupportedEncodingException
    {
      
        String url_1 = "";
        url_1 = URLEncoder.encode(this.add_modelName,"utf-8");
        url_1 = url_1.replace("+", "%20");
        String modelname_new = url_1;  
        
        String path1 = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%27" + modelname_new + "%27&$top=3&$skip=1&$format=JSON&Market=%27en-GB%27";
        //String path1 = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%27" + "Toyota" + "%27&$top=1&$skip=1&$format=JSON&Market=%27en-GB%27";
        URL url;
        HttpURLConnection conn = null;
        String username = "65196cf1-49f4-44d6-ae1f-3e3bbe518319";
        String password = "crfO+EBYiFbGV6EJUQIiG957gXMq8BzmuU7jnSngllE=";
        String userpass = username+":"+password;
        //update
        String basicAuth ="Basic "+new String(Base64.encode(userpass.getBytes()));
        try {
            url = new URL(path1);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization",basicAuth);
            String resmsg = "";
            System.out.println("1111  " + conn.getResponseCode());
            InputStream instream = new BufferedInputStream(conn.getInputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(instream));
            StringBuilder sb = new StringBuilder();
            while ((resmsg = buffer.readLine()) != null) {
                sb.append(resmsg);
            }
            buffer.close();
            //System.out.println("output===" + sb.toString());
            String required = sb.toString();
            JSONObject jsonObject = new JSONObject(required);
            JSONObject jsonObject1 = jsonObject.getJSONObject("d");
            JSONArray jsonArray2 = jsonObject1.getJSONArray("results");
            JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
            String decription = jsonObject2.getString("Description");
            String preview_url = jsonObject2.getString("Url");
            System.out.println("decription" + decription);
            System.out.println("preview_url" + preview_url);
            
            //this.rest_description = decription;
            this.add_PreviewURL = preview_url;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("88888000000");
        } finally {
            conn.disconnect();
        }

    
    }
    
    
    
    public void invokeImage() throws UnsupportedEncodingException
    {

        String url_1 = "";
        url_1 = URLEncoder.encode(this.add_modelName,"utf-8");
        url_1 = url_1.replace("+", "%20");
        String modelname_new = url_1;            
        String path = "https://api.datamarket.azure.com/Bing/Search/Image?Query=%27"+modelname_new+"%27&$top=1&$skip=1&$format=JSON";
        //String path =  "https://api.datamarket.azure.com/Bing/Search/Image?Query=%27"+ "Toyota" +"%27&$top=1&$skip=1&$format=JSON";

        
        URL url;
        HttpURLConnection conn = null;
        String username = "65196cf1-49f4-44d6-ae1f-3e3bbe518319";
        String password = "crfO+EBYiFbGV6EJUQIiG957gXMq8BzmuU7jnSngllE=";
        String userpass = username+":"+password;
        String basicAuth ="Basic "+new String(Base64.encode(userpass.getBytes()));
        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization",basicAuth);
            String resmsg = "";
            InputStream instream = new BufferedInputStream(conn.getInputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(instream));
            StringBuilder sb = new StringBuilder();
            while ((resmsg = buffer.readLine()) != null) {
                sb.append(resmsg);
            }
            buffer.close();
            
            String required = sb.toString();
            JSONObject jsonObject = new JSONObject(required);
            JSONObject jsonObject1 = jsonObject.getJSONObject("d");
            JSONArray jsonArray2 = jsonObject1.getJSONArray("results");
            JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
            String mediaUrl = jsonObject2.getString("MediaUrl");
            
            System.out.println("mediaURL===" + mediaUrl);
            Image(mediaUrl);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("88888000000");
        } finally {
            conn.disconnect();
        }
        
        
        
        
    }
    
    public void Image(String path)
    {
        URL url;
        HttpURLConnection conn = null;
        //Bitmap bitmap = null;
        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(20000);
            conn.setDoInput(true);
//                conn.setRequestMethod("GET");
            //System.out.println("3333   " + conn.getResponseCode());
            conn.connect();
            System.out.println("55555   " + conn.getResponseCode());
            if(conn.getResponseCode()==301 ||conn.getResponseCode()==403 || conn.getResponseCode()==404)
            {
                //return null;
            }
            InputStream is = conn.getInputStream();
            //bitmap = BitmapFactory.decodeStream(is);
             
            byte[] data = readInputStream(is);  
  
//            HttpServletRequest request = new HttpServletRequest();
//            String filePath = request.getSession().getServletContext().getRealPath("Image");  
            //File imageFile = new File("filePath"+this.add_vin+".jpg");    
            
            FacesContext facesContext = FacesContext.getCurrentInstance(); 
            ExternalContext externalContext = facesContext.getExternalContext(); 
            
            System.out.println("Context path:" + externalContext.getRequestContextPath()); 
            System.out.println("path========" + externalContext.getRealPath("/"));
          //  File imageFile = new File(externalContext.getRealPath("/")+"/Image/"+this.add_vin+".jpg");   
            File imageFile = new File("D:\\netbeans_data\\FIT5192-web\\web"+"/Image/"+this.add_vin+".jpg");   
            //D:\netbeans_data\FIT5192-web\web\Image
            
            
            //File imageFile = new File(externalContext.getRealPath("/Image")+"/"+this.add_vin+".jpg"); 
            this.add_Image = this.add_vin+".jpg";
        
            FileOutputStream outStream = new FileOutputStream(imageFile);
            System.out.println("outStream=======" + outStream);
            outStream.write(data);    
            outStream.close();  
            is.close();
            conn.getResponseCode();
            //Bitmap b = bitmap
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("000000");
        } finally {
            conn.disconnect();
        }
    }
    
    
     public  byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;    
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }    
        inStream.close();    
        return outStream.toByteArray();  
    }  
    
     
     
       
       
       
       
    

}
