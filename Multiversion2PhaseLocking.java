						/*MULTIVERSION 2-PHASE LOCKING*/

import java.util.*;
public class Multiversion2PhaseLocking
{
	static int TS =0;
	static int f=0;

	public static void main(String st[])
	{
		int flag=0,n=0,k=0,j=0,i=0,fl=0;
		String s1="";
		boolean b=true;
		int ts_counter=0,Q=10,Q1;

		System.out.println("Starting Values::");
		System.out.println("Q = "+Q);
		System.out.println("ts_counter = "+ts_counter);

		Scanner sc = new Scanner(System.in);
		System.out.println("How many instruction do you want to enter?");
		n=sc.nextInt();

		ArrayList<String> str = new ArrayList<String>();
		String s[]=new String[str.size()];
		Q1=Q;
		System.out.println("Enter "+n+" instructions.");
		for(i=0;i<n;i++)
		{
			str.add(sc.next());
			s=str.toArray(s);
			if(s[i].equals("commit"))
			{
				TS = ts_counter+1;
				ts_counter = ts_counter + 1;
				Q1=Q;
			}
			Q1 = check(s,ts_counter,Q,Q1);
		}
		
		

		System.out.println("Conflict Serializable Schedule is :");
		print(s);
	}

	public static int check(String s[],int ts_counter,int Q,int Q1)
	{
		int j=0,k=0;

		for(int i=(s.length-2);i>=0;i--)
		{
			if(s[i].charAt(0) == 'w')
			{
				j = s[i].charAt(1);
				break;
			}
		}

		if(s[s.length-1].equals("commit"))
		{
			for(int i=(s.length-2);i>=0;i--)
			{
				if(s[i].charAt(0) == 'w')
				{
					k = s[i].charAt(1);
					break;
				}
			}
		}

		if( s[(s.length-1)].charAt(0) == 'r' && TS!=100 )
		{
			System.out.println("Transaction reads latest value!! and Value is Q"+(ts_counter)+" is "+Q1);
		}
		else
		{
			if( s[(s.length-1)].charAt(0) != 'w' && !s[(s.length-1)].equals("commit") )
			{
				System.out.println("Transaction reads old value!! and Value is Q"+(ts_counter)+" is "+Q);			
			}
		}
		
		if( j <= (int)s[(s.length-1)].charAt(1) || j==0 )
		{
			if( (s[(s.length-1)].charAt(0) == 'w' && TS!=100) && j==0 )
			{
				Q1 = Q1 + 10;
				TS = 100;
				System.out.println("New Version Q"+(ts_counter+1)+" = "+Q1+" is created !");
			}

			else if( (s[(s.length-1)].charAt(0) == 'w' && TS==100 && j==(int)s[(s.length-1)].charAt(1)) )
			{
				Q1 = Q1+10;
				System.out.println("Overwrite!");
			}

			else if( s[(s.length-1)].equals("commit") && (k>j || (j==50 && f==0)) )
			{
				Q1 = Q1+10;
				if(f==0){TS = 100;}
				f=1;
				System.out.println("New Version Q"+(ts_counter+1)+" = "+Q1+" is created !");
			}

			else
			{
				if( s[(s.length-1)].charAt(0) != 'r' && !s[(s.length-1)].equals("commit") )
				{
					System.out.println("IN WAITING CONDITION!!");
				}
			}
		}
		return Q1;
	}
	
	public static void print(String st[])
	{
		int k=0;
		System.out.println("T1    |T2    |T3");
		for(int i=0;i<st.length;i++)
		{
			if(st[i].charAt(1)=='1')
			{
				System.out.println(""+st[i]+"   |      |");
			}
			else if(st[i].charAt(1)=='2')
			{
				System.out.println("      |"+st[i]+"   |");
			}
			else if(st[i].charAt(1)=='3')
			{
				System.out.println("      |      |"+st[i]);
			}
			else
			{
				if(k==0)
				{
					System.out.println("commit|      |");
				}
				else if(k==1)
				{
					System.out.println("      |commit|");
				}
				else
				{
					System.out.println("      |      |commit");
				}
			}
		}
	}
}