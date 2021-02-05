public class Course implements Comparable<Course>{
    private boolean isGraduateCourse;
    private int courseNum;
    private String courseDept;
    private int numCredits;

    public Course(boolean isGraduateCourse, int courseNum, String courseDept, int numCredits){
        this.isGraduateCourse = isGraduateCourse;
        this.courseNum = courseNum;
        this.courseDept = courseDept;
        this.numCredits = numCredits;
    }

    // GETTERS

    public int getCourseNum() {
        return this.courseNum;
    }

    public String getCourseDept() {
        return this.courseDept;
    }

    public int getNumCredits() {
        return this.numCredits;
    }

    public String getCourseName(){
        return (this.isGraduateCourse ? "G" : "U") + this.courseDept + this.courseNum;
    }

    // METHODS

    public boolean isGraduateCourse(){
        return this.isGraduateCourse ? true : false;
    }

    public boolean equals(Object o){
//        equals(Object obj): boolean	//all attributes must match for 2 Course objects to be considered equal

        if(o instanceof Course){
            Course temp = (Course) o;
            if(temp.isGraduateCourse == this.isGraduateCourse &&
                    temp.courseNum == this.courseNum &&
                    temp.courseDept.equals(this.courseDept) &&
                    temp.numCredits == this.numCredits){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        String level = isGraduateCourse ? "Graduate" : "Undergraduate";
        return String.format("Course: %3s-%3d | Number of Credits: %02d | " + level, courseDept, courseNum, numCredits, isGraduateCourse);
    }

    public int compareTo(Course c){
        //use the Comparable interface specification. Sort by courseNum
        if(this.courseNum > c.courseNum){
            return 1;
        } else if(this.courseNum < c.courseNum){
            return -1;
        }
        return 0;
    }

}

/*
isGraduateCourse: boolean
courseNum: int
courseDept: String
numCredits: int

Course(boolean isGraduateCourse, int courseNum, String courseDept, int numCredits)
isGraduateCourse(): boolean
getCourseNum(): int
getCourseDept(): String
getNumCredits(): int
getCourseName: String 	//return String of “U” or “G” + courseDept + courseNum
equals(Object obj): boolean	//all attributes must match for 2 Course objects to be considered equal
toString(): String	//”Course: %3s-%3d | Number of Credits: %02d  | Graduate/Undergraduate”, courseDept, courseNum, numCredits, isGraduateCourse
!!!! compareTo(Course c): int      //use the Comparable interface specification. Sort by courseNum

 */