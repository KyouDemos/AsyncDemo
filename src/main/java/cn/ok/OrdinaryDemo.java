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

        // �����̳߳�
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        int cnt = 1;

        // ���߳����������
        CompletableFuture[] futures = new CompletableFuture[cnt];

        // ���������첽�߳�
        for (int i = 0; i < cnt; i++) {
            // ָ��ִ���̳߳أ������첽���̣߳�ָ�����߳��쳣��������
            futures[i] = CompletableFuture.supplyAsync(new SupplierDemo(i + 1), executor).exceptionally(ExceptionHandler::handleException);
        }

        log.debug("�첽�����������, �ȴ����߳�ִ����...");

        // �ȴ�ȫ���߳�ִ����ɡ�
        CompletableFuture.allOf(futures).join();

        // ѭ����ȡ�����̴߳�����
        for (CompletableFuture future : futures) {
            log.debug(future.get().toString());
        }

        // �ر��̳߳�
        executor.shutdown();
    }
}
