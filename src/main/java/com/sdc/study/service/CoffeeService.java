package com.sdc.study.service;

import java.util.HashMap;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.sdc.study.mqtt.SdcMqttClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoffeeService {

	final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //메시지를 받는 콜백 행위
        arg.forEach((key, value)->{
            System.out.println( String.format("메시지 도착 : 키 -> %s, 값 -> %s", key, value) );
        });
    };

    public static void main(String [] args){

        final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //메시지를 받는 콜백 행위
            arg.forEach((key, value)->{
                System.out.println( String.format("메시지 도착 : 키 -> %s, 값 -> %s", key, value) );
            });
        };

        SdcMqttClient client = new SdcMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.

        client.init("", "", "tcp://localhost:1883", "")
              .subscribe(new String[]{"test"});  //subscribe 메소드는 구독할 대상


        client.initConnectionLost( (arg)->{  //콜백행위1, 서버와의 연결이 끊기면 동작
            arg.forEach((key, value)->{
                System.out.println( String.format("커넥션 끊김~! 키 -> %s, 값 -> %s", key, value) );
            });
        });

        client.initDeliveryComplete((arg)-> {  //콜백행위2, 메시지를 전송한 이후 동작
            arg.forEach((key, value)->{
                System.out.println( String.format("메시지 전달 완료~! 키 -> %s, 값 -> %s", key, value) );
            });
        });


        new Thread( ()->{
            try {
                Thread.sleep(9000);
                client.sender("test", "Hello world! 한글한글!");  //이런식으로 보낸다.
                client.close();  //종료는 이렇게!
            } catch (Exception e) {
                e.printStackTrace();
            }
        } ).start();

    }

}
