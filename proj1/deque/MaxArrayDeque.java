package deque;

import java.util.Comparator;
import java.util.concurrent.ConcurrentMap;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }



    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        int maxInx = 0;
        for (int i = 0; i < size(); i += 1) {
            if (c.compare(get(i), get(maxInx)) > 0) {
                maxInx = i;
            }
        }
        return get(maxInx);
    }

    public T max() {
        return max(cmp);
    }



}
