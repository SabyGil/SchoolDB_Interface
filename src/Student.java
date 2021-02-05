public class Student extends Person implements Comparable<Person>{
    private static int numStudents;
    private int studentID;		 //generated
    private Course[ ] coursesTaken;
    private int numCoursesTaken; 	//controlled variable
    private boolean isGraduate;
    private String major;

    public Student(){
        super();
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        numStudents++;
        this.studentID = numStudents;
        this.isGraduate = false;
        this.major = "undeclared";
    }
    public Student(boolean isGraduate){
        super();
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        numStudents++;
        this.studentID = numStudents;
        this.isGraduate = isGraduate;
        this.major = "undeclared";
    }
    public Student(String major, boolean isGraduate){
        super();
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        numStudents++;
        this.studentID = numStudents;
        this.isGraduate = isGraduate;
        this.major = major;
    }
    public Student(String name, int birthYear, String major, boolean isGraduate){
        super(name, birthYear);
        this.coursesTaken = new Course[50];
        this.numCoursesTaken = 0;
        numStudents++;
        this.studentID = numStudents;
        this.isGraduate = isGraduate;
        this.major = major;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public int getNumCoursesTaken() {
        return numCoursesTaken;
    }

    public static int getNumStudents() {
        return numStudents;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setIsGraduate(boolean isGraduate){
        this.isGraduate = isGraduate;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    // METHODS
    public void addCourseTaken(Course course){
        for(int i = 0; i < coursesTaken.length; i++){
            if(coursesTaken[i] == null){
                coursesTaken[i] = course;
                numCoursesTaken++;
                break;
            }
        }
    }

    public void addCoursesTaken(Course [] course){
        //appends courses to the end of the existing array
        for(int i = 0; i < course.length; i++){
            addCourseTaken(course[i]);
        }
    }

    public Course getCourseTaken(int index){
        // note: index must be verified. Return “null” if invalid
        if(index >= coursesTaken.length || index < 0 || coursesTaken[index] == null){
            return null;
        }
        return coursesTaken[index];
    }

    public String getCourseTakenAsString(int index){
        if(getCourseTaken(index) == null){
            return "";
        }

        String courseDept = coursesTaken[index].getCourseDept();
        int courseNum = coursesTaken[index].getCourseNum();

        return courseDept + "-" + courseNum;
    }

    public String getAllCoursesTakenAsString(){
        String output = "";

        for(int i=0; i<numCoursesTaken; ++i) {
            String course = getCourseTakenAsString(i);
            output += course ;

            if(i != this.numCoursesTaken - 1){
                output += ",";
            }
        }

        return output;

    }

    public boolean equals(Object o){
        if(o instanceof Student){
            Student temp = (Student) o;
            if(super.equals(temp)){
                if(temp.numCoursesTaken == this.numCoursesTaken && temp.isGraduate == this.isGraduate && temp.major.equals(this.major)){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        String grad = isGraduate == true ? "Graduate" : "Undergraduate";
//       return String.format("%s Student: studentID: %04d | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s", super.toString(), studentID, major, grad, numCoursesTaken, getAllCoursesTakenAsString());
        return String.format("%s Student: studentID: %04d | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s", super.toString(), studentID, major, grad, numCoursesTaken, getAllCoursesTakenAsString());

    }


    public int compareTo(Student s){
        //use the Comparable interface specification, sort by numCredits
        int sumOfCreditsOne = 0;
        int sumOfCreditsTwo = 0;


        for(int i = 0; i < s.numCoursesTaken; i++){
            sumOfCreditsOne += s.coursesTaken[i].getNumCredits();
        }

        for(int i = 0; i < this.numCoursesTaken; i++){
            sumOfCreditsTwo += this.coursesTaken[i].getNumCredits();
        }

        if(sumOfCreditsTwo > sumOfCreditsOne ){
            return 1;
        } else if(sumOfCreditsTwo < sumOfCreditsOne){
            return -1;
        }

        return 0;
    }

}
