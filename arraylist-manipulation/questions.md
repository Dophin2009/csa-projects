# ArrayList Manipulation Questions

1.  List three benefits of using an `ArrayList` over an `Array`.

    Unlike `Array` objects, `ArrayList` objects can be easily modified
    in terms of size. The size of an `ArrayList` can be expanded
    dynamically, whereas the size of an `Array` cannot be changed. New
    elements can be appended to the end of an `ArrayList` easily.
    Removal and insertion of elements is also easy. Java's core
    libraries already provide methods for these processes.

2.  What is a limitation of the `ArrayList` class? When is using an
    `Array` better than using an `ArrayList`?

    An `Array` is much more memory efficient than an `ArrayList`, which
    is more taxing on the system and negatively impacts runtime. For
    static or small data sets, it is better to use an `Array`.

3.  What interface does the `ArrayList` class implement?

    The interface `List` is implemented.

4.  What is the difference between `arr.size()` and `arr.length`?

    `arr.size()` implies that `arr` references an `ArrayList` object,
    whereas `arr.length` implies that `arr` is referencing an `Array`
    object.

5.  When would it be appropriate to use `set(int index, E element)`
    versus `add(int index, E element)`?

    If one wishes to simply replace an element in the `ArrayList` at a
    specific index, the `set()` method should be used. `add()` inserts a
    new element at the specified index and increments the indices of all
    elements to the right by one.

6.  There are two `add()` methods for an `ArrayList` (single parameter
    versus double parameter). When would you want to use the one
    parameter method versus the two paramter method?

    `add(E element)` is used to append the `ArrayList` with the given
    element, whereas `add(int index, E element)` inserts a new element
    at the specified index and shifts all elements at and to the right
    of that index.

7.  You are given the following code. What will the `ArrayList` display
    in the console?

    ``` java
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    numbers.add(new Integer(3));
    numbers.add(new Integer(4));
    numbers.add(0, new Integer(5));
    ```

    The following will be displayed: `[3, 4, 5]`

8.  What three `ArrayList` methods could you use to access the element
    at index 2?

    ``` java
    numbers.get(2);
    numbers.remove(2);
    numbers.set(2);
    ```
