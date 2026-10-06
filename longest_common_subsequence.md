**Longest Common Subsequence (LCS)** is one of the most important introductory dynamic programming problems. It teaches a slightly different DP pattern from LIS: instead of asking what happens when a sequence **ends at a particular position**, we ask about the best answer for **prefixes of two sequences**.

# Problem 15 — Longest Common Subsequence

Given two strings, find the length of their **longest common subsequence**.

For example:

```text
String 1: ABCBDAB
String 2: BDCABA
```

One longest common subsequence is:

```text
BCBA
```

so the answer is:

```text
4
```

There are other LCSs of length 4, but we only need the length.

---

# 1. What is a subsequence?

A subsequence keeps the original order, but we are allowed to **skip characters**.

For example, given:

```text
A B C D E
```

these are subsequences:

```text
A C E
B D
A B C D E
```

because the characters remain in their original order.

But this is **not** a subsequence:

```text
C A
```

because `A` comes before `C` in the original string.

---

# 2. Subsequence vs. substring

This distinction is especially important.

Suppose we have:

```text
ABCDE
```

### Substring

A substring must be consecutive:

```text
BCD
```

is a substring.

But:

```text
ACE
```

is not a substring.

### Subsequence

A subsequence can skip characters:

```text
ACE
```

is a subsequence.

LCS is about **subsequences**, not substrings.

---

# 3. What does "common" mean?

The subsequence has to appear in **both** strings.

For example:

```text
String 1: A B C D E
String 2: A X B Y C
```

The sequence:

```text
A B C
```

is common to both.

So the LCS has length 3.

---

# 4. The key DP question

Suppose we have:

```text
String 1: A B C D
String 2: A C D
```

We could ask:

> What is the LCS of the first 4 characters of String 1 and the first 3 characters of String 2?

This leads naturally to a two-dimensional DP table.

Define:

> **`dp[i][j]` = length of the LCS between the first `i` characters of String 1 and the first `j` characters of String 2.**

This definition is the key to the entire problem.

---

# 5. Why do we use `i` and `j`?

Suppose:

```text
String 1 = ABCBDAB
String 2 = BDCABA
```

We can think of:

```text
i = how much of String 1 we've considered
j = how much of String 2 we've considered
```

For example:

```text
dp[3][4]
```

means:

> Find the LCS between the first 3 characters of String 1 and the first 4 characters of String 2.

Those prefixes are:

```text
String 1: ABC
String 2: BDCA
```

---

# 6. Why do we have an extra row and column?

This is an important detail in the implementation.

Suppose:

```text
String 1 = ABC
String 2 = BCA
```

We create:

```text
dp[4][4]
```

rather than `dp[3][3]`.

The first row represents:

> String 1 has zero characters.

The first column represents:

> String 2 has zero characters.

So initially:

|      | `""` |  B |  C |  A |
| ---- | ---: | -: | -: | -: |
| `""` |    0 |  0 |  0 |  0 |
| A    |    0 |    |    |    |
| B    |    0 |    |    |    |
| C    |    0 |    |    |    |

Why are these zeros?

Because:

> The LCS between a string and an empty string has length 0.

This gives us our base cases automatically.

---

# 7. The two possible cases

Now we reach the heart of the problem.

Suppose:

```text
String 1 character = a[i-1]
String 2 character = b[j-1]
```

There are two cases.

---

## Case 1: The characters match

Suppose:

```text
a[i-1] == b[j-1]
```

For example:

```text
A B C
    ↑
```

and:

```text
B C A
  ↑
```

If both current characters are `C`, then we can include that `C` in our common subsequence.

Therefore:

$$
dp[i][j] = dp[i-1][j-1] + 1
$$

Why `i-1` and `j-1`?

Because we are saying:

> Find the best common subsequence before these two matching characters, then add this matching character.

---

# 8. Example of a matching character

Suppose:

```text
String 1 prefix: ABC
String 2 prefix: BC
```

The final characters are both:

```text
C
```

So:

```text
dp[3][2]
```

