
import java.io.*;
import java.util.Random;

import javax.swing.JOptionPane;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
public class DataSet
{
	public void divideDataSet(String srcFile)
	{
		
		try
		{
			 	  File inputWorkbook = new File(srcFile);
			      Workbook w1;
			    
			      w1 = Workbook.getWorkbook(inputWorkbook);
			      // Get the first sheet
			      Sheet sheet1 = w1.getSheet(0);
			      int r=0;
			      try
			      {
			      
			      while(true)
			      {
			    	 Cell cell = sheet1.getCell(0,r);
			         
			         r++;

			       }
			      }
			      catch(Exception e)
			      {
			    	 // System.out.print("\nError :"+e);
			      }
			      System.out.print("\n total row="+r);
			      w1.close();
			      
			      int rand_rows[]=new int[r];
			      Random rnd=new Random();
			      
			     
			      
			      for(int i=0;i<r;i++)
			      {
			    	  int val=rnd.nextInt(r);
			    	  if(isduplicate(rand_rows,i,val))
			    	  {
			    		  i--;
			    		  continue;
			    	  }
			    	  if(val==0)
			    		  val=28;
			    	  rand_rows[i]=val;
			     }
			      
			     
			      
			      
			  
			      
			      
			      /*for(int i=0;i<r;i++)
			      {
			    	  System.out.print("\n "+rand_rows[i]);
			      }*/
			        	      
			      
				    
				  w1 = Workbook.getWorkbook(inputWorkbook);
				      // Get the first sheet
				  sheet1 = w1.getSheet(0);
			
				  
				  WritableWorkbook w2 = Workbook.createWorkbook(new File("C:\\Users\\dataset2.xls"));
				WritableSheet sheet2 = w2.createSheet("Sheet1", 0);
				  int fileper1=(int)(r*0.8);
				  
				      for(int i=1;i<fileper1;i++)
				      {
				    	  
				    	  for(int j=0;j<38;j++)
				    	  {
				    		  Cell cell = sheet1.getCell(j,rand_rows[i-1]);
				    		  String data=cell.getContents();
				    		  if(j==37 && data.length()>0 && !data.equalsIgnoreCase("Ebola"))
				    		  {
				    		  int n=Integer.parseInt(data);
				    		  if(n>=1)
				    			  n=1;
				    		  data=""+n;
				    			  
				    		  }
				    		   Label label = new Label(j, i, ""+data);
					  		  sheet2.addCell(label);    		  
				    		
				    	  }

				       }
			
				      w2.write();
				      w2.close();
			
			
				      WritableWorkbook w3 = Workbook.createWorkbook(new File("C:\\Users\\dataset3.xls"));
						WritableSheet sheet3 = w3.createSheet("Sheet1", 0);
						  int rr=1;
						 
						      for(int i=fileper1;i<r-1;i++)
						      {
						    	  for(int j=0;j<38;j++)
						    	  {
						    		  Cell cell = sheet1.getCell(j,rand_rows[i-1]);
						    		  String data=cell.getContents();
						    		  if(j==37 && data.length()>0 && !data.equalsIgnoreCase("Ebola"))
						    		  {
						    		  int n=Integer.parseInt(data);
						    		  if(n>=1)
						    			  n=1;
						    		  data=""+n;
						    			  
						    		  }	  
						    		  if(data.length()==0)
						    			  data="0";
						    		  Label label = new Label(j, rr, ""+data);
							  		  sheet3.addCell(label);
					
						    	  }
						    	  rr++;

						       }
					
						w3.write();
						w3.close();
				      w1.close();
				      
			
			JOptionPane.showMessageDialog(null, "division completed with Ebola processing");					
		}
		catch(Exception e)
		{
			System.out.print("\nError1 :"+e);
		}
	}
	
	
	public boolean isduplicate(int x[],int len,int val)
	{
		boolean found=false;
		for(int i=0;i<len;i++)
		{
			if(x[i]==val)
			{
				found=true;
				break;
			}
		}
		return found;
		
	}
	
	public void makeRandomDataSets(String dstFile)
	{
		
		try
		{
			 	  File inputWorkbook = new File("C:\\Users\\dataset2.xls");
			      Workbook w1;
			    
			      w1 = Workbook.getWorkbook(inputWorkbook);
			      // Get the first sheet
			      Sheet sheet1 = w1.getSheet(0);
			      int r=0;
			      try
			      {
			      
			      while(true)
			      {
			    	 Cell cell = sheet1.getCell(0,r);
			         
			         r++;

			       }
			      }
			      catch(Exception e)
			      {
			    	 // System.out.print("\nError :"+e);
			      }
			      System.out.print("\n total row="+r);
			      w1.close();
			      
			      int rand_rows[]=new int[r];
			      Random rnd=new Random();
			      
			     
			      
			      for(int i=1;i<r;i++)
			      {
			    	  int val=rnd.nextInt(r);
			    	  if(isduplicate(rand_rows,i,val))
			    	  {
			    		  i--;
			    		  continue;
			    	  }
			    	  
			    	  rand_rows[i]=val;
			     }
			   
			      /*for(int i=0;i<r;i++)
			      {
			    	  System.out.print("\n "+rand_rows[i]);
			      }*/
			        	      
			      
				    
				  w1 = Workbook.getWorkbook(inputWorkbook);
				      // Get the first sheet
				  sheet1 = w1.getSheet(0);
			
				  
				  WritableWorkbook w2 = Workbook.createWorkbook(new File("C:\\Users\\"+dstFile));
				  WritableSheet sheet2 = w2.createSheet("Sheet1", 0);
 
				      for(int i=1;i<r;i++) //for(int i=1;i<5300;i++)
				      {
				    	
				    	  for(int j=0;j<38;j++)
				    	  {
				    		  Cell cell = sheet1.getCell(j,rand_rows[i-1]);
				    		  String data=cell.getContents();
				    		  if(j==37 && data.length()>0 && !data.equalsIgnoreCase("Ebola"))
				    		  {
				    		  int n=Integer.parseInt(data);
				    		  if(n>=1)
				    			  n=1;
				    		  data=""+n;
				    			  
				    		  }
				    		  if(data.length()==0)
				    			  data="0";
				    		  Label label = new Label(j, i, ""+data);
					  		  sheet2.addCell(label);    		  
					  	//	System.out.print("  "+data);
					  		
				    	  }
				    	  System.out.print("\n"+i);
				       }
				      w2.write();
				      w2.close();
				      w1.close();
				      
			System.out.print("\n Completed.... ");
			//JOptionPane.showMessageDialog(null, "completed random datasets");					
				 
		}
		catch(Exception e)
		{
			System.out.print("\nError2 :"+e);
		}
	}
	
	
}
