							/*SHARED-NEAREST NEIGHBOUR ALGORITHM*/

import java.util.*;

class Cluster
{	int clusterId;
	Vector<Integer> cElements;
	Cluster()
	{	cElements=new Vector<Integer>();	}
}
class MergedClusters
{	Vector<Integer> clId;	
	MergedClusters()
	{	clId=new Vector<Integer>();	}
}

public class SharesNearestNeighbour
{	static int max=0,min=0;
	public static void main(String args[])
	{	int no_elements,minElements,minNeighbours;
		int i,j,k;
		double minDistance;
		Scanner sc=new Scanner(System.in);
		Random ra=new Random();
		System.out.print("ENTER NO OF ELEMENTS : ");
		no_elements=sc.nextInt();
		System.out.print("ENTER MIN_ELEMENTS & MIN_DISTANCE : ");
		minElements=sc.nextInt();
		minDistance=sc.nextDouble();
		System.out.print("ENTER MINIMUM SHARED NUMBERS : ");
		minNeighbours=sc.nextInt();
		
		int elements[]=new int[no_elements];
		System.out.println("ELEMENTS \n");
		for(i=0;i<no_elements;i++)
		{	elements[i]=ra.nextInt(1000)+1;
			if(i==0)
			{	max=elements[i];	min=elements[i];	}
			else
			{	if(elements[i]>max)
				{	max=elements[i];	}
				if(elements[i]<min)
				{	min=elements[i];	}
			}
			System.out.println(elements[i]);
		}
		System.out.println("\n\n MIN = "+min+" MAX = "+max);
		double distance;
		Vector<Cluster> clusters=new Vector<Cluster>();
		for(i=0;i<no_elements;i++)
		{	Cluster temp=new Cluster();
			temp.clusterId=elements[i];
			for(j=0;j<no_elements;j++)
			{	if(i!=j)
				{	distance=findDistance(elements[i],elements[j]);
					if(distance<=minDistance)
					{	temp.cElements.add(elements[j]);	}
				}
			}
			if(temp.cElements.size()>=minElements)
			{	System.out.println("POSSIBLE CLUSTER-ID : "+temp.clusterId);	
				clusters.add(temp);
			}
		}
		Cluster c1,c2;
		int k1,k2,a,b;
		boolean flag=false;
		MergedClusters mcl;
		Vector<MergedClusters> mElements=new Vector<MergedClusters>();
		int count;
		System.out.println("\n\n");
		for(i=0;i<clusters.size();i++)
		{	c1=clusters.elementAt(i);
			count=0;
			for(j=0;j<clusters.size();j++)
			{	c2=clusters.elementAt(j);
				if(i!=j && c1.clusterId!=c2.clusterId)
				{	for(k1=0;k1<c1.cElements.size();k1++)
					{	a=c1.cElements.elementAt(k1);
						for(k2=0;k2<c2.cElements.size();k2++)
						{	b=c2.cElements.elementAt(k2);
							if(a==b)
							{	count++;	}
						}
					}
					if(count>=minNeighbours)
					{	mcl=new MergedClusters();	
						mcl.clId.add(c1.clusterId);
						mcl.clId.add(c2.clusterId);
						mElements.add(mcl);
						clusters.removeElementAt(j);
						continue;
					}
				}
			}
		}
		a=-1;	b=-1;	k1=-1;	k2=-1;			
		MergedClusters mcl2;
		for(i=0;i<mElements.size();i++)
		{	mcl=mElements.elementAt(i);
			a=mcl.clId.elementAt(0);
			b=mcl.clId.elementAt(1);
			for(j=0;j<mElements.size();j++)
			{	if(i!=j)
				{	mcl2=mElements.elementAt(j);
					k1=mcl2.clId.elementAt(0);
					k2=mcl2.clId.elementAt(1);
					if(a==k1) 
					{	mcl.clId.add(k2);
						mElements.removeElementAt(j);
					}
					else if(a==k2)
					{	mcl.clId.add(k1);
						mElements.removeElementAt(j);
					}
				}
			}
		}
		for(i=0;i<mElements.size();i++)
		{	mcl=mElements.elementAt(i);
			a=mcl.clId.elementAt(0);
			for(j=i+1;j<mElements.size();j++)
			{	mcl2=mElements.elementAt(j);
				b=mcl2.clId.elementAt(0);
				if(a==b)
				{	for(k=1;k<mcl2.clId.size();k++)
					{	mcl.clId.add(mcl2.clId.elementAt(k));	}
					mElements.removeElementAt(j);
				}
			}
		}
		System.out.println(" FINAL CLUSTER WITH COMBINED CLUSTER-CORES ");
		for(i=0;i<mElements.size();i++)
		{	mcl=mElements.elementAt(i);
			System.out.println("\nCLUSTER : "+(i+1));
			for(j=0;j<mcl.clId.size();j++)
			{	System.out.println(mcl.clId.elementAt(j));	}
		}
	}
	static double findDistance(int p1,int p2)
	{	double distance;
		distance=(double)(Math.abs(p1-p2))/(max-min);
		return distance;
	}
}