import java.io.Console;
import java.util.Arrays;
import java.io.IOException;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author Francesco Borzuola
 * @version 01/02/2024
 */
public class GestioneFile {
  
   static Lettore lettore = new Lettore("user.json");

  public static void serial() {
    // Creazione delle istanze della classe User.java
    User user1 = new User("username1", "password1");
    User user2 = new User("username2", "password2");
    User user3 = new User("username3", "password3");
    // sfruttamento della serializzazione per scrivere i dati degli oggetti in un
    // solo file
    try (DataOutputStream fileOut = new DataOutputStream(
        new BufferedOutputStream(new FileOutputStream("serial.ser")))) {
      ObjectOutputStream Objout = new ObjectOutputStream(fileOut);
      Objout.writeObject(user1);
      Objout.writeObject(user2);
      Objout.writeObject(user3);
    } catch (Exception e) {
      System.err.println("Errore in scrittura del file di serializzazione");
    }
  }

  public static void stream() {
    String scrittura = lettore.Stream("user.json");
    String lettura = "";

    // scrittura dei dati degli utenti all'interno del file user.csv con l'utilizzo
    // di DataOutputStream
    try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("user.csv")))) {
      out.writeUTF(scrittura);
    } catch (IOException ex) {
      System.err.println("Errore in scrittura del file user.csv");
    }

    // lettura dei dati del file user.csv con l'utilizzo di DataInputStream
    try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("user.csv")))) {
      lettura = in.readUTF();
      System.out.println(lettura);
    } catch (IOException ex) {
      System.err.println("Errore in lettura del file user.csv");
    }
  }

  public static void lettura() {
    // 1)LETTURA
    lettore.start();
  }

  public static void elaborazione() {

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
      System.err.println("Errore nel metodo join numero 1");
    }
    
    // 2)ELABORAZIONE
    // inserire utente e password e assegnare a 2 stringhe
    Scanner input = new Scanner(System.in);
    System.out.println("Enter username (SCRIVI TUTTO IN MAIUSCOLO):");
    String username = input.nextLine();
    String username_cifrato = m.cifra(username);
    System.out.println("Enter password (SCRIVI TUTTO IN MAIUSCOLO):");
    String password = input.nextLine();
    String password_cifrata = m.cifra(password);

    // scrivere il file output.csv con questi dati
    // 3) SCRITTURA
    Scrittore scrittore = new Scrittore("output.csv", username_cifrato, password_cifrata);
    // creazione della copia
    Scrittore copia = new Scrittore("copia.csv", username_cifrato, password_cifrata);

    Thread threadScrittore = new Thread(scrittore);
    threadScrittore.start();
    Thread threadCopia = new Thread(copia);
    threadCopia.start();
  }

  public static void main(String[] args) throws IOException {
    lettura();
    elaborazione();
    stream();
    serial();
  }
}