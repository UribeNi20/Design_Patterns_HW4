package q6_3;

public class Manager extends Employee {
    private double bonus;

    public Manager(String name) {
        super(name);
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    protected double getSalaryForJson() {
        return salary + bonus;
    }

    @Override
    public String toJson() {
        return "{\"class\":\"Manager\", \"name\":\"" + name + "\", \"salary\":" + getSalaryForJson() + ", \"bonus\":" + bonus + "}";
    }

    public static void main(String[] args) {
        Employee sarah = new Employee("Sarah");
        sarah.setSalary(50000);
        System.out.println(sarah.toJson());

        Manager sandy = new Manager("Sandy");
        sandy.setSalary(100000);
        sandy.setBonus(1234);
        System.out.println(sandy.toJson());
    }
}
