import java.security.InvalidParameterException;

public class MainTestsOFInterfaces {
    public static void main(String []argv) {
        // Run the examples often used from class
        // NOTE that you must create some objects here, and maybe more methods, to run all tests
        StackImmutableLectureTests(new LinkedStackImmutable<Integer>());
        StackImmutableLectureTests(new ArrayStackImmutable<Integer>());

        // @Done: after creating class LinkedStack or ArrayStack, "un-comment" one of the below
//        StackLectureTests(new LinkedStack<Integer>());
//        StackLectureTests(new ArrayStack<Integer>());

        // @ToDo: after creating class LinkedQueue or ArrayQueue, "un-comment" the below
//        QueueLectureTests(new LinkedQueue<Integer>());
//        QueueLectureTests(new ArrayQueue<Integer>());

        // @ToDo: after creating SumOrProduct, "un-comment" the below (note: not always assigned)
//        SumOrProductTests();
    }

    // @ToDo: Dave needs to learn how to make this method generic, if it's possible!
    private static void StackImmutableLectureTests(StackImmutable<Integer> s0) {
        if (s0.isEmpty()) {  // MUST START EACH TEST WITH AN EMPTY STACK!
            System.out.println("Initially, s0=" + s0);

            StackImmutable<Integer> s1 = s0.add(5);
            // s0 were known to be a LinkedStackImmutable, we could have done this:
            // LinkedStackImmutable<Integer> s1_alt = new LinkedStackImmutable<Integer>(5, s0);  // same as above
            System.out.println("Expect Stack with 5:          " + s1 + "\n");

            StackImmutable<Integer> s2 = s1.add(3);           // could use constructor...
            System.out.println("Expect Stack with 3 atop 5: " + s2);
            int s2_size = s2.size();
            System.out.println("Expect same stack, size 2:  " + s2 + " and size=" + s2_size + "\n");

            int s2_top = s2.top();
            StackImmutable<Integer> s1_again = s2.belowTop(); // should get us s1 back!
            System.out.println("Expect 3 then Stack with 5: " + s2_top + " " + s1_again);
            boolean s1a_empty = s1_again.isEmpty();
            System.out.println("Expect same stack, empty=F:   " + s1_again + " and empty=" + s1a_empty + "\n");

            int s1a_top = s1_again.top();
            StackImmutable<Integer> s0_again = s1_again.belowTop(); // should get us s0 back!
            System.out.println("Expect 5 the empty Stack:    " + s1a_top + " " + s0_again);
            boolean s0a_empty = s0_again.isEmpty();
            System.out.println("Expect same stack, empty=T:    " + s0_again + "  and empty=" + s0a_empty + "\n");

            try {
                int failPlease = s0_again.top();
                System.out.println("**** Oops, shouldn't get here :-(   got empty-stack top of " + failPlease);
            } catch (InvalidParameterException complaint) {
                System.out.println("Good, received proper exception from empty-stack \"top\"\n");
            }

            StackImmutable<Integer> s3 = s0_again.add(7);           // could use constructor...
            System.out.println("Expect Stack with 7:          " + s3);
            StackImmutable<Integer> s4 = s3.add(9);           // could use constructor...
            System.out.println("Expect Stack with 9 atop 7: " + s4);
            int s4_top = s4.top();
            System.out.println("Expect same stack, top=9:   " + s4 + " and top=" + s4_top + "\n");
        } else {
            throw new InvalidParameterException("Stack tests must start w/empty stack!");
        }
    }

    // @ToDo: after creating class LinkedStack, "un-comment" the below
//    private static void StackLectureTests(Stack<Integer> s) {
//        if (s.isEmpty()) {  // MUST START EACH TEST WITH AN EMPTY STACK!
//            System.out.println("Initially, s=" + s);
//
//            s.push(5);
//            System.out.println("Expect Stack with 5      :    " + s + "\n");
//
//            s.push(3);
//            System.out.println("Expect Stack with 3 atop 5: " + 2);
//            int s_size = s.size();
//            System.out.println("Expect same stack, size 2:  " + s + " and size=" + s_size + "\n");
//
//            int s_pop = s.pop();
//            System.out.println("Expect 3 then Stack with 5:  " + s_pop + " " + s);
//            boolean s_empty = s.isEmpty();
//            System.out.println("Expect same stack, empty=F:    " + s + " and empty=" + s_empty + "\n");
//
//            int s_pop2 = s.pop();
//            System.out.println("Expect 5 the empty Stack:     " + s_pop2 + " " + s);
//            boolean s_empty2 = s.isEmpty();
//            System.out.println("Expect same stack, empty=T:     " + s + "  and empty=" + s_empty2 + "\n");
//
//            try {
//                int failPlease = s.pop();
//                System.out.println("**** Oops, shouldn't get here :-(   got empty-stack pop of " + failPlease);
//            } catch (InvalidParameterException complaint) {
//                System.out.println("Good, received proper exception from empty-stack \"pop\"\n");
//            }
//
//            s.push(7);
//            System.out.println("Expect Stack with 7:           " + s);
//            s.push(9);
//            System.out.println("Expect Stack with 9 atop 7: " + s);
//            int s_top = s.peek();
//            System.out.println("Expect same stack, top=9:   " + s + " and top=" + s_top + "\n");
//        } else {
//            throw new InvalidParameterException("Stack tests must start w/empty stack!");
//        }
//    }

    // ToDo: add a similar test of queues

}
