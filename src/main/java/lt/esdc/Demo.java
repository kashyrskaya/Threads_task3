package lt.esdc;

import lt.esdc.partFive.Part5;
import lt.esdc.partFour.Part4;
import lt.esdc.partOne.Part1;
import lt.esdc.partThree.MatrixGenerator;
import lt.esdc.partThree.Part3;
import lt.esdc.partTwo.Part2;

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
