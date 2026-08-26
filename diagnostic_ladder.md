# Programming Proficiency Assessment

## Purpose

This assessment is designed determine each student's current programming ability before introducing competitive programming techniques.

Students begin with basic problems and progressively encounter:

1. Java syntax and control flow
2. Methods and decomposition
3. Arrays and strings
4. Problem-solving with data structures
5. Algorithmic reasoning
6. Recursion
7. Searching and sorting
8. Complexity analysis
9. More challenging algorithmic problems

# Recommended Assessment Strategy

Use the following progression:

```text
Level 1
   ↓
Level 2
   ↓
Level 3
   ↓
Level 4
   ↓
Level 5
   ↓
Level 6
   ↓
Level 7
```

A student should move to the next level when they can solve approximately **70–80% of the current level independently**.

Please pay attention not only to whether the final answer is correct, but also to:

* Code organization
* Variable naming
* Ability to decompose a problem
* Debugging ability
* Handling edge cases
* Algorithm selection
* Complexity awareness

# Level 1 — Basic Programming

### Purpose

Determine whether the student is comfortable with:

* Variables
* Arithmetic
* `if`
* `for`
* `while`
* Basic input/output
* Simple methods

These problems should feel easy to a student who successfully completed CS1.

## Problem 1 — Hello, Student

Write a program that asks the user for their name and age and prints:

```text
Hello, Alice!
You are 20 years old.
```

<details>
<summary>solution</summary>

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String name = input.next();
        int age = input.nextInt();

        System.out.println("Hello, " + name + "!");
        System.out.println("You are " + age + " years old.");
    }
}
```
</details>

# Problem 2 — Even or Odd

Given an integer `n`, print:

```text
Even
```

if `n` is even and

```text
Odd
```

otherwise.

### Example

```text
Input:
17

Output:
Odd
```

<details>
<summary>solution</summary>

```java
public static String evenOrOdd(int n) {
    if (n % 2 == 0)
        return "Even";
    else
        return "Odd";
}
```
</details>


### Skills

* Modulus
* Conditional statements
* Boolean reasoning

# Problem 3 — Sum from 1 to N

Write a method:

```java
public static int sum(int n)
```

that returns:

$$
1+2+3+\cdots+n
$$

### Example

```text
sum(5) → 15
sum(10) → 55
```

<details>
<summary>solution</summary>

```java
public static int sum(int n) {
    int total = 0;

    for (int i = 1; i <= n; i++) {
        total += i;
    }

    return total;
}
```
</details>

### Follow-up

> Can you solve this without a loop?

Expected answer:

```java
public static int sum(int n) {
    return n * (n + 1) / 2;
}
```
**different correct algorithms can have different complexity**

# Problem 4 — Count Digits

Write:

```java
public static int countDigits(int n)
```

that returns the number of digits in a positive integer.

### Example

```text
countDigits(58291) → 5
countDigits(7) → 1
```

<details>
<summary>solution</summary>

```java
public static int countDigits(int n) {
    int count = 0;

    while (n > 0) {
        n /= 10;
        count++;
    }

    return count;
}
```
</details>

### Important Edge Case

Ask:

> What should happen for `n = 0`?

<details>
<summary>solution</summary>

```java
public static int countDigits(int n) {
    if (n == 0)
        return 1;

    int count = 0;

    while (n > 0) {
        n /= 10;
        count++;
    }

    return count;
}
```
</details>

# Level 1 Interpretation

A student who struggles significantly with Problems 1–4 probably needs:

* Java syntax review
* Variables and expressions
* Conditional statements
* Loops
* Methods
* Basic debugging

# Level 2 — Basic Problem Solving

These problems require students to combine several CS1 concepts.

# Problem 5 — Maximum of Three

Write:

```java
public static int max3(int a, int b, int c)
```

that returns the largest value.

<details>
<summary>solution</summary>

```java
public static int max3(int a, int b, int c) {
    int max = a;

    if (b > max)
        max = b;

    if (c > max)
        max = c;

    return max;
}
```
</details>

### Follow-up

Ask:

> What happens if two or three values are equal?

This tests whether students naturally think about boundary cases.


# Problem 6 — Reverse Digits

Write:

```java
public static int reverse(int n)
```

that reverses the digits.

### Example

```text
reverse(12345) → 54321
reverse(701) → 107
```

<details>
<summary>solution</summary>

```java
public static int reverse(int n) {
    int result = 0;

    while (n > 0) {
        int digit = n % 10;
        result = result * 10 + digit;
        n /= 10;
    }

    return result;
}
```
</details>

A student must understand:

* Modulus
* Integer division
* Loops
* Maintaining an accumulator
* Building a result incrementally

# Problem 7 — Count Multiples

Given integers `n` and `k`, count how many integers from `1` through `n` are divisible by `k`.

### Example

```text
n = 20
k = 3

