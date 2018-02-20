package tspSolution1;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;

public class SolverClass {
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Node> nodesTemp = new ArrayList<Node>();

	double distance = Double.POSITIVE_INFINITY;

	public SolverClass(ArrayList<Node> nodes) {
		this.nodes = nodes;
		
	}

	ArrayList<Node> nodesResult = new ArrayList<Node>();

	public double getDistance(Node nodeOne, Node nodeTwo) {
		return Point2D.distance(nodeOne.getxCoord(), nodeOne.getyCoord(), nodeTwo.getxCoord(), nodeTwo.getyCoord());
	}

	public void Solver() {
		saveValuesToArray(nodes);
		long StartTime = System.nanoTime();
		Node currentNode = nodes.get(0);
		while (nodes.size() > 0) {
			nodes.remove(currentNode);
			Node closestNode = null;
			nodesResult.add(currentNode);
			for (Node possibleNode : nodes) {
				if (getDistance(currentNode, possibleNode) < distance || closestNode == null) {
					closestNode = possibleNode;
					distance = getDistance(currentNode, possibleNode);
				}
			}
			currentNode = closestNode;
		}
		nodesResult.add(nodesResult.get(0));

		long EndTime = System.nanoTime();
		long RunTime = (EndTime - StartTime) / 1000000;

		System.out.println(RunTime);

		Optimize();
	}

	private void Optimize() {
		long StartTime = System.nanoTime();
		try {
			double distanceA = totalDistance(nodesResult);
			boolean Changed = true;
			while (Changed) {
				Changed = false;
				System.out.println(distanceA);
				for (int i = 1; i < nodesResult.size() - 1; i++) {
					for (int j = i + 1; j < nodesResult.size() - 1; j++) {
						if (nodesResult.get(i) != nodesResult.get(j)) {

							Collections.swap(nodesResult, i, j);
							double distanceTemp = totalDistance(nodesResult);
							if (distanceTemp < distanceA) {

								distanceA = distanceTemp;
								Changed = true;
							} else {
								Collections.swap(nodesResult, i, j);
							}
						}
					}

				}
			}
			
			
			nodesTemp.add(nodesTemp.get(0));
			
			if(nodesTemp.containsAll(nodesResult))
			{
				System.out.println("yes.");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}

		long EndTime = System.nanoTime();
		long RunTime = (EndTime - StartTime) / 1000000;

		System.out.println(RunTime);
		// System.exit(1);

	}

	private static double totalDistance(ArrayList<Node> nodesTemp) {
		double distance = 0;
		for (int i = 1; i < nodesTemp.size(); i++) {
			distance += Point2D.distance(nodesTemp.get(i).getxCoord(), nodesTemp.get(i).getyCoord(),
					nodesTemp.get(i - 1).getxCoord(), nodesTemp.get(i - 1).getyCoord());
		}
		return distance;
	}

	
	private void saveValuesToArray(ArrayList<Node> temp)
	{
		for(Node tempNode : temp)
		{
			nodesTemp.add(tempNode);
		}
	}
	
	private void getAllFromArrays(ArrayList<Node> temp) {
		int ii = 0;
		for (int i = 0; i < temp.size(); i++) {
			System.out
					.println(temp.get(i).getCityName() + " " + temp.get(i).getyCoord() + " " + temp.get(i).getyCoord());
			ii++;
		}
		System.out.println(ii);
	}

}
