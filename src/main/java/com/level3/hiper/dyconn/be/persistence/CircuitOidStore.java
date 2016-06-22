/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.level3.hiper.dyconn.be.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NavigableSet;
import org.mapdb.BTreeKeySerializer;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Fun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zendle.joe
 */
public class CircuitOidStore {

   private static final Logger log = LoggerFactory.getLogger(CircuitOidStore.class);

   private NavigableSet<Fun.Tuple2<CircuitKey, String>> circuitOidMultiMap = null;

   private static final String CIRCUIT_OID_MAP = "circuitOidMap";

   static CircuitOidStore instance = null;

   private DB db;

   public static CircuitOidStore instance() {
      if (instance == null) {
         instance = new CircuitOidStore();
      }
      return instance;
   }

   public void init(String file) throws IllegalArgumentException {

      log.info("accessing db file: " + file);
      db = DBMaker.newFileDB(new File(file)).closeOnJvmShutdown().make();

      if (db == null) {
         throw new IllegalArgumentException("unable to open db file: " + CIRCUIT_OID_MAP);
      }

      circuitOidMultiMap = (db.exists(CIRCUIT_OID_MAP) ? db.getTreeSet(CIRCUIT_OID_MAP)
              : db.createTreeSet(CIRCUIT_OID_MAP).serializer(BTreeKeySerializer.TUPLE2).make());
   }

   public Boolean addOidForCircuitKey(CircuitKey key, String oid) {

      boolean ret = false;
      // TODO check duplicate?
      if (circuitOidMultiMap.contains(Fun.t2(key, oid))) {
         log.debug("map already contains entry for key:'{}' value:'{}'", key, oid);
      } else if (circuitOidMultiMap.add(Fun.t2(key, oid))) {
         db.commit();
         ret = true;
      }

      return ret;

   }
     
   // TODO
   public Collection<String> getOidsForCircuitKey(CircuitKey key, String oid) {

      Collection<String> ret = new ArrayList<>();
      
      return ret;

   }

}
