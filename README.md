# Minesweeper
Development Tool(s) and Language： Java, IntelliJ IDEA 

## 1. Algorithm(s)

### 1.1 Brute Force

When calculating the number of mines around each unit block, I use the algorithm -- bruce force. For each unit block M, traversing the eight surrounding blocks (up, down, left, right, left-up, left-down, right-up, right-down), and determining whether they are mines. For each surrounding block, if it is the mine, the number around M is increased by one.

```java
public static void computeBomb(MineLabel[][] label) {
  for (int i = 0; i < label.length; i++) {
   for (int j = 0; j < label[i].length; j++) {
     if (!label[i][j].isMineTag()) {
      int count = 0; // count the number of mines around
      for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.totalRow - 1, i + 1); x++) {
        for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.totalColumn - 1, j + 1); y++) {

// (x, y) is a mine, count++
         if (label[x][y].isMineTag()) {
           count++;
         }
        }
      }
      label[i][j].setCountAround(count);
     }
   }
  }
}
```

the complexity: O(nm)

 

### 1.2 Depth-First-Search (DFS)

When a unit block with no mine around is clicked, I use the algorithm -- Depth-First-Search. For the block at position (x, y), if the number of mines around is 0, it should shown firstly, and then, for the eight surrounding blocks (up, down, left, right, left-up, left-down, right-up, right-down), determining whether it is a block with no mine around. If it is, DFS its eight surrounding blocks until the block with mines around

```java
private void expand(int x, int y) {
  int count = mineLabel[x][y].getCountAround();
  if (!mineLabel[x][y].isExpendTag() && !mineLabel[x][y].isFlagTag()) {
   if (count == 0) {
     mineLabel[x][y].setIcon(StaticTool.num[count]);
     mineLabel[x][y].setExpendTag(true);
     for (int i = Math.max(0, x - 1); i <= Math.min(mineLabel.length - 1, x + 1); i++) {
      for (int j = Math.max(0, y - 1); j <= Math.min(mineLabel[x].length - 1, y + 1); j++) {
        expand(i, j);
      }
     }
   } else {
     mineLabel[x][y].setIcon(StaticTool.num[count]);
     mineLabel[x][y].setExpendTag(true);
   }
  }
}
```

the complexity: O(nm)

 

## 2. Development Procedure

### 2.1 Game Introduction

- Click the menu "Game" -> "Start" to start a new game.
- Click the menu "Game" -> "Primary", enter the primary game interface;
- Click the menu "Game" -> "Intermediate", enter the intermediate game interface;
- Click the menu "Game" -> "Senior" to enter the senior game interface;
- Click the menu "Game" -> "Custom", enter the custom game control interface;
- Click the menu "Game" -> "Quit", quit the game;
- Click the menu "Help" -> "About", a mode window pops up, in the window display version copyright information;
- Click the menu "Help" -> "Backdoor", automatically conduct mine sweeper.
- Left-click the mine field unit to expand the current mine block.
- Right-click the mine field unit blocks, click the flag once, mark it as a question mark twice, and restore the initial blank mine block three times.
- Left-click and right-click the mine-field unit blocks at the same time. If no number is displayed under the mine-field, there is no response on the interface. If there is a number displayed under the minefield, detect whether there is lightning in the 8 grid area around the minefield. If all the surrounding areas have been explored, open the safe minefield.

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml11680\wps1.jpg) 

Figure 1.1. Game Interface

After click the menu "Help" -> "Backdoor", click the Smile, the game will start automatically.

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml11680\wps2.jpg) 

Figure 1.2 Auto Sweeper

### 2.2 Project Introduction

2.2.1 Software Framework Diagram

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml11680\wps3.jpg) 

Figure 2.1. Software Framework Diagram

2.2.2 Development Environment

·Language: Java

·ntegrated Development Environment: IntelliJ IDEA.

·JDK ≥ 1.7

·Maven ≥ 3.0

2.2.3 Class Block Diagram

·three Panels

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml11680\wps4.jpg) 

Figure 2.2 three Panels

·Main Window

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml11680\wps5.jpg) 

Figure 2.3 Main Window

 

### 2.3 What I learned

Through this project development, in addition to using Java to write a game, I learned how to choose a more appropriate algorithm by analyzing and comparing the complexity of different algorithms. In addition, I also have a deeper understanding of Java multithreading. Otherwise, I learned more about algorithms like Brute Force and Depth-First-Search.

 

## 3. Problems and Solutions

(1) Problem: At first, I didn't know how to use Java to make a timer.

Solution: I searched related books and literature on the Internet, and finally chose the way of timer listener to complete the timer.

(2) Problem: After all things done except auto sweeping mines, I didn;t konw how to completethe method of automatic mine sweeper.

Solution: After many times of mine clearance, I came to a conclusion: for the currently opened block, if the number of red flags around is equal to the number of the current block, open the surrounding unopened block; If the number of open squares is equal to the number of open squares minus the number of current squares, set flags for all open squares. Of course, this is the simplest method of automatic mine sweeper, and it may have problems, but it is the best I have come up with.

 

## 4. References

[1] D.L. Parnas. On the Criteria To Be Used in Decomposing Systems into Modules[J].Communications of the ACM, Vol. 15, No. 12, December 1972 pp. 1053 – 1058

[2] Tony Mott.1001 Video Games You Must Play Before You Die[M]. 2 Aug 2010. Cassell Illustrated.

[3] Abbas Mr Ansar, Eliyana Prof Anis,Ekowati Dr Dian,Saud Mr Muhammad, Raza Mr Ali,Wardani Ms Ratna. Data set on coping strategies in the digital age: The role of psychological well-being and social capital among university students in Java Timor, Surabaya, Indonesia.[J]. Data in brief,2020,30.

…

 