can be calculated from:

```text
dp[2][1]
```

because:

```text
ABC
  ↑
BC
 ↑
```

The `C`s match.

Thus:

$$
dp[3][2] = dp[2][1]+1
$$

---

# 9. Case 2: The characters don't match

Now suppose:

```text
a[i-1] != b[j-1]
```

For example:

```text
String 1: ABC
String 2: BDA
```

The final characters are:

```text
C
A
```

They don't match.

What can we do?

We have two choices.

### Choice 1: Ignore the last character of String 1

Look at:

```text
dp[i-1][j]
```

### Choice 2: Ignore the last character of String 2

Look at:

```text
dp[i][j-1]
```

We don't know which one is better, so we take the maximum:

$$
dp[i][j] =
\max(dp[i-1][j], dp[i][j-1])
$$

This gives the complete recurrence.

---

# 10. The complete recurrence

### If the characters match:

$$
\boxed{
dp[i][j]=dp[i-1][j-1]+1
}
$$

### If they don't match:

$$
\boxed{
dp[i][j]=\max(dp[i-1][j],dp[i][j-1])
}
$$

And:

$$
dp[0][j]=0
$$

$$
dp[i][0]=0
$$

---

# 11. Let's trace a small example first

Before tracing the full example, let's use:

```text
String 1 = ABC
String 2 = AC
```

We expect:

```text
AC
```

so the answer is 2.

Our table is:

|      | `""` |  A |  C |
| ---- | ---: | -: | -: |
| `""` |    0 |  0 |  0 |
| A    |    0 |    |    |
| B    |    0 |    |    |
| C    |    0 |    |    |

---

## Cell `[1][1]`

Compare:

```text
A
A
```

They match.

Therefore:

$$
dp[1][1]=dp[0][0]+1
$$

$$
=0+1=1
$$

Table:

|      | `""` |     A |  C |
| ---- | ---: | ----: | -: |
| `""` |    0 |     0 |  0 |
| A    |    0 | **1** |    |
| B    |    0 |       |    |
| C    |    0 |       |    |

---

## Cell `[1][2]`

Compare:

```text
A
C
```

They don't match.

So:

$$
dp[1][2]=\max(dp[0][2],dp[1][1])
$$

$$
=\max(0,1)
$$

$$
=1
$$

Table:

|      | `""` |  A |     C |
| ---- | ---: | -: | ----: |
| `""` |    0 |  0 |     0 |
| A    |    0 |  1 | **1** |
| B    |    0 |    |       |
| C    |    0 |    |       |

---

## Cell `[2][1]`

Compare:

```text
B
A
```

They don't match.

So:

$$
dp[2][1]=\max(dp[1][1],dp[2][0])
$$

$$
=\max(1,0)=1
$$

---

## Cell `[2][2]`

Compare:

```text
B
C
```

They don't match.

So:

$$
dp[2][2]=\max(dp[1][2],dp[2][1])
$$

$$
=\max(1,1)=1
$$

---

## Cell `[3][1]`

Compare:

```text
C
A
```

No match.

Therefore:

$$
dp[3][1]=\max(dp[2][1],dp[3][0])
$$

$$
=1
$$

---

## Cell `[3][2]`

Now:

```text
C
C
```

match!

Therefore:

$$
dp[3][2]=dp[2][1]+1
$$

$$
=1+1
$$

$$
=2
$$

Final table:

|      |  `""` |     A |     C |
| ---- | ----: | ----: | ----: |
| `""` | **0** | **0** | **0** |
| A    | **0** | **1** | **1** |
| B    | **0** | **1** | **1** |
| C    | **0** | **1** | **2** |

Therefore:

```text
LCS = 2
```

---

# 12. Now trace the original example

Let's use:

```text
String 1 = ABCBDAB
String 2 = BDCABA
```

We create a table with the characters labeled across the top and left:

