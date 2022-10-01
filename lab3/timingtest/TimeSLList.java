package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        int M = 10000;
        int N = 1000;
        AList<Integer> firstCol = new AList<>();
        AList<Double> secondCol = new AList<>();
        AList<Integer> thirdCol = new AList<>();
        SLList<Integer> testList = new SLList<>();
        while (N <= 128000) {
            firstCol.addLast(N);
            thirdCol.addLast(M);
            for (int i =0; i < N; i += 1) {
                testList.addLast(i);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < M; j +=1) {
                testList.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            secondCol.addLast(timeInSeconds);
            N *= 2;
        }
        printTimingTable(firstCol, secondCol, thirdCol);
    }
}
