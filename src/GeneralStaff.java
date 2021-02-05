public class GeneralStaff extends Employee {
    private String duty;

    public GeneralStaff(){
        super();
        duty = "";
    }

    public GeneralStaff(String duty){
        super();
        this.duty = duty;
    }

    public GeneralStaff(String deptName, String duty){
        super(deptName);
        this.duty = duty;
    }

    public GeneralStaff(String name, int birthYear, String deptName, String duty){
        super(name, birthYear, deptName);
        this.duty = duty;
    }

    public String getDuty() {
        return duty;
    }

    public boolean equals(Object o){
        if(o instanceof GeneralStaff){
            GeneralStaff temp = (GeneralStaff) o;
            if(super.equals(temp)){
                if(temp.duty.equals(this.duty)){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        return String.format("%s GeneralStaff: Duty: %10s", super.toString(), duty);
    }

    @Override
    public int compareTo(Person p) {
        return super.compareTo(p);
    }
}
