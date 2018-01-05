package programs.administrative;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import entities.Group;
import utils.Connector;

public class GroupsAdministrative {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		Connection conn = Connector.getConnection();
		Group[] groups = Group.loadAllGroups(conn);
		String firstBoard = "Wybierz jedną z opcji: \n" + "\"add\" - dodanie grupy, \n" + "\"edit\" - edycja grupy, \n"
				+ "\"delete\" - usunięcie grupy, \n" + "\"quit\" - wyjście z programu. ";
		boolean quit = true;
		System.out.println(Arrays.toString(groups));
		while (quit) {
			System.out.println(firstBoard);
			String choice = scan.next();
			if (choice.equals("add")) {
				Group group = new Group();
				System.out.println("Podaj nazwe grupy:");
				String name = scan.next();
				group.setName(name);
				group.saveToDB(conn);
			} else if (choice.equals("edit")) {
				System.out.println("Podaj id grupy:");
				int id = scan.nextInt();
				Group group = Group.loadGroupById(conn, id);
				System.out.println("Podaj nazwe grupy:");
				String name = scan.next();
				group.setName(name);
				group.saveToDB(conn);
			} else if (choice.equals("delete")) {
				System.out.println("Podaj id grupy:");
				int id = scan.nextInt();
				Group group = Group.loadGroupById(conn, id);
				group.delete(conn);
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
