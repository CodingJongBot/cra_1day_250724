package mission2.car.components;

public class Engine implements IComponent {
    int nameIdx;

    public Engine(int nameIdx) {
        this.nameIdx = nameIdx;
    }

    @Override
    public int getNameIdx() {
        return nameIdx;
    }
}
