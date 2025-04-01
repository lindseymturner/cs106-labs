<h1 style="text-align: center;">Lab1: Introduction to Java</h1>
<h3 style="text-align: center;">Haverford CS 106 - Introduction to Data Structures</h3>
<h3 style="text-align: center;">Due: *** </h3>


# Overview

In order to make any Java program, you need to make a class. As you saw
in the Hello World Tutorial, the most basic class you can make has only
the single `public static void main` method, and is often called `Main'.

In this lab, we'll practice Java syntax by making some `public` `static`
methods in that Main class,
which is basically the Java equivalent of writing a Python function.
Almost all of the relevant techniques and ideas should be familiar to you
from your prior programming work, though sometimes Java will realize an
idea in a way that is quite different from other languages such as Python.


# Code to be written

You should write the following `public` `static` methods in the Main class,
and test them by calling them from the `main` method.
Be sure to write clear and complete comments for all of your methods,
including "docstring" comments for all methods.


1. Make a method `power(x,exp)` that takes a given number `x` and
   returns the number raised to a given exponent `exp`.
   Think carefully about the data type of the parameters.
   You may assume that `exp` is a positive integer, e.g., 1, 2, 3, etc..
   You may use either a loop or recursion, but don't use the Java library to make the problem trivial.
   Also include the following comments:
   1. As always, the docstring comment explaining the return value and any expectations of the parameters.
   2. An answer to the following question (given as a comment right after your `power` method):
      > How do Java and Python's different approaches to _type checking_ impact our confidence in
        software that's written by a team? In particular, if another programmer wrote code that included
        the expression `power(x, 3.8)` to call upon your `power` method,
        what would happen when you tried to run your Java program,
        and how would this differ from the equivalent situation in Python?
        (If you've never programmed in Python, write "I have not programmed in Python",
        and just explain what would happen in the hypothetical problematic code in Java.)
      


2. Write Java methods to compute the greatest common divisor of two positive integers,
   demonstrating the requested programming techniques.
   (Recall that "greatest common divisor", also called "greatest common denominator" or "greatest common factor",
   is the largest integer that evenly divides each of the two given integers.
   Consider 39 and 12 ... 13 and 3 and 1 evenly divide 39, and 4 and 3 and 2 and 1 evenly divide 12.
   The biggest divisor that they have in common is 3.)
   Your Java `GCD` methods should not use Java library functions,
   but instead use Java arithmetic operations to implement the GCD algorithm from Euclid
   in two approaches that should be familiar from your first semester of programming
   (if not, talk to an instructor or T.A.):

   1. Write a _recursive_ method named `GCD`, to implement Euclid's GCD algorithm.
      Recursion is a central element of the _pure functional_ programming paradigm,
      which focuses on writing a formula for what the answer _is_,
      typically in several specific cases.
      Euclid's algorithm tells us what the GCD _is_ in terms of two cases,
      which will naturally translate into a recursive function with and if/else statement:

      > 1. If the larger can be evenly divided by the smaller
           (i.e., the remainder of larger/smaller, written `larger%smaller` in Java or Python, is zero),
           then the GCD is the smaller number.
           So, since `12%3` is zero, the GCD of 12 and 3 is 3.
      > 2. Otherwise, the GCD is the same as the GCD you'd get by answering the _simpler_
           question ``what's the GCD of (a) the smaller number and (b) and the remainder
           when the larger is divided by the smaller?''.
           So, if we're asked about 39 and 12, since `39%12` isn't zero,
           we find the GCD of (a) 12, and (b) 39%12, which is 3.
           Since we saw, above, that the GCD of 12 and 3 is 3,
           that means the GCD of 39 and 13 is _also_ 3.

   2. Make a method `GCD_loop` that uses a loop _and does *not* call itself_,
      to return the greatest common denominator of two positive integers,
      once again using the algorithm from Euclid.
      Loops are a central element of the _imperative_ programming paradigm,
      which focuses on describing what to _do_ to arrive at an answer,
      often in terms of making changes to a set of variables.
      Euclid's algorithm can also be described in terms of repeatedly _doing_ certain steps,
      providing a description that can be translated into a Java loop:

      > Given two positive integers, keep replacing whichever number is
        larger by the remainder of the larger divided by the smaller.
        When you have replaced a number with zero, the other number is the GCD.
        So, for example, if we're asked to find the GCD of 39 and 12,
        since neither of them is zero, we replace the larger (39) with
        39%12, which is 3, so now we have 3 and 12.
        Since neither 3 nor 12 is zero, we replace the larger (12) with
        12 % 3, which is 0, so now we have 3 and 0.
        Since we now have a zero, the GCD of 39 and 12 is the other number, i.e., 3.


3. Make a method `round` that takes a double floating point number and
   returns the number rounded to the nearest integer. You may *not* use
   a library to do this for you: you should only use basic arithmetic
   operations and the usual program statements.
   As always, try to choose variable names carefully and use comments to ensure that your
   approach would be clear to a programmer reading your code.


4. The `RegulusTime` class in given in these starter files (and described in README-Lab1.md)
   is basically a simplified class for keeping track of date and time information,
   on a hypothetical planet where time is simple and regular (see "Notes" below, if
   you've not yet talked about programmer-defined classes in lecture).
   
   The `RegulusTime` class includes a method `minutesUntil`, which can be used to ask one
   `RegulusTime` object how many minutes it is away from some other, later, `RegulusTime`,
   if `lab1Start` and `lab2Start` are `RegulusTime` objects, we could use the expression
   `lab1Start.minutesUntil(lab2Start)` to find out how many minutes we'd have to wait, 
   after the first time, until it was the second lab start time.
   The `minutesUntil` method returns an integer, rounding off the seconds, so if two events are 57, 60, or 68 seconds
   apart, the method will return 1.

   Write a function `sameIntervals` that takes _three_ `RegulusTime` objects and tells us
   whether or not the number of minutes between the first and second is the same as the number
   of minutes between the second and third, to the nearest minute.
   Note that there may be several ways to interpret this question;
   you may choose any reasonable interpretation,
   but if you can identify more than one, provide
   a comment giving a brief explanation of the options and your choice among them.



# Notes for this Lab


- For the power function, you are welcome to use _either_ the algorithm
  which does about `exp` multiplications,
  or, if you wish, the algorithm for which the number of multiplications
  grows with the logarithm of `exp` (which grows roughly with the number of digits,
  not the value, of `exp`).
  - but, either way, you'll be graded on _correctness_ and _clarity_,
    so don't get fancy unless you are confident you can get it right!

- You may have heard a bit about programmer-defined classes in lecture, but if not,
  or if you want a review, here's the basic idea:

   Real software includes not just built-in types like integers, strings, lists, etc.,
   but also programmer-defined types that relate to the problem being solved by the software.
   Java's `class` keyword will be our primary tool for creating programmer-defined types,
   in this course, and you'll begin by writing a variant on the programmer-defined type
   `RegulusTime` that's given in these starter files (and described in README-Lab1.md;
   it's basically a simplified class for keeping track of date and time information,
   on a hypothetical planet where time is simple and regular).
   We work with class-type objects primarily by applying _methods to an object_:
   if you've used Python's `sort` method on a list, you've seen the notation:

   > `>>> myNumbers:List[float] = [20, 6, 71, 13.2, 55]`

   > `>>> myNumbers`

   > `[20, 6, 71, 13.2, 55]`

   > `>>> myNumbers.sort()`

   > `>>> myNumbers`

   > `[6, 13.2, 20, 55, 71]`

   The expression `myNumbers.sort()` applying the `sort` method to the `myNumbers` list;
   this is also called _sending `myNumbers` the `sort` message_
   (the exact relationship between _messages_ and _methods_ will be discussed later).


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