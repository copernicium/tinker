package ComputerScience.Chapter3;
/**
 * Tests the Employee class.
 * 
 * @author Logan Traffas
 * @version 10/17/16
 * assignment: Chapter 03-Implementing Classes-E3.8-Employee
 */
public class EmployeeTester
{
    public static void main(String[] args){
        Employee steve = new Employee("Baron Blinky", 50000);
        Employee sami = new Employee("Slacker Sami", 1425,3);
        System.out.println("Name - Employee One: " + steve.getName() + " Employee Two: " + sami.getName());
        System.out.println("Years Empoyed - Employee One: " + steve.getYearsEmployed() + " Employee Two: " + sami.getYearsEmployed());
        System.out.println("Salary - Employee One: $" + steve.getSalary() + " Employee Two: $" + sami.getSalary());
        steve.raiseSalary(10); 
        sami.raiseSalary(20);
        System.out.println("New Expected Salary - Employee One: $55000" + " Employee Two: $1710.50");
        System.out.println("New Salary - Employee One: $" + steve.getSalary() + " Employee Two: $" + sami.getSalary());
        if(steve.getSalary() > sami.getSalary()) System.out.println(steve.getName() + " has a salary of $" + steve.getSalary() + ", which is more than " + sami.getName() + "'s salary of $" + sami.getSalary());
        else System.out.println(sami.getName() + " has a salary of $" + sami.getSalary() + ", which is more than " + steve.getName() + "'s salary of $" + steve.getSalary());
    }
}
