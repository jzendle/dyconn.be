/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.level3.hiper.dyconn.be.persistence;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author zendle.joe
 */
public class CircuitKey implements Serializable, Comparable<CircuitKey> {

	private String device;
	private String circuitId;

	public CircuitKey() {
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(String circuitId) {
		this.circuitId = circuitId;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + Objects.hashCode(this.device);
		hash = 23 * hash + Objects.hashCode(this.circuitId);
		return hash;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final CircuitKey other = (CircuitKey) obj;
		if (!Objects.equals(this.device, other.device)) {
			return false;
		}
		if (!Objects.equals(this.circuitId, other.circuitId)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(CircuitKey other) {
		int first = circuitId.compareTo(other.circuitId);
		return first != 0 ? first : device.compareTo(other.device);
	}

	@Override
	public String toString() {
		return "CircuitKey{" + "device=" + device + ", circuitId=" + circuitId + '}';
	}
	
	

}
