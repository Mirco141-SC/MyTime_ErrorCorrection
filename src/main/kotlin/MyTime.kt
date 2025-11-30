class MyTime(hourValue:Int,minuteValue:Int,secondValue:Int) {
   init {
       require(hourValue >= 0 && hourValue<24) { "hour must be positive and less then 24" }
       require(minuteValue >= 0 && minuteValue<60) { "minute must be positive and less then 60" }
       require(secondValue >= 0 && secondValue<60) { "second must be positive and less then 60" }
   }
   var hour = hourValue
       set(value) {
           require(value>=0 && value<24) {"value must be positive and less then 24 "}
           field=value
       }


   var minute = minuteValue
       set(value) {
           require(value>=0 && value<60) {"value must be positive and less then 60 "}
           field=value
       }
   var second=secondValue
       set(value) {
           require(value>=0 && value<60) {"value must be positive and less then 60 "}
           field=value
       }


   fun convertInSecond(): Int {
       val hoursInSeconds = hour * 3600
       val minutesInSeconds = minute * 60
       val timeInSeconds = hoursInSeconds + minutesInSeconds + second
       return timeInSeconds
       //return hour * 3600 + minute * 60 + second
   }


   fun addSeconds(secondsToAdd: Int) {
       require(secondsToAdd>=0){"newSeconds must be positive"}
       val newTime = this.convertInSecond() + secondsToAdd
       hour = newTime / 3600
       minute = (newTime - (3600 * hour)) / 60
       second = newTime - (3600 * hour) - (60 * minute)
   }


   fun isPrevious(time: MyTime): Boolean {
       if (time.convertInSecond() > this.convertInSecond())
           return true
       else
           return false
   }


   fun differenceTime(time: MyTime): Int {
       val different= convertInSecond()-time.convertInSecond()
       return different
   }


   override fun equals(other: Any?): Boolean {
       if (other is MyTime)
           return hour==other.hour && minute == other.minute && second == other.second
       return false
   }


   override fun hashCode(): Int {
       return hour.hashCode()+minute.hashCode()+second.hashCode()
   }


}
