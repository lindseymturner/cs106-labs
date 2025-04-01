"""
CMSC 106 will be taught in Java, but most of you have prior experience with Python,
so this initial lab has some things written in both languages, to help you translate.
"""


def diffSlow(x: int, y: int) -> int:
    """
    Find the numeric difference (x-y or y-x, whichever is non-negative),
     but without actually subtracting anything other than 1,
     i.e., doing it the slow way.

    This code demonstrates loops, local variables, and if-else.

    :return:  abs(x-y), done the hard way

    :examples:
    >>> diffSlow(10, 15)
    5
    >>> diffSlow(15, 10)
    5
    >>> diffSlow(10, 10)
    0
    """

    # first, identify the maximum and minimum of (x,y)
    if x > y:
        max: int = x
        min: int = y
    else:
        max: int = y
        min: int = x

    difference: int = 0
    while max != min:   # let's hope they're integers
        max = max-1
        difference += 1   # abbreviation for difference = difference+1

    return difference


def intQuotientSlow(x: int, y: int) -> int:
    """
    Find the integer part of the quotient x/y,
     assuming both are positive and y!=0,
     but without actually dividing,
     i.e., doing it the slow way via subtraction.

    Demonstrates recursion and also raising exceptions.

    :param x: a non-negative integer
    :param y: a strictly positive integer
    :return:  x//y, done the hard way

    :examples:
    >>> intQuotientSlow(50, 10)
    5
    >>> intQuotientSlow(59, 10)
    5
    >>> intQuotientSlow(9, 10)
    0
    >>> try:
    ...     intQuotientSlow(50, -10)
    ...     print("Huh, should not get here!")
    ... except:
    ...     print("Good, that should throw an exception.")
    Good, that should throw an exception.
    """

    # we only plan to handle the case of positive numbers,
    #  throw an exception if not:
    if x < 0 or y <= 0:
        raise ValueError("intQuotientSlow(x, y) requires x>0, y>=0")

    # if x is smaller, the quotient is zero, we're done:
    if x < y:
        return 0
    else:
        smaller_x: int = x-y
        smaller_iq: int = intQuotientSlow(smaller_x, y)
        my_iq: int = 1+smaller_iq
        return my_iq
        # Equivalent shorter version is less illuminating in debugger:
        #   return 1+intQuotientSlow(x-y, y)
        # Note that refactoring can transform the former into the latter,
        #  so we can debug it like this, and then smoosh it down without
        #  breaking it (assuming no bugs in the refactorer).

