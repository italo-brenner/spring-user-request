package br.com.springuserrequest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class LabControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private static Random random = new Random();
	
	private String[] adminArray = {"admin0", "admin1", "admin2", "admin3", "admin4", "admin5"};

	@Test
	public void shouldReturnAnonymousUser() throws Exception {
		this.mockMvc.perform(get("/lab")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("anonymousUser")));
	}

	@Test
	public void testFunction1AdminUser() throws Exception {
		this.mockMvc.perform(get("/lab/function1").header("X-User", "admin")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("admin")));
	}

	@Test
	public void testLogUserRequest() throws Exception {
		doSyncAndConcurrently(50, threadName -> {
			try {
				String username = adminArray[random.nextInt(6)];
				this.mockMvc.perform(get("/lab/function1").header("X-User", username)).andDo(print()).andExpect(status().isOk())
					.andExpect(content().string(containsString(username)));
			} catch (Exception e) {
				log.error("error while executing operation {}: {}", threadName, e.getMessage());
			}
		});
	}
	
	private void doSyncAndConcurrently(int threadCount, Consumer<String> operation) throws InterruptedException {

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            String threadName = "Thread-" + i;
            new Thread(() -> {
                try {
                    startLatch.await();
                    operation.accept(threadName);
                } catch (Exception e) {
                    log.error("error while executing operation {}: {}", threadName, e.getMessage());
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }

        startLatch.countDown();
        endLatch.await();
    }
	
}
