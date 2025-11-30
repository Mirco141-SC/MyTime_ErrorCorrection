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
    @Test
    fun isPrevious_parameterTimeIsPrevious_returnFalse() {
        val time1 = MyTime(12,10,30)
        val time2 = MyTime(12,10,40)

        val result = time2.isPrevious(time1)

        assertEquals(false, result)
    }

    @Test
    fun isPrevious_parameterTimeIsNotPrevious_returnTrue() {
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


    /************************** GET ********************************/
    @Test
    fun getHours_undefined_hours() {
        val time1 = MyTime(12,10,30)

        val result = time1.hours
        val expected = 12

        assertEquals(expected, result)
    }

    @Test
    fun getMinutes_undefined_minutes() {
        val time1 = MyTime(12,10,30)

        val result = time1.minutes
        val expected = 10

        assertEquals(expected, result)
    }

    @Test
    fun getSeconds_undefined_seconds() {
        val time1 = MyTime(12,10,30)

        val result = time1.seconds
        val expected = 30

        assertEquals(expected, result)
    }

    @Test
    fun getDaysSinceFirstMidnight_undefined_returnDaysSinceFirstMidnight() {
        val time1 = MyTime(12, 10, 30)

        val result = time1.daysSinceFirstMidnight
        val expected = 0

        assertEquals(expected, result)
    }

    /************************** addSeconds ********************************/
    @Test
    fun addSeconds_timeToAddLessThan0_IllegalArgumentException() {
        val time1 = MyTime(12,10,30)


        assertThrows<IllegalArgumentException> {time1.addSeconds(-1)}
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

        val previous = time1.secondsSinceFirstMidnight
        val addedSeconds = 45
        time1.addSeconds(addedSeconds)

        assertEquals(previous + 45, time1.secondsSinceFirstMidnight)
        assertEquals(1, time1.daysSinceFirstMidnight)
    }


    /************************ differenceTime *******************************/
    @Test
    fun differenceTime_sameTime_zero() {
        val time1 = MyTime(1,1,1)
        val time2 = MyTime(1,1,1)

        assertEquals(0, time1.differenceTime(time2))
    }

    @Test
    fun differenceTime_parameterTimeAfter_differenceCalculatedToParameter() {
        val time1 = MyTime(2,0,0)
        val time2 = MyTime(5,0,0)

        val expected = 3*3600

        assertEquals(expected, time1.differenceTime(time2))
    }

    @Test
    fun differenceTime_parameterTimePrevious_differenceCalculatedToDayAfter() {
        val time1 = MyTime(2,0,0)
        val time2 = MyTime(5,0,0)

        val expected = 21*3600

        assertEquals(expected, time2.differenceTime(time1))
    }

    @Test
    fun differenceTime_acrossMidnight_maxDifference() {
        val time1 = MyTime(0,0,0)
        val time2 = MyTime(23,59,59)

        assertEquals(86399, time1.differenceTime(time2))
    }

    @Test
    fun differenceTime_acrossMidnight_minDifference() {
        val time1 = MyTime(23,59,59)
        val time2 = MyTime(0,0,0)

        assertEquals(1, time1.differenceTime(time2))
    }

    /************************ equals *******************************/
    @Test
    fun equals_timesAreEqual_returnTrue() {
        val time1 = MyTime(10,0,0)
        val time2 = MyTime(10,0,0)

        assertEquals(true, time1 == time2)
    }

    @Test
    fun equals_differentTypes_returnFalse() {
        val time1 = MyTime(10,0,0)

        assertEquals(false, time1.equals("Not a MyTime"))
    }

    /************************ hashCode *******************************/
    @Test
    fun hashCode_timesAreEqual_returnTrue() {
        val time1 = MyTime(10,0,0)
        val time2 = MyTime(10,0,0)

        assertEquals(time1.hashCode(), time2.hashCode())
    }
}
