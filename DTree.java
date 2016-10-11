import java.io.*;
import java.util.*;
import jxl.*;
import jxl.read.biff.BiffException;
public class DTree 
{
	double ds[][]=new double[1000][6];
	int rows=0;
	String features[]=new String[6];
	
	Node root;
	Tree tobj[]=new Tree[10];
	
	boolean flag=false;
	///////

	////////////
	public DTree(String files[])throws IOException
	{
		for(int i=0;i<10;i++)
		{
			/*System.out.print("\n======================================================");
			System.out.print("\nTaining File : "+files[i]);
			System.out.print("\n======================================================");*/
			System.out.print("\n\n\nTree......."+i+"\n");
			tobj[i]=new Tree();
			readDataSet(files[i],i);
			
		}
	}
	public void predictClasses(String testfile)
	{
		Tree t=new Tree();
		t.searchFeatures(tobj,testfile);
		
		
	}
	
	
		public void readDataSet(String path,int index) throws IOException
		{
			 int i=0;
			try 
			{

			  Workbook workbook = Workbook.getWorkbook(new File(path));
	          Sheet sheet = workbook.getSheet(0);
	          
			  for(int j=0;j<=5;j++)
			  {
				  Cell cell1 = sheet.getCell(j,0);
				  features[j]=""+cell1.getContents();
				//  System.out.print(" "+features[j]);
			  }
	          
			   i=0;	 	
	          while(i<=1000)
			  {
	            for(int j=0;j<=5;j++)
	            {
	               Cell cell1 = sheet.getCell(j,i+1);  //i+1
	               ds[i][j]=Double.parseDouble(cell1.getContents());
	            // System.out.print(ds[i][j]+  "    ");
	            }
	            //System.out.println();
	            i++;
			  }
	          workbook.close();
			}
			catch(Exception e)	
			{
				//System.out.print("\nError :"+e);
			}
	        rows=i;  //i-1
	        //System.out.print("\nrows="+rows);
	        flag=true;
	       constructDecisionTree(ds, index,rows);
	       
	       tobj[index].printTree();
	       
/*	        Node newnode=constructNode(ds,rows);
			System.out.print("\n Root node..\n Eval="+newnode.eval+" gain="+newnode.gain);
		//	System.out.print("\n feature="+newnode.feature+" rows="+newnode.rows+" col="+newnode.colno);
			//System.out.print("\n ds cols="+newnode.ds[0].length);
			tobj[index].insertNode(newnode);

			constructTree(newnode,index);
	     	tobj[index].printTree(); */
			//Tree t=new Tree();
			//t.searchFeatures(tobj);
		}
		////////////////
		public void constructDecisionTree(double ds[][],int index,int rows)throws IOException
		{
			Node newnode=constructNode(ds,rows);
			//System.out.print("\n Root node..\n Eval="+newnode.eval+" gain="+newnode.gain);
		//	System.out.print("\n feature="+newnode.feature+" rows="+newnode.rows+" col="+newnode.colno);
			//System.out.print("\n ds cols="+newnode.ds[0].length);
			newnode.child="root node";
			if(flag==true)
			{
			tobj[index].insertNode(newnode);
			}

			constructTree(newnode,index);
	        //tobj.levelOrderTraversal(tobj.root);
			 

		}
		
		
		
