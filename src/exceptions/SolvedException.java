package exceptions;

public class SolvedException extends Exception {

    private final String path;

    public SolvedException(String path) {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return path;
    }
}
