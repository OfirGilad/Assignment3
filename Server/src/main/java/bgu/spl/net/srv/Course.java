package bgu.spl.net.srv;

import java.util.HashMap;

public class Course {
    int courseNum;
    String courseName;
    int[] KdamCoursesList;
    int numOfMaxStudent;
    int currentNumberOfStudents = 0;
    HashMap<String,Student> registeredStudents;

    public Course (int courseNum, String courseName, int[] KdamCoursesList, int numOfMaxStudent) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.KdamCoursesList = KdamCoursesList;
        this.numOfMaxStudent = numOfMaxStudent;
        registeredStudents = new HashMap<>();
    }

    public boolean registerStudent(Student student) {
        if (isEligible(student.getCourses())) {
            currentNumberOfStudents ++;
            registeredStudents.put(student.getUsername(), student);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean unregisterStudent(Student student) {
        if (registeredStudents.containsValue(student)) {
            currentNumberOfStudents --;
            registeredStudents.remove(student.getUsername(), student);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isEligible(HashMap<Integer, Course> courses) {
        if (currentNumberOfStudents < numOfMaxStudent) {
            for (int courseNum : KdamCoursesList) {
                if (courses.get(courseNum) == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int getCourseNum() {
        return courseNum;
    }
}
