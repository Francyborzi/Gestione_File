import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 *
 * @author Francesco Borzuola
 * @ 30/01/2024
 */

public class Lettore extends Thread {
  String nomeFile;

  public Lettore(String nomeFile) {
    this.nomeFile = nomeFile;
  }

  /**
   * Legge il file senza tener conto del tipo di file
   * e lo mostra in output
   */
  public void leggi() {
    int i;
    // try-with-resources
    try (BufferedReader fr = new BufferedReader(new FileReader(nomeFile))) {
      // 1) apro il file
      // 2) leggo carattere per carattere e lo stampo
      while ((i = fr.read()) != -1)
        System.out.print((char) i);

      System.out.print("\n\r");
      // 3) chiudo il file
    } catch (IOException ex) {
      System.err.println("Errore in lettura!");
    }
  }

  //aggiunta del metodo dedicato agli stream
  public String Stream(String nomeFile) {
    String userData = "";
    try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
      String line;
      StringBuilder sb = new StringBuilder();
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
      userData = sb.toString();
    } catch (IOException ex) {
      System.err.println("Errore in lettura!");
    }
    return userData;
  }

  public void run() {
    leggi();
  }
}
