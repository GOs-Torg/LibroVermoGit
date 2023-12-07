package com.example.librovermosite.models.plugs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class Employee {
    private long id_Employee;
    private String employee_Surname;
    private String employee_Middle_Name;
    private String employee_Name;
    @Pattern(regexp = "^[a-zA-Z0-9]{4,24}$")
    private String employee_Login;
    @Pattern(regexp = "^[a-zA-Z0-9]{4,32}$")
    private String employee_Password;
    @Email()
    private String employee_Email;
    private String id_Position;

    public Employee(){}

    public Employee(long id_Employee, String employee_Surname, String employee_Middle_Name, String employee_Name, String employee_Login, String employee_Password, String employee_Email, String employee_Position) {
        this.id_Employee = id_Employee;
        this.employee_Surname = employee_Surname;
        this.employee_Middle_Name = employee_Middle_Name;
        this.employee_Name = employee_Name;
        this.employee_Login = employee_Login;
        this.employee_Password = employee_Password;
        this.employee_Email = employee_Email;
        this.id_Position = employee_Position;
    }

    public long getId_Employee() {
        return id_Employee;
    }

    public void setId_Employee(long id_Employee) {
        this.id_Employee = id_Employee;
    }

    public String getEmployee_Surname() {
        return employee_Surname;
    }

    public void setEmployee_Surname(String employee_Surname) {
        this.employee_Surname = employee_Surname;
    }

    public String getEmployee_Middle_Name() {
        return employee_Middle_Name;
    }

    public void setEmployee_Middle_Name(String employee_Middle_Name) {
        this.employee_Middle_Name = employee_Middle_Name;
    }

    public String getEmployee_Name() {
        return employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        this.employee_Name = employee_Name;
    }

    public String getEmployee_Login() {
        return employee_Login;
    }

    public void setEmployee_Login(String employee_Login) {
        this.employee_Login = employee_Login;
    }

    public String getEmployee_Password() {
        return employee_Password;
    }

    public void setEmployee_Password(String employee_Password) {
        this.employee_Password = employee_Password;
    }

    public String getEmployee_Email() {
        return employee_Email;
    }

    public void setEmployee_Email(String employee_Email) {
        this.employee_Email = employee_Email;
    }

    public String getEmployee_Position() {
        return id_Position;
    }

    public void setEmployee_Position(String employee_Position) {
        this.id_Position = employee_Position;
    }
}
