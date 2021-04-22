/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubt.interfaces.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import tubt.dto.RegistrationDTO;
import tubt.interfaces.RegistrationInterface;

/**
 *
 * @author buith
 */
public class RegistrationImpl extends UnicastRemoteObject implements RegistrationInterface {

    public RegistrationImpl() throws RemoteException {

    }

    String filePath = "C:\\Users\\buith\\Desktop\\Courses\\Bloc_3Weeks_SP21_CN8\\LAB221\\J2.L.P0002.FamilyHealthCare.Management.RMI.400\\src\\tubt.data\\RegistrationData.txt";

    @Override
    public boolean createRegistration(RegistrationDTO dto) throws RemoteException {
        try {
            File file = new File(filePath);
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
            //write data to file
            pw.println(dto.getRegistrationID() + "-" + dto.getFullName() + "-" + dto.getAge() + "-" + dto.isGender() + "-"
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
    public RegistrationDTO findByRegistrationID(String id) throws RemoteException {
        RegistrationDTO dto = null;
        String line;
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] recentLine = line.split("-");
                if (recentLine[0].equalsIgnoreCase(id)) {
                    String regisID = recentLine[0];
                    String fullName = recentLine[1];
                    int age = Integer.valueOf(recentLine[2]);
                    boolean gender = Boolean.valueOf(recentLine[3]);
                    String email = recentLine[4];
                    String phone = recentLine[5];
                    String address = recentLine[6];
                    int numberOfMember = Integer.valueOf(recentLine[7]);
                    int numberOfChildren = Integer.valueOf(recentLine[8]);
                    int numberOfAdults = Integer.valueOf(recentLine[9]);
                    dto = new RegistrationDTO(regisID, fullName, age, gender, email, phone, address, numberOfMember, numberOfChildren, numberOfAdults);
                }
            }
        } catch (Exception e) {
            System.out.println("Error while finding Registration By ID...");
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public ArrayList<RegistrationDTO> findAllRegistrations() throws RemoteException {
        ArrayList<RegistrationDTO> registrationList = new ArrayList<>();

        String line;
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                //StringTokenizer myToken = new StringTokenizer(line, "-");
                String[] currentLine = line.split("-");

                String id = currentLine[0];
                String fullName = currentLine[1];
                int age = Integer.valueOf(currentLine[2]);
                boolean gender = Boolean.valueOf(currentLine[3]);
                String email = currentLine[4];
                String phone = currentLine[5];
                String address = currentLine[6];
                int numberOfMember = Integer.valueOf(currentLine[7]);
                int numberOfChildren = Integer.valueOf(currentLine[8]);
                int numberOfAdults = Integer.valueOf(currentLine[9]);
                RegistrationDTO dto = new RegistrationDTO(id, fullName, age, gender, email, phone, address, numberOfMember, numberOfChildren, numberOfAdults);
                registrationList.add(dto);

            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error while loading all Registrations...");
            e.printStackTrace();
        }
        return registrationList;
    }

    @Override
    public boolean removeRegistration(String id) throws RemoteException {
        boolean result = false;
        try {
            File file = new File(filePath);
            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] currentLine = line.split("-");
                if (currentLine[0].equals(id)) {
                    result = true;

                } else {
                    sb.append(line);
                    sb.append("\n");
                }
            }
            try {
                PrintWriter pw = new PrintWriter(file);
                pw.append(sb);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateRegistration(RegistrationDTO dto) throws RemoteException {
        boolean result = false;
        try {

            File file = new File(filePath);

            StringBuilder sb = new StringBuilder();
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] currentLine = line.split("-");
                if (currentLine[0].equals(dto.getRegistrationID())) {
                    result = true;
                    String updateLine = dto.getRegistrationID() + "-" + dto.getFullName() + "-" + dto.getAge() + "-" + dto.isGender() + "-"
                            + dto.getEmail() + "-" + dto.getPhone() + "-" + dto.getAddress() + "-" + dto.getNumberOfMember() + "-"
                            + dto.getNumberOfChildren() + "-" + dto.getNumberOfAdults() + "\n";
                    //System.out.println(updateLine);
                    sb.append(updateLine);
                    System.out.println("StringBuilder: " + sb);
                } else {
                    sb.append(line);
                    sb.append("\n");
                }
            }
            try {
                PrintWriter pw = new PrintWriter(file);
                pw.append(sb);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
