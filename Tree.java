//import java.awt.List;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class Tree 
{
	Node root;
	int countnodes=0;
	public Tree()
	{
		root=null;
	}
	void insertNode(Node newnode)
	{
		Node ptr=null;
		newnode.left=null;
		newnode.right=null;
		if(root==null)
		{
			root=newnode;
			countnodes++;
			
		}
		else
		{
			ptr=root;
			while(true)
			{
				if(newnode.gain<ptr.gain)
				{
					if(ptr.left==null)
					{
						ptr.left=newnode;
						countnodes++;
						break;
					}
					else
					 ptr=ptr.left;
				}
				else if( newnode.gain>=ptr.gain)
				{
					if(ptr.right==null)
					{
						ptr.right=newnode;
						countnodes++;
						break;
					}
					else
						ptr=ptr.right;
				}
				else
				{
					break;
				}
							
			}
		}
	}
	////////////
		
	//////////
	
	
	public void printTree()
	{
		Node queue[]=new Node[1000];
		int f=1,r=1;
		Node ptr=root;
		if(root==null)
		{
			System.out.print("\nTree is empty");
			return;
		}
		queue[r]=root;
		int nodes=0;
		//System.out.print("\ncountnodes="+countnodes);
		
		while(true)
		{
			
			/*if(f==r+1)
				break;
			
			ptr=queue[f];
			if(ptr!=null)
			{
				queue[++r]=ptr.left;
				queue[++r]=ptr.right;
			}
			f++; */
			if(f==999 || r==999)
				break;
			
			ptr=queue[f++];
			if(ptr!=null)
			{
				nodes++;
				queue[++r]=ptr.left;
				queue[++r]=ptr.right;
				if(nodes==countnodes)
					break;
				
			}
			else if(ptr==null)
			{
				queue[++r]=null;
				queue[++r]=null;
			}
					
			
			
		}
		
		while(queue[--r]!=null)
		{
			
		}
		
		int nextlevel=1,k=1;;
		int space=10;
		System.out.print("\nTree values...\n\n" );
		//printspaces(space*45);
		for(int i=1;i<=r;i++)
		{
			
			ptr=queue[i];
			if(ptr!=null)
			{
			//System.out.print("    Gain ="+ptr.gain+"  feature="+ptr.feature +" colno="+ptr.colno+" class="+max(ptr.clas_count));
				System.out.print("    G :"+ptr.gain+" {c1:"+ptr.clas_count[1]+",c2:"+ptr.clas_count[2]+",c3:"+ptr.clas_count[3]+",c4:"+ptr.clas_count[4]+"}"); 
				//System.out.print("    G :"+ptr.gain+" {"+ptr.clas_count[1]+","+ptr.clas_count[2]+","+ptr.clas_count[3]+","+ptr.clas_count[4]+" }" );
				
			}
			else
			{
				//printspaces(18);
				//System.out.print("nochild");
			  //printspaces(18);
			}
			
			if(k==nextlevel)
			{
				System.out.println();
				System.out.println();
				System.out.println();
				
				nextlevel*=2;
				k=1;
			//	space--;
				//printspaces(space*45);
			}
			else
			{
				k++;
				
			}
				
		}
			
	}
	///////////////
	
	public void printspaces(int n)
	{
		for(int i=1;i<=n;i++)
		{
			System.out.print(" ");
		}
		
	}

//////////////////////////
	
	
	
	public void searchFeatures(Tree tobj[],String testfile)
	{
		
		int i=1,r=0;
		double rows[][]=new double[500][5];
		try 
		{

		  Workbook workbook = Workbook.getWorkbook(new File(testfile));
          Sheet sheet = workbook.getSheet(0);
		  
		  
          while(i<=1000)
		  {
        	  
        	  //System.out.print("i="+i);
            for(int j=0;j<=4;j++)
            {
               Cell cell1 = sheet.getCell(j,i);  
               rows[i-1][j]=Double.parseDouble(cell1.getContents());
            }
           i++;
		  }
          
		}catch(Exception e)
          {
          }
		
         r=i;
         
         for(int l=0;l<10;l++)
 		{
        	 System.out.print("\n\nTree...."+l+"  predictions...\n");
 			for(int j=0;j<r;j++)
 			{
 				double row[][]=new double[1][5];
 				 for(int k=0;k<=4;k++)
 	            {
 					 //System.out.print("k="+k);
 	                row[0][k]= rows[j][k];  
 	            }
 				 //System.out.print("ok");
          	int clssno=findClass(row,tobj[l]);
        	System.out.print("\n "+clssno);
 			}
 		}
            
    //        int classs[]=new int[10];
            
            
            
            //////////
            ///////////
            
            
            
        /*    int class_count[]=new int[]{0,0,0,0,0}; 
            for(int j=0;j<10;j++)
            {
            	classs[j]=findClass(row,tobj[j]);
            	System.out.print(" "+classs[j]);
            	class_count[classs[j]]++;
            	           
            }
            
            int big=class_count[1];
            int classno=1;
            for(int k=2;k<5;k++)
            {
            	if(class_count[k]>big)
            	{
            		big=class_count[k];
            		classno=k;
            		
            	}
            		
            }
            /////////
            /////////////
            
            System.out.println("\nPredicted class ="+classno+"\n");*/
       
		

	}
	
	public int findClass(double row[][],Tree t)
	{
		int classno=0;
		int nodes=0;
		try
		{
			Node ptr=null;
			if(t.root==null)
			{
				System.out.print("\n Tree is empty");
				return classno;
			}
		
			ptr=t.root;
			int col=ptr.colno;
			while(ptr!=null)
			{
			nodes++;
				if(row[0][col]>=ptr.gain)
				{
				
					if(ptr.right==null)
					{
					//	System.out.print("\nClass= " + max(ptr.clas_count));
						classno=max(ptr.clas_count);
						break;
					}
					else
					{
						ptr=ptr.right;
						col=ptr.colno;
					}
				
				
				}
				else if(row[0][col]<ptr.gain)
				{
				
					if(ptr.left==null)
					{	
					
						//System.out.print("\nClass="+max(ptr.clas_count));
						classno=max(ptr.clas_count);
						break;
					}
					else
					{
						ptr=ptr.left;
						col=ptr.colno;
					}
				}
			
			}
		}catch(Exception e)
		{
			System.out.print("\nError4 :"+e);
		}
		//System.out.print("\n nodes visited="+nodes+"\n");
		return classno;
		
	}
	
	public int max(int class_count[])
	{
		int big=class_count[0];
		int colno=0;
		for(int i=1;i<=4;i++)
		{
			if(class_count[i]>big)
			{
				big=class_count[i];
				colno=i;
			}
		}
		return colno;
	}
	
	
	
	
 }
