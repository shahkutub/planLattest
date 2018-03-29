package com.nanosoft.planInternational.tracking.utility;

/**
 * Created by Nanosoft-Android on 3/8/2017.
 */


import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Nanosoft-Android on 3/8/2017.
 */
public class AgeCalculator {
    private int fromDay, fromMonth, fromYear, toDay, toMonth, toYear;

    public AgeCalculator(int fromDay, int fromMonth, int fromYear, int toDay, int toMonth,
                         int toYear) {
        this.fromDay = fromDay;
        this.fromMonth = fromMonth;
        this.fromYear = fromYear;
        this.toDay = toDay;
        this.toMonth = toMonth;
        this.toYear = toYear;
    }

    public int[] getAge() {
        Calendar c = Calendar.getInstance();
        c.set(fromYear, fromMonth + 1, fromDay);
        int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int athand = 0, day = 0, month = 0;

        if (toDay > fromDay) {
            day = toDay - fromDay;
            athand = 0;
        } else {
            day = (toDay + maxDay) - fromDay;
            athand = 1;
        }

        if (toMonth > fromMonth + athand) {
            month = toMonth - (fromMonth + athand);
            athand = 0;
        } else {
            month = (toMonth + 12) - (fromMonth + athand);
            athand = 1;
        }

        int year = toYear - (fromYear + athand);
        return new int[]{day, month, year};
    }

    public int getMonths() {
        int[] age = getAge();
        return age[0] * 12 + age[1];
    }

    public String getAgeString() {
        int[] age=getAge();
        return String.format(Locale.ENGLISH, "%s day(s), %s month(s), %s year(s)",age[2],age[1],age[0]);
    }
}
