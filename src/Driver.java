import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class Driver {

    final FileManipulator fileManip;
    final String database = "src/SchoolDB_Updated.txt";
    Scanner keyb;
    String[] mainMenu = new String[] {
            "1) View database",
            "2) Create new object",
            "3) Add Course(s) to an object",
            "4) Get Course from an object",
            "5) Check if a faculty member teaches a course or if a student is in a course",
            "6) Show Most and Least courses taught by faculty member",
            "7) Show Maximum and Minimum courses in Course catalog",
            "8) Show Most and Least credits by student",
            "0) *exit program*"
    };

    String[] createObjectMenuOption = new String[] {
            "1) Student",
            "2) Faculty",
            "3) Course",
            "4) GeneralStaff",
            "5) *go back to main menu*"
    };

    String[] addToObjectMenuOption = new String[] {
            "1) Student",
            "2) Faculty",
            "3) *go back to main menu*"
    };

    public Driver() {
        keyb = new Scanner(System.in);
        fileManip = new FileManipulator();
        fileManip.readFileAndCreateArrays("src/SchoolDB_Initial.txt");
        fileManip.writeArraysToFile();
    }

    // database cli start
    public void start() {

        String input = "";
        boolean wantToContinue = true;

        System.out.println("Welcome to the interactive School Database!");
        while(wantToContinue) {
            System.out.println("What do you want to do?");
            displayMenuOptions(mainMenu);
            input = keyb.nextLine();

            switch(input) {
                case "1":
                    System.out.println(getDatabaseAsString());
                    break;
                case "2":
                    createObjectsMenu();
                    break;
                case "3":
                    addCoursesToObjectsMenu();
                    break;
                case "4":
                    getCourseFromObjectMenu();
                    break;
                case "5":
                    checkIfObjectsContainsCourseMenu();
                    break;
                case "6":
                    mostAndLeastCoursesTaught();
                    break;
                case "7":
                    minAndMaxCourse();
                    break;
                case "8":
                    mostAndLeastCredits();
                    break;
                case "0":
                    System.out.println("Bye!");
                    wantToContinue = false;
                    break;
                default:
                    System.out.println("Invalid input, try again\n");
                    break;
            }
        }
    }

    // only prints menu options
    public void displayMenuOptions(String[] options) {
        for(String o: options) {
            System.out.println("\t" + o);
        }
    }

    public void createObjectsMenu() {
        String input = "";
        int howMany;
        while(true) {
            System.out.println("Which object do you want to create?");
            displayMenuOptions(createObjectMenuOption);
            input = keyb.nextLine();

            if(Integer.valueOf(input) > 5 || Integer.valueOf(input) < 1){
                System.out.println("Invalid input, try again\n");
                continue;
            }

            if(input.equals("5")) {
                return;
            }

            System.out.println("How many? ");
            howMany = keyb.nextInt();
            keyb.nextLine();

            switch(input) {
                case "1":
                    Student[] s = createStudent(howMany);
                    appendToDatabase(s);
                    return;
                case "2":
                    Faculty[] f = createFaculty(howMany);
                    appendToDatabase(f);
                    return;
                case "3":
                    Course[] c = createCourse(howMany);
                    appendToDatabase(c);
                    return;
                case "4":
                    GeneralStaff[] g = createGeneralStaff(howMany);
                    appendToDatabase(g);
                    return;
                default:
                    System.out.println("Invalid input, try again\n");
                    break;
            }
        }
    }

    public void addCoursesToObjectsMenu() {
        String input = "";

        while(true) {
            System.out.println("Choose an object you want to add to: ");
            displayMenuOptions(addToObjectMenuOption);
            input = keyb.nextLine();

            switch(input) {
                case "1":
                    findStudentAndAddCourse();
                    fileManip.writeArraysToFile();
                    return;
                case "2":
                    findFacultyAndAddCourse();
                    fileManip.writeArraysToFile();
                    return;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input, try again\n");
                    break;
            }
        }
    }

    public void checkIfObjectsContainsCourseMenu() {
        String input = "";
        String response = "";

        while(true) {
            System.out.println("Choose an object you want to check: ");
            displayMenuOptions(addToObjectMenuOption);
            input = keyb.nextLine();

            switch(input) {
                case "1":
                    response = checkIfStudentTakenCourse();
                    System.out.println(response);
                    return;
                case "2":
                    response = checkIfFacultyTeachesCourse();
                    System.out.println(response);
                    return;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input, try again\n");
                    break;
            }
        }
    }

    public void getCourseFromObjectMenu() {
        String input = "";
        int index;
        Course course;

        while(true) {
            System.out.println("Choose an object you want to get a course from: ");
            displayMenuOptions(addToObjectMenuOption);
            input = keyb.nextLine();

            switch(input) {

                case "1":
                    System.out.print("Enter course index: ");
                    index = Integer.valueOf(keyb.nextLine());
                    course = getCourseFromStudent(index);
                    System.out.println(course);
                    System.out.println();
                    fileManip.writeArraysToFile();
                    return;
                case "2":
                    System.out.print("Enter course index: ");
                    index = Integer.valueOf(keyb.nextLine());
                    course = getCourseFromFaculty(index);
                    System.out.println(course);
                    System.out.println();
                    fileManip.writeArraysToFile();
                    return;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input, try again\n");
                    break;
            }
        }
    }

    public void mostAndLeastCredits() {
        ArrayList<Student> allStudent = new ArrayList<>();
        for(Student f: fileManip.students) {
            allStudent.add(f);
        }

        // METHOD 1: Sorting with collections
        Collections.sort(allStudent); // from least to greatest
        Student max =  allStudent.get(allStudent.size() - 1);
        Student min = allStudent.get(0);

        System.out.println("Most credits: ");
        System.out.println("\t" + max);
        System.out.println();
        System.out.println("Least credits: ");
        System.out.println("\t" + min);
        System.out.println();
    }

    public void minAndMaxCourse() {

        ArrayList<Course> allCourses = new ArrayList<>();
        for(Course f: fileManip.courses) {
            allCourses.add(f);
        }

        // METHOD 1: Sorting with collections
        Collections.sort(allCourses); // from least to greatest
        Course max =  allCourses.get(allCourses.size() - 1);
        Course min = allCourses.get(0);

        System.out.println("Max course: ");
        System.out.println("\t" + max);
        System.out.println();
        System.out.println("Min course: ");
        System.out.println("\t" + min);
        System.out.println();
    }

    public void mostAndLeastCoursesTaught() {

        ArrayList<Faculty> allFaculties = new ArrayList<>();
        for(Faculty f: fileManip.faculty) {
            allFaculties.add(f);
        }

        // METHOD 1: Sorting with collections
        Collections.sort(allFaculties); // from least to greatest
        Faculty most =  allFaculties.get(allFaculties.size() - 1);
        Faculty least = allFaculties.get(0);

        System.out.println("Most courses taught: ");
        System.out.println("\t" + most);
        System.out.println();
        System.out.println("Least courses taught: ");
        System.out.println("\t" + least);
        System.out.println();
    }

    public String checkIfStudentTakenCourse() {

        // Displaying list of faculties
        for(int i=0; i<fileManip.students.size(); i++) {
            Student student = fileManip.students.get(i);
            System.out.println("\t" + i + ") Name: " + student.getName() + ", EMP-ID: " + student.getStudentID());
        }

        // User chooses a student
        System.out.print("Enter student (index): ");
        int studentIndex = Integer.valueOf(keyb.nextLine());

        // Display courses
        for(int i=0; i<fileManip.courses.size(); i++) {
            System.out.println("\t"+ i + ") " + fileManip.courses.get(i));
        }
        System.out.print("Which course would you like to check (index): ");
        int courseIndex = Integer.valueOf(keyb.nextLine());

        // hold these in temp objects.
        Student selectedStudent = fileManip.students.get(studentIndex);
        Course selectedCrse = fileManip.courses.get(courseIndex);

        // Checking to see if chosen student taken the selected course
        // Loop through all courses taught by the selected student.
        for(int i=0; i < selectedStudent.getNumCoursesTaken(); i++) {
            Course taughtCrse = selectedStudent.getCourseTaken(i);

            // Compare if selected course is equal to a course the student teaches (is in their array of courses)
            if(taughtCrse.equals(selectedCrse)) {
                return "Yes! this course was taken by this student.";
            }
        }

        return "No, this student did not take that course.";
    }

    public String checkIfFacultyTeachesCourse() {

        // Displaying list of faculties
        for(int i=0; i<fileManip.faculty.size(); i++) {
            Faculty faculty = fileManip.faculty.get(i);
            System.out.println("\t" + i + ") Name: " + faculty.getName() + ", EMP-ID: " + faculty.getEmployeeID());
        }

        // User chooses a faculty
        System.out.print("Enter faculty (index): ");
        int facultyIndex = Integer.valueOf(keyb.nextLine());

        // Display courses
        for(int i=0; i<fileManip.courses.size(); i++) {
            System.out.println("\t"+ i + ") " + fileManip.courses.get(i));
        }
        System.out.print("Which course would you like to check (index): ");
        int courseIndex = Integer.valueOf(keyb.nextLine());

        // hold these in temp objects.
        Faculty selectedFaculty = fileManip.faculty.get(facultyIndex);
        Course selectedCrse = fileManip.courses.get(courseIndex);

        // Checking to see if chosen faculty teaches the selected course
        // Loop through all courses taught by the selected faculty.
        for(int i=0; i < selectedFaculty.getNumCoursesTaught(); i++) {
            Course taughtCrse = selectedFaculty.getCourseTaught(i);

            // Compare if selected course is equal to a course the faculty teaches (is in their array of courses)
            if(taughtCrse.equals(selectedCrse)) {
                return "Yes! this course is taught by this faculty.";
            }
        }

        return "No, this faculty does not teach that course.";
    }

    public Course getCourseFromFaculty(int index){
        System.out.println("Enter Employee Number: ");
        int id = Integer.valueOf(keyb.nextLine());

        Faculty emp = null;
        for(Faculty employee : fileManip.faculty){
            if(employee.getEmployeeID() == id) {
                emp = employee;
            }
        }

        return emp.getCourseTaught(index);
    }

    public Course getCourseFromStudent(int index) {
        System.out.println("Enter Student's ID: ");
        int id = Integer.valueOf(keyb.nextLine());

        Student stu = null;
        for(Student student: fileManip.students) {
            if(student.getStudentID() == id) {
                stu = student;
            }
        }

        return stu.getCourseTaken(index);
    }

    public void findFacultyAndAddCourse(){
        while(true) {
            System.out.println("Enter Employee ID: ");
            int id = Integer.valueOf(keyb.nextLine());

            Faculty emp = null;
            for(Faculty faculty: fileManip.faculty) {
                if(faculty.getEmployeeID() == id) {
                    emp = faculty;
                }
            }


            if(emp != null) {
                // how many?
                System.out.print("How many courses do you want to add? ");
                int numCourses = Integer.valueOf(keyb.nextLine());

                System.out.println("Which course do you want to add to the course list? ");
                for(int i=0; i<fileManip.courses.size(); i++) {
                    System.out.println( i + ") " + fileManip.courses.get(i));
                }
                System.out.println("\n");
                // To hold the courses the user is going to select.
                Course[] selectCourses = new Course[numCourses];

                // Get course by index and hold in selectedCourses.
                for(int i=0; i < numCourses; ++i) {
                    System.out.print((i+1) +") Enter course index: ");
                    int selectIndex = Integer.valueOf(keyb.nextLine());
                    selectCourses[i] = fileManip.courses.get(selectIndex);
                }

                emp.addCoursesTaught(selectCourses);
                System.out.println("Course(s) at index was added!");
                return;
            }
        }
    }

    public void findStudentAndAddCourse() {
        while(true) {
            System.out.println("Enter Student's ID: ");
            int id = Integer.valueOf(keyb.nextLine());

            Student stu = null;
            for(Student student: fileManip.students) {
                if(student.getStudentID() == id) {
                    stu = student;
                }
            }

            if(stu != null) {
                System.out.print("How many courses do you want to add? ");
                int numCourses = Integer.valueOf(keyb.nextLine());

                // Display courses in the database
                System.out.println("Which course do you want to add to the course list (Choose index)? ");
                for(int i=0; i<fileManip.courses.size(); i++) {
                    System.out.println( i + ") " + fileManip.courses.get(i));
                }
                System.out.println("\n");

                // To hold the courses the user is going to select.
                Course[] selectCourses = new Course[numCourses];

                // Get course by index and hold in selectedCourses.
                for(int i=0; i < numCourses; ++i) {
                    System.out.print((i+1) +") Enter course index: ");
                    int selectIndex = Integer.valueOf(keyb.nextLine());
                    selectCourses[i] = fileManip.courses.get(selectIndex);
                }

                stu.addCoursesTaken(selectCourses);
                System.out.println("Course(s) at index was added!");
                return;
            }
        }
    }
    public void appendToDatabase( Object[] people ) {

        // Add object to correct ArrayList.
        if(people instanceof Student[]) {
            Student[] students = (Student[]) people;
            for(Student s: students)
                fileManip.students.add(s);

        } else if(people instanceof Faculty[]) {
            Faculty[] faculties = (Faculty[]) people;
            for(Faculty f: faculties)
                fileManip.faculty.add(f);

        } else if(people instanceof Course[]) {
            Course[] courses = (Course[]) people;
            for(Course c: courses)
                fileManip.courses.add(c);

        } else if(people instanceof GeneralStaff[]) {
            GeneralStaff[] genStaffs = (GeneralStaff[]) people;
            for(GeneralStaff g: genStaffs)
                fileManip.genStaff.add(g);

        }

        fileManip.writeArraysToFile();
    }

    public Student[] createStudent(int count) {
        Student[] students = new Student[count];
        for(int i=0; i<count; ++i) {
            System.out.print("Enter student name: ");
            String name = keyb.nextLine();

            System.out.print("Enter student major: ");
            String major = keyb.nextLine();

            System.out.print("Is student a graduate (Y/N): ");
            boolean isGrad = convertYesOrNoToBoolean(keyb.nextLine());

            System.out.print("Enter student birthYear: ");
            int bYear = Integer.valueOf(keyb.nextLine());
            System.out.println();

            students[i] = new Student(name, bYear, major, isGrad);
        }
        return students;
    }

    public Faculty[] createFaculty(int count) {
        Faculty[] faculty = new Faculty[count];
        for(int i=0; i<count; ++i) {
            System.out.print("Enter faculty name: ");
            String name = keyb.nextLine();

            System.out.print("Enter faculty deptName: ");
            String dept = keyb.nextLine();

            System.out.print("Is faculty tenured (Y/N): ");
            boolean isTenure = convertYesOrNoToBoolean(keyb.nextLine());

            System.out.print("Enter faculty birthYear: ");
            int bYear = Integer.valueOf(keyb.nextLine());
            System.out.println();

            faculty[i] = new Faculty(name, bYear, dept, isTenure);
        }
        return faculty;
    }

    public Course[] createCourse(int count) {
        Course[] courses = new Course[count];
        for(int i=0; i<count; ++i) {
            System.out.print("Enter course credit: ");
            int numCredits = Integer.valueOf(keyb.nextLine());

            System.out.print("Enter course dept name: ");
            String dept = keyb.nextLine();

            System.out.print("Is this a graduate course? (Y/N): ");
            boolean isGradCrse = convertYesOrNoToBoolean(keyb.nextLine());

            System.out.print("Enter course number: ");
            int crseNum = Integer.valueOf(keyb.nextLine());
            System.out.println();

            courses[i] = new Course(isGradCrse, crseNum, dept, numCredits);
        }
        return courses;
    }

    public GeneralStaff[] createGeneralStaff(int count) {
        GeneralStaff[] staff = new GeneralStaff[count];
        for(int i=0; i<count; ++i) {
            System.out.print("Enter general staff name: ");
            String name = keyb.nextLine();

            System.out.print("Enter general staff deptName: ");
            String dept = keyb.nextLine();

            System.out.print("Enter general staff duty: ");
            String duty = keyb.nextLine();

            System.out.print("Enter general staff birthYear: ");
            int bYear = Integer.valueOf(keyb.nextLine());
            System.out.println();

            staff[i] = new GeneralStaff(name, bYear, dept, duty);
        }
        return staff;
    }

    public String getDatabaseAsString() {
        // first, write the contents of the arraylists to the database
        fileManip.writeArraysToFile();
        String databaseContent = "\n\t\t\t---Database!---\n";
        try {
            File file = new File(database);
            Scanner inFS = new Scanner( file );
            while(inFS.hasNext()) {
                databaseContent += inFS.nextLine() + "\n";
            }
            inFS.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return databaseContent;
    }

    public boolean convertYesOrNoToBoolean(String resp) {
        if( resp.toLowerCase().startsWith("y") ) {
            return true;
        } else {
            return false;
        }
    }
    // --------------------------------------
    public static void main(String[] args)  {
        Driver d = new Driver();
        d.start();
    }
}