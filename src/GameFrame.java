import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
public class GameFrame extends JFrame{
	//이미지 로딩 하여 아이콘 만들기
	private ImageIcon normalIcon=new ImageIcon("normal.gif");
	private ImageIcon pressedIcon=new ImageIcon("pressed.gif");
	private ImageIcon overIcon=new ImageIcon("over.gif");
	private ImageIcon stopIcon=new ImageIcon("stop.jpg");
	private JMenuItem startItem=new JMenuItem("start");
	private JMenuItem stopItem=new JMenuItem("stop");
	private JMenuItem exitItem=new JMenuItem("Exit");
	private JMenuItem diff1=new JMenuItem("1");
	private JMenuItem diff2=new JMenuItem("2");
	private JMenuItem diff3=new JMenuItem("3");
	private JButton startBtn=new JButton(normalIcon);
	private JButton stopBtn=new JButton(stopIcon);
	private EditPanel editPanel=new EditPanel();
	private ScorePanel scorePanel=new ScorePanel(editPanel);
	private GamePanel gamePanel=new GamePanel(scorePanel,editPanel);
	public GameFrame() {
		setTitle("타이핑 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		splitPane();
		makeMenu();
		makeToolBar();
		setResizable(false);
		setVisible(true);
		File bgm=new File("bgm.wav");
		try {
            
            AudioInputStream stream = AudioSystem.getAudioInputStream(bgm);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception e) {
            
            e.printStackTrace();
        }
		
	}

	private void splitPane() {
		JSplitPane hPane=new JSplitPane();
		getContentPane().add(hPane,BorderLayout.CENTER);
		hPane.setOrientation(javax.swing.JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(600);
		hPane.setEnabled(false);
		
		JSplitPane pPane=new JSplitPane();
		pPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(300);
		pPane.setEnabled(false);
		pPane.setTopComponent(scorePanel);
		pPane.setBottomComponent(editPanel);
		hPane.setRightComponent(pPane);
		hPane.setLeftComponent(gamePanel);
	}
	private void makeMenu() {
		JMenuBar mBar=new JMenuBar();
		setJMenuBar(mBar);
		JMenu filemenu = new JMenu("Game");
		JMenu diffmenu=new JMenu("난이도");
		diffmenu.add(diff1);
		diffmenu.addSeparator();
		diffmenu.add(diff2);
		diffmenu.addSeparator();
		diffmenu.add(diff3);
		filemenu.add(startItem);
		filemenu.add(stopItem);
		filemenu.addSeparator();
		filemenu.add(exitItem);
		mBar.add(filemenu);
		mBar.add(diffmenu);
		startItem.addActionListener(new StartAction());
		stopItem.addActionListener(new StopAction());
		exitItem.addActionListener(new ExitAction());
		diff1.addActionListener(new diffOne());
		diff2.addActionListener(new diffTwo());
		diff3.addActionListener(new diffThree());
	}
	private void makeToolBar() {
		JToolBar tBar= new JToolBar();
		tBar.add(startBtn);
		stopBtn.setSize(100,100);
		tBar.add(stopBtn);
		getContentPane().add(tBar,BorderLayout.NORTH);
		startBtn.addActionListener(new StartAction());
		startBtn.setRolloverIcon(overIcon);
		startBtn.setPressedIcon(pressedIcon);
		stopBtn.addActionListener(new StopAction());
	}
	private class StartAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			gamePanel.startGame();
			startBtn.setEnabled(false);
		}
	}
	private class ExitAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(1);
		}
	}
	private class StopAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			gamePanel.stopGame();
			startBtn.setEnabled(true);
		}
	}
	private class diffOne implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			editPanel.different.setText("난이도 1");
			for(int i=0;i<10;i++) {
				gamePanel.fallingThread.thread[i].one();
			}
		}
	}
	private class diffTwo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			editPanel.different.setText("난이도 2");
			for(int i=0;i<10;i++) {
				gamePanel.fallingThread.thread[i].two();
			}
		}
	}
	private class diffThree implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			editPanel.different.setText("난이도 3");
			for(int i=0;i<10;i++) {
				gamePanel.fallingThread.thread[i].three();
			}
	}
}
}
