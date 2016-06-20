/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.level3.hiper.dyconn.be;

import com.level3.hiper.dyconn.config.Config;
import com.level3.hiper.dyconn.messaging.Broker;
import com.level3.hiper.dyconn.messaging.MsgReceiver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zendle.joe
 */
public class Main {

   private static final Logger log = LoggerFactory.getLogger(Main.class);

   public static void main(String... args) {

      try {
         String bootstrap = "/dyconn-be-toml.cfg";
         CommandLineParser parser = new DefaultParser();
         Options options = new Options();
         options.addOption("c", "config-file", true, "configuration for hapi dyconn module");
         try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("config-file")) {
               bootstrap = line.getOptionValue("config-file");
            }
         } catch (ParseException ex) {
            log.error("command line", ex);
            return;
         }

         // read config file
         log.info("loading configuration");
         Config.instance().initialize(bootstrap);

         // initialize queue subsystem
         log.info("initializing messaging");
         Broker.instance().initialize();

         // initilaize persistence
         log.info("starting exector");
         ExecutorService executor = Executors.newSingleThreadExecutor();
         executor.submit(new MsgReceiver());
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
