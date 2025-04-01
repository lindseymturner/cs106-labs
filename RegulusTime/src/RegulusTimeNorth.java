package src;

import java.util.Objects;

import static java.lang.Math.round;

public class RegulusTimeNorth {

    public static void demoRegulusTimeNorth(Boolean printExamples) {

        if (printExamples) System.out.println("beginning demoRegulusTimeNorth, with printing option turned on");

        RegulusTimeNorth beforeSpringAhead = new RegulusTimeNorth(24,  3, 12, 1, 59,  59, "RST");
        RegulusTimeNorth afterSpringAhead = new RegulusTimeNorth(24,3,12,3,0,0, "RDT");

        RegulusTimeNorth beforeFallBack = new RegulusTimeNorth(24,11,5,1,59,59,"RDT");
        RegulusTimeNorth afterFallBack = new RegulusTimeNorth(24,11,5,1,0,0, "RST");



        RegulusTime labStart    = new RegulusTime(23,  1, 20, 9, 35,  0);
        RegulusTime currentTime = new RegulusTime(23,  1, 20, 9, 35,  0);

        if (!(labStart.toString().compareTo("23-01-20 9:35:00 RST")==0)) System.err.println(" Self-check failed: (labStart.toString().compareTo(\"23-01-20 9:35:00 RST\")==0)");
        if (!(labStart.getYear()   == 23)) System.err.println(" Self-check failed: (labStart.getYear()   == 23)");
        if (!(labStart.getMonth()  ==  1)) System.err.println(" Self-check failed: (labStart.getMonth()  == 01)");
        if (!(labStart.getDay()    == 20)) System.err.println(" Self-check failed: (labStart.getDay()    == 20)");
        if (!(labStart.getHour()   ==  9)) System.err.println(" Self-check failed: (labStart.getHour()   ==  9)");
        if (!(labStart.getMinute() == 35)) System.err.println(" Self-check failed: (labStart.getMinute() == 35)");
        if (!(labStart.getSecond() ==  0)) System.err.println(" Self-check failed: (labStart.getSecond() ==  0)");

        if (printExamples) System.out.println("Let's start at " + labStart + " and add 140 seconds in 7-second intervals");
        for (int i=0; i<20; i++) {
            currentTime.makeNSecondsLater(7);
            if (printExamples) System.out.println("  Waited 7, seconds, now it's " + currentTime);
        }
        if (printExamples) System.out.println("Let's check if " + labStart + " now is the expected 9:37:20, complain if not");

        if (!(currentTime.getYear()   == 23)) System.err.println(" Self-check failed: (currentTime.getYear()   == 23)");
        if (!(currentTime.getMonth()  ==  1)) System.err.println(" Self-check failed: (currentTime.getMonth()  == 01)");
        if (!(currentTime.getDay()    == 20)) System.err.println(" Self-check failed: (currentTime.getDay()    == 20)");
        if (!(currentTime.getHour()   ==  9)) System.err.println(" Self-check failed: (currentTime.getHour()   ==  9)");
        if (!(currentTime.getMinute() == 37)) System.err.println(" Self-check failed: (currentTime.getMinute() == 37)");
        if (!(currentTime.getSecond() == 20)) System.err.println(" Self-check failed: (currentTime.getSecond() == 20)");
        if (!(currentTime.AM() && !currentTime.PM())) System.err.println(" Self-check failed: (currentTime.AM() && !currentTime.PM())");

        if (!(labStart.AM() && !labStart.PM())) System.err.println(" Self-check failed: (labStart.AM() && !labStart.PM())");
        if (!(labStart.secondsUntil(currentTime) == 7*20)) System.err.println(" Self-check failed: (labStart.secondsUntil(currentTime) == 7*20)");
        if (!(labStart.minutesUntil(currentTime) == 2)) System.err.println(" Self-check failed: (labStart.minutesUntil(currentTime) == 2)");  //  140 seconds is 2:20, so that makes it 2 minutes

        currentTime.makeNSecondsLater(14);
        if (!(labStart.secondsUntil(currentTime) == 7*20+14)) System.err.println(" Self-check failed: (labStart.secondsUntil(currentTime) == 7*20+14)");
        if (!(labStart.minutesUntil(currentTime) == 3)) System.err.println(" Self-check failed: (labStart.minutesUntil(currentTime) == 3)");  //  155 seconds is 2:35, so that makes it about 3 minutes

        RegulusTime nearlyNoon = new RegulusTime(23,  1, 20, 11, 57, 45);
        if (!(nearlyNoon.AM() && !nearlyNoon.PM())) System.err.println(" Self-check failed: (nearlyNoon.AM() && !nearlyNoon.PM())");
        nearlyNoon.makeNMinutesLater(2);  // 11:59:45
        if (!(nearlyNoon.AM() && !nearlyNoon.PM())) System.err.println(" Self-check failed: (nearlyNoon.AM() && !nearlyNoon.PM())");
        nearlyNoon.makeNSecondsLater(14);  // 11:59:59
        if (!(nearlyNoon.AM() && !nearlyNoon.PM())) System.err.println(" Self-check failed: (nearlyNoon.AM() && !nearlyNoon.PM())");
        nearlyNoon.makeNSecondsLater(2);  // 12:00:01
        if (!(!nearlyNoon.AM() && nearlyNoon.PM())) System.err.println(" Self-check failed: (!nearlyNoon.AM() && nearlyNoon.PM())");
        nearlyNoon.makeNSecondsLater(200);
        if (!(!nearlyNoon.AM() && nearlyNoon.PM())) System.err.println(" Self-check failed: (!nearlyNoon.AM() && nearlyNoon.PM())");

        RegulusTime happyNewCentury = new RegulusTime(399, 12, 30, 23, 59, 50);
        if (!(happyNewCentury.toString().compareTo("399-12-30 23:59:50 RST") == 0))
            System.err.println("Oops, failed self-check happyNewCentury.toString().compareTo(\"399-12-30 23:59:50\") == 0");
        if (!(happyNewCentury.getYear()  == 399)) System.err.println(" Self-check failed: (happyNewCentury.getYear()  == 399)");
        if (!(happyNewCentury.getMonth()  == 12)) System.err.println(" Self-check failed: (happyNewCentury.getMonth()  == 12)");
        if (!(happyNewCentury.getDay()    ==  30)) System.err.println(" Self-check failed: (happyNewCentury.getDay()    == 30)");
        if (!(happyNewCentury.getHour()   ==  23)) System.err.println(" Self-check failed: (happyNewCentury.getHour()   ==  23)");
        if (!(happyNewCentury.getMinute() ==  59)) System.err.println(" Self-check failed: (happyNewCentury.getMinute() == 59)");
        if (!(happyNewCentury.getSecond() ==  50)) System.err.println(" Self-check failed: (happyNewCentury.getSecond() == 50)");
        if (!(!happyNewCentury.AM() && happyNewCentury.PM())) System.err.println(" Self-check failed: (happyNewCentury.AM() && !happyNewCentury.PM())");

        happyNewCentury.makeNSecondsLater(12);
        if (!(happyNewCentury.getYear()  == 400)) System.err.println(" Self-check failed: (happyNewCentury.getYear()  == 400)");
        if (!(happyNewCentury.getMonth()  ==  1)) System.err.println(" Self-check failed: (happyNewCentury.getMonth()  ==  1)");
        if (!(happyNewCentury.getDay()    ==  1)) System.err.println(" Self-check failed: (happyNewCentury.getDay()    ==  1)");
        if (!(happyNewCentury.getHour()   ==  0)) System.err.println(" Self-check failed: (happyNewCentury.getHour()   ==  0)");
        if (!(happyNewCentury.getMinute() ==  0)) System.err.println(" Self-check failed: (happyNewCentury.getMinute() ==  0)");
        if (!(happyNewCentury.getSecond() ==  2)) System.err.println(" Self-check failed: (happyNewCentury.getSecond() ==  2)");
        if (!(happyNewCentury.AM() && !happyNewCentury.PM())) System.err.println(" Self-check failed: (happyNewCentury.AM() && !happyNewCentury.PM())");

        // Hold on, if the Regulus calendar did start with year 1, not zero, then going  from 400 to 401 is the new century!
        // Let's add almost another year, then do it again!
        happyNewCentury.makeNMinutesLater(11*min_per_hour*hour_per_day*day_per_mon); // 11 months
        happyNewCentury.makeNMinutesLater(29*min_per_hour*hour_per_day); // 29 days
        happyNewCentury.makeNMinutesLater(23*min_per_hour);
        happyNewCentury.makeNMinutesLater(55);

        if (!(happyNewCentury.toString().compareTo("400-12-30 23:55:02 RST") == 0))
            System.err.println("Oops, failed self-check happyNewCentury.toString().compareTo(\"400-12-30 23:55:02 RST\") == 0");
        happyNewCentury.makeNSecondsLater(5*sec_per_min);
        if (!(happyNewCentury.toString().compareTo("401-01-01 0:0:02 RST") == 0))
            System.err.println("Oops, failed self-check happyNewCentury.toString().compareTo(\"401-01-01 0:0:02 RST\") == 0");

        if (printExamples) System.out.println("completed demoRegulusTimeNorth");
    }

