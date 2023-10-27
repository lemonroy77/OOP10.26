import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
class Student implements Comparable<Student>{//学生信息：学号、姓名、性别、年龄、是否党员、语文、数学
String ID,name;  char sex; boolean partyMember;  double math,chinese;
LocalDate birthday;
static void titleHint(){//用于读取数据时给出的输入格式提示。思考：为何设为静态？
System.out.print("\n请输入一组学生，输入Ctrl+Z结束，格式为：");
System.out.print("\n学号 姓名 出生日期 年龄 党员 数学 语文， 例如：");
System.out.print("\n001 张三 M 18 2005-07-15 84.2 93.7\n");
}
static void showTitle() {
System.out.print("学号 姓名 性别 出生日期 党员 数学 语文\n");
System.out.print("------------------------------------\n");
}
void read(Scanner sc){//从sc对象读取所需的所有数据，读取次序与titleHint()相同
ID=sc.next(); name=sc.next(); //读取学号、姓名两个字符串
sex=sc.next().charAt(0);     //先读取String型数据，如"M"，再取其首字符'M'
//  age=sc.nextInt();            //读取int型数据
String bd = sc.next();//用于读取生日字符串
birthday = LocalDate.parse(bd);//将日期型字符串转变成日期型
partyMember=sc.nextBoolean();        //读取boolean型数据
math=sc.nextDouble();  
chinese=sc.nextDouble();  //读取double型数据
}
public String toString(){//格式化后的字符串
String id, xm, xb, dy, sx, yw;
id = String.format("%-5s", ID);
xm = StringFormat.formatC(name, 8, ' '); //姓名占8个字符，左对齐，不足部分用空格补充。注：1个汉字占2个字符宽度
xb=(sex=='F'||sex=='f')?"女":( (sex=='M'||sex=='m')?"男":"未知" );
dy=(partyMember==true) ? "中共党员":"非党员";
sx = String.format("%-6.2f", math); //整数位至多3位，保留2位小数，左对齐
yw = String.format("%-6.2f", chinese);
return id+" "+xm+" "+xb+" "+birthday+" "+dy+" "+sx+" "+yw; 
}
public int compareTo(Student s) {
if (birthday.isBefore(s.birthday)) return -1;
else if (birthday.isAfter(s.birthday)) return 1;
else return 0;
}
}

class SortByID implements Comparator<Student> {
public int compare(Student a, Student b) {
return a.ID.compareTo(b.ID);
}
}

class SortByMath implements Comparator<Student> {
public int compare(Student a, Student b) {
if (a.math > b.math) return -1;
else if (a.math < b.math) return 1;
else return 0;
}
}
class BanJi{//班级：班级容量+存储Student对象的数组+实际人数
final int maxN;   Student [] st;  int renShu;
BanJi(int max){ maxN=max; st=new Student[max]; renShu=0; } 
void add(Student s){st[renShu]=s; renShu++; }//向班级中追加学生s
void append( ){ //向数组尾部“追加”输入一批学生，以Ctrl+Z结束
//  Student.showTitle();
Student.titleHint();  //给出输入次b序和格式的示例
Scanner sc=new Scanner(System.in); //用空白符作为间隔符
// Scanner sc =new Scanner(System.in).useDelimiter("[\\n\\r#]+");//用#、回车等作为间隔符
Student s;  //下面循环：造对象--向对象填充数据--将对象加入班级
while(sc.hasNext()==true){ s=new Student(); s.read(sc); add(s); }
}
void show(){
Student.showTitle();
for(int i=0; i<renShu; i++) System.out.println(st[i]);
System.out.println("班级中共有 "+renShu+" 人。");
}
void readFromFile(File f)throws Exception{//从文件读取研究生数据
Scanner sc=new Scanner(f);
sc.nextLine();  sc.nextLine(); //f前两行不是数据--见实际文件
Student s;  //下面读取数据代码与append()方法相同
while(sc.hasNext()==true){ s=new Student(); s.read(sc); add(s); }
}
void sortByBirthday() {
Arrays.sort(st, 0, renShu);
}

void sortByID() {
Arrays.sort(st, 0, renShu, new SortByID());
}

void sortByMath() {
Arrays.sort(st, 0, renShu, new SortByMath());
}

}
public class Mis {
// public static void main(String[] args) {
//  // TODO Auto-generated method stub
//  BanJi bj=new BanJi(20);   bj.append();
//  System.out.println("班级信息如下："); bj.show();
// }
public static void main (String[] args) throws Exception{
BanJi bj=new BanJi(50);   //bj.append();//改为从文件读取信息
File f;  //定义一个文件引用，类似文件指针
System.out.println("从文件读取数据。。。");
f = new File("D:/eclipse/Mis/src/StudentInfo.txt");
bj.readFromFile(f);
System.out.println("班级信息如下："); bj.show();
System.out.print("\n按出生日期排序后：\n");
bj.sortByBirthday();   bj.show(); 
System.out.print("\n按学号排序后：\n");
bj.sortByID(); bj.show();
System.out.print("\n按数学成绩排序后：\n");
bj.sortByMath(); bj.show();
}
}
/* 以空白作为间隔符
001 赵颖 F 2005-09-18 true 73.1 98.6
002 李晓明 M 2004-08-03 false 89 76
003 罗亮   M 2003-07-15 true 78 99
004 王大川 F 2005-04-01 true 100 20
*/