Answer = 6
```

because:

```text
3 6 9 12 15 18
```

<details>
<summary>solution</summary>

```java
public static int countMultiples(int n, int k) {
    int count = 0;

    for (int i = 1; i <= n; i++) {
        if (i % k == 0)
            count++;
    }

    return count;
}
```
</details>

<details>
<summary>solution</summary>

```java
public static int countMultiples(int n, int k) {
    return n / k;
}
```
</details>

This is a useful diagnostic distinction.

# Problem 8 — Prime Number

Write:

```java
public static boolean isPrime(int n)
```

that determines whether `n` is prime.

<details>
<summary>solution</summary>

```java
public static boolean isPrime(int n) {
    if (n < 2)
        return false;

    for (int i = 2; i < n; i++) {
        if (n % i == 0)
            return false;
    }

    return true;
}
```
</details>

A stronger student should recognize that it is sufficient to test divisors through √n.

<details>
<summary>better solution</summary>

```java
public static boolean isPrime(int n) {
    if (n < 2)
        return false;

    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0)
            return false;
    }

    return true;
}
```
</details>

### Diagnostic Value

This is an excellent transition problem.

Ask:

> Why is `i * i <= n` sufficient?

<details>
<summary>answer</summary>

Because if $n$ is composite, it must have a factor no greater than $\sqrt n$.
Suppose

$n = a \times b$

where $a$ and $b$ are integers greater than 1. If both $a$ and $b$ were greater than $\sqrt n$, then

$a > \sqrt n,\qquad b > \sqrt n$

so

$ab > \sqrt n \times \sqrt n = n,$

which contradicts $ab=n$.

Therefore, at least one factor must satisfy

$a \leq \sqrt n$.
</details>

If the student can explain this, they are beginning to think algorithmically rather than merely syntactically.

# Level 2 Interpretation

Students who successfully solve Levels 1–2 are generally ready for:

* Arrays
* Strings
* Searching
* Sorting
* Basic recursion
* Basic Big-O analysis

# Level 3 — Arrays and Strings

This should be a major CS2 readiness checkpoint.

# Problem 9 — Array Sum

Write:

```java
public static int sum(int[] a)
```

<details>
<summary>solution</summary>

```java
public static int sum(int[] a) {
    int total = 0;

    for (int x : a) {
        total += x;
    }

    return total;
}
```
</details>

# Problem 10 — Array Maximum

Write:

```java
public static int max(int[] a)
```

<details>
<summary>solution</summary>

```java
public static int max(int[] a) {
    int max = a[0];

    for (int i = 1; i < a.length; i++) {
        if (a[i] > max)
            max = a[i];
    }

    return max;
}
```
</details>

### Follow-up

Ask:

> Why shouldn't we initialize `max` to 0?

<details>
<summary>answer</summary>

A good student should identify:

```text
[-8, -3, -10]
```

as a counterexample.

</details>

# Problem 11 — Linear Search

Write:

```java
public static int search(int[] a, int target)
```

Return the index of `target`, or `-1` if it does not occur.

<details>
<summary>solution</summary>


```java
public static int search(int[] a, int target) {
    for (int i = 0; i < a.length; i++) {
        if (a[i] == target)
            return i;
    }

    return -1;
}
```
</details>


### Follow-up

Ask:

> What is the worst-case time complexity?

<details>
<summary>answer</summary>

$O(n)$
</details>

# Problem 12 — Count Vowels

Write:

```java
public static int countVowels(String s)
```

<details>
<summary>solution</summary>

```java
public static int countVowels(String s) {
    int count = 0;

    for (int i = 0; i < s.length(); i++) {
        char c = Character.toLowerCase(s.charAt(i));

        if (c == 'a' || c == 'e' || c == 'i' ||
            c == 'o' || c == 'u') {
            count++;
        }
    }

    return count;
}
```
</details>


# Problem 13 — Palindrome

Write:

```java
public static boolean isPalindrome(String s)
```

### Example

```text
"racecar" → true
"hello"   → false
```

<details>
<summary>solution</summary>

```java
public static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        if (s.charAt(left) != s.charAt(right))
            return false;

        left++;
        right--;
    }

    return true;
}
```
</details>

### Diagnostic Significance

This is the first problem where I would look for a student's ability to discover an algorithm rather than simply translate instructions into code.

# Problem 14 — Second Largest

Given an integer array, return the second-largest **distinct** value.

### Example

```text
[10, 4, 7, 10, 3]

