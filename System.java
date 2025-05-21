/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.util.*;
/**
 *
 * @author maban
 */
public class System {
        static final String FILE = "students.txt";
    private static Readable in;

    public static void main(String[] args) throws IOException {
        var sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add  2. List  3. Find  4. Edit  5. Remove  6. Exit\nPick: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> add(sc);
                case 2 -> list();
                case 3 -> find(sc);
                case 4 -> editOrDelete(sc, true);
                case 5 -> editOrDelete(sc, false);
                case 6 -> System.exit(0);
                default -> System.out.println("Try again.");
            }
        }
    }

    static void add(Scanner sc) throws IOException {
        System.out.print("Enter: ID,First,Middle,Last,Course,Year: ");
        try (var w = new FileWriter(FILE, true)) {
            w.write(sc.nextLine() + "\n");
        }
    }

    static void list() throws IOException {
        try (var r = new BufferedReader(new FileReader(FILE))) {
            System.out.println("\n-- Student Records --");
            r.lines().forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println("No records yet.");
        }
    }

    static void find(Scanner sc) throws IOException {
        System.out.print("Enter ID to search: ");
        String id = sc.nextLine();
        try (var r = new BufferedReader(new FileReader(FILE))) {
            r.lines()
             .filter(l -> l.startsWith(id + ","))
             .findFirst()
             .ifPresentOrElse(System.out::println, () -> System.out.println("Not found."));
        }
    }

    static void editOrDelete(Scanner sc, boolean edit) throws IOException {
        System.out.print("Enter ID to " + (edit ? "edit" : "remove") + ": ");
        String id = sc.nextLine();
        File oldFile = new File(FILE), tempFile = new File("temp.txt");
        boolean found = false;

        try (var r = new BufferedReader(new FileReader(oldFile));
             var w = new PrintWriter(new FileWriter(tempFile))) {
            for (String line; (line = r.readLine()) != null;) {
                if (line.startsWith(id + ",")) {
                    found = true;
                    if (edit) {
                        System.out.print("Enter new: First,Middle,Last,Course,Year: ");
                        w.println(id + "," + sc.nextLine());
                    }
                } else w.println(line);
            }
        }

        if (found) {
            oldFile.delete(); tempFile.renameTo(oldFile);
            System.out.println(edit ? "Updated." : "Removed.");
        } else {
            tempFile.delete();
            System.out.println("ID not found.");
        }
    }

    private static void exit(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class out {

        private static void println(String _Add__2_List__3_Find__4_Edit__5_Remove__6) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void print(String enter_IDFirstMiddleLastCourseYear_) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public out() {
        }
    }
}
