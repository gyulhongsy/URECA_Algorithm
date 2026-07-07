package study.week12;

import java.util.Arrays;

public class Solution_42892_길찾기게임_개선 {

    static class Node {
        int x;
        int y;
        int value;
        Node left;
        Node right;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    } //class - Node

    int[][] answer;
    int idx; //배열에 값을 넣기 위한 인덱스

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        answer = new int[2][n];

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
        } //for - Node 객체 배열 생성

        Arrays.sort(nodes, (a, b) -> {
            if (a.y == b.y) return a.x - b.x;
            return b.y - a.y;
        }); //sort

        Node parent = nodes[0];
        for (int i = 1; i < n; i++) {
            insertNode(parent, nodes[i]);
        } //for - 트리 구성

        idx = 0;
        preorder(parent);

        idx = 0;
        postorder(parent);

        return answer;
    } //Solution

    private void insertNode(Node parent, Node child) {
        if (child.x < parent.x) { //자식의 x좌표가 부모의 x좌표보다 작음
            if (parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        } else { //자식의 x좌표가 부모의 x좌표보다 큼
            if (parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    } //insertNode

    private void preorder(Node node) {
        if (node == null) return;

        answer[0][idx++] = node.value;
        preorder(node.left);
        preorder(node.right);
    } //preorder

    private void postorder(Node node) {
        if (node == null) return;

        postorder(node.left);
        postorder(node.right);
        answer[1][idx++] = node.value;
    } //postorder
}
