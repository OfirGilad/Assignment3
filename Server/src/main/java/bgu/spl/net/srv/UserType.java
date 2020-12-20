package bgu.spl.net.srv;

import java.util.HashMap;

public abstract class UserType {

    protected final String username;
    protected final String password;


    public UserType (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}

class Student extends UserType {
    private final HashMap<Integer, Course> courses;

    public Student (String username, String password) {
        super(username, password);
        courses = new HashMap<>();
    }

    public HashMap<Integer, Course> getCourses() {
        return courses;
    }

    public boolean addCourse (Course course) {
        //Kdam check
        if ((!courses.containsValue(course)) && (course.registerStudent(this))) {
            courses.put(course.getCourseNum(), course);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeCourse (Integer courseNumber, Course course) {
        if (course.unregisterStudent(this)) {
            courses.remove(courseNumber, course);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isRegisteredToCourse (Course course) {
        return courses.containsValue(course);
    }
}

class Admin extends UserType {

    public Admin (String username, String password) {
        super(username, password);
    }
}
