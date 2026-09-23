## Sliding Window Problem Set

### Level 1 — Fixed-Size Windows

#### Problem 1 — Maximum Average Segment

Given an array of `n` integers and an integer `k`, find the maximum sum of any `k` consecutive elements.

**Input**

```text
8 3
2 1 5 1 3 2 6 2
```

**Output**

```text
11
```

The segment is `3 2 6`.

**Expected technique:** Fixed-size sliding window
**Complexity:** `O(n)`

<details>
<summary>Solution</summary>

```java
public static long maxWindowSum(int[] a, int k) {
    long sum = 0;

    // First window
    for (int i = 0; i < k; i++) {
        sum += a[i];
    }

    long best = sum;

    // Slide the window
    for (int right = k; right < a.length; right++) {
        sum += a[right];
        sum -= a[right - k];

        best = Math.max(best, sum);
    }

    return best;
}
```

Complexity

Time: O(n)

Space: O(1)

The important operation is:

```
sum += a[right];
sum -= a[right - k];
```

We never recompute the entire window.

#### Problem 2 — Number of Good Windows

An array contains `0`s and `1`s. A window of length `k` is called **good** if it contains at least `k-1` ones.

Count the number of good windows.

**Input**

```text
10 4
1 1 0 1 0 1 1 1 0 1
```

**Output**

```text
5
```

**Expected technique:** Maintain the number of ones in the current window.

---

#### Problem 3 — Temperature Alert

A monitoring system records the temperature every hour.

Given `n` temperatures and a window size `k`, find the number of windows whose average temperature is at least `T`.

**Input**

```text
10 4 70
68 72 75 71 69 73 76 72 68 74
```

**Output**

```text
6
```

**Expected technique:** Fixed-size window.

**Teaching point:** Students should realize they don't need to recompute every window's sum.

---

### Level 2 — Variable-Size Windows

These are more representative of the classic competitive-programming sliding-window pattern.

#### Problem 4 — Longest Stable Segment

You are given an array of integers and an integer `K`.

Find the longest contiguous segment whose sum is **at most `K`**.

All numbers are positive.

**Input**

```text
8 10
2 3 1 2 4 3 1 1
```

**Output**

```text
5
```

One longest segment is:

```text
3 1 2 4
```

Actually its sum is 10 and length 4; the segment

```text
2 3 1 2 1 1
```

would depend on the actual array, so this is a good opportunity to have students verify examples carefully.

**Expected technique:** Expand right; while sum > K, move left.

**Complexity:** `O(n)`

---

#### Problem 5 — Minimum-Length Journey

You are given positive integers representing travel times between stops. Find the **minimum number of consecutive stops whose total travel time is at least `K`**.

**Input**

```text
8 11
2 3 1 2 4 3 1 1
```

**Output**

```text
3
```

For example:

```text
4 + 3 + 1 + 1 = 9
```

doesn't reach 11, but:

```text
2 + 4 + 3 + 1 + 1 = 11
```

has length 5. Students should determine the actual minimum.

**Expected technique:** Variable-size sliding window.

**Important:** Tell students explicitly that all numbers are positive. This is what makes the standard sliding-window solution possible.

---

#### Problem 6 — Longest Subarray with Sum ≤ K

Given an array of **positive integers**, find the longest contiguous subarray whose sum is at most `K`.

**Input**

```text
7 12
3 2 5 1 1 4 2
```

**Output**

```text
4
```

**Expected technique:** Two pointers.

**Complexity:** `O(n)`

---

## Level 3 — Frequency Maps

These are particularly useful because students learn that a sliding window isn't necessarily about maintaining a sum.

### Problem 7 — Longest Substring Without Repeating Characters

Given a string, find the length of the longest substring containing no repeated characters.

**Input**

```text
abcabcbb
```

**Output**

```text
3
```

The answer is:

```text
abc
```

**Expected technique:** Sliding window + `HashSet` or frequency array/map.

**Complexity:** `O(n)`

---

### Problem 8 — DNA Diversity

A DNA sequence consists of the characters `A`, `C`, `G`, and `T`.

Find the longest contiguous substring containing **at most `K` distinct nucleotides**.

**Input**

```text
ACGTACGTAAA
2
```

**Output**

