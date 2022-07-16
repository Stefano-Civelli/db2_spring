package it.polimi.db2_spring.utility;

public enum ServiceType {
   FIXED_PHONE("fixed phone"),
   FIXED_INTERNET("fixed internet"),
   MOBILE_PHONE("mobile phone"),

   MOBILE_INTERNET("mobile internet");

   private final String name;

   ServiceType(String s) {
      this.name = s;
   }

   public String getName() {
      return name;
   }
}
