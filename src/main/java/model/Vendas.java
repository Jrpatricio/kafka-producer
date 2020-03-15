package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Vendas {
    private Long operacao;
    private Long cliente;
    private Integer quantidade;
    private BigDecimal valorTotal;
}