		////////////////
		public void constructTree(Node newnode,int index) throws IOException 
		{
				try
				{
				
			if(newnode.rows>10)
			{
				//System.out.print("\nSplitting################## rows="+newnode.rows);
				Node leftnode=new Node();
				Node rightnode=new Node();
				splitNode(newnode,leftnode,rightnode,newnode.colno);
				//System.out.print("\n leftrows="+leftnode.rows);
				if(leftnode.rows>0 && leftnode.rows!=newnode.rows )
				//if(leftnode.rows>20)
				{
				//System.out.print("\n leftnode  rows="+leftnode.rows);
					//System.out.print("\n left side col= "+newnode.colno);
					
				leftnode=constructNode(leftnode.ds,leftnode.rows);
			//	System.out.print("\n left node..\n Eval="+leftnode.eval+" gain="+leftnode.gain);
				//System.out.print("\n feature="+leftnode.feature+" rows="+leftnode.rows+" col="+leftnode.colno);
				//System.out.print("\n ds cols="+leftnode.ds[0].length);
				classCountOfDataSet(leftnode, leftnode.rows, leftnode.colno);
				leftnode.child="leftnode";
				tobj[index].insertNode(leftnode);
				//for(int i=1;i<=4;i++)
				//{
				//	System.out.print("\n class: "+i+" = "+leftnode.clas_count[i]);
				//}
				/*System.out.print("\n moving left");
				int n=System.in.read();*/
				/*for(int i=0;i<leftnode.rows ;i++)
				{
					System.out.print("\n");
					for(int j=0;j<6;j++)
					{
						System.out.print("   "+leftnode.ds[i][j]);
					}
				}*/
				constructTree(leftnode,index);
				
				if(leftnode.rows>10)
				{
					//System.out.print("\npending left***********************");
					flag=false;
					constructDecisionTree(leftnode.ds, index,leftnode.rows);
				/*Node newnode1=constructNode(leftnode.ds,leftnode.rows);
				System.out.print("\n pending left node..\n Eval="+newnode1.eval+" gain="+newnode1.gain);
			//	System.out.print("\n feature="+newnode.feature+" rows="+newnode.rows+" col="+newnode.colno);
				//System.out.print("\n ds cols="+newnode.ds[0].length);
				tobj[index].insertNode(newnode1); */
				}
				
				
				}
				
				//System.out.print("\n rightrows="+rightnode.rows);
				if(rightnode.rows>0 && rightnode.rows!=newnode.rows)
				//if(rightnode.rows>20)
				{	
					//System.out.print("\n right side col= "+newnode.colno);	
				//System.out.print("\n rihgtnode  rows="+rightnode.rows);
				rightnode=constructNode(rightnode.ds,rightnode.rows);
				//System.out.print("\n right node..\n Eval="+rightnode.eval+" gain="+rightnode.gain);
				//System.out.print("\n feature="+rightnode.feature+" rows="+rightnode.rows+" col="+rightnode.colno);
				//System.out.print("\n ds cols="+rightnode.ds[0].length);
				
				classCountOfDataSet(rightnode, rightnode.rows, rightnode.colno);
				rightnode.child="rightnode";
				tobj[index].insertNode(rightnode);
				//for(int i=1;i<=4;i++)
				//{
				//	System.out.print("\n class: "+i+" = "+rightnode.clas_count[i]);
				//}
				/*System.out.print("\n moving right");
				int n=System.in.read();*/
				
				
				/*for(int i=0;i<rightnode.rows ;i++)
				{
					System.out.println();
					for(int j=0;j<6;j++)
					{
						System.out.print("   "+rightnode.ds[i][j]);
					}
				}*/
				
				
				constructTree(rightnode,index);
				if(rightnode.rows>10)
				{
					//System.out.print("\npending right***********************");
					flag=false;
					constructDecisionTree(rightnode.ds, index,rightnode.rows);
				/*Node newnode1=constructNode(rightnode.ds,rightnode.rows);
				System.out.print("\n pending right node..\n Eval="+newnode1.eval+" gain="+newnode1.gain);
			//	System.out.print("\n feature="+newnode.feature+" rows="+newnode.rows+" col="+newnode.colno);
				//System.out.print("\n ds cols="+newnode.ds[0].length);
				tobj[index].insertNode(newnode1); */
				}

				}
				
				
				//***************************
				
				
				
				
				
				//constructTree(newnode,index);
				
				//***************************
				
				
				
				
				
			}
			else
			{
				//System.out.print("\nnot Splitting============ rows="+newnode.rows);
			}
				}catch(NumberFormatException e)
				{
					System.out.print("Error :"+e);
				}
		}
		
		
		
		public void classCountOfDataSet(Node node,int rows,int colno)
		{
			for(int i=0;i<node.rows;i++)
			{
				node.clas_count[(int)node.ds[i][5]]++;
				/*System.out.println();
				for(int j=0;j<6;j++)
				{
					System.out.print("  "+node.ds[i][j]);
				}*/
			}
			
		
		}
		//////////////////
		public void splitNode(Node node,Node left,Node right,int colno)
		{
			left.ds=new double[node.rows][6];
			right.ds=new double[node.rows][6];
			int lrows=0,rrows=0;
			for(int i=0;i<node.rows;i++)
			{
				
					if(node.ds[i][colno]<node.gain)
					{
						for(int k=0;k<6;k++)
						{
						 left.ds[lrows][k]=node.ds[i][k];
						 
						 
						}
						left.clas_count[(int)left.ds[lrows][5]]++;
			
						lrows++;
					}
					else if(node.ds[i][colno]>=node.gain)
					{
						for(int k=0;k<6;k++)
						{
						  right.ds[rrows][k]=node.ds[i][k];
						  
						}
						right.clas_count[(int)right.ds[rrows][5]]++;
						rrows++;
					}
			}
			left.rows=lrows;
			right.rows=rrows;
			
		}
		
