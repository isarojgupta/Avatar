package io.android.myadapterapp;

public class EmployeeModel {
    private int eId;
    private String eName;
    private Double eSalary;

    public EmployeeModel(int eId, String eName, Double eSalary) {
        this.eId = eId;
        this.eName = eName;
        this.eSalary = eSalary;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public Double geteSalary() {
        return eSalary;
    }

    public void seteSalary(Double eSalary) {
        this.eSalary = eSalary;
    }
}
