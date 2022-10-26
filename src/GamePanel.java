import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class GamePanel extends JPanel{
	private JTextField input =new JTextField(40);
	private JLabel text[]=new JLabel[10];
	private ScorePanel scorePanel=null;
	private EditPanel editPanel=null;
	private TextSource textSource = new TextSource();//�ܾ� ���� ����
	private ImageIcon img=new ImageIcon("house.jpg");
	public WordThread thread[]=new WordThread[10];
	public FallingThread fallingThread;
	public GamePanel(ScorePanel scorePanel,EditPanel editPanel) {
		this.scorePanel=scorePanel;
		this.editPanel=editPanel;
		fallingThread=new FallingThread(this,thread, scorePanel, textSource,text);
		setLayout(new BorderLayout());
		add(new GameGroundPanel(),BorderLayout.CENTER);
		add(new InputPanel(),BorderLayout.SOUTH);
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t=(JTextField)(e.getSource());
				String inWord=t.getText();
				for(int i=0;i<10;i++) {
					if(inWord.equals(""))return;
				if(text[i].getText().equals(inWord)) { //���߱� ����
					//���� �ø���
					if(text[i].getForeground()==Color.RED) {
						scorePanel.life++;
						scorePanel.setString(scorePanel.life);
						try {
							AudioInputStream ais=AudioSystem.getAudioInputStream(new File("lifeup.wav"));
							Clip clip=AudioSystem.getClip();
							clip.stop();
							clip.open(ais);
							clip.start();
							}
							catch(Exception e1) {
								System.out.println("lifeup error");
							}
					}
					else {
						try {
							AudioInputStream ais=AudioSystem.getAudioInputStream(new File("effect.wav"));
							Clip clip=AudioSystem.getClip();
							clip.stop();
							clip.open(ais);
							clip.start();
							}
							catch(Exception e1) {
								System.out.println("effect.wav error");
							}
					}
					scorePanel.increase();
					text[i].setText("");
					fallingThread.wordThreadStop(i);
					//input â �����
					t.setText("");
				}
				else {
					t.setText("");
				}
				}
			}
		});
		editPanel.addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=editPanel.edit.getText();
				int i;
				for(i=0;i<textSource.v.size();i++) {
					if(str.equals(textSource.v.get(i))) {
						editPanel.edit.setText("");
						break;
					}
				}
				if(i==textSource.v.size()) {
					textSource.v.add(str);
					editPanel.edit.setText("");
					try {
						File file=new File("words.txt");
						FileWriter fw=new FileWriter(file,true);
						fw.write(str+"\r\n");
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("���� �̾�� ����");
					}
				}
				}
		});
			editPanel.saveButton.addActionListener(new ActionListener() {
				FileWriter fwrite;
				File file=new File("score.txt");
				public void actionPerformed(ActionEvent e) {
					String w=Integer.toString(scorePanel.getScore());
					String name=editPanel.edit.getText();
					try {
						fwrite=new FileWriter(file,true);
						fwrite.write(name+":"+w+"\r\n");
						editPanel.edit.setText("");
						fwrite.close();
						System.exit(1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						System.out.println("score.txt �̾�� ����");
					}
				}
			});
	}
	
	public void stopGame() {
		fallingThread.interrupt(); // ������ ���� ����
	}
	public void startGame() {
		fallingThread=null;
		fallingThread=new FallingThread(this,thread, scorePanel, textSource,text);
		fallingThread.start();
		}
	 class GameGroundPanel extends JPanel{
		 JLabel text1=null;
		 public GameGroundPanel() {
			 setLayout(null);
			 text1=new JLabel("Ÿ���� ���ּ���");
			
			 text1.setLocation(0,0);
			 text1.setSize(120,20);
			 add(text1); 
			 for(int i=0;i<10;i++) {
				 text[i]=new JLabel();
				 text[i].setLocation(0,0);
				 text[i].setSize(120,20);
				 add(text[i]);
			 }
			 
			 }
		 public void paintComponent(Graphics g) {
			 Image image=img.getImage();
			 g.drawImage(image,0,0,getWidth(),getHeight(),null);
		 }
	 }
	 class InputPanel extends JPanel{
	 public InputPanel() {
		 setLayout(new FlowLayout());
		 setBackground(Color.YELLOW);
		 add(input);
	 }
	 }
	 }
	 

