class MyTime(hoursValue:Int, minutesValue:Int, secondsValue:Int) {
   init {
    require(hoursValue in 0..23){"The hours value must be positive and less than 24"}
    require(minutesValue in 0..59){"The minutes value must be positive and less than 60"}
    require(secondsValue in 0..59){"The seconds value must be positive and less than 60"}
   }

   var secondsSinceFirstMidnight = (hoursValue * 60 * 60) + (minutesValue * 60) + secondsValue
     private set(value) {
         require(value >= 0){"The given value must be higher or equal to 0"}
   
         field = value
   }
   
   var hours:Int
     get() {
         val daySeconds = secondsSinceFirstMidnight % 86400 //86400 = 1 day

         return daySeconds / 3600 //3600 = 1 hour
     }
     
   var minutes:Int
     get() {
         val daySeconds = secondsSinceFirstMidnight % 86400
         val remainingSecondsAfterHours = daySeconds % 3600
         return remainingSecondsAfterHours / 60
     }
       
   var seconds:Int
     get() {
         return secondsSinceFirstMidnight % 60
     }

   val daysSinceFirstMidnight:Int //Only here to be used in future cases
     get() {
         return secondsSinceFirstMidnight / 86400
     }

   fun addSeconds(secondsToAdd: Int) {
        require(secondsToAdd > 0){"The given value must be higher than 0"}

        secondsSinceFirstMidnight += secondsToAdd
   }

   fun isPrevious(time: MyTime): Boolean {
        return time.secondsSinceFirstMidnight > this.secondsSinceFirstMidnight
   }


   fun differenceTime(time: MyTime): Int {
        /**
         * Since we don't use a unique epoch timestamp,
         * this function calculates the difference between the two times
         * assuming they are on the same day, and ignores the fact that
         * they could be on different days of distance
         */

        val difference = time.secondsSinceFirstMidnight - this.secondsSinceFirstMidnight

        return if(difference > 0) {
            difference
        } else if (difference < 0) {
            86400 - Math.abs(difference)
        } else 0
   }


   override fun equals(other: Any?): Boolean {
        /**
         * Since we don't use a unique epoch timestamp,
         * this function calculates the difference between the two times
         * assuming they are on the same day, and ignores the fact that
         * they could be on different days of distance
         */

        if(this === other) return true
        if(other !is MyTime) return false
        return this.secondsSinceFirstMidnight == other.secondsSinceFirstMidnight
   }


   override fun hashCode(): Int {
        return secondsSinceFirstMidnight.hashCode()
   }
}
