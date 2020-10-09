/*
In this problem we want to find the number of zeros in a given range, and additionally find the index of the k-th zero using a second function

input --> 
--> N
--> array a[0---N-1]
--> Q
--> for each Q input ty
--> if ty == 1  input L,R,K
--> if ty == 2  input x,val

for ty==1 ouput the count of zeros in range [L..R] and ouput the index of kth Zero.
*/

import java.io.*;
import java.util.*;

class ST_5_countzero
{
	public static void main(String[] args)throws Exception{ new ST_5_countzero().run();} 
	long mod=1000000000+7;
	
	int N;
	long t[];
	long ct[];
	void solve() throws Exception
	{
		N=ni();
		t = new long[(4*N)+1];
		ct = new long[(4*N)+1];
		long a[]=new long[N];
		for (int i = 0; i < N; i++) {
			a[i] = nl();
		}
		build(a,1,0,N-1);
		int Q=ni();
		for (int i = 0; i < Q; i++) {
			int ty=ni();
			if(ty==1)
			{
				int L=ni();
				int R=ni();
				int k=ni();
				System.out.println(countzero(1,0,N-1,L-1,R-1));
				System.out.println(find_kth(1, L-1, R-1,k ));
			}
			else
			{
				int x=ni();
				int new_val=ni();
				update(1, 0, N-1, x-1, new_val);
			}
		}
	}
	void build(long a[], int v, int tl, int tr)
	{
		if(tl==tr){
			//t[v]=a[tl];
			if(a[tl]==0)
			t[v]=1;
			}
		else {
			int tm=(tl+tr)/2;
			build(a,2*v,tl,tm);
			build(a,(2*v)+1,tm+1,tr);
			t[v]=(t[2*v]+t[(2*v)+1]);
		}
	}
	long countzero(int v,int tl,int tr, int l,int r)  //range sum
	{
		if(l>r)return 0;
		if(tl==l&&tr==r) return t[v];
		int tm=(tl+tr)/2;
		return 	  (countzero(2*v,tl,tm,l,Math.min(r, tm)) 
				     +countzero((2*v)+1,tm+1,tr,Math.max(l, tm+1),r));
	}
	void update(int v,int tl, int tr,int pos,long new_val) //point query
	{
		if(tl==tr) {
			if(new_val==0)
			t[v]=1;
			else
			t[v]=0;
		}
		else {
		    int tm=(tl+tr)/2;
		    if(pos<=tm)
		    	update(2*v,tl,tm,pos,new_val);
		    else
		    	update((2*v)+1,tm+1,tr,pos,new_val);
		    t[v]=(t[2*v]+t[(2*v)+1]);
		}
	}
	long find_kth(int v, int tl, int tr, long k) // find kth zero from [0 to N-1]
	{
	    if (k > t[v])
	        return -1;
	    if (tl == tr)
	        return tl;
	    int tm = (tl + tr) / 2;
	    if (t[v*2] >= k)
	        return find_kth(v*2, tl, tm, k);
	    else 
	        return find_kth(v*2+1, tm+1, tr, k - t[v*2]);
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
