package com.hypermindr.controlpanel.security;

/**
*
* @author Ricardo
*/
public class SessionToken {
   
   private long stamp;
   /** Creates a new instance of SessionToken */
   public SessionToken() {
       stamp = System.currentTimeMillis();
   }
   
}