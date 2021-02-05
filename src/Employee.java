public class Employee extends Person implements Comparable<Person>{
//    private static int EMPLOYEE_ID_TO_ASSIGN = 0;
    private String deptName;
    private static int numEmployees = 0;
    private int employeeID;

    public Employee(){
        super();
        this.deptName = "";
        numEmployees++;
        this.employeeID = numEmployees;
    }

    public Employee(String deptName){
        super();
        this.deptName = deptName;
        numEmployees++;
        this.employeeID = numEmployees;
    }
    public Employee(String name, int birthYear, String deptName){
        super(name, birthYear);
        this.deptName = deptName;
        numEmployees++;
        this.employeeID = numEmployees;
    }
    //errors check#1: moving numEmployees++ up

    // GETTERS
    public String getDeptName(){
        return this.deptName;
    }

    public static int getNumEmployees(){
        return numEmployees;
    }

    public int getEmployeeID(){
        return this.employeeID;
    }

    // SETTERS
    public void setDeptName(String deptName){
        this.deptName = deptName;
    }

    // METHODS

    @Override
    public boolean equals(Object o){
        if(o instanceof Employee) {
            Employee otherP = (Employee) o;
            if (super.equals(otherP)) {
                if(otherP.deptName.equals(this.deptName) && otherP.employeeID == this.employeeID){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
//        return String.format("<content of Person> Employee: Department: %20s | Employee Number: %3d", deptName, employeeID);
//        return String.format("        Person: Name:                   Employee: Department: %20s | Employee Number: %3d", deptName, employeeID);
        return String.format("%s Employee: Department: %20s | Employee Number: %3d", super.toString(), deptName, employeeID);


//Fidel Castro | Birth Year: 1926 Employee: Department:           Revolution | Employee Number:   7

    }

    public int compareTo(Employee p) {
        if(this.employeeID > p.employeeID){
            return 1;
        }
        else if(this.employeeID < p.employeeID){
            return -1;
        }
        return 0;
    }
}
/*

deptName: String
numEmployees: static int
employeeID: int		//generated

Employee()			// deptName = “”, employeeID computed
Employee(String deptName)	// employeeID computed
Employee(String name, int birthYear, String deptName)	// employeeID computed
getDeptName(): String
static getNumEmployees(): int
getEmployeeID(): int
setDeptName(String deptName): void
equals(Object obj): boolean	//all attributes inhereted+local must match for 2 Employee objects to be considered equal
toString(): String	//”<content of Person> Employee: Department: %20s | Employee Number: %3d”, deptName, employeeID
compareTo(Person p): int	//use the Comparable interface specification. Sort by employeeID

 */