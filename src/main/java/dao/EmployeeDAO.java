package dao;

import entities.Employee;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *Employee data access object class. Implements AutoClosable interface for use in try-with-resources
 */
public class EmployeeDAO implements AutoCloseable{
    private Connection connection;

    /**
     *SQL query constants for use in methods
     */
    private static final String SQL_GET = "SELECT * FROM employee";
    private static final String SQL_ADD = "INSERT INTO employee (name, email, phone) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE employee SET name = ?, email = ?, phone = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM employee WHERE id = ?";

    /**
     *Constructor. Establishes database connection
     */
    public EmployeeDAO(String url, String user, String password){
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *Returns all employee table entries as a List of Employee entities
     */
    public List<Employee> get(){
        List<Employee> employees = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET)){
            while(resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("phone"));
                employees.add(employee);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }

    /**
     *Inserts an entry into the employee table
     */
    public void add(Employee employee){
        try (PreparedStatement statement = connection.prepareStatement(SQL_ADD)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setInt(3, employee.getPhone());
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *Updates an entry in the employee table
     */
    public void update(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setInt(3, employee.getPhone());
            statement.setInt(4, employee.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *Deletes an entry from the employee table
     */
    public void delete(int id){
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *Closes database connection
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
