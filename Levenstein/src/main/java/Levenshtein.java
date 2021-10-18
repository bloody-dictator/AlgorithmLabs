import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

public class Levenshtein {
    public static long preLastTime;

    public static int getDistance(String word1, String word2) {
        int cost;
        int n = word1.length();
        int m = word2.length();
        if (n == 0 && m == 0) {
            return 0;
        } else if (n == 0 && m > 0) {
            return m;
        } else if (m == 0 && n > 0) {
            return n;
        } else {
            int[][] matrixD = new int[n+1][m+1];
            for (int i = 0; i <= n; i++) {
                matrixD[i][0] = i;
            }
            PrinterWord.printMatrix(matrixD);
            System.out.println();
            for (int j = 0; j <= m; j++) {
                matrixD[0][j] = j;
            }
            PrinterWord.printMatrix(matrixD);
            System.out.println();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    cost = costOfSubstitution(word1.charAt(i - 1), word2.charAt(j - 1));
                    matrixD[i][j] = min(matrixD[i][j - 1] + 1, matrixD[i - 1][j] + 1, matrixD[i - 1][j - 1] + cost);
                }
            }
            return matrixD[n][m];
        }
    }

    public static void wrapperGetDistance(int count, String word1, String word2, String file, List<LevenshteinDto> list){
        List<Long> times = new ArrayList<>();
        int distance = 0;
        for (int i = 0; i < count; i++) {
            ThreadMXBean tm = ManagementFactory.getThreadMXBean();
            distance = Levenshtein.getDistance(word1, word2);
            long time = tm.getCurrentThreadUserTime();
            long procTime = time - preLastTime;
            preLastTime = time;
            times.add(procTime);
            var lev = new LevenshteinDto(NameAlg.ITERATIVE.getTitle(), procTime, word1, word2, distance);
            list.add(lev);
        }
        long middleTime = (long)times.stream().mapToDouble(d->d).average().orElse(0.0);
        System.out.println(middleTime);
        list.add(new LevenshteinDto(NameAlg.ITERATIVE.getTitle(), middleTime, word1, word2, distance));
    }

    public static int getDistanceRecursive(String word1, String word2) {
        if(word1.isEmpty() && word2.isEmpty()){
            return 0;
        } else if (word1.isEmpty()) {
            return word2.length();
        } else if (word2.isEmpty()) {
            return word1.length();
        }
        int substitution = getDistanceRecursive(word1.substring(1), word2.substring(1))
                + costOfSubstitution(word1.charAt(0), word2.charAt(0));
        int insertion = getDistanceRecursive(word1, word2.substring(1)) + 1;
        int deletion = getDistanceRecursive(word1.substring(1), word2) + 1;

        return min(substitution, insertion, deletion);
    }

    public static void wrapperGetDistanceRecursive(int count, String word1, String word2, String file, List<LevenshteinDto> list){
        List<Long> times = new ArrayList<>();
        int distance = 0;
        for (int i = 0; i < count; i++) {
            ThreadMXBean tm = ManagementFactory.getThreadMXBean();
            distance = Levenshtein.getDistanceRecursive(word1, word2);
            long time = tm.getCurrentThreadUserTime();
            long procTime = time - preLastTime;
            preLastTime = time;
            times.add(procTime);
            var lev = new LevenshteinDto(NameAlg.RECURSIVE.getTitle(), procTime, word1, word2, distance);
            list.add(lev);
        }
        long middleTime = (long)times.stream().mapToDouble(d->d).average().orElse(0.0);
        System.out.println(middleTime);
        list.add(new LevenshteinDto(NameAlg.RECURSIVE.getTitle(), middleTime, word1, word2, distance));
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int getDistanceWithMemo(String word1, String word2, int n, int m, int[][] cash) {
        if(word1.isEmpty() && word2.isEmpty()){
            return 0;
        }
        if (m == 0) {
            return n;
        }
        if (n == 0) {
            return m;
        }
        if (cash[n - 1][m - 1] != -1) {
           // PrinterWord.printMatrix(cash);
          //  System.out.println();
            return cash[n - 1][m - 1];
        }
        if (word1.charAt(n - 1) == word2.charAt(m - 1)) {
            cash[n - 1][m - 1] = getDistanceWithMemo(word1, word2, n - 1, m - 1, cash);
            return cash[n - 1][m - 1];
        }
        cash[n - 1][m - 1] = 1 + min(getDistanceWithMemo(word1, word2, n, m - 1, cash),
                getDistanceWithMemo(word1, word2, n - 1, m, cash),
                getDistanceWithMemo(word1, word2, n - 1, m - 1, cash));

        return cash[n - 1][m - 1];
    }

    public static  void wrapperGetDistanceWithMemo(int count, String word1, String word2, String file, List<LevenshteinDto> list){
        List<Long> times = new ArrayList<>();
        int distance = 0;
        for (int i = 0; i<count; i++) {
            int[][] cash = GeneratorMatrix.initCash(word1.length(), word2.length());
            ThreadMXBean tm = ManagementFactory.getThreadMXBean();
            distance = Levenshtein.getDistanceWithMemo(word1, word2, word1.length(), word2.length(), cash);
            long time = tm.getCurrentThreadUserTime();
            long procTime = time - preLastTime;
            preLastTime = time;
            times.add(procTime);
            var lev = new LevenshteinDto(NameAlg.RECURSIVEWITHCASH.getTitle(), procTime, word1, word2, distance);
            list.add(lev);
        }
        long middleTime = (long)times.stream().mapToDouble(d->d).average().orElse(0.0);
        System.out.println(middleTime);
        list.add(new LevenshteinDto(NameAlg.RECURSIVEWITHCASH.getTitle(), middleTime, word1, word2, distance));
    }

    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void writeInExcelAlgs(String file, String sheetName, List<LevenshteinDto> c) {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet(sheetName);
        int rownum = 0;
        for (LevenshteinDto lev : c) {
            Row row = sheet.createRow(rownum++);
            Cell name = row.createCell(0);
            name.setCellValue(lev.getNameAlgs());
            Cell time = row.createCell(1);
            time.setCellValue(lev.getProcTime());
            Cell word1 = row.createCell(2);
            word1.setCellValue(lev.word1);
            Cell word2 = row.createCell(3);
            word2.setCellValue(lev.word2);
            Cell result = row.createCell(4);
            result.setCellValue(lev.result);
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(file));
            book.write(out);
            sheet.autoSizeColumn(1);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}