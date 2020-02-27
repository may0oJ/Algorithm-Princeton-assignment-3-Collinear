import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> segements = new ArrayList<LineSegment>();
    private int numberOfSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        nullexception(points);
        Point[] copy = points.clone();
        Arrays.sort(copy);
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    if (copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[k])) {
                        for (int h = k + 1; h < points.length; h++) {
                            if (copy[i].slopeTo(copy[j]) == copy[i].slopeTo(copy[h])) {
                                segements.add(new LineSegment(copy[i], copy[h]));
                                this.numberOfSegments++;
                            }
                        }
                    }
                }
            }
        }
    }

    private void nullexception(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point p : points)
            if (p == null) {
                throw new IllegalArgumentException();
            }
        Point[] points1 = points.clone();
        Arrays.sort(points1);
        for (int i = 1; i < points1.length; i++) {
            if (points1[i].compareTo(points1[i - 1]) == 0)
                throw new IllegalArgumentException();
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return this.numberOfSegments;

    }

    // the line segments
    public LineSegment[] segments() {
        int count = 0;
        LineSegment[] results = new LineSegment[this.numberOfSegments];
        for (LineSegment line : segements)
            results[count++] = line;
        return results;
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
