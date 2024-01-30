import java.io.Console;
import java.util.Arrays;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Francesco Borzuola
 * @version 30/01/2024
 */
public class GestioneFile {

  public static void main(String[] args) throws IOException {

    Matrice m = new Matrice("PROVA");
    Vigenere v = new Vigenere(0, 12, 0, 12, m);
    Vigenere v1 = new Vigenere(12, 26, 0, 12, m);
    Vigenere v2 = new Vigenere(0, 12, 12, 26, m);
    Vigenere v3 = new Vigenere(12, 26, 12, 26, m);
    Thread t = new Thread(v);
    Thread t1 = new Thread(v1);
    Thread t2 = new Thread(v2);
    Thread t3 = new Thread(v3);
    t.start();
    t1.start();
    t2.start();
    t3.start();
    try {
      t.join();
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      System.err.println("Errore nel metodo join");
    }
    // 1)LETTURA
    Lettore lettore = new Lettore("user.json");
    lettore.start();
    // 2)ELABORAZIONE
    Scanner input = new Scanner(System.in);
    System.out.println("Enter username (SCRIVI TUTTO IN MAIUSCOLO):");
    String username = input.nextLine();
    String username_cifrato = m.cifra(username);
    System.out.println("Enter password (SCRIVI TUTTO IN MAIUSCOLO):");
    String password = input.nextLine();
    String password_cifrata = m.cifra(password);
    // inserire utente e password e assegnare a 2 stringhe
    // scrivere il ile output.csv con questi dati
    // 3) SCRITTURA
    Scrittore scrittore = new Scrittore("output.csv", username_cifrato, password_cifrata);
    Thread threadScrittore = new Thread(scrittore);
    threadScrittore.start();
  }

}
