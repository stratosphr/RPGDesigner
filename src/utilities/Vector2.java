package utilities;

/**
 * Created by stratosphr on 22/07/2018.
 */
public final class Vector2 {

    private final Integer first;
    private final Integer second;

    public Vector2(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + getFirst() + ", " + getSecond() + ")";
    }

}
