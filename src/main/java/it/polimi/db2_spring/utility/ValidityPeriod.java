package it.polimi.db2_spring.utility;

public enum ValidityPeriod {
   SHORT(12),
   MEDIUM(24),
   LONG(36);

   private final int duration;

   ValidityPeriod(int duration) {
      this.duration = duration;
   }

   public int getDuration() {
      return duration;
   }

}
