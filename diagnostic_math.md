# ICPC Mathematics Proficiency Diagnostic

## Purpose

This diagnostic assesses the mathematical background students need for competitive programming and ICPC-style contests.

It is designed to answer two questions:

1. **What mathematical knowledge does the student already have?**
2. **What type of mathematical training should the student receive next?**


# Mathematics for Competitive Programming

ICPC problems generally do not require advanced calculus. More commonly, students need to be comfortable with:

```text
Arithmetic
    ↓
Algebra
    ↓
Discrete Mathematics
    ↓
Number Theory
    ↓
Combinatorics
    ↓
Probability
    ↓
Graph-related mathematics
    ↓
Advanced contest mathematics
```

The most important skill is often not calculating an answer, but **recognizing the mathematical structure hidden inside a programming problem**.


# Level 1 — Arithmetic Fundamentals

# Problem 1 — Divisibility

Which of the following numbers are divisible by 3?

```text
123
247
891
1001
5724
```

<details>
<summary>solution</summary>

A number is divisible by 3 if the sum of its digits is divisible by 3.

```text
123  → 1+2+3 = 6       yes
247  → 2+4+7 = 13      no
891  → 8+9+1 = 18      yes
1001 → 1+0+0+1 = 2     no
5724 → 5+7+2+4 = 18    yes
```
</details>

# Problem 2 — Powers

Calculate:

$$
2^{10}
$$

<details>
<summary>solution</summary>

$$
\boxed{1024}
$$
</details>


> What is $ 2^{30} $?

Answer:

$$
1,073,741,824
$$

This is useful because competitive programmers frequently encounter powers of 2 in complexity analysis and bit manipulation.

# Problem 3 — Modular Arithmetic

Find:

$$
137\bmod 12
$$

<details>
<summary>solution</summary>

$$
137=12(11)+5
$$

Therefore:

$$
\boxed{5}
$$

</details>

# Level 2 — Algebra and Mathematical Reasoning

# Problem 4 — Solve an Equation

Solve:

$$
3x+7=25
$$

<details>
<summary>solution</summary>

$$
3x=18
$$

$$
x=6
$$

</details>

# Problem 5 — Sequence

Consider:

```text
3, 7, 11, 15, 19, ...
```

What is the 100th term?

<details>
<summary>solution</summary>

This is an arithmetic sequence with:

$$
a_1=3
$$

and common difference:

$$
d=4
$$

Therefore:

$$
a_n=3+(n-1)4
$$

For \(n=100\):

$$
a_{100}=3+99(4)=399
$$

</details>

# Problem 6 — Sum of an Arithmetic Sequence

Calculate:

$$
1+2+3+\cdots+100
$$

<details>
<summary>solution</summary>

Use:

$$
\frac{n(n+1)}{2}
$$

Therefore:

$$
\frac{100(101)}{2}=5050
$$

</details>

### Competitive Programming Connection

This is an example of replacing an \(O(n)\) computation with an \(O(1)\) mathematical formula.

# Problem 7 — Growth Rates

Arrange the following in increasing order of growth:

$$
n,\quad \log n,\quad n^2,\quad 2^n,\quad n!
$$

<details>
<summary>solution</summary>

$$
\boxed{
\log n
<
n
<
n^2
<
2^n
<
n!
}
$$

</details>

This is an essential foundation for competitive programming.


# Level 3 — Discrete Mathematics

This level is particularly important for ICPC.

# Problem 8 — Sets

Let:

$$
A=\{1,2,3,4,5\}
$$

and:

$$
B=\{3,4,5,6,7\}
$$

Find:

### a. $A\cup B$

### b. $A\cap B$

### c. $A-B$

<details>
<summary>solution</summary>

$$
A\cup B=\{1,2,3,4,5,6,7\}
$$

$$
A\cap B=\{3,4,5\}
$$

$$
A-B=\{1,2\}
$$
</details>

# Problem 9 — Counting Subsets

How many subsets does a set containing \(n\) elements have?

<details>
<summary>solution</summary>

$$
\boxed{2^n}
$$

### Why?

For every element, there are two choices:

```text
include it
exclude it
```

Therefore:

$$
2\times2\times\cdots\times2=2^n
$$

</details>

# Problem 7 — Pigeonhole Principle

