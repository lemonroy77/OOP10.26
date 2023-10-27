import java.util.Random;
import java.util.Scanner;
public class Send_Red_Envelopes {
	public int[] Random_Send_1(int money) {
		Random r=new Random();
		int money_fen=money*100;
		int[]money_group=new int[10];
		for(int i=0;i<10;i++) {
			money_group[i]=5;
		}
		money_fen=money_fen-5*10;
		int i;
		for(i=0;i<9;i++) {
			int shue=r.nextInt(money_fen);
			money_group[i]+=shue;
			money_fen-=shue;
		}
		money_group[i]+=money_fen;
		return money_group;
	}
	public int[] Random_Send_2(int money) {
		Random r=new Random();
		int[]money_group=new int[10];
		int i,e_du,red_shu;
		for(i=0;i<9;i++) {
			e_du=money/(10-i)*2;
			red_shu=r.nextInt(e_du);
			money_group[i]=red_shu;
			money-=red_shu;
		}
		money_group[i]=money;
		return money_group;
	}
    public static void main(String[] args) {
    	int money;
    	System.out.print("请输入发红包的金额(元)：");
    	Scanner sc=new Scanner(System.in);
    	money=sc.nextInt();
    	Send_Red_Envelopes sre=new Send_Red_Envelopes();
    	int []money_g=sre.Random_Send_1(money);
    	int sum=0;
    	System.out.print("策略1：\n");
    	for(int i=0;i<money_g.length;i++) {
    		System.out.print(money_g[i]/100.0+" ");
    		sum+=money_g[i];
    	}
    	System.out.print("红包总和为："+sum/100.0);
//    	money_g=sre.Random_Send_2(money);
//    	sum=0;
//    	System.out.print("\n策略2：\n");
//    	for(int i=0;i<money_g.length;i++) {
//    		System.out.print(money_g[i]+" ");
//    		sum+=money_g[i];
//    	}
    	System.out.print("红包总和为："+sum);
    	}
}
