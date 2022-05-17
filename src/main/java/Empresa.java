import controller.DepartamentoController;
import controller.ProgramadorController;
import dto.DepartamentoDTO;
import dto.ProgramadorDTO;
import manager.HibernateController;
import model.Departamento;
import model.Lenguajes;
import model.Programador;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

public class Empresa {
    Departamento d1, d2;
    Programador p1, p2, p3, p4;
    private static Empresa instance;

    private Empresa() {

    }

    public static Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa();
        }
        return instance;
    }

    public void initDataBase() {
        HibernateController hc = HibernateController.getInstance();
        hc.open();

        //Departamentos
        System.out.println("Insertando departamentos...");
        hc.getTransaction().begin();
        d1 = new Departamento("Programación", 200000);
        d2 = new Departamento("BBDD", 100000);
        hc.getManager().persist(d1);
        hc.getManager().persist(d2);
        hc.getTransaction().commit();

        //Programadores
        System.out.println("Insertando programadores...");
        hc.getTransaction().begin();
        p1 = new Programador("Sebastian", 4, new HashSet<Lenguajes>(Arrays.asList(Lenguajes.Java)), 3000, d1);
        p2 = new Programador("David", 7, new HashSet<Lenguajes>(Arrays.asList(Lenguajes.Java, Lenguajes.C, Lenguajes.CSharp)), 4500, d1);
        p3 = new Programador("Alejandro", 2, new HashSet<Lenguajes>(Arrays.asList(Lenguajes.Java, Lenguajes.Python)), 1600, d2);
        p4 = new Programador("Sandra", 2, new HashSet<Lenguajes>(Arrays.asList(Lenguajes.Java, Lenguajes.Python)), 1600, d2);
        hc.getManager().persist(p1);
        hc.getManager().persist(p2);
        hc.getManager().persist(p3);
        hc.getManager().persist(p4);
        hc.getTransaction().commit();
        hc.close();
    }

    public void Departamentos() {
        DepartamentoController dc = DepartamentoController.getInstance();

        System.out.println("Inicio DEPARTAMENTOS");
        System.out.println("--Obteniendo todos los departamentos--");
        System.out.println(dc.getAllDepartamentos());

        System.out.println("--Obteniendo departamento mediante su ID--");
        System.out.println(dc.getDepartamentoById(2L));

        System.out.println("--Insertando nuevo departamento--");
        DepartamentoDTO nuevoDept = DepartamentoDTO.builder()
                .nombre("Diseño")
                .presupuesto(200000)
                .build();
        dc.postDepartamento(nuevoDept);
        System.out.println(dc.getDepartamentoById(3L));

        System.out.println("--Eliminando departamento--");
        dc.deleteDepartamento(dc.getDepartamentoById(3L));
        System.out.println(dc.getAllDepartamentos());
    }

    public void Programadores() {
        ProgramadorController pc = ProgramadorController.getInstance();

        System.out.println("Inicio PROGRAMADORES");
        System.out.println("--Obteniendo todos los programadores--");
        System.out.println(pc.getAllProgramadores());

        System.out.println("--Obteniendo programador meduante su ID--");
        System.out.println(pc.getProgramadorById(3L));

        System.out.println("--Insertando nuevo programador");
        ProgramadorDTO nuevoProg = ProgramadorDTO.builder()
                .nombre("Javier")
                .exp(5)
                .salario(4000)
                .departamento(d2)
                .lenguajes(new HashSet<>(Arrays.asList(Lenguajes.Java, Lenguajes.CSharp, Lenguajes.Python)))
                .build();
        pc.postProgramador(nuevoProg);
        System.out.println(pc.getProgramadorById(5L));

        System.out.println("--Eliminando programador--");
        pc.deleteProgramador(pc.getProgramadorById(5L));
        System.out.println(pc.getAllProgramadores());
    }
}
