								/*KMEANS ALGORITHM*/

import java.util.*;

class Records
{	int marks;
	int phdis,gender;
	int education;
	int haircol;
	Records()
	{}
	Records(Records i)
	{	this.marks=i.marks;
		this.phdis=i.phdis;
		this.gender=i.gender;
		this.education=i.education;
		this.haircol=i.haircol;
	}
}

public class KMeans
{	static int max,min;
	static String edu[]={"PHD","PG","UG","HSC","SSC"};
	static String hc[]={"BLACK","RED","BROWN","GREY"};
	static String pd[]={"NO","YES"};
	static String gd[]={"FEMALE","MALE"};
	public static void main(String args[])
	{	Records r1[]=new Records[40];
		Random ra=new Random();
		
		int t;
		for(int i=0;i<20;i++)
		{	r1[i]=new Records();
			r1[i].marks=ra.nextInt(101);
			r1[i].phdis=ra.nextInt(2);
			r1[i].gender=ra.nextInt(2);
			r1[i].education=ra.nextInt(5);
			r1[i].haircol=ra.nextInt(4);
			if(i==0)
			{	max=r1[i].marks;	min=r1[i].marks;	}
			else
			{	if(r1[i].marks<min)
				{	min=r1[i].marks;	}
				if(r1[i].marks>max)
				{	max=r1[i].marks;	}
			}
		}
		if(min==0)
		{	min=1;	}
		Records mean1=r1[4],mean2=r1[8],mean3=r1[12],mean4=r1[16];
		Vector<Records> cluster1=new Vector<Records>();
		Vector<Records> cluster2=new Vector<Records>();
		Vector<Records> cluster3=new Vector<Records>();
		Vector<Records> cluster4=new Vector<Records>();
		Records nmean1,nmean2,nmean3,nmean4;
		double dist1,dist2,dist3,dist4;
		int clust,round=1;
		double dist,mindist;
		while(true)
		{	for(int i=0;i<20;i++)
			{	dist=mindist=findDistance(mean1,r1[i]);
				clust=0;
				dist=findDistance(mean2,r1[i]);
				if(dist<mindist)
				{	clust=1;	
					mindist=dist;
				}
				dist=findDistance(mean3,r1[i]);
				if(dist<mindist)
				{	clust=2;	
					mindist=dist;
				}
				dist=findDistance(mean4,r1[i]);
				if(dist<mindist)
				{	clust=3;	}
				switch(clust)
				{	case 0 : cluster1.add(r1[i]);
							 break;
					
					case 1 : cluster2.add(r1[i]);
							 break;
					
					case 2 : cluster3.add(r1[i]);
							 break;
					
					case 3 : cluster4.add(r1[i]);
							 break;
				}
			}
			nmean1=findNew(cluster1);
			nmean2=findNew(cluster2);
			nmean3=findNew(cluster3);
			nmean4=findNew(cluster4);
			dist1=findDistance(nmean1,mean1);
			dist2=findDistance(nmean2,mean2);
			dist3=findDistance(nmean3,mean3);
			dist4=findDistance(nmean4,mean4);
			if(dist1==0 && dist2==0 && dist3==0 && dist4==0)
			{	break;	}
			System.out.println("\nROUND : "+round+"\nDISTANCE1 :"+dist1+"\nDISTANCE2 :"+dist2+"\nDISTANCE3 :"+dist3+"\nDISTANCE4 :"+dist4);	
			cluster1.removeAllElements();	cluster2.removeAllElements();	
			cluster3.removeAllElements();	cluster4.removeAllElements();
			mean1=nmean1;	mean2=nmean2;	mean3=nmean3;	mean4=nmean4;
			round++;
		}
		System.out.println("\n\n FINAL CLUSTERS\n CLUSTER 1");
		showCluster(cluster1);
		System.out.println("\n\n CLUSTER 2");
		showCluster(cluster2);
		System.out.println("\n\n CLUSTER 3");
		showCluster(cluster3);
		System.out.println("\n\n CLUSTER 4");
		showCluster(cluster4);
	}
	
	static void showCluster(Vector<Records> c1)
	{	Records rec;
		for(int i=0;i<c1.size();i++)
		{	rec=c1.get(i);
			System.out.println("\n RECORD : "+(i+1));
			System.out.println(" MARKS : "+rec.marks+"\n GENDER : "+gd[rec.gender]+"\n PHYSICALLY DISABLE : "+pd[rec.phdis]+"\n EDUCATION : "+edu[rec.education]+"\n HAIR COLOR : "+hc[rec.haircol]);
		}
	}
	
	static Records findNew(Vector<Records> v1)
	{	Records rec;
		Records mean=new Records();
		double marksmean,sum=0;
		int countedu[]={0,0,0,0,0},counthair[]={0,0,0,0};
		int genderm=0,phdyes=0;
		for(int i=0;i<v1.size();i++)
		{	rec=v1.get(i);
			sum+=rec.marks;
			genderm+=rec.gender;
			phdyes+=rec.phdis;
			switch(rec.education)
			{	case 0:	countedu[0]++;	break;
				case 1:	countedu[1]++;	break;
				case 2:	countedu[2]++;	break;
				case 3:	countedu[3]++;	break;
				case 4:	countedu[4]++;	break;
			}
			switch(rec.haircol)
			{	case 0:counthair[0]++;	break;
				case 1:counthair[1]++;	break;
				case 2:counthair[2]++;	break;
				case 3:counthair[3]++;	break;
			}
		}
		marksmean=sum/v1.size();
		mean.marks=(int)marksmean;
		if(genderm>(v1.size()%2))
		{	mean.gender=1;	}
		else
		{	mean.gender=0;	}
		if(phdyes>(v1.size()%2))
		{	mean.phdis=1;	}
		else
		{	mean.phdis=0;	}
		int maxed=countedu[0];
		mean.education=0;
		for(int i=1;i<5;i++)
		{	if(maxed<countedu[i])
			{	maxed=countedu[i];
				mean.education=i;
			}
		}
		maxed=counthair[0];
		mean.haircol=0;
		for(int i=1;i<4;i++)
		{	if(maxed<counthair[i])
			{	maxed=counthair[i];
				mean.haircol=i;
			}
		}
		return mean;
	}
	static double findDistance(Records r1,Records r2)
	{	double dist;
		double d1,d2=1,d3,d4=1,d5=1;
		double d11,d22,d33,d44,d55;
		
		if(r1.marks==0 || r2.marks==0)
		{	d1=0;
			d11=0;
		}
		else
		{	d1=1;
			int a=Math.abs(r1.marks-r2.marks);
			int b=max-min;
			d11=(double)a/b;
		}
		
		if(r1.gender==r2.gender)
		{	d22=0;	}
		else
		{	d22=1;	}
		
		if(r1.phdis==0 && r2.phdis==0)
		{	d3=0;	}
		else
		{	d3=1;	}
		
		if(r1.phdis==r2.phdis)
		{	d33=0;	}
		else
		{	d33=1;	}
		
		double z1,z2;
		z1=(double)(r1.education)/4;
		z2=(double)(r2.education)/4;
		d44=Math.abs(z1-z2);
		
		if(r1.haircol==r2.haircol)
		{	d55=0;	}
		else
		{	d55=1;	}
		
		double a=d1*d11+d2*d22+d3*d33+d4*d44+d5*d55;
		double b=d1+d2+d3+d4+d5;
		dist=a/b;
		return dist;
	}
}