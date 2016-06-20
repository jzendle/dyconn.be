/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.level3.hiper.dyconn.messaging;

import java.util.Map;
import javax.jms.JMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zendle.joe
 */
public class MsgReceiver implements Runnable {

   Broker broker = Broker.instance();
   private static final Logger log = LoggerFactory.getLogger(MsgReceiver.class);
   
   @Override
   public void run() {
      
      try {
         while (true) {
            Map msg = broker.receive();
            log.debug("received msg '{}'" + msg);
         }
       }
      
      catch (JMSException e) {
         e.printStackTrace();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      catch (Throwable e) {
         e.printStackTrace();
                 
      }
   }
   
   
   
}
