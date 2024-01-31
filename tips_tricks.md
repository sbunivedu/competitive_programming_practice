# Tips and Tricks

## `Arrays.toString()`
Arrays can be printed without a loop as follows. Sometimes it is useful to explode a String into an array of chars.
```java
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    char[] char_array = new char[]{'a', 'b', 'c'};
    System.out.println(Arrays.toString(char_array));

    char_array = "hello".toCharArray();
    System.out.println(Arrays.toString(char_array));
  }
}
```

## `Arrays.sort()`
Arrays can be sorted easily with one method call:
```java
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    int[] int_array = {1, 3, 2, 5, 4};
    System.out.println("The original array is: "+Arrays.toString(int_array));
    Arrays.sort(int_array);
    System.out.println("The sorted array is: "+Arrays.toString(int_array));

    String[] str_array = { "c", "a", "b"};
    System.out.println("The original array is: "+Arrays.toString(str_array));
    Arrays.sort(str_array);
    System.out.println("The sorted array is: "+Arrays.toString(str_array));
  }
}
```

## Enhanced for loop (for each) for array

```java
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    int[] int_array = {1, 2, 3};
    for(int n : int_array) {
      System.out.println(n);
    }
  }
}
```
