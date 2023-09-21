package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {



        Scanner entry = new Scanner(System.in);
        System.out.println("İsim: ");
        String studentsName = entry.next();
        System.out.println("Numara: ");
        int studentsNumber = Integer.parseInt(entry.next());
        System.out.println("Puan: ");
        int studentsGrade = Integer.parseInt(entry.next());

        Students insertStudent = new Students(studentsName,studentsNumber,studentsGrade);

        SQLMethod.getConnection();
        SQLMethod.insertData(insertStudent.getStudentsName(),
                insertStudent.getStudentsNumber(), insertStudent.getStudentsGrade() );
        SQLMethod.selectSQL();
        System.out.println("Notunu görmek istediğiniz numarayı giriniz: ");
        int qNumber = Integer.parseInt(entry.next());
        SQLMethod.findStudentNumber(qNumber);

    }
}