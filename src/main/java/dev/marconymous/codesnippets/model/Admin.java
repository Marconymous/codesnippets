package dev.marconymous.codesnippets.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;



/**
 * Class to represent an admin.
 */
@Data
@AllArgsConstructor
public class Admin extends ApplicationUser {
  /**
   * When the admin was last online.
   */
  private Date lastOnline;
}
