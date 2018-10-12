import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class crecv extends JFrame implements ActionListener
{
	JTextField t1;
	JButton b1, b2;
	JTextArea ta;
	Container c;

	Socket s;
	ServerSocket ss;
	OutputStreamWriter os;
	BufferedWriter bw;
	PrintWriter send;
	BufferedReader br;

	crecv()
	{
		super("Reciever");
		c=getContentPane();
		c.setLayout(new FlowLayout());

	

		t1=new JTextField(10);
		ta=new JTextArea(10,10);
		b1=new JButton("Send");
		b2=new JButton("Reciever");


		try{
			ss=new ServerSocket(8090);
			s=ss.accept();
		}catch(Exception ee)
			{
				System.out.println(ee.getMessage());
			}

		try{
			os=new OutputStreamWriter(s.getOutputStream());
			bw=new BufferedWriter(os);
			send=new PrintWriter(os);
			send=new PrintWriter(bw,true);
		}catch(Exception ee)
			{
				System.out.println(ee.getMessage());
			}

		c.add(t1);
		c.add(ta);
		c.add(b1);
		c.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
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
		crecv a1=new crecv();
		a1.setSize(100,100);
		a1.setVisible(true);
	}
}