package br.dcx.ufpb.meajude.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_CAMPANHAS")
public class CampanhaModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private EstadoCampanha estado;
    
    @NotBlank(message = "Título curto é obrigatório")
    @Size(max = 100, message = "Título curto deve ter no máximo 100 caracteres")
    private String tituloCurto;
    
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;
    
    @Positive(message = "A meta deve ser maior que zero")
    private double meta;
    
    @Future(message = "A data de término deve estar no futuro")
    private Date dataTermino;
    
    private double valorArrecadado;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL)
    private List<DoacaoModelo> doacoes;

    
    @ManyToOne
    private UsuarioModelo usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoCampanha getEstado() {
        return estado;
    }

    public void setEstado(EstadoCampanha estado) {
        this.estado = estado;
    }

    public String getTituloCurto() {


        
        return tituloCurto;
    }

    public void setTituloCurto(String tituloCurto) {
        this.tituloCurto = tituloCurto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public double getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }

    public UsuarioModelo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModelo usuario) {
        this.usuario = usuario;
    }
}