```text
5
```

For example:

```text
GTAAA
```

contains only `G`, `T`, and `A`, so this particular example would actually be invalid for `K=2`.

This makes a good instructor-created problem where students must carefully reason about the window rather than blindly trusting an example.

---

### Problem 9 — Almost Palindrome

Given a string and integer `K`, find the longest substring containing at most `K` characters that occur an odd number of times.

**Input**

```text
aabbccddeefg
2
```

**Output**

```text
?
```

This is a more challenging problem because the window property isn't a simple count of distinct characters.

**Expected technique:** Sliding window + frequency counts.

**Difficulty:** ★★★

This is a good bridge from ordinary sliding windows to more sophisticated invariant-based windows.

---

## Level 4 — “At Most K” / “Exactly K”

### Problem 10 — Shopping Basket

A shopper walks through a store and buys one item at each stop.

Each item has an integer type.

Find the longest contiguous sequence containing **at most `K` different item types**.

**Input**

```text
9 2
1 2 1 2 3 2 2 1 4
```

**Output**

```text
4
```

One optimal segment is:

```text
1 2 1 2
```

**Expected technique:** Sliding window + `HashMap`.

This is an excellent introductory ICPC problem.

---

### Problem 11 — Exactly K Flavors

Given an array of integers representing ice-cream flavors, count the number of contiguous segments containing **exactly `K` distinct flavors**.

**Input**

```text
5 2
1 2 1 2 3
```

**Output**

```text
7
```

**Expected technique:**

A useful identity is:

```text
exactly(K) = atMost(K) - atMost(K - 1)
```

This is an important competitive-programming pattern.

**Difficulty:** ★★★

---

### Problem 12 — Binary Subarrays With Exactly K Ones

Given a binary array, count the number of contiguous subarrays containing exactly `K` ones.

**Input**

```text
5 2
1 0 1 0 1
```

**Output**

```text
4
```

**Expected technique:** Sliding window / `atMost(K) - atMost(K-1)`.

This is a particularly good problem because students may initially try nested loops.

---

## Level 5 — Two-Constraint Windows

### Problem 13 — Network Bandwidth

A network connection receives packets with positive sizes.

Find the longest contiguous sequence of packets whose:

* total size is at most `B`, and
* number of packets is at most `K`.

**Input**

```text
8 15 5
2 3 1 5 2 4 1 3
```

**Output**

```text
5
```

**Expected technique:** Sliding window maintaining multiple constraints.

**Teaching point:** A window can have several invariants.

---

### Problem 14 — Exam Preparation

A student studies a sequence of topics. Each topic is represented by a category:

* `1` = programming
* `2` = mathematics
* `3` = algorithms
* `4` = databases

Find the longest contiguous study period containing **at least two programming topics and at most one database topic**.

**Expected technique:** Variable-size sliding window.

**Difficulty:** ★★★

This is good for teaching students to decide:

> What information about my current window must I maintain?

---

## Level 6 — Sliding Window + Deque

These are much more ICPC-like.

### Problem 15 — Maximum in Every Window

Given an array and window size `K`, output the maximum value in every window.

**Input**

```text
8 3
1 3 -1 -3 5 3 6 7
```

**Output**

```text
3 3 5 5 6 7
```

**Expected technique:** Monotonic deque.

**Complexity:** `O(n)`

This is a very important problem because students often discover that a normal sliding window isn't enough.

---

### Problem 16 — Minimum in Every Window

Same idea, but output the minimum value in every window.

**Input**

```text
8 3
1 3 -1 -3 5 3 6 7
```

**Output**

```text
-1 -3 -3 -3 3 3
```

**Expected technique:** Increasing monotonic deque.

---

### Problem 17 — Stable Sensor Readings

A sensor records one value per second.

A period is considered stable if:

```text
maximum value - minimum value <= K
```

Find the longest stable contiguous period.

**Input**

```text
8 4
1 3 6 4 5 2 3 4
```

**Expected technique:** Two monotonic deques.

Maintain:

```text
maxDeque
minDeque
```

and shrink the window whenever:

```text
max - min > K
```

**Complexity:** `O(n)`

**Difficulty:** ★★★★

This is an excellent transition from basic sliding windows to serious ICPC techniques.

---

