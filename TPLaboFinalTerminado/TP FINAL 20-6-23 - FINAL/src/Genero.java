public enum Genero {
    M("Masculino"),
    F("Femenino");

    private String genero;

    Genero(String genero){
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "" + genero;

    }
}
