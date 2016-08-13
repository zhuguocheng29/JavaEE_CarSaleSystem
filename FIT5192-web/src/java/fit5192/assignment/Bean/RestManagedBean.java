/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import static fit5192.assignment.Bean.test.invokeImage;
import static fit5192.assignment.Bean.test.invokeSearch;
import fit5192.assignment.repository.CarRepository;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import static javax.servlet.SessionTrackingMode.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Think
 */
@Named(value = "restManagedBean")
@SessionScoped
public class RestManagedBean implements Serializable{

    @EJB
    private CarRepository carRepository;
    
    private String rest_vin;
    
    private String rest_modelName;
    
    private String rest_modelNo;
    
    private String rest_Make;
    
    private String rest_Type;
    
    private String rest_color;
    
    private float rest_price;
    
    private String rest_description;
    
    private String rest_previewUrl;
    
    private String rest_image;
    
    private String rest_state;

    public float getRest_price() {
        return rest_price;
    }

    public void setRest_price(float rest_price) {
        this.rest_price = rest_price;
    }
    
    

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public String getRest_vin() {
        return rest_vin;
    }

    public void setRest_vin(String rest_vin) {
        this.rest_vin = rest_vin;
    }

    public String getRest_modelName() {
        return rest_modelName;
    }

    public void setRest_modelName(String rest_modelName) {
        this.rest_modelName = rest_modelName;
    }

    public String getRest_modelNo() {
        return rest_modelNo;
    }

    public void setRest_modelNo(String rest_modelNo) {
        this.rest_modelNo = rest_modelNo;
    }

    public String getRest_Make() {
        return rest_Make;
    }

    public void setRest_Make(String rest_Make) {
        this.rest_Make = rest_Make;
    }

    public String getRest_Type() {
        return rest_Type;
    }

    public void setRest_Type(String rest_Type) {
        this.rest_Type = rest_Type;
    }

    public String getRest_color() {
        return rest_color;
    }

    public void setRest_color(String rest_color) {
        this.rest_color = rest_color;
    }

    public String getRest_description() {
        return rest_description;
    }

    public void setRest_description(String rest_description) {
        this.rest_description = rest_description;
    }

    public String getRest_previewUrl() {
        return rest_previewUrl;
    }

    public void setRest_previewUrl(String rest_previewUrl) {
        this.rest_previewUrl = rest_previewUrl;
    }

    public String getRest_image() {
        return rest_image;
    }

    public void setRest_image(String rest_image) {
        this.rest_image = rest_image;
    }

    public String getRest_state() {
        return rest_state;
    }

    public void setRest_state(String rest_state) {
        this.rest_state = rest_state;
    }
    
    
    
    
    public RestManagedBean() {
    }
    
    public String search() throws UnsupportedEncodingException
    {
        
        URL url;
        try
        {
            String path = "https://api.edmunds.com/api/vehicle/v2/vins/"+this.rest_vin+"?&fmt=json&api_key=m84maxzxqyjbgdb23pm8spkt";
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
            this.rest_modelName = Modelname;
            
            JSONObject jsonObject_modelNo = jsonObject.getJSONObject("model");
            String ModelNo = jsonObject_modelNo.getString("name");
            System.out.println("modelNo===" + ModelNo);
            this.rest_modelNo = ModelNo;
            
            JSONObject jsonObject_make = jsonObject.getJSONObject("make");
            String make = jsonObject_modelName.getString("name");
            System.out.println("modelMake:===" + make);
            this.rest_Make = make;
            
            String type = jsonObject.getString("drivenWheels");
            System.out.println("type====" + type);
            this.rest_Type = type;
            
//            JSONArray jsonArray_color1 = jsonObject.getJSONArray("options");
//            JSONObject jsonObject_color2 = jsonArray_color1.getJSONObject(0);
//            System.out.println("color2" + jsonObject_color2);
//            JSONArray jjsonArray_color3 = jsonObject_color2.getJSONArray("options");
//            System.out.println("color3" + jjsonArray_color3);
//            JSONObject jsonObject_color4 = jjsonArray_color3.getJSONObject(0);
//            String description = jsonObject_color4.getString("description");
//            System.out.println("description====" + description);
                   
        }catch(Exception e)
        {
            
        }
        
        invokeSearch();
        invokeImage();
        
        return "/SalesPerson/SalesPersonRest";
    }
    
    
     public  void invokeSearch() throws UnsupportedEncodingException
    {
      
        String url_1 = "";
        url_1 = URLEncoder.encode(this.rest_modelName,"utf-8");
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
            
            this.rest_description = decription;
            this.rest_previewUrl = preview_url;
            
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
        url_1 = URLEncoder.encode(this.rest_modelName,"utf-8");
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
  
            File imageFile = new File("web\\Image\\Toyota.jpg");    
            FileOutputStream outStream = new FileOutputStream(imageFile);    
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
    
     
     public void add()
     {
         
     }
     
     
}
