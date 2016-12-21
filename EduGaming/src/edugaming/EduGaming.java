/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edugaming;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rami
 */
public class EduGaming {
      static Interface IN=new Interface();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Interface i=new Interface();
        i.reqRegister();
        Registered r = i.reqLogin();
        i.reqCreate(r);
        
        i.reqPlay(1);
     */
        HomeForm f=new HomeForm();
        f.setVisible(true);
         if (IN.getUsersSize()==0){
             f.hideLogin();
        }
        // TODO code application logic here
    }
    
}
