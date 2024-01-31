/**
 * @author Francesco Borzuola
 * @version 31/01/2024
 */

import java.io.Serializable;

public class User implements Serializable {
  private String username;
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}