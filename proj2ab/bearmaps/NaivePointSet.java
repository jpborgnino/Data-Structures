package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> list) {
        points = list;
    }

    @Override
    public Point nearest(double x, double y) {
        double closestdistance = -1;
        Point closestP = null;
        Point location = new Point(x, y);
        for (Point e : points) {
            double distance = Point.distance(e, location);
            if (distance < closestdistance || closestdistance == -1.0) {
                closestP = e;
                closestdistance = distance;
            }


        }

        return closestP;
    }

}
