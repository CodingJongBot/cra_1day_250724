package mission1;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CAR_TYPE_IDX = 0;
    private static final int ENGINE_IDX = 1;
    private static final int BRAKE_SYSTEM_IDX = 2;
    private static final int STEERING_SYSTEM_IDX = 3;
    private static final int RUN_TEST_IDX = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;
    public static final int RUN_PRODUCE_CAR = 1;
    public static final int TEST_PRODUCE_CAR = 2;
    public static final int BROKEN_ENGINE = 4;
    public static final int UNDO_OR_RESET = 0;

    private static int[] carComponentList = new int[5];

    public static void main(String[] args) {
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

    private static void clearConsole() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    private static void showMenuItemEachMakingStep(int makingStep) {
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


    //TODO 확장성 고려
    private static void showCarTypeMenuItem() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }

    private static void showEngineMenuItem() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }

    private static void showBrakeMenuItem() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }

    private static void showSteeringMenuItem() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    private static void showRunTestMenuItem() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private static boolean isCmdExit(String inputMenuItem) {
        if (inputMenuItem.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;

    }

    private static Integer parseSelectMenuNumber(String inputMenuItem) {
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

    private static boolean isValidMenuItem(int makingStep, int selectMenuNumber) {
        if (!isValidRange(makingStep, selectMenuNumber)) {
            delayRunTest(800);
            return false;
        }
        return true;
    }

    private static boolean isValidRange(int makingStep, int selected) {
        switch (makingStep) {
            case CAR_TYPE_IDX:
                if (selected < 1 || selected > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case ENGINE_IDX:
                if (selected < 0 || selected > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BRAKE_SYSTEM_IDX:
                if (selected < 0 || selected > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case STEERING_SYSTEM_IDX:
                if (selected < 0 || selected > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
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


    private static int doMakingStepBySelected(int makingStep, Integer selectMenuNumber) {
        if (makingStep == RUN_TEST_IDX) return selectRunTest(makingStep, selectMenuNumber);
        if (makingStep > CAR_TYPE_IDX && selectMenuNumber == UNDO_OR_RESET) return makingStep - 1;
        else if (makingStep == CAR_TYPE_IDX) selectCarType(selectMenuNumber);
        else if (makingStep == ENGINE_IDX) selectEngine(selectMenuNumber);
        else if (makingStep == BRAKE_SYSTEM_IDX) selectBrakeSystem(selectMenuNumber);
        else if (makingStep == STEERING_SYSTEM_IDX) selectSteeringSystem(selectMenuNumber);
        delayRunTest(800);
        return nextMakingStep(makingStep);
    }

    private static int selectRunTest(int makingStep, Integer selectMenuNumber) {
        if (selectMenuNumber == UNDO_OR_RESET) makingStep = CAR_TYPE_IDX;
        else if (selectMenuNumber == RUN_PRODUCE_CAR) runProducedCar();
        else if (selectMenuNumber == TEST_PRODUCE_CAR) testProducedCar();
        delayRunTest(2000);
        return makingStep;
    }

    private static void runProducedCar() {
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

    private static boolean isBrokenEngine() {
        return carComponentList[ENGINE_IDX] == BROKEN_ENGINE;
    }

    private static void printCarComponents() {
        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        System.out.printf("Car Type : %s\n", carNames[carComponentList[CAR_TYPE_IDX]]);
        System.out.printf("Engine   : %s\n", engNames[carComponentList[ENGINE_IDX]]);
        System.out.printf("Brake    : %s\n", carComponentList[BRAKE_SYSTEM_IDX] == 1 ? "Mando" : carComponentList[BRAKE_SYSTEM_IDX] == 2 ? "Continental" : "Bosch");
        System.out.printf("Steering : %s\n", carComponentList[STEERING_SYSTEM_IDX] == 1 ? "Bosch" : "Mobis");
        System.out.println("자동차가 동작됩니다.");
    }

    private static void testProducedCar() {
        System.out.println("Test...");
        delayRunTest(1500);
        if (carComponentList[CAR_TYPE_IDX] == SEDAN && carComponentList[BRAKE_SYSTEM_IDX] == CONTINENTAL) {
            failRunTest("Sedan에는 Continental제동장치 사용 불가");
        } else if (carComponentList[CAR_TYPE_IDX] == SUV && carComponentList[ENGINE_IDX] == TOYOTA) {
            failRunTest("SUV에는 TOYOTA엔진 사용 불가");
        } else if (carComponentList[CAR_TYPE_IDX] == TRUCK && carComponentList[ENGINE_IDX] == WIA) {
            failRunTest("Truck에는 WIA엔진 사용 불가");
        } else if (carComponentList[CAR_TYPE_IDX] == TRUCK && carComponentList[BRAKE_SYSTEM_IDX] == MANDO) {
            failRunTest("Truck에는 Mando제동장치 사용 불가");
        } else if (carComponentList[BRAKE_SYSTEM_IDX] == BOSCH_B && carComponentList[STEERING_SYSTEM_IDX] != BOSCH_S) {
            failRunTest("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private static boolean isCombinationValidCheck() {
        if (carComponentList[CAR_TYPE_IDX] == SEDAN && carComponentList[BRAKE_SYSTEM_IDX] == CONTINENTAL) return false;
        if (carComponentList[CAR_TYPE_IDX] == SUV && carComponentList[ENGINE_IDX] == TOYOTA) return false;
        if (carComponentList[CAR_TYPE_IDX] == TRUCK && carComponentList[ENGINE_IDX] == WIA) return false;
        if (carComponentList[CAR_TYPE_IDX] == TRUCK && carComponentList[BRAKE_SYSTEM_IDX] == MANDO) return false;
        if (carComponentList[BRAKE_SYSTEM_IDX] == BOSCH_B && carComponentList[STEERING_SYSTEM_IDX] != BOSCH_S)
            return false;
        return true;
    }

    private static void selectCarType(int carTypeNum) {
        carComponentList[CAR_TYPE_IDX] = carTypeNum;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", carTypeNum == 1 ? "Sedan" : carTypeNum == 2 ? "SUV" : "Truck");
    }


    private static void selectEngine(int engineNum) {
        carComponentList[ENGINE_IDX] = engineNum;
        String manufacture = engineNum == 1 ? "GM" : engineNum == 2 ? "TOYOTA" : engineNum == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", manufacture);
    }

    private static void selectBrakeSystem(int brakeSystemNum) {
        carComponentList[BRAKE_SYSTEM_IDX] = brakeSystemNum;
        String manufacture = brakeSystemNum == 1 ? "MANDO" : brakeSystemNum == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", manufacture);
    }

    private static void selectSteeringSystem(int steeringNum) {
        carComponentList[STEERING_SYSTEM_IDX] = steeringNum;
        String manufacture = steeringNum == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", manufacture);
    }

    private static int nextMakingStep(int currentStep) {
        return currentStep + 1;
    }


    private static void failRunTest(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }


    private static void delayRunTest(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
