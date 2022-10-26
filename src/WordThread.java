
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WordThread extends Thread{
	private ScorePanel scorePanel;
	private JLabel text;
	private static int delay=400;
	public WordThread(ScorePanel scorePanel,JLabel text) {
		this.text=text;
		this.scorePanel=scorePanel;
	}
	public void one() {
		delay=400;
	}
	public void two() {
		delay=300;
	}
	public void three() {
		delay=150;
	}
	public void run() {
		Container c=text.getParent();
		int xSize=c.getWidth();
		int x=(int)(Math.random()*(xSize-60-40))+40;
		text.setLocation(x,0);
		text.setForeground(Color.CYAN);
		if(Math.random()<0.1)
			text.setForeground(Color.RED);
		while(true) {
			int y=text.getY();
			text.setLocation(x,y+5);
			if(text.getY()==410) {
				scorePanel.decrease();
				text.setText("");
				return;
			}
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				text.setText("");
				return;
			}
		}
	}
}