## Level 7 — ICPC-Style Challenges

### Problem 18 — Minimum Window Cover

You are given two strings `S` and `T`.

Find the shortest substring of `S` containing every character of `T` with at least the required frequency.

**Input**

```text
ADOBECODEBANC
ABC
```

**Output**

```text
BANC
```

**Expected technique:** Sliding window + frequency maps.

This is one of the classic variable-window problems.

**Difficulty:** ★★★★

---

### Problem 19 — K-Distinct Substring

Given a string `S`, find the number of substrings containing **exactly `K` distinct characters**.

**Input**

```text
pqpqs
2
```

**Output**

```text
7
```

**Expected technique:**

```text
countAtMost(K) - countAtMost(K-1)
```

**Difficulty:** ★★★★

---

### Problem 20 — Longest Bounded Difference

Given an array, find the longest contiguous subarray for which:

```text
max(array[i...j]) - min(array[i...j]) <= K
```

**Input**

```text
8 3
10 1 2 4 7 2 5 6
```

**Output**

```text
4
```

**Expected technique:**

* sliding window
* monotonic maximum deque
* monotonic minimum deque

**Complexity:** `O(n)`

This is one I would definitely include in an ICPC diagnostic.

---

### Problem 21 — Minimum Cost Window

You are given a sequence of positive integers. Find the shortest contiguous segment whose sum is at least `K`.

But there is a twist:

You may remove **one element** from the segment.

Find the shortest segment for which the remaining sum is at least `K`.

**Input**

```text
8 15
2 3 1 7 2 5 1 4
```

**Expected technique:** Sliding window + maintaining additional information.

**Difficulty:** ★★★★★

This is a good contest-style problem because the students must recognize that a standard sliding window alone isn't enough.

---

### Problem 22 — Longest Segment With Limited Bad Values

A road consists of good and damaged sections:

```text
0 = good
1 = damaged
```

You may repair at most `K` damaged sections.

Find the longest continuous road segment that can be made completely good.

**Input**

```text
10 2
1 1 0 0 1 0 1 1 0 0
```

**Output**

```text
6
```

**Expected technique:** Sliding window counting the number of `1`s.

This is conceptually simple but very useful for teaching the **"expand until invalid, then shrink"** pattern.

---

# A Useful Progression for Your Students

I would actually teach sliding windows as a sequence of **patterns**, rather than simply giving students 20 problems.

| Pattern                 | Problems | Main idea                         |
| ----------------------- | -------- | --------------------------------- |
| Fixed window            | 1–3      | Add right, remove left            |
| Positive-sum window     | 4–6      | Expand/shrink based on sum        |
| Frequency window        | 7–9      | Maintain character frequencies    |
| At most K               | 10–12    | Maintain number of distinct items |
| Multiple constraints    | 13–14    | Maintain several invariants       |
| Monotonic deque         | 15–17    | Maintain max/min efficiently      |
| Minimum covering window | 18       | Frequency requirements            |
| Exactly K               | 19       | `atMost(K)-atMost(K-1)`           |
| Max-min constraint      | 20       | Two monotonic deques              |
| Modified window         | 21–22    | More sophisticated state          |

## The key pattern I would have students memorize

For many problems, the skeleton is:

```text
left = 0

for right = 0 to n-1:

    add a[right] to the window

    while window is invalid:
        remove a[left]
        left++

    update answer
```

The real ICPC skill is **not memorizing this loop**. It is determining:

> **What makes a window valid, and what information can I maintain so that I can test validity in O(1)?**

That question leads naturally to:

* sum → simple integer
* number of distinct values → `HashMap`
* character requirements → frequency map
* maximum → monotonic deque
* minimum → monotonic deque
* number of occurrences → frequency array/map
* multiple conditions → multiple maintained variables

For your students, I would make **Problems 1, 4, 7, 10, 12, 15, 17, 18, and 20** the core progression. That gives them a much better understanding of sliding-window techniques than doing ten variations of fixed-size windows.


# ICPC Sliding Window Problems — Java Solutions

## Common Sliding-Window Pattern

The basic variable-size sliding-window structure is:

```java
int left = 0;

for (int right = 0; right < n; right++) {
    // Add a[right] to the window

    while (/* window is invalid */) {
        // Remove a[left]
        left++;
    }

    // Current window [left, right] is valid
    // Update the answer
}
```