		////////////////*************************************
		public Node constructNode(double ds[][],int rows)
		{
			   int cols[]=read3RandCols();
			   double parentEntropy=calculateParentEntropy(ds,rows,5);
		      // System.out.print("\nParent Eval="+parentEntropy);
		       double c1gain=calculateGainOfCol(ds,rows,cols[0],parentEntropy);
		     // System.out.print("\ngain of col "+cols[0]+" is : "+c1gain);
		       
		       double c2gain=calculateGainOfCol(ds,rows,cols[1],parentEntropy);
		     // System.out.print("\ngain of col "+cols[1]+ " is : "+c2gain);
		       
		       double c3gain=calculateGainOfCol(ds,rows,cols[2],parentEntropy);
		      // System.out.print("\ngain of col "+cols[2]+ " is : "+c3gain);
		       double maxgain;
		       int fcol=0;
		       String feature;
		       if(c1gain>c2gain && c1gain>c3gain)
		       {
		    	   maxgain=c1gain;
		    	   fcol=cols[0];

		       }
		       else if(c2gain>c3gain)
		       {
		    	   maxgain=c2gain;
		    	   fcol=cols[1];
		       }
		       else
		       {
		    	   maxgain=c3gain;
		    	   fcol=cols[2];
		       }
		     //  System.out.print("\nMax gain = "+maxgain);
		       Node root=new Node();
		       
		       root.eval=parentEntropy;
		       if(maxgain>1.0)
		    	   maxgain=1.0;
		       root.gain=maxgain;
		       root.feature=features[fcol];
		      

		       root.ds=new double[rows][6];

		       for(int i=0;i<rows;i++)
		       {
		    	   for(int j=0;j<=5;j++)
		    	   {
		    		   root.ds[i][j]=ds[i][j];
		    	   }
		       }
		       
		       root.rows=rows;
		       root.colno=fcol;
		       return root;
		       
		}
		
		////////////////
		
		public double calculateParentEntropy(double[][] ds,int rows,int col)
		{
			List<String> l=new ArrayList<String>();
			//System.out.print("\nrows="+rows);
			for(int i=0;i<rows;i++)
			{
				//System.out.print("\nclass="+ds[i][col]);
					l.add(""+ds[i][col]);
	
			}
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			  // count the occurrences of each value
			  for (String sequence : l)
			  {
			    if (!map.containsKey(sequence)) 
			    {
			      map.put(sequence, 0);
			    }
			    map.put(sequence, map.get(sequence) + 1);
			  }
			 
			  // calculate the entropy
			  Double result = 0.0;
			  for (String sequence : map.keySet()) 
			  {
				  double freq=(double)map.get(sequence);
				 // double logval=Math.log(freq/l.size())/Math.log(2);
				  double logval=logOfBase(2.0,(freq/l.size()));
				  result+= (freq/l.size())*logval;
				 // System.out.print("\n logval="+logval);
			    //Double frequency = (double) map.get(sequence) / l.size();
			    //result += frequency * Math.log(2)*frequency;
				//System.out.print("\n sequence="+sequence +" frequency="+map.get(sequence) +"  l.size="+l.size());
			  }
			  result=Math.abs(result);
			  return result;
			
		}
		///////////////
		public static double logOfBase(double base, double num) {
		    return Math.log(num) / Math.log(base);
		}

		
		////////////////
		public double calculateGainOfCol(double[][] ds,int rows,int col,double parententropy)
		{
			double result=0.0;
			
			double colds[][]=new double[rows][2];
			int rc=0;
			double gain=0.0;
			for(int i=0;i<rows;i++)
			{
					colds[i][0]=ds[i][col];
					colds[i][1]=ds[i][5];
					
			}
			
			for(int i=0;i<=rows-2;i++)
			{
				for(int j=i+1;j<=rows-1;j++)
				{
					if(colds[i][0]>colds[j][0])
					{
						double t=colds[i][0];
						colds[i][0]=colds[j][0];
						colds[j][0]=t;
						
						 t=colds[i][1];
						colds[i][1]=colds[j][1];
						colds[j][1]=t;
						
					}
				}
				
			}
		
			//System.out.print("\n-------------");
			
			double childds[][]=new double[rows][1];
			int crows=0;
			gain= parententropy;
			int i;
			for( i=0;i<rows;i++)
			{
			   //System.out.print("\n"+colds[i][0]+"  "+colds[i][1]);
				if(colds[i][0]<=0.5)
				{
				   childds[crows++][0]=colds[i][1];  //assiging classes to childds
				   continue;
				}
				else
				{
					gain=gain-(((double)crows/(double)rows)*calculateParentEntropy(childds, crows, 0));

					break;
				}
			}
			//System.out.print("\n-------------------");
			crows=0;
			while(i<rows)
			{
				if(colds[i][0]>0.5)
				{
					 //System.out.print("\n"+colds[i][0]+"  "+colds[i][1]);
				   childds[crows++][0]=colds[i][1];  //assiging classes to childds
				   
				}

				i++;
			}
			if(crows>0)
			 gain=gain-(((double)crows/(double)rows)*calculateParentEntropy(childds, crows, 0));
			
			
				
			
			
			result=Math.abs(gain);
			
			return result;
		}
		
