package com.ibec_api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCartao;

    private String sobrenomeCartao;

    private Integer numeroCartao;

    private Integer expiracao;

    private Integer cvv;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
