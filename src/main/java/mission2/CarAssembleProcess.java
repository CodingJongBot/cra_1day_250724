package mission2;

import mission2.car.CarBuilder;
import mission2.car.componentFactory.BrakeFactory;
import mission2.car.componentFactory.CarTypeFactory;
import mission2.car.componentFactory.EngineFactory;
import mission2.car.componentFactory.SteeringFactory;
import mission2.car.components.IComponent;

import java.util.List;
import java.util.Scanner;

public class CarAssembleProcess {
    private final String CLEAR_SCREEN = "\033[H\033[2J";

    private final int CAR_TYPE_IDX = 0;
    private final int ENGINE_IDX = 1;
    private final int BRAKE_SYSTEM_IDX = 2;
    private final int STEERING_SYSTEM_IDX = 3;
    private final int RUN_TEST_IDX = 4;

    private final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private final int GM = 1, TOYOTA = 2, WIA = 3;
    private final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private final int BOSCH_S = 1, MOBIS = 2;
    public final int RUN_PRODUCE_CAR = 1;
    public final int TEST_PRODUCE_CAR = 2;
    public final int BROKEN_ENGINE = 4;
    public final int UNDO_OR_RESET = 0;

    private IComponent[] carComponentList = new IComponent[5];
    private final CarBuilder carBuilder = new CarBuilder();
    private final CarTypeFactory carTypeFactory = new CarTypeFactory();
    private final EngineFactory engineFactory = new EngineFactory();
    private final BrakeFactory brakeFactory = new BrakeFactory();
    private final SteeringFactory steeringFactory = new SteeringFactory();


    public void run() {
        Scanner sc = new Scanner(System.in);
        int makingStep = CAR_TYPE_IDX;
        while (true) {
            clearConsole();
            showMenuItemEachMakingStep(makingStep);

            String inputMenuItem = sc.nextLine().trim();
            if (isCmdExit(inputMenuItem)) break;

            Integer selectMenuNumber = parseSelectMenuNumber(inputMenuItem);
            if (selectMenuNumber == null) continue;

            if (!isValidMenuItem(makingStep, selectMenuNumber)) continue;

            makingStep = doMakingStepBySelected(makingStep, selectMenuNumber);
        }
        sc.close();
    }

    private void clearConsole() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    private void showMenuItemEachMakingStep(int makingStep) {
        switch (makingStep) {
            case CAR_TYPE_IDX:
                showCarTypeMenuItem();
                break;
            case ENGINE_IDX:
                showEngineMenuItem();
                break;
            case BRAKE_SYSTEM_IDX:
                showBrakeMenuItem();
                break;
            case STEERING_SYSTEM_IDX:
                showSteeringMenuItem();
                break;
            case RUN_TEST_IDX:
                showRunTestMenuItem();
                break;
        }
        System.out.print("INPUT > ");
    }


    private void showCarTypeMenuItem() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");

