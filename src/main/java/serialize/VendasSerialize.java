package serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import model.Vendas;
import org.apache.kafka.common.serialization.Serializer;

public class VendasSerialize implements Serializer<Vendas> {

    @SneakyThrows
    @Override
    public byte[] serialize(String topic, Vendas data) {
        return new ObjectMapper().writeValueAsBytes(data);
    }
}
