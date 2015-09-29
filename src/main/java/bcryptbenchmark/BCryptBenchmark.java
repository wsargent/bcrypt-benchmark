package bcryptbenchmark;

import org.mindrot.jbcrypt.BCrypt;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;

/**
 *  Benchmarks how long bcrypt takes on your device.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class BCryptBenchmark {

    int WORK_FACTOR = 13;

    @Benchmark
    public void benchmarkHashpw() {
        String salt = BCrypt.gensalt(WORK_FACTOR);
        BCrypt.hashpw(String.valueOf(System.currentTimeMillis()), salt);
    }
}
