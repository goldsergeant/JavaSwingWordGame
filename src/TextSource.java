import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class TextSource {
	Vector<String> v=new Vector<String>();
	Scanner fscanner=null;
	public TextSource() { //���Ͽ��� �б�
		try {
			fscanner=new Scanner(new FileReader("words.txt"));
			while(fscanner.hasNext()) {
				v.add(fscanner.nextLine().trim());
			}
		} catch (FileNotFoundException e) {
			System.out.println("���� �о���� ����");
		}
	}
	public String get() {
		int index=(int)(Math.random()*v.size());
		return v.get(index);
	}
}