Answer: 7
```

<details>
<summary>solution</summary>

```java
public static int secondLargest(int[] a) {
    int largest = Integer.MIN_VALUE;
    int second = Integer.MIN_VALUE;

    for (int x : a) {
        if (x > largest) {
            second = largest;
            largest = x;
        }
        else if (x > second && x != largest) {
            second = x;
        }
    }

    return second;
}
```
</details>


### Follow-up

Ask students what should happen if there is no second distinct value.

This reveals whether they think carefully about specifications.

# Level 3 Interpretation

Students who can comfortably solve Level 3 should be ready to begin competitive-programming fundamentals.

Recommended topics:

```text
Big-O
Sorting
Binary Search
Two Pointers
Prefix Sums
Frequency Counting
Stacks and Queues
Basic Recursion
```

# Level 4 — Algorithmic Thinking

At this point, stop emphasizing syntax.

The questions should ask students to find a more efficient approach.

# Problem 15 — Duplicate Detection

Given an integer array, determine whether any value occurs more than once.

### Example

```text
[3, 7, 2, 3, 9] → true

[1, 4, 7, 9] → false
```

<details>
<summary>Solution Using Nested Loops</summary>

```java
public static boolean hasDuplicate(int[] a) {
    for (int i = 0; i < a.length; i++) {
        for (int j = i + 1; j < a.length; j++) {
            if (a[i] == a[j])
                return true;
        }
    }

    return false;
}
```
</details>


Complexity: $O(n^2)$

### 
<details>
<summary>Better Solution</summary>

```java
import java.util.HashSet;

public static boolean hasDuplicate(int[] a) {
    HashSet<Integer> seen = new HashSet<>();

    for (int x : a) {
        if (!seen.add(x))
            return true;
    }

    return false;
}
```
</details>

Complexity: $O(n)$ on average.

### Diagnostic Question

Ask:

> Which solution would you choose if `n = 1,000,000`?

This tests whether students understand why algorithms matter.


# Problem 16 — Two Sum

Given an array and a target, determine whether two different elements add up to the target.

### Example

```text
a = [2, 7, 11, 15]
target = 9

Answer: true
```

<details>
<summary>Simple Solution</summary>

```java
public static boolean twoSum(int[] a, int target) {
    for (int i = 0; i < a.length; i++) {
        for (int j = i + 1; j < a.length; j++) {
            if (a[i] + a[j] == target)
                return true;
        }
    }

    return false;
}
```
</details>

<details>
<summary>Better Solution</summary>

```java
import java.util.HashSet;

public static boolean twoSum(int[] a, int target) {
    HashSet<Integer> seen = new HashSet<>();

    for (int x : a) {
        if (seen.contains(target - x))
            return true;

        seen.add(x);
    }

    return false;
}
```
</details>

Average complexity: $O(n)$


# Problem 17 — Two-Pointer Pair Sum

Assume the array is sorted.

Determine whether two values sum to `target`.

### Example

```text
[1, 2, 4, 7, 11, 15]
target = 15

