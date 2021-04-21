/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubt.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import tubt.dto.RegistrationDTO;

/**
 *
 * @author buith
 */
public interface RegistrationInterface extends Remote{
    boolean createRegistration(RegistrationDTO dto) throws RemoteException;
    RegistrationDTO findByRegistrationID(String id) throws RemoteException;
    ArrayList<RegistrationDTO> findAllRegistrations() throws RemoteException;
    boolean removeRegistration(String id) throws RemoteException;
    boolean updateRegistration(RegistrationDTO dto) throws RemoteException;
}
