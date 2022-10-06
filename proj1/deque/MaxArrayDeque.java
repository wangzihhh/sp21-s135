package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        int maxElemDex = 0;
        for (int i = 0; i < size(); i += 1) {
            if (cmp.compare(get(i), get(maxElemDex)) > 0) {
                maxElemDex = i;
            }
        }
        return get(maxElemDex);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxElemDex = 0;
        for (int i = 0; i < size(); i += 1) {
            if (c.compare(get(i), get(maxElemDex)) > 0) {
                maxElemDex = i;
            }
        }
        return get(maxElemDex);
    }


}
