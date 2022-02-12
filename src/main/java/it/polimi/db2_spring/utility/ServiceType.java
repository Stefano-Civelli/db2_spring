package it.polimi.db2_spring.utility;

public enum ServiceType {
   MOBILE_PHONE("mobile phone"),
   FIXED_PHONE("mobile phone"),
   FIXED_INTERNET("mobile phone"),
   MOBILE_INTERNET("mobile phone");

   private final String name;

   ServiceType(String s) {
      this.name = s;
   }

   public String getName() {
      return name;
   }
}
