/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository;

import javax.ejb.Remote;

/**
 *
 * @author Think
 */
@Remote
public interface UserRepository {
    public String searchByNameAPassword(String username, String password);
    public String searchByType(String username, String password);
}
