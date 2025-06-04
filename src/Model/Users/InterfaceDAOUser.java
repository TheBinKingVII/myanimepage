/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Users;

import java.util.List;

/**
 *
 * @author BINTORO
 */
public interface InterfaceDAOUser {
    public void insert(ModelUser user);
    public void update(ModelUser user);
    public void delete(int id);
    public List<ModelUser> getAll();
    public ModelUser fetchUserByID(int ID);
}
