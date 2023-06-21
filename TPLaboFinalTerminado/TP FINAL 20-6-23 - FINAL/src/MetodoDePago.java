import java.io.Serializable;

public enum MetodoDePago   {
    EFECTIVO("Efectivo"),
    DEBITO("Debito"),
    CREDITO("Credito");

    private String metodoDePago;
    MetodoDePago(String metodoDePago) {
        this.metodoDePago=metodoDePago;
    }

    @Override
    public String toString() {
        return  metodoDePago;
    }
}
