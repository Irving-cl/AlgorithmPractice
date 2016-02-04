import java.util.Scanner;

public class BrattleShip {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numCases = input.nextInt();
		for (int n = 0; n < numCases; n++) {
			int R = input.nextInt();
			int C = input.nextInt();
			int W = input.nextInt();
			int result = minTries(R, C, W);

			System.out.printf("Case #%d: ", n + 1);
			System.out.println(result);
		}
	}

	private static int minTries(int r, int c, int w) {
		boolean flag = c % w == 0; // 表示最後一格是否為black
		int ret = flag ? F(c, w) * r + w - 1 : F(c, w) * r + w;
		return ret;
	}

	private static int F(int c, int w) {
		return c / w;
	}

}
