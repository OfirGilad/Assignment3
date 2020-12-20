package bgu.spl.net.srv;

import java.util.HashMap;

public class Course {
    int courseNum;
    String courseName;
    int[] KdamCoursesList;
    int numOfMaxStudent;
    int currentNumberOfStudents = 0;

    public Course (int courseNum, String courseName, int[] KdamCoursesList, int numOfMaxStudent) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.KdamCoursesList = KdamCoursesList;
        this.numOfMaxStudent = numOfMaxStudent;
    }

    public boolean registerStudent(HashMap<Integer, Course> courses) {
        if (isEligible(courses)) {
            currentNumberOfStudents ++;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean unregisterStudent(HashMap<Integer, Course> courses) {
        if (courses.containsKey(courseNum)) {
            currentNumberOfStudents --;
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
