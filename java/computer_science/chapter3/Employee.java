/**
 * Simulates an employee
 * 
 * @author Logan Traffas
 * @version 10/17/16
 * assignment: Chapter 03-Implementing Classes-E3.8-Employee
 */
public class Employee
{
    private String name;
    double salary;
    int yearsEmployed;
    /**
     * Instantiates an Employee class to default values
     */
    public Employee(){
       this.name = "Unknown";
       this.salary = 0.0;
       this.yearsEmployed = 0;
       
    }
    /**
     * Instantiates an Employee class to given values
     * @param name The name of the employee
     * @param salary The salary of the employee
     */
    public Employee(String name, double salary){
       this.name = name;
       this.salary = salary;
       
    }
     /**
     * Instantiates an Employee class to given values
     * @param name The name of the employee
     * @param salary The salary of the employee
     * @param yearsEmployed The number of years the employee has been employed
     */
    public Employee(String name, double salary, int yearsEmployed){
       this.name = name;
       this.salary = salary;
       this.yearsEmployed = yearsEmployed;
       
    }
    /**
     * Fetches the name of the employee
     * @return The name of the employee
     */
    public String getName(){
        return name;
    }
    /**
     * Fetches the salary of the employee
     * @return The salary of the employee
     */
    public double getSalary(){
        return salary;
    }
    /**
     * Fetches the number of years the employee has been employed
     * @return The number of years the employee has been employed
     */
    public int getYearsEmployed(){
        return yearsEmployed;
    }
    /**
     * Raises the employee's salary by a given percent
     * @param The percent to raise the employee's salary by
     */
    public void raiseSalary(double percent){
        salary = salary * (1.0 + percent/100);
    }
}
