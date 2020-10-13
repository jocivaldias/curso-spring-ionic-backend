package com.jocivaldias.cursomc.dto;

import com.jocivaldias.cursomc.domain.Cliente;
import com.jocivaldias.cursomc.domain.Estado;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class EstadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "Preenchimento obrigatório.")
    @Length(min=5, max=120, message = "O tamanho deve ser entre 5 e 120 caracteres.")
    private String nome;

    public EstadoDTO() {
    }

    public EstadoDTO(Estado obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
