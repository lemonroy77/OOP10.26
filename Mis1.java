import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

class Student implements Comparable<Student> {
    String ID, name;
    char sex;
    boolean partyMember;
    double math, chinese;
    LocalDate birthday;

    static void titleHint() {
        System.out.print("\n请输入一组学生，输入Ctrl+Z结束，格式为：");
        System.out.print("\n学号 姓名 出生日期 年龄 党员 数学 语文， 例如：");
        System.out.print("\n001 张三 M 18 2005-07-15 84.2 93.7\n");
    }

    static void showTitle() {
        System.out.print("学号 姓名 性别 出生日期 党员 数学 语文\n");
        System.out.print("------------------------------------\n");
    }

    void read(Scanner sc) {
        ID = sc.next();
        name = sc.next();
        sex = sc.next().charAt(0);
        String bd = sc.next();
        birthday = LocalDate.parse(bd);
        partyMember = sc.nextBoolean();
        math = sc.nextDouble();
        chinese = sc.nextDouble();
    }

    public String toString() {
        String id, xm, xb, dy, sx, yw;
        id = String.format("%-5s", ID);
        xm = String.format("%-8s", name);
        xb = (sex == 'F' || sex == 'f') ? "女" : ((sex == 'M' || sex == 'm') ? "男" : "未知");
        dy = (partyMember == true) ? "中共党员" : "非党员";
        sx = String.format("%-6.2f", math);
        yw = String.format("%-6.2f", chinese);
        return id + " " + xm + " " + xb + " " + birthday + " " + dy + " " + sx + " " + yw;
    }

    public int compareTo(Student s) {
        if (math < s.math)
            return -1;
        if (math == s.math)
            return 0;
        return 1;
    }
}

class SortByID implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.ID.compareTo(b.ID);
    }
}

class SortByBirthday implements Comparator<Student> {
    public int compare(Student a, Student b) {
        if (a.birthday.isBefore(b.birthday))
            return -1;
        else if (a.birthday.isAfter(b.birthday))
            return 1;
        else
            return 0;
    }
}

class BanJi {
    final int maxN;
    Student[] st;
    int renShu;

    BanJi(int max) {
        maxN = max;
        st = new Student[max];
        renShu = 0;
    }

    void add(Student s) {
        st[renShu] = s;
        renShu++;
    }

    void append() {
        Student.titleHint();
        Scanner sc = new Scanner(System.in);
        Student s;
        while (sc.hasNext() == true) {
            s = new Student();
            s.read(sc);
            add(s);
        }
    }

    void show() {
        Student.showTitle();
        for (int i = 0; i < renShu; i++)
            System.out.println(st[i]);
        System.out.println("班级中共有 " + renShu + " 人。");
    }

    void readFromFile(File f) throws Exception {
        Scanner sc = new Scanner(f);
        sc.nextLine();
        sc.nextLine();
        Student s;
        while (sc.hasNext() == true) {
            s = new Student();
            s.read(sc);
            add(s);
        }
    }

    void sortByMath() {
        Arrays.sort(st, 0, renShu);
    }

    void sortByID() {
        Arrays.sort(st, 0, renShu, new SortByID());
    }

    void sortByBirthday() {
        Arrays.sort(st, 0, renShu, new SortByBirthday());
    }
}

public class Mis1 {
    public static void main(String[] args) throws Exception {
        BanJi bj = new BanJi(50);
        File f;
        System.out.println("从文件读取数据。。。");
        f = new File("D:/eclipse/Mis/src/StudentInfo.txt");
        bj.readFromFile(f);
        System.out.println("班级信息如下：");
        bj.show();
        System.out.print("\n按数学成绩排序后：\n");
        bj.sortByMath();
        bj.show();
        System.out.print("\n按学号排序后：\n");
        bj.sortByID();
        bj.show();
        System.out.print("\n按出生日期排序后：\n");
        bj.sortByBirthday();
        bj.show();
    }
}
