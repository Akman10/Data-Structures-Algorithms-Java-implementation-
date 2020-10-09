/*
Given N X M matrix , Calculate the sum of submatrix or update a point in the matrix according to the type of query,
For each Query-->
  input Type-->
   If Type = 1 then input 4 values r1,c1,r2,c2 
   If Type = 2 then input 3 values x,y,val
   
For Type 1, Given left-uppermost and right-bottomost points of the submatrix (r1,c1) and (r2,c2) respectively. Calculate sum of submatrix and print it ,
For Type 2, Upadate matrix at point x,y with value val.
*/


import java.io.*;
import java.util.*;

class BIT_2D_submatrix_sum
{
	public static void main(String[] args)throws Exception{ new BIT_2D_submatrix_sum().run();} 
	long mod=1000000000+7;
	long BIT[][];
	long a[][];
	int N;
	int M;
	void solve() throws Exception
	{
		 N=ni();
		 M=ni();
		BIT=new long[N+1][M+1];
		a=new long[N+1][M+1];
		for (int i = 0; i <N; i++) 
			for (int j = 0; j < M; j++) 
				a[i][j]=nl();
		
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < M; j++) {
				add(i,j,a[i][j]);
			}
		}
		int Q=ni();
		for(int i=0;i<Q;i++)
		{
			int ty=ni();
			if(ty==1)
			{
				int r1=ni();
				int c1=ni();
				int r2=ni();
				int c2=ni();
				long ans=query(r1,c1,r2,c2);
				System.out.println(ans);
			}
			else
			{
				int x=ni();
				int y=ni();
				long val=nl();
				update(x-1, y-1, val);
			}
		}
	}
	void add(int x, int y,long val)  // ADD ELEMENTS IN BIT
	{
		for(int i=x+1;i<=N;i+=i&(-i))
			for(int j=y+1;j<=M;j+=j&(-j))
				BIT[i][j]+=val;
	}
	long sum(int x, int y)   // Sum of matrix from 0,0 --> x,y
	{
		long s=0;
		for(int i=x;i>0;i-=i&(-i))
			for(int j=y;j>0;j-=j&(-j))
				s+=BIT[i][j];
		return s;
	}
	long query(int r1,int c1,int r2,int c2)
	{
		return sum(r2,c2) - sum(r1-1,c2)-sum(r2,c1-1)+sum(r1-1,c1-1); 
	}
	void update(int x, int y, long val)
	{
		long diff=val-a[x][y];   // if x and y are zero based otherwise --> a[x+1][y+1]
		a[x][y]=val;
		
		for(int i=x+1;i<=N;i+=i&(-i))
			for(int j=y+1;j<=M;j+=j&(-j))
				BIT[i][j]+=diff;
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
