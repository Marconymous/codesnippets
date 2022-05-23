package dev.marconymous.codesnippets.model;

import java.util.Date;

/**
 * Class to represent an admin.
 */
public class Admin extends ApplicationUser {
  /**
   * When the admin was last online.
   */
  private Date lastOnline;

  public Admin() {
    super();
  }

  public Admin(Date lastOnline) {
    this.lastOnline = lastOnline;
  }

  public Admin(Date signUpDate, Date lastOnline) {
    super(signUpDate);
    this.lastOnline = lastOnline;
  }

  public Date getLastOnline() {
    return lastOnline;
  }

  public void setLastOnline(Date lastOnline) {
    this.lastOnline = lastOnline;
  }
}
