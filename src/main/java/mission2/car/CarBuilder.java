package mission2.car;

import mission2.car.components.IComponent;

public class CarBuilder {
    private IComponent carType;
    private IComponent engine;
    private IComponent brake;
    private IComponent steering;

    public CarBuilder carType(IComponent carType) {
        this.carType = carType;
        return this;
    }

    public CarBuilder engine(IComponent engine) {
        this.engine = engine;
        return this;
    }

    public CarBuilder brake(IComponent brake) {
        this.brake = brake;
        return this;
    }

    public CarBuilder steering(IComponent steering) {
        this.steering = steering;
        return this;
    }

    public Car build() {
        return new Car(carType, engine, brake, steering); // Student 생성자 호출
    }
}