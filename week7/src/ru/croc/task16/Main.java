package ru.croc.task16;

import java.util.*;

public class Main {

    public static class Inform { // хранение информации о человеке age - возраст, name - ФИО
        int age;
        String name;
    }

   static class Sorting implements Comparator<Inform> { // сортировка, которая сначала сортирует по возрасту (по убыванию) 
        @Override // в случае совпадения возрста, по ФИО (по возрастанию)
        public int compare(Inform o1, Inform o2) {
            if (o2.age == o1.age) {
                return o1.name.compareTo(o2.name);
            } else {
                return o2.age-o1.age;
            }
        }
    }
    public static void main(String[] args) {
        // взял коллекцию TreeMap(Сollections.reverseOrder()), чтобы было удобнее делать вывод и обход, начиная с большего по возрасту
        // ключ - возраст, который был подан на вход с командной строки, значение - список ФИО+возраст
        // дополнительно добавил ключ со значением 0, чтобы туда хранить людей, по возрасту меньших args[0] 
        TreeMap<Integer,List<Inform>> social = new TreeMap<Integer, List<Inform>>(Collections.reverseOrder());
        for (int i = 0; i < args.length; i++) {
            List<Inform> b = new ArrayList<Inform>();
            social.put(Integer.parseInt(args[i]),b);
        }
        List<Inform> b = new ArrayList<Inform>();
        social.put(0,b);
        // парсим строки, подающие на вход, кладем в Infrom, с помощью итератора проходимся по TreeMap и вставляем в нужный ключ
        // ключ 0 все те, кто меньше args[0], ключ args[0] - все те, кто меньше args[1] и ... , ключ args[args.length-1] - сразу кладутся в первый ключ
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        while(!a.equals("END")) {
            int key = Integer.parseInt(a.split(",")[1]);
            Inform c = new Inform();
            c.age = key;
            c.name = a.split(",")[0];
            Iterator<Integer> it_s = social.keySet().iterator();
            Integer k = it_s.next();
            while(it_s.hasNext() && key < k) {
                k = it_s.next();
            }
            // переобновляем значения TreeMap и сортируем 
            List<Inform> SurAndName = new ArrayList<>();
            SurAndName = social.get(k);
            SurAndName.add(c);
            Collections.sort(SurAndName, new Sorting());
            social.put(k,SurAndName);
            a = sc.nextLine();
        }
        //максимально корявый вывод TreeMap, лучше пока не придумал
        Iterator<Integer> it = social.keySet().iterator();
        int j = args.length-1;
        int flag = -1;
        while (it.hasNext()) {
            Integer k = it.next();
            List<Inform> val = social.get(k);
            if (flag == -1) {
                k++;
                if (val.size()!=0) System.out.print(k+"+:");
                flag = 1;
            } else {
                if (j==0) {
                    if (val.size()!=0) System.out.print(k);
                } else {
                    if (val.size()!=0) System.out.print(k+1);
                }
                Integer y = Integer.parseInt(args[j]);
                if (val.size()!=0) System.out.print("-"+y+":");
                j--;
            }
            for (int i = 0; i < val.size(); i++) {
                if (i == val.size()-1) {
                    System.out.println(val.get(i).name+" ("+val.get(i).age+") ");
                } else {
                    System.out.print(val.get(i).name+" ("+val.get(i).age+"), ");
                }
            }
        } 
    }
}