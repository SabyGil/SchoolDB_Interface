public class Faculty extends Employee implements Comparable<Person> {
    private Course[] coursesTaught;
    private int numCoursesTaught;
    private boolean isTenured;

    public Faculty(){
        super();
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = false;
    }

    public Faculty(boolean isTenured){
        this();
        this.isTenured = isTenured;
    }

    public Faculty(String deptName, boolean isTenured){
        super(deptName);
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public Faculty(String name, int birthYear, String deptName, boolean isTenured){
        super(name, birthYear, deptName);
        this.coursesTaught = new Course[100];
        this.numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public boolean isTenured() {
        return isTenured;
    }

    public int getNumCoursesTaught() {
        return numCoursesTaught;
    }

    public void setIsTenured(boolean isTenured) {
        this.isTenured = isTenured;
    }

    public void addCourseTaught(Course course){
        for(int i = 0; i < coursesTaught.length; i++){
            if(coursesTaught[i] == null){
                coursesTaught[i] = course;
                numCoursesTaught++;
                return;
            }
        }
    }

    public void addCoursesTaught(Course[] course) {
        for (int i = 0; i < course.length; i++) {
            addCourseTaught(course[i]);
        }
    }

    public Course getCourseTaught(int index){
        if(index >= coursesTaught.length || index < 0 || coursesTaught[index] == null){
            return null;
        }
        return coursesTaught[index];
    }

    public String getCourseTaughtAsString(int index){
        if(getCourseTaught(index) == null){
            return "";
        }

        String courseDept = coursesTaught[index].getCourseDept();
        int courseNum = coursesTaught[index].getCourseNum();

        return courseDept + "-" + courseNum;
    }

    public String getAllCoursesTaughtAsString(){
        // comma seperated list of all courses taught
        //  uses getCourseTaughtAsString(int index) as a helper method
        String temp = "";
        for(int i = 0; i < coursesTaught.length; i++){
            if(!getCourseTaughtAsString(i).equals("")){
                if(!getCourseTaughtAsString(i+1).equals("")){
                    temp += getCourseTaughtAsString(i) + ",";
                } else {
                    temp += getCourseTaughtAsString(i);
                }
            }
        }
        return temp;
    }

    @Override
    public boolean equals(Object o){
        // all attributes inhereted+local must match for 2 Faculty objects to be considered equal
        if(o instanceof Faculty){
            Faculty temp = (Faculty) o;
            if(super.equals(temp)){
                if(temp.numCoursesTaught == this.numCoursesTaught && temp.isTenured == this.isTenured){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String tenured = isTenured == true ? "Is Tenured": "Not Tenured";
        return String.format("%s Faculty: %11s | " +
                        "Number of Courses Taught: %3d | Courses Taught: %s", super.toString(), tenured, numCoursesTaught, getAllCoursesTaughtAsString());
    }
    /*
    Error ----- f.toString() returns:
	<content of Employee> Faculty:        true | Number of Courses Taught:   0 | Courses Taught:
instead of:
	Person: Name:                   Fidel Castro | Birth Year: 1926 Employee: Department:           Revolution | Employee Number:  11 Faculty:  Is Tenured | Number of Courses Taught:   0 | Courses Taught:
     */

    public int compareTo(Faculty p) {
        if(this.numCoursesTaught > p.numCoursesTaught){
            return 1;
        }
        else if(this.numCoursesTaught < p.numCoursesTaught){
            return -1;
        }
        return 0;
    }
}

/*

coursesTaught: Course[ ]	// you can assume that the maximum number of courses a faculty has
                                               // taught cannot exceed 100
numCoursesTaught: int 	//controlled variable
isTenured: boolean

Faculty()	// coursesTaught = [], numCoursesTaught = 0, isTenured = false
Faculty(boolean isTenured)	// coursesTaught = [], numCoursesTaught = 0, this.isTenured = isTenured
Faculty(String deptName, boolean isTenured)
Faculty(String name, int birthYear, String deptName, boolean isTenured)
isTenured():boolean
getNumCoursesTaught(): int
setIsTenured(boolean isTenured): void
addCourseTaught(Course course): void	//appends course to the end of the existing array
addCoursesTaught(Course [] course): void	//appends courses to the end of the existing array
getCourseTaught(int index): Course		// note: index must be verified. Return “null” if invalid
getCourseTaughtAsString(int index): String	// note: index must be verified. Return “” if invalid
                                                                       // returns “courseDept-courseNum”
getAllCoursesTaughtAsString(): String	// comma seperated list of all courses taught
                                                           //  uses getCourseTaughtAsString(int index) as a helper method
equals(Object obj): boolean	//all attributes inhereted+local must match for 2 Faculty objects to be considered equal
toString(): String	//”<content of Employee> Faculty: %11s | Number of Courses Taught: %3d |
                                            Courses Taught: %s”, Is Tenured/Not Tenured , numCoursesTaught,
                                            getAllCoursesTaughtAsString()
compareTo(Person p): int	// use the Comparable interface specification, sort by
                                               // numCoursesTaught



 */