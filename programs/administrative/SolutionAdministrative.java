package programs.administrative;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import entities.Exercise;
import entities.Solution;
import entities.User;
import utils.Connector;

public class SolutionAdministrative {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		Connection conn = Connector.getConnection();
		boolean quit = true;
		String firstBoard = "Wybierz jedną z opcji: \n" + "\"add\" - przypisywanie zadań do użytkowników, \n"
				+ "\"view\" - przeglądanie rozwiązań danego użytkownika, \n" + "\"quit\" - wyjście z programu. ";
		while (quit) {
			System.out.println(firstBoard);
			String choice = scan.next();
			if (choice.equals("add")) {
				User[] users = User.loadAllUsers(conn);
				System.out.println(Arrays.toString(users));
				System.out.println("Podaj id użytkownika: ");
				int userID = scan.nextInt();
				Exercise[] exercises = Exercise.loadAllExercises(conn);
				System.out.println(Arrays.toString(exercises));
				System.out.println("Podaj id zadania: ");
				int exerciseID = scan.nextInt();
				Solution solution = new Solution();
				solution.setExcersie_id(exerciseID);
				solution.setUser_id(userID);
				solution.saveToDB(conn);			
			} else if (choice.equals("view")) {
				System.out.println("Podaj id użytkownika: ");
				int userID = scan.nextInt();
				Solution[] solutions = Solution.loadAllByUserId(conn, userID);
				System.out.println(Arrays.toString(solutions));
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
