package mission2.car.componentFactory;

import mission2.car.components.IComponent;
import mission2.car.components.Steering;

import java.util.List;

public class SteeringFactory implements IComponentFactory {
    private static final int BOSCH_S = 1, MOBIS = 2;
    private List<String> steeringList = List.of("BOSCH", "MOBIS");

    @Override
    public List<String> getAvailableList() {
        return steeringList;
    }

    @Override
    public IComponent getComponent(int nameIdx) {
        return new Steering(nameIdx);
    }
}
