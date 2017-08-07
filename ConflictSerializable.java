						/*CONFLICT SERIALIZABLE*/

import java.util.*;
public class ConflictSerializable
{
	public static void main(String st[])
	{
		int flag=0,n=0,k=0,j=0,i=0,fl=0;
		String s1="";
		boolean b=true;

		Scanner sc = new Scanner(System.in);
		System.out.println("How many instruction do you want to enter?");
		n=sc.nextInt();

		ArrayList<String> str = new ArrayList<String>();

		System.out.println("Enter "+n+" instructions.");
		for(i=0;i<n;i++)
		{
			str.add(sc.next());
		}
		String s[]=new String[str.size()];
		s=str.toArray(s);

		System.out.println("Schedule S1 before swapping is :");
		print(s);
		
		if(s[0].charAt(1) != '1')
		{
			fl=1;
		}

		n=s.length;
		
		for(i=0;i<n;i++)
		{
			for(j=0;j<(n-1);j++)
			{
				if(fl==1)
				{
					b = s[j].charAt(1) < s[j+1].charAt(1);
				}
				else
				{
					b = s[j].charAt(1) > s[j+1].charAt(1);
				}
				if(b)
				{
					if(s[j].charAt(2)==s[j+1].charAt(2))
					{
						if(s[j].charAt(0)=='w' || s[j+1].charAt(0)=='w')
						{
							flag=1;
						}
						else
						{
							s1=s[j+1];
							s[j+1]=s[j];
							s[j]=s1;
							j=j-2;
							flag=0;
						}
					}
					else
					{
						s1=s[j+1];
						s[j+1]=s[j];
						s[j]=s1;
						j=j-2;
						flag=0;
					}
				}
			}
		}

		if(flag == 0)
		{
			System.out.println("Conflict serializable");
		}
		else
		{
			System.out.println("not Conflict-serializable");
		}
		
		System.out.println("Schedule S1 after swapping is :");
		print(s);
	}

	public static void print(String st[])
	{
		System.out.println("T1  |T2  |T3");
		for(int i=0;i<st.length;i++)
		{
			if(st[i].charAt(1)=='1')
			{
				System.out.println(""+st[i]+" |    |");
			}
			else if(st[i].charAt(1)=='2')
			{
				System.out.println("    |"+st[i]+" |");
			}
			else
			{
				System.out.println("    |    |"+st[i]);
			}
		}
	}
}
