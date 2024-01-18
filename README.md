**Exploring Different Maze Search Algorithms**
**Overview**

This project implements different search methods (Breadth First Search, Depth First Search, and A-Star) in a maze using a priority queue in the form of a heap data structure. The goal is to find the shortest path from a specified source to a specified goal in a maze with varying obstacle densities.

**Algorithms Implemented**

Breadth First Search (BFS)
BFS starts at the tree root and explores all nodes at the present depth prior to moving on to the nodes at the next depth level. It ensures an exhaustive exploration of the maze.

Depth First Search (DFS)
DFS starts at the root node and explores as far as possible along each branch before backtracking. This algorithm delves deeply into paths before exploring alternative options.

A-Star (A*)

A-Star algorithm finds the shortest path from a specified source to a specified goal. It combines the benefits of both BFS and DFS, utilizing a heuristic to estimate the cost from the current state to the goal.

**Maze Representation**

The maze is represented as a grid, and the density of obstacles affects the probability of reaching the target. As obstacle density increases, the likelihood of finding a path to the target decreases.

**Usage**

1. Choose a search method (BFS, DFS, A*) in the “AbstractMazeSearch.java” file  to find the path in the maze.
2. Compile and run the “AbstractMazeSearch.java” to run the program.
3. Observe the algorithm's exploration and pathfinding process.

**How to Run**

Open the terminal(example: VS Code terminal)

Enter “javac AbstractMazeSearch.java.java” and hit enter

Enter: “java AbstractMazeSearch.java” and hit enter

**Applications of this project**

1. Pathfinding in Robotics:
The maze search algorithms can be applied in robotics for path planning. Robots can use these algorithms to navigate through complex environments, avoiding obstacles and reaching their destination efficiently.

2. Navigation Systems:
In GPS and navigation systems, these algorithms can be used to find optimal routes considering real-world road networks, traffic conditions, or any other obstacles.

3. Game Development:
Maze search algorithms are commonly used in game development for character navigation. They can be applied to create intelligent NPCs (Non-Playable Characters) that can navigate through game environments.

4. Network Routing:
These algorithms can be adapted for network routing protocols, helping to find the most efficient paths for data packets to traverse a network.

5. Optimization in Manufacturing:
In manufacturing processes, where automated systems need to navigate through assembly lines or warehouses, maze search algorithms can optimize the movement of materials or products.

6. Resource Allocation:
The algorithms can be utilized for resource allocation in scenarios such as project management, where tasks need to be scheduled and assigned efficiently.

7. Emergency Evacuation Planning:
In emergency situations, like building evacuations, the project's algorithms can assist in planning optimal evacuation routes to ensure the safety of individuals.

8. AI and Autonomous Vehicles:
These algorithms play a crucial role in the development of AI systems and autonomous vehicles. They help in decision-making and pathfinding for vehicles to navigate through dynamic environments.
9. Urban Planning:
In urban planning, the algorithms can contribute to traffic management, determining efficient routes for public transportation, and optimizing urban infrastructure.
10. Supply Chain Management:
For supply chain logistics, maze search algorithms can be applied to optimize the movement of goods through warehouses and distribution centers.


