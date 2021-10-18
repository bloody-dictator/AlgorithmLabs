
import java.util.ArrayList;
import java.util.List;

public class OutLevenshtein {
    public static void main(String[] args)  {
//        ScannerWord scannerWord = new ScannerWord();
//        List<String> scanlist = scannerWord.scanWord(2);
        String file = "src/main/resources/Levenshtein.xlsx";
        String sheetname = "levenshtein";
        List<LevenshteinDto> list = new ArrayList<>();
        String word1 = "кит";
        String word2 = "скот";
        Levenshtein.wrapperGetDistanceWithMemo(5, word1, word2, file, list);
        Levenshtein.wrapperGetDistanceRecursive(5, word1, word2,file, list);
        Levenshtein.wrapperGetDistance(5, word1, word2, file, list);
        Levenshtein.writeInExcelAlgs(file, sheetname, list);
    }
}