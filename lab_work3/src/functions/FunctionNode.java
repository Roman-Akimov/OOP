package functions;

public class FunctionNode {
    private FunctionPoint data;
    private FunctionNode next;
    private FunctionNode prev;

    // Конструктор
    public FunctionNode(FunctionPoint data){
        this.data = data;
        this.next = this; // Указывает на себя
        this.prev = this; // Указывает на себя
    }

    public FunctionPoint getData() {
        return data;
    }

    public FunctionNode getNext() {
        return next;
    }

    public FunctionNode getPrev() {
        return prev;
    }

    public void setData(FunctionPoint data) {
        this.data = data; // Здесь устанавливается новое значение
    }

    public void setNext(FunctionNode next) {
        this.next = next;
    }

    public void setPrev(FunctionNode prev) {
        this.prev = prev;
    }
}
