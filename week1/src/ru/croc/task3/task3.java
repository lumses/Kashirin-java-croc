import java.util.Scanner;

public class Main {

    // считывание массива, сначала считываем как строку, создаем массив из строк,разделенных пробелов, с помощью parseInt преобразуем строку в число
    public static int[] readArray(){
        Scanner console = new Scanner(System.in);
        String s = console.nextLine();
        String sArr[] = s.split(" ");
        int array[] = new int[sArr.length];
        for (int i = 0; i < sArr.length; i++){
            array[i] = Integer.parseInt(sArr[i]);
        }
        return array;
    }

    // нахождение индекса максимального элемента в массиве
    public static int idxMax(int [] array){
        int max_idx = 0;
        for (int i = 1; i < array.length; i++){
        if (array[i] > array[max_idx]){
            max_idx = i;
            }
        }
        return max_idx;
    }

    // нахождение индекса минимального элемента в массиве
    public static int idxMin(int [] array){
        int min_idx = 0;
        for (int i = 1; i < array.length; i++){
        if (array[i] < array[min_idx]){
            min_idx = i;
            }
        }
        return min_idx;
    }

    // перестановка элементов массива
    public static void swap(int[] array, int one_idx, int two_idx){
        int t;
        t = array[one_idx];
        array[one_idx] = array[two_idx];
        array[two_idx] = t;
    }
    
    // вывод элементов массива
    public static void print_array(int[] array){
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int array[] = readArray();
        swap(array,idxMin(array),0);
        swap(array,idxMax(array),array.length-1);
        print_array(array);
    }
}
