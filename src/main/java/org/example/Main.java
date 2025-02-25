package org.example;

import dao.EmployeeDAO;
import entities.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     *URL username, and password constants used for establishing a database connection
     */
    private static final String URL = "jdbc:postgresql://localhost/dbTest";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Otrjneyxbr4";

    /**
     *Console app main method
     */
    public static void main(String[] args) {
        try (EmployeeDAO employeeDAO = new EmployeeDAO(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                //Console app main menu
                System.out.println("1. Add employee");
                System.out.println("2. View employees");
                System.out.println("3. Update employee");
                System.out.println("4. Delete employee");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    //Adding entry
                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter phone: ");
                        int phone = scanner.nextInt();
                        scanner.nextLine();

                        employeeDAO.add(new Employee(0, name, email, phone));
                        System.out.println("Employee added successfully!\n");
                        break;

                    //Reading and outputting entries
                    case 2:
                        List<Employee> employees = employeeDAO.get();
                        for (Employee employee : employees) {
                            System.out.println(employee);
                        }
                        System.out.println();
                        break;

                    //Updating entry
                    case 3:
                        System.out.print("Enter employee ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Enter new phone: ");
                        int newPhone = scanner.nextInt();
                        scanner.nextLine();

                        employeeDAO.update(new Employee(updateId, newName, newEmail, newPhone));
                        System.out.println("Employee updated successfully!\n");
                        break;

                    //Deleting entry
                    case 4:
                        System.out.print("Enter employee ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();

                        employeeDAO.delete(deleteId);
                        System.out.println("Employee deleted successfully!\n");
                        break;

                    //Exiting the app
                    case 5:
                        System.out.println("Exiting...");
                        return;

                    //Invalid menu option
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}