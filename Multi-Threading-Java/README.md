
 Instance / non Static Methods without Synchronization :
						Always executes simultaneously 
 
 * Two Threads on One Object  --> Executes simultaneously --> Data Inconsistency
 
 * Two Threads on Two objects --> Executes simultaneously --> Data Inconsistency
 
 
 Instance / non Static Methods with Synchronous:
  	
 			Internally JVM Uses Object level Locking
 
 * Two Threads on One Object  --> Executes Synchronously (one after another ) --> Data Consistency
 
 * Two Threads on Two objects --> Executes simultaneously --> Data Inconsistency
   
  The same can be achieved by Synchronous blocks with object level lock ( same object use this --> other object then use reference) 
 
  synchronized(this){
 ---
 ---
 ---
 //code 
 }
 
 Display d  = new Display()
 
   synchronized(d){
 ---
 ---
 ---
 //code 
 }
 
 
Class / Static Methods with Synchronous:
 	
 			Internally JVM Uses Class level Locking

 * Two Threads on One Object  --> Executes Synchronously (one after another ) --> Data Consistency
 
 * Two Threads on Two objects --> Executes Synchronously (one after another  ) --> Data Consistency
 
 The same can be achieved by Synchronous blocks with Class level lock
 
 synchronized(Display.class){
 ---
 ---
 ---
 //code 
 }
 
 =============================
 
 # Java Synchronization — Object-Level and Class-Level Locking

## 1. Instance / Non-Static Method WITHOUT Synchronization

An instance (non-static) method without the `synchronized` keyword does **not acquire any lock**.

```java
public void display() {
    // code
}
```

### Case 1: Two Threads → One Object

```text
Thread-1 ──┐
           ├──> Same Object ──> display()
Thread-2 ──┘
```

Both threads can execute the method **simultaneously**.

If both threads modify the same shared instance data, it may result in:

* Race Condition
* Data Inconsistency

### Case 2: Two Threads → Two Objects

```text
Thread-1 ──> Object-1 ──> display()

Thread-2 ──> Object-2 ──> display()
```

Both threads can execute **simultaneously** because there is no synchronization.

> **Important:** Different objects do not share an instance lock. Whether data becomes inconsistent depends on whether the threads are actually accessing shared data.

---

# 2. Instance / Non-Static Method WITH Synchronization

An instance method declared with `synchronized` uses **object-level locking**.

```java
public synchronized void display() {
    // code
}
```

Internally, the lock is acquired on the **current object (`this`)**.

Conceptually:

```java
public synchronized void display() {
    // code
}
```

is equivalent to:

```java
public void display() {

    synchronized (this) {
        // code
    }
}
```

---

## Case 1: Two Threads → One Object

```text
Thread-1 ──┐
           ├──> Same Object
Thread-2 ──┘
                ↓
           Object Lock
```

Both threads try to acquire the **same object lock**.

```text
Thread-1 → Acquires Lock → Executes
                         ↓
                      Releases
                         ↓
Thread-2 → Acquires Lock → Executes
```

Therefore:

* Threads execute **one after another**
* Mutual exclusion is provided
* Properly synchronized shared state can remain consistent

---

## Case 2: Two Threads → Two Objects

```text
Thread-1 ──> Object-1 ──> Lock-1

Thread-2 ──> Object-2 ──> Lock-2
```

Each object has its own lock.

Therefore:

```text
Thread-1 → Lock-1 → Executes
Thread-2 → Lock-2 → Executes
```

Both threads can execute **simultaneously**.

### Key Rule

```text
Synchronized Instance Method
            ↓
     Object-Level Lock
            ↓
   ┌────────┴─────────┐
Same Object       Different Objects
    ↓                    ↓
Same Lock            Different Locks
    ↓                    ↓
 Wait                  No Wait
```

---

# 3. Synchronized Block — Object-Level Lock

The same object-level locking can be explicitly achieved using a synchronized block.

## Using `this`

```java
synchronized (this) {
    // code
}
```

Here, `this` represents the current object.

```text
synchronized(this)
        ↓
Current Object's Lock
```

Example:

```java
public void display() {

    synchronized (this) {
        System.out.println("Executing...");
    }
}
```

---

## Using a Specific Object

We can also synchronize using a specific object reference.

```java
Display d = new Display();

synchronized (d) {
    // code
}
```

Here, the lock is acquired on object `d`.

```text
synchronized(d)
       ↓
    Lock of d
```

Only threads trying to synchronize on the **same `d` object** will block each other.

---

# 4. Static / Class Method WITH Synchronization

A static synchronized method uses **class-level locking**.

```java
public static synchronized void display() {
    // code
}
```

The lock is acquired on the `Class` object.

For example:

```java
Display.class
```

Conceptually:

```java
public static synchronized void display() {
    // code
}
```

is equivalent to:

```java
public static void display() {

    synchronized (Display.class) {
        // code
    }
}
```

---

# 5. Static Synchronized Method — Two Threads on One Object

