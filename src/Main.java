import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static void leftShift(int[] arr) { // сдвиг влево
        int startIndex = arr[0];
        for (int i = 0; i < 4; i++) {
            arr[i] = arr[i + 1];
        }
        arr[4] = startIndex;
        startIndex = arr[5];
        for (int i = 5; i < 9; i++) {
            arr[i] = arr[i + 1];
        }
        arr[9] = startIndex;
    }

    static void fillingArray(int[] description, int[] base, int[] arrayToFill) { //заполнение массива
        // description - откуда берутся индексы (p_10, p_8)
        // base - откуда берутся знаечния (k_10, array_1)
        // arrayToFill - куда вписать значения (array_1, key_1, key_2)

        int index;
        for (int i = 0; i < arrayToFill.length; i++) {
            index = description[i];
            arrayToFill[i] = base[index - 1];
        }
    }

    static void sumArrays(int[] arr_1, int[] arr_2, int[] arr_sum) { //сложение массивов
        int value;
        for (int i = 0; i < arr_sum.length; i++) {
            value = (arr_1[i] + arr_2[i]) % 2;
            arr_sum[i] = value;
        }
    }

    static int binaryToDecimal(int a, int b) { // псевдо-перевод из 2 -> 10
        String str = Integer.toString(a) + Integer.toString(b);

        if (str.equals("00")) {
            return 0;
        } else if (str.equals("01")) {
            return 1;
        } else if (str.equals("10")) {
            return 2;
        } else {
            return 3;
        }
    }

    static void decimalToBinary(ArrayList<Integer> arr, int value) { // псевдо-перевод из 10 -> 2
        switch (value) {
            case 0:
                arr.add(0);
                arr.add(0);
                break;
            case 1:
                arr.add(0);
                arr.add(1);
                break;
            case 2:
                arr.add(1);
                arr.add(0);
                break;
            case 3:
                arr.add(1);
                arr.add(1);
                break;
        }
    }

    static void rowCol(int a_0, int a_1, int a_2, int a_3, int[][] S_num, ArrayList<Integer> arrayToFill) { //поиск по S
        int row = binaryToDecimal(a_0, a_3);
        int col = binaryToDecimal(a_1, a_2);
        int value = S_num[row][col];
        decimalToBinary(arrayToFill, value);
    }

    static void encryption(int [] key_num, int[] array_2, int callNumber) { // само зашифрование

        int[] r_num = {8, 5, 6, 7, 6, 7, 8, 5}; // r4, r1, r2, r3....
/*        int[] r_num = {8, 7, 6, 5, 6, 7, 8, 5}; // r4, r1, r2, r3....*/
        int[] array_3 = new int[8];
        int[] array_sum = new int[8];
        int[][] S_0 = {{1, 0, 3, 2},
                {3, 2, 1, 0},
                {0, 2, 1, 3},
                {3, 1, 3, 1}};
        int[][] S_1 = {{1, 1, 2, 3},
                {2, 0, 1, 3},
                {3, 0, 1, 0},
                {2, 1, 0, 3}};
        ArrayList<Integer> array_4 = new ArrayList<Integer>(4);
        int[] p_4 = {2, 4, 3, 1};
        int[] array_5 = new int[4];
        int[] array_6 = new int[4];
        int[] left_side = new int[4]; // левая часть от array_2
        int[] right_side = new int[4]; // правая часть от array_2
        int[] array_sum_2 = new int[4];

        int row;
        int col;
        int value;

        fillingArray(r_num, array_2, array_3); // E/P
        System.out.println("После E/P: ");
        System.out.println(Arrays.toString(array_3));

        sumArrays(array_3, key_num, array_sum); // прибавление ключа k
        System.out.println("После суммирования: ");
        System.out.println(Arrays.toString(array_sum));

        rowCol(array_sum[0], array_sum[1], array_sum[2], array_sum[3], S_0, array_4); // преобразования и поиск по S0 S1
        rowCol(array_sum[4], array_sum[5], array_sum[6], array_sum[7], S_1, array_4);
        System.out.println("После поиска по таблицам: ");
        System.out.println(array_4);

        for (int i = 0; i < array_5.length; i++) { // ArrayList перезаписали в массив, чтобы потом отправить в sumArray
            array_5[i] = array_4.get(i);
        }

        fillingArray(p_4, array_5, array_6); // P4
        System.out.println("После P4: ");
        System.out.println(Arrays.toString(array_6));

        System.arraycopy(array_2, 0, left_side, 0, 4); // делить array_2 на левую и правую часть
        System.arraycopy(array_2, 4, right_side, 0, 4);

        sumArrays(array_6, left_side, array_sum_2); // левая часть суммируется с array_6
        System.out.println("После суммирования: ");
        System.out.println(Arrays.toString(array_sum_2));

        if (callNumber == 1) { // склейка правой части и полученной суммы. Выполняется перестановка SW
            // только для первого захода
            System.arraycopy(right_side, 0, array_2, 0, 4);
            System.arraycopy(array_sum_2, 0, array_2, 4, 4);
        } else { //склейка, без SWф, для второго захода
            System.arraycopy(array_sum_2, 0, array_2, 0, 4);
            System.arraycopy(right_side, 0, array_2, 4, 4);
        }
    }


    public static void main(String[] args) {

        ////////поиск ключей
/*        int[] k_10 = {1, 0, 0, 1, 1, 0, 0, 1, 0, 0};*/
        int[] k_10 = new int[10];
        int[] p_10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
        int[] p_8 = {6, 3, 7, 4, 8, 5, 10, 9};
        int[] array_1 = new int[10];
        int[] key_1 = new int[8];
        int[] key_2 = new int[8];
        int value;
        Scanner scan = new Scanner(System.in);

        

        System.out.println("Введите десятибитный ключ k10: ");
        for (int i = 0; i < k_10.length; i++) {
            value = scan.nextInt();
            k_10[i] = value;
        }

        fillingArray(p_10, k_10, array_1); // p10
        System.out.println("После p_10:");
        System.out.println(Arrays.toString(array_1));

        leftShift(array_1); // LS-1
        System.out.println("После сдвига LS-1:");
        System.out.println(Arrays.toString(array_1));

        fillingArray(p_8, array_1, key_1); //p8
        System.out.println("Ключ 1: ");
        System.out.println(Arrays.toString(key_1));

        for (int i = 0; i < 2; i++) { // LS-2
            leftShift(array_1);
        }
        fillingArray(p_8, array_1, key_2); // p8
        System.out.println("Ключ 2: ");
        System.out.println(Arrays.toString(key_2));
        System.out.println("\n\n");
        ////////////////////////

        int[] P = new int[8];
        int[] IP = {2, 6, 3, 1, 4, 8, 5, 7};
        int[] IP_reverse = {4, 1, 3, 5, 7, 2, 8, 6};
        int[] array_2 = new int[8];
        int[] C = new int[8];


/*        int[] key_11 = {1, 1, 1, 1, 0, 1, 0, 1}; //пример тетрадь
        int[] key_22 = {0, 1, 1, 1, 0, 0, 1, 1};*/
/*        int[] P = {1, 0, 1, 0, 1, 0, 1, 1};*/


        System.out.println("Введите восьмибитный открытый текст P: ");
        for (int i = 0; i < P.length; i++) {
            value = scan.nextInt();
            P[i] = value;
        }



        fillingArray(IP, P, array_2); // IP
        System.out.println("После IP: ");
        System.out.println(Arrays.toString(array_2));

        encryption(key_1, array_2, 1);
        System.out.println("Итог первого захода: " + Arrays.toString(array_2));
        System.out.println("\n\n");
        encryption(key_2, array_2, 2);
        System.out.println("Итог второго захода: " + Arrays.toString(array_2));

        fillingArray(IP_reverse, array_2, C); // IP
        System.out.println("Итог шифрования: ");
        System.out.println(Arrays.toString(C));

    }


}