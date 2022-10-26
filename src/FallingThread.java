import javax.swing.JLabel;

public class FallingThread extends Thread{
	public WordThread thread[]=new WordThread[10];
	private ScorePanel scorePanel;
	private TextSource textSource;
	private JLabel text[];
	private GamePanel gamePanel;
	public FallingThread(GamePanel gamePanel,WordThread thread[],ScorePanel scorePanel,TextSource textSource,JLabel text[]) {
		this.thread=thread;
		this.scorePanel=scorePanel;
		this.textSource=textSource;
		this.text=text;
		this.gamePanel=gamePanel;
	}
	public void run() {
		int i=0;
		while(true) {
			if(i==10)i=0;
			text[i].setText(textSource.get());
			thread[i]=null;
			thread[i]=new WordThread(scorePanel,text[i]);
			thread[i].start();
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				for(int a=0;a<10;a++) {
					thread[a].interrupt();
				}
				return;
			}
			i++;
			if(scorePanel.life==0) {
				for(int a=0;a<10;a++) {
					thread[a].interrupt();
				}
				break;
			}
		}
	}
	public void wordThreadStop(int i) {
		thread[i].interrupt();
	}
}
