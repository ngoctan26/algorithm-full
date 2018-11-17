package com.nqntan.algorithm.full;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class TestRoadConstruction {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.id = 1;
        employee.importance = 5;
        employee.subordinates = Arrays.asList(2, 3);

        Employee employee1 = new Employee();
        employee.id = 2;
        employee.importance = 3;
        employee.subordinates = Collections.emptyList();

        Employee employee2 = new Employee();
        employee.id = 3;
        employee.importance = 3;
        employee.subordinates = Collections.emptyList();




        Scanner scanner = new Scanner(System.in);
        int numberOfTCs = scanner.nextInt();
        int tmpCount = 1;
        while (tmpCount <= numberOfTCs) {
            handleTC(tmpCount, scanner);
            tmpCount++;
        }
    }

    private static void handleTC(int tcNumber, Scanner scanner) {
        int numberOfRoad = scanner.nextInt();
        List<List<Node>> graph = new ArrayList<>();
        int tmpCount = 0;
        while (tmpCount < numberOfRoad) {
            graph.add(new ArrayList<>());
            tmpCount++;
        }
        String line;
        int index = 0;
        Map<String, Integer> cityToIndex = new HashMap<>();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String[] rawLineArrays = line.split("%s");
            String startCity = rawLineArrays[0];
            String endCity = rawLineArrays[1];
            int cost = Integer.valueOf(rawLineArrays[2]);
            int startCityIndex = getCityIndex(startCity, cityToIndex, index);
            int endCityIndex = getCityIndex(endCity, cityToIndex, index);
            graph.get(startCityIndex).add(new Node(endCityIndex, cost));
            graph.get(endCityIndex).add(new Node(startCityIndex, cost));
        }
        prim(0, graph);
    }

    private static int getCityIndex(String name, Map<String, Integer> cityIndexCache, int currentIndex) {
        return cityIndexCache.computeIfAbsent(name, k -> currentIndex + 1);
    }

    private static void prim(int src, List<List<Node>> graph) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int n = graph.size();
        int[] dist = new int[n];
        int[] path = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        queue.add(new Node(src, 0));
        dist[src] = 0;
        while (!queue.isEmpty()) {
            Node top = queue.poll();
            int u = top.id;
            visited[u] = true;
            for (int i = 0; i < graph.get(u).size(); i++) {
                Node neighbor = graph.get(u).get(i);
                int v = neighbor.id;
                int w = neighbor.dist;
                if (!visited[v] && w < dist[v]) {
                    dist[v] = w;
                    queue.add(new Node(v, w));
                    path[v] = u;
                }
            }
        }
        int mst = getMST(dist, path);
    }

    private static int getMST(int[] dist, int[] path) {
        int n = dist.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (path[i] == -1) {
                continue;
            }
            ans += dist[i];
        }
        return ans;
    }

    private static class Node implements Comparable<Node> {
        public int id;
        public Integer dist;

        public Node(int id, Integer dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist.compareTo(o.dist);
        }
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        Map<Integer, Integer> countMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer input : A) {
            Integer count = countMap.get(input);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            if (count > 1) {
                result.add(0, input);
            }
            countMap.put(input, count);
        }
        int tmpCount = 1;
        while (countMap.get(tmpCount) != null) {
            tmpCount++;
        }
        result.add(1, tmpCount);
        return result;
    }

    //[[1,5,[2,3]],[2,3,[4]],[3,4,[]],[4,1,[]]]
    //[[1,5,[2,3]],[2,3,[]],[3,3,[]]]

    public static int getImportance(List<Employee> employees, int id) {
        Employee checkingEmployee = null;
        Map<Integer, Employee> employeeImportantCache = new HashMap<>();
        for (Employee employee : employees) {
            if (employee.id == id) {
                checkingEmployee = employee;
            }
            employeeImportantCache.put(employee.id, employee);
        }
        Set<Integer> checkedIDs = new HashSet<>();
        List<Integer> checkingSubordinates = checkingEmployee.subordinates;
        checkedIDs.addAll(checkingSubordinates);
        int result = checkingEmployee.importance;
        result += getImportanceOfChild(checkedIDs, checkingSubordinates, employeeImportantCache);
        return result;
    }

    private static int getImportanceOfChild(Set<Integer> records, List<Integer> checkingIDs, Map<Integer, Employee> employeeImportantCache) {
        int result = 0;
        for (Integer checkingID : checkingIDs) {
            Employee employee = employeeImportantCache.get(checkingID);
            List<Integer> childEmployees = employee.subordinates;
            if (!records.contains(checkingID)) {
                result += employee.importance;
                records.add(checkingID);
            }
            if (!childEmployees.isEmpty()) {
                result += getImportanceOfChild(records, childEmployees, employeeImportantCache);
            }
        }
        return result;
    }

    private static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}