|      | `""` |  B |  D |  C |  A |  B |  A |
| ---- | ---: | -: | -: | -: | -: | -: | -: |
| `""` |    0 |  0 |  0 |  0 |  0 |  0 |  0 |
| A    |    0 |    |    |    |    |    |    |
| B    |    0 |    |    |    |    |    |    |
| C    |    0 |    |    |    |    |    |    |
| B    |    0 |    |    |    |    |    |    |
| D    |    0 |    |    |    |    |    |    |
| A    |    0 |    |    |    |    |    |    |
| B    |    0 |    |    |    |    |    |    |

We fill the table **from top-left to bottom-right**.

---

# 13. First row and column

They're all zero:

|      |  `""` |     B |     D |     C |     A |     B |     A |
| ---- | ----: | ----: | ----: | ----: | ----: | ----: | ----: |
| `""` | **0** | **0** | **0** | **0** | **0** | **0** | **0** |
| A    | **0** |       |       |       |       |       |       |
| B    | **0** |       |       |       |       |       |       |
| C    | **0** |       |       |       |       |       |       |
| B    | **0** |       |       |       |       |       |       |
| D    | **0** |       |       |       |       |       |       |
| A    | **0** |       |       |       |       |       |       |
| B    | **0** |       |       |       |       |       |       |

---

# 14. Row A

Compare `A` with each character in the second string.

```text
A vs B → no
A vs D → no
A vs C → no
A vs A → match
A vs B → no
A vs A → match
```

The row becomes:

|       | `""` |  B |  D |  C |     A |  B |     A |
| ----- | ---: | -: | -: | -: | ----: | -: | ----: |
| `""`  |    0 |  0 |  0 |  0 |     0 |  0 |     0 |
| **A** |    0 |  0 |  0 |  0 | **1** |  1 | **1** |

For example, at the first `A`:

$$
dp[1][4]=dp[0][3]+1=1
$$

---

# 15. Row B

Now the first string character is `B`.

Compare it against:

```text
B D C A B A
```

The first character matches:

```text
B == B
```

so:

$$
dp[2][1]=dp[1][0]+1=1
$$

Later, when we reach the second `B` in String 2, we can get a longer result.

The completed row is:

|       | `""` |     B |  D |  C |  A |     B |  A |
| ----- | ---: | ----: | -: | -: | -: | ----: | -: |
| A     |    0 |     0 |  0 |  0 |  1 |     1 |  1 |
| **B** |    0 | **1** |  1 |  1 |  1 | **2** |  2 |

At `dp[2][5]`:

```text
String 1 prefix = AB
String 2 prefix = BDCAB
```

The LCS is:

```text
AB
```

with length 2.

---

# 16. Row C

Now compare `C` against:

```text
B D C A B A
```

When we reach `C`:

```text
C == C
```

we add 1 to the diagonal.

The completed row:

|       | `""` |  B |  D |     C |  A |  B |  A |
| ----- | ---: | -: | -: | ----: | -: | -: | -: |
| B     |    0 |  1 |  1 |     1 |  1 |  2 |  2 |
| **C** |    0 |  1 |  1 | **2** |  2 |  2 |  2 |

At `dp[3][3]`:

$$
dp[3][3]=dp[2][2]+1
$$

$$
=1+1=2
$$

The LCS of:

```text
ABC
BDC
```

has length 2.

One such LCS is:

```text
BC
```

---

# 17. Row B again

String 1 is:

```text
A B C B D A B
      ↑
```

Now we're at the second `B`.

The row becomes:

|       | `""` |     B |  D |  C |  A |     B |  A |
| ----- | ---: | ----: | -: | -: | -: | ----: | -: |
| C     |    0 |     1 |  1 |  2 |  2 |     2 |  2 |
| **B** |    0 | **1** |  1 |  2 |  2 | **3** |  3 |

At `dp[4][5]`, the characters are both `B`.

Therefore:

$$
dp[4][5]=dp[3][4]+1
$$

$$
=2+1=3
$$

---

# 18. Row D

Now the character is `D`.

The completed row:

