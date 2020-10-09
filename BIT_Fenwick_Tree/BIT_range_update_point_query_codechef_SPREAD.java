/*
https://www.codechef.com/problems/SPREAD
  Bob has n heap(s) of gravel (initially there are exactly c piece(s) in each). He wants to do m operation(s) with that heaps, each maybe:
  ->  Adding pieces of gravel onto the heaps from u to v, exactly k pieces for each,
  ->  Or querying "how many pieces of gravel are there in the heap p now?".
*/

import java.io.*;
import java.util.*;

class BIT_range_update_point_query_codechef_SPREAD {
	static long mod = 1000000000 + 7;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out=new PrintWriter(System.out);
	static long BIT[];
	static int N;
	static int M;

	public static void main(String args[]) throws IOException {
		String s[]=br.readLine().split(" ");
		N=Integer.parseInt(s[0]);
		M=Integer.parseInt(s[1]);
		long C=Long.parseLong(s[2]);
    
		BIT = new long[N + 1];

		for (int i = 0; i < M; i++) {
			 String s2[]=br.readLine().split(" ");
			
			if (s2[0].equals("S")) {
				int u = Integer.parseInt(s2[1]); // 1
				int v = Integer.parseInt(s2[2]);
				long k = Long.parseLong(s2[3]);
				update(u, k);
				update(v + 1, -k);
			} else {
				int p = Integer.parseInt(s2[1]); // 1
				out.println(get(p) + C);
			}
      
		}
		out.flush();
	}

	static void update(int x, long k) {
		for (int i = x; i <= N; i += i & (-i))
			BIT[i] += k;
	}

	static long get(int p) {
		long s = 0;
		for (int i = p; i > 0; i -= i & (-i))
			s += BIT[i];
		return s;
	}

	static int gcd(int a, int b) {
		if (a == 0)
			return b;
		return gcd(b % a, a);
	}

	/* for (1/a)%mod = ( a^(mod-2) )%mod ----> use expo to calc -->(a^(mod-2)) */
	static long expo(long p, long q) /* (p^q)%mod */
	{
		long z = 1;
		while (q > 0) {
			if (q % 2 == 1) {
				z = (z * p) % mod;
			}
			p = (p * p) % mod;
			q >>= 1;
		}
		return z;
	}

	static int ni() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static long nl() throws IOException {
		return Long.parseLong(br.readLine());
	}

	static double nd() throws IOException {
		return Double.parseDouble(br.readLine());
	}

	static String ns() throws IOException {
		return (br.readLine());
	}

	static char nc() throws IOException {
		String s = br.readLine();
		return (s.charAt(0));
	}
}
