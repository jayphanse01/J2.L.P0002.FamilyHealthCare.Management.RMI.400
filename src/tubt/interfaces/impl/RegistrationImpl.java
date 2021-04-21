/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubt.interfaces.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import tubt.dto.RegistrationDTO;
import tubt.interfaces.RegistrationInterface;

/**
 *
 * @author buith
 */
public class RegistrationImpl extends UnicastRemoteObject implements RegistrationInterface{
    
    public RegistrationImpl() throws RemoteException{
        
    }
    
    String filePath = "C:\\Users\\buith\\Desktop\\Courses\\Bloc_3Weeks_SP21_CN8\\LAB221\\J2.L.P0002.FamilyHealthCare.Management.RMI.400\\src\\tubt.data\\RegistrationData.txt";
    
    @Override
    public boolean createRegistration(RegistrationDTO dto) {
        try {
            File file = new File(filePath);
            PrintWriter pw  = new PrintWriter(new FileOutputStream(file, true));
            //write data to file
            pw.println("id@" + dto.getRegistrationID() + "-" + "fullName@" + dto.getFullName() + "-" + dto.getAge() + "-" + dto.isGender() + "-"
                    + dto.getEmail() + "-" + dto.getPhone() + "-" + dto.getAddress() + "-" + dto.getNumberOfMember() + "-"
                    + dto.getNumberOfChildren() + "-" + dto.getNumberOfAdults());
            
            pw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error while adding...");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public RegistrationDTO findByRegistrationID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<RegistrationDTO> findAllRegistrations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeRegistration(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateRegistration(RegistrationDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
