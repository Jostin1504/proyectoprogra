package Proyecto.Data;

import Proyecto.Logic.Medico;

import java.util.ArrayList;
import java.util.List;

public class data {
    private List<Medico> medicos;

    public data() {
        medicos = new ArrayList<Medico>();

        medicos.add(new Medico("Santiago", "111"));
    }
}
