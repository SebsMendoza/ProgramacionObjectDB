package dto;

import lombok.Builder;
import lombok.Data;
import model.Departamento;
import model.Lenguajes;

import java.util.Set;

@Data
@Builder
public class ProgramadorDTO {
    private Long id;
    private String nombre;
    private int exp;
    private Set<Lenguajes> lenguajes;
    private double salario;
    private Departamento departamento;

    @Override
    public String toString() {
        return "ProgramadorDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", exp=" + exp +
                ", lenguajes=" + lenguajes +
                ", salario=" + salario +
                ", departamento=" + departamento.getNombre() +
                "}";
    }
}