There are 13 people in a room.

Show that at least two of them must have been born in the same month.

<details>
<summary>solution</summary>

There are only 12 months.

We have:

$$
13>12
$$

Therefore, by the pigeonhole principle, at least two people must share a birth month.

</details>

# Problem 8 — Parity

Prove that the sum of two odd integers is even.

### Solution

Let the two odd integers be:

$$
2a+1
$$

and:

$$
2b+1
$$

Their sum is:

$$
(2a+1)+(2b+1)
$$

$$
=2a+2b+2
$$

$$
=2(a+b+1)
$$

which is divisible by 2.

Therefore, the sum is even.

# Problem 9 — Invariant Thinking

A frog starts at position 0.

At every move it jumps either 2 or 4 positions.

Can it ever land on position 7?

<details>
<summary>solution</summary>

No.

Every reachable position is even:

```text
0
2
4
6
8
10
...
```

The parity of the position never changes.

Since 7 is odd, it is unreachable.

</details>

### Key Concept

This is an example of an **invariant**.

Students should learn to ask:

> What property never changes?

This is extremely useful in ICPC problems.

# Level 3 Interpretation

Students who succeed here are ready for serious competitive-programming mathematics.

Recommended topics:

```text
Number theory
Combinatorics
Probability
Graph theory
Mathematical proofs
Invariants
```

# Level 4 — Number Theory

This is one of the most useful mathematical areas for ICPC.

# Problem 7 — GCD

Find:

$$
\gcd(84,30)
$$

<details>
<summary>solution</summary>

Using the Euclidean algorithm:

$$
84=2(30)+24
$$

$$
30=1(24)+6
$$

$$
24=4(6)+0
$$

Therefore:

$$
\boxed{6}
$$

</details>


# Problem 8 — Least Common Multiple

Find:

$$
\operatorname{lcm}(12,18)
$$

<details>
<summary>solution</summary>

Prime factorization:

$$
12=2^2\cdot3
$$

$$
18=2\cdot3^2
$$

Therefore:

$$
\operatorname{lcm}(12,18)=2^2\cdot3^2=36
$$

</details>

### Useful Identity

$$
\gcd(a,b)\times\operatorname{lcm}(a,b)=ab
$$

---

# Problem 9 — Prime Factorization

Find the prime factorization of:

$$
360
$$

<details>
<summary>solution</summary>

$$
360=36(10)
$$

$$
=2^2\cdot3^2\cdot2\cdot5
$$

Therefore:

$$
\boxed{360=2^3\cdot3^2\cdot5}
$$

</details>

# Problem 16 — Number of Divisors

How many positive divisors does:

$$
360=2^3\cdot3^2\cdot5^1
$$

have?

<details>
<summary>solution</summary>

For each prime, choose an exponent:

```text
2: 0,1,2,3 → 4 choices
3: 0,1,2   → 3 choices
5: 0,1     → 2 choices
```

Therefore:

$$
4\cdot3\cdot2=24
$$

</details>

# Problem 17 — Euclidean Algorithm

Write the sequence of remainders produced when calculating:

$$
\gcd(1071,462)
$$

<details>
<summary>solution</summary>

$$
1071=2(462)+147
$$

$$
462=3(147)+21
$$

$$
147=7(21)+0
$$

Therefore:

$$
\boxed{\gcd(1071,462)=21}
$$

</details>

# Problem 7 — Modular Exponentiation

Find:

$$
2^{10}\bmod 7
$$

<details>
<summary>solution</summary>

$$
2^{10}=1024
$$

and:

$$
1024=7(146)+2
$$

Therefore:

$$
\boxed{2}
$$

A stronger student should recognize that for very large exponents, we cannot simply calculate the entire power.

This motivates **fast modular exponentiation**.

</details>

# Problem 8 — Modular Arithmetic

Calculate:

$$
(17+29)\bmod 10
$$

<details>
<summary>solution</summary>

$$
17+29=46
$$

so:

$$
46\bmod10=6
$$

Therefore:

$$
\boxed{6}
$$

Also:

$$
(17\bmod10+29\bmod10)\bmod10
$$

$$
=(7+9)\bmod10
$$

$$
=6
$$

</details>

# Problem 9 — Divisibility

Prove that if:

$$
a\mid b
$$

and:

$$
a\mid c
$$

then:

$$
a\mid(b+c)
$$

<details>
<summary>solution</summary>

Because \(a\mid b\), there exists an integer \(x\) such that:

$$
b=ax
$$

Because \(a\mid c\), there exists an integer \(y\) such that:

$$
c=ay
$$

Therefore:

$$
b+c=ax+ay
$$

$$
=a(x+y)
$$

Since \(x+y\) is an integer:

$$
\boxed{a\mid(b+c)}
$$

</details>

# Level 4 Interpretation

Students who reach this level should begin studying:

* Sieve of Eratosthenes
* Fast exponentiation
* Modular arithmetic
* Modular inverse
* Extended Euclidean algorithm
* Prime factorization
* Chinese Remainder Theorem

# Level 5 — Combinatorics

Combinatorics appears frequently in ICPC problems.


# Problem 16 — Permutations

How many ways can 5 distinct people be arranged in a line?

<details>
<summary>solution</summary>

$$
5!=5\cdot4\cdot3\cdot2\cdot1
$$

$$
=120
$$

</details>


# Problem 17 — Combinations

How many ways can we choose 3 students from a class of 10?

<details>
<summary>solution</summary>

Order does not matter.

Therefore:

$$
\binom{10}{3}
=
\frac{10!}{3!7!}
$$

$$
=\frac{10\cdot9\cdot8}{3\cdot2\cdot1}
$$

$$
=120
$$

</details>


# Problem 7 — Binary Strings

How many binary strings of length 8 exist?

<details>
<summary>solution</summary>

Each position has two choices:

$$
2^8=256
$$

</details>


# Problem 8 — Strings with Exactly Three 1s

How many binary strings of length 10 contain exactly three `1`s?


<details>
<summary>solution</summary>

Choose which three positions contain the `1`s:

$$
\binom{10}{3}=120
$$

</details>

# Problem 9 — Passwords

A password consists of 4 uppercase letters followed by 3 digits.

Repetition is allowed.

How many passwords are possible?

<details>
<summary>solution</summary>

Each letter has 26 choices:

$$
26^4
$$

Each digit has 10 choices:

$$
10^3
$$

Therefore:

$$
26^4\cdot10^3
$$

</details>

# Problem 16 — No Adjacent Ones

How many binary strings of length 5 contain no two consecutive `1`s?

<details>
<summary>solution</summary>

Let \(f(n)\) be the number of valid strings of length \(n\).

A valid string either:

* ends in `0`, preceded by any valid string of length \(n-1\)
* ends in `10`, effectively corresponding to a valid string of length \(n-2\)

Therefore:

$$
f(n)=f(n-1)+f(n-2)
$$

with:

$$
f(1)=2
$$

$$
f(2)=3
$$

Thus:

```text
f(3) = 5
f(4) = 8
f(5) = 13
```

</details>

### Important Connection

This is simultaneously:

* A combinatorics problem
* A recurrence
* A dynamic programming problem

This is exactly the kind of connection students need to develop for ICPC.


# Problem 17 — Inclusion-Exclusion

How many integers from 1 through 100 are divisible by 3 or 5?

<details>
<summary>solution</summary>

Multiples of 3:

$$
\left\lfloor\frac{100}{3}\right\rfloor=33
$$

Multiples of 5:

$$
\left\lfloor\frac{100}{5}\right\rfloor=20
$$

Multiples of both:

$$
\left\lfloor\frac{100}{15}\right\rfloor=6
$$

Therefore:

$$
33+20-6=47
$$

</details>

# Level 5 Interpretation

Students reaching this level should study:

```text
Permutations
Combinations
Pascal's triangle
Binomial coefficients
Inclusion-exclusion
Recurrences
Stars and bars
Basic combinatorial DP
```

# Level 6 — Probability

# Problem 7 — Basic Probability

A fair six-sided die is rolled.

What is the probability of rolling an even number?

<details>
<summary>solution</summary>

Even outcomes:

```text
2, 4, 6
```

There are 3 favorable outcomes out of 6.

$$
P=\frac{3}{6}=\frac12
$$

</details>


# Problem 8 — Two Dice

Two fair six-sided dice are rolled.

What is the probability that their sum is 7?

