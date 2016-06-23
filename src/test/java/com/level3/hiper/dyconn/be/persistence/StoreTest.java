/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.level3.hiper.dyconn.be.persistence;

import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zendle.joe
 */
public class StoreTest {

	static CircuitOidStore store = CircuitOidStore.instance();

	public StoreTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		System.out.println("setUpClass");
		store.init("circuitOid.db");
		store.clear();

	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("tearDownClass");
		store.close();

	}

	@Before
	public void setUp() {
		System.out.println("setUp");
		store.clear();
	}

	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	public void addCircuitOids() {
		System.out.println("addCircuitOids");
		CircuitKey key = new CircuitKey();
		key.setCircuitId("24/VLXX/123456/TWCS");
		key.setDevice("ash1-er1");

		String oid1 = "1.6.1.7.1.2.1.1";
		String oid2 = "1.6.1.7.1.2.1.2";
		String oid3 = "1.6.1.7.1.2.1.3";

		store.addOidForCircuitKey(key, oid1);
		store.addOidForCircuitKey(key, oid2);
		store.addOidForCircuitKey(key, oid3);

		key = new CircuitKey();
		key.setCircuitId("25/VLXX/123456/TWCS");
		key.setDevice("ash1-er1");

		store.addOidForCircuitKey(key, oid1);
		store.addOidForCircuitKey(key, oid2);

		store.dump();

		System.out.println("addDevices done");

	}

	@Test
	public void attemptToAddExisting() {
		System.out.println("attemptToAddExisting");

		addCircuitOids();
		CircuitKey key = new CircuitKey();
		key.setCircuitId("24/VLXX/123456/TWCS");
		key.setDevice("ash1-er1");

		String oid1 = "1.6.1.7.1.2.1.1";

		boolean ret = store.addOidForCircuitKey(key, oid1);

		assertFalse(ret);

	}

	@Test
	public void retrieveExisting() {
		System.out.println("retrieveExisting");

		addCircuitOids();
		CircuitKey key = new CircuitKey();
		key.setCircuitId("24/VLXX/123456/TWCS");
		key.setDevice("ash1-er1");

		Collection<String> ret = store.getOidsForCircuitKey(key);
		System.out.println("oids: " + ret);
		assertEquals(ret.size(), 3);
		
		System.out.println("after retrieve:");
		store.dump();

	}
	@Test
	public void deleteExisting() {
		System.out.println("deleteExisting");

		addCircuitOids();
		CircuitKey key = new CircuitKey();
		key.setCircuitId("24/VLXX/123456/TWCS");
		key.setDevice("ash1-er1");

		Boolean ret = store.deleteOidsForCircuitKey(key);
		System.out.println("oids: " + ret);
		assertTrue(ret);
		
		System.out.println("after delete:");
		store.dump();

	}

	/*
   @Test
   public void deleteByDevice() {
      System.out.println("deleteByDevice");

      addCircuitOids();
      Boolean ret = store.deleteByDeviceName("dev1");

      System.out.println("after deleting by device");
      store.dump();

      assertTrue(ret);

   }
   
   @Test
   public void deleteByDevice2() {
      System.out.println("deleteByDevice2");

      addDevices();
      Boolean ret = store.deleteByDeviceName("dev2");

      System.out.println("after deleting by device2");
      store.dump();

      assertTrue(ret);

   }

   @Test
   public void deleteByCircuit() {
      System.out.println("deleteByCircuit");

      addDevices();
      Boolean ret = store.deleteByCircuitId("circ1");

      System.out.println("after deleting by circ1");
      store.dump();

      assertTrue(ret);

   }
	 */
}
