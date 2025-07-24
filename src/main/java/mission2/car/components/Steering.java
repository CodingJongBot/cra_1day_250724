package mission2.car.components;

public class Steering implements IComponent {
    int nameIdx;

    public Steering(int nameIdx) {
        this.nameIdx = nameIdx;
    }

    @Override
    public int getNameIdx() {
        return nameIdx;
    }
}
