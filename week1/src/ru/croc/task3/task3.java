import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner console = new Scanner(System.in);
    String s = console.nextLine();
    String sArr[] = s.split(" ");
    int array[] = new int[sArr.length];
    for (int i = 0; i < sArr.length; i++){
        array[i] = Integer.parseInt(sArr[i]);
    }
    int min_elem = array[0];
    int min_idx = 0;
    int max_elem = array[0];
    int max_idx = 0;
    for (int i = 1; i < array.length; i++){
        if (array[i] > max_elem){
            max_elem = array[i];
            max_idx = i;
        }
        if (array[i] < min_elem){
            min_elem = array[i];
            min_idx = i;
        }
    }
    int t;
    t = array[0];
    array[0] = min_elem;
    array[min_idx] = t;
    t = array[array.length-1];
    array[array.length-1] = max_elem;
    array[max_idx] = t;
    for (int i = 0; i < array.length; i++){
        System.out.print(array[i]+" ");
    }
    System.out.println();
    }
}