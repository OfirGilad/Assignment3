package bgu.spl.net.srv;

import java.util.TreeMap;

public class Course {
    private final int courseNum;
    private final String courseName;
    private int[] KdamCoursesList;
    private final int numOfMaxStudent;
    private int currentNumberOfStudents;
    private final TreeMap<String, Student> registeredStudents;
    private final int courseId;
    private boolean isSorted;

    public Course (int courseNum, String courseName, int[] KdamCoursesList, int numOfMaxStudent, int courseId) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.KdamCoursesList = KdamCoursesList;
        this.numOfMaxStudent = numOfMaxStudent;
        registeredStudents = new TreeMap<>();
        currentNumberOfStudents = 0;
        this.courseId = courseId;
        isSorted = false;
    }

    public int getCourseId() {
        return courseId;
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

    public synchronized void registerStudent(Student student) {
        if (!isFull() && isEligible(student.getCourses())) {
            currentNumberOfStudents ++;
            registeredStudents.put(student.getUsername(), student);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void unregisterStudent(Student student) {
        if (registeredStudents.containsValue(student)) {
            currentNumberOfStudents --;
            registeredStudents.remove(student.getUsername(), student);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setSortedKdamCoursesList (int[] KdamCoursesList) {
        this.KdamCoursesList = KdamCoursesList;
        isSorted = true;
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

    public synchronized int getCurrentNumberOfStudents() {
        return currentNumberOfStudents;
    }

    public int getNumOfMaxStudent() {
        return numOfMaxStudent;
    }

    public synchronized TreeMap<String, Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean getIsSorted() {
        return isSorted;
    }
}
