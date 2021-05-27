
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FindMinPlainDrome {

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        BigInteger input = null;
        int[] inputList;

        System.out.print("Enter a Number to find Min PlainDrome : ");

        try {
            input = scanner.nextBigInteger();
            System.out.println("your input is : " + input);
            inputList = getArr(input);
            String result = getMinPlainDrome(inputList, input);
            System.out.println("Result is :" + result);

        } catch (InputMismatchException im) {
            System.out.println("Sorry! plz enter only integer ");
        }

    }

    private static String getMinPlainDrome(int[] inputList, BigInteger input) {
        int inputLength = inputList.length;
        String res;

        if (input.add(BigInteger.ONE).toString().length() > inputLength)
            return input.add(BigInteger.ONE).add(BigInteger.ONE).toString();
        else if (inputLength < 2)
            return String.valueOf(inputList[0] + 1);
        else {

            int increasement = 1;
            int leftIndex = (inputLength / 2) - 1;
            int rightIndex = (inputLength % 2 != 0) ? (inputLength / 2) + 1 : (inputLength / 2);
            boolean leftsmaller = false;

            while (leftIndex >= 0 && inputList[leftIndex] == inputList[rightIndex]) {

                leftIndex--;
                rightIndex++;

            }

            if (leftIndex < 0 || inputList[leftIndex] < inputList[rightIndex]) {

                leftsmaller = true;
            }

            while (leftIndex >= 0) {
              
                inputList[rightIndex++] = inputList[leftIndex--];
            }

            if (leftsmaller) {
           

                if (inputLength % 2 == 1) {
                    inputList[inputLength / 2] += 1;
                    increasement = inputList[inputLength / 2] / 10;
                    inputList[inputLength/2] %= 10;
                }
                leftIndex = (inputLength / 2) - 1;
                rightIndex = (inputLength % 2 == 0 ? (inputLength / 2) : (inputLength / 2) + 1);

                while (leftIndex >= 0 && increasement > 0) {
                    inputList[leftIndex] = inputList[rightIndex] + increasement;
                    increasement = inputList[leftIndex] / 10;
                    inputList[leftIndex] %= 10;
                    inputList[rightIndex] = inputList[leftIndex];
                    leftIndex--;
                    rightIndex++;
                }

            }
        }

        return Arrays.toString(inputList);

    }

    private static int[] getArr(BigInteger num) {
        String str = num.toString();
        int[] arr = new int[str.length()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = str.charAt(i) - '0';
        return arr;
    }

}
