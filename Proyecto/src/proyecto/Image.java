package proyecto;

import java.util.Arrays;

/**
 * Class that represents an image
 * An image contains a name and its rgb average values
 */
public class Image {
    
    protected String name;
    protected double[] values;
    
    public Image(String name, double[] values) {
        this.name = name;
        this.values = values;
    }
    
    @Override
    public String toString(){
        return name+","+Arrays.toString(values);
    }
}