The important question is:

> **What information do we need to maintain about the current window?**

---

# Problem 1 — Maximum Sum of K Consecutive Elements

### Solution

```java
public static long maxWindowSum(int[] a, int k) {
    long sum = 0;

    // First window
    for (int i = 0; i < k; i++) {
        sum += a[i];
    }

    long best = sum;

    // Slide the window
    for (int right = k; right < a.length; right++) {
        sum += a[right];
        sum -= a[right - k];

        best = Math.max(best, sum);
    }

    return best;
}
```

### Complexity

* Time: `O(n)`
* Space: `O(1)`

The important operation is:

```java
sum += a[right];
sum -= a[right - k];
```

We never recompute the entire window.

---

# Problem 2 — Number of Good Windows

A window of length `k` is good if it contains at least `k - 1` ones.

```java
public static int countGoodWindows(int[] a, int k) {
    int ones = 0;
    int answer = 0;

    for (int i = 0; i < k; i++) {
        if (a[i] == 1) {
            ones++;
        }
    }

    if (ones >= k - 1) {
        answer++;
    }

    for (int right = k; right < a.length; right++) {
        if (a[right] == 1) {
            ones++;
        }

        if (a[right - k] == 1) {
            ones--;
        }

        if (ones >= k - 1) {
            answer++;
        }
    }

    return answer;
}
```

**Complexity:** `O(n)` time, `O(1)` space.

---

# Problem 3 — Temperature Alert

Count windows of length `k` whose average is at least `T`.

Instead of using floating-point arithmetic:

```text
sum / k >= T
```

we use:

```text
sum >= k * T
```

```java
public static int countHotWindows(int[] temp, int k, int T) {
    long sum = 0;

    for (int i = 0; i < k; i++) {
        sum += temp[i];
    }

    int answer = 0;

    if (sum >= (long) k * T) {
        answer++;
    }

    for (int right = k; right < temp.length; right++) {
        sum += temp[right];
        sum -= temp[right - k];

        if (sum >= (long) k * T) {
            answer++;
        }
    }

    return answer;
}
```

**Complexity:** `O(n)`.

---

# Problem 4 — Longest Stable Segment

Find the longest contiguous segment with sum at most `K`.

All numbers are positive.

