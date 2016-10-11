import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AppWindow extends JFrame implements ActionListener
{
	JTextField t1;
	JButton b1,b2;
	public String filename="";
	JLabel l;
	public AppWindow(String title)
	{
		super(title);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		l=new JLabel("Select DataSet File:");
		t1=new JTextField(20);
		b1=new JButton("Browse..");
		b2=new JButton("Divide Dataset");
		add(l);
		add(t1);
		add(b1);
		add(b2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
	}

	
	public void actionPerformed(ActionEvent e) 
	{
		String str=e.getActionCommand();
		
		if(str.equals("Browse.."))
		{
			FileDialog f=new FileDialog(this,"Open",FileDialog.LOAD);
			f.setVisible(true);
			filename=f.getDirectory()+ "/" + f.getFile();
			t1.setText(filename);
		}
		else
		{
			DataSet obj=new DataSet();
			obj.divideDataSet(t1.getText());
			
		}
		
	}

}
