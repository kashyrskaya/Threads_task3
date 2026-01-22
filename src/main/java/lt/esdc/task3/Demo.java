package lt.esdc.task3;

import lt.esdc.task3.partFive.Part5;
import lt.esdc.task3.partFour.Part4;
import lt.esdc.task3.partOne.Part1;
import lt.esdc.task3.partThree.MatrixGenerator;
import lt.esdc.task3.partThree.Part3;
import lt.esdc.task3.partTwo.Part2;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("====== DEMO PART 1 ======");
        Part1.main(args);

        System.out.println("\n====== DEMO PART 2 ======");
        Part2.main(args);

        System.out.println("\n====== DEMO PART 3 ======");
        MatrixGenerator.main(args);
        Part3.main(args);

        System.out.println("\n====== DEMO PART 4 ======");
        Part4.main(args);

        System.out.println("\n====== DEMO PART 5 ======");
        Part5.main(args);
    }
}