Answer: true
```

<details>
<summary>Solution</summary>

```java
public static boolean pairSum(int[] a, int target) {
    int left = 0;
    int right = a.length - 1;

    while (left < right) {
        int sum = a[left] + a[right];

        if (sum == target)
            return true;

        if (sum < target)
            left++;
        else
            right--;
    }

    return false;
}
```
</details>

Complexity: $O(n)$

### Key Diagnostic Question

Ask:

> Why can we safely move `left` or `right`?

Students who can explain this are demonstrating algorithmic reasoning.


# Problem 18 — Prefix Sum

Given an array, answer many range-sum queries.

For example:

```text
a = [2, 4, 1, 7, 3]
```

Query:

```text
sum(1, 3)
```

should return:

```text
4 + 1 + 7 = 12
```

Suppose there are 100,000 queries.

### Naive Approach

Loop through the range for every query.

Complexity: $O(nq)$

### Better Solution

Construct prefix sums:

```java
public static int[] prefixSum(int[] a) {
    int[] p = new int[a.length + 1];

    for (int i = 0; i < a.length; i++) {
        p[i + 1] = p[i] + a[i];
    }

    return p;
}
```

Then:

```java
public static int rangeSum(int[] p, int left, int right) {
    return p[right + 1] - p[left];
}
```

Preprocessing: $O(n)$

Each query: $O(1)$

This is an important competitive-programming pattern.


# Level 4 Interpretation

Students who reach this level are ready for:

```text
Sorting
Binary Search
Hashing
Two Pointers
Sliding Window
Prefix Sums
Greedy Algorithms
Basic Graph Traversal
```

They should not yet be required to study advanced DP or segment trees.


# Level 5 — Recursion and Backtracking

These problems identify students who are ready for more sophisticated algorithmic thinking.

# Problem 19 — Recursive Factorial

Write:

```java
public static long factorial(int n)
```

recursively.

<details>
<summary>Solution</summary>

```java
public static long factorial(int n) {
    if (n <= 1)
        return 1;

    return n * factorial(n - 1);
}
```
</details>

# Problem 20 — Recursive Sum

Write:

```java
public static int sum(int[] a, int index)
```

that recursively calculates the sum of an array starting at `index`.

<details>
<summary>Solution</summary>

```java
public static int sum(int[] a, int index) {
    if (index == a.length)
        return 0;

    return a[index] + sum(a, index + 1);
}
```
</details>


# Problem 21 — Generate Binary Strings

Given `n`, print all binary strings of length `n`.

For `n = 3`:

```text
000
001
010
011
100
101
110
111
```

<details>
<summary>Solution</summary>

```java
public static void generate(String current, int n) {
    if (current.length() == n) {
        System.out.println(current);
        return;
    }

    generate(current + "0", n);
    generate(current + "1", n);
}
```
</details>

### Key Concept

The number of solutions is:$2^n$

Students should recognize that exponential growth quickly becomes expensive.

# Problem 22 — Generate Permutations

Given a string containing distinct characters, print all permutations.

For:

```text
ABC
```

possible output:

```text
ABC
ACB
BAC
BCA
CAB
CBA
```

<details>
<summary>Solution</summary>

```java
public static void permutations(String remaining, String result) {
    if (remaining.length() == 0) {
        System.out.println(result);
        return;
    }

    for (int i = 0; i < remaining.length(); i++) {
        char c = remaining.charAt(i);

        String next =
            remaining.substring(0, i) +
            remaining.substring(i + 1);

        permutations(next, result + c);
    }
}
```
</details>

### Complexity

Approximately: $O(n!)$

This problem is useful for identifying students who are ready to study backtracking.


# Level 5 Interpretation

Students who reach this level are ready for:

* Backtracking
* Divide and conquer
* More advanced recursion
* Binary search
* Greedy algorithms
* Introductory dynamic programming

# Level 6 — Intermediate Algorithms

Now the problems begin to resemble genuine competitive-programming problems.

# Problem 23 — Binary Search

Given a sorted array, find the index of a target.

<details>
<summary>Solution</summary>

```java
public static int binarySearch(int[] a, int target) {
    int left = 0;
    int right = a.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (a[mid] == target)
            return mid;

        if (a[mid] < target)
            left = mid + 1;
        else
            right = mid - 1;
    }

    return -1;
}
```
</details>


### Questions

1. Why must the array be sorted?
2. Why do we use `left + (right-left)/2`?
3. What is the complexity?

Expected: $O(\log n)$

# Problem 24 — Maximum Subarray

Given an integer array, find the largest possible sum of a contiguous subarray.

### Example

```text
[-2, 1, -3, 4, -1, 2, 1, -5, 4]

