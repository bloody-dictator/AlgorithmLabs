import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerWord {
    public List<String> scanWord(int count){
        List<String> resultList = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i<count; i++){
            System.out.println("Введите слово №" + (i+1) +": ");
            resultList.add(scanner.nextLine());
        }
        return resultList;
    }
}