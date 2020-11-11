package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private Node root;

    private class Node {
        private Point point;
        private boolean isVertical;
        private Node less;
        private Node more;

        private Node(Point p, Boolean b, Node l, Node r) {
            point = p;
            isVertical = b;
            less = l;
            more = r;
        }
    }

    public KDTree(List<Point> points) {
        for (Point e : points) {
            insert(e, root);
        }
    }

    private void insert(Point e, Node r) {
        if (root == null) {
            root = new Node(e, true, null, null);
            return;
        } else if (e.equals(r.point)) {
            return;
        } else if (r.isVertical) {
            if (e.getX() >= r.point.getX()) {
                if (r.more == null) {
                    r.more = new Node(e, false, null, null);
                } else {
                    insert(e, r.more);
                }

            } else {
                if (r.less == null) {
                    r.less = new Node(e, false, null, null);
                } else {
                    insert(e, r.less);
                }

            }
        } else {
            if (e.getY() >= r.point.getY()) {
                if (r.more == null) {
                    r.more = new Node(e, true, null, null);
                } else {
                    insert(e, r.more);
                }

            } else {
                if (r.less == null) {
                    r.less = new Node(e, true, null, null);
                } else {
                    insert(e, r.less);
                }

            }
        }
    }


    @Override
    public Point nearest(double x, double y) {
        if (root == null) {
            return null;
        }
        Point current = new Point(x, y);
        return nearest(root, current, root).point;

    }

    private Node nearest(Node p, Point point, Node best) {
        if (p == null) {
            return best;
        }
        if ((point.distance(point, p.point) < best.point.distance(best.point, point))) {
            best = p;
        }
        if (p.isVertical) {
            double xdistance = point.getX() - p.point.getX();
            if (xdistance >= 0) {
                best = nearest(p.more, point, best);
                if ((best.point.distance(point, best.point) >= (xdistance * xdistance))) {
                    best = nearest(p.less, point, best);
                }
            } else {
                best = nearest(p.less, point, best);
                if (best.point.distance(point, best.point) >= (xdistance * xdistance)) {
                    best = nearest(p.more, point, best);
                }
            }
        } else {
            double ydistance = point.getY() - p.point.getY();
            if (ydistance >= 0) {
                best = nearest(p.more, point, best);
                if (best.point.distance(point, best.point) >= (ydistance * ydistance)) {
                    best = nearest(p.less, point, best);
                }
            } else {
                best = nearest(p.less, point, best);
                if (best.point.distance(point, best.point) >= (ydistance * ydistance)) {
                    best = nearest(p.more, point, best);
                }
            }
        }
        return best;

    }

}