Answer: 6
```

because:

```text
4 + (-1) + 2 + 1 = 6
```

<details>
<summary>Solution — Kadane's Algorithm</summary>

```java
public static int maxSubarray(int[] a) {
    int current = a[0];
    int best = a[0];

    for (int i = 1; i < a.length; i++) {
        current = Math.max(a[i], current + a[i]);
        best = Math.max(best, current);
    }

    return best;
}
```
</details>

Complexity: $O(n)$

### Diagnostic Value

This is a very good dividing line.

A student who can independently derive or understand this solution is beginning to think at the level expected in competitive programming.

# Problem 25 — Coin Change: Minimum Coins

Given coin denominations and a target amount, determine the minimum number of coins required.

For example:

```text
coins = [1, 3, 4]
amount = 6
```

Answer:

```text
2
```

because:

```text
3 + 3
```

<details>
<summary>Dynamic Programming Solution</summary>

```java
public static int minCoins(int[] coins, int amount) {
    int[] dp = new int[amount + 1];

    Arrays.fill(dp, amount + 1);
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (coin <= i) {
                dp[i] = Math.min(
                    dp[i],
                    dp[i - coin] + 1
                );
            }
        }
    }

    return dp[amount] > amount ? -1 : dp[amount];
}
```
</details>

Complexity: $O(n \times amount)$

### Key Question

Ask:

> What does `dp[i]` mean?

A student who can clearly answer this is demonstrating an important DP skill: **state definition**.


# Problem 26 — Climbing Stairs

You can climb either 1 or 2 steps at a time.

How many distinct ways are there to climb `n` stairs?

### Example

```text
n = 4

Answer = 5
```

The possibilities are:

```text
1+1+1+1
1+1+2
1+2+1
2+1+1
2+2
```

<details>
<summary>Solution</summary>

```java
public static long climb(int n) {
    if (n <= 1)
        return 1;

    long[] dp = new long[n + 1];

    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];
}
```
</details>

This is an excellent first DP problem.

# Level 6 Interpretation

Students at this level are ready for:

```text
Dynamic Programming
Greedy Algorithms
Graph Algorithms
Binary Search on Answer
More sophisticated data structures
```

# Level 7 — Advanced Diagnostic Problems

These problems are primarily intended to identify students who could move quickly into serious competitive-programming training.

# Problem 27 — Number of Islands

Given a grid containing `'1'` for land and `'0'` for water, count the number of connected islands.

Example:

```text
11000
11000
00100
00011
```

Answer:

```text
3
```

<details>
<summary>Solution</summary>

```java
public static int numIslands(char[][] grid) {
    int count = 0;

    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[0].length; c++) {

            if (grid[r][c] == '1') {
                count++;
                floodFill(grid, r, c);
            }
        }
    }

    return count;
}

private static void floodFill(
        char[][] grid, int r, int c) {

    if (r < 0 || r >= grid.length ||
        c < 0 || c >= grid[0].length ||
        grid[r][c] != '1') {
        return;
    }

    grid[r][c] = '0';

    floodFill(grid, r + 1, c);
    floodFill(grid, r - 1, c);
    floodFill(grid, r, c + 1);
    floodFill(grid, r, c - 1);
}
```
</details>


### Concepts

* 2D arrays
* Recursion
* DFS
* Graph modeling

This is the point where ordinary CS1 programming begins turning into algorithmic problem solving.

# Problem 28 — Shortest Path in a Grid

Given a grid containing open cells and blocked cells, find the shortest path from the upper-left corner to the lower-right corner.

Students should first attempt the problem without being told which algorithm to use.

### Expected Insight

This is an **unweighted shortest-path problem**.

Therefore:

> BFS

is appropriate.

### Basic BFS Structure

```java
Queue<int[]> queue = new LinkedList<>();

