package org.example;


import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {



        Scanner entry = new Scanner(System.in);
        System.out.println("İsim: ");
        String studentsName = entry.next();
        System.out.println("Numara: ");
        int studentsNumber = Integer.parseInt(entry.next());
        System.out.println("Puan: ");
        int studentsGrade = Integer.parseInt(entry.next());

        Students students = new Students(studentsName,studentsNumber,studentsGrade);
        StudentDbProcess studentDbProcess = new StudentDbProcess();
        DbConnection dbConnection = new DbConnection();




        StudentDbProcess.insertStudent(dbConnection.getDBConnection(),students);
        StudentDbProcess.selectSQL(dbConnection.getDBConnection());
        System.out.println("Notunu görmek istediğiniz numarayı giriniz: ");
        int qNumber = Integer.parseInt(entry.next());
        StudentDbProcess.findStudentNumber(dbConnection.getDBConnection(),qNumber);

    }
}