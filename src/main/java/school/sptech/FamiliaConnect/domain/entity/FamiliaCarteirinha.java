package school.sptech.FamiliaConnect.domain.entity;

import school.sptech.FamiliaConnect.domain.entity.carteirinha.ProcessamentoCarteirinha;

public class FamiliaCarteirinha {

    private Familia familia;
    private ProcessamentoCarteirinha processamentoCarteirinha;

    public FamiliaCarteirinha() {
    }

    public FamiliaCarteirinha(Familia familia, ProcessamentoCarteirinha processamentoCarteirinha) {
        this.familia = familia;
        this.processamentoCarteirinha = processamentoCarteirinha;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public ProcessamentoCarteirinha getProcessamentoCarteirinha() {
        return processamentoCarteirinha;
    }

    public void setProcessamentoCarteirinha(ProcessamentoCarteirinha processamentoCarteirinha) {
        this.processamentoCarteirinha = processamentoCarteirinha;
    }
}
