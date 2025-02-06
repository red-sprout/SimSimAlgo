import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int val, l, r;

        Node(int val) {
            this.val = val;
            l = -1;
            r = -1;
        }

        @Override
        public String toString() {
            return "[" + val + ", " + l + ", " + r + "]";
        }
    }

    static ArrayList<Node> list;
    static Node root;

    static void setNode(Node n1, Node n2) {
        if(n2.val < n1.val) {
            if(n1.l == -1) {
                n1.l = list.size();
                list.add(n2);
            } else {
                setNode(list.get(n1.l), n2);
            }
        } else {
            if(n1.r == -1) {
                n1.r = list.size();
                list.add(n2);
            } else {
                setNode(list.get(n1.r), n2);
            }
        }
    }

    static void find(int idx, StringBuilder sb) {
        if(idx == -1) return;
        find(list.get(idx).l, sb);
        find(list.get(idx).r, sb);
        sb.append(list.get(idx).val).append('\n');
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        find(0, sb);
        System.out.print(sb);
    }

    public static void main(String[] args) {
        list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
            while(true) {
                int val = Integer.parseInt(br.readLine());
                Node node = new Node(val);
                if(root == null) {
                    root = node;
                    list.add(root);
                } else {
                    setNode(root, node);
                }
            }
        } catch(Exception e) {
            print();
        }
    }
}