|       | `""` |  B |     D |  C |  A |  B |  A |
| ----- | ---: | -: | ----: | -: | -: | -: | -: |
| B     |    0 |  1 |     1 |  2 |  2 |  3 |  3 |
| **D** |    0 |  1 | **2** |  2 |  2 |  3 |  3 |

At `[5][2]`, the `D`s match:

$$
dp[5][2]=dp[4][1]+1
$$

$$
=1+1=2
$$

---

# 19. Row A

Now the character is `A`.

The completed row:

|       | `""` |  B |  D |  C |     A |  B |     A |
| ----- | ---: | -: | -: | -: | ----: | -: | ----: |
| D     |    0 |  1 |  2 |  2 |     2 |  3 |     3 |
| **A** |    0 |  1 |  2 |  2 | **3** |  3 | **4** |

At `[6][4]`, we have:

```text
A == A
```

so:

$$
dp[6][4]=dp[5][3]+1
$$

$$
=2+1=3
$$

At `[6][6]`, the two `A`s also match:

$$
dp[6][6]=dp[5][5]+1
$$

$$
=3+1=4
$$

---

# 20. Final row: B

Finally, String 1's last character is `B`.

The completed table is:

|      | `""` |  B |  D |  C |  A |     B |     A |
| ---- | ---: | -: | -: | -: | -: | ----: | ----: |
| `""` |    0 |  0 |  0 |  0 |  0 |     0 |     0 |
| A    |    0 |  0 |  0 |  0 |  1 |     1 |     1 |
| B    |    0 |  1 |  1 |  1 |  1 |     2 |     2 |
| C    |    0 |  1 |  1 |  2 |  2 |     2 |     2 |
| B    |    0 |  1 |  1 |  2 |  2 |     3 |     3 |
| D    |    0 |  1 |  2 |  2 |  2 |     3 |     3 |
| A    |    0 |  1 |  2 |  2 |  3 |     3 |     4 |
| B    |    0 |  1 |  2 |  2 |  3 | **4** | **4** |

Therefore:

$$
\boxed{LCS=4}
$$

---

# 21. Where does the `4` come from?

One LCS is:

```text
B C B A
```

Let's locate it:

```text
String 1: A B C B D A B
            ↑ ↑ ↑   ↑
            B C B   A
```

and:

```text
String 2: B D C A B A
          ↑   ↑   ↑   ↑
          B   C   B   A
```

The characters appear in the same order in both strings.

So:

```text
BCBA
```

has length 4.

Another possible LCS is:

```text
BDAB
```

also length 4.

---

# 22. Understanding the "mismatch" case

This is probably the hardest part for students.

Suppose we're calculating:

```text
dp[i][j]
```

and:

```text
a[i-1] != b[j-1]
```

Why do we take:

```java
Math.max(dp[i - 1][j], dp[i][j - 1])
```

?

Imagine:

```text
String 1: ... X
String 2: ... Y
```

and:

```text
X != Y
```

We cannot use **both** X and Y as the next matching pair.

So at least one of them must be excluded from consideration.

We try both possibilities:

```text
Ignore X:
dp[i-1][j]
```

or:

```text
Ignore Y:
dp[i][j-1]
```

and keep whichever produces the longer LCS.

That's the intuition behind:

$$
\boxed{
dp[i][j]=\max(dp[i-1][j],dp[i][j-1])
}
$$

---

# 23. Understanding the "match" case

When:

```text
a[i-1] == b[j-1]
```

we can safely use that character as part of the common subsequence.

For example:

```text
... A
... A
```

becomes:

```text
... A
    ↑
```

We take the best LCS before those characters:

```text
dp[i-1][j-1]
```

and append the matching character:

```text
+ 1
```

So:

$$
\boxed{
dp[i][j]=dp[i-1][j-1]+1
}
$$

---

# 24. The Java solution

```java
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1] + 1;

                } else {

                    dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
```

---

# 25. Connecting every line to the DP idea

### Create the table

```java
int[][] dp = new int[n + 1][m + 1];
```

Why `+1`?

Because row 0 and column 0 represent the empty string.

---

### Examine each pair of characters

