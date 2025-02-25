package entities;

/**
 *Employee entity class
 */
public class Employee {
    private int id;
    private String name;
    private String email;
    private int phone;

    public Employee(int id, String name, String email, int phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Employee() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    /**
     *Overriden toString method for console app output
     */
    @Override
    public String toString(){
        return "Employee {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", email = '" + email + '\'' +
                ", phone = " + phone +
                '}';
    }
}
