package by.gsu.epamlab.factories;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.ifaces.DatabaseUserDao;
import by.gsu.epamlab.ifaces.IUserDao;
import by.gsu.epamlab.ifaces.RamUserDao;
import by.gsu.epamlab.impl.DaoException;

public class UserFactory {
	private static final String DB_TYPE = "db";
	private static final String RAM_TYPE = "ram";
	private static final String ERROR_CONNECTION = "connection error";
	
	public static IUserDao getUserDao(String type) throws DaoException{
		if(type.equals(DB_TYPE)) {
			return new DatabaseUserDao();
		} else if(type.equals(RAM_TYPE)){
			return new RamUserDao();
		} else {
			throw new DaoException(ERROR_CONNECTION);
		}
	}
		public static User getUserFromFactory(String login, String email) {
			User user = new User(login, email);
			return user;
		}
	}