package com.lin.lojbackendjudgeservice.rabbitmq;

import cn.hutool.json.JSONUtil;
import com.lin.lojbackendjudgeservice.judge.JudgeService;
import com.lin.lojbackendmodel.model.entity.QuestionSubmit;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

// 指定程序监听的消息队列和确认机制
@Component
@Slf4j
public class MyMessageConsumer {

	@Resource
	private JudgeService judgeService;

	// 指定程序监听的消息队列和确认机制
	@SneakyThrows
	@RabbitListener(queues = {"code_queue"}, ackMode = "MANUAL")
	public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
		log.info("receiveMessage message = {}", message);
		QuestionSubmit questionSubmit = JSONUtil.toBean(message, QuestionSubmit.class);
		try {
			judgeService.doJudge(questionSubmit);
			channel.basicAck(deliveryTag, false);
		}catch (Exception e){
			channel.basicNack(deliveryTag,false,false);
		}

	}
}