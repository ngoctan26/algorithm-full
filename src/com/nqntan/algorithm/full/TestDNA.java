package com.nqntan.algorithm.full;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://vjudge.net/problem/LightOJ-1224
 */
public class TestDNA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numbersOfInput = scanner.nextInt();
        int tmpCount = 0;
        Trie trie = new Trie();
        while (numbersOfInput < tmpCount) {
            trie.addWord(scanner.next(), -1);
            numbersOfInput++;
        }
        System.out.println(trie.findMaxPassNode());
    }

    static class Node {
        private static final int MAX = 26;
        public Node[] children;
        public int countWord;
        public int weight;
        public int countPass; // Number of words contain from root to current node

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
            int countPass = 0;
            Node temp = root;
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i) - 'a';
                if (temp.children[ch] == null) {
                    Node x = new Node();
                    temp.children[ch] = x;
                }
                temp = temp.children[ch];
                if (temp.countWord > 0) {
                    countPass++;
                }
            }
            temp.weight = weight;
            temp.countPass = countPass;
            temp.countWord++;
        }

        public int findMaxPassNode() {
            List<Integer> resultRecord = new ArrayList<>();
            resultRecord.add(0, 0);
            resultRecord.add(0, 0);
            findMaxPassNode(root, resultRecord);
            return resultRecord.get(1);
        }

        public void findMaxPassNode(Node node, List<Integer> recordResult) {
            Integer numberOfChar = recordResult.get(0);
            Integer maxResult = recordResult.get(0);
            for (int i = 0; i < node.children.length; i++) {
                Node tmpNode = node.children[i];
                if (tmpNode != null) {
                    recordResult.add(0, numberOfChar++);
                    int result = tmpNode.countPass * numberOfChar;
                    if (result > maxResult) {
                        recordResult.add(1, result);
                    }
                }
            }
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
