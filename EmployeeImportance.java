// Time Complexity : O(n) for map searching and O(n) fo rBFS traversal. So overall O(n)
// Space Complexity: map storage O(n) and Queue storage O(n). sO total is O(n)
import java.util.List;
import java.util.*;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;

    Employee(int id, int importance, List<Integer> subordinates) {
        this.id = id;
        this.importance = importance;
        this.subordinates = subordinates;
    }
}
public class EmployeeImportance {
    public int getImportance(List<Employee> employees, int id) {
        // Create a map for quick lookup of employees by ID
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee emp : employees) {
            map.put(emp.id, emp);
        }

        // Initialize BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        int totalImportance = 0;

        while (!queue.isEmpty()) {
        int currId = queue.poll();
        Employee emp = map.get(currId);
        totalImportance += emp.importance;

        // Add subordinates to the queue
        for (int subId : emp.subordinates) {
                queue.add(subId);
            }
        }

        return totalImportance;
    }

    public static void main(String[] args) {
        // Create the list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 5, Arrays.asList(2, 3)));
        employees.add(new Employee(2, 3, Collections.emptyList()));
        employees.add(new Employee(3, 3, Collections.emptyList()));

        // Employee ID to calculate the importance for
        int id = 1;

        // Run the BFS solution
        EmployeeImportance solution = new EmployeeImportance();
        int totalImportance = solution.getImportance(employees, id);

        // Print the result
        System.out.println("Total Importance (BFS): " + totalImportance); // Output: 11
    }
}
