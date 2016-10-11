import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener
{
	JButton b1,b2,b3;
	JLabel l;
	public MainFrame(String title)
	{
		super(title);
		setLayout(null);
		l=new JLabel("A project on Random Forest");
		l.setBounds(50,50,450,60);
		l.setFont(new Font("Arial",Font.BOLD,25));

		b1=new JButton("Dataset Division");
		b1.setBounds(50,150,200,50);
		
		b2=new JButton("Make 10 Random Datasets");
		b2.setBounds(50,250,200,50);
		
		b3=new JButton("Decision Tree Construction");
		b3.setBounds(50,350,200,50);
		add(l);
		
		add(l);
	
		add(b1);
		add(b2);
		add(b3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		String str=e.getActionCommand();
		
		if(str.equals("Dataset Division"))
		{
			AppWindow w=new AppWindow("Random Forest");
			w.setSize(500,400);
			w.setVisible(true);
		}
		else if(str.equals("Decision Tree Construction"))
		{
			DecisionTree obj=new DecisionTree();
			try
			{
				//pass array of files

				String datafiles[]={"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset1.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset2.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset3.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset4.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset5.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset6.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset7.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset8.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset9.xls",
						"C:\\Users\\Desktop\\projectebola\\Forest1\\rdataset10.xls",
				};
			obj.readExcelData(datafiles);
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null, "Error MainFrame :"+e1);
			}
			
			
		}
		else if(str.equals("Make 10 Random Datasets"))
		{
			DataSet obj=new DataSet();
			obj.makeRandomDataSets("rdataset1.xls");
			obj.makeRandomDataSets("rdataset2.xls");
			obj.makeRandomDataSets("rdataset3.xls");
			obj.makeRandomDataSets("rdataset4.xls");
			obj.makeRandomDataSets("rdataset5.xls");
			obj.makeRandomDataSets("rdataset6.xls");
			obj.makeRandomDataSets("rdataset7.xls");
			obj.makeRandomDataSets("rdataset8.xls");
			obj.makeRandomDataSets("rdataset9.xls");
			obj.makeRandomDataSets("rdataset10.xls"); 
			JOptionPane.showMessageDialog(null, "Random Datasets created.");
			
			
		}
			
		
	}

}