queue.add(new int[]{0, 0});

while (!queue.isEmpty()) {
    int[] current = queue.remove();

    // examine neighboring cells
}
```

A student who independently identifies BFS is demonstrating strong algorithm recognition.


# Problem 29 — Connected Components

Given an undirected graph, count the number of connected components.

### Expected Algorithm

DFS or BFS.

### Complexity

$$
O(V+E)
$$

### Diagnostic Question

Ask:

> Why isn't it necessary to start a new DFS from every vertex?

A strong student should explain that once a vertex has been visited, all vertices reachable from it have also been visited.

# Problem 30 — Dijkstra's Algorithm

Given a weighted graph with nonnegative edge weights, find the shortest distance from vertex `s` to every other vertex.

### Expected Algorithm

Dijkstra's algorithm.

### Key Data Structure

Priority queue.

### Complexity

With an adjacency list and binary heap:

$$
O((V+E)\log V)
$$

A student who knows to combine:

```text
graph
+
priority queue
+
relaxation
```

is ready for more serious competitive-programming training.


# Problem 31 — Longest Increasing Subsequence

Given an array, find the length of its longest strictly increasing subsequence.

Example:

```text
10 9 2 5 3 7 101 18
```

Answer:

```text
4
```

One LIS is:

```text
2 3 7 101
```

<details>
<summary>Basic DP Solution</summary>

```java
public static int lis(int[] a) {
    int n = a.length;
    int[] dp = new int[n];

    Arrays.fill(dp, 1);

    int answer = 1;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (a[j] < a[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        answer = Math.max(answer, dp[i]);
    }

    return answer;
}
```
</details>

Complexity: $O(n^2)$

### Advanced Follow-Up

Ask:

> Can you solve it in $O(n\log n)$?

A student who can solve or even seriously discuss this version is likely ready for advanced competitive programming.

# Level 7 Interpretation

Students who successfully handle these problems are candidates for:

* ICPC team training
* Codeforces
* Kattis
* AtCoder
* CSES advanced problems
* Dynamic programming
* Graph algorithms
* Advanced data structures

# Suggested Proficiency Rubric

| Level | Description                     | Recommended Next Step                  |
| ----- | ------------------------------- | -------------------------------------- |
| 1     | Struggles with basic Java       | CS1 review                             |
| 2     | Basic programming competence    | More CS1 problem solving               |
| 3     | Comfortable with arrays/strings | Basic algorithms                       |
| 4     | Beginning algorithmic thinker   | Competitive programming fundamentals   |
| 5     | Strong CS2 programmer           | Recursion, greedy, searching, basic DP |
| 6     | Algorithmically strong          | Graphs, DP, contest training           |
| 7     | Advanced problem solver         | ICPC/serious competitive programming   |

# Recommended Placement into Training Tracks

Rather than having every CS2 student work through the same competitive-programming curriculum, I would create three tracks.

## Track A — Foundations

For students below Level 3.

### Topics

```text
Java review
loops
methods
arrays
strings
ArrayList
basic recursion
debugging
Big-O
```

### Practice

Mostly short problems.

Goal:

> Become a competent CS2 programmer.

# Track B — Competitive Programming Fundamentals

For Levels 3–5.

### Topics

```text
Complexity
sorting
binary search
hashing
two pointers
sliding window
prefix sums
stacks/queues
greedy
recursion
backtracking
basic graphs
introductory DP
```

Goal:

> Learn to recognize common algorithmic patterns.


# Track C — ICPC Preparation

For Levels 6–7.

### Topics

```text
Advanced graph algorithms
dynamic programming
shortest paths
MST
DSU
Fenwick trees
segment trees
number theory
combinatorics
string algorithms
advanced data structures
```

Then introduce:

```text
Kattis
CSES
ICPC regional problems
```

Goal:

> Develop contest-level speed, algorithm recognition, and team strategy.
