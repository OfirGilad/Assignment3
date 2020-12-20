package bgu.spl.net.srv;


import java.util.HashMap;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {

	private final HashMap<String,UserType> users;
	private HashMap<Integer, Course> courses;

	private static class SingletonHolder {
		private static final Database getInstance = new Database();
	}

	//to prevent user from creating new Database
	private Database() {
		// TODO: implement
		users = new HashMap<>();
		courses = new HashMap<>();
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

	public boolean addUser(String username, String password, UserType user) {
		if (users.get(username) != null) {
			return false;
		}
		else {
			if (user.getClass() == Admin.class)
				users.put(username,new Admin(username, password));
			else {
				users.put(username,new Student(username, password));
			}
			return true;
		}
	}

}