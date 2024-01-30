import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francesco Borzuola
 * @version 12/01/23
 */

public class Scrittore implements Runnable {

  String nomeFile;
  String username;
  String password;

  public Scrittore(String nomeFile, String username, String password) {
    this.nomeFile = nomeFile;
    this.username = username;
    this.password = password;
  }
  // aggiungerer costruttore con 2 parametri testo

  @Override
  public void run() {
    scrivi();
  }

  public void scrivi() {
    BufferedWriter br = null;

    try {
      br = new BufferedWriter(new FileWriter(nomeFile));
      br.write("<" + username + ">");
      br.write("\n\r");
      br.write("<" + password + ">");
      br.write("\n\r");
      br.flush();
    } catch (IOException ex) {
      Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (br != null)
        try {

          br.close();
        } catch (IOException ex) {
          Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  }
}
