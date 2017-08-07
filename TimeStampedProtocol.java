						/*TIME-STAMPED PROTOCOL*/

import java.util.*;
public class TimeStampedProtocol
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
		String s[]=new String[str.size()];

		System.out.println("Enter "+n+" instructions.");
		for(i=0;i<n;i++)
		{
			str.add(sc.next());
			s=str.toArray(s);
			check(s);
		}
		
		

		System.out.println("Conflict Serializable Schedule is :");
		print(s);
	}

	public static void check(String s[])
	{
		if(s[(s.length-1)].charAt(0) == 'r')
		{
			for(int i=0;i<s.length;i++)
			{
				if(s[i].charAt(0) == 'w' && s[i].charAt(2) == s[(s.length-1)].charAt(2))
				{
					if(s[(s.length-1)].charAt(1) < s[i].charAt(1))
					{
						System.out.println("Rollback!!!");
						System.exit(0);
					}
				}
			}
		}
			
		if(s[(s.length-1)].charAt(0) == 'w')
		{
			for(int i=0;i<s.length;i++)
			{
				if((s[i].charAt(0) == 'w' || s[i].charAt(0) == 'r') && s[i].charAt(2) == s[(s.length-1)].charAt(2))
				{
					if(s[(s.length-1)].charAt(1) < s[i].charAt(1))
					{
						System.out.println("Rollback!!!");
						System.exit(0);
					}
				}
			}	
		}
		
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