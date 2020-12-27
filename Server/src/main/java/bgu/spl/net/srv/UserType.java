package bgu.spl.net.srv;

import java.util.HashMap;

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
}

class Student extends UserType {
    private final HashMap<Integer, Course> courses;
    private int numberOfCoursesRegisteredTo;

    public Student(String username, String password) {
        super(username, password);
        courses = new HashMap<>();
        numberOfCoursesRegisteredTo = 0;
    }

    //All Student class methods are synchronized since every change in the student's courses HashMap effect all this functions output
    //Example: when Student requests: COURSEREG, and Admin requests: this STUDENTSTAT
    public synchronized HashMap<Integer, Course> getCourses() {
        return courses;
    }

    public synchronized int getNumberOfCoursesRegisteredTo() {
        return numberOfCoursesRegisteredTo;
    }

    public synchronized boolean registerToCourse(Course course) {
        if (!courses.containsValue(course)) {
            if (!course.isFull()) {
                if (!course.isEligible(courses)) {
                    course.registerStudent(this);
                    courses.put(course.getCourseNum(), course);
                    //"User registered successfully"
                    numberOfCoursesRegisteredTo++;
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
            numberOfCoursesRegisteredTo--;
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
}
