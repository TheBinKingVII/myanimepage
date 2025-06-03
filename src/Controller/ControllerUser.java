/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Users.DAOUser;
import Model.Users.InterfaceDAOUser;
import Model.Users.ModelUser;
import View.DashboardView;
import View.LoginView;
import View.RegisterView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author BINTORO
 */
public class ControllerUser {

    RegisterView halamanRegister;
    LoginView halamanLogin;
    DashboardView halamanDashboard;

    InterfaceDAOUser daoUser;
    List<ModelUser> listUser;

    public ControllerUser(RegisterView halamanRegister) {
        this.halamanRegister = halamanRegister;
        this.daoUser = new DAOUser();
    }

    public ControllerUser(LoginView halamanLogin) {
        this.halamanLogin = halamanLogin;
        this.daoUser = new DAOUser();
    }
    
    public ControllerUser(DashboardView halamanDashboard){
        this.halamanDashboard = halamanDashboard;
        this.daoUser = new DAOUser();
    }

    public boolean registerUser() {
        try {
            listUser = daoUser.getAll();
            ModelUser user = new ModelUser();

            String username = halamanRegister.getInputUsername();
            String password = halamanRegister.getInputPassword();
            String name = halamanRegister.getInputName();
            String umur = halamanRegister.getUmurText();

            if (username.isEmpty() || password.isEmpty() || name.isEmpty() || umur.isEmpty()) {
                JOptionPane.showMessageDialog(halamanRegister, "Semua Data harus diisi!!");
                return false;
            }

            for (ModelUser users : listUser) {
                if (users.getUsername().equals(username)) {
                    JOptionPane.showMessageDialog(halamanRegister, "Username sudah ada");
                    return false;
                }
            }
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setUmur(Integer.valueOf(umur));
            daoUser.insert(user);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(halamanRegister, "Error saat registrasi: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean loginUser() {
        try {
            listUser = daoUser.getAll();
            ModelUser user = new ModelUser();

            String username = halamanLogin.getInputUsername();
            String password = halamanLogin.getInputPassword();
            

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(halamanLogin, "Semua Data harus diisi!!");
                return false;
            }

            for (ModelUser users : listUser) {
                if (users.getUsername().equals(username) && users.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(halamanLogin, "Anda berhasil Login");
                    DashboardView halamanDashboard = new DashboardView(users.getId(), 1);
                    halamanLogin.dispose();
                    halamanDashboard.setVisible(true);
                    
                    return true;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(halamanLogin, "Error saat login: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    public ModelUser fetchUser(int ID){
        return daoUser.fetchUserByID(ID);
    }

}