```java
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
```

We're comparing prefixes of the two strings.

---

### Compare the current characters

```java
a.charAt(i - 1) == b.charAt(j - 1)
```

Why `i - 1`?

Because:

```text
dp[1][1]
```

represents the first character of each string, whose Java indexes are:

```text
0
```

---

### If they match

```java
dp[i][j] = dp[i - 1][j - 1] + 1;
```

Meaning:

> Take the best answer before these two characters and add this matching character.

---

### If they don't match

```java
dp[i][j] = Math.max(
    dp[i - 1][j],
    dp[i][j - 1]
);
```

Meaning:

> Try ignoring one character or the other, and keep the better answer.

---

# 26. Why is the table filled from top-left to bottom-right?

Look at the recurrence:

```text
dp[i][j]
```

depends on:

```text
dp[i-1][j-1]
dp[i-1][j]
dp[i][j-1]
```

These are:

```text
             dp[i-1][j]
                  ↑
                  |
dp[i-1][j-1] → dp[i][j] ← dp[i][j-1]
```

All three are **above or to the left** of the current cell.

Therefore, if we fill the table row by row from top-left to bottom-right, all required values have already been calculated.

This is a nice opportunity to teach students that:

> **The recurrence often tells you the order in which the DP table must be evaluated.**

---

# 27. Complexity

If the first string has length `n` and the second has length `m`, we have:

```text
(n + 1) × (m + 1)
```

DP states.

Each state takes constant time.

Therefore:

$$
\boxed{O(nm)}
$$

time.

The table requires:

$$
\boxed{O(nm)}
$$

space.

---

# 28. A useful comparison with Problem 14 — LIS

These two problems make a nice pair for students:

|                    | LIS                              | LCS                                     |
| ------------------ | -------------------------------- | --------------------------------------- |
| Input              | One sequence                     | Two sequences                           |
| DP state           | `dp[i]`                          | `dp[i][j]`                              |
| Meaning            | Best subsequence **ending at i** | Best common subsequence of two prefixes |
| Main question      | Can `a[i]` follow `a[j]`?        | Do the current characters match?        |
| Match condition    | `a[j] < a[i]`                    | `a[i-1] == b[j-1]`                      |
| Typical complexity | `O(n²)`                          | `O(nm)`                                 |

The big conceptual difference is:

**LIS:**

> "What is the best solution ending here?"

**LCS:**

> "What is the best solution for these two prefixes?"

---

# 29. A good student trace exercise

I would give students this smaller example before asking them to trace the full one:

```text
String 1: ABC
String 2: AC
```

Ask them to fill:

|      | `""` |   A |   C |
| ---- | ---: | --: | --: |
| `""` |    0 |   0 |   0 |
| A    |    0 | ___ | ___ |
| B    |    0 | ___ | ___ |
| C    |    0 | ___ | ___ |

Then ask:

1. What does `dp[2][1]` mean?
2. Why is `dp[1][1] = 1`?
3. Why is `dp[2][2] = 1`?
4. Why does `dp[3][2]` become 2?
5. What is the LCS?

The completed table is:

|      | `""` |     A |     C |
| ---- | ---: | ----: | ----: |
| `""` |    0 |     0 |     0 |
| A    |    0 | **1** | **1** |
| B    |    0 | **1** | **1** |
| C    |    0 | **1** | **2** |

---

# 30. The three questions students should remember

For LCS, I would have students focus on these three questions rather than memorizing the code.

### 1. What does `dp[i][j]` mean?

> The LCS length of the first `i` characters of String 1 and the first `j` characters of String 2.

### 2. What if the current characters match?

```text
dp[i][j] = dp[i-1][j-1] + 1
```

**Match → diagonal + 1.**

### 3. What if they don't match?

```text
dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

**No match → discard one side and take the better result.**

A very memorable summary for students is:

> **Match → go diagonal and add 1.**
> **Mismatch → look up or left and take the maximum.**

That gives them the core pattern they need to recognize LCS problems in an ICPC setting.