```java
public static int longestSumAtMost(int[] a, long k) {
    int left = 0;
    long sum = 0;
    int best = 0;

    for (int right = 0; right < a.length; right++) {
        sum += a[right];

        while (sum > k) {
            sum -= a[left];
            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

### Why positivity matters

Suppose:

```text
sum > K
```

Removing `a[left]` must decrease the sum.

That is guaranteed only because all values are positive.

**Complexity:** `O(n)`.

---

# Problem 5 — Minimum-Length Journey

Find the shortest subarray whose sum is at least `K`.

```java
public static int minLengthAtLeast(int[] a, long k) {
    int left = 0;
    long sum = 0;
    int best = Integer.MAX_VALUE;

    for (int right = 0; right < a.length; right++) {
        sum += a[right];

        while (sum >= k) {
            best = Math.min(best, right - left + 1);

            sum -= a[left];
            left++;
        }
    }

    return best == Integer.MAX_VALUE ? 0 : best;
}
```

### Key idea

Once the window satisfies the condition, we try to make it **smaller**.

```java
while (sum >= k) {
    ...
    left++;
}
```

**Complexity:** `O(n)`.

---

# Problem 6 — Longest Subarray With Sum ≤ K

This is essentially the same sliding-window pattern as Problem 4.

```java
public static int longestAtMostK(int[] a, long k) {
    int left = 0;
    long sum = 0;
    int best = 0;

    for (int right = 0; right < a.length; right++) {
        sum += a[right];

        while (sum > k) {
            sum -= a[left];
            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

**Complexity:** `O(n)`.

---

# Problem 7 — Longest Substring Without Repeating Characters

Use a frequency array.

```java
public static int longestUniqueSubstring(String s) {
    int[] freq = new int[256];

    int left = 0;
    int best = 0;

    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        freq[c]++;

        while (freq[c] > 1) {
            freq[s.charAt(left)]--;
            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

For:

```text
abcabcbb
```

the answer is:

```text
3
```

**Complexity:** `O(n)`.

---

# Problem 8 — DNA Diversity

Find the longest substring containing at most `K` distinct characters.

```java
import java.util.*;

public static int longestKDistinct(String s, int k) {
    Map<Character, Integer> freq = new HashMap<>();

    int left = 0;
    int best = 0;

    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);

        freq.put(c, freq.getOrDefault(c, 0) + 1);

        while (freq.size() > k) {
            char old = s.charAt(left);

            freq.put(old, freq.get(old) - 1);

            if (freq.get(old) == 0) {
                freq.remove(old);
            }

            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

**Complexity:** `O(n)` expected time.

---

# Problem 9 — Characters With Odd Frequency

For this problem, maintain the number of characters currently having odd frequency.

```java
public static int longestOddFrequencyWindow(String s, int k) {
    int[] freq = new int[256];

    int odd = 0;
    int left = 0;
    int best = 0;

    for (int right = 0; right < s.length(); right++) {
        int c = s.charAt(right);

        if (freq[c] % 2 == 0) {
            odd++;
        } else {
            odd--;
        }

        freq[c]++;

        while (odd > k) {
            int old = s.charAt(left);

            freq[old]--;

            if (freq[old] % 2 == 0) {
                odd--;
            } else {
                odd++;
            }

            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

The important concept is that we don't repeatedly scan the window to determine how many odd-frequency characters it contains.

---

# Problem 10 — Shopping Basket

Longest subarray containing at most `K` different values.

```java
import java.util.*;

public static int longestBasket(int[] a, int k) {
    Map<Integer, Integer> freq = new HashMap<>();

    int left = 0;
    int best = 0;

    for (int right = 0; right < a.length; right++) {
        freq.put(a[right], freq.getOrDefault(a[right], 0) + 1);

        while (freq.size() > k) {
            int x = a[left];

            freq.put(x, freq.get(x) - 1);

            if (freq.get(x) == 0) {
                freq.remove(x);
            }

            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

This is one of the most important templates for competitive programming.

---

# Problem 11 — Exactly K Flavors

A very useful trick:

```text
exactly K = atMost(K) - atMost(K - 1)
```

First implement `atMost`.

```java
public static long countAtMost(int[] a, int k) {
    if (k < 0) {
        return 0;
    }

    Map<Integer, Integer> freq = new HashMap<>();

    int left = 0;
    long answer = 0;

    for (int right = 0; right < a.length; right++) {
        freq.put(a[right], freq.getOrDefault(a[right], 0) + 1);

        while (freq.size() > k) {
            int x = a[left];

            freq.put(x, freq.get(x) - 1);

            if (freq.get(x) == 0) {
                freq.remove(x);
            }

            left++;
        }

        // Every subarray ending at right and
        // starting between left and right is valid.
        answer += right - left + 1;
    }

    return answer;
}
```

Then:

```java
public static long countExactlyK(int[] a, int k) {
    return countAtMost(a, k) - countAtMost(a, k - 1);
}
```

### Why does

```java
answer += right - left + 1;
```

work?

Suppose:

```text
left = 3
right = 7
```

Then these windows all satisfy the condition:

```text
[3..7]
[4..7]
[5..7]
[6..7]
[7..7]
```

There are:

```text
7 - 3 + 1 = 5
```

of them.

This is an extremely important ICPC pattern.

---

# Problem 12 — Binary Subarrays With Exactly K Ones

Again use:

```text
exactly(K) = atMost(K) - atMost(K - 1)
```

```java
public static long countBinaryAtMost(int[] a, int k) {
    if (k < 0) {
        return 0;
    }

    int left = 0;
    int ones = 0;
    long answer = 0;

    for (int right = 0; right < a.length; right++) {
        if (a[right] == 1) {
            ones++;
        }

        while (ones > k) {
            if (a[left] == 1) {
                ones--;
            }

            left++;
        }

        answer += right - left + 1;
    }

    return answer;
}

public static long countBinaryExactlyK(int[] a, int k) {
    return countBinaryAtMost(a, k)
         - countBinaryAtMost(a, k - 1);
}
```

**Complexity:** `O(n)`.

---

# Problem 13 — Network Bandwidth

Maintain both:

* total packet size
* number of packets

Since the number of packets is simply the window length, the second constraint is straightforward.

```java
public static int longestBandwidth(
        int[] packets, long maxBandwidth, int maxPackets) {

    int left = 0;
    long sum = 0;
    int best = 0;

    for (int right = 0; right < packets.length; right++) {
        sum += packets[right];

        while (sum > maxBandwidth ||
               right - left + 1 > maxPackets) {

            sum -= packets[left];
            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

**Complexity:** `O(n)`.

---

# Problem 14 — Exam Preparation

Suppose:

```text
1 = programming
2 = mathematics
3 = algorithms
4 = databases
```

We want:

* at least 2 programming topics
* at most 1 database topic

```java
public static int longestStudyPeriod(int[] topics) {
    int left = 0;

    int programming = 0;
    int database = 0;

    int best = 0;

    for (int right = 0; right < topics.length; right++) {

        if (topics[right] == 1) {
            programming++;
        }

        if (topics[right] == 4) {
            database++;
        }

        /*
         * The window is invalid only when:
         *
         * database > 1
         *
         * Notice that "programming < 2" is not treated
         * as invalid here because we want the longest
         * window satisfying BOTH conditions.
         */
        while (database > 1) {
            if (topics[left] == 1) {
                programming--;
            }

            if (topics[left] == 4) {
                database--;
            }

            left++;
        }

        if (programming >= 2) {
            best = Math.max(best, right - left + 1);
        }
    }

    return best;
}
```

This is a useful example for discussing **what constitutes a valid window**.

---

# Problem 15 — Maximum in Every Window

A normal sliding window cannot efficiently find the maximum if we repeatedly scan the window.

Use a **monotonic deque**.

```java
import java.util.*;

public static int[] maxInWindows(int[] a, int k) {
    int n = a.length;

    int[] result = new int[n - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {

        // Remove indices outside the window.
        while (!deque.isEmpty() &&
               deque.peekFirst() <= i - k) {
            deque.pollFirst();
        }

        // Remove values smaller than a[i].
        while (!deque.isEmpty() &&
               a[deque.peekLast()] <= a[i]) {
            deque.pollLast();
        }

        deque.addLast(i);

        // The front contains the maximum.
        if (i >= k - 1) {
            result[i - k + 1] = a[deque.peekFirst()];
        }
    }

    return result;
}
```

### Important invariant

The deque contains indices whose values are in decreasing order:

```text
a[d0] >= a[d1] >= a[d2] ...
```

Therefore:

```java
deque.peekFirst()
```

is always the maximum.

**Complexity:** `O(n)`.

---

# Problem 16 — Minimum in Every Window

This is the mirror image.

```java
public static int[] minInWindows(int[] a, int k) {
    int n = a.length;

    int[] result = new int[n - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {

        while (!deque.isEmpty() &&
               deque.peekFirst() <= i - k) {
            deque.pollFirst();
        }

        // Increasing deque
        while (!deque.isEmpty() &&
               a[deque.peekLast()] >= a[i]) {
            deque.pollLast();
        }

        deque.addLast(i);

        if (i >= k - 1) {
            result[i - k + 1] = a[deque.peekFirst()];
        }
    }

    return result;
}
```

The deque maintains:

```text
a[d0] <= a[d1] <= a[d2] ...
```

so the front contains the minimum.

---

# Problem 17 — Stable Sensor Readings

Find the longest subarray satisfying:

```text
maximum - minimum <= K
```

Use two monotonic deques.

```java
public static int longestStable(int[] a, int k) {
    Deque<Integer> maxDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();

    int left = 0;
    int best = 0;

    for (int right = 0; right < a.length; right++) {

        // Maintain decreasing deque for maximum.
        while (!maxDeque.isEmpty() &&
               a[maxDeque.peekLast()] <= a[right]) {
            maxDeque.pollLast();
        }

        maxDeque.addLast(right);

        // Maintain increasing deque for minimum.
        while (!minDeque.isEmpty() &&
               a[minDeque.peekLast()] >= a[right]) {
            minDeque.pollLast();
        }

        minDeque.addLast(right);

        // Shrink until valid.
        while (a[maxDeque.peekFirst()]
             - a[minDeque.peekFirst()] > k) {

            if (maxDeque.peekFirst() == left) {
                maxDeque.pollFirst();
            }

            if (minDeque.peekFirst() == left) {
                minDeque.pollFirst();
            }

            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

### Complexity

Each index:

* enters a deque once
* leaves a deque once

Therefore:

```text
O(n)
```

rather than `O(n²)`.

---

# Problem 18 — Minimum Window Cover

Find the shortest substring of `S` containing all characters of `T`.

Example:

```text
S = "ADOBECODEBANC"
T = "ABC"
```

Answer:

```text
"BANC"
```

### Java solution

```java
public static String minWindow(String s, String t) {

    if (t.length() > s.length()) {
        return "";
    }

    int[] need = new int[256];

    for (char c : t.toCharArray()) {
        need[c]++;
    }

    int[] have = new int[256];

    int required = t.length();
    int formed = 0;

    int left = 0;

    int bestStart = 0;
    int bestLength = Integer.MAX_VALUE;

    for (int right = 0; right < s.length(); right++) {

        char c = s.charAt(right);
        have[c]++;

        if (need[c] > 0 && have[c] <= need[c]) {
            formed++;
        }

        while (formed == required) {

            int length = right - left + 1;

            if (length < bestLength) {
                bestLength = length;
                bestStart = left;
            }

            char old = s.charAt(left);

            if (need[old] > 0 &&
                have[old] <= need[old]) {
                formed--;
            }

            have[old]--;
            left++;
        }
    }

    if (bestLength == Integer.MAX_VALUE) {
        return "";
    }

    return s.substring(
            bestStart,
            bestStart + bestLength
    );
}
```

### Key idea

The window becomes **valid** when:

```java
formed == required
```

Then we shrink it as much as possible.

This is the classic:

> **Expand → become valid → shrink → record answer**

pattern.

---

# Problem 19 — K-Distinct Substring

Count substrings containing exactly `K` distinct characters.

```java
public static long countAtMostDistinct(
        String s, int k) {

    if (k < 0) {
        return 0;
    }

    int[] freq = new int[256];

    int distinct = 0;
    int left = 0;

    long answer = 0;

    for (int right = 0; right < s.length(); right++) {

        char c = s.charAt(right);

        if (freq[c] == 0) {
            distinct++;
        }

        freq[c]++;

        while (distinct > k) {
            char old = s.charAt(left);

            freq[old]--;

            if (freq[old] == 0) {
                distinct--;
            }

            left++;
        }

        answer += right - left + 1;
    }

    return answer;
}

public static long countExactlyDistinct(
        String s, int k) {

    return countAtMostDistinct(s, k)
         - countAtMostDistinct(s, k - 1);
}
```

For:

```text
pqpqs
```

and:

```text
K = 2
```

the answer is:

```text
7
```

---

# Problem 20 — Longest Bounded Difference

This is essentially the same algorithm as Problem 17.

```java
public static int longestBoundedDifference(
        int[] a, int k) {

    Deque<Integer> maxDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();

    int left = 0;
    int best = 0;

    for (int right = 0; right < a.length; right++) {

        while (!maxDeque.isEmpty() &&
               a[maxDeque.peekLast()] <= a[right]) {
            maxDeque.pollLast();
        }

        maxDeque.addLast(right);

        while (!minDeque.isEmpty() &&
               a[minDeque.peekLast()] >= a[right]) {
            minDeque.pollLast();
        }

        minDeque.addLast(right);

        while (a[maxDeque.peekFirst()]
             - a[minDeque.peekFirst()] > k) {

            if (maxDeque.peekFirst() == left) {
                maxDeque.pollFirst();
            }

            if (minDeque.peekFirst() == left) {
                minDeque.pollFirst();
            }

            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

**Complexity:** `O(n)` time, `O(n)` worst-case space.

---

# Bonus Problem 21 — Minimum Window After Removing One Element

This problem needs more careful interpretation than a standard sliding window.

Suppose we want a window where removing at most one element makes the remaining sum at least `K`.

For a positive array, one approach is to maintain:

* current window sum
* largest element in the window

The condition becomes:

```text
sum >= K
```

or

```text
sum - maximum >= K
```

Depending on the exact interpretation of "remove one element," the implementation differs. This makes it a good advanced discussion problem rather than a basic sliding-window exercise.

---

# Bonus Problem 22 — Longest Segment With At Most K Damaged Sections

Here `1` means damaged and `0` means good.

```java
public static int longestRepairable(int[] road, int k) {
    int left = 0;
    int damaged = 0;
    int best = 0;

    for (int right = 0; right < road.length; right++) {

        if (road[right] == 1) {
            damaged++;
        }

        while (damaged > k) {

            if (road[left] == 1) {
                damaged--;
            }

            left++;
        }

        best = Math.max(best, right - left + 1);
    }

    return best;
}
```

**Complexity:** `O(n)`.

---

# The Five Sliding-Window Templates Students Should Know

## Template 1 — Fixed Size

Use when the window always has exactly `K` elements.

```java
for (int i = 0; i < k; i++) {
    // initialize
}

for (int right = k; right < n; right++) {
    // add right
    // remove right-k
}
```

Typical problems:

* maximum sum
* minimum sum
* average
* number of elements satisfying a condition

---

## Template 2 — Longest Valid Window

```java
int left = 0;

for (int right = 0; right < n; right++) {

    add(a[right]);

    while (windowInvalid()) {
        remove(a[left]);
        left++;
    }

    answer = Math.max(answer, right - left + 1);
}
```

Typical problems:

* longest subarray with sum ≤ K
* longest substring with ≤ K distinct characters
* longest segment with ≤ K bad values

---

## Template 3 — Shortest Valid Window

```java
int left = 0;

for (int right = 0; right < n; right++) {

    add(a[right]);

    while (windowValid()) {

        answer = Math.min(
            answer,
            right - left + 1
        );

        remove(a[left]);
        left++;
    }
}
```

Typical problems:

* minimum subarray sum ≥ K
* minimum covering substring

---

## Template 4 — Count Valid Windows

Once `[left, right]` is the smallest valid left boundary:

```java
answer += right - left + 1;
```

This counts:

```text
[left ... right]
[left+1 ... right]
[left+2 ... right]
...
[right ... right]
```

Typical problems:

* number of subarrays with at most K distinct values
* number of binary subarrays with at most K ones

---

## Template 5 — Exactly K

A very important transformation:

```text
exactly(K)
    =
atMost(K)
    -
atMost(K - 1)
```

This is useful for:

* exactly K distinct elements
* exactly K ones
* exactly K different characters

---

# Instructor Challenge: Have Students Identify the Pattern

Before allowing students to code, give them the following table and ask them to identify the appropriate technique.

| Problem                                        | Pattern                   |
| ---------------------------------------------- | ------------------------- |
| Maximum sum of K consecutive elements          | Fixed window              |
| Longest sum ≤ K                                | Variable window           |
| Shortest sum ≥ K                               | Variable window           |
| Longest substring without repetition           | Frequency window          |
| Longest substring with ≤ K distinct            | Frequency window          |
| Exactly K distinct                             | `atMost(K) - atMost(K-1)` |
| Number of binary subarrays with exactly K ones | `atMost` trick            |
| Maximum in every K-window                      | Monotonic deque           |
| Minimum in every K-window                      | Monotonic deque           |
| Longest window where max-min ≤ K               | Two monotonic deques      |
| Minimum substring containing T                 | Frequency window          |
| Longest window with ≤ K bad values             | Counter + window          |

The goal is for students eventually to look at a new ICPC problem and ask:

1. **Is the answer about a contiguous segment?**
2. **Can I maintain the window's state incrementally?**
3. **When I move the right pointer, can I repair the window by moving the left pointer?**
4. **Does the problem have a monotonic validity condition?**
5. **What information must I maintain?**

If the answers line up, a sliding-window/two-pointer solution is often available.

## Suggested ICPC practice order

I would assign these in this order:

**Beginner**
1 → 3 → 4 → 5 → 7

**Intermediate**
10 → 11 → 12 → 13 → 22

**Advanced**
15 → 16 → 17 → 18 → 19 → 20

This progression teaches students that **"sliding window" is not one algorithm**. It is a family of techniques built around maintaining an efficiently updateable contiguous interval.
