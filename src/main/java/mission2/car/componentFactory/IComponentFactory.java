package mission2.car.componentFactory;

import mission2.car.components.IComponent;

import java.util.List;

public interface IComponentFactory {
    List<String> getAvailableList();

    IComponent getComponent(int nameIdx);
}
