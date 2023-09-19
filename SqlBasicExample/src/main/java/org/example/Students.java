package org.example;

public class Students {

    private String studentsName;
    private int studentsNumber;
    private int studentsGrade;

    public Students(String studentsName, int studentsNumber, int studentsGrade) {
        this.studentsName = studentsName;
        this.studentsNumber = studentsNumber;
        this.studentsGrade = studentsGrade;
    }

    public String getStudentsName() {
        return studentsName;
    }

    public void setStudentsName(String studentsName) {
        this.studentsName = studentsName;
    }

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public int getStudentsGrade() {
        return studentsGrade;
    }

    public void setStudentsGrade(int studentsGrade) {
        this.studentsGrade = studentsGrade;
    }
}
