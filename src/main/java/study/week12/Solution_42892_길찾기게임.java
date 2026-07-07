package study.week12;

import java.util.ArrayList;
import java.util.TreeMap;

public class Solution_42892_길찾기게임 {

    static class Node {
        int x; //자식 위치를 결정할 x좌표
        int idx;
        Node left;
        Node right;

        public Node(int x, int idx) {
            this.x = x;
            this.idx = idx;
        }
    } //class - Node

    ArrayList<Integer> list;

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        TreeMap<int[], Integer> map = new TreeMap<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return b[1] - a[1];
        });

        for (int i = 0; i < nodeinfo.length; i++) {
            map.put(nodeinfo[i], i + 1);
        }

        Node parent = null;
        for (int[] key : map.keySet()) {
            int x = key[0];
            int idx = map.get(key);
            Node child = new Node(x, idx);

            if (parent == null) parent = child;
            else insertNode(parent, child);
        } //for - 맵을 순회하며 트리 조합

        list = new ArrayList<>();
        preorder(parent);
        answer[0] = list.stream().mapToInt(i -> i).toArray();

        list = new ArrayList<>();
        postorder(parent);
        answer[1] = list.stream().mapToInt(i -> i).toArray();

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

    private void preorder(Node parent) {
        if (parent == null) return;

        list.add(parent.idx);
        preorder(parent.left);
        preorder(parent.right);
    } //preorder

    private void postorder(Node parent) {
        if (parent == null) return;

        postorder(parent.left);
        postorder(parent.right);
        list.add(parent.idx);
    } //postorder
}
