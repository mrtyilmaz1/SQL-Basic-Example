package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLMethod {
    public static final String jdbcUrl = "jdbc:postgresql://localhost:5432/Students";
    public static final String kullaniciAdi = "postgres";
    public static final String parola = "22725";

    static Connection conn = null;

    public static Connection getConnection() {

        try {
            conn = DriverManager.getConnection(jdbcUrl, kullaniciAdi, parola);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void insertData(String studentsName, int studentsNumber, int studentsGrade) {
        try {
            conn = getConnection();
            String insertSQL = "INSERT INTO students (studentsName, studentsNumber, studentsGrade) VALUES (?, ?, ?)";
            // ? sembolleri, daha sonra parametrelerle değiştirilecek olan yer tutuculardır.
            PreparedStatement insertStatement = conn.prepareStatement(insertSQL); // SQL'e eklemek için gerekli değişkeni tanımladık.
            insertStatement.setString(1, studentsName);
            //ekleme metodu ile gelen değeri verilen değişken tipine göre ser edip index'i verilen ? için ekler.
            insertStatement.setInt(2, studentsNumber);
            insertStatement.setInt(3, studentsGrade);
            int affectedRows = insertStatement.executeUpdate();
            System.out.println("INSERT işlemi sonucunda etkilenen satır sayısı: " + affectedRows);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void selectSQL() {
        List<Students> studentsList;
                try {
            conn = getConnection();
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
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void findStudentNumber(int number){

        try{
            conn = getConnection();
            String query = "SELECT * FROM students WHERE student_number = ?"; // istenilen numarayı databasede sorgulamak için.
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(2, number);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String studentsName = resultSet.getString("studentsName");
                int studentsGrade = resultSet.getInt("studentsGrade");
                System.out.println("studentsName: " + studentsName + ", studentsGrade: " + studentsGrade);
            }
            else {
                System.out.println("Öğrenci bulunamadı.");
            }

            // Bağlantıyı kapatma
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        }

    }

