package br.dcx.ufpb.meajude.dtos;
import br.dcx.ufpb.meajude.modelos.UsuarioModelo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDto {
    private String nome;

    @NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    private String email;
    private String celular;

    @NotBlank
    private String CPF_CNPJ;

    @NotBlank
    private String senha;

    public UsuarioDto() {
    }

    public UsuarioDto(String nome, String email, String celular, String CPF_CNPJ, String senha) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.CPF_CNPJ = CPF_CNPJ;
        this.senha = senha;
    }

    // Método estático para criar um UsuarioDto a partir de um UsuarioModelo
    public static UsuarioDto from(UsuarioModelo usuarioModelo) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome(usuarioModelo.getNome());
        usuarioDto.setEmail(usuarioModelo.getEmail());
        // Copie outros campos, se necessário
        return usuarioDto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCPF_CNPJ() {
        return CPF_CNPJ;
    }

    public void setCPF_CNPJ(String CPF_CNPJ) {
        this.CPF_CNPJ = CPF_CNPJ;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
