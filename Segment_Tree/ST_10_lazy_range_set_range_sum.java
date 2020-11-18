/*

Range set then Range sum (Efficient / best / Clean Code)

*/


import java.io.*;
import java.util.*;

public class ques4
{
	public static void main(String[] args)throws Exception{ new ques4().run();} 
	long mod=1000000000+7;
	int n;
	long a[],lazy[],t[],mn[],mx[];
	long ans; 
	void init(int n)
	{
		 t = new long[(4*n)+1];
		 lazy = new long[(4*n)+1];
		 mn = new long[(4*n)+1];
		 mx = new long[(4*n)+1];
	}
	long money;
	void solve() throws Exception
	{
		 n=ni();
		 int q=ni();
		 a=new long[n];
		 init(n);
		for(int i=0;i<n;i++)
			a[i]=nl();
		build(a,1,0,n-1);
		
		for(int i=0;i<q;i++) 
		{
			int type=ni();
			int l=ni();
			int r=ni();
			if(type==1)
			{
				long val=nl();
				update(1,0,n-1, l-1, r-1, val);
			}
			else
			{
				ans=0;
				get(1,0,n-1, l-1,r-1);
				System.out.println(ans);
			}
		}
	}

	void build(long a[],int v, int tl, int tr)  //(tl is starting index of array 'a' and tr is ending index )
	{
		if(tl == tr){
			t[v] = a[tl];
			mn[v] = mx[v] = a[tl];
			return;
		}
		int tm = (tl + tr) >> 1;
		build(a,v << 1, tl, tm);
		build(a,v << 1 | 1, tm + 1, tr);
		t[v] = t[v << 1] + t[v << 1 | 1];
		mn[v] = Math.min(mn[v << 1], mn[v << 1 | 1]);
		mx[v] = Math.max(mx[v << 1], mx[v << 1 | 1]);
	}
	void push(int v, int tl, int tr)
	{
		if(lazy[v]!=0) // see constraints for this checking
		{                            
			lazy[v << 1] = mn[v << 1] = mx[v << 1] = lazy[v];
			lazy[v << 1 | 1] = mn[v << 1 | 1] = mx[v << 1 | 1] = lazy[v];
			t[v << 1] =lazy[v] * ((tl + tr) / 2 - tl + 1);
			t[v << 1 | 1] =lazy[v] * (tr - (tl + tr) / 2);
			lazy[v] = 0;
		}
	}
	void update(int v, int tl, int tr, int l,int r , long y)
	{
		if(r < tl || l>tr || l>r)
			return;
		if(tl>=l && tr<=r)
		{
			t[v] = y * (tr - tl + 1);
			mn[v] = mx[v] = y;
			lazy[v] = y;
			return;
		}
		push(v, tl, tr);
		int tm = (tl + tr) >> 1;
		update(v << 1, tl, tm, l,r, y);
		update(v << 1 | 1, tm + 1, tr, l,r, y);
		t[v] = t[v << 1] + t[v << 1 | 1];
		mn[v] = Math.min(mn[v << 1], mn[v << 1 | 1]);
		mx[v] = Math.max(mx[v << 1], mx[v << 1 | 1]);
	}

	void get(int v, int tl, int tr, int l,int r)
	{
		if(r < tl || l>tr || l>r)
			return;
		if(tl>=l && tr<=r)
		{
			ans+=t[v];
			return;
		}
		push(v, tl, tr);
		int tm = (tl + tr) >> 1;
		get(v << 1, tl, tm, l,r);
		get(v << 1 | 1, tm + 1, tr,l,r);
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
