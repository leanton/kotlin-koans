package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        if (this.year != other.year) {
            return year.compareTo(other.year)
        }
        if (this.month != other.month) {
            return month.compareTo(other.month)
        }
        return dayOfMonth.compareTo(other.dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {

    private var currentDate = start

    override fun next(): MyDate {
        val dateToReturn = currentDate
        currentDate = currentDate.nextDay()
        return dateToReturn
    }

    override fun hasNext(): Boolean = currentDate <= endInclusive

    operator fun contains(date: MyDate): Boolean {
        return date >= start && date <= endInclusive
    }
}
