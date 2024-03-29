package com.jamily.projetocursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jamily.projetocursomc.domain.enums.EstadoPagamento;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private Integer estadoPag;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;
	
	public Pagamento () {
		
	}

	public Pagamento(Integer id, EstadoPagamento estadoPag, Pedido pedido) {
		super();
		this.id = id;
		this.estadoPag = estadoPag.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPag() {
		return EstadoPagamento.toEnum(estadoPag);
	}

	public void setEstadoPag(EstadoPagamento estadoPag) {
		this.estadoPag = estadoPag.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
