package com.salesinaos.triana.dam.proyectoversion3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
	
	@Id
	@GeneratedValue
	private long idVenta;
	
	private double precioTotal;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVenta;
	
	@Builder.Default
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany (mappedBy = "venta",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private  List<LineaDeVenta> lineaDeVenta = new ArrayList<LineaDeVenta>();
	
	@ManyToOne
	private Usuario usuario;
	
	public List<LineaDeVenta> getLineaDeVentas(){
		return Collections.unmodifiableList(lineaDeVenta);
	}
 	
	//MÉTODOS HELPER
	
	public void addLineaVenta(LineaDeVenta lv) {
		lv.getLineaVentaPK().setLineaVenta_id(getLineaVentaId());
		lv.setVenta(this);
		this.lineaDeVenta.add(lv);
	}
		
		public void removeUnaLineaDeVenta(LineaDeVenta lineaV) {
			lineaV.setVenta(null);
			this.lineaDeVenta.remove(lineaV);
		}
	
	public long getLineaVentaId() {
		if(this.lineaDeVenta.size() > 0) {
			return this.lineaDeVenta.stream()
					.map(LineaDeVenta::getLineaVentaPK)
					.map(LineaVentaPK::getLineaVenta_id)
					.max(Comparator.naturalOrder())
					.orElse(0l)+11;
		}else
			return 11;
	}
	

}
