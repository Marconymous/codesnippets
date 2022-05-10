package dev.marconymous.codesnippets.model;

import java.util.Date;

public class Admin extends ApplicationUser {
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
