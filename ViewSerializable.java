						/*VIEW SERIALIZABLE*/

import java.util.*;
public class ViewSerializable
{
	public static void main(String st[])
	{
		int flag=0,n=0,k=0,j=0,i=0,fl=0,l=0,m=0;
		boolean b=true;
		char ch,ch1;
		char c[] = new char[5];
		char c1[] = new char[5];
		String t[] = new String[5];
		String t1[] = new String[5];

		Scanner sc = new Scanner(System.in);
		System.out.println("How many instruction do you want to enter?");
		n=sc.nextInt();

		ArrayList<String> str = new ArrayList<String>();
		ArrayList<String> str1 = new ArrayList<String>();

		System.out.println("Enter "+n+" instructions of Schedule S1.");
		for(i=0;i<n;i++)
		{
			str.add(sc.next());
		}
		String s[]=new String[str.size()];
		s=str.toArray(s);

		System.out.println("Schedule S1 is :");
		print(s);

		System.out.println("Enter "+n+" instructions of Schedule S2.");
		for(i=0;i<n;i++)
		{
			str1.add(sc.next());
		}
		String s1[]=new String[str1.size()];
		s1=str1.toArray(s1);

		System.out.println("Schedule S2 is :");
		print(s1);

//RULE1
		for(i=0;i<n;i++)
		{
			ch = s[i].charAt(2);
			
			if(i==0)
			{
				j=0;
				c[j] = ch;
				j=j+1;
			}

			else
			{
				for(k=0;k<j;k++)
				{
					if(c[k]==ch)
					{
						break;
					}
					else
					{
						c[j] = ch;
						flag=1;
					}
				}
				if(flag==1){j=j+1;flag=0;}
			}
		}

		for(k=0;k<j;k++)
		{
			for(i=0;i<n;i++)
			{
				if(s[i].charAt(2) == c[k] && s[i].charAt(0) == 'r')
				{t[k]=s[i];break;}
			}
			
			for(i=0;i<n;i++)
			{
				if(s1[i].charAt(2) == c[k] && s1[i].charAt(0) == 'r')
				{t1[k]=s1[i];break;}
			}
		}

		for(k=0;k<j;k++)
		{
			if(!t[k].equals(t1[k]))
			{
				fl = 1;
			}
		}

		if(fl==1)
		{
			System.out.println("RULE 1 IS NOT FOLLOWED.");
			System.exit(0);
		}

//RULE2

		for(i=0;i<n;i++)
		{
			if(s[i].charAt(1) == 'r')
			{
				for(k=(i-1);k>=0;k--)
				{
					if(s[k].charAt(2) == s[i].charAt(2) && s[k].charAt(k) == 'w')
					{
						t[l] = s[k];
						l=l+1;
						break;
					}
				}
			}

			if(s1[i].charAt(1) == 'r')
			{
				for(k=(i-1);k>=0;k--)
				{
					if(s1[k].charAt(2) == s1[i].charAt(2) && s1[k].charAt(k) == 'w')
					{
						t[m] = s1[k];
						m=m+1;
						break;
					}
				}
			}
		}

		fl=0;

		for(k=0;k<m;k++)
		{
			if(!t[k].equals(t1[k]))
			{
				fl = 1;
			}
		}

		if(fl==1)
		{
			System.out.println("RULE 2 IS NOT FOLLOWED.");
			System.exit(0);
		}

//RULE 3

		for(k=0;k<j;k++)
		{
			for(i=0;i<n;i++)
			{
				if(s[i].charAt(2) == c[k] && s[i].charAt(0) == 'w')
				{
					t[k] = s[i];	
				}
			}
			for(i=0;i<n;i++)
			{
				if(s1[i].charAt(2) == c[k] && s1[i].charAt(0) == 'w')
				{
					t1[k] = s1[i];	
				}
			}
		}

		fl=0;

		for(k=0;k<m;k++)
		{
			if(!t[k].equals(t1[k]))
			{
				fl = 1;
			}
		}

		if(fl==1)
		{
			System.out.println("RULE 2 IS NOT FOLLOWED.");
			System.exit(0);
		}
		else
		{
			System.out.println("VIEW SERIALIZABLE!");
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