    private long yr;
    private int mo;
    private int dy;
    private int hr;
    private int min;
    private int sec;

    private String typeTime;

    public RegulusTimeNorth(long year, int month, int day, int hour, int minute, int second, String typeTime) {
        this.yr=year;
        this.mo=month;
        this.dy=day;
        this.hr=hour;
        this.min=minute;
        this.sec=second;
        this.typeTime = typeTime;
    }


    public int getSecond() { return sec; }
    public int getMinute() { return min; }
    public int getHour()   { return hr; }
    public int getDay()    { return dy; }
    public int getMonth()  { return mo; }
    public long getYear()  { return yr; }

    public String getTypeTime() { return typeTime;}

    static final int sec_per_min  = 60;
    static final int min_per_hour = 60;
    static final int hour_per_day = 24;
    static final int day_per_mon  = 30;
    static final int mon_per_year = 12;

    /**
     * Approximately how many minutes from this time until the presumably-later "someLaterTime"
     * @param someLaterTime, a RegulusTimeNorth after "this" time
     * @return the number of minutes difference, rounded to the nearest minute,
     *         so 4:10:59 and 4:11:20 are 0 minutes apart, but 4:10:59 and 4:10:40 are 1 minute apart
     */
    public long minutesUntil(RegulusTimeNorth someLaterTime) {
        double SecDiff = secondsUntil(someLaterTime);
        return round(SecDiff/sec_per_min);
    }
    public long secondsUntil(RegulusTimeNorth someLaterTime) {
        // if the earlier time is in RDT and the later time is in RST, add an hour to the later time before comparing them
        if (isRDT(this) && !isRDT(someLaterTime)) {
            someLaterTime.hr ++;
        }
        // if the earlier time is in RST and the later time is in RDT, add an hour to the later time before comparing them
        if (!isRDT(this) && isRDT(someLaterTime)) {
            someLaterTime.hr --;
        }
            long hoursTil = ((((someLaterTime.yr - this.yr) * mon_per_year +
                (someLaterTime.mo - this.mo)                  ) * day_per_mon +
                (someLaterTime.dy - this.dy)                                     ) * hour_per_day);

        return ((((hoursTil + someLaterTime.hr - this.hr) * min_per_hour +
                (someLaterTime.min - this.min)                           ) * sec_per_min) +
                (someLaterTime.sec - this.sec)                                               );
    }
    /** method takes a number of seconds and adds that to the current time
     * @param N: a non-negative number of seconds to add to the current time, can be bigger than 60
     */
    public void makeNSecondsLater(int N) {
        assert(N >= 0);
        sec = sec+N%sec_per_min;
        while (sec >= sec_per_min) { // "if" should be enough, but why not be sure?
            sec = sec - sec_per_min;  // reduces "second hand" by 60 seconds, get to/below 60
            min = min + 1;            // without changing the actual time
        }
        if (N>=sec_per_min || min>=min_per_hour) {
            makeNMinutesLater(N/sec_per_min);
        }
            // if time is in RST, but it passed spring ahead, the time must increase by 1 hour and typeTime must be changed to RDT
            if (!isRDT(this)) {
                if (3<mo && mo<11) {
                    this.typeTime = "RDT";
                    this.hr ++;
                }
                else if (mo==3 && dy>12) {
                    this.typeTime = "RDT";
                    this.hr ++;
                }
                else if (mo==3 && dy==12 && hr>=2) {
                    this.typeTime = "RDT";
                    this.hr ++;
                }
                else if (mo==11 && dy<5) {
                    this.typeTime = "RDT";
                    this.hr ++;
                }
                else if (mo==11 && dy==5 && hr<2) {
                    typeTime = "RDT";
                    hr ++;
                }
            }
            // if time is in RDT, but it passed fall back, the time must be decreased by 1 hour and typeTime must be changed to RST
            if (isRDT(this)) {
                if (mo<3 || mo>11) {
                    typeTime = "RST";
                    hr --;
                }
                else if (mo==3 && dy<12) {
                    typeTime = "RST";
                    hr --;
                }
                else if (mo==3 && dy==12 && hr<2) {
                    typeTime = "RST";
                    hr --;
                }
                else if (mo==11 && dy>5) {
                    typeTime = "RST";
                    hr --;
                }
                else if (mo==11 && dy==5 && hr>=2) {
                    typeTime = "RST";
                    hr --;
                }
            }
    }
    /**
     * @param N: a non-negative number of minutes to add to the current time, can be bigger than 60
     */
    public void makeNMinutesLater(int N) { // Note that "min" may not be proper, due to call from Seconds
        assert(N >= 0);
        min = min+N%min_per_hour;
        while (min >= min_per_hour) { // "if" might be enough, but why not be sure?
            min = min - min_per_hour;
            hr = hr + 1;
        }
        if (N>=min_per_hour || hr>=hour_per_day) {
            makeNHoursLater(N/min_per_hour);
        }
        if (typeTime == "RST") {
            if (3<mo && mo<11) {
                this.typeTime = "RDT";
                this.hr ++;
            }
            else if (mo==3 && dy>12) {
                this.typeTime = "RDT";
                this.hr ++;
            }
            else if (mo==3 && dy==12 && hr>=2) {
                this.typeTime = "RDT";
                this.hr ++;
            }
            else if (mo==11 && dy<5) {
                this.typeTime = "RDT";
                this.hr ++;
            }
            else if (mo==11 && dy==5 && hr<2) {
                typeTime = "RDT";
                hr ++;
            }
        }
        else if (typeTime == "RDT") {
            if (mo<3 || mo>11) {
                typeTime = "RST";
                hr --;
            }
            else if (mo==3 && dy<12) {
                typeTime = "RST";
                hr --;
            }
            else if (mo==3 && dy==12 && hr<2) {
                typeTime = "RST";
                hr --;
            }
            else if (mo==11 && dy>5) {
                typeTime = "RST";
                hr --;
            }
            else if (mo==11 && dy==5 && hr>=2) {
                typeTime = "RST";
                hr --;
            }
        }
    }
    /**
     * @param N: a non-negative number of seconds to add to the current time, can be bigger than 60
     */
    public void makeNHoursLater(int N) {
        assert(N >= 0);
        hr = hr+N%hour_per_day;
        while (hr >= hour_per_day) { // "if" might be enough, but why not be sure?
            hr = hr - hour_per_day;
            dy = dy + 1;
        }
        dy = dy+(N/hour_per_day)%day_per_mon;
        // Note the next two have > rather than >= because days and months count from one up to 30 or 12 inclusive
        while (dy > day_per_mon) { // "if" might be enough, but why not be sure?
            dy = dy - day_per_mon;
            mo = mo + 1;
        }
        mo = mo+N/(hour_per_day*day_per_mon);
        while (mo > mon_per_year) { // "if" might be enough, but why not be sure?
            mo = mo - mon_per_year;
            yr = yr + 1;
        }
            if (typeTime == "RST") {
                if (3<mo && mo<11) {
                    this.typeTime = "RDT";
                    this.hr ++;
                }
                else if (mo==3 && dy>12) {
                    this.typeTime = "RDT";
                    this.hr ++;
                }
                else if (mo==3 && dy==12 && hr>=2) {
                    this.typeTime = "RDT";
                    this.hr ++;
                }
                else if (mo==11 && dy<5) {
                    this.typeTime = "RDT";
                    this.hr ++;
                }
                else if (mo==11 && dy==5 && hr<2) {
                    typeTime = "RDT";
                    hr ++;
                }
            }
            else if (typeTime == "RDT") {
                if (mo<3 || mo>11) {
                    typeTime = "RST";
                    hr --;
                }
                else if (mo==3 && dy<12) {
                    typeTime = "RST";
                    hr --;
                }
                else if (mo==3 && dy==12 && hr<2) {
                    typeTime = "RST";
                    hr --;
                }
                else if (mo==11 && dy>5) {
                    typeTime = "RST";
                    hr --;
                }
                else if (mo==11 && dy==5 && hr>=2) {
                    typeTime = "RST";
                    hr --;
                }
            }
    }

    public Boolean AM() {
        return hr<12;
    }
    public Boolean PM() {
        return hr>=12;
    }
    public String toString() {
        String month2digits =  (mo < 10) ? ("0"+mo)  : (""+mo);
        String day2digits   =  (dy < 10) ? ("0"+dy)  : (""+dy);
        String sec2digits   = (sec < 10) ? ("0"+sec) : (""+sec);
        return (yr+"-"+month2digits+"-"+day2digits +" "+ hr+":"+min+":"+sec2digits+ typeTime);
    }



    /** This method takes three RegulusTime objects (each object has date and time) and passes them to the minutes
     * Until method to computer the number of minutes between the first and second times, and then the second and
     * third times. If these are equal it returns true.
     **/
    public static boolean sameIntervals(RegulusTimeNorth first, RegulusTimeNorth second, RegulusTimeNorth third) {
        return first.minutesUntil(second) == second.minutesUntil(third);
    }

    /**
     * Method determines if the clocks ever switched between two times.
     * @param oldTime this is the earlier time object that is passed into the function.
     * @return returns true if the clocks ever changed and false if else.
     */
    public boolean clocksChangedBetween(RegulusTimeNorth oldTime) {
        RegulusTimeNorth springForward = new RegulusTimeNorth(0, 3, 12, 2, 0, 0, "RST");
        RegulusTimeNorth fallBack = new RegulusTimeNorth(0, 11,5,2,0,0,"RDT");

        // if time crosses spring forward threshold clocks had to change
        if (!isRDT(oldTime)) {
            // if in the same year, new month is greater than March, it had to cross the threshold
            if (oldTime.yr == this.yr && this.mo > springForward.mo) {
                return true;
            }
            // if in the same year, and new month is march, and new day is later than 12, then it had to cross the threshold
            else if (oldTime.yr == this.yr && this.mo == springForward.mo && this.dy > springForward.dy) {
                return true;
            }
            // if in the same year, and month is march, and day is 12, and new hour is greater than or equal to 2, then it had to cross the threshold
            else if (oldTime.yr == this.yr && this.mo == springForward.mo && this.dy == springForward.dy && this.hr >= springForward.hr) {
                return true;
            }
            // if in greater year on the same date, it has to cross the threshold
            else if(this.yr > oldTime.yr && this.mo==oldTime.mo && this.dy == oldTime.dy && this.hr== oldTime.hr) {
                return true;
            }
            // if in greater year, and new month is greater than march, then it had to cross the threshold
            else if (this.yr > oldTime.yr && this.mo > springForward.mo) {
                return true;
            }
            // if in greater year, and new month is march, and the day is greater than 12, it had to cross the threshold.
            else if (this.yr > oldTime.yr && this.mo == springForward.mo && this.dy > springForward.dy) {
                return true;
            }
            // if in greater year, and new month is march, and the day is 12, and the hour is greater than 2 or equal to 2, it had to cross the threshold
            else if (this.yr > oldTime.yr && this.mo == springForward.mo && this.dy == springForward.dy && this.hr >= 2) {
                return true;
            }
            // if in greater year, and old month was less than march, it had to cross the threshold
            else if (this.yr > oldTime.yr && oldTime.mo < springForward.mo) {
                return true;
            }
            // if in greater year, and old month was march, and old day was less than 12, it had to cross the threshold
            else if (this.yr > oldTime.yr && oldTime.mo == springForward.mo && oldTime.dy < springForward.dy) {
                return true;
            }
            // if in greater year, and old month was march, and old day was 12, and old hour was less than 2, it had to cross the threshold
            else return this.yr > oldTime.yr && oldTime.mo == springForward.mo && oldTime.dy == springForward.dy && oldTime.hr < springForward.hr;
        }


        // if time crosses fall back threshold, it had to change
        if (isRDT(oldTime)) {
            // if the year goes up, must have crossed fall back threshold
            if (this.yr > oldTime.yr) {
                return true;
            }
            // if month is greater than november, then it had to pass threshold
            else if (this.mo > fallBack.mo) {
                return true;
            }
            // if month is november, and day is greater than 12, then  it had to pass the threshold
            else if (this.mo == fallBack.mo && this.dy > fallBack.dy) {
                return true;
            }
            // if month is november, and day is 12 and the hour is greater than or equal to 2, then  it had to pass the threshold
            else return this.mo == fallBack.mo && this.dy == fallBack.dy && this.hr >= fallBack.hr;
        }
        // if timeType changes from RST to RDT or vice-versa, obviously the clocks had to change
        if (!isRDT(oldTime)) {
            return Objects.equals(this.typeTime, "RDT");
        }
        if (isRDT(oldTime)) {
            return Objects.equals(this.typeTime, "RST");
        }
        return false;
    }

    /**
     * method determines if the time is in RDT or RST
     * @param someTime takes a regulus time object
     * @return returns true if in RDT or false if in RST
     */
    public boolean isRDT(RegulusTimeNorth someTime) {
        return Objects.equals(someTime.typeTime, "RDT");
    }
}


