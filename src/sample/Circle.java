package sample;

import javafx.geometry.Point2D;

public class Circle
{
    private Point2D center;
    private double  radius;
    private double initialRadius;

    public Circle(double x, double y, double r)
    {
        setCenter(x,y);
        setRadius(r);
        this.initialRadius = r;
    }
    
    public void setCenter(double x, double y)
    {
        center = new Point2D(x,y);
    }
    
    public void setRadius(double r)
    {  
        radius = r;  
    }

    public void setInitialRadius(){
        this.radius = this.initialRadius;
    }
    
    public double getX()
    {
        return center.getX();
    }
    
    public double getY()
    {
        return center.getY();
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public boolean containsPoint(double x, double y)
    {
        return (center.distance(x,y) < radius);
    }
}