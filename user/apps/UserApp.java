package user.apps;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import entities.Exercise;
import entities.Solution;
import entities.User;
import utils.Connector;

public class UserApp {

	public static void main(String[] args) throws SQLException  {
		int userID = Integer.parseInt(args[0]);
		Scanner scan = new Scanner(System.in);
		Connection conn = Connector.getConnection();
		User user = User.loadUserById(conn, userID);
		boolean quit = true;
		String firstBoard = "Wybierz jedną z opcji: \n" + "\"add\" - dodawanie rozwiazania, \n"
				+ "\"view\" - przeglądanie swoich rozwiązań, \n" + "\"quit\" - wyjście z programu. ";
		while (quit) {
			System.out.println(firstBoard);
			String choice = scan.next();
			if (choice.equals("add")) {
				Solution[] solutions = Solution.loadAllByUserId(conn, userID);
				Exercise[] exercises = Exercise.loadAllExercises(conn);
				
				
				
			} else if (choice.equals("view")) {
				Solution[] solutions = Solution.loadAllByUserId(conn, userID);
				System.out.println(Arrays.toString(solutions));
				break;
			} else if (choice.equals("quit")) {
				quit = false;
			} else {
				System.out.println(firstBoard);
				choice = scan.next();
			}
			
			
			
		}
		
		

	}

}
