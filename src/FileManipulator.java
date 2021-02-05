import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
class FileManipulator {

    //    static ArrayList<Object> database = new ArrayList<>();
    ArrayList<GeneralStaff> genStaff = new ArrayList<>();
    ArrayList<Faculty> faculty = new ArrayList<>();
    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();

    public void processLine(String line) {
        // FINAL INT OBJECTINDEX = 0 // MEANS OBJECT NAME
        String[] objAndFields = line.split(":");
        String objName = objAndFields[0];
        String[] fields = new String[0];

        if( objAndFields.length > 1) {
            fields = objAndFields[1].split(",");
            for(int i=0; i<fields.length; ++i) {
                fields[i] = fields[i].trim();
            }
        }


        Object o = null;
        if(objName.equals("Faculty")) {
//            o = createFaculty(fields);
            faculty.add(createFaculty(fields));


        } else if (objName.equals("Course")) {
//            o = createCourse(fields);
            courses.add(createCourse(fields));
        } else if (objName.equals("Student")) {
//            o = createStudent(fields);
            students.add(createStudent(fields));

        } else if (objName.equals("GeneralStaff")) {
//            o = createGenStaff(fields);
            genStaff.add(createGenStaff(fields));
        }

        return;
    }

    public Course createCourse(String... fields) {
        boolean grad = Boolean.valueOf(fields[0]);
        int crseNum = Integer.valueOf(fields[1]);
        String name = fields[2];
        int credits = Integer.valueOf(fields[3]);
        return new Course(grad, crseNum, name, credits);
    }

    public GeneralStaff createGenStaff(String[] fields) {
        if(fields.length == 0) {
            return new GeneralStaff();
        } else if (fields.length == 1) {
            String duty = fields[0];
            return new GeneralStaff(duty);
        } else if(fields.length == 2) {
            String dept = fields[0];
            String duty = fields[1];
            return new GeneralStaff(dept, duty);
        } else {
            String name = fields[0];
            int birthYear = Integer.valueOf(fields[1]);
            String dept = fields[2];
            String duty = fields[3];
            return new GeneralStaff(name, birthYear, dept, duty);
        }
    }

    public Student createStudent(String[] fields) {
        if(fields.length == 0) {
            return new Student();
        } else if (fields.length == 1) {
            boolean grad = Boolean.valueOf(fields[0]);
            return new Student(grad);
        } else if(fields.length == 2) {
            String major = fields[0];
            boolean grad = Boolean.valueOf(fields[1]);
            return new Student(major, grad);
        } else {
            String name = fields[0];
            int birthYear = Integer.valueOf(fields[1]);
            String major = fields[2];
            boolean grad = Boolean.valueOf(fields[3]);
            return new Student(name, birthYear, major, grad);
        }
    }

    public Faculty createFaculty(String[] fields) {
        if(fields.length == 0) {
            return new Faculty();
        } else if (fields.length == 1) {
            boolean isTenured = Boolean.valueOf(fields[0]);
            return new Faculty(isTenured);
        } else if(fields.length == 2) {
            String dept = fields[0];
            boolean isTenured = Boolean.valueOf(fields[1]);
            return new Faculty(dept, isTenured);
        } else {
            String name = fields[0];
            int birthYear = Integer.valueOf(fields[1]);
            String dept = fields[2];
            boolean isTenured = Boolean.valueOf(fields[3]);
            return new Faculty(name, birthYear, dept, isTenured);
        }
    }

    public void readFileAndCreateArrays(String fileName) {
        Scanner inStream = null;
        try{
            File file = new File( fileName );
            if(file.exists() && file.canRead()){
                inStream = new Scanner (file );
                while(inStream.hasNextLine()){
                    String dbEntry = inStream.nextLine();
//                    Object processedDbEntry = processLine(dbEntry);
                    processLine(dbEntry);

//                    database.add(processedDbEntry == null? "": processedDbEntry);
                    // courses -> persons -> emps -> gen staff -> faculty -> students
                }
            }
        }catch(FileNotFoundException e){
            System.err.println("Cannot read from file "+fileName);
            e.printStackTrace();
        }
        finally{
            if(inStream !=null){
                inStream.close();
            }
        }
    }

    public void readFileDisplayOnConsole(String fileName) {
        Scanner inStream = null;
        try{
            File file = new File( fileName );
            if(file.exists() && file.canRead()){
                inStream = new Scanner (file );
//				int lineNum=0;
                while(inStream.hasNextLine()){
                    String dbEntry = inStream.nextLine();
                    System.out.println(dbEntry);
//					System.out.println(" line num "+(++lineNum) + ": "+ theLine);
                }
            }
        }catch(FileNotFoundException e){
            System.err.println("Cannot read from file "+fileName);
            e.printStackTrace();
        }
        finally{
            if(inStream !=null){
                inStream.close();
            }
        }
    }

