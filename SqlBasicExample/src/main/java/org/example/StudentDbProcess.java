package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDbProcess {
    public static final String jdbcUrl = "jdbc:postgresql://localhost:5432/Students";
    public static final String kullaniciAdi = "postgres";
    public static final String parola = "22725";

    static Connection conn = null;


    public static void insertStudent(Connection conn,Students insertStudent) throws SQLException {


            String insertSQL = "INSERT INTO students (studentsname, studentsnumber, studentsgrade) VALUES (?, ?, ?)";
            // ? sembolleri, daha sonra parametrelerle değiştirilecek olan yer tutuculardır.
            PreparedStatement insertStatement = conn.prepareStatement(insertSQL); // SQL'e eklemek için gerekli değişkeni tanımladık.
            insertStatement.setString(1,insertStudent.getStudentsName() );
            //ekleme metodu ile gelen değeri verilen değişken tipine göre ser edip index'i verilen ? için ekler.
            insertStatement.setInt(2, insertStudent.getStudentsNumber());
            insertStatement.setInt(3, insertStudent.getStudentsGrade());
            int affectedRows = insertStatement.executeUpdate();
            System.out.println("INSERT işlemi sonucunda etkilenen satır sayısı: " + affectedRows);
            conn.close();
        }


    public static void selectSQL(Connection conn) throws SQLException {
        List<Students> studentsList;


            // SELECT işlemi
            String selectSQL = "SELECT * FROM students"; // SQL sorgusunu doğrudan yamamıza yarıyor.
            PreparedStatement selectStatement = conn.prepareStatement(selectSQL); // SQL select için gerekli değişkeni tanımladık.
            ResultSet resultSet = selectStatement.executeQuery();
            studentsList = new ArrayList<>(); // gösterilecek veriler liste şeklinde tutulur.
                    while (resultSet.next()) /*bir sonraki satır olduğu sürece çalışır. */ {
                        // Sonuçları işleme
                        String studentsName = resultSet.getString("studentsname");
                        int studentsNumber = resultSet.getInt("studentsnumber");
                        int studentsGrade = resultSet.getInt("studentsgrade");
                        Students students = new Students(studentsName, studentsNumber, studentsGrade);
                        studentsList.add(students);
                        System.out.println("studentsname: " + studentsName + ", studentsgrade: " + studentsGrade);
                    }

        }
    public static void findStudentNumber(Connection conn, int number) throws SQLException {



            String query = "SELECT * FROM students WHERE studentsnumber = ?"; // istenilen numarayı databasede sorgulamak için.
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, number);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String studentsName = resultSet.getString("studentsname");
                int studentsGrade = resultSet.getInt("studentsgrade");
                System.out.println("studentsname: " + studentsName + ", studentsgrade: " + studentsGrade);
            }
            else {
                System.out.println("Öğrenci bulunamadı.");
            }

            // Bağlantıyı kapatma
            conn.close();
        }

        }



