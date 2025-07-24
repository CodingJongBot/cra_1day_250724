package mission2.car.componentFactory;

import mission2.car.components.Engine;
import mission2.car.components.IComponent;

import java.util.List;

public class EngineFactory implements IComponentFactory {
    private List<String> engineList = List.of("GM", "TOYOTA", "WIA", "고장난 엔진");
    private static final int GM = 1, TOYOTA = 2, WIA = 3, BROKEN_ENGINE = 4;


    @Override
    public List<String> getAvailableList() {
        return engineList;
    }

    @Override
    public IComponent getComponent(int nameIdx) {
        return new Engine(nameIdx);
    }
}
