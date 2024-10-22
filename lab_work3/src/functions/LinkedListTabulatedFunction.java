package functions;

public class LinkedListTabulatedFunction implements TabulatedFunction{
    private FunctionNode head;
    private int size;

    public LinkedListTabulatedFunction(int i, int i1, int i2){
        head = new FunctionNode(null);
        head.setNext(head);
        head.setPrev(head);
        size = 0;
    }

    // метод для получения узла по индексу
    private FunctionNode getNodeByIndex(int index){
        if (index < 0 || index >= size){
            throw new FunctionPointIndexOutOfBoundsException("Индекс вне диапазона");
        }
        FunctionNode cur;
        if (index < size/2){
            cur = head.getNext(); // начинаем с головы
            for (int i = 0; index < i; i++) {
                cur = cur.getNext();
            }
        }
        else {
            cur = head.getPrev(); // начинаем с хвоста
            for (int i = size - 1; i > index; i--){
                cur = cur.getPrev();
            }
        }
        return cur;
    }

    // метод для добавления эл-та в конец списка
    public FunctionNode addNodeToTail(FunctionPoint point){
        FunctionNode newNode = new FunctionNode(point);
        FunctionNode tail = head.getPrev();

        tail.setNext(newNode);
        newNode.setPrev(tail);
        newNode.setNext(head);
        head.setPrev(newNode);
        size++;
        return newNode;
    }

    // метод для добавления эл-та в указанную позицию
    public FunctionNode addNodeByIndex(int index, FunctionPoint point) {
        if (index < 0 || index > size){
            throw new FunctionPointIndexOutOfBoundsException("Индекс вне диапозона");
        }

        FunctionNode newNode = new FunctionNode(point);
        if (index == size){
            return addNodeToTail(point); // добавляем в конец, если индекс равен размеру
        }
        FunctionNode cur =  getNodeByIndex(index);
        FunctionNode prevNode = cur.getPrev();

        prevNode.setNext(newNode);
        newNode.setPrev(prevNode);
        newNode.setNext(cur);
        cur.setPrev(newNode);
        size++;
        return newNode;
    }

    // метод удаляющий элемент списка по индексу
    public FunctionNode deleteNodeByIndex(int index){
        if (index < 0 || index > size){
            throw new FunctionPointIndexOutOfBoundsException("Индекс вне диапозона");
        }

        FunctionNode toDel = getNodeByIndex(index);
        FunctionNode prevNode = toDel.getPrev();
        FunctionNode nextNode = toDel.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        size--;

        return toDel; // Возвращаем удалённый узел
    }
    // метод для получения размера списка
    public int getSize(){
        return size;
    }

    @Override
    public double getLeftDomainBorder() {
        return head.getNext().getData().getX();
    }

    @Override
    public double getRightDomainBorder() {
        return head.getPrev().getData().getX();
    }

    @Override
    public double getFunctionValue(double x) {
        if (Double.compare(x, getLeftDomainBorder()) < 0 || Double.compare(x, getRightDomainBorder()) > 0) {
            return Double.NaN;
        }

        FunctionNode current = head.getNext();
        while (current != head) {
            if (Double.compare(current.getData().getX(), x) == 0) {
                return current.getData().getY();
            }
            if (Double.compare(current.getData().getX(), x) > 0) {
                FunctionNode prev = current.getPrev();
                double x1 = prev.getData().getX();
                double y1 = prev.getData().getY();
                double x2 = current.getData().getX();
                double y2 = current.getData().getY();

                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
            current = current.getNext();
        }

        return Double.NaN; // Для безопасности
    }

    @Override
    public int getPointsCount() {
        return size;
    }

    @Override
    public FunctionPoint getPoint(int index) {
        return getNodeByIndex(index).getData();
    }

    @Override
    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        if (index < 0 || index >= size) {
            throw new FunctionPointIndexOutOfBoundsException("Index out of bounds.");
        }
        FunctionNode currentNode = getNodeByIndex(index);
        if (index > 0 && index < size - 1) {
            if (Double.compare(point.getX(), currentNode.getPrev().getData().getX()) > 0 &&
                    Double.compare(point.getX(), currentNode.getNext().getData().getX()) < 0) {
                currentNode.setData(point);
            } else {
                throw new InappropriateFunctionPointException("Inappropriate x value.");
            }
        } else {
            currentNode.setData(point);
        }
    }

    @Override
    public double getPointX(int index) {
        return getPoint(index).getX();
    }

    @Override
    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        FunctionPoint point = getPoint(index);
        point.setX(x);
        setPoint(index, point);
    }

    @Override
    public double getPointY(int index) {
        return getPoint(index).getY();
    }

    @Override
    public void setPointY(int index, double y) {
        getPoint(index).setY(y);
    }

    @Override
    public void deletePoint(int index) {
        if (size < 3) {
            throw new IllegalStateException("Cannot delete point; fewer than three points.");
        }
        deleteNodeByIndex(index);
    }

    @Override
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        if (point == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }
        for (int i = 0; i < size; i++) {
            if (Double.compare(getPointX(i), point.getX()) == 0) {
                throw new InappropriateFunctionPointException("Duplicate x value.");
            }
        }
        if (size == 0 || Double.compare(point.getX(), getLeftDomainBorder()) < 0) {
            addNodeToTail(point);
        } else {
            for (int i = 0; i < size; i++) {
                if (Double.compare(getPointX(i), point.getX()) > 0) {
                    addNodeByIndex(i, point);
                    return;
                }
            }
            addNodeToTail(point);
        }
    }
}