package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {



        Scanner entry = new Scanner(System.in);
        System.out.println("İsim: ");
        String sName = entry.next();
        System.out.println("Numara: ");
        int sNumber = Integer.parseInt(entry.next());
        System.out.println("Puan: ");
        int sGrade = Integer.parseInt(entry.next());

        Students insertStudent = new Students(sName,sNumber,sGrade);

        SQLMethod.getConnection();
        SQLMethod.insertData(insertStudent.getStudentsName(),
                insertStudent.getStudentsNumber(), insertStudent.getStudentsGrade() );
        SQLMethod.selectSQL();
        System.out.println("Notunu görmek istediğiniz numarayı giriniz: ");
        int qNumber = Integer.parseInt(entry.next());
        SQLMethod.findStudentNumber(qNumber);

    }
}