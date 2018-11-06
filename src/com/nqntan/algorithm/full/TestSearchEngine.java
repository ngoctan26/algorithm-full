package com.nqntan.fullproject.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSearchEngine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfEntries = scanner.nextInt();
        int numberOfQueries = scanner.nextInt();
        Trie trie = new Trie();
        int tmpCount = 0;
        while (tmpCount < numberOfEntries) {
            String line = scanner.next();
            int weight  = scanner.nextInt();
            trie.addWord(line, weight);
            tmpCount++;
        }
        tmpCount = 0;
        List<String> queries = new ArrayList<>();
        while(tmpCount < numberOfQueries) {
            String line = scanner.next();
            queries.add(line);
            tmpCount++;
        }
        for (int i = 0; i < queries.size(); i++) {
            String query = queries.get(i);
            trie.getMaxChild(query);
        }
    }

    static class Node {
        private static final int MAX = 26;
        public Node[] children;
        public int countWord;
        public int weight;

        public Node() {
            countWord = 0;
            children = new Node[MAX];
        }
    }

    static class Trie {
        public static final int MAX = 26;
        private Node root;

        public Trie() {
            root = new Node();
        }

        public void addWord(String s, int weight) {
            int ch;
            Node temp = root;
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i) - 'a';
                if (temp.children[ch] == null) {
                    Node x = new Node();
                    temp.children[ch] = x;
                }
                temp = temp.children[ch];
            }
            temp.weight = weight;
            temp.countWord++;
        }

        public boolean findWord(String s) {
            int ch;
            Node temp = root;
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i) - 'a';
                if (temp.children[ch] == null) {
                    return false;
                }
                temp = temp.children[ch];
            }
            return temp.countWord > 0;
        }

        public Node findNode(String s) {
            int ch;
            Node temp = root;
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i) - 'a';
                if (temp.children[ch] == null) {
                    return null;
                }
                temp = temp.children[ch];
            }
            return temp;
        }

        public void getMaxChild(String s) {
            Node foundNode = findNode(s);
            if (foundNode == null) {
                System.out.println(-1);
                return;
            }
            List<Integer> record = new ArrayList<>();
            record.add(0, -1);
            getMaxWeightChildNode(foundNode, record);
            System.out.println(record.get(0));
        }

        private void getMaxWeightChildNode(Node node, List<Integer> recordMaxFound) {
            for (int i = 0; i < node.children.length; i++) {
                Node tmpNode = node.children[i];
                if (tmpNode != null) {
                    int checkedWeigth = recordMaxFound.get(0);
                    int childWeight = tmpNode.weight;
                    if (childWeight > checkedWeigth) {
                        recordMaxFound.add(0, childWeight);
                    }
                    getMaxWeightChildNode(tmpNode, recordMaxFound);
                }
            }
        }
    }
}
