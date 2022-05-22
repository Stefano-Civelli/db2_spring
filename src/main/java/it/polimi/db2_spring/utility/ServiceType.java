package it.polimi.db2_spring.utility;

public enum ServiceType {
   MOBILE_PHONE("mobile phone"),
   FIXED_PHONE("fixed phone"),
   FIXED_INTERNET("fixed internet"),
   MOBILE_INTERNET("mobile internet");

   private final String name;

   ServiceType(String s) {
      this.name = s;
   }

   public String getName() {
      return name;
   }
}
