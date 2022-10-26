import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	private int score=0;
	int life=5;
	private JLabel textLabel=new JLabel("점수");
	private JLabel scoreLabel=new JLabel(Integer.toString(score));
	private JLabel lifeTextLabel=new JLabel("생명");
	private JLabel lifeLabel=new JLabel(Integer.toString(life));
	private HashMap<String,Integer>map=new HashMap<String,Integer>();
	private EditPanel editPanel=null;
	public ScorePanel(EditPanel editPanel) {
		this.editPanel=editPanel;
		setBackground(Color.CYAN);
		setLayout(null);
		textLabel.setSize(50, 20);
		textLabel.setLocation(10,10);
		add(textLabel);
		lifeTextLabel.setSize(50,20);
		lifeTextLabel.setLocation(60,10);
		add(lifeTextLabel);
		scoreLabel.setSize(100,20);
		scoreLabel.setLocation(40,10);
		lifeLabel.setSize(100,20);
		lifeLabel.setLocation(100,10);
		add(scoreLabel);
		add(lifeLabel);
		Scanner fscanner=null;
		try {
			fscanner=new Scanner(new FileReader("score.txt"));
			while(fscanner.hasNext()) {
				String a=fscanner.nextLine();
				a=a.trim();
				String str[]=a.split(":");
				map.put(str[0],Integer.parseInt(str[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2)
			{
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		int i=0;
		for(Entry<String, Integer> entry : list_entries) {
			if(i==10)break;
			JLabel a=new JLabel((i+1)+"위 "+entry.getKey()+": "+entry.getValue() );
			a.setSize(100,40);
			a.setLocation(10,40+i*10);
			add(a);
			if(i<3)
				a.setForeground(Color.MAGENTA);
			i++;
		}
		
	}
	public void increase() {
		score+=10;
		scoreLabel.setText(Integer.toString(score));
	}
	public void decrease() {
		score-=10;
		scoreLabel.setText(Integer.toString(score));
		life--;
		lifeLabel.setText(Integer.toString(life));
		if(life==0) {
			editPanel.saveButton.setEnabled(true);
			JLabel save=new JLabel("save 버튼을 눌러 저장해주세요.");
			editPanel.add(save);
		}
	}
	public int getScore() {
		return score;
	}
	public void setString(int life) {
		lifeLabel.setText(Integer.toString(life));
	}
}
