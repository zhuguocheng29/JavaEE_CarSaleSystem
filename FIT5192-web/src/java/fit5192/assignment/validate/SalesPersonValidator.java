/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.validate;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Think
 */
@FacesValidator("salesPersonValidator")
public class SalesPersonValidator implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String componentValue = value.toString();
        if (componentValue.equals("Please choose salesPerson:")) {
        //String msg = MessageFormat.format("{0} not a valid password format,password consists of 6-10 letters or Numbers", componentValue);
        String msg = "Please choose salesPerson !";
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        throw new ValidatorException(facesMessage);
}
    }
    
    
}
