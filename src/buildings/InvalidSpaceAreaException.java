package buildings;

public class InvalidSpaceAreaException extends IllegalArgumentException {

    public InvalidSpaceAreaException () {}

    public InvalidSpaceAreaException(String message){

        super (message);
    }
}
