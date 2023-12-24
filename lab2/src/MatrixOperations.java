/*  Разработать консольное приложение на Java.
        Размерность и элементы матрицы ввести с клавиатуры.
        Необходимо выполнить ТРИ задания из перечисленного ниже списка задач.
        Выбор осуществлять следующим образом: выполнять задачу с номером 14*k+n,
         где k – принимает значения 0, 1, 2, а n является Вашим порядковым номером в списке группы.
        Для обработки использовать классы Random,  Arrays, Vector,ArraysList

        5.Среди столбцов заданной  матрицы, содержащих только такие элементы,
        которые по модулю не больше n, найти столбец с минимальным произведением элементов.

        19.Пусть m(А, i) означает номер столбца матрицы A, в котором находится последний в строке минимум i-й строки.
         Проверить,  что для заданной матрицы А выполняются неравенства m(A,1) £ m(A,2) £ ... £ m(A,m).

        33.Переставляя ее строки и столбцы,  добиться,  чтобы наибольший элемент  (один  из них) оказался в верхнем левом углу.
          Вывести на экран полученную матрицу.
*/


import java.util.*;

public class MatrixOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        // Заполнение матрицы числами, введенными с клавиатуры
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Введите элемент [" + (i + 1) + "][" + (j + 1) + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Вывод исходной матрицы
        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        // Задание значения n
        System.out.print("Введите значение n: ");
        int n = scanner.nextInt();

        // 1. Поиск столбца с минимальным произведением элементов, по модулю не больше n
        int minProductCol = findMinProductColumn(matrix, n);
        System.out.println("Столбец с минимальным произведением элементов, модуль которых не больше " + n + ": " + minProductCol);

        // 2. Проверка неравенств m(A,1) £ m(A,2) £ ... £ m(A,m)
        boolean isSatisfied = checkInequalities(matrix);
        System.out.println("Неравенство m(A,1) £ m(A,2) £ ... £ m(A,m): " + isSatisfied);

        // 3. Перестановка строк и столбцов для нахождения наибольшего элемента в верхнем левом углу
        rearrangeMatrix(matrix);
        System.out.println("Матрица после перестановки строк и столбцов:");
        printMatrix(matrix);
    }

    // Метод для печати матрицы
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }

    // Метод для поиска столбца с минимальным произведением элементов, по модулю не больше n
    public static int findMinProductColumn(int[][] matrix, int n) {
        int minProductCol = -1;
        int minProduct = Integer.MAX_VALUE;

        for (int col = 0; col < matrix[0].length; col++) {
            int product = 1;
            boolean columnValid = true;

            for (int[] row : matrix) {
                if (Math.abs(row[col]) > n) {
                    columnValid = false;
                    break;
                }
                product *= row[col];
            }

            if (columnValid && product < minProduct) {
                minProduct = product;
                minProductCol = col;
            }
        }
        return minProductCol;
    }

    // Метод для проверки неравенств m(A,1) £ m(A,2) £ ... £ m(A,m)
    public static boolean checkInequalities(int[][] matrix) {
        int[] lastMinIndexes = new int[matrix.length + 1];
        Arrays.fill(lastMinIndexes, -1);

        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] <= min) {
                    min = matrix[i][j];
                    minIndex = j;
                }
            }
            lastMinIndexes[i + 1] = Math.max(lastMinIndexes[i], minIndex);
        }

        for (int i = 1; i < lastMinIndexes.length - 1; i++) {
            if (lastMinIndexes[i] > lastMinIndexes[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // Метод для перестановки строк и столбцов для перемещения максимального элемента в левый верхний угол
    public static void rearrangeMatrix(int[][] matrix) {
        int maxRowIndex = 0;
        int maxRowValue = matrix[0][0];

        // Поиск максимального элемента в матрице
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > maxRowValue) {
                    maxRowValue = matrix[i][j];
                    maxRowIndex = i;
                }
            }
        }

        // Перестановка строк
        int[] temp = matrix[0];
        matrix[0] = matrix[maxRowIndex];
        matrix[maxRowIndex] = temp;

        // Перестановка столбцов
        for (int i = 0; i < matrix.length; i++) {
            int tempValue = matrix[i][0];
            matrix[i][0] = matrix[i][maxRowIndex];
            matrix[i][maxRowIndex] = tempValue;
        }
    }
}

