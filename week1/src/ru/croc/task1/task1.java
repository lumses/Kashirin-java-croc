import java.util.Scanner;
import java.math.MathContext;
import java.math.BigDecimal;

public class Main {

  static class Point {
    BigDecimal x;
    BigDecimal y;
  }

  public static BigDecimal side(Point a, Point b){

    MathContext mc = new MathContext(10);
    BigDecimal side_a_b = (b.x.subtract(a.x).multiply(b.x.subtract(a.x)).add(b.y.subtract(a.y).multiply(b.y.subtract(a.y)))).sqrt(mc); // sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))
    return side_a_b;

  }

  public static BigDecimal square(Point a, Point b, Point c) {

    MathContext mc = new MathContext(5);
    BigDecimal side_a_b = side(a,b);
    BigDecimal side_b_c = side(b,c);
    BigDecimal side_a_c = side(a,c);
    BigDecimal p = side_a_b.add(side_b_c).add(side_a_c).divide(new BigDecimal("2")); // (a+b+c)/2
    BigDecimal s = p.multiply(p.subtract(side_a_b)).multiply(p.subtract(side_b_c)).multiply(p.subtract(side_a_c)); // p*(p-a)*(p-b)*(p-c)
    return s.sqrt(mc);

  }

  public static void main(String[] args) {
    
    Scanner console = new Scanner(System.in);

    Point a = new Point();
    System.out.print("Введите координату х вершины №1: ");
    a.x = console.nextBigDecimal();
    System.out.print("Введите координату y вершины №1: ");
    a.y = console.nextBigDecimal();

    Point b = new Point();
    System.out.print("Введите координату х вершины №2: ");
    b.x = console.nextBigDecimal();
    System.out.print("Введите координату y вершины №2: ");
    b.y = console.nextBigDecimal();

    Point c = new Point();
    System.out.print("Введите координату х вершины №3: ");
    c.x = console.nextBigDecimal();
    System.out.print("Введите координату y вершины №3: ");
    c.y = console.nextBigDecimal();

    System.out.println(square(a,b,c).stripTrailingZeros());

  }
}
