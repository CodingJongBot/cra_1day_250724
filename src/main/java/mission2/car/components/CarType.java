package mission2.car.components;

public class CarType implements IComponent {
    int nameIdx;

    public CarType(int nameIdx) {
        this.nameIdx = nameIdx;
    }

    @Override
    public int getNameIdx() {
        return nameIdx;
    }
}
