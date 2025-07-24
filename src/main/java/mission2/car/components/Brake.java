package mission2.car.components;

public class Brake implements IComponent {
    int nameIdx;

    public Brake(int nameIdx) {
        this.nameIdx = nameIdx;
    }

    public int getNameIdx() {
        return nameIdx;
    }
}
