/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Think
 */
@Entity
@DiscriminatorValue("SalesPerson")
public class SalesPerson extends Users {


    public SalesPerson() {
    }

    
}