<details>
<summary>solution</summary>

Possible pairs:

```text
(1,6)
(2,5)
(3,4)
(4,3)
(5,2)
(6,1)
```

There are 6 favorable outcomes.

There are:

$$
6\times6=36
$$

total outcomes.

Therefore:

$$
P=\frac6{36}=\frac16
$$

</details>


# Problem 9 — At Least One Six

A fair die is rolled three times.

What is the probability of getting at least one 6?

<details>
<summary>solution</summary>

It is easier to calculate the complement.

Probability of no 6 on one roll:

$$
\frac56
$$

Probability of no 6 in three rolls:

$$
\left(\frac56\right)^3
$$

Therefore:

$$
P(\text{at least one 6})
=
1-\left(\frac56\right)^3
$$

$$
=1-\frac{125}{216}
$$

$$
=\frac{91}{216}
$$

</details>


# Problem 16 — Expected Value

A game gives:

```text
$0 with probability 1/2
$10 with probability 1/4
$20 with probability 1/4
```

What is the expected payout?

<details>
<summary>solution</summary>

$$
E[X]
=
0\left(\frac12\right)
+
10\left(\frac14\right)
+
20\left(\frac14\right)
$$

$$
=0+2.5+5
$$

</details>

# Level 6 Interpretation

Students who struggle here should review:

* Probability
* Counting
* Conditional probability
* Expected value

Students who succeed should be introduced to probability-based contest problems.

# Level 7 — Advanced ICPC Mathematical Reasoning

These problems are intended primarily for identifying strong competitors.

# Problem 17 — Fast Power

You need to calculate:

$$
a^b\bmod m
$$

where:

$$
b
$$

may be as large as $10^{18}$.

Can you calculate $a^b$ directly?

<details>
<summary>solution</summary>

No.

We use **binary exponentiation**.

### Algorithm

```text
result = 1
base = a

while b > 0:
    if b is odd:
        result = result * base mod m

    base = base * base mod m
    b = b / 2
```

### Complexity

$$
O(\log b)
$$

A student who understands why this works has an important competitive-programming technique.

</details>

# Problem 7 — Extended Euclidean Algorithm

Find integers \(x,y\) satisfying:

$$
35x+22y=1
$$

<details>
<summary>solution</summary>

Euclidean algorithm:

$$
35=1(22)+13
$$

$$
22=1(13)+9
$$

$$
13=1(9)+4
$$

$$
9=2(4)+1
$$

Back substitute:

$$
1=9-2(4)
$$

$$
=9-2(13-9)
$$

$$
=3(9)-2(13)
$$

$$
=3(22-13)-2(13)
$$

$$
=3(22)-5(13)
$$

$$
=3(22)-5(35-22)
$$

$$
=8(22)-5(35)
$$

Therefore:

$$
x=-5,\quad y=8
$$

</details>

# Problem 8 — Modular Inverse

Find the inverse of 3 modulo 7.

That is, find \(x\) such that:

$$
3x\equiv1\pmod7
$$

<details>
<summary>solution</summary>

Try:

$$
3(5)=15
$$

and:

$$
15\bmod7=1
$$

Therefore:

$$
\boxed{x=5}
$$

</details>

# Problem 9 — Fibonacci Matrix Idea

Suppose we need:

$$
F_n
$$

for:

$$
n=10^{18}
$$

where:

$$
F_0=0,\quad F_1=1
$$

A linear \(O(n)\) algorithm is impossible.

What general technique could reduce the complexity?

<details>
<summary>solution</summary>


**Matrix exponentiation.**

The Fibonacci recurrence can be represented as:

$$
\begin{bmatrix}
F_{n+1}\\
F_n
\end{bmatrix}
=
\begin{bmatrix}
1&1\\
1&0
\end{bmatrix}
\begin{bmatrix}
F_n\\
F_{n-1}
\end{bmatrix}
$$

Therefore, compute the matrix power using binary exponentiation.

Complexity:

$$
O(\log n)
$$

</details>


# Problem 16 — Graph Coloring

A graph is bipartite if its vertices can be divided into two groups such that no edge connects vertices within the same group.

How could you determine algorithmically whether a graph is bipartite?

### Expected Idea

Use BFS or DFS while assigning alternating colors:

```text
color[v] = 0
color[neighbor] = 1
```

If an edge connects two vertices with the same color, the graph is not bipartite.

### Mathematical Connection

Students should recognize that graph algorithms often rely on mathematical properties such as:

* parity
* equivalence relations
* coloring
* invariants

# Problem 17 — Invariant Challenge

You have a chessboard with one corner square removed.

Can the remaining board always be tiled with dominoes, where each domino covers two adjacent squares?

<details>
<summary>solution</summary>

Color the chessboard like a normal chessboard.

There are:

```text
32 black squares
32 white squares
```

Removing a corner removes one square of one color.

The remaining board therefore has:

```text
31 squares of one color
32 squares of the other color
```

Every domino covers exactly:

```text
1 black + 1 white
```

Therefore, the remaining board cannot be tiled.

### Answer

$$
\boxed{\text{No}}
$$

### Key Concept

This is a classic example of an **invariant / coloring argument**.

It is excellent preparation for ICPC reasoning.

</details>


# Problem 7 — Recurrence Recognition

Suppose:

$$
T(n)=2T(n/2)+n
$$

What is the asymptotic complexity?

<details>
<summary>solution</summary>

$$
\boxed{O(n\log n)}
$$

This can be derived using the Master Theorem or a recursion-tree argument.

</details>

# Problem 8 — Recurrence

Consider:

$$
T(n)=T(n-1)+1
$$

What is the complexity?

<details>
<summary>solution</summary>

$$
\boxed{O(n)}
$$

Now consider:

$$
T(n)=2T(n-1)+1
$$

The complexity is:

$$
\boxed{O(2^n)}
$$

Students should learn to recognize these growth patterns.

</details>

# Final Mathematical Challenge

## Problem 40 — Counting Paths

You are standing at the upper-left corner of a grid and want to reach the lower-right corner.

You may move only:

```text
Right
Down
```

Suppose the grid has 5 rows and 7 columns.

How many different paths are there?

<details>
<summary>solution</summary>

You must make:

```text
6 right moves
4 down moves
```

for a total of:

$$
10
$$

moves.

Choose which 4 of the 10 moves are down moves:

$$
\binom{10}{4}
$$

Therefore:

$$
\boxed{210}
$$

### Competitive Programming Connection

This problem connects:

```text
Combinatorics
+
Grid problems
+
Dynamic programming
```

</details>

# Mathematical Proficiency Rubric

After administering the diagnostic, record the student's level in each category.

| Area                              | Level 1–5 |
| --------------------------------- | --------: |
| Arithmetic                        |           |
| Algebra                           |           |
| Discrete mathematics              |           |
| Number theory                     |           |
| Combinatorics                     |           |
| Probability                       |           |
| Mathematical reasoning/proofs     |           |
| Complexity/growth                 |           |
| Algorithmic mathematical thinking |           |

A student's **overall mathematical level should not simply be the average**.

For competitive programming, mathematical reasoning and number sense are often more important than advanced algebraic manipulation.


# Important Distinction: Mathematical Knowledge vs. Mathematical Thinking

I recommend evaluating these separately.

For example, a student may not know the formula:

$$
\binom{n}{k}
$$

but, after being given the formula, immediately recognize that a problem requires choosing \(k\) positions.

That student may be **very promising**.

Conversely, a student may know many formulas but have difficulty recognizing when to use them.

For competitive programming, the second student needs more practice with **problem recognition**.

# A Particularly Useful Final Diagnostic

After students finish the mathematical questions, give them several problems without telling them the mathematical topic.

For example:

### Problem A

> You need to determine whether two integers have a common factor greater than 1.

Expected recognition:

$$
\gcd
$$

### Problem B

> You need to count the number of ways to select exactly 5 objects from 20 objects.

Expected recognition:

$$
\binom{20}{5}
$$

### Problem C

> You need to determine whether a very large integer is divisible by 3.

Expected recognition:

**digit sum / divisibility rule**

### Problem D

> You need to calculate \(7^{10^{18}}\pmod{1000000007}\).

Expected recognition:

**modular exponentiation**

### Problem E

> A game has a position that can be classified as winning or losing depending on the parity of the number of remaining moves.

Expected recognition:

**parity / invariant / game-state reasoning**
