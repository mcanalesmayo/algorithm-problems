import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToVertex {
	
	private static class Point {
		// Making everything public for the sake of simplicity
		public int[] coords;
		public double dist;
		
		public Point(int[] coords, double dist) {
			this.coords = coords;
			this.dist = dist;
		}
	}
	
	private static class MaxHeapComparator implements Comparator<Point> {

		@Override
		public int compare(Point a, Point b) {
			return b.dist - a.dist > 0.0d ? 1 : -1;
		}
	}
	
	private static double distance(int[] point, int[] vertex) {
		int xDist = point[0] - vertex[0];
		int yDist = point[1] - vertex[1];
		
		return Math.sqrt(xDist*xDist + yDist*yDist);
	}
	
	public static void main(String[] args) {
		int[][] points = {{-2, 1}, {1, 2}, {3, -1}, {2, 1}, {2, 3}, {0, 0}};
		int[] vertex = {-2, 3};
		int k = 3;
		
		MaxHeapComparator maxHeapComp = new MaxHeapComparator();		
		PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k, maxHeapComp); // Space O(K)
		
		for (int i = 0; i < points.length; i++) { // Time O(N)
			Point currPoint = new Point(points[i], distance(points[i], vertex));

			if (maxHeap.size() == k) {
				if (maxHeapComp.compare(maxHeap.peek(), currPoint) < 0) { // O(1)
					maxHeap.remove(); // O(logK)
					maxHeap.add(currPoint); // Time O(logK)
				}
			} else {
				maxHeap.add(currPoint); // Time O(logK)
			}
		}
		
		int[][] res = new int[k][2];
		
		for (int i = 0; i < res.length; i++) { // O(K)
			res[i] = maxHeap.remove().coords;
			System.out.printf("{%d, %d}\n", res[i][0], res[i][1]);
		}
		
		// Time complexity: O(N*logK)
		// Space complexity: O(K)
	}
}
