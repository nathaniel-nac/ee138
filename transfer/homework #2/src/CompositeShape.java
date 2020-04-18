import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class CompositeShape implements Shape{
    
    ArrayList<Shape> shapes;
    Rectangle2D rect2D;
    Rectangle rect;
    
    public CompositeShape() {
        shapes = new ArrayList<Shape>();
        rect2D = new Rectangle();
        rect = new Rectangle();
        
    }
    
    @Override
    public boolean contains(Point2D point) {
        // TODO Auto-generated method stub
        int bool = 0;
        for(int i=0; i<shapes.size(); i++) {
            if(shapes.get(i).contains(point))
                bool=1;
        }
        if(bool == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean contains(Rectangle2D rect) {
        // TODO Auto-generated method stub
        int bool = 0;
        for(int i=0; i<shapes.size(); i++) {
            if(shapes.get(i).contains(rect))
                bool=1;
        }
        if(bool == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean contains(double pt1, double pt2) {
        // TODO Auto-generated method stub
        int bool = 0;
        for(int i=0; i<shapes.size(); i++) {
            if(shapes.get(i).contains(pt1, pt2))
                bool = 1;
        }
        if(bool == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean contains(double pt1, double pt2, double pt3, double pt4) {
        // TODO Auto-generated method stub
        int bool = 0;
        for(int i=0; i<shapes.size(); i++) {
            if(shapes.get(i).contains(pt1, pt2, pt3, pt4))
                bool = 1;
        }
        if(bool == 1)
            return true;
        else
            return false;
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        for(int i=0; i<shapes.size(); i++) {
            rect.union(shapes.get(i).getBounds());
        }
        return rect;
    }

    @Override
    public Rectangle2D getBounds2D() {
        // TODO Auto-generated method stub
        for(int i=0; i<shapes.size(); i++) {
            rect2D.union(shapes.get(i).getBounds2D(), shapes.get(i).getBounds2D(), rect2D);
        }
        return rect2D;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        // TODO Auto-generated method stub
        return new PathIterator() {
            private PathIterator currentPathIterator;
            private Iterator<Shape> shapesIterator;
            
            {
                shapesIterator = shapes.iterator();
                nextShape();
            }
            
            private void nextShape() {
                if(shapesIterator.hasNext())
                    currentPathIterator = shapesIterator.next().getPathIterator(at);
                else
                    currentPathIterator = null;
            }

            @Override
            public int currentSegment(float[] coords) {
                // TODO Auto-generated method stub
                return currentPathIterator.currentSegment(coords);
            }

            @Override
            public int currentSegment(double[] coords) {
                // TODO Auto-generated method stub
                return currentPathIterator.currentSegment(coords);
            }

            @Override
            public int getWindingRule() {
                // TODO Auto-generated method stub
                return WIND_NON_ZERO;
            }

            @Override
            public boolean isDone() {
                // TODO Auto-generated method stub
                if(currentPathIterator == null)
                    return true;
                if(!currentPathIterator.isDone())
                    return false;
                nextShape();
                return isDone();
            }

            @Override
            public void next() {
                // TODO Auto-generated method stub
                currentPathIterator.next();
            }
        };
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        // TODO Auto-generated method stub
        return getPathIterator(at);
    }

    @Override
    public boolean intersects(Rectangle2D rect) {
        // TODO Auto-generated method stub
        int bool = 0;
        for(int i=0; i<shapes.size(); i++) {
            if(shapes.get(i).intersects(rect))
                bool = 1;
        }
        if(bool == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean intersects(double pt1, double pt2, double pt3, double pt4) {
        // TODO Auto-generated method stub
        int bool = 0;
        for(int i=0; i<shapes.size(); i++) {
            if(shapes.get(i).intersects(pt1, pt2, pt3, pt4))
                bool = 1;
        }
        if(bool == 1)
            return true;
        else
            return false;
    }
    
    public void add(Shape shape) {
        shapes.add(shape);
    }

}
