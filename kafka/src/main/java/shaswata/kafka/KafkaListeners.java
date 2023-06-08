//package shaswata.kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.json.JSONObject;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class KafkaListeners {
//
//    @KafkaListener(topics = "gdwTopic", groupId = "notification")
//    public JSONObject listener(ConsumerRecord data){
//        String strData = data.value().toString();
//        JSONObject jsonObject = new JSONObject(strData);
//        System.out.println(jsonObject);
//        return jsonObject;
//    }
//
//}
