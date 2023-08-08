# aws-localstack-sns-sqs
SNS Producer para um SQS Consumer onde o Tópico SNS tem uma subscrição para o SQS

O SNS está implementado o SQS ainda não.

Primeiro configura credenciais depois de subir o Docker do Localstack
aws --endpoint-url=http://localhost:4566 configure
Devolve
AWS Access Key ID [None]: marcelo
AWS Secret Access Key [None]: marcelo
Default region name [None]: us-east-1
Default output format [None]: json


---------------------------------------------------------SNS---------------------------------------------------------

Lista Tópicos
aws --endpoint-url=http://localhost:4566  sns list-topics --region us-east-1

Cria um tópico
aws --endpoint-url=http://localhost:4566  sns create-topic  --name  order-created-topic
Devolve{
    "TopicArn": "arn:aws:sns:us-east-1:000000000000:order-created-topic"
}

Lista Subscrições do tópico
aws --endpoint-url=http://localhost:4566 sns list-subscriptions-by-topic --topic-arn arn:aws:sns:us-east-1:000000000000:order-created-topic
Devolve
{
    "Subscriptions": []
}


aws sns publish --endpoint-url=http://localhost:4566 --topic-arn arn:aws:sns:us-east-1:000000000000:order-creation-events --message "Hello World" --profile test-profile --region us-east-1 --output json | cat
	  
aws --endpoint-url=http://localhost:4566  sns list-topics --region us-east-1

aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:order-creation-events --profile test-profile  --protocol sqs --notification-endpoint http://localstack:4566/000000000000/dummy-queue --output table | cat	  
aws --endpoint-url=http://localhost:4566 sns subscribe ––topic-arn arn:aws:sns:us-east-1:000000000000:order-creation-events ––protocol sqs ––notification-endpoint http://localstack:4566/000000000000/dummy-queue ––attributes RawMessageDelivery=true
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:order-creation-events --protocol sqs --notification-endpoint http://localstack:4566/000000000000/dummy-queue
			  
aws --endpoint-url=http://localhost:4566 sns list-subscriptions-by-topic --topic-arn arn:aws:sns:us-east-1:000000000000:order-creation-events
  
	  
aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/dummy-queue --profile test-profile --region us-east-1 --output json | cat



aws --endpoint-url=http://localhost:4566 sns subscribe ––topic-arn arn:aws:sns:us-east-1:000000000000:order-creation-events ––protocol sqs ––notification-endpoint http://localstack:4566/000000000000/dummy-queue ––attributes RawMessageDelivery=true



---------------------------------------------------------SQS---------------------------------------------------------

List queues
aws --endpoint-url=http://localhost:4566  sqs list-queues

Cria Queues
aws --endpoint-url=http://localhost:4566  sqs create-queue  --queue-name  order-queue
Devolve
{
    "QueueUrl": "http://localhost:4566/000000000000/order-queue"
}

aws --endpoint-url=http://localhost:4566  sqs create-queue  --queue-name  order-queue-2
Devolve
{
    "QueueUrl": "http://localhost:4566/000000000000/order-queue-2"
}

Cria subscrições no topico
aws --endpoint-url=http://localhost:4566 sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:order-created-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:order-queue
Devolve{
    "SubscriptionArn": "arn:aws:sns:us-east-1:000000000000:order-created-topic:5a8e2754-253c-428b-be54-21997fba2504"
}

Lista subscrições no topico sns
aws --endpoint-url=http://localhost:4566  sns list-subscriptions
Devolve
{
    "Subscriptions": [
        {
            "SubscriptionArn": "arn:aws:sns:us-east-1:000000000000:order-created-topic:5a8e2754-253c-428b-be54-21997fba2504",
            "Owner": "000000000000",
            "Protocol": "sqs",
            "Endpoint": "arn:aws:sqs:us-east-1:000000000000:order-queue",
            "TopicArn": "arn:aws:sns:us-east-1:000000000000:order-created-topic"
        }
    ]
}

Envio direto de msg para SQS
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url order-queue --message-body "hi"
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url order-queue --message-body '{"key": ["value1", "value2"]}'

Recebe msg do SQS
aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url order-queue




Referencias 
https://auth0.com/blog/spring-cloud-messaging-with-aws-and-localstack/

----Não utilizado

Policy
https://github.com/awswithdotnet/sns/blob/main/sqs-attributes.json

