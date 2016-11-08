Name: Caroline Kessler
Lab: noon-2 lab (I used a late day for this assignment)

THOUGHT QUESTIONS:
1. There are 6003 board  positions for the 3 x 4 board. There are 215150 moves for a 3 x 5 board.
   Both can be determined using the size method.
2. When two computers are playing against each, as with HIM and HER, the one who will ultimately
   win more frequently is the one that learns the quickest. The one that will learn the quickest
   is the one who starts out with the lower probability of winning. HIM, the one who starts
   first, has the greatest probability from the start of winning in a 3x3 GameBoard,so  HER will
   learn more quickly and therefore will win most frequently. For larger GameBoards it becomes
   much harder to tell, because it is more difficult to determine which player, HIM or HER,
   starts off with the greatest probability of winning, and therefore the lowest probability
   of learning.

PRE LAB QUESTIONS:
1. It is not reasonable to have an OrderedStack. This is because when implemented correctly, a 
   a stack inherently is a type of ordered structure because elements are added to the stack
   in priority order. A user determines the order priority by adding elements to the stack.
2. The lessThan and CompareTo methods are not comparable methods. The CompareTo method is more
   versatile because it can provide three levels of detail. It can tell if the one element is
   greater than, equal to, or less than another element. On the other hand, the lessThan method
   only provides two levels of details- it can tell if an element is less than or not less than
   another element.
3. public boolean equals (BinaryTree<E> other) {
   if (depth() == 0 ){
     return root().equals(other.root())
   } else {
     return right().equals(other.right()) && left().equals(other.left());
   }
}
4. Prove that a binary tree of height h has at most 2^(h+1)-1 nodes.
   Base case: h = 0, there is one node. 2^(0+1)-1 = 1
   Base case: h = 1, there are three nodes. 2^(1+1) -1 = 3
   Inductive Hypothesis: For some k greater than 1 and less than h, a binary tree of height k
   has at most 2^(k+1)-1 nodes.
   Inductive Step: Show that the inductive hypothesis holds for a binary tree of height h. 
   We know that the number of nodes in level h is equal to the number of nodes in level h-1 times
   2. To determine the number of nodes in level h-1 we subtract the number of nodes in a binary 
   tree of height h-2 from the number of nodes in a binary tree of height h-1. Then, to calculate
   the total number of nodes in a tree of height h, we multiply the number of nodes in level h-1
   (which we just calculated) by 2, and add the number of nodes in a binary tree of height h-1. By
   the inductive hypothesis this can be written as
    2[(2^(h-1+1)-1) - (2^(h-2+1)-1)] + (2^(h-1+1)-1) = 
    2[(2^(h)-1) - (2^(h-1)-1)] + (2^(h)-1)=
    2^(h+1) - 2^(h) + 2^(h) - 1 = 2^(h+1) - 1
    This is an example of strong induction because in order to determine the number of nodes for 
    a tree of heigh h, we must know that the inductive hypothesis holds for all the binary trees
    with height less than h. This is evident in the necessity of two base cases. 