        List<String> availableList = carTypeFactory.getAvailableList();
        for (int i = 0; i < availableList.size(); i++) {
            System.out.println(i + 1 + ". " + availableList.get(i));
        }
        System.out.println("===============================");
    }

    private void showEngineMenuItem() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");

        List<String> availableList = engineFactory.getAvailableList();
        for (int i = 0; i < availableList.size(); i++) {
            System.out.println(i + 1 + ". " + availableList.get(i));
        }
        System.out.println("===============================");
    }

    private void showBrakeMenuItem() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        List<String> availableList = brakeFactory.getAvailableList();
        for (int i = 0; i < availableList.size(); i++) {
            System.out.println(i + 1 + ". " + availableList.get(i));
        }
        System.out.println("===============================");
    }

    private void showSteeringMenuItem() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        List<String> availableList = steeringFactory.getAvailableList();
        for (int i = 0; i < availableList.size(); i++) {
            System.out.println(i + 1 + ". " + availableList.get(i));
        }
        System.out.println("===============================");
    }

    private void showRunTestMenuItem() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private boolean isCmdExit(String inputMenuItem) {
        if (inputMenuItem.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }

    public Integer parseSelectMenuNumber(String inputMenuItem) {
        int selectMenuNumber;
        try {
            selectMenuNumber = Integer.parseInt(inputMenuItem);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            delayRunTest(800);
            return null;
        }
        return selectMenuNumber;
    }

    public boolean isValidMenuItem(int makingStep, int selectMenuNumber) {
        if (!isValidRange(makingStep, selectMenuNumber)) {
            delayRunTest(800);
            return false;
        }
        return true;
    }

    private boolean isValidRange(int makingStep, int selected) {
        int validRange = 0;
        switch (makingStep) {
            case CAR_TYPE_IDX:
                validRange = carTypeFactory.getAvailableList().size();
                if (selected < 1 || selected > validRange) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ " + validRange + " 범위만 선택 가능");
                    return false;
                }
                break;
            case ENGINE_IDX:
                validRange = engineFactory.getAvailableList().size();
                if (selected < 0 || selected > validRange) {
                    System.out.println("ERROR :: 엔진은 1 ~ " + validRange + " 범위만 선택 가능");
                    return false;
                }
                break;
            case BRAKE_SYSTEM_IDX:
                validRange = brakeFactory.getAvailableList().size();
                if (selected < 0 || selected > validRange) {
                    System.out.println("ERROR :: 제동장치는 1 ~ " + validRange + " 범위만 선택 가능");
                    return false;
                }
                break;
            case STEERING_SYSTEM_IDX:
                validRange = steeringFactory.getAvailableList().size();
                if (selected < 0 || selected > validRange) {
                    System.out.println("ERROR :: 조향장치는 1 ~ " + validRange + " 범위만 선택 가능");
                    return false;
                }
                break;
            case RUN_TEST_IDX:
                if (selected < 0 || selected > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }


    public int doMakingStepBySelected(int makingStep, Integer selectMenuNumber) {
        if (makingStep == RUN_TEST_IDX) return selectRunTest(makingStep, selectMenuNumber);
        if (makingStep > CAR_TYPE_IDX && selectMenuNumber == UNDO_OR_RESET) return makingStep - 1;
        else if (makingStep == CAR_TYPE_IDX) selectCarType(selectMenuNumber);
        else if (makingStep == ENGINE_IDX) selectEngine(selectMenuNumber);
        else if (makingStep == BRAKE_SYSTEM_IDX) selectBrakeSystem(selectMenuNumber);
        else if (makingStep == STEERING_SYSTEM_IDX) selectSteeringSystem(selectMenuNumber);
        delayRunTest(800);
        return nextMakingStep(makingStep);
    }

    public int selectRunTest(int makingStep, Integer selectMenuNumber) {
        if (selectMenuNumber == UNDO_OR_RESET) makingStep = CAR_TYPE_IDX;
            //build
        else if (selectMenuNumber == RUN_PRODUCE_CAR) runProducedCar();
        else if (selectMenuNumber == TEST_PRODUCE_CAR) testProducedCar();
        delayRunTest(2000);
        return makingStep;
    }

    private void runProducedCar() {
        if (!isCombinationValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (isBrokenEngine()) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }
        printCarComponents();
    }

    private boolean isBrokenEngine() {
        return carComponentList[ENGINE_IDX].getNameIdx() == BROKEN_ENGINE;
    }

    private void printCarComponents() {
        System.out.printf("Car Type : %s\n", carTypeFactory.getAvailableList().get(carComponentList[CAR_TYPE_IDX].getNameIdx()));
        System.out.printf("Engine   : %s\n", engineFactory.getAvailableList().get(carComponentList[ENGINE_IDX].getNameIdx()));
        System.out.printf("Brake    : %s\n", brakeFactory.getAvailableList().get(carComponentList[BRAKE_SYSTEM_IDX].getNameIdx()));
        System.out.printf("Steering : %s\n", steeringFactory.getAvailableList().get(carComponentList[STEERING_SYSTEM_IDX].getNameIdx()));
        System.out.println("자동차가 동작됩니다.");
    }

    private void testProducedCar() {
        int carTypeNum = carComponentList[CAR_TYPE_IDX].getNameIdx() + 1;
        int engineNum = carComponentList[ENGINE_IDX].getNameIdx() + 1;
        int brakeNum = carComponentList[BRAKE_SYSTEM_IDX].getNameIdx() + 1;
        int steeringNum = carComponentList[STEERING_SYSTEM_IDX].getNameIdx() + 1;

        System.out.println("Test...");
        delayRunTest(1500);
        if (carTypeNum == SEDAN && brakeNum == CONTINENTAL) {
            failRunTest("Sedan에는 Continental제동장치 사용 불가");
        } else if (carTypeNum == SUV && engineNum == TOYOTA) {
            failRunTest("SUV에는 TOYOTA엔진 사용 불가");
        } else if (carTypeNum == TRUCK && engineNum == WIA) {
            failRunTest("Truck에는 WIA엔진 사용 불가");
        } else if (carTypeNum == TRUCK && brakeNum == MANDO) {
            failRunTest("Truck에는 Mando제동장치 사용 불가");
        } else if (brakeNum == BOSCH_B && steeringNum != BOSCH_S) {
            failRunTest("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    public boolean isCombinationValidCheck() {
        int carTypeNum = carComponentList[CAR_TYPE_IDX].getNameIdx() + 1;
        int engineNum = carComponentList[ENGINE_IDX].getNameIdx() + 1;
        int brakeNum = carComponentList[BRAKE_SYSTEM_IDX].getNameIdx() + 1;
        int steeringNum = carComponentList[STEERING_SYSTEM_IDX].getNameIdx() + 1;

        if (carTypeNum == SEDAN && brakeNum == CONTINENTAL) return false;
        if (carTypeNum == SUV && engineNum == TOYOTA) return false;
        if (carTypeNum == TRUCK && engineNum == WIA) return false;
        if (carTypeNum == TRUCK && brakeNum == MANDO) return false;
        if (brakeNum == BOSCH_B && steeringNum != BOSCH_S) return false;
        return true;
    }

    private void selectCarType(int carTypeNum) {
        int carTypeIdx = carTypeNum - 1;
        carComponentList[CAR_TYPE_IDX] = carTypeFactory.getComponent(carTypeIdx);
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", carTypeFactory.getAvailableList().get(carTypeIdx));
    }


    private void selectEngine(int engineNum) {
        int engineIdx = engineNum - 1;
        carComponentList[ENGINE_IDX] = engineFactory.getComponent(engineIdx);
        System.out.printf("%s 엔진을 선택하셨습니다.\n", engineFactory.getAvailableList().get(engineIdx));
    }

    private void selectBrakeSystem(int brakeNum) {
        int brakeIdx = brakeNum - 1;
        carComponentList[BRAKE_SYSTEM_IDX] = brakeFactory.getComponent(brakeIdx);
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", brakeFactory.getAvailableList().get(brakeIdx));
    }

    private void selectSteeringSystem(int steeringNum) {
        int steeringIdx = steeringNum - 1;
        carComponentList[STEERING_SYSTEM_IDX] = steeringFactory.getComponent(steeringIdx);
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", steeringFactory.getAvailableList().get(steeringIdx));
    }

    private int nextMakingStep(int currentStep) {
        return currentStep + 1;
    }


    private void failRunTest(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }


    private void delayRunTest(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
