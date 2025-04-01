<h1 style="text-align: center;">Lab 2: Regulus Time</h1>
<h3 style="text-align: center;">Haverford CS 106 - Introduction to Data Structures</h3>
<h3 style="text-align: center;">Due: *** </h3>

# Overview

In order to make any Java program, you need to make a class.
In Lab 1, we made some classes for you, but they were used in different ways:
* `class Main` existed just because we had to make a class;
  in Python and many other languages, the `public static` _methods_ of `class Main`
  could have been written as functions.
* `class RegulusTime` existed for a different purpose, to let us create
  _objects_ of type `ReglusTime`, as well as the built-in types like
  strings and integers.

Now that you've seen a few more lectures more about classes,
this lab asks you to write another class that, like class `RegulusTime`,
creates objects.
Your class will be it will be almost identical to `RegulusTime`,
but implement a "Daylight Savings Time" feature,
automatically switching between Regulus Standard Time ("RST"),
and _Regulus Daylight Time_ ("RDT"),
as requested by the people of the farthest-north country on Regulus.

RDT begins, as does EDT in the U.S., at 2AM on the 12th day of the 3rd month of the year,
when "the clocks move forward", i.e., the next second after "03-12 02:00:00 RST"
is referred to as "03-12-0**3**:00:01 **RDT**".
RDT ends, as does EDT in the U.S., at 2AM _daylight savings time_ on the 5th day of the 11th month,
i.e., the next second after "11-05 02:00:00 RDT"
is referred to as "11-05 0**1**:00:01 **RST**".
Note that the next second after "11-05 02:00:00 **RST**"
is referred to as "11-05 02:00:01 RST",
the clocks don't [get set back every time they show 2AM that night](https://rachelbythebay.com/w/2020/10/07/wintime/).

Your job is to create
* `class RegulusTimeNorth`,
   which implements all the methods from the original RegulusTime,
   but automatically adjusts between the wintertime RST
   (which is identical to RST elsewhere), and summertime RDT,
   as per details below.

The existing `class RegulusTime` should _not_ be changed;
people in countries that do not observe the RST/RDT switch
can still use it.

The two time classes do _not_ need to interact, in this lab,
i.e., you don't have to be able to figure out how many minutes occur
between a RegulusTime object and a RegulusTimeNorth object,
though you should be able to find the number of minutes between
two RegulusTimeNorth objects, even if one is in the summer and one the winter.

Details of the class are given below.



# Code to be written

Create a `class RegulusTimeNorth`, as described below.

Be sure to write clear and complete comments for all of your methods,
including "docstring" comments for all methods.


0. Optionally, choose a Lab partner and record their name and email,
   with the comment "Lab 2 partner" next to your Lab 1 partner's name.
   All steps except the last can be done in close collaboration with
   your partner, i.e., you can look at each other's code,
   but don't copy/paste, email it, etc.
   
1. Create a `class RegulusTimeNorth`, via "New->Java Class"

2. Think carefully about the proper representation.

   You'll need to keep track of the previous information, and also know whether the time
   is in RST or RDT.
   The "obvious" solution is just to use the same data fields as `RegulusTime`,
   and add a string or boolean field to distinguish RST vs RDT.
   But, this is not the only way.
   Some systems just represent time as the total number of seconds since the start
   of some standard year (e.g., "year 0"),
   and then figure out the current month, day, hour, etc., when asked.
   There is a long history of systems exploring a variety of date/time
   representations, and having
   [a variety of bugs related to time representation](https://en.wikipedia.org/wiki/Time_formatting_and_storage_bugs).
   We strongly recommend that you not use a two-digit string to represent the year,
   for example.

   Each of those representations makes some operations _easier_, and some _harder_.
   The relative overall merits may be different, for the two time classes,
   and `RegulusTime` may not use the best possible solution ...
   it's just there to demonstrate some basic techniques for writing classes.

   We encourage you to talk with classmates about which representation is going to make
   the rest of this lab easier,
   and write a comment about why you chose this.
   If you're not looking at code, you can talk to as many other classmates as you like.

3. Write all the methods that exist in `class RegulusTime` for your `class RegulusTimeNorth`;
   they should work the same _except_:

   1. The constructor should have an additional string field that must be given as
      "RST" for a standard time, or "RDT" for a daylight-savings time.

   2. The toString method should include the proper "RST" or "RDT" text at the end.
   
   3. Provide a demoRegulusTimeNorth method,
      which includes all the tests from the original demoRegulusTime
      (feel free to copy-paste it),
      and also demonstrates how it works with RDT.

   4. The other methods should handle conversions correctly, e.g.,
      if you create a `RegulusTimeNorth` called `t` that represents
      "22-11-05 01:59:50 RDT",
      and then use `t.makeNSecondsLater(13)`,
      that should give a time that gets printed as "22-11-05 01:00:03 RST",
      and `t.getHour()` should produce 1, not 2.
      If you also had another variable `t_original` that is still
      "22-11-05 01:59:50 RDT",
      then `t_original.secondsUntil(t)` it should say 13,
      since 1:59:50 **RDT** is _earlier_ than 1:00:03 **RST**.

4. Add a boolean method `isRDT`, which returns true for a daylight-savings time.

5. **Solo** work: add a method clocksChangedBetween, which takes one parameter, 
   also a `RegulusTimeNorth`, and tells whether the clocks
   have ever changed between RST and RDT, or vice versa, between `this` and the parameter.
   So, if `t1` is 23-02-14 12:00:00 RST (i.e., Feb. 14 at noon),
   and `t2` is 23-07-01 18:00:00 RDT (i.e., July 1 at 6 PM),
   then `t1.clocksChangedBetween(t2)` will be true.
   If `t3` is 23-07-14 14:12:00 RDT (i.e., July 14 at 2:12 PM),
   then `t1.clocksChangedBetween(t3)` will be true, but
   then `t2.clocksChangedBetween(t3)` will be false.
   Note that, if `t4` is after the switch back to RST, e.g. 23-12-10 06:00:00 RST,
   or 24-02-10 09:17:00 RST,
   then `t1.clocksChangedBetween(t4)` will be true, even though `t1` and `t4` are both RST,
   because we had to switch the clocks (twice) between t1 and `t4`.
 



# General Style & Coding Tips

Below are some helpful general tips and style guidelines for all labs (they will be taken into account during grading):

- Remember to use Javadoc style conventions while commenting, wherever you have something meainingful you can express (see the sample functions for examples).
  > For reference on Javadoc style, check out [this resource](https://www.tutorialspoint.com/java/java_documentation.htm).
  > Don't worry about tags, just look at the examples at the top. Plus, use [this code example](https://pythontutor.com/java.html#code=/**%0A%20*%20Java%20program%20that%20computes%20the%20sum%20of%20the%20first%20n%20numbers%0A%20*%20%40author%20Jeova%20Farias%0A%20*%20%40version%20January%2013,%202022%0A%20*/%0Apublic%20class%20Main%20%7B%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%20%7B%0A%20%20%20%20%20%20%20%20int%20n%20%3D%2010%3B%0A%20%20%20%20%20%20%20%20int%20result%20%3D%20sumIntegers%28n%29%3B%0A%20%20%20%20%20%20%20%20System.out.println%28result%29%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Returns%20the%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*%20%40param%20n%20positive%20integer%20n%0A%20%20%20%20%20*%20%40return%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*/%0A%20%20%20%20public%20static%20int%20sumIntegers%28int%20n%29%7B%0A%20%20%20%20%20%20%20%20int%20total%20%3D%200%3B%20//%20Set%20up%20a%20variable%20for%20the%20total%0A%20%20%20%20%20%20%20%20for%20%28int%20i%20%3D%200%3B%20i%20%3C%3D%20n%3B%20i%2B%2B%29%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20total%20%2B%3D%20i%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20total%3B%0A%20%20%20%20%7D%0A%7D&cumulative=false&heapPrimitives=nevernest&mode=edit&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false)
  from class as a reference for what your style should look like.

- Write your variable and method names in camelCase, and be sure to indent properly.

- Don't forget to add comments! Include...
  > - A document header (name, date, program description)
  > - Comments at the top of each function describing what the
      >   function does
  > - Comments within the functions as needed, to describe
      >   non-obvious steps

- When working on larger programs, test as you go! That is, write
  a little of code, then make sure it works before building on it.
  Save each test that isn't essentially the same as one you've already saved,
  so that you can re-run all the previous tests each time you make a chance.
  For this lab, write and then test one method at a time.

- Focus on correctness rather than efficiency.