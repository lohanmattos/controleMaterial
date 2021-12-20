package com.material.controleMaterial.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@NotEmpty
	private String numeroPatrimonial;
	
	private String nomeclatura; 
	
	private int quantidade; 
	
	private double valor; 
	
	private String setor; 
	
	private String status;
	
	@DateTimeFormat(pattern = ("yyyy-MM-dd"))
	private LocalDate dataConferido;

	@Deprecated
	protected Produto() {
		
	}
	
	public Produto(String numeroPatrimonial ) {
		this.numeroPatrimonial = numeroPatrimonial;
	}

	public LocalDate getDataConferido() {
		return dataConferido;
	}

	public void setDataConferido(LocalDate dataConferido) {
		this.dataConferido = dataConferido;
	}

	public String getNumeroPatrimonial() {
		return numeroPatrimonial;
	}

	public void setNumeroPatrimonial(String numeroPatrimonial) {
		this.numeroPatrimonial = numeroPatrimonial;
	}

	public String getNomeclatura() {
		return nomeclatura;
	}

	public void setNomeclatura(String nomeclatura) {
		this.nomeclatura = nomeclatura;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataConferido, nomeclatura, numeroPatrimonial, quantidade, setor, status, valor);
	}

	
}