		/////////////////////////
		
		
		public double calculateEntropyOfCol(double[][] ds,int rows,int col,double parententropy)
		{
			List<String> l=new ArrayList<String>();
			double colds[][]=new double[rows][2];
			int rc=0;
			double result=0,gain=0.0;
			for(int i=0;i<rows;i++)
			{
					l.add(""+ds[i][col]);
					colds[i][0]=ds[i][col];
					colds[i][1]=ds[i][5];
					
			}
			
			for(int i=0;i<=rows-2;i++)
			{
				for(int j=i+1;j<=rows-1;j++)
				{
					if(colds[i][0]>colds[j][0])
					{
						double t=colds[i][0];
						colds[i][0]=colds[j][0];
						colds[j][0]=t;
						
						 t=colds[i][1];
						colds[i][1]=colds[j][1];
						colds[j][1]=t;
						
					}
				}
				
			}
			/*System.out.print("\nafter sorting...");
			for(int i=0;i<rows;i++)
			{
			   System.out.print("\n"+colds[i][0]+"  "+colds[i][1]);
			}
			System.out.print("\n-------------");*/
			
			double childds[][]=new double[rows][1];
			int crows=0;
			gain= parententropy;
			for(int i=0;i<rows;i++)
			{
			   //System.out.print("\n"+colds[i][0]+"  "+colds[i][1]);
				childds[crows++][0]=colds[i][1];
				if(i+1<rows)
				{
				if(colds[i][0]!=colds[i+1][0])
				{
				//	System.out.print("\nchild Entropy="+calculateParentEntropy(childds, crows, 0));
					//System.out.print("\n crows="+crows+" rows="+rows);
					gain=gain-(((double)crows/(double)rows)*calculateParentEntropy(childds, crows, 0));
					//System.out.print("\ngain1="+gain);
					crows=0;
				}
				}
				else
				{
					//System.out.print("\nchild Entropy="+calculateParentEntropy(childds, crows, 0));
					//System.out.print("\n crows="+crows+" rows="+rows);
					gain=gain-(((double)crows/(double)rows)*calculateParentEntropy(childds, crows, 0));
					//System.out.print("\ngain2="+gain);
					crows=0;
				}
				//crows++;
				
			}
			
					 result=Math.abs(gain);
			  return result;
			
		}
	
		
		
	////////////
		public int[] read3RandCols()
		{
			Random r=new Random();
			int count=0;
			int cols[]=new int[3];
			ArrayList al=new ArrayList();
			while(count<3)
			{
				int n=r.nextInt(5);
				if(!al.contains(n))
				{
					al.add(n);
					cols[count]=n;
					//System.out.print("\n col="+cols[count]);
					count++;
				}
			}
			return cols;
		}
 ///////////////
		 /*public Double calculateShannonEntropy(List<String> values) 
		{
		  Map<String, Integer> map = new HashMap<String, Integer>();
		  // count the occurrences of each value
		  for (String sequence : values)
		 {
		    if (!map.containsKey(sequence)) 
		    {
		      map.put(sequence, 0);
		    }
		    map.put(sequence, map.get(sequence) + 1);
		  }
		 
		  // calculate the entropy
		  Double result = 0.0;
		  for (String sequence : map.keySet()) 
		  {
		   Double frequency = (double) map.get(sequence) / values.size();
		    result += frequency * Math.log(2)*frequency;
			 // System.out.print("\n sequence="+sequence +" frequency="+map.get(sequence) +"  values.size="+values.size());
		  }
		 
		  return result;
		}
 ////////////////////
		
		public double findRootFromCols(int cols[],double ds[][])
		{
			double sum[]=new double[]{0.0,0.0,0.0};
			System.out.print("\n ds length="+ds.length);
			for(int i=0;i<cols.length;i++)
			{
				List<String> l=new ArrayList<String>();
				
				for(int j=1;j<=rows;j++)
				{
					//Double d=ds[j][cols[i]];
					l.add(""+ds[j][cols[i]]);
				}
				//System.out.print("\n l size="+l.size());
				sum[i]=calculateShannonEntropy(l);
				//System.out.print("\nEval="+sum[i]);
						
			}
			double min=0.0;
			if(sum[0]<sum[1] && sum[0]<sum[2])
				min=sum[0];
			else if(sum[1]<sum[0] && sum[1]<sum[2])
				min=sum[1];
			else if(sum[2]<sum[0] && sum[2]<sum[1])
				min=sum[2];
				
			return min;
		}
		
		
		public boolean isExist(double d[],int value)
		{
			boolean flag=false;
			
			for(int i=0;i<d.length;i++)
			{
				
			}
			
			return flag;
		} */
		
	}


