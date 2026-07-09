package study.week13;

public class Solution_68936_쿼드압축후개수세기 {
    
    int[][] area;
    int[] answer = new int[2];
    
    public int[] solution(int[][] arr) {
        area = arr;
        
        divide(0, 0, arr.length);
        
        return answer;
    }
    
    private void divide(int x, int y, int size) {
        int firstValue = area[x][y];
        boolean isSame = true;
        
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (area[i][j] != firstValue) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) break;
        } //for - 영역 돌기
        
        if (isSame) {
            if (firstValue == 0) answer[0]++;
            else answer[1]++;
            return;
        } //if - 압축
        
        int nextSize = size/2;
        divide(x, y, nextSize);
        divide(x+nextSize, y, nextSize);
        divide(x, y+nextSize, nextSize);
        divide(x+nextSize, y+nextSize, nextSize);
    } //divide
}
