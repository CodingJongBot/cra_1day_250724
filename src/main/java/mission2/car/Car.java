package mission2.car;

import mission2.car.components.IComponent;

public class Car implements ICar {
    private IComponent carType;
    private IComponent engine;
    private IComponent brake;
    private IComponent steering;

    protected Car(IComponent carType, IComponent engine, IComponent brake, IComponent steering) {
        this.carType = carType;
        this.engine = engine;
        this.brake = brake;
        this.steering = steering;
    }

    public IComponent getCarType() {
        return carType;
    }

    public IComponent getEngine() {
        return engine;
    }

    public IComponent getBrake() {
        return brake;
    }

    public IComponent getSteering() {
        return steering;
    }
}
