package mission2.car;

import mission2.car.components.Brake;
import mission2.car.components.CarType;
import mission2.car.components.Engine;
import mission2.car.components.Steering;

class Car implements ICar {
    private CarType carType;
    private Engine engine;
    private Brake brake;
    private Steering steering;

    protected Car(CarType carType, Engine engine, Brake brake, Steering steering) {
        this.carType = carType;
        this.engine = engine;
        this.brake = brake;
        this.steering = steering;
    }

    public CarType getCarType() {
        return carType;
    }

    public Engine getEngine() {
        return engine;
    }

    public Brake getBrake() {
        return brake;
    }

    public Steering getSteering() {
        return steering;
    }
}
