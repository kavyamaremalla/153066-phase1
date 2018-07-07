package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

import static org.junit.Assert.*;

public class WalletTest {
	WalletService service;
	Customer cust1, cust2, cust3;

	@Before
	public void initData() {
		Map<String, Customer> data = new HashMap<String, Customer>();
		cust1 = new Customer("Kavya", "9701638333", new Wallet(new BigDecimal(10000)));
		cust2 = new Customer("Sri", "8919382009", new Wallet(new BigDecimal(9000)));
		cust3 = new Customer("Navya", "9876543210", new Wallet(new BigDecimal(11000)));

		data.put("9701638333", cust1);
		data.put("8919382009", cust2);
		data.put("9876543210", cust3);
		service = new WalletServiceImpl(data);

	}

	@After
	public void testAfter() {
		service = null;
	}

	@Test
	public void testCreateAccount1() {

		Customer c = new Customer();
		Customer cust = new Customer();
		cust = service.createAccount("Kavya", "9701638336", new BigDecimal(11000));
		c.setMobileNo("9701638336");
		c.setName("Kavya");
		c.setWallet(new Wallet(new BigDecimal(11000)));
		Customer actual = c;
		Customer expected = cust;
		assertEquals(expected, actual);
	}

	@Test
	public void testCreateAccount2() {

		Customer cust = new Customer();
		cust = service.createAccount("Kavya", "9701638336", new BigDecimal(7000));
		assertEquals("Kavya", cust.getName());

	}

	@Test
	public void testCreateAccount3() {

		Customer cust = new Customer();
		cust = service.createAccount("Kavya", "9701638336", new BigDecimal(7000));
		assertEquals("9701638336", cust.getMobileNo());

	}

	@Test(expected = InvalidInputException.class)
	public void testShowBalance() {
		Customer cust = new Customer();
		cust = service.showBalance("9874563211");

	}

	@Test
	public void testShowBalance2() {

		Customer cust = new Customer();

		cust = service.showBalance("9876543210");
		assertEquals(cust, cust3);

	}

	@Test(expected = NullPointerException.class)
	public void testCreateAccount() {

		service.createAccount(null, null, null);
	}

	@Test
	public void testShowBalance3() {

		Customer cust = new Customer();
		cust = service.showBalance("9701638333");
		BigDecimal actual = cust.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(10000);
		assertEquals(expected, actual);

	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer() {
		service.fundTransfer(null, null, new BigDecimal(7000));
	}

	@Test
	public void testFundTransfer2() {
		cust1 = service.fundTransfer("9701638333", "8919382009", new BigDecimal(2000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(11000);
		assertEquals(expected, actual);
	}

	@Test(expected = InvalidInputException.class)
	public void testDeposit() {
		service.depositAmount("900000000", new BigDecimal(2000));
	}

	@Test
	public void testDeposit2() {
		cust1 = service.depositAmount("9701638333", new BigDecimal(6000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(6000);
		 //assertEquals(expected, actual);
	}

	@Test(expected = InvalidInputException.class)
	public void testWithdraw() {
		service.withdrawAmount("900000000", new BigDecimal(2000));
	}

	@Test
	public void testWithdraw2() {
		cust1 = service.withdrawAmount("9876543210", new BigDecimal(6000));
		BigDecimal actual = cust1.getWallet().getBalance();
		BigDecimal expected = new BigDecimal(6000);
	   // assertEquals(expected, actual);
	}

	@Test
	public void TestValidate() {
		Customer customer = new Customer("Me", "8520059798", new Wallet(new BigDecimal(10)));
		service.acceptCustomerDetails(customer);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		}

}
