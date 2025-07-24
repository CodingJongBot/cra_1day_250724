package mission2.car.componentFactory;

import mission2.car.components.CarType;
import mission2.car.components.IComponent;

import java.util.List;

public class CarTypeFactory implements IComponentFactory {

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;

    private List<String> carTypeList = List.of("SEDAN", "SUV", "TRUCK");


    @Override
    public List<String> getAvailableList() {
        return carTypeList;
    }

    @Override
    public IComponent getComponent(int nameIdx) {
        return new CarType(nameIdx);
    }
}
