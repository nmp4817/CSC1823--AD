								/*K-NEAREST NEIGHBOUR ALGORITHM*/
	
import java.util.*;

class ClassItems
{	int classId;
	Vector<Integer> cElements;
	ClassItems()
	{	cElements=new Vector<Integer>();	}
	ClassItems(int cid,int offset)
	{	classId=cid;
		cElements=new Vector<Integer>();
		Random ra=new Random();
		int a;
		for(int i=0;i<20;i++)
		{	a=ra.nextInt(25)+offset;
			cElements.add(a);
		}
	}
}
class Neighbours
{	int classId;
	double distance;
}

public class KNearestNeighbour
{	static int max=100,min=0;
	public static void main(String args[])
	{	int no_elements;
		int i,j,k;
		int kElements;
		Scanner sc=new Scanner(System.in);
		Random ra=new Random();
		System.out.print("ENTER NO OF ELEMENTS : ");
		no_elements=sc.nextInt();
		System.out.print("ENTER K NEAREST NEIGHBOUR : ");
		kElements=sc.nextInt();
		int elements[]=new int[no_elements];
		for(i=0;i<no_elements;i++)
		{	elements[i]=ra.nextInt(100)+1;	}
		ClassItems class1;
		Vector<ClassItems> classes=new Vector<ClassItems>();
		classes.add(new ClassItems(1,1));
		classes.add(new ClassItems(2,26));
		classes.add(new ClassItems(3,51));
		classes.add(new ClassItems(4,76));
		double distance=0;
		for(i=0;i<no_elements;i++)
		{	Vector<Neighbours> neighbours=new Vector<Neighbours>();
			for(j=0;j<classes.size();j++)
			{	class1=classes.elementAt(j);
				for(k=0;k<class1.cElements.size();k++)
				{	distance=findDistance(elements[i],class1.cElements.elementAt(k));
					Neighbours nb=new Neighbours();
					nb.classId=class1.classId;
					nb.distance=distance;
					neighbours.add(nb);
				}
			}
			//SORT THE DATA
			sortNeighbours(neighbours);
			int cnt[]=new int[4];
			Neighbours nb;
			int maxclass=1;
			if(kElements<=neighbours.size())
			{	for(j=0;j<kElements;j++)
				{	nb=neighbours.elementAt(i);
					switch(nb.classId)
					{	case 1 : cnt[0]++; break;
						case 2 : cnt[1]++; break;
						case 3 : cnt[2]++; break;
						case 4 : cnt[3]++; break;
					}
					max=cnt[0];
					for(k=1;k<4;k++)
					{	if(max<cnt[k])
						{	max=cnt[k];	maxclass=k+1;	}
					}
				}
				System.out.println("ELEMENT["+(i+1)+"]\t VALUE : "+elements[i]+"\t CLASS : "+maxclass);
				switch(maxclass)
				{	case 1 : classes.elementAt(0).cElements.add(elements[i]); break;
					case 2 : classes.elementAt(1).cElements.add(elements[i]); break;
					case 3 : classes.elementAt(2).cElements.add(elements[i]); break;
					case 4 : classes.elementAt(3).cElements.add(elements[i]); break;
				}
			}
		}
	}
	static double findDistance(int p1,int p2)
	{	double distance;
		distance=(double)(Math.abs(p1-p2))/(max-min);
		return distance;
	}
	static void sortNeighbours(Vector<Neighbours> nbs)
	{	double a,b;
		Neighbours n1,n2,temp;
		for(int i=0;i<nbs.size()-1;i++)
		{	n1=nbs.elementAt(i);
			for(int j=i+1;j<nbs.size();j++)
			{	n2=nbs.elementAt(j);
				a=n1.distance;
				b=n2.distance;
				if(a>b)
				{	temp=n1;
					n1=n2;
					n2=temp;
					Collections.swap(nbs,i,j);
				}
			}
		}
	}
}