package src;

/*

Name: Lindsey Turner
Date: 2/1/23
Program Description: This program contains methods that perform mathematical functions such as raising numbers to
powers, finding the greatest common denominator, and rounding decimals. My tests are in the main section.

 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the project for the \"Introduction to Java\" and Regulus Time labs! ");
        System.out.println(" (See README-Lab1.md for the former, in the first week, then README-Lab2.md.");

        // check that the starter-files RegulusTime class seems o.k., but don't print anything if it is:
        RegulusTime.demoRegulusTime(false);

        // ToDo: put example calls to your lab methods (like Python functions) here
        if (!(power(2,3) == 8)) System.err.println("Failed test power(2, 3) == 8");
        if (!(GCD(39,12) == 3)) System.err.println("Failed test GCD(39, 12) == 3");
        if (!(GCD_loop(39, 12) == 3)) System.err.println("Failed test GCD_loop(39, 12) == 3");
        if (!(round(2.66) == 3)) System.err.println("Failed test round(2.66) == 3");


        // For reference, examples of testing the diffSlow method (see pythonVersions.py for Python version)
        if (!(diffSlow(10, 15) == 5)) System.err.println("Oops, failed test diffSlow(10, 15) == 5");
        if (!(diffSlow(15, 10) == 5)) System.err.println("Oops, failed test diffSlow(15, 10) == 5");
        if (!(diffSlow(10, 10) == 0)) System.err.println("Oops, failed test diffSlow(10, 10) == 0");

        // Examples of testing the diffSlow method (see pythonVersions.py for Python version)
        if (! (intQuotientSlow(50, 10) == 5)) System.err.println("Oops, failed test intQuotientSlow(50, 10) == 5");
        if (! (intQuotientSlow(59, 10) == 5)) System.err.println("Oops, failed test intQuotientSlow(59, 10) == 5");
        if (! (intQuotientSlow( 9, 10) == 0)) System.err.println("Oops, failed test intQuotientSlow( 9, 10) == 0");
        try {
            int youCantTouchThis = intQuotientSlow(50, -10);
            System.err.println("Huh, should not get here, since intQuotientSlow should throw an exception!");
        } catch (IllegalArgumentException ignored) {
            // we could do something about the exception here, if we wanted to, but in this example all is fine already.
        }
        System.out.println (power(3,3)) ;
        System.out.println (GCD_loop(39, 12));
        System.out.println (round(16.5)) ;


        RegulusTimeNorth labStart = new RegulusTimeNorth(24,  3, 12, 1, 59,  59, "RST");
        RegulusTimeNorth currentTime = new RegulusTimeNorth(24,3,12,3,0,0, "RDT");

        RegulusTimeNorth labStart2 = new RegulusTimeNorth(24,11,5,1,59,59,"RDT");
        RegulusTimeNorth currentTime2 = new RegulusTimeNorth(24,11,5,1,0,0, "RST");

        RegulusTimeNorth time1 = new RegulusTimeNorth(22,11,5,1,59,50,"RDT");
        RegulusTimeNorth time2 = new RegulusTimeNorth(22,11,5,1,0,3, "RST");

       RegulusTimeNorth time3 = new RegulusTimeNorth(22,3,12,1,59,50,"RST");
       RegulusTimeNorth time4 = new RegulusTimeNorth(22,3,12,3,0,3, "RDT");

       RegulusTimeNorth yearBefore = new RegulusTimeNorth(22,3,5,1,0,0,"RST");
       RegulusTimeNorth yearAfter = new RegulusTimeNorth(24,3,5,1,0,0,"RST");

       RegulusTimeNorth time5 = new RegulusTimeNorth(22,3,5,2,0,0,"RST");
       RegulusTimeNorth time6 = new RegulusTimeNorth(22,5,3,2,0,0,"RDT");

       RegulusTimeNorth time7 = new RegulusTimeNorth(22, 11,5,2,0,0,"RDT");
       RegulusTimeNorth time8 = new RegulusTimeNorth(22,12,12,5,0,0, "RST");



       System.out.println(time1.secondsUntil(time2));

        System.out.println(time3.secondsUntil(time4));

        time1.makeNSecondsLater(13);

        System.out.println(time1);

        time3.makeNSecondsLater(13);

        System.out.println(time3);

        System.out.println(yearAfter.clocksChangedBetween(yearBefore));

        System.out.println(time6.clocksChangedBetween(time5));

        System.out.println(time8.clocksChangedBetween(time7));







    }

    // ToDo: put definitions of your lab methods here

    public static int power(int x, int exp) {
        /**
         * This method uses an iteration/for loop to find a number raised to a power.
         * This method returns an integer for (x) raised to the power of (exp)
         * This method takes two parameters, an integer (x) and an integer (exp).
         */
        int result = x ;
        /* the original integer x is multiplied by itself. That result is multiplied by x. This loop runs (exp) number
        of times. i.e. if the exponent passed to the function is 3, the loop will run through 3 times, essentially
        multiplying x by itself 3 times.
         */
        for (int i = 1 ; i < exp ; i ++) {
            result = result * x ;
        }
        return result ;
    }


    public static int GCD(int x, int y) {
        /**
         * This method finds the greatest common denominator of two ints (x and y) using recursion.
         * It returns the GCD integer.
         * It takes two parameters (x and y). Both are integers.
         */
        int big;
        int small;
        if (x > y) {
            big = x;
            small = y;
        }
        else {
            big = y;
            small = x;
        }
        if (big%small == 0) {
            return small;
        }
        /* if there is a remainder when the larger number is divided by the smaller number, the method calls upon
        itself, passing the small number and the remainder. This loop stops when there is no remainder from dividing
        the two numbers passed to it; it has found the GCD, which is the smaller number.
         */
        else {
            return GCD(small, big%small);
        }
    }

    public static int GCD_loop(int x, int y) {
        /**
         * This method finds the GCD of two integers using a while loop instead of calling upon itself like the
         * method above. It takes two integers and returns and integer.
         */
        int big;
        int small;
        if (x > y) {
            big = x;
            small = y;
        }
        else {
            big = y;
            small = x;
        }
        /*
        This loop will continue to run until the remainder is 0 (the small number is returned for the GCD)
        The previous small number and the previous remainder are divided to determine if the remainder is 0.
         */
        while (true) {
            int remainder = big%small;
            if (remainder == 0) {
                return small;
            }
            else {
                big = small;
                small = remainder;
            }
        }
    }

    public static int round(double fullNum) {
        /**
         * This method takes a double (a decimal number) and rounds it to the nearest integer. It returns the integer.
         */
        /* initialize the integer value of the double that is passed to the method. The double is automatically
        rounded down to the nearest integer.
         */
        int intNum = (int)(fullNum);
        // 1 is subtracted from the double until only the decimal part is left.
        while (fullNum > 1.0) {
            fullNum --;
        }
        // round up by adding 1 to the intNum
        if (fullNum >= 0.5) {
            intNum ++;
            return intNum;
        }
        // keeps the rounded down integer and returns that.
        else {
            return intNum;
        }
    }



    /*
    In order to call upon the power method in Java, we need to pass two integers for x and exp. If we passed 'x'
    to the power method, it would cause an error because power will only take an integer. Java catches these errors
    ahead of time. In Python, however, the power function would accept the 'x', and an error would likely come up
    as it goes through the code. This can make it less clear where a mistake has been made.
     */



    // To get you started, below are a few definitions with Python in equivalent comments,
    //   to help you relate to your previous course.
    // You can also see the Python code in pythonVersions.py, which you can run on the command-line via
    //   python3 pythonVersions.py  # (e.g., in the "Terminal" pane you can start from the bottom border of IntelliJ).

    /*
     * Note that comments, which Python starts with hash-mark (#) or records as unused triple-quoted strings,
     *  can either start with double-slash, or be between slash-star and star-slash. What would happen if we
     *  added the actual symbols, as is done for hash-mark, for "triple-quote" and the other symbol descriptions?
     */


    /*
    def diffSlow(x: int, y: int) -> int:
     */
    // For Python function definition (above), the closest Java equivalent is a "public static" method (below)
    public static int diffSlow(int x, int y) {
        /*
        # first, identify the maximum and minimum of (x,y)  --- Python code is here as comments for reference
        if x > y:
            max: int = x
            min: int = y
        else:
            max: int = y
            min: int = x
         */
        // Note that, in Java, variables created in an "if" are only valid within the "if", so create min/max here:
        int min;
        int max;
        if (x > y) {
            max = x;
            min = y;
        } else {
            max = y;
            min = x;
        }
        /*
            difference: int = 0
            while max != min:   # let's hope they're integers
                max = max-1
                difference += 1   # abbreviation for difference = difference+1

            return difference
         */
        int difference = 0;
        while (max != min) {
            max = max - 1;
            difference += 1;
        }

        return difference;
    } // end of diffSlow method


    /*
    def intQuotientSlow(x: int, y: int) -> int:
     */
    public static int intQuotientSlow(int x, int y) {
    /*
        # we only plan to handle the case of positive numbers,
        #  throw an exception if not:
        if x < 0 or y <= 0:
            raise ValueError("intQuotientSlow(x, y) requires x>0, y>=0")
     */
        // In Java, we "throw" an exception created with "new", for the equivalent of Python's "raise".
        // And, the names of specific exceptions differ.
        if (x < 0 || y <= 0) {
            throw new IllegalArgumentException("intQuotientSlow(x, y) requires x>0, y>=0");
        }
    /*
        if x < y:
            return 0
        else:
            smaller_x: int = x-y
            smaller_iq: int = intQuotientSlow(smaller_x, y)
            my_iq: int = 1+smaller_iq
            return my_iq
     */
        // Java is pretty similar to Python, for the above if/then, variables, and function calls:
        // if x is smaller, the quotient is zero, we're done:
        if (x < y) {
            return 0;
        } else {
            int smaller_x  = x - y;
            int smaller_iq = intQuotientSlow(smaller_x, y);
            int my_iq      = 1 + smaller_iq;
            return my_iq;

            //  Equivalent shorter version is less illuminating in debugger:
            //    return 1+intQuotientSlow(x-y, y)
            //  Note that refactoring can transform the former into the latter,
            //    so we can debug the current form, and then smoosh it down without
            ///   breaking it (assuming no bugs in the refactorer).
        }
    }
}



