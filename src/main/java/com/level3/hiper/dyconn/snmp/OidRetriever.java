/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.level3.hiper.dyconn.snmp;

import com.level3.hiper.dyconn.be.persistence.CircuitKey;
import com.level3.hiper.dyconn.be.persistence.CircuitOidStore;
import java.util.Collection;
import java.util.HashSet;
import org.omg.CORBA.Current;

/**
 *
 * @author jzendle
 */
public class OidRetriever implements Runnable {

	CircuitKey key; // circuitId, device

	Collection<String> oids = new HashSet<>();

	CircuitOidStore store = CircuitOidStore.instance();

	public OidRetriever(CircuitKey key) {
		this.key = key;
	}

	@Override
	public void run() {

		try {
			// go off and discover relevant oids to poll
			Thread.sleep(2000);
			oids.add("1.1.6.1.1.1");
			oids.add("1.1.6.1.1.2");
			oids.add("1.1.6.1.1.3");
			store.addOidsForCircuitKey(key, oids);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
		}

	}

}