Suppose:

```java
Display d = new Display();
```

and two threads invoke:

```java
d.display();
```

where `display()` is:

```java
public static synchronized void display() {
    // code
}
```

Both threads use the same class-level lock:

```text
Thread-1 ──┐
           ├──> Display.class Lock
Thread-2 ──┘
```

Therefore:

```text
Thread-1 → Acquires Class Lock → Executes
                              ↓
                           Releases
                              ↓
Thread-2 → Acquires Class Lock → Executes
```

They execute **one after another**.

---

# 6. Static Synchronized Method — Two Threads on Two Objects

Suppose:

```java
Display d1 = new Display();
Display d2 = new Display();
```

Two threads invoke the static synchronized method:

```text
Thread-1 → d1.display()
Thread-2 → d2.display()
```

Even though `d1` and `d2` are different objects, a static synchronized method uses the **same class-level lock**:

```text
             Display.class
                  ↓
             Class Lock
              ↙       ↘
          Thread-1   Thread-2
```

Therefore:

* Both threads compete for the same class lock
* They execute **one after another**
* Different objects do **not** give them different locks

### Key Rule

```text
Static Synchronized Method
            ↓
      Class-Level Lock
            ↓
       Display.class
            ↓
   Same lock for all objects
```

---

# 7. Synchronized Block — Class-Level Lock

The same class-level locking can be explicitly achieved using:

```java
synchronized (Display.class) {
    // code
}
```

Example:

```java
public static void display() {

    synchronized (Display.class) {
        System.out.println("Executing...");
    }
}
```

Here:

```text
synchronized(Display.class)
            ↓
      Class-Level Lock
```

Any thread trying to enter another synchronized block using the same class object must wait until the lock is released.

---

# 8. Object-Level Lock vs Class-Level Lock

```text
                 SYNCHRONIZATION
                        |
            ┌───────────┴───────────┐
            |                       |
       Object-Level             Class-Level
          Lock                     Lock
            |                       |
     Non-Static Method        Static Method
            |                       |
     synchronized(this)     synchronized(Display.class)
            |                       |
       Specific Object           Class Object
            |                       |
  Different objects have     All objects share
     different locks           same class lock
```

---

# 9. Complete Comparison

| Type                           | Lock Used   | Two Threads → Same Object | Two Threads → Different Objects        |
| ------------------------------ | ----------- | ------------------------- | -------------------------------------- |
| Instance method                | No Lock     | Simultaneous              | Simultaneous                           |
| `synchronized` instance method | Object Lock | Sequential                | Simultaneous                           |
| `synchronized(this)`           | Object Lock | Sequential                | Simultaneous                           |
| `synchronized(obj)`            | `obj` Lock  | Sequential if same `obj`  | Simultaneous if different lock objects |
| `static synchronized` method   | Class Lock  | Sequential                | Sequential                             |
| `synchronized(Display.class)`  | Class Lock  | Sequential                | Sequential                             |

---

# 10. Easy Interview Shortcut

Remember these two rules:

```text
NON-STATIC synchronized
          ↓
    OBJECT-LEVEL LOCK
          ↓
    Same Object
          ↓
      Same Lock
          ↓
    One at a time
```

```text
STATIC synchronized
          ↓
     CLASS-LEVEL LOCK
          ↓
     Display.class
          ↓
      Same Lock
          ↓
    One at a time
```

---

# 11. Most Important Difference

### Instance `synchronized`

```java
synchronized void display() {
    // code
}
```

Equivalent to:

```java
synchronized (this) {
    // code
}
```

**Lock = Current Object**

### Static `synchronized`

```java
static synchronized void display() {
    // code
}
```

Equivalent to:

```java
synchronized (Display.class) {
    // code
}
```

**Lock = Class Object**

---

# 12. Final Summary

```text
┌───────────────────────────────────────────────┐
│          INSTANCE SYNCHRONIZATION             │
├───────────────────────────────────────────────┤
│ synchronized instance method                  │
│                 ↓                             │
│          Object-Level Lock                    │
│                 ↓                             │
│ Same Object → Threads wait                   │
│ Different Objects → Different locks          │
│                 ↓                             │
│ Threads can execute simultaneously            │
└───────────────────────────────────────────────┘


┌───────────────────────────────────────────────┐
│           CLASS SYNCHRONIZATION               │
├───────────────────────────────────────────────┤
│ static synchronized method                    │
│                 ↓                             │
│          Class-Level Lock                     │
│                 ↓                             │
│       Display.class Lock                      │
│                 ↓                             │
│ All objects share the same class lock         │
│                 ↓                             │
│ Threads execute one after another             │
└───────────────────────────────────────────────┘
```

## Golden Rule

> **Instance `synchronized` → Object Lock**
> **Static `synchronized` → Class Lock**

## Important Note

Synchronization provides **mutual exclusion and memory-visibility guarantees** when the same lock is used consistently. It does not automatically guarantee data consistency if shared data is accessed through unsynchronized paths.
 