package bgu.spl.net.srv;

import java.util.TreeMap;

public abstract class UserType {

    protected final String username;
    protected final String password;

    public UserType(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract String getType();
}

class Student extends UserType {
    private final TreeMap<Integer, Course> courses;

    public Student(String username, String password) {
        super(username, password);
        courses = new TreeMap<>();
    }

    public String getType() {
        return "Student";
    }

    //All Student class methods are synchronized since every change in the student's courses HashMap effect all this functions output
    //Example: when Student requests: COURSEREG, and Admin requests: this STUDENTSTAT
    public synchronized TreeMap<Integer, Course> getCourses() {
        return courses;
    }

    public synchronized boolean registerToCourse(Course course) {
        if (!courses.containsValue(course)) {
            if (!course.isFull()) {
                if (course.isEligible(courses)) {
                    course.registerStudent(this);
                    courses.put(course.getCourseId(), course);
                    //"User registered successfully"
                    return true;
                }
                else {
                    //"User isn't meet the Kdam curses requirement"
                    return false;
                }
            }
            else {
                //"Course is full"
                return false;
            }
        }
        else {
            //"User is already registered"
            return false;
        }
    }

    public synchronized boolean unregisterToCourse(Course course) {
        if (courses.containsValue(course)) {
            course.unregisterStudent(this);
            courses.remove(course.getCourseNum(), course);
            //"User unregistered successfully"
            return true;
        }
        else {
            //"User is already unregistered"
            return false;
        }
    }

    public synchronized boolean isRegisteredToCourse(int courseNumber) {
        return courses.containsKey(courseNumber);
    }
}

class Admin extends UserType {

    public Admin(String username, String password) {
        super(username, password);
    }

    public String getType() {
        return "Admin";
    }
}
