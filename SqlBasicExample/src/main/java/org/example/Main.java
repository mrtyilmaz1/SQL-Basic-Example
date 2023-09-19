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


        String jdbcUrl = "jdbc:postgresql://localhost:5432/Students";
        String kullaniciAdi = "postgres";
        String parola = "22725";
        List<Students> studentsList = null;
        Students insertStudent = new Students(sName,sNumber,sGrade);

        try (Connection conn = DriverManager.getConnection(jdbcUrl, kullaniciAdi, parola)) {

            // INSERT işlemi
            String insertSQL = "INSERT INTO students (studentsName, studentsNumber, studentsGrade) VALUES (?, ?, ?)";
            // ? sembolleri, daha sonra parametrelerle değiştirilecek olan yer tutuculardır.

            PreparedStatement insertStatement = conn.prepareStatement(insertSQL); // SQL'e eklemek için gerekli değişkeni tanımladık.
            insertStatement.setString(1, insertStudent.getStudentsName());
            //ekleme metodu ile gelen değeri verilen değişken tipine göre ser edip index'i verilen ? için ekler.
            insertStatement.setInt(2, insertStudent.getStudentsNumber());
            insertStatement.setInt(3, insertStudent.getStudentsGrade());
            int affectedRows = insertStatement.executeUpdate();
            System.out.println("INSERT işlemi sonucunda etkilenen satır sayısı: " + affectedRows);

            // SELECT işlemi
            String selectSQL = "SELECT * FROM students"; // SQL sorgusunu doğrudan yamamıza yarıyor.
            PreparedStatement selectStatement = conn.prepareStatement(selectSQL); // SQL select için gerekli değişkeni tanımladık.
            ResultSet resultSet = selectStatement.executeQuery();
            studentsList = new ArrayList<>(); // gösterilecek veriler liste şeklinde tutulur.

            while (resultSet.next()) /*bir sonraki satır olduğu sürece çalışır. */ {
                // Sonuçları işleme
                String studentsName = resultSet.getString("studentsName");
                int studentsNumber = resultSet.getInt("studentsNumber");
                int studentsGrade = resultSet.getInt("studentsGrade");
                Students students = new Students(studentsName, studentsNumber, studentsGrade);
                studentsList.add(students);
                System.out.println("studentsName: " + studentsName + ", studentsGrade: " + studentsGrade);
            }

            // Bağlantıyı kapatma
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}