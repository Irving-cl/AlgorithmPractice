import java.util.*;
import java.math.*;

public class Main{

	public static int prod(int x, int y){
		if(x < 0) return -prod(-x, y);
		if(y < 0) return -prod(x, -y);
		if(x == 1) return y;
		if(y == 1) return x;
		if(x == y) return -1;
		int ans = 9 - x - y;
		if((y + 1) % 3 == x % 3) ans = -ans;
		return ans;
	}

	public static boolean func(int L, int X, String s){
		int i;
		
		int a[] = new int[L*X+1];
		a[0] = 1;
		for(i=0;i<L*X;i++) a[i+1] = prod(a[i], s.charAt(i%L) - 'i' + 2);
		
		if(a[L*X] != prod(prod(2, 3), 4)) return false;
		
		int tmp = 1;
		int state = 0;
		for(i=1;i<L*X;i++){
			if(state == 0 && a[i] == 2){
				state = 1;
			} else if(state == 1 && a[i] == prod(2, 3)){
				state = 2;
			}
		}
		
		return (state == 2);
	}

	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		
		for(int t=1;t<=T;t++){
			int L = scan.nextInt();
			long X = scan.nextLong();
			if(X >= 100) X -= (X - 100) / 4 * 4;
			String s = scan.next();
			boolean ans = func(L, (int)X, s);
			System.out.println("Case #" + t + ": " + (ans ? "YES" : "NO"));
		}
	}

}
