package mission2.car.componentFactory;

import mission2.car.components.Brake;
import mission2.car.components.IComponent;

import java.util.List;

public class BrakeFactory implements IComponentFactory {
    private final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;

    private List<String> brakeList = List.of("MANDO", "CONTINENTAL", "BOSCH");

    @Override
    public List<String> getAvailableList() {
        return brakeList;
    }

    @Override
    public IComponent getComponent(int nameIdx) {
        return new Brake(nameIdx);
    }
}
