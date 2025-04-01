<h1 style="text-align: center;">Lab 5: Postfix and Infix</h1>
<h3 style="text-align: center;">Haverford CS 106 - Introduction to Data Structures</h3>
<h3 style="text-align: center;">Due: ** </h3>


# Overview

In this assignment, you'll have the chance to work with stacks and queues in a real application. The goal will be to
implement algorithms to read infix and postfix mathematical expressions. This lab is also a great opportunity to
learn and use the debugging tools from this IDE. When coding, you can  set _debugging breakpoints_ in your code
(double click to the left of the line that is causing issues). Then click the “bug” icon to run it in debugging mode. Make
sure to contact the instructor or the TA if you are not sure of how to use the debugging tools of this IDE.


# Postfix and Infix expressions
When writing mathematical expressions, one can either use the postfix or the infix notation.

## 1. Infix notation
The most common of those is the infix notation, where the operators show up in between the operands. For example, in
> 1 + 2 * 3 - 4 ^ 2

The operators `*`, `+`, `-` and `^` show up in between the two numbers they are operating on. In this notation, we also
have a notion of _precedence_, such that the power operation precedes the multiplication, which precedes the addition.
In the following table you have the precedence table of the operators we will use in this lab (its precedence values are
arbitrary, what really matters is their order):

| Symbol |  Precedence   | Value |
|--------|:-------------:|:-----:|
| `^`    |     High      |  10   |
| `/`    | Less than `^` |   5   |
| `*`    | Equal of `/`  |   5   |
| `+`    |      Low      |   0   |
| `-`    | Equal of `+`  |   0   |

In order to change the precedence of certain parts of our expression, we use parentheses, such that in expressions like
> (1 + 2) * (3 - 4) ^ 2

the operations of addition and subtraction take more precedence than the multiplication and power operations close to them.

## 2. Postfix
In postfix (also called reverse polish) notation, the operator is added after the two operands. Therefore, the operation in
> 1 2 +

results in 1+2=3. And the expression in
> 3 10 2 - 5 * +

evaluates to 3+(10–2)*5=43. Notice that in this notation, the use of parenthesis and the precedence table becomes unnecessary.

