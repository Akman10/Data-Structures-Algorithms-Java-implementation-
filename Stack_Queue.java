import java.io.*;
import java.util.*;
class node
{
	node next;
	int val;
}
class Stack
{
	int size;
	node root;
	public Stack()
	{
		size=0;
		root=null;
	}
	public int size()
	{
		return size;
	}
	public void push(int val)
	{
		if(size==0)
		{
			root=new node();
			root.val=val;
			size++;
		}
		else
		{
			node tem=new node();
			tem.val=val;
			tem.next=root;
			root=tem;
			size++;
		}
	}
	public int pop()
	{
		int val=-1;
		if(size==0)
			return val;
		val=root.val;
		root=root.next;
		size--;
		return val;
	}
}
class Queue
{
	int size;
	node front;
	node rear;
	public Queue()
	{
		size=0;
		front=null;
		rear=null;
	}
	public int size()
	{
		return size;
	}
	public void push(int val)
	{
		if(front==null&&rear==null)
		{
			front=new node();
			front.val=val;
			rear=front;
			size++;
		}
		else
		{
			node tem=new node();
			tem.val=val;
			rear.next=tem;
			rear=tem;
			size++;
		}
	}
	public int pop()
	{
		int val=-1;
		if(size==0)
			return -1;
		val=front.val;
		if(size==1)
		{
			front=null;
			rear=null;
			size--;
			return val;
		}
		front=front.next;
		size--;
		return val;
	}
}
class ques3
{
	public static void main(String args[])
	{
		Queue qu=new Queue();
		qu.push(10);
		qu.push(20);
		qu.push(30);
		qu.push(40);
		System.out.println(qu.pop());
		System.out.println(qu.pop());
		System.out.println(qu.pop());
		System.out.println(qu.pop());
		System.out.println(qu.pop());
		System.out.println(qu.pop());
		System.out.println(qu.pop());
	}
}
