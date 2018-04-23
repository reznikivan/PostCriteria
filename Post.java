import java.util.Arrays;
import java.util.Scanner;

public class Post {

	public static boolean saveZero(int[] a) {
		if (a[0] == 0) {
			return true;
		}
		return false;
	}

	public static boolean saveOne(int[] a) {
		if (a[a.length - 1] == 1)
			return true;
		return false;
	}

	public static boolean isMonotonous(int[] a, int n) {
		for (int i = 0; i < (1 << n); i++) {
			for (int j = 0; j < (1 << n); j++) {
				boolean isBigger = false;
				for (int k = 0; k < n; k++) {// for every pair checking
					if (((i >> k) & 1) > ((j >> k) & 1)) {
						isBigger = true;
					}
				}
				if (!isBigger) {// if so, then function is not monotonous
					if (a[i] > a[j]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean isSelfDual(int[] a, int n) {
		for (int i = 0; i < (1 << n); i++) {
			if (a[i ^ ((1 << n) - 1)] == a[i]) {
				return false;// if there's at least one set when a[x1,...,n] = a[notx1,...,n]
			}
		}
		return true;
	}

	public static boolean isLinear(int[] a, int n) {
		for (int i = 0; i < (1 << (n+1)); i++) {
			int[] res = new int[1 << n];
			for (int j = 0; j < (1 << n); j++) {
				int ans = 0;
				for (int k = 0; k < n + 1; k++) {
					int cur = ((i >> k) & 1);
					if (cur == 1) {// what to choose for xor
						if (k < n) {
							ans = ans^(j >> k);
						} else {
							ans = ans^1;
						}
					}
				}
				res[j] = ans;
			}

			boolean funcSameAsRes = true;
			for (int j = 0; j < (1 << n); j++) {
				if (a[j] != res[j]) {
					funcSameAsRes = false;// for going to beginning
				}
			}
			if (!funcSameAsRes)
				continue;

			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = 3;// in.nextInt();
		for (int i = 0; i < 1 << (1 << n); i++) {
			int[] table = new int[1 << n];
			String template = "function is ";
			for (int j = 0; j < 1 << n; j++) {
				table[j] = (i >> j) & 1;
			}
			template = Arrays.toString(table) + template;
			boolean fullSet = true;
			if (isMonotonous(table, n)) {
				if (fullSet) {
					//System.out.println(template + "monotonous");
					fullSet = false;
				} else {
					//System.out.println(" and monotonous");
				}
			}
			if (saveZero(table)) {
				if (fullSet) {
					//System.out.println(template + "preserving 0");
					fullSet = false;
				} else {
					//System.out.println(" and preserving 0");
				}
			}
			if (saveOne(table)) {
				if (fullSet) {
					//System.out.println(template + "preserving 1");
					fullSet = false;
				} else {
					//System.out.println(" and preserving 1");
				}
			}
			if (isSelfDual(table, n)) {
				if (fullSet) {
					//System.out.println(template + "selfudal");
					fullSet = false;
				} else {
					//System.out.println(" and selfdual");
				}
			}
			if (isLinear(table, n)) {
				if (fullSet) {
					//System.out.println(template + "linear");
					fullSet = false;
				} else {
					//System.out.println(" and linear");
				}
			}
			System.out.println();
			if (fullSet) {
				System.out.println(Arrays.toString(table) + "is a full set");
			}
		}
		// System.out.println(isMonotonous(variables, n));
		// System.out.println(variables);
		in.close();

	}

}
