Name: Caroline Kessler

10.3: Pop off element from the original stack and push onto temporary stack. This is done for all 
     elements in the original stack until it is empty. Then the elements on the temporary stack are
     popped off and pushed onto the final stack. The result is an empty original stack, and empty
     temporary stack, and a full final stack with elements in the same order as the original stack.

10.4: Two temporary stacks are needed. The elements of the original stack are popped off and 
      pushed onto a temporary stack. The first temporary stack then contains all of the elements
      in reverse order. The elements are then popped from the first temporary stack and pushed onto
      the second temporary stack. The elements are now in the correct order on the temporary stack.
      The elements are then popped from the second temporary stack and pushed onto the original 
      stack. The original stack now contains all of the elements in reverse order.

10.5: First the size of the queue is determined. Then, in a for loop as long as the queue size,
      each element is dequeued from the original queue and enqueued into both the original and
      final queues.
