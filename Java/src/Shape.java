public enum Shape {
    RECTANGLE, CYLINDER;
    public static Shape extrapolateShape(String dimension){
        if(dimension.contains("x")){
            return RECTANGLE;
        }
        return CYLINDER;
    }

    @Override
    public String toString() {
        if(this.equals(Shape.RECTANGLE)){
            return"Rectangle";
        }
        return "Cylinder";
    }
}
