package bgu.spl.net.srv;

import java.util.HashMap;
import java.util.TreeMap;

public class Course {
    private final int courseNum;
    private final String courseName;
    private final int[] KdamCoursesList;
    private final int numOfMaxStudent;
    private int currentNumberOfStudents;
    TreeMap<String, Student> registeredStudents;

    public Course (int courseNum, String courseName, int[] KdamCoursesList, int numOfMaxStudent) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.KdamCoursesList = KdamCoursesList;
        this.numOfMaxStudent = numOfMaxStudent;
        registeredStudents = new TreeMap<>();
        currentNumberOfStudents = 0;
    }

    public boolean isFull() {
        return currentNumberOfStudents < numOfMaxStudent;
    }

    public boolean isEligible(TreeMap<Integer, Course> courses) {
        for (int courseNum : KdamCoursesList) {
            if (courses.get(courseNum) == null) {
                return false;
            }
        }
        return true;
    }

    public void registerStudent(Student student) {
        if (!isFull() && isEligible(student.getCourses())) {
            currentNumberOfStudents ++;
            registeredStudents.put(student.getUsername(), student);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void unregisterStudent(Student student) {
        if (registeredStudents.containsValue(student)) {
            currentNumberOfStudents --;
            registeredStudents.remove(student.getUsername(), student);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public int[] getKdamCoursesList() {
        return KdamCoursesList;
    }

    public int getCurrentNumberOfStudents() {
        return currentNumberOfStudents;
    }

    public int getNumOfMaxStudent() {
        return numOfMaxStudent;
    }

    public TreeMap<String, Student> getRegisteredStudents() {
        return registeredStudents;
    }
}
