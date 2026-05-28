package sistema.financeiro.api.entities;

import jakarta.persistence.*;
import lombok.*;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Endereco {
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String cep;
        private String cidade;
        private String estado;
    }
