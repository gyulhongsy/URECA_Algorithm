package study.week11;

import java.util.Arrays;
import java.util.Comparator;

public class Solution_17686_파일명정렬 {
    public String[] solution(String[] files) {

        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String f1, String f2) {

                String[] parts1 = f1.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)", 3);
                String[] parts2 = f2.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)", 3);

                String head1 = parts1[0];
                String head2 = parts2[0];

                int headComp = head1.compareToIgnoreCase(head2);

                if (headComp != 0) return headComp;
                else {
                    int num1 = Integer.parseInt(parts1[1]);
                    int num2 = Integer.parseInt(parts2[1]);

                    return num1 - num2;
                }
            }
        });

        return files;
    }
}
