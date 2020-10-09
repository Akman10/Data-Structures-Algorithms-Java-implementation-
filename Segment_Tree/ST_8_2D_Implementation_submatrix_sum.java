/*
Given N X M matrix , Calculate the sum of submatrix or update a point in the matrix according to the type of query,

--> input Q
For each Query-->
--> input Type-->
   If Type = 1 then input 4 values r1,c1,r2,c2 
   If Type = 2 then input 3 values x,y,val
   
For Type 1, Given left-uppermost and right-bottomost points of the submatrix (r1,c1) and (r2,c2) respectively. Calculate sum of submatrix and print it ,
For Type 2, Upadate matrix at point x,y with value val.
*/

import java.io.*;
import java.util.*;

class ST_8_2D_Implementation_submatrix_sum
{
	public static void main(String[] args)throws Exception{ new ST_8_2D_Implementation_submatrix_sum().run();} 
	long mod=1000000000+7;
	
	int N;
	int M;
	long t[][];
	void solve() throws Exception
	{
		N=ni();
		M=ni();
		t = new long[(4*N)+1][(4*M)+1];
		long a[][]=new long[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				a[i][j]=nl();
			}
		}
		build_x(a,1,0,N-1);
		int Q=ni();
		for (int i = 0; i < Q; i++) {
			int ty=ni();
			if(ty==1)
			{
				int x1=ni();
				int x2=ni();
				int y1=ni();
				int y2=ni();
				System.out.println(sum_x(1, 0, N-1, x1-1, x2-1, y1-1, y2-1));
			}
			else
			{
				int x=ni();
				int y=ni();
				long w=nl();
				update_x(1, 0, N-1, x-1, y-1, w);
			}
		}
	}
	void build_y(long a[][], int vx, int lx, int rx, int vy, int ly, int ry)
	{
		if (ly == ry) {
	        if (lx == rx)
	            t[vx][vy] = a[lx][ly];
	        else
	            t[vx][vy] = t[vx*2][vy] + t[vx*2+1][vy];
	    } else {
	        int my = (ly + ry) / 2;
	        build_y(a,vx, lx, rx, vy*2, ly, my);
	        build_y(a,vx, lx, rx, vy*2+1, my+1, ry);
	        t[vx][vy] = t[vx][vy*2] + t[vx][vy*2+1];
	    }
	}
	void build_x(long a[][], int vx, int lx, int rx)  // vx=1, lx=0, rx=N-1
	{
		if(lx!=rx)
		{
			int mx=(lx+rx)/2;
			build_x(a, 2*vx, lx, mx);
			build_x(a, 2*vx+1, mx+1, rx);
		}
		build_y(a,vx,lx,rx,1,0,M-1);
	}
	long sum_y(int vx, int vy, int tly, int try_, int ly, int ry)
	{ 
		if (ly > ry) 
			return 0;
		if (ly == tly && try_ == ry)
			return t[vx][vy];
		int tmy = (tly + try_) / 2;
		return sum_y(vx, vy*2, tly, tmy, ly, Math.min(ry, tmy))
		   	 + sum_y(vx, vy*2+1, tmy+1, try_, Math.max(ly, tmy+1), ry);
	}
	long sum_x(int vx,int tlx, int trx, int lx, int rx, int ly, int ry) // (lx,rx) (ly,ry)
	{
		 if (lx > rx)
		        return 0;
		    if (lx == tlx && trx == rx)
		        return sum_y(vx, 1, 0, M-1, ly, ry);
		    int tmx = (tlx + trx) / 2;
		    return sum_x(vx*2, tlx, tmx, lx, Math.min(rx, tmx), ly, ry)
		         + sum_x(vx*2+1, tmx+1, trx, Math.max(lx, tmx+1), rx, ly, ry);
	}
	void update_y(int vx, int lx, int rx, int vy, int ly, int ry, int x, int y, long new_val) {
	    if (ly == ry) {
	        if (lx == rx)
	            t[vx][vy] = new_val;
	        else
	            t[vx][vy] = t[vx*2][vy] + t[vx*2+1][vy];
	    } else {
	        int my = (ly + ry) / 2;
	        if (y <= my)
	            update_y(vx, lx, rx, vy*2, ly, my, x, y, new_val);
	        else
	            update_y(vx, lx, rx, vy*2+1, my+1, ry, x, y, new_val);
	        t[vx][vy] = t[vx][vy*2] + t[vx][vy*2+1];
	    }
	}

	void update_x(int vx, int lx, int rx, int x, int y, long new_val) {
	    if (lx != rx) {
	        int mx = (lx + rx) / 2;
	        if (x <= mx)
	            update_x(vx*2, lx, mx, x, y, new_val);
	        else
	            update_x(vx*2+1, mx+1, rx, x, y, new_val);
	    }
	    update_y(vx, lx, rx, 1, 0, M-1, x, y, new_val);
	}
	/*IMPLEMENTATION BY AMAN KOTIYAL, FAST INPUT OUTPUT & METHODS BELOW*/
	
	private byte[] buf=new byte[1024];
	private int index;
	private InputStream in;
	private int total;
	private SpaceCharFilter filter;
	PrintWriter out;
	
	int gcd(int a, int b) 
	{ 
		if (a == 0) 
			return b; 
		return gcd(b%a, a); 
	} 
	long gcd(long a, long b) 
	{ 
		if (a == 0) 
			return b; 
		return gcd(b%a, a); 
	}
	/* for (1/a)%mod = ( a^(mod-2) )%mod  ----> use expo to calc -->(a^(mod-2)) */
	long expo(long p,long q)  /*  (p^q)%mod   */
	{
		long z = 1;
		while (q>0) {
			if (q%2 == 1) {
				z = (z * p)%mod;
			}
			p = (p*p)%mod;
			q >>= 1;
		}
		return z;
	}
	void run()throws Exception
	{
		in=System.in; out = new PrintWriter(System.out);
		solve();
		out.flush();
	}
	private int scan()throws IOException
	{
		if(total<0)
			throw new InputMismatchException();
		if(index>=total)
		{
			index=0;
			total=in.read(buf);
			if(total<=0)
				return -1;
		}
		return buf[index++];
	}
	private int ni() throws IOException 
	{
		int c = scan();
		while (isSpaceChar(c))
			c = scan();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = scan();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = scan();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
	private long nl() throws IOException 
	{
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = scan()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = scan();
		}
		
		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = scan();
		}
	}
	private double nd() throws IOException{
		return Double.parseDouble(ns());
	}
	private String ns() throws IOException {
		int c = scan();
		while (isSpaceChar(c))
			c = scan();
		StringBuilder res = new StringBuilder();
		do {
			if (Character.isValidCodePoint(c))
				res.appendCodePoint(c);
			c = scan();
		} while (!isSpaceChar(c));
		return res.toString();
	}
	private String nss() throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	private char nc() throws IOException 
	{
		int c = scan();
		while (isSpaceChar(c))
			c = scan();
		return (char) c;
	}
	private boolean isWhiteSpace(int n)
	{
		if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
			return true;
		return false;
	}
	private boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return isWhiteSpace(c);
	}
	private interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}
