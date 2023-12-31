package fooddeliveryservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddeliveryservice.config.kafka.KafkaProcessor;
import fooddeliveryservice.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MimeTypeUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotifyRestaurantOwnerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(
        NotifyRestaurantOwnerTest.class
    );

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public MenuRepository repository;

    @Test
    @SuppressWarnings("unchecked")
    public void test0() {
        //given:
        Menu entity = new Menu();

        entity.setMenuId("1");
        entity.setName("메뉴1");
        entity.setPrice(new Money(10.0, "KRW"));
        entity.setDescription("메뉴1의 설명");

        repository.save(entity);

        //when:

        MenuRegistered event = new MenuRegistered(entity);

        MenuManagementApplication.applicationContext = applicationContext;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String msg = objectMapper.writeValueAsString(event);

            processor
                .inboundTopic()
                .send(
                    MessageBuilder
                        .withPayload(msg)
                        .setHeader(
                            MessageHeaders.CONTENT_TYPE,
                            MimeTypeUtils.APPLICATION_JSON
                        )
                        .setHeader("type", event.getEventType())
                        .build()
                );

            //then:

            Message<String> received = (Message<String>) messageCollector
                .forChannel(processor.outboundTopic())
                .poll();

            assertNotNull("Resulted event must be published", received);

            LOGGER.info("Response received: {}", received.getPayload());

            MenuRegistered outputEvent = objectMapper.readValue(
                received.getPayload(),
                MenuRegistered.class
            );

            assertEquals(outputEvent.getMenuId(), "1");
            assertEquals(outputEvent.getName(), "메뉴1");
            assertEquals(outputEvent.getPrice(), new Money(10.0, "KRW"));
            assertEquals(outputEvent.getDescription(), "메뉴1의 설명");
        } catch (Exception e) {
            LOGGER.error("exception", e);
            assertTrue("exception", false);
        }
    }
}
