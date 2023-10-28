package q6_3;

public class Employee {
    protected String name;
    protected double salary;

    public Employee(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    protected double getSalaryForJson() {
        return salary;
    }

    public String toJson() {
        return "{\"class\":\"Employee\", \"name\":\"" + name + "\", \"salary\":" + getSalaryForJson() + "}";
    }
}

