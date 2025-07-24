package mission2.car;

import mission2.car.components.Brake;
import mission2.car.components.CarType;
import mission2.car.components.Engine;
import mission2.car.components.Steering;

public class CarBuilder {
    private CarType carType;
    private Engine engine;
    private Brake brake;
    private Steering steering;

    public CarBuilder carType(CarType carType) {
        this.carType = carType;
        return this;
    }

    public CarBuilder engine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public CarBuilder brake(Brake brake) {
        this.brake = brake;
        return this;
    }

    public CarBuilder steering(Steering steering) {
        this.steering = steering;
        return this;
    }

    public Car build() {
        return new Car(carType, engine, brake, steering); // Student 생성자 호출
    }
}