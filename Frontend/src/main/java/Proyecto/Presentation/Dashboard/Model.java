package Proyecto.Presentation.Dashboard;

import Proyecto.logic.*;
import Proyecto.Presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Model extends AbstractModel {
    private List<Medicamento> medicamentos;
    private List<Medicamento> medicamentosSeleccionados;
    private List<Receta> todasLasRecetas;
    private Object chart1Update;
    private Object chart2Update;

    public static final String CHART1 = "chart1";
    public static final String CHART2 = "chart2";
    public static final String MEDICAMENTOS = "medicamentos";
    public static final String MEDICAMENTOS_SELECCIONADOS = "medicamentosSeleccionados";

    public Model() {
        medicamentos = new ArrayList<>();
        medicamentosSeleccionados = new ArrayList<>();
        todasLasRecetas = new ArrayList<>();
        chart1Update = null;
        chart2Update = null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CHART1);
        firePropertyChange(CHART2);
        firePropertyChange(MEDICAMENTOS);
        firePropertyChange(MEDICAMENTOS_SELECCIONADOS);
    }

    // ==================== GETTERS Y SETTERS ====================

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        firePropertyChange(MEDICAMENTOS);
    }

    public List<Medicamento> getMedicamentosSeleccionados() {
        return medicamentosSeleccionados;
    }

    public void setMedicamentosSeleccionados(List<Medicamento> medicamentosSeleccionados) {
        this.medicamentosSeleccionados = medicamentosSeleccionados;
        firePropertyChange(MEDICAMENTOS_SELECCIONADOS);
    }

    public List<Receta> getTodasLasRecetas() {
        return todasLasRecetas;
    }

    public void setTodasLasRecetas(List<Receta> todasLasRecetas) {
        this.todasLasRecetas = todasLasRecetas;
    }

    public void setChart1(Object trigger) {
        this.chart1Update = trigger;
        firePropertyChange(CHART1);
    }

    public void setChart2(Object trigger) {
        this.chart2Update = trigger;
        firePropertyChange(CHART2);
    }

    // ==================== MÉTODOS PARA GRÁFICO DE LÍNEA ====================

    /**
     * Genera el dataset para el gráfico de medicamentos por mes
     */
    public Map<String, Map<String, Integer>> generarDatosMedicamentosPorMes(
            String añoInicio, String mesInicio,
            String añoFin, String mesFin) {

        Map<String, Map<String, Integer>> resultado = new LinkedHashMap<>();

        try {
            YearMonth inicio = YearMonth.of(Integer.parseInt(añoInicio), parseMes(mesInicio));
            YearMonth fin = YearMonth.of(Integer.parseInt(añoFin), parseMes(mesFin));

            if (inicio.isAfter(fin)) {
                return resultado;
            }

            // Inicializar estructura para cada medicamento seleccionado
            for (Medicamento med : medicamentosSeleccionados) {
                resultado.put(med.getNombre(), new LinkedHashMap<>());

                // Inicializar todos los meses en 0
                YearMonth mes = inicio;
                while (!mes.isAfter(fin)) {
                    String mesLabel = mes.format(DateTimeFormatter.ofPattern("MMM yyyy", new Locale("es")));
                    resultado.get(med.getNombre()).put(mesLabel, 0);
                    mes = mes.plusMonths(1);
                }
            }

            // Contar medicamentos por mes
            for (Receta receta : todasLasRecetas) {
                YearMonth mesReceta = parsearFechaReceta(receta.getFechaCreacion());

                if (mesReceta != null &&
                        !mesReceta.isBefore(inicio) &&
                        !mesReceta.isAfter(fin)) {

                    String mesLabel = mesReceta.format(DateTimeFormatter.ofPattern("MMM yyyy", new Locale("es")));

                    for (Medicamento medReceta : receta.getMedicamentos()) {
                        for (Medicamento medSeleccionado : medicamentosSeleccionados) {
                            if (medReceta.getCodigo().equals(medSeleccionado.getCodigo())) {
                                Map<String, Integer> mesesMed = resultado.get(medSeleccionado.getNombre());
                                mesesMed.put(mesLabel, mesesMed.get(mesLabel) + medReceta.getCantidad());
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // ==================== MÉTODOS PARA GRÁFICO DE PASTEL ====================

    /**
     * Genera el dataset para el gráfico de recetas por estado
     */
    public Map<String, Integer> generarDatosRecetasPorEstado() {
        Map<String, Integer> resultado = new LinkedHashMap<>();

        for (Receta receta : todasLasRecetas) {
            String estado = receta.getEstado();
            resultado.put(estado, resultado.getOrDefault(estado, 0) + 1);
        }

        return resultado;
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private YearMonth parsearFechaReceta(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            return null;
        }

        try {
            String[] formatos = {
                    "yyyy-MM-dd",
                    "dd/MM/yyyy",
                    "dd-MM-yyyy",
                    "yyyy/MM/dd"
            };

            for (String formato : formatos) {
                try {
                    LocalDate date = LocalDate.parse(fecha, DateTimeFormatter.ofPattern(formato));
                    return YearMonth.from(date);
                } catch (DateTimeParseException e) {
                    // Intentar siguiente formato
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Convierte el texto del mes a número (1-12)
     */
    private int parseMes(String mesTexto) {
        // Formato esperado: "11-septiembre" o similar
        if (mesTexto.contains("-")) {
            String[] partes = mesTexto.split("-");
            return Integer.parseInt(partes[0]);
        }
        return 1;
    }

    public void agregarMedicamentoSeleccionado(Medicamento med) {
        if (!medicamentosSeleccionados.contains(med)) {
            medicamentosSeleccionados.add(med);
            firePropertyChange(MEDICAMENTOS_SELECCIONADOS);
        }
    }

    public void removerMedicamentoSeleccionado(Medicamento med) {
        medicamentosSeleccionados.remove(med);
        firePropertyChange(MEDICAMENTOS_SELECCIONADOS);
    }

    public void limpiarMedicamentosSeleccionados() {
        medicamentosSeleccionados.clear();
        firePropertyChange(MEDICAMENTOS_SELECCIONADOS);
    }
}
