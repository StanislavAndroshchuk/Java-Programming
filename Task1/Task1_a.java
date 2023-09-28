
package Task1;
import java.io.Console;
import java.io.IOException;
import java.util.*;

public class Task1_a {
    static int n, m;
    static int[][] matrix = new int[n][m];
    static Vector<Integer> vector = new Vector<Integer>();
    public static int[][] generateRandomMatrix(int rows, int columns) {
        Random random = new Random();
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = random.nextInt(10000); // Генеруємо випадкове ціле число від 0 до 99 (змініть за потребою)
            }
        }

        return matrix;
    }
    public static void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i ++){
            for (int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " " + "\t\t");
            }
            System.out.println('\n');
        }
    }
    public static int sumOfDigits(int number) {
        int sum = 0;
        while (number != 0) {
            int digit = number % 10; // Last number of element
            sum += digit;
            number /= 10;            // Kick last element for next
        }

        return sum;
    }
    public static Vector<Integer> getMaxSumOfTheRow(int[][] matrix){
        Vector<Integer> vector = new Vector<Integer>();
        int max = 0 ;
        for(int i = 0; i < matrix.length; i ++){
            for(int j = 0; j < matrix[0].length; j ++){
                int temp = sumOfDigits(matrix[i][j]);
                if ( temp > max){
                    max = temp;
                }
            }
            vector.add(max);
            max = 0;
        }
        return vector;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of matrix n*m:");
        n = scanner.nextInt();
        m = scanner.nextInt();
        System.out.println("Creating matrix " + n + " by " + m);
        matrix = generateRandomMatrix(n,m);
        printMatrix(matrix);
        vector = getMaxSumOfTheRow(matrix);
        System.out.println("Created vector of max sum of elements in rows");
        System.out.print(vector);
    }


}

