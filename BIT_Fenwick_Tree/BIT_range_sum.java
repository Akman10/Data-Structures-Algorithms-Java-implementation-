/*
   Given an array arr of integers of size n. We need to compute sum of elements from index l to index r.
   The queries consisting of l and r index values will be executed multiple times.
*/

import java.io.*;
import java.util.*;

class BIT_range_sum
{
	public static void main(String[] args)throws Exception{ new BIT_range_sum().run();} 
	long mod=1000000000+7;
	long BIT[];
	int N;
	long a[];
  
	void solve() throws Exception
	{
		 N=ni();
		 a=new long[N];
	 	for (int i = 0; i < a.length; i++) {
			a[i]=nl(); 
		}
		BIT=new long[N+1];   // One based Indexing
		for (int i = 0; i < N; i++) {
			add(i,a[i]);
		}
		int Q=ni();
		for (int i = 0; i < Q; i++) {
			int l=ni();
			int r=ni();
			out.println(query(l+1,r+1));
		}
	}
	void add(int x, long val) { 
		x=x+1;  
		for(int i=x;i<=N;i+=i&(-i))
			BIT[i]+=val;
	}
	long sum(int in){
		long s=0;
		for(int i=in;i>0;i-=i&(-i))
			s+=BIT[i];
		return s;
	}
	long query(int l, int r)  {
		return sum(r)-sum(l-1);
	}
	void update(int in,int val){
		long diff=val-a[in];  
		a[in]=val;
		for(int i=in+1;i<=N;i+=i&(-i))
			BIT[i]+=diff;
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
