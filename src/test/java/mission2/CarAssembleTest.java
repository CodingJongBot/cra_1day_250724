package mission2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarAssembleTest {

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
    public static final int UNDIFINED_IDX = 6;

    private CarAssembleProcess carAssembleProcess;

    @BeforeEach
    void setUp() {
        carAssembleProcess = new CarAssembleProcess();
    }

    @Test
    void PASS_정상_ITEM_MENU_입력() {
        //Arrange
        String inputMenuItem = "1";
        Integer expected = 1;

        //Action
        Integer ret = carAssembleProcess.parseSelectMenuNumber(inputMenuItem);

        //Assert
        assertEquals(expected, ret);
    }


    @Test
    void FAIL_비정상_ITEM_MENU_입력() {
        //Arrange
        String inputMenuItem = "INVALID_INPUT";
        Integer expected = null;

        //Action
        Integer ret = carAssembleProcess.parseSelectMenuNumber(inputMenuItem);

        //Assert
        assertEquals(expected, ret);
    }

    @Test
    void PASS_CAR_TYPE에_적합한_ITEM_선택() {
        boolean expected = true;
        for (int selectMenuNumber = 1; selectMenuNumber < 4; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(CAR_TYPE_IDX, selectMenuNumber));
        }
        for (int selectMenuNumber = 1; selectMenuNumber < 5; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(ENGINE_IDX, selectMenuNumber));
        }
        for (int selectMenuNumber = 1; selectMenuNumber < 4; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(BRAKE_SYSTEM_IDX, selectMenuNumber));
        }
        for (int selectMenuNumber = 1; selectMenuNumber < 3; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(STEERING_SYSTEM_IDX, selectMenuNumber));
        }
        for (int selectMenuNumber = 1; selectMenuNumber < 3; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(RUN_TEST_IDX, selectMenuNumber));
        }
    }


    @Test
    void FAIL_CAR_TYPE에_적합하지_않은_ITEM_선택() {
        boolean expected = false;
        for (int selectMenuNumber = 4; selectMenuNumber < 5; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(CAR_TYPE_IDX, selectMenuNumber));
        }
        for (int selectMenuNumber = 5; selectMenuNumber < 6; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(ENGINE_IDX, selectMenuNumber));
        }

        for (int selectMenuNumber = 4; selectMenuNumber < 5; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(BRAKE_SYSTEM_IDX, selectMenuNumber));
        }
        for (int selectMenuNumber = 3; selectMenuNumber < 4; selectMenuNumber++) {
            assertEquals(expected, carAssembleProcess.isValidMenuItem(STEERING_SYSTEM_IDX, selectMenuNumber));
        }
        assertEquals(expected, carAssembleProcess.isValidMenuItem(RUN_TEST_IDX, 4));


        assertEquals(expected, carAssembleProcess.isValidMenuItem(CAR_TYPE_IDX, -1));
        assertEquals(expected, carAssembleProcess.isValidMenuItem(ENGINE_IDX, -1));
        assertEquals(expected, carAssembleProcess.isValidMenuItem(BRAKE_SYSTEM_IDX, -1));
        assertEquals(expected, carAssembleProcess.isValidMenuItem(STEERING_SYSTEM_IDX, -1));
        assertEquals(expected, carAssembleProcess.isValidMenuItem(RUN_TEST_IDX, -1));
    }


    @Test
    void PASS_RUN_TEST시_선택한_MENU_ITEM_0_MAKING_STEP_초기화() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, BOSCH_S);
        int ret = carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, UNDO_OR_RESET);
        assertEquals(CAR_TYPE_IDX, ret);
    }

    @Test
    void PASS_RUN_TEST시_선택한_MENU_ITEM_NO_0_MAKING_STEP_유지() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, BOSCH_S);
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }


    @Test
    void PASS_RUN_TEST도_CAR_TYPE_선택도_아닐시_선택한_MENU_ITEM이_0_MAKING_STEP_이전으로() {
        assertEquals(CAR_TYPE_IDX, carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, UNDO_OR_RESET));
        assertEquals(ENGINE_IDX, carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, UNDO_OR_RESET));
        assertEquals(BRAKE_SYSTEM_IDX, carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, UNDO_OR_RESET));
    }

    @Test
    void PASS_RUN_TEST_아닐시_선택한_MENU_ITEM이_0_아니고_MAKING_STEP_다음으로() {
        assertEquals(ENGINE_IDX, carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN));
        assertEquals(BRAKE_SYSTEM_IDX, carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA));
        assertEquals(STEERING_SYSTEM_IDX, carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS));
    }

    @Test
    void FAIL_정의되지_않은_MAKING_STEP_강제_입력시_변화_없음() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, BOSCH_S);
        carAssembleProcess.doMakingStepBySelected(UNDIFINED_IDX, MOBIS);
        assertTrue(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }


    @Test
    void PASS_제동장치_BOSCH_조향장치_BOSCH() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, BOSCH_S);
        assertTrue(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }

    @Test
    void FAIL_제동장치_BOSCH_조향장치_NO_BOSCH() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }


    @Test
    void FAIL_SEDAN_CONTINENTAL_부품_포함() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, CONTINENTAL);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }

    @Test
    void FAIL_SUV_TOYOTA_ENGINE_포함() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SUV);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, MANDO);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }

    @Test
    void PASS_SUV_NO_TOYOTA_ENGINE_포함() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SUV);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, WIA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, MANDO);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertTrue(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }


    @Test
    void FAIL_TRUCK_WIA_ENGINE_포함() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, TRUCK);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, WIA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, CONTINENTAL);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(carAssembleProcess.isCombinationValidCheck());

        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }

    @Test
    void PASS_TRUCK_NO_WIA_ENGINE_포함() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, TRUCK);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, CONTINENTAL);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertTrue(carAssembleProcess.isCombinationValidCheck());

        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }


    @Test
    void FAIL_TRUCK_MANDO_BRAKE_포함() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, TRUCK);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, MANDO);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }

    @Test
    void PASS_TRUCK_NO_MANDO_BRAKE_포함() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, TRUCK);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, CONTINENTAL);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertTrue(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }


    @Test
    void FAIL_BROKEN_ENGINE_포함() {
        carAssembleProcess.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        carAssembleProcess.doMakingStepBySelected(ENGINE_IDX, BROKEN_ENGINE);
        carAssembleProcess.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B);
        carAssembleProcess.doMakingStepBySelected(STEERING_SYSTEM_IDX, BOSCH_S);
        assertTrue(carAssembleProcess.isCombinationValidCheck());
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, carAssembleProcess.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }
}