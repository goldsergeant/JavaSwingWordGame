import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class TextSource {
	Vector<String> v=new Vector<String>();
	Scanner fscanner=null;
	public TextSource() { //파일에서 읽기
		try {
			fscanner=new Scanner(new FileReader("words.txt"));
			while(fscanner.hasNext()) {
				v.add(fscanner.nextLine().trim());
			}
		} catch (FileNotFoundException e) {
			System.out.println("파일 읽어오기 실패");
		}
	}
	public String get() {
		int index=(int)(Math.random()*v.size());
		return v.get(index);
	}
}
