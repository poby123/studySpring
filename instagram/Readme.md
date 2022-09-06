# README.md
## Todo
- 🎈 게시물 좋아요수와 현재 로그인한 유저의 좋아요 유무를 알도록 BoardLikeDto 수정 및 쿼리 제작.
- 🎈 좋아요 기능.
- 🎈 api가 얼추 완성되면 웹 서비스단도 api걸 써서 osiv를 꺼도 잘 동작하도록 하기.

## Complete
- 💎 웹 댓글 추가 기능. ajax로 처리함.

---

## Notice
현재 Kafka disable 상황으로, 적용하고 싶다면[이 부분](https://github.com/poby123/studySpring/blob/1c12cedd19d5b6c053875b6cb55d95eb2e3bcc52/instagram/src/main/java/com/example/demo/config/kafka/KafkaConsumer.java#L22)의 주석을 해제해야 활성화됩니다.

## Swagger
- http://wj-code-server.com:8080/swagger-ui/index.html

---

## References
### 코드
- https://github.com/Instagram-Clone-Coding/Spring_instagram-clone

### 디자인
- https://www.behance.net/
- https://meetup.toast.com/posts/223

### STOMP
- https://stomp.github.io/stomp-specification-1.2.html 

### KAFKA
- https://velog.io/@sossont/Spring-WebSocket-with-Kafka
- https://ifuwanna.tistory.com/488
- https://dev.to/subhransu/realtime-chat-app-using-kafka-springboot-reactjs-and-websockets-lc
- https://pearlluck.tistory.com/351?category=947339

---

## KAFKA
### 서버 정보 변경 필요시
> config/server.properties 를 수정.

### KAFKA 실행
1. ZooKeeper 실행: `$ bin/zookeeper-server-start.sh config/zookeeper.properties`
2. KAFKA 실행 `$ bin/kafka-server-start.sh config/server.properties`
3. 실행 확인 `$ jps`


### KAFKA CLI
- kafka-topics.sh: 토픽 생성, 조회, 수정 등 역할
- kafka-console-consumer.sh: 토픽의 레코드 즉시 조회
- kafka-console-producer.sh: 토픽의 레코드를 전달(String)
- kafka-consumer-groups.sh: 컨슈머그룹 조회, 컨슈머 오프셋 확인, 수정
s

#### 토픽
  토픽이란? 메시지를 구분하는 단위.
  ##### 토픽 생성하기
  ```shell
  $ ./kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic test
  ```
   - --replication-factor: 복제 대상 파티션 개수: 1
   -  --partitions: 파티션 개수: 3
   -  토픽이름: test

#### 생성된 토픽 확인하기
  ```shell
  $ ./kafka-topics.sh --describe --bootstrap-server localhost:9092
  ```

#### 이벤트 쓰기(Producer write)
  ```shell
  $ ./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test
  ```
  > hello </br>
  > ifuwanna </br>
  > kafka


#### 이벤트 읽기(Consumer read)
  ```shell
  $ ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic test
  ```
  -  `--from-beginning`: 최초 시작 시점의 오프셋부터 가져오기
  ##### 결과
  1. Consumer가 데이터를 polling 해서 위에서 넣은 이벤트 3개를 모두 가져온다.
  2. 데이터가 순서대로 출력되지 않는데, 그 이유는 파티션을 3개 만들었기 때문이다. 순서를 유지하려면, 파티션 하나를 지정해서 메시지를 관리해야한다.
  3. 이벤트는 Kafka에 영구 저장되므로, 많은 소비자가 이벤트를 읽을 수 있다.

  ```shell
  $ ./kafka-console-consumer.sh --bootstrap-server localhost:9092 -group testgroup  --from-beginning --topic test
  ```
  -  `-group`: Consumer group은 이벤트를 처리할때 해당 그룹의 컨슈머들이 이벤트를 어디까지 처리했는지 Consumer Offset을 통해 기억하고 그 이후 오프셋의 이벤트부터 처리해 나갑니다.



#### 그룹 생성
  ```shell
  $ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic [토픽 이름] --group [그룹 이름]
  ```