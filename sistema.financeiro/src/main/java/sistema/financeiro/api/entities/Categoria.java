package sistema.financeiro.api.entities;

import jakarta.persistence.*;
import lombok.*;
import sistema.financeiro.api.dto.DadosAtualizacaoCategoria;
import sistema.financeiro.api.dto.DadosCadastroCategoria;

    @Entity(name = "Categoria")
    @Table(name = "categoria")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "codigo")
    public class Categoria {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long codigo;
        private String nome;
        private Boolean ativo;

        public Categoria(DadosCadastroCategoria dados) {
            this.nome = dados.nome();
            this.ativo = true;
        }

        public void atualizarInformacoes(DadosAtualizacaoCategoria dados) {
            if (dados.nome() != null) {
                this.nome = dados.nome();
            }
        }

        public void excluir() {
            this.ativo = false;
        }
    }
