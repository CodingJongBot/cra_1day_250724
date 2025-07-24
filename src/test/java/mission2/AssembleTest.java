package mission2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssembleTest {

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


    @Test
    void PASS_정상_ITEM_MENU_입력() {
        //Arrange
        String inputMenuItem = "1";
        Integer expected = 1;

        //Action
        Integer ret = Assemble.parseSelectMenuNumber(inputMenuItem);

        //Assert
        assertEquals(expected, ret);
    }


    @Test
    void FAIL_비정상_ITEM_MENU_입력() {
        //Arrange
        String inputMenuItem = "INVALID_INPUT";
        Integer expected = null;

        //Action
        Integer ret = Assemble.parseSelectMenuNumber(inputMenuItem);

        //Assert
        assertEquals(expected, ret);
    }

    @Test
    void PASS_CAR_TYPE에_적합한_ITEM_선택() {
        boolean expected = true;
        for (int selectMenuNumber = 1; selectMenuNumber < 4; selectMenuNumber++) {
            assertEquals(expected, Assemble.isValidMenuItem(CAR_TYPE_IDX, selectMenuNumber));
        }
    }


    @Test
    void FAIL_CAR_TYPE에_적합하지_않은_ITEM_선택() {
        boolean expected = false;
        for (int selectMenuNumber = 4; selectMenuNumber < 7; selectMenuNumber++) {
            assertEquals(expected, Assemble.isValidMenuItem(CAR_TYPE_IDX, selectMenuNumber));
        }
    }
//
//    @Test
//    void isValidRange() {
//    }


    @Test
    void PASS_RUN_TEST시_선택한_MENU_ITEM_0_MAKING_STEP_초기화() {
        int ret = Assemble.doMakingStepBySelected(RUN_TEST_IDX, UNDO_OR_RESET);
        assertEquals(CAR_TYPE_IDX, ret);
    }

    @Test
    void PASS_RUN_TEST시_선택한_MENU_ITEM_NO_0_MAKING_STEP_유지() {
        assertEquals(RUN_TEST_IDX, Assemble.doMakingStepBySelected(RUN_TEST_IDX, RUN_PRODUCE_CAR));
        assertEquals(RUN_TEST_IDX, Assemble.doMakingStepBySelected(RUN_TEST_IDX, TEST_PRODUCE_CAR));
    }


    @Test
    void PASS_RUN_TEST도_CAR_TYPE_선택도_아닐시_선택한_MENU_ITEM이_0_MAKING_STEP_이전으로() {
        assertEquals(CAR_TYPE_IDX, Assemble.doMakingStepBySelected(ENGINE_IDX, UNDO_OR_RESET));
        assertEquals(ENGINE_IDX, Assemble.doMakingStepBySelected(BRAKE_SYSTEM_IDX, UNDO_OR_RESET));
        assertEquals(BRAKE_SYSTEM_IDX, Assemble.doMakingStepBySelected(STEERING_SYSTEM_IDX, UNDO_OR_RESET));
    }

    @Test
    void PASS_RUN_TEST_아닐시_선택한_MENU_ITEM이_0_아니고_MAKING_STEP_다음으로() {
        assertEquals(ENGINE_IDX, Assemble.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN));
        assertEquals(BRAKE_SYSTEM_IDX, Assemble.doMakingStepBySelected(ENGINE_IDX, TOYOTA));
        assertEquals(STEERING_SYSTEM_IDX, Assemble.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B));
        assertEquals(RUN_TEST_IDX, Assemble.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS));
    }

    //    //mocking으로 내부 behavior 검증 가능
    //    @Test
    //    void selectRunTest() {
    //    }


    @Test
    void PASS_제동장치_BOSCH_조향장치_BOSCH() {
        Assemble.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        Assemble.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        Assemble.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B);
        Assemble.doMakingStepBySelected(STEERING_SYSTEM_IDX, BOSCH_S);
        assertTrue(Assemble.isCombinationValidCheck());
    }

    @Test
    void FAIL_제동장치_BOSCH_조향장치_NO_BOSCH() {
        Assemble.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        Assemble.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        Assemble.doMakingStepBySelected(BRAKE_SYSTEM_IDX, BOSCH_B);
        Assemble.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(Assemble.isCombinationValidCheck());
    }


    @Test
    void FAIL_SEDAN_CONTINENTAL_부품_포함() {
        Assemble.doMakingStepBySelected(CAR_TYPE_IDX, SEDAN);
        Assemble.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        Assemble.doMakingStepBySelected(BRAKE_SYSTEM_IDX, CONTINENTAL);
        Assemble.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(Assemble.isCombinationValidCheck());
    }

    @Test
    void FAIL_SUV_TOYOTA_ENGINE_포함() {
        Assemble.doMakingStepBySelected(CAR_TYPE_IDX, SUV);
        Assemble.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        Assemble.doMakingStepBySelected(BRAKE_SYSTEM_IDX, MANDO);
        Assemble.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(Assemble.isCombinationValidCheck());
    }

    @Test
    void FAIL_TRUCK_WIA_ENGINE_포함() {
        Assemble.doMakingStepBySelected(CAR_TYPE_IDX, TRUCK);
        Assemble.doMakingStepBySelected(ENGINE_IDX, WIA);
        Assemble.doMakingStepBySelected(BRAKE_SYSTEM_IDX, CONTINENTAL);
        Assemble.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(Assemble.isCombinationValidCheck());
    }

    @Test
    void FAIL_TRUCK_MANDO_BRAKE_포함() {
        Assemble.doMakingStepBySelected(CAR_TYPE_IDX, TRUCK);
        Assemble.doMakingStepBySelected(ENGINE_IDX, TOYOTA);
        Assemble.doMakingStepBySelected(BRAKE_SYSTEM_IDX, MANDO);
        Assemble.doMakingStepBySelected(STEERING_SYSTEM_IDX, MOBIS);
        assertFalse(Assemble.isCombinationValidCheck());
    }
}