import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.github.netmastermichael.AlgorithmSuiteX.Classic.SortingAlgorithms.SortingAlgorithmOperation;

class Test_SortingAlgorithmOperation {

	final String className = "Test_SortingAlgorithmOperation";
	
	@Test
	void testInitialiseSortingAlgorithmOperation_SWAP() {
		String testName = "testInitialiseSortingAlgorithmOperation_SWAP";
		AuxiliaryTestMethods.logMessage(className, testName + " started");
		try {
			SortingAlgorithmOperation testSAO = SortingAlgorithmOperation.SWAP;
			assertTrue(testSAO == SortingAlgorithmOperation.SWAP,
					"Test that a new SortingAlgorithmObject correctly initialises with operation SWAP");
			AuxiliaryTestMethods.logPass(className, testName);
		} catch (Exception e) {
			AuxiliaryTestMethods.logFail(className, testName);
			fail("Exception " + e + " thrown while testing creating a SortingAlgorithmOperation object of type SWAP; "
					+ e.getMessage());
		}
	}
	
	@Test
	void testInitialiseSortingAlgorithmOperation_COMPARE() {
		String testName = "testInitialiseSortingAlgorithmOperation_COMPARE";
		AuxiliaryTestMethods.logMessage(className, testName + " started");
		try {
			SortingAlgorithmOperation testSAO = SortingAlgorithmOperation.COMPARE;
			assertTrue(testSAO == SortingAlgorithmOperation.COMPARE,
					"Test that a new SortingAlgorithmObject correctly initialises with operation COMPARE");
			AuxiliaryTestMethods.logPass(className, testName);
		} catch (Exception e) {
			AuxiliaryTestMethods.logFail(className, testName);
			fail("Exception " + e + " thrown while testing creating a SortingAlgorithmOperation object of type COMPARE; "
					+ e.getMessage());
		}
	}
	
	@Test
	void testInitialiseSortingAlgorithmOperation_MOVE_LITERAL() {
		String testName = "testInitialiseSortingAlgorithmOperation_MOVE_LITERAL";
		AuxiliaryTestMethods.logMessage(className, testName + " started");
		try {
			SortingAlgorithmOperation testSAO = SortingAlgorithmOperation.MOVE_LITERAL;
			assertTrue(testSAO == SortingAlgorithmOperation.MOVE_LITERAL,
					"Test that a new SortingAlgorithmObject correctly initialises with operation MOVE_LITERAL");
			AuxiliaryTestMethods.logPass(className, testName);
		} catch (Exception e) {
			AuxiliaryTestMethods.logFail(className, testName);
			fail("Exception " + e + " thrown while testing creating a SortingAlgorithmOperation object of type MOVE_LITERAL; "
					+ e.getMessage());
		}
	}

}