## 3. Using stacks and queues in infix and postfix expressions
In order to parse an infix expression, the common strategy is to first "convert" it to postfix notation. This is accomplished
via an algorithm called **Shunting-Yard**, which makes use of stacks and queues. Once a postfix expression is found, we can then
use a stack to read it and to compute the mathematical expressions in it. For more details on the Shunting-Yard algorithm and
on how to read postfix expressions, you can go [here](https://aquarchitect.github.io/swift-algorithm-club/Shunting%20Yard/)
or [here](https://brilliant.org/wiki/shunting-yard-algorithm/).

# Classes to be Implemented

You are given the `Stack` and `Queue` interfaces and one of your goals will be to create a Linked Stack and a
Linked Queue data structures that implement them. After that, the goal is to read an infix expression from the
command line arguments and output its result using the stack and queue classes created here to implement the Shunting-Yard
algorithm and to read a postfix expression.

## 1. `Node` and `SinglyLinkedList` classes
These are the classes we implemented in previous lectures. They will give support to our LinkedStack and LinkedQueue
classes. Feel free to use the implementations you found in the lecture slides or in the book.

## 2. ` LinkedStack`  and  `LinkedQueue` classes
In the `LinkedStack` and `LinkedQueue` classes, you should implement the methods from the `Stack`  and `Queue` interfaces
using a **Singly Linked List** data structure. . In this lab, both the **linked stack and the linked queue classes should
be generic**. Feel free to use the implementations you found in the lecture slides or in the book.

OBS.: Feel free to implement the Stack and Queue classes using arrays. In that case, just make sure to create a constructor for that
class such that it limits the number of elements in both data structures to a given number.

## 3. ` Main` class
In here you'll read an infix expression as a command line argument and then output its result. To that goal, you'll have
to implement the shunting yard algorithm and a method to compute a postfix computation using stacks. For simplicity,
you can assume all the numbers you'll encounter are doubles. In your `Main` class you are required to implement the
following methods:
1. `shuntingYard(String[] stringArray)`: This method receives a string array from the command line arguments representing
   an infix expression and parses it to a postfix notation. If that array contains a string that is not an operator (`+`, `-`, `/`,`*` or
   `^`) or number or a parenthesis (`(` or `)`), this method should throw an `IllegalArgumentException`. Finally, it should return
   a `LinkedQueue<String>`: that contains the expression in postfix notation.
2. `postFix(LinkedQueue<String> stringQueue)`: This method should read a string queue that contains a postfix expression
   and return the result of evaluating that expression.

In order to simplify your code, you should also implement the following methods:
1. `isNumeric(String strNum)`: checks if a string is a number.
2. `isOperator(String strOp)`: checks if a string is an operator (`+`, `-`, `/`,`*` or `^`).
3. `isParenthesis(String str)`: checks if a string is an opening or closing parenthesis.
4. `precedence(String operator)`: returns an integer that represents the precedence of the input (string) operator.

**OBS**.: For this program to run well, we are assuming that each token in our mathematical expression is separated by a space
in the command line arguments. Therefore, you can assume the user won't provide `(1+2)/3*4` or `(1 + 2) / 3 * 4` as
arguments, but they will type `( 1 + 2 ) / 3 * 4`.


# Output

Here are some examples of input/output for this lab:

### First example
Command line argument:
```
1 + 1 + 1 + 1 + 1 
```
Output:
```
Input expression: 1 + 1 + 1 + 1 + 1 
Result: 5.0
```

### Second example
Command line argument:
```
7 + 5 * ( 8 - 5 )
```
Output:
```
Input expression: 7 + 5 * ( 8 - 5 ) 
Result: 22.0
```

### Third example
Command line argument:
```
4 + 2 / ( 9 - 8 ) ^ 4 ^ 2
```
Output:
```
Input expression: 4 + 2 / ( 9 - 8 ) ^ 4 ^ 2 
Result: 6.0
```

### Fourth example
Command line argument:
```
Abby + 2
```
Output (some details below may be different depending on your implementation):
```
Exception in thread "main" java.lang.IllegalArgumentException: Illegal Argument
	at Main.shuntingYard(Main.java:45)
	at Main.main(Main.java:6)
```

# Notes for Lab 5

- Use `.equals()` when comparing strings.  For this lab, you do not need to overwrite the `.equals()` method for lists
  or nodes, you can use `String` to compare two strings directly.
- Consult the [documentation](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html) to learn about
  the`.compareTo()` method for strings in Java.

# General Style & Coding Tips

Below are some helpful general tips and style guidelines for all labs (they will be taken into account during grading):

-   Remember to use Javadoc style conventions while commenting.
    > For reference on Javadoc style, check out [this resource](https://www.tutorialspoint.com/java/java_documentation.htm).
    > Don't worry about tags, just look at the examples at the top. Plus, use [this code example](https://pythontutor.com/java.html#code=/**%0A%20*%20Java%20program%20that%20computes%20the%20sum%20of%20the%20first%20n%20numbers%0A%20*%20%40author%20Jeova%20Farias%0A%20*%20%40version%20January%2013,%202022%0A%20*/%0Apublic%20class%20Main%20%7B%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%20%7B%0A%20%20%20%20%20%20%20%20int%20n%20%3D%2010%3B%0A%20%20%20%20%20%20%20%20int%20result%20%3D%20sumIntegers%28n%29%3B%0A%20%20%20%20%20%20%20%20System.out.println%28result%29%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20/**%0A%20%20%20%20%20*%20Returns%20the%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*%20%40param%20n%20positive%20integer%20n%0A%20%20%20%20%20*%20%40return%20sum%20of%20the%20first%20n%20integers%0A%20%20%20%20%20*/%0A%20%20%20%20public%20static%20int%20sumIntegers%28int%20n%29%7B%0A%20%20%20%20%20%20%20%20int%20total%20%3D%200%3B%20//%20Set%20up%20a%20variable%20for%20the%20total%0A%20%20%20%20%20%20%20%20for%20%28int%20i%20%3D%200%3B%20i%20%3C%3D%20n%3B%20i%2B%2B%29%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20total%20%2B%3D%20i%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20return%20total%3B%0A%20%20%20%20%7D%0A%7D&cumulative=false&heapPrimitives=nevernest&mode=edit&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false)
    from class as a reference for what your style should look like.

-   Write your variables in CamelCase, and be sure to indent properly.

-   Don't forget to add comments! Include...
    > - A document header (name, date, program description)
    > - Comments at the top of each function describing what the
        >   function does
    > - Comments within the functions as needed, to describe
        >   non-obvious steps

-   When working on larger programs, test as you go! That is, write
    a little of code, then make sure it works before building on it.

-   Focus on correctness rather than efficiency.