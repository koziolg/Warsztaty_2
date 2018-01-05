package programs.administrative;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import entities.User;
import utils.Connector;

public class UsersAdministrative {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		Connection conn = Connector.getConnection();
		User[] users = User.loadAllUsers(conn);
		String firstBoard = "Wybierz jedną z opcji: \n" + "\"add\" - dodanie użytkownika, \n"
				+ "\"edit\" - edycja użytkownika, \n" + "\"delete\" - usunięcie użytkownika, \n"
				+ "\"quit\" - wyjście z programu. ";
		boolean quit = true;
		System.out.println(Arrays.toString(users));
		while (quit) {
			System.out.println(firstBoard);
			String choice = scan.next();

			if (choice.equals("add")) {
				User user = new User();
				System.out.println("Podaj nazwe użytkownika: ");
				String username = scan.next();
				user.setUsername(username);
				System.out.println("Podaj email: ");
				String email = scan.next();
				user.setEmail(email);
				System.out.println("Podaj hasło: ");
				String password = scan.next();
				user.setPassword(password);
				System.out.println("Podaj id grupy: ");
				int user_group_id = scan.nextInt();
				user.setUser_group_id(user_group_id);
				user.saveToDB(conn);				
			} else if (choice.equals("edit")) {
				System.out.println("Podaj id użytkownika: ");
				int id = scan.nextInt();
				User user = User.loadUserById(conn, id);
				System.out.println("Podaj nazwe użytkownika: ");
				String username = scan.next();
				user.setUsername(username);
				System.out.println("Podaj email: ");
				String email = scan.next();
				user.setEmail(email);
				System.out.println("Podaj hasło: ");
				String password = scan.next();
				user.setPassword(password);
				System.out.println("Podaj id grupy: ");
				int user_group_id = scan.nextInt();
				user.setUser_group_id(user_group_id);
				user.saveToDB(conn);	
			} else if (choice.equals("delete")) {
				System.out.println("Podaj id użytkownika: ");
				int id = scan.nextInt();
				User user = User.loadUserById(conn, id);
				user.delete(conn);
			} else if (choice.equals("quit")) {
				quit = false;
			} else {
				System.out.println(firstBoard);
				choice = scan.next();
			}
		}
		scan.close();
	}

}
