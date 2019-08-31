package cn.ok;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * cn.ok
 *
 * @author Kyou on 2019/8/31 11:53
 */
@Slf4j
public class SupplierDemo implements Supplier<String> {

    private int delay = 0;

    SupplierDemo(int delay) {
        this.delay = delay;
    }

    @Override
    public String get() {
        log.debug("SupplierDemo go to sleep {}s.", delay);
        try {
            Thread.sleep(1000 * delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("SupplierDemo wakeup.");

        int n = 10 / 0;

        return "Success: " + delay;
    }
}
