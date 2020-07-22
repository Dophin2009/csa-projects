# APCSA, Unit 1 - Complex Number Questions

1.  What are the pieces of data that an object remembers about itself
    called?

    *These pieces of data are called fields.*

2.  Which visibility modifier is usually used for the pieces of data
    mentioned in question 1? Why?

    *The visibility modifier* `private` *is usually used for fields.
    This is an object-oriented programming concept known as
    encapsulation. Instance variables are hidden from other classes and
    can only be accessed via getters/accessors and setters/mutators.*

3.  From the point of view of someone implementing a constructor, what
    does a constructor do?

    *Constructors are used to initialize instance variables.*

4.  From the point of view of someone using a given class, what does a
    constructor do?

    *Constructors are called when objects are created. For example:*
    `java  Object obj = new Object();` *The* `Object()` *constructor is
    called to create a new instance of* `Object`*.*

5.  Which method(s) exhibited overriding in `ComplexNumber`? Explain.

    *The method* `equals()` *of the* `Object` *class was overridden in*
    `Complex Number`*. In the* `Object` *class, it tests if the two
    references are pointing to the same object, while the overridden
    method tests if the two* `ComplexNumber` *objects have the same
    value.*

    `toString()` *from the* `Object` *class was also overridden. The
    overridden method simply returned a String of the object's id,
    whereas the new method in* `ComplexNumber` *returns in a String the
    object's value in* **a+bi** *form.*

6.  Which method(s) exhibited overloading in `ComplexNumber`? Explain.

    *The methods* `times()`*,* `dividedBy()`*, and* `pow()` *were
    overloaded. Each of these has one version with a* `double n` *or*
    `x` *parameter and another version with a* `ComplexNumber`
    *parameter.*

7.  Write a section of code that will create two `ComplexNumber`
    objects, multiply them, and print the result of the multiplication
    operation.

    ``` java
    ComplexNumber complex1 = new ComplexNumber(1, 2);
    ComplexNumber complex2 = new ComplexNumber(-3, 5);
    System.out.println(complex1.times(complex2));
    ```

8.  Write a section of code that will create a `ComplexNumber` object
    and print the modulus of that `ComplexNumber`.

    ``` java
    System.out.println(new ComplexNumber(1, 3).getModulus());
    ```
