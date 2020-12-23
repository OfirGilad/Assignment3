package bgu.spl.net.srv;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {

	private final ConcurrentHashMap<String, UserType> users;
	private ConcurrentHashMap<Integer, Course> courses;

	private static class SingletonHolder {
		private static final Database getInstance = new Database();
	}

	//to prevent user from creating new Database
	private Database() {
		// TODO: implement
		users = new ConcurrentHashMap<>();
		courses = new ConcurrentHashMap<>();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Database getInstance() {
		return SingletonHolder.getInstance;
	}
	
	/**
	 * loads the courses from the file path specified
	 * into the Database, returns true if successful.
	 */
	boolean initialize(String coursesFilePath) {
		// TODO: implement
		courses = null;
		return false;
	}

	//Used for: ADMINREG, STUDENTREG
	public String registerUser(String username, String password, UserType user) {
		if (users.containsKey(username)) {
			return "User is already registered";
		}
		else {
			if (user.getClass() == Admin.class)
				users.put(username,new Admin(username, password));
			else {
				users.put(username,new Student(username, password));
			}
			return "User registered successfully";
		}
	}

	//Used for: LOGIN
	public String checkLoginInfo(String username, String password) {
		if (!users.containsKey(username)) {
			return "User is not registered";
		}
		else {
			if (users.get(username).getPassword().equals(password)) {
				return "User info is valid";
			}
			else {
				return "User password is incorrect";
			}
		}
	}

	//Used for: COURSEREG
	public String registerToCourse(String username, int courseNumber) {
		UserType user = users.get(username);
		if (user.getClass() != Admin.class) {
			if (courses.containsKey(courseNumber)) {
				Course course = courses.get(courseNumber);
				return ((Student)user).registerToCourse(course);
			}
			else {
				return "Course number is not valid";
			}
		}
		else {
			return "User is Admin";
		}
	}

	//Used for: KDAMCHECK
	public int[] KdamCheck(int courseNumber) {
		if (courses.containsKey(courseNumber)) {
			Course course = courses.get(courseNumber);
			return course.getKdamCoursesList();
		}
		else  {
			throw new IllegalArgumentException();
		}
	}

	//Used for: COURSESTAT
	public String courseStatsSeatsAvailable(int courseNumber) {
		if (courses.containsKey(courseNumber)) {
			Course course = courses.get(courseNumber);
			return course.getCurrentNumberOfStudents() + "/" + course.getNumOfMaxStudent();
		}
		else  {
			throw new IllegalArgumentException();
		}
	}

	//Used for: COURSESTAT
	public String courseStatsStudentRegistered(int courseNumber) {
		if (courses.containsKey(courseNumber)) {
			Course course = courses.get(courseNumber);
			Set courseSet = course.getRegisteredStudents().entrySet();
			Iterator courseIterator = courseSet.iterator();
			StringBuilder studentRegistered = new StringBuilder("[");
			while (courseIterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry)courseIterator.next();
				studentRegistered.append(mapEntry.getKey());
				if (courseIterator.hasNext()) {
					studentRegistered.append(", ");
				}
			}
			studentRegistered.append("]");
			return studentRegistered.toString();
		}
		else  {
			throw new IllegalArgumentException();
		}
	}

	//used for: STUDENTSTAT, MYCOURSES
	public String studentStats(String username) {
		if (users.containsKey(username)) {
			UserType user = users.get(username);
			if (user.getClass() != Admin.class) {
				Set coursesSet = ((Student) user).getCourses().entrySet();
				Iterator coursesIterator = coursesSet.iterator();
				StringBuilder coursesRegistered = new StringBuilder("[");
				while (coursesIterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) coursesIterator.next();
					coursesRegistered.append(mapEntry.getKey());
					if (coursesIterator.hasNext()) {
						coursesRegistered.append(", ");
					}
				}
				coursesRegistered.append("]");
				return coursesRegistered.toString();
			} else {
				return "User is Admin";
			}
		}
		else  {
			return "User does not exist";
		}
	}

	//used for: ISREGISTERED
	public String isRegistered(String username, int courseNumber) {
		UserType user = users.get(username);
		if (user.getClass() != Admin.class) {
			if (((Student)user).isRegisteredToCourse(courseNumber)) {
				return "Student is registered";
			}
			else {
				return "Student is not registered";
			}
		}
		else {
			return "User is Admin";
		}
	}

	//used for: UNREGISTER
	public String unregisterToCourse(String username, int courseNumber) {
		UserType user = users.get(username);
		if (user.getClass() != Admin.class) {
			if (courses.containsKey(courseNumber)) {
				Course course = courses.get(courseNumber);
				return ((Student) user).unregisterToCourse(course);
			}
			else  {
				return "Course number is not valid";
			}
		}
		else {
			return "User is Admin";
		}
	}

}