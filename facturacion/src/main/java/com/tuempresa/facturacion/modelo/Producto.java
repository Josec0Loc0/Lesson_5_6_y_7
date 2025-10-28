package com.tuempresa.facturacion.modelo;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.openxava.annotations.*;
import lombok.*;

@Entity
@Getter @Setter
public class Producto {

    @Id @Column(length=9)
    int numero;

    @Column(length=50) 
    @Required
    @NotNull(message="La descripción no puede estar vacía")
    @Size(min=3, max=50, message="La descripción debe tener entre 3 y 50 caracteres")
    String descripcion;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList
    Autor autor;

    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @DescriptionsList
    Categoria categoria;

    @Money
    @DecimalMin(value="0.01", message="El precio debe ser mayor que cero")
    BigDecimal precio;

    @Files
    @Column(length=32)
    String fotos;

    @TextArea
    String observaciones;

    @Column(length=20)
    @Pattern(regexp="^[A-Za-z0-9 ]*$", message="El código de producto solo puede contener letras y números")
    String codigoProducto;

    // Validación a nivel de entidad
    @AssertTrue(message="El producto debe tener precio y descripción válidos")
    public boolean isProductoValido() {
        return precio != null && precio.compareTo(BigDecimal.ZERO) > 0 
               && descripcion != null && !descripcion.isEmpty();
    }
}
