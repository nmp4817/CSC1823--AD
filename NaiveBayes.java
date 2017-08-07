								/*NAIVE-BAYES ALGORITHM*/

import java.util.*;

class Person
{	int rowid,age,income,student,buysCPU,credit;
	Person(int rid)
	{	Random ra=new Random();
		rowid=rid;
		age=ra.nextInt(3);
		income=ra.nextInt(3);
		credit=ra.nextInt(2);
		student=ra.nextInt(2);
		buysCPU=ra.nextInt(2);
	}
}

public class NaiveBayes
{	public static void main(String args[])
	{	int no_elements;
		int i,j;
		Scanner sc=new Scanner(System.in);
		System.out.print("ENTER NO OF ELEMENTS FOR TRAINING DATA: ");
		no_elements=sc.nextInt();
		String ageclass[]={"<30","31-40",">40"};
		String incomeclass[]={"LOW","MEDIUM","HIGH"};
		String creditclass[]={"FAIR","EXCELLENT"};
		String yesno[]={"YES","NO"};
		
		Vector<Person> tdata=new Vector<Person>();
		System.out.println("\n TRAINING DATA");
		for(i=0;i<no_elements;i++)
		{	Person per=new Person(i+1);
			tdata.add(per);
			System.out.println("ROW-ID : "+per.rowid+"\t AGE : "+ageclass[per.age]+"\t INCOME : "+incomeclass[per.income]+"\t STUDENT : "+yesno[per.student]+"\t CREDIT : "+creditclass[per.credit]+"\t BUYS_CPU : "+yesno[per.buysCPU]);
		}
		System.out.println("\n\nTEST DATA : ");
		Person test=new Person(no_elements+1);
		System.out.println("ROW-ID : "+test.rowid+"\t AGE : "+ageclass[test.age]+"\t INCOME : "+incomeclass[test.income]+"\t STUDENT : "+yesno[test.student]+"\t CREDIT : "+creditclass[test.credit]+"\t BUYS_CPU : "+yesno[test.buysCPU]);
		
		//APPLYING NAIVE BAYES ALGO
		//YES NO PROBABILITY
		int yescount=0;
		double pyes,pno;
		Person per;
		for(i=0;i<no_elements;i++)
		{	per=tdata.elementAt(i);
			if(per.buysCPU==0)
			{	yescount++;	}
		}
		pyes=(double)yescount/no_elements;
		pno=1-pyes;
		System.out.println("\n\nPYES : "+pyes+" PNO : "+pno);
		int nocount=no_elements-yescount;
		int aycount=getCount(tdata,1,test.age,0);
		int ancount=getCount(tdata,1,test.age,1);
		int iycount=getCount(tdata,2,test.income,0);
		int incount=getCount(tdata,2,test.income,1);
		int sycount=getCount(tdata,3,test.student,0);
		int sncount=getCount(tdata,3,test.student,1);
		int cycount=getCount(tdata,4,test.credit,0);
		int cncount=getCount(tdata,4,test.credit,1);
		double pay=(double)aycount/yescount;
		double pan=(double)ancount/nocount;
		double piy=(double)iycount/yescount;
		double pin=(double)incount/nocount;
		double psy=(double)sycount/yescount;
		double psn=(double)sncount/nocount;
		double pcy=(double)cycount/yescount;
		double pcn=(double)cncount/nocount;
		double ptotyes=pyes*pay*piy*psy*pcy;
		double ptotno=pno*pan*pin*psn*pcn;
		System.out.println("PTOTYES : "+ptotyes+" PTOTNO : "+ptotno);
		if(ptotyes>=ptotno)
		{	System.out.println("\n\n TEST DATA BELONG TO CLASS YES");	}
		else
		{	System.out.println("\n\n TEST DATA BELONG TO CLASS NO");	}
	}
	static int getCount(Vector<Person> tdata,int param,int paramValue,int yesno)
	{	int count=0;
		for(int i=0;i<tdata.size();i++)
		{	Person per=tdata.elementAt(i);	
			switch(param)
			{	case 1 :if(per.age==paramValue && per.buysCPU==yesno)
						{	count++;	}
						break;
				case 2 :if(per.income==paramValue && per.buysCPU==yesno)
						{	count++;	}
						break;		
				case 3 :if(per.student==paramValue && per.buysCPU==yesno)
						{	count++;	}
						break;
				case 4 :if(per.credit==paramValue && per.buysCPU==yesno)
						{	count++;	}
						break;		
			}
		}
		return count;
	}
}