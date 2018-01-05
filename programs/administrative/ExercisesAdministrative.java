package programs.administrative;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import entities.Exercise;
import utils.Connector;

public class ExercisesAdministrative {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		Connection conn = Connector.getConnection();
		Exercise[] exercises = Exercise.loadAllExercises(conn);
		String firstBoard = "Wybierz jedną z opcji: \n" + "\"add\" - dodanie zadania, \n"
				+ "\"edit\" - edycja zadania, \n" + "\"delete\" - usunięcie zadania, \n"
				+ "\"quit\" - wyjście z programu. ";
		boolean quit = true;
		System.out.println(Arrays.toString(exercises));
		while (quit) {
			System.out.println(firstBoard);
			String choice = scan.next();
			if (choice.equals("add")) {
			Exercise exercise = new Exercise();
			System.out.println("Podaj tytuł zadania :");
			String title = scan.next();
			exercise.setTitle(title);
			System.out.println("Podaj opis zadania :");
			String description = scan.next();
			exercise.setDescription(description);
			exercise.saveToDB(conn);
			} else if (choice.equals("edit")) {
				System.out.println("Podaj id zadania: ");
				int id = scan.nextInt();
				Exercise exercise = Exercise.loadExerciseById(conn, id);
				System.out.println("Podaj tytuł zadania :");
				String title = scan.next();
				exercise.setTitle(title);
				System.out.println("Podaj opis zadania :");
				String description = scan.next();
				exercise.setDescription(description);
				exercise.saveToDB(conn);
			} else if (choice.equals("delete")) {
				System.out.println("Podaj id zadania: ");
				int id = scan.nextInt();
				Exercise exercise = Exercise.loadExerciseById(conn, id);
				exercise.delete(conn);
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
