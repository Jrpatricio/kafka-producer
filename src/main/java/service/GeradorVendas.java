package service;

import serialize.VendasSerialize;
import model.Vendas;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

public class GeradorVendas {

    private static long operacao = 0;

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VendasSerialize.class.getName());


        try (KafkaProducer<String, Vendas> producer = new KafkaProducer<String, Vendas>(properties)) {
            for (int i = 0; i <= 100; i++) {
                Vendas venda = geraVenda();
                ProducerRecord<String, Vendas> record = new ProducerRecord<String, Vendas>("vendas", venda);
                producer.send(record);
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Vendas geraVenda() {

        long cliente = new Random().nextLong();
        int quantIngressos = new Random().nextInt(10);
        BigDecimal valorTotal = BigDecimal.valueOf(500);

        return new Vendas(operacao++, cliente, quantIngressos, valorTotal.multiply(BigDecimal.valueOf(quantIngressos)));
    }
}
