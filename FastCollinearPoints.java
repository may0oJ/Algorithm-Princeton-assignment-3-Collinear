import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private int numberOfSegements;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        nullexception(points);
        Point[] copy = points.clone();
        Arrays.sort(copy);
        for (int j = 0; j < points.length - 3; j++) {
            Point[] copy2 = new Point[points.length];
            for (int k = 0; k < points.length; k++)
                copy2[k] = copy[k];
            Arrays.sort(copy2, copy[j].slopeOrder());
            int count = 0;
            for (int i = 1; i < copy2.length - 1; i++) {
                while (i < copy2.length - 1 && copy2[0].slopeTo((copy2[i])) == copy2[0]
                        .slopeTo(copy2[i + 1])) {
                    i++;
                    count++;
                }
                if (count > 1 && copy2[0].compareTo(copy2[i - count]) < 0) {
                    segments.add(new LineSegment(copy2[0], copy2[i]));
                    this.numberOfSegements++;
                    count = 0;
                }
                else count = 0;
            }
                /*
                if (copy[0].slopeTo(copy[i]) == copy[0].slopeTo(copy[i + 1])
                        && copy[0].slopeTo(copy[i]) == copy[0].slopeTo(copy[i + 2])) {
                    segments.add(new LineSegment(copy[0], copy[i + 2]));
                    this.numberOfSegements++;
                }
                 */

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
        return numberOfSegements;
    }

    // the line segments
    public LineSegment[] segments() {
        int count = 0;
        LineSegment[] result = new LineSegment[numberOfSegements];
        for (LineSegment line : segments)
            result[count++] = line;
        return result;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
