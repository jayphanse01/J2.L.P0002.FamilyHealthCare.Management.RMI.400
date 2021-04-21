/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubt.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import tubt.interfaces.impl.RegistrationImpl;

/**
 *
 * @author buith
 */
public class RegistrationServer {

    public static void main(String[] args) {
        try {
            RegistrationImpl registrationInterface = new RegistrationImpl();
            LocateRegistry.createRegistry(1919);
            Naming.bind("rmi://localhost:1919/RegistrationServer", registrationInterface);
            System.out.println("Server is running....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
