							/*Apriori ALGORITHM*/
							
import java.util.*;

class Set1
{	int  item;
	int supportCount;
	Set1(int a)
	{	item=a;	supportCount=0;	}
	void setSC(int s)
	{	supportCount=s;	}
	int getSC()
	{	return supportCount;	}
}

class Set2
{	int item1,item2;	
	int supportCount;
	Set2(int a,int b)
	{	item1=a;	item2=b;	supportCount=0;	}
	void setSC(int s)
	{	supportCount=s;	}
	int getSC()
	{	return supportCount;	}
}

class Set3
{	int item1,item2,item3;
	int supportCount;
	Set3(int a,int b,int c)
	{	item1=a;	item2=b;	item3=c;	supportCount=0;	}
	void setSC(int s)
	{	supportCount=s;	}
	int getSC()
	{	return supportCount;	}
}

public class Apriori
{	public static void main(String[] args)
	{	Scanner sc=new Scanner(System.in);
		int no_items,no_trans,minSupport,minConf;
		System.out.print("ENTER NO OF ITEMS AND TRANSACTIONS : ");
		no_items=sc.nextInt();
		no_trans=sc.nextInt();
		int transactions[][]=new int[no_trans][no_items];
		int translen[]=new int[no_trans];
		Set1 iset1,iset12;
		Vector<Set1> itemsarr=new Vector<Set1>();
		int i,j,k;
		for(i=0;i<no_items;i++)
		{	iset1=new Set1(i+1);	
			itemsarr.add(iset1);
		}
		
		for(i=0;i<no_trans;i++)
		{	System.out.print("\nITEMS FOR TRANSACTION "+(i+1)+"(ENTER 0 TO STOP) : ");
			for(j=0;j<no_items;j++)
			{	k=sc.nextInt();
				if(k==0)
				{	break;	}
				else
				{	transactions[i][j]=k;	}
			}
			translen[i]=j;
		}
		System.out.print("\nENTER MIN SUPPORT COUNT & MIN CONFIDENCE (%): ");
		minSupport=sc.nextInt();
		minConf=sc.nextInt();
		
		//Checking for FirstCandidateList
		int scount;
		Vector<Set1> freq1=new Vector<Set1>();
		for(i=0;i<itemsarr.size();i++)
		{	iset1=itemsarr.elementAt(i);
			int a[]={iset1.item};
			scount=getSupportCount(transactions,translen,a);
			iset1.setSC(scount);
			System.out.println("ITEM "+(i+1)+" SUPPORT COUNT : "+scount);	
			if(scount>=minSupport)
			{	freq1.add(iset1);	}
		}
		System.out.println("\nFREQUENT SET 1");
		for(i=0;i<freq1.size();i++)
		{	iset1=freq1.elementAt(i);
			System.out.print(iset1.item+"\t");	}
		
		Vector<Set2> cnd2,freq2;
		Set2 iset2;
		Set3 iset3;
		Vector<Set3> cnd3,freq3;
		if(freq1.size()>1)
		{	int size=nCr(freq1.size(),2);
			System.out.println("\n\nCANDIDATE SET 2");
			cnd2=new Vector<Set2>(size);
			for(i=0;i<freq1.size();i++)
			{	for(j=i+1;j<freq1.size();j++)
				{	iset1=freq1.elementAt(i);	
					iset12=freq1.elementAt(j);
					iset2=new Set2(iset1.item,iset12.item);
					cnd2.add(iset2);
					System.out.println(iset1.item+" - "+iset12.item);
				}
			}	
			
			//FINDING FREQUENT SET 2 
			System.out.println("\n\n");
			freq2=new Vector<Set2>();
			for(i=0;i<cnd2.size();i++)
			{	iset2=cnd2.elementAt(i);
				int a[]={iset2.item1,iset2.item2};	
				scount=getSupportCount(transactions,translen,a);
				iset2.setSC(scount);
				System.out.println("ITEM "+iset2.item1+" - "+iset2.item2+" SUPPORT COUNT : "+scount);	
				if(scount>=minSupport)
				{	freq2.add(iset2);	}
			}
			System.out.println("\nFREQUENT SET 2");
			for(i=0;i<freq2.size();i++)
			{	iset2=freq2.elementAt(i);
				System.out.println(iset2.item1+" - "+iset2.item2);	
			}
			if(freq2.size()>0)
			{	//CHECKING FOR CANDIDATE SET 3
				cnd3=new Vector<Set3>();
				Set2 f1;
				boolean b1=false;
				for(i=0;i<freq2.size();i++)
				{	f1=freq2.elementAt(i);
					for(j=i+1;j<freq2.size();j++)
					{	iset2=freq2.elementAt(j);
						if(f1.item1==iset2.item1 || f1.item2==iset2.item1)
						{	int i1=-1,i2=-1;
							if(f1.item1==iset2.item1)
							{	i1=f1.item2;	i2=iset2.item2;	}
							else if(f1.item2==iset2.item1)
							{	i1=f1.item1;	i2=iset2.item2;	}
							for(k=0;k<cnd2.size();k++)
							{	iset2=cnd2.elementAt(k);
								if(iset2.item1==i1 && iset2.item2==i2)
								{	if(iset2.getSC()>=minSupport)
									{	b1=true;	}
									else
									{	b1=false;	}
									break;
								}
							}							
							if(b1)
							{	iset3=new Set3(f1.item1,f1.item2,iset2.item2);
								cnd3.add(iset3);
							}
						}
					}
				}
				if(cnd3.size()==0)
				{	System.out.println("CANDIDATE SET 3 EMPTY");	
					System.out.println("\n\n GETTING CONFIDENCE FOR FREQUENT SET 2");
					findConfidence2(freq1,freq2,minConf);
				}
				else
				{	System.out.println("\n\nCANDIDATE SET 3");
					Set3 temp;
					for(i=0;i<cnd3.size();i++)
					{	iset3=cnd3.elementAt(i);
						for(j=0;j<cnd3.size();j++)
						{	if(i!=j)
							{	temp=cnd3.elementAt(j);	
								if(iset3.item1==temp.item1 && iset3.item2==temp.item2 && iset3.item3==temp.item3)
								{	cnd3.removeElementAt(j);	}
							}
						}
					}
					//FINDING FREQUENT SET 3
					freq3=new Vector<Set3>();
					for(i=0;i<cnd3.size();i++)
					{	iset3=cnd3.elementAt(i);
						int a[]={iset3.item1,iset3.item2,iset3.item3};	
						scount=getSupportCount(transactions,translen,a);
						iset3.setSC(scount);
						System.out.println("ITEM "+iset3.item1+" - "+iset3.item2+" - "+iset3.item3+" SUPPORT COUNT : "+scount);	
						if(scount>=minSupport)
						{	freq3.add(iset3);	}
					}
					if(freq3.size()>0)
					{	System.out.println("\n\nFREQUENT SET 3");
						for(i=0;i<freq3.size();i++)
						{	iset3=freq3.elementAt(i);	
							System.out.println("ITEM "+iset3.item1+" - "+iset3.item2+" - "+iset3.item3);	
						}
						//FINDING COFIDENCE 
						System.out.println("\n\n");
						int it1=0,it2=0,it3=0;
						int sup1=1,sup2=1,sup3=1,totsup=1,sup12=1,sup13=1,sup23=1;
						float conf1_23,conf2_13,conf3_12,conf12_3,conf13_2,conf23_1;
						int count=0;
						for(i=0;i<freq3.size();i++)
						{	iset3=freq3.elementAt(i);
							it1=iset3.item1;	it2=iset3.item2;	it3=iset3.item3;
							totsup=iset3.getSC();
							for(j=0;j<freq1.size();j++)
							{	iset1=freq1.elementAt(j);	
								if(iset1.item==it1)
								{	sup1=iset1.getSC();	}
								if(iset1.item==it2)
								{	sup2=iset1.getSC();	}
								if(iset1.item==it3)
								{	sup3=iset1.getSC();	}
							}
							for(j=0;j<freq2.size();j++)
							{	iset2=freq2.elementAt(j);	
								if((iset2.item1==it1 && iset2.item2==it2)|| (iset2.item1==it2 && iset2.item2==it1))
								{	sup12=iset2.getSC();	}
								if((iset2.item1==it1 && iset2.item2==it3)|| (iset2.item1==it3 && iset2.item2==it1))
								{	sup13=iset2.getSC();	}
								if((iset2.item1==it2 && iset2.item2==it3)|| (iset2.item1==it3 && iset2.item2==it2))
								{	sup23=iset2.getSC();	}
							}
							conf1_23=((float)totsup/sup1)*100;
							conf2_13=((float)totsup/sup2)*100;
							conf3_12=((float)totsup/sup3)*100;
							conf12_3=((float)totsup/sup12)*100;
							conf13_2=((float)totsup/sup13)*100;
							conf23_1=((float)totsup/sup23)*100;
							System.out.println("\n CONFIDENCE CHECKING FOR SET "+(i+1));
							System.out.println("CONFIDENCE "+it1+" -> "+it2+","+it3+" = "+conf1_23);
							System.out.println("CONFIDENCE "+it2+" -> "+it1+","+it3+" = "+conf2_13);
							System.out.println("CONFIDENCE "+it3+" -> "+it1+","+it2+" = "+conf3_12);
							System.out.println("CONFIDENCE "+it1+","+it2+" -> "+it3+" = "+conf12_3);
							System.out.println("CONFIDENCE "+it1+","+it3+" -> "+it2+" = "+conf13_2);
							System.out.println("CONFIDENCE "+it2+","+it3+" -> "+it1+" = "+conf23_1);
							System.out.println("\n\n");
							if(conf1_23>minConf)
							{	System.out.println("INTERESTING RULE "+it1+" -> "+it2+","+it3);	}
							if(conf2_13>minConf)
							{	System.out.println("INTERESTING RULE "+it2+" -> "+it1+","+it3);	}
							if(conf3_12>minConf)
							{	System.out.println("INTERESTING RULE "+it3+" -> "+it1+","+it2);	}
							if(conf12_3>minConf)
							{	System.out.println("INTERESTING RULE "+it1+","+it2+" -> "+it3);	}
							if(conf13_2>minConf)
							{	System.out.println("INTERESTING RULE "+it1+","+it3+" -> "+it2);	}
							if(conf23_1>minConf)
							{	System.out.println("INTERESTING RULE "+it2+","+it3+" -> "+it1);	}
						}
					}
					else
					{	System.out.println("\n\nFREQUENT SET 3 EMPTY ");
						System.out.println("\n\n GETTING CONFIDENCE FOR FREQUENT SET 2");
						findConfidence2(freq1,freq2,minConf);
					}
				}
			}
			else
			{	System.out.println("\n END OF PROCESS");
				System.out.println("INTERESTING ITEMS ARE THAT OF FREQUENT SET 1 IN NO PARTICULAR ORDER");
			}
		}
		else 
		{	System.out.println("\n END OF PROCESS");
			if(freq1.size()==1)
			{	System.out.println("INTERSTING ITEM : "+freq1.elementAt(0));	}
			else
			{	System.out.println("NO INTERESTING ITEM");	}
		}
	}
	static void findConfidence2(Vector<Set1> freq1,Vector<Set2> freq2,int minConf)
	{	int sup1=1,sup2=1,totsup=1;
		float conf1,conf2;
		int it1,it2;
		Set1 iset1;
		Set2 iset2;
		for(int i=0;i<freq2.size();i++)
		{	iset2=freq2.elementAt(i);	
			it1=iset2.item1;	it2=iset2.item2;
			totsup=iset2.getSC();
			for(int j=0;j<freq1.size();j++)
			{	iset1=freq1.elementAt(j);	
				if(iset1.item==it1)
				{	sup1=iset1.getSC();	}
				if(iset1.item==it2)
				{	sup2=iset1.getSC();	}
			}
			conf1=((float)totsup/sup1)*100;
			conf2=((float)totsup/sup2)*100;
			System.out.println("\n CONFIDENCE CHECKING FOR SET "+(i+1));
			System.out.println("CONFIDENCE "+it1+" - "+it2+" = "+conf1);
			System.out.println("CONFIDENCE "+it2+" - "+it1+" = "+conf2);
			if(conf1>=minConf)
			{	System.out.println("INTERESTING RULE : "+it1+" - "+it2);	}
			if(conf2>=minConf)
			{	System.out.println("INTERESTING RULE : "+it2+" - "+it1);	}
		}
	}
	static int factorial(int n)
	{	int f=1;
		for(int i=1;i<=n;i++)
		{	f=f*i;	}
		return f;
	}
	static int nCr(int n,int r)
	{	int result;
		int n1,r1,nr1;
		n1=factorial(n);
		r1=factorial(r);
		nr1=factorial(n-r);
		result=n1/(r1*nr1);
		return result;
	}
	static int getSupportCount(int trans[][],int tlen[],int items[])
	{	boolean flags[]=new boolean[items.length];
		int suppcount=0;
		boolean flag;
		int i,j,k;
		for(i=0;i<trans.length;i++)
		{	for(j=0;j<items.length;j++)
			{	flags[j]=false;	}
			for(j=0;j<tlen[i];j++)
			{	for(k=0;k<items.length;k++)
				{	if(trans[i][j]==items[k])
					{	flags[k]=true;	}
				}
			}
			flag=true;
			for(k=0;k<items.length;k++)
			{	if(!flags[k])
				{	flag=false;	break;	}
			}
			if(flag)
			{	suppcount++;	}
		}
		return suppcount;
	}
}
