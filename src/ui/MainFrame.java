package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.undo.UndoManager;

import rmi.RemoteHelper;
import runner.ClientRunner;


public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JLabel resultLabel;
	JFrame frame = new JFrame("BF Cilent");
	JTextArea input;
    
	JLabel logad=new JLabel("admin");
	JLabel logpa=new JLabel("password");
	JFrame a=new JFrame("log");
	JTextField admin=new JTextField(10);
	JTextField password=new JTextField(10);
	
	JFrame b=new JFrame("new File");
	JLabel newfilename=new JLabel("filename");
	JTextField filename=new JTextField();
	
	JLabel logad1=new JLabel("admin");
	JLabel logpa1=new JLabel("password");
	JFrame a1=new JFrame("register");
	JTextField admin1=new JTextField(10);
	JTextField password1=new JTextField(10);
	
	JFrame openframe=new JFrame("Open a new frame");
	JTextArea filelist=new JTextArea(20,10);
	JTextField choosefile=new JTextField(20);
	
	JMenuItem undo=new JMenuItem("undo");
	JMenuItem redo=new JMenuItem("redo");
	
	String[] textarea_order=new String[10000];
	int count = 0;
	int[] can=new int[10000];
	JFrame error=new JFrame("error");
	
	JMenuItem version1=new JMenuItem("version1");
	JMenuItem version2=new JMenuItem("version2");
	JMenuItem version3=new JMenuItem("version3");
	JMenu version=new JMenu("version");
	public MainFrame() {
		// 鍒涘缓绐椾綋
		error.setLayout(null);
		error.setVisible(false);
		error.setBounds(500, 300, 200, 100);
		JLabel wrong=new JLabel("can't redo or undo!!");
		wrong.setBounds(20, 20, 150, 20);
		error.add(wrong);
		
		b.setVisible(false);
		b.setBounds(500,300,300,200);
		JButton create=new JButton("create");
		b.setLayout(null);
		b.add(create);
		b.add(filename);
		b.add(newfilename);
		create.setBounds(175, 40, 90, 50);
		filename.setBounds(60, 40, 100, 50);
		newfilename.setBounds(10, 40, 80, 50);
        create.addActionListener(new createActionListener());
		
        JButton button=new JButton("login");
		a.setVisible(false);
		a.setBounds(500,300,300,200);
		logad.setBounds(10,50,80,30);
		logpa.setBounds(10,100,80,30);
		button.setBounds(195, 50, 80, 80);
		admin.setBounds(80, 50 , 100, 30);
		password.setBounds(80, 100, 100, 30);
		button.addActionListener(new action());
		a.setLayout(null);
		a.add(logad);
		a.add(logpa);
		a.add(button);
		a.add(admin);
		a.add(password);
			
		JButton registerbutton=new JButton("register");
		JButton revisebutton=new JButton("revise");
		a1.setVisible(false);
		a1.setBounds(500,300,300,200);
		logad1.setBounds(10,50,80,30);
		logpa1.setBounds(10,100,80,30);
		registerbutton.setBounds(195, 50, 80, 80);
		revisebutton.setBounds(195,10,80,30);
		admin1.setBounds(80, 50 , 100, 30);
		password1.setBounds(80, 100, 100, 30);
		registerbutton.addActionListener(new REaction());
		revisebutton.addActionListener(new RVaction());
		a1.setLayout(null);
		a1.add(logad1);
		a1.add(logpa1);
		a1.add(registerbutton);
		a1.add(revisebutton);
		a1.add(admin1);
		a1.add(password1);
        
		JButton choose=new JButton("choose");
		JLabel name=new JLabel("input name:");
		openframe.setLayout(null);
		openframe.setVisible(false);
		filelist.setLineWrap(true);
		filelist.setWrapStyleWord(true);
	    filelist.setMargin(new Insets(10, 10, 10, 10));
		filelist.setBackground(Color.white);
		filelist.setFont(new Font("黑体",Font.BOLD,20));
		JScrollPane scrollopen=new JScrollPane(filelist);
		scrollopen.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollopen.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		openframe.setBounds(450, 250, 400, 300);
		scrollopen.setBounds(0, 0, 350, 200);
		choosefile.setBounds(80, 200, 200, 50);
		choosefile.setMargin(new Insets(10,10,10,10));
		name.setBounds(10,200,80,50);
		choose.setBounds(290, 200, 80, 50);
		choose.addActionListener(new chooseActionListener());
		openframe.add(name);
		openframe.add(choose);
		openframe.add(scrollopen);
		openframe.add(choosefile);
		
		frame.setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		frame.setJMenuBar(menuBar);
		JMenu run = new JMenu("run");
		JMenuItem execute=new JMenuItem("execute");
		run.add(execute);
		menuBar.add(run);
		JMenu log=new JMenu("log");
		JMenuItem login=new JMenuItem("login");
		JMenuItem logout=new JMenuItem("logout");
		JMenuItem register=new JMenuItem("register");
		
		
		JMenu operate=new JMenu("operate");
		operate.add(undo);
		operate.add(redo);
		menuBar.add(operate);
		
		version.add(version1);
		version.add(version2);
		version.add(version3);
		menuBar.add(version);
		version1.addActionListener(new v1());
		version2.addActionListener(new v2());
		version3.addActionListener(new v3());
		
		log.add(register);
		log.add(login);
		log.add(logout);
		menuBar.add(log);
		
		newMenuItem.addActionListener(new newActionListener());
		openMenuItem.addActionListener(new OpenActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		execute.addActionListener(new ExecuteActionListener());
		login.addActionListener(new loginActionListener());
		logout.addActionListener(new logoutActionListener());
		register.addActionListener(new registerActionListener());
        frame.setLayout(null);
		textArea = new JTextArea(20,10);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setBackground(Color.white);
		textArea.setFont(new Font("黑体",Font.BOLD,25));
		JScrollPane scroll=new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.add(scroll);
		scroll.setBounds(0, 0, 480, 190);
        input=new JTextArea();
        input.setBackground(Color.lightGray);
        input.setMargin(new Insets(10, 10, 10, 10));
        input.setBounds(0, 0, 150, 150);
        input.setFont(new Font("黑体",Font.BOLD,25));
		// 鏄剧ず缁撴灉
        JPanel p=new JPanel();
        p.setBackground(Color.white);
        p.setLayout(null);
		resultLabel = new JLabel();
		resultLabel.setText("Result");
		resultLabel.setBounds(200, 0, 250, 150);
		resultLabel.setFont(new Font("黑体",Font.BOLD,15));
		p.add(input);
		p.add(resultLabel);
		frame.add(p);
        p.setBounds(0, 200, 480, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocation(400, 200);
		
		textArea.addKeyListener(new key());
		undo.addActionListener(new undoAction());
		redo.addActionListener(new redoAction());
		frame.setVisible(true);
	}
	class redoAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(can[count+1]==0){
				error.setVisible(true);
			}
			else{
			count=count+1;
			textArea.setText(textarea_order[count]);
			}
		}
		
	}
	class undoAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(count==0){
				error.setVisible(true);
			}
			else{
			count=count-1;
			textArea.setText(textarea_order[count]);
			}
		}
		
	}
	class key implements KeyListener {
		Boolean key=false;
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub		
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub	
			key=true;
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(key){
				count=count+1;
			    textarea_order[count]=textArea.getText();
			    can[count]=1;
			}
			key=false;
		}
		
	}
    class createActionListener implements ActionListener {
        JLabel tip=new JLabel();
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			tip.setText("");
			try {
				if(!RemoteHelper.getInstance().getIOService().writeFile("", admin.getText(), filename.getText())){
					b.setVisible(false);
				   frame.setTitle(filename.getText()+":"+frame.getTitle());
				   count=0;
				   textarea_order[count]=textArea.getText();
				   can[count]=1;
				}
				else{
					b.add(tip);
					tip.setText("this file is existed");
					tip.setBounds(50, 105, 100, 50);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    }
	class newActionListener implements ActionListener {
		/**
		 * 瀛愯彍鍗曞搷搴斾簨浠�
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			b.setVisible(true);
			textArea.setText("");
			input.setText("");
			resultLabel.setText("");
			frame.setTitle(admin.getText()+":"+"BF Client");
		}
	}
	class OpenActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			openframe.setVisible(true);
			try {
				filelist.setText(RemoteHelper.getInstance().getIOService().readFileList(admin.getText()));
				frame.setTitle(admin.getText()+":"+"BF Client");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			choosefile.setText("");
		}
	}
	class chooseActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				textArea.setText(RemoteHelper.getInstance().getIOService().readfileline(1,admin.getText(), choosefile.getText()));
				openframe.setVisible(false);
				frame.setTitle(choosefile.getText()+":"+frame.getTitle());
				filelist.setText("");
				input.setText("");
				resultLabel.setText("");
				count=0;
				textarea_order[count]=textArea.getText();
				can[count]=1;
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	class ExecuteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String code=textArea.getText();
			String param=input.getText();
			String output="";
			resultLabel.setText("");
			try {
				output="output:"+RemoteHelper.getInstance().getExecuteService().execute(code, param);
				resultLabel.setText(output);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
	class loginActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			a.setVisible(true);
			admin.setText("");
			password.setText("");
		}
		
	}
	class action implements ActionListener{
			JLabel tip=new JLabel();
			@Override
		public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				try {
					if(RemoteHelper.getInstance().getUserService().login(admin.getText(), password.getText())){
					    frame.setTitle(admin.getText()+":BF Client");
					    tip.setText("");
					    a.setVisible(false);
					}
					else{
						a.add(tip);
						tip.setText("admin or password is wrong");
						tip.setBounds(50, 115, 200, 50);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}	
	}
	
	class logoutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.setTitle("BF Client");
			admin.setText("");
			password.setText("");
		}
	}
	class registerActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			a1.setVisible(true);
			password1.setText("");
			admin1.setText("");
		}
	}
	class REaction implements ActionListener{
		JLabel tip=new JLabel();
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			tip.setText("");
			try {
				if(admin1.getText().equals("")||password1.getText().equals("")){
					a1.add(tip);
					tip.setText("add admin or password");
					tip.setBounds(50, 115, 200, 50);
				}
				else{
				    if(!RemoteHelper.getInstance().getIOService().writeFile(password1.getText(), admin1.getText(), "password")){
				         tip.setText("");
				         a1.setVisible(false);
				    }
				    else{
					    a1.add(tip);
					    tip.setText("this admin is existed");
					    tip.setBounds(50, 115, 150, 50);
				    }
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class RVaction implements ActionListener {
		JLabel tip=new JLabel();
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			tip.setText("");
			try {
				if(admin1.getText().equals("")||password1.getText().equals("")){
					a1.add(tip);
					tip.setText("add admin or password");
					tip.setBounds(50, 115, 200, 50);
				}
				else{
				    if(RemoteHelper.getInstance().getIOService().register(password1.getText(), admin1.getText())){
				          tip.setText("");
				          a1.setVisible(false);
				    }
				    else{
					    a1.add(tip);
					    tip.setText("this admin is not existed");
					    tip.setBounds(50, 115, 200, 50);
				    }
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class SaveActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textArea.getText();
			try {
				RemoteHelper.getInstance().getIOService().writefileline(code, admin.getText(), frame.getTitle().split(":")[0]);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
	class v1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				textArea.setText(RemoteHelper.getInstance().getIOService().readfileline(1, admin.getText(), frame.getTitle().split(":")[0]));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class v2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				textArea.setText(RemoteHelper.getInstance().getIOService().readfileline(2, admin.getText(), frame.getTitle().split(":")[0]));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	class v3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				textArea.setText(RemoteHelper.getInstance().getIOService().readfileline(3, admin.getText(), frame.getTitle().split(":")[0]));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
