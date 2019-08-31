package cn.ok;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * cn.ok
 *
 * @author Kyou on 2019/8/31 11:19
 */
@Slf4j
public class OrdinaryDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 构建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        int cnt = 1;

        // 多线程任务的数组
        CompletableFuture[] futures = new CompletableFuture[cnt];

        // 启动若干异步线程
        for (int i = 0; i < cnt; i++) {
            // 指定执行线程池，启动异步子线程，指定子线程异常处理函数。
            futures[i] = CompletableFuture.supplyAsync(new SupplierDemo(i + 1), executor).exceptionally(ExceptionHandler::handleException);
        }

        log.debug("异步任务启动完成, 等待子线程执行中...");

        // 等待全部线程执行完成。
        CompletableFuture.allOf(futures).join();

        // 循环读取各子线程处理结果
        for (CompletableFuture future : futures) {
            log.debug(future.get().toString());
        }

        // 关闭线程池
        executor.shutdown();
    }
}
