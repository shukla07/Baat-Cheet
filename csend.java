import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class csend extends JFrame implements ActionListener,Runnable
{
	JTextField t1;
	JButton b1;
	JTextArea ta;
	Container c;

	Socket s;
	OutputStreamWriter os;
	BufferedWriter bw;
	PrintWriter send;
	BufferedReader br;

	csend()
	{
		super("Sender");
		c=getContentPane();
		c.setLayout(new FlowLayout());

		Thread th=new Thread(this);
		th.start();

		t1=new JTextField(10);
		ta=new JTextArea(10,10);
		b1=new JButton("Send");

		try{
			s=new Socket("127.0.0.1",8090);
			os=new OutputStreamWriter(s.getOutputStream());
			bw=new BufferedWriter(os);
			send=new PrintWriter(bw,true);
		}catch(Exception ee)
			{
				System.out.println(ee.getMessage());
			}

		c.add(t1);
		c.add(ta);
		c.add(b1);
		b1.addActionListener(this);
	}

public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			String s1=t1.getText();
			ta.append("Me:"+s1+"\n");
			try{
				send.println(s1);
			}catch(Exception ee)
				{
					System.out.println(ee.getMessage());
				}
		}


	}

public void run()
	{
		while(true)
		{
			
			try{
				br=new BufferedReader(new InputStreamReader(s.getInputStream()));
				String s1=br.readLine();
				ta.append("You:"+s1+"\n");
			}catch(IOException ee)
				{
					System.out.println(ee.getMessage());
				}
		}
	}	

public static void main(String ab[])
	{
		csend a1=new csend();
		a1.setSize(100,100);
		a1.setVisible(true);
	}
*}