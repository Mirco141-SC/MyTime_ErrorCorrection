import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class MyTimeTest {


   /************************** Constructor ********************************/
   @Test
   fun MyTime_hourLessThan0_IllegalArgumentException(){
       assertThrows<IllegalArgumentException> { val time = MyTime(-1,0,0) }
   }
   @Test
   fun MyTime_hourGreatherThan23_IllegalArgumentException(){
       assertThrows<IllegalArgumentException> { val time = MyTime(24,0,0) }
   }
   @Test
   fun MyTime_minuteGreatherThan59_IllegalArgumentException(){
       assertThrows<IllegalArgumentException> { val time = MyTime(1,60,0) }
   }
   @Test
   fun MyTime_secondGreatherThan59_IllegalArgumentException(){
       assertThrows<IllegalArgumentException> { val time = MyTime(1,0,60) }
   }
   @Test
   fun MyTime_secondLessThan0_IllegalArgumentException(){
       assertThrows<IllegalArgumentException> { val time = MyTime(1,0,-1) }
   }
   @Test
   fun MyTime_minutesLessThan0_IllegalArgumentException(){
       assertThrows<IllegalArgumentException> { val time = MyTime(1,-1,0) }
   }


   /************************** isPrevious ********************************/
   //test per verificare il corretto comportamento della fun isPrevious
   @Test
   fun isPrevious_parameterTimeIsPrevius_returnFalse() {
       val time1 = MyTime(12,10,30)
       val time2 = MyTime(12,10,40)


       val result = time2.isPrevious(time1)


       assertEquals(false, result)
   }


   @Test
   fun isPrevious_parameterTimeIsNotPrevius_returnTrue() {
       val time1 = MyTime(12,10,30)
       val time2 = MyTime(12,10,40)


       val result = time1.isPrevious(time2)


       assertEquals(true, result)
   }


   @Test
   fun isPrevious_parameterTimeIsEquals_returnFalse() {
       val time1 = MyTime(12,10,30)


       val result = time1.isPrevious(time1)


       assertEquals(false, result)
   }


   /************************** SET ********************************/
   //test per verificare il corretto comportamento del set
   @Test
   fun setHour_valueIn023_hourUpdated() {
       val time1 = MyTime(12,10,30)


       val expected = 23
       time1.hour=expected


       val result = time1.hour
       assertEquals(expected, result)
   }
   //test per verificare la corretta gestione dei dati accettabili e non accettabili
   @Test
   fun setHour_valueLessThan0_IllegalArgumentException() {
       val time1 = MyTime(12,10,30)


       assertThrows<IllegalArgumentException> { time1.hour = -1 }
   }
   @Test
   fun setHour_valueGreatherThan23_IllegalArgumentException() {
       val time1 = MyTime(12,10,30)


       assertThrows<IllegalArgumentException> { time1.hour = 24 }
   }


   //test per verificare il corretto comportamento del set
   @Test
   fun setMinute_valueIn059_hourUpdated() {
       val time1 = MyTime(12,10,30)


       val expected = 23
       time1.minute=expected


       val result = time1.minute
       assertEquals(expected, result)
   }
   //test per verificare la corretta gestione dei dati accettabili e non accettabili
   @Test
   fun setMinute_valueLessThan0_IllegalArgumentException() {
       val time1 = MyTime(12,10,30)


       assertThrows<IllegalArgumentException> { time1.minute = -1 }
   }
   @Test
   fun setMinute_valueGreatherThan59_IllegalArgumentException() {
       val time1 = MyTime(12,10,30)


       assertThrows<IllegalArgumentException> { time1.minute = 60 }
   }


   @Test
   fun setSecond_valueIn059_hourUpdated() {
       val time1 = MyTime(12,10,30)


       val expected = 23
       time1.second=expected


       val result = time1.second
       assertEquals(expected, result)
   }
   //test per verificare la corretta gestione dei dati accettabili e non accettabili
   @Test
   fun setSecond_valueLessThan0_IllegalArgumentException() {
       val time1 = MyTime(12,10,30)


       assertThrows<IllegalArgumentException> { time1.second = -1 }
   }


   @Test
   fun setSecond_valueGreatherThan59_IllegalArgumentException() {
       val time1 = MyTime(12,10,30)


       assertThrows<IllegalArgumentException> { time1.second = 60 }
   }


   /************************** addSeconds ********************************/
   @Test
   fun addSeconds_timeToAddLessThan0_IllegalArgumetnExcetion() {
       val time1 = MyTime(12,10,30)


       assertThrows<IllegalArgumentException> { time1.addSeconds(-1) }
   }


   @Test
   fun addSeconds_timeToAddInDay_timeUpdated() {
       val time1 = MyTime(12,10,30)


       val addedSeconds = 45
       time1.addSeconds(addedSeconds)


       val expected = MyTime(12,11,15)


       assertEquals(expected, time1)
   }


   @Test
   fun addSeconds_timeToAddInNewDay_timeUpdated() {
       val time1 = MyTime(23,59,30)


       val addedSeconds = 45
       time1.addSeconds(addedSeconds)


       val expected = MyTime(0,1,15)


       assertEquals(expected, time1)
   }




   /************************ convertInSecond *******************************/
   @Test
   fun convertInSecond_timezero_zero() {
       val time1 = MyTime(0,0,0)


       assertEquals(0,time1.convertInSecond())
   }


   @Test
   fun convertInSecond_timeAcceptable_zero() {
       val time1 = MyTime(1,1,1)


       assertEquals(3661,time1.convertInSecond())
   }






   @Test
   fun differenceTime_sameTime_zero() {
       val time1 = MyTime(1,1,1)
       val time2 = MyTime(1,1,1)


       assertEquals(0, time1.differenceTime(time2))


   }


   @Test
   fun differenceTime_parameterTimeAfter_differenceCalculetdToParameter() {


       /*
       tra le 2:00:00 e le 5:00:00 intercorrono 3 ore
        */
       val time1 = MyTime(2,0,0)
       val time2 = MyTime(5,0,0)


       val expected = 3*3600


       assertEquals(expected, time1.differenceTime(time2))


   }


   @Test
   fun differenceTime_parameterTimePrevious_differenceCalculetdToDayAfter() {


       /*
       tra le 5:00:00 e le 2:00:00 intercorrono 21 ore
        */
       val time1 = MyTime(2,0,0)
       val time2 = MyTime(5,0,0)


       val expected = 21*3600


       assertEquals(expected, time2.differenceTime(time1))
   }






}