    public void writeToNewFile(String fileName, String text) {
        PrintWriter outStream = null;
        try {

            outStream = new PrintWriter(fileName);//connect to the file on the system
            outStream.println(text); //write to the file
//			outStream.flush();//to be sure content is written to destination we flush

        } catch (FileNotFoundException e) {
            System.out.println("Could not write to file " + fileName);
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                outStream.close();//close the connection to the file
            }
        }
    }

    public void appendToFile(String fileName, String text) {
        PrintWriter outStream = null;
        try {

//			outStream = new PrintWriter(fileName);//connect to the file on the system
            FileOutputStream fOutStrm = new FileOutputStream(fileName, true);
            outStream = new PrintWriter(fOutStrm);
            outStream.println(text); //write to the file
            outStream.flush();//to be sure content is written to destination we flush

        } catch (FileNotFoundException e) {
            System.out.println("Could not write to file "+ fileName);
            e.printStackTrace();
        }
        finally{
            if(outStream != null){
                outStream.close();//close the connection to the file
            }
        }
    }

    public void createNewFile(String fileName) {
        PrintWriter outStream = null;
        try {
            outStream = new PrintWriter(fileName);//connect to the file on the system

        } catch (FileNotFoundException e) {
            System.out.println("Could not create file "+ fileName);
            e.printStackTrace();
        }
        finally{
            if(outStream != null){
                outStream.close();//close the connection to the file
            }
        }
    }

    public boolean deleteFile(String fileName) {
        File theFile = new File(fileName);
        boolean successStatus = false;
        if(theFile.exists()){
            if(theFile.delete()){
//                System.out.println("YAY! the file "+ fileName + " was deleted ");
                successStatus = true;
            }
            else{
                System.out.println("FAILURE! "+ fileName + " could not be deleted");
                successStatus = false;
            }
        }
        else{
            System.out.println("FAILURE! "+ fileName + " could not be found");
            successStatus = false;
        }
        return successStatus;
    }

    public void writeArraysToFile() {
        String filename = "src/SchoolDB_Updated.txt";
        deleteFile(filename);
        createNewFile(filename);

        String coursesOutput = "";
        String genStaffOutput = "";
        String facultyOutput = "";
        String studentsOutput = "";

        for(int i=0; i < courses.size(); i++){
            if(i == courses.size() - 1){
                coursesOutput += courses.get(i);
                break;
            }
            coursesOutput += courses.get(i) + "\n";
        }

        for(int i=0; i < genStaff.size(); i++){
            if(i == genStaff.size() - 1){
                genStaffOutput += genStaff.get(i);
                break;
            }
            genStaffOutput += genStaff.get(i) + "\n";
        }

        for(int i=0; i < faculty.size(); i++){
            if(i == faculty.size() - 1){
                facultyOutput += faculty.get(i);
                break;
            }
            facultyOutput += faculty.get(i) + "\n";
        }

        for(int i=0; i < students.size(); i++){
            if(i == students.size() - 1){
                studentsOutput += students.get(i);
                break;
            }
            studentsOutput += students.get(i) + "\n";
        }

        appendToFile(filename, "**************************************************************\n" +
                "SCHOOL DATABASE INFO:\n");
        appendToFile(filename, "************************************************");
        appendToFile(filename, "COURSES:");
        appendToFile(filename, coursesOutput);
        appendToFile(filename, "************************************************");
        appendToFile(filename, "************************************************");
        appendToFile(filename, "PERSONS:");
        appendToFile(filename, "************************************************");
        appendToFile(filename, "************************************************");
        appendToFile(filename, "EMPLOYEES:");
        appendToFile(filename, "************************************************");
        appendToFile(filename, "************************************************");
        appendToFile(filename, "GENERAL STAFF:");
        appendToFile(filename, genStaffOutput);
        appendToFile(filename, "************************************************");
        appendToFile(filename, "************************************************");
        appendToFile(filename, "FACULTY:");
        appendToFile(filename, facultyOutput);
        appendToFile(filename, "************************************************");
        appendToFile(filename, "************************************************");
        appendToFile(filename, "STUDENTS:");
        appendToFile(filename, studentsOutput);
        appendToFile(filename, "************************************************");
        appendToFile(filename, "**************************************************************\n");
    }
}
