package sistema.financeiro.api.entities;

import jakarta.persistence.*;
import lombok.*;
import sistema.financeiro.api.dto.DadosAtualizacaoPessoa;
import sistema.financeiro.api.dto.DadosCadastroPessoa;

    @Entity(name = "Pessoa")
    @Table(name = "pessoa")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "codigo")
    public class Pessoa {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long codigo;
        private String nome;
        private Boolean ativo;
        @Embedded
        private Endereco endereco;

        public Pessoa(DadosCadastroPessoa dados) {
            this.nome = dados.nome();
            this.ativo = dados.ativo();
            if (dados.endereco() != null) {
                this.endereco = new Endereco(dados.endereco());
            }
        }

        public void atualizarInformacoes(DadosAtualizacaoPessoa dados) {
            if (dados.nome() != null) {
                this.nome = dados.nome();
            }
            if (dados.ativo() != null) {
                this.ativo = dados.ativo();
            }
            if (dados.endereco() != null) {
                this.endereco.atualizarInformacoesEndereco(dados.endereco());
            }
        }

        public void excluir() {
            this.ativo = false;
        }

    }
    

