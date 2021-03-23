public class Parameter {
    int column;
    boolean isEssentialData;

    public Parameter(int index, boolean isImportant) {
        this.column = index;
        this.isEssentialData =isImportant;
    }
}
