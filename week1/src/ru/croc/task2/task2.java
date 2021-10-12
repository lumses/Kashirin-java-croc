import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner console = new Scanner(System.in);
    String[] name  = {"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
    double a = console.nextDouble();
    int i = 0;
    while (a >= 1024) {
      a = a/1024;
      i++;
      }

    System.out.println(String.format("%.1f",a) + " " + name[i]);

  }
}
