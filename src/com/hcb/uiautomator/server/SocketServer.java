/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hcb.uiautomator.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import com.android.uiautomator.common.UiWatchers;
import com.hcb.uiautomator.utils.Logger;
import com.hcb.uiautomator.utils.TheWatchers;
import com.hcb.uiautomator.utils.WDStatus;

/**
 * The SocketServer class listens on a specific port for commands from Appium,
 * and then passes them on to the {@link AndroidCommandExecutor} class. It will
 * continue to listen until the command is sent to exit.
 */
class SocketServer {

  ServerSocket   server;
  Socket         client;
  BufferedReader in;
  BufferedWriter out;
  boolean        keepListening;
  private final AndroidCommandExecutor executor;
  private final TheWatchers watchers = TheWatchers.getInstance();
  private final Timer       timer    = new Timer("WatchTimer");
  /**
   * Constructor
   *
   * @param port
   * @throws Exception
   */
  public SocketServer(int port) throws Exception{
    keepListening = true;
    executor = new AndroidCommandExecutor();
    try {
      server = new ServerSocket(port);
      Logger.debug("Socket opened on port " + port);
    } catch (final IOException e) {
      throw new Exception(
          "Could not start socket server listening on " + port);
    }

  }

  /**
   * Constructs an @{link AndroidCommand} and returns it.
   *
   * @param data
   * @return @{link AndroidCommand}
   * @throws JSONException
   * @throws Exception
   */
  private PraseCommand getCommand(final String data) throws JSONException,
      Exception {
    return new PraseCommand(data);
  }

  private StringBuilder input = new StringBuilder();

  /**
   * When data is available on the socket, this method is called to run the
   * command or throw an error if it can't.
   *
   * @throws Exception
   */
  private void handleClientData() throws Exception {
    try {
      input.setLength(0); // clear
      String res;
      int a;
      while ((a = in.read()) != -1 && in.ready()) {
        input.append((char) a);
      }
      String inputString = input.toString();
      Logger.debug("Got data from client: " + inputString);
      try {
    	  PraseCommand cmd = getCommand(inputString+"}");
    	  Logger.debug("EXECUTE COMMAND:"+cmd.getType());
    	  res = runCommand(cmd);
    	  Logger.debug("Returning result: " + res);
      } catch (final Exception e) {
    	  res = new ActionResult(WDStatus.UNKNOWN_ERROR, e.getMessage())
            .toString();
      }
      out.write(res);
      out.flush();
    } catch (final IOException e) {
      throw new Exception("Error processing data to/from socket ("
          + e.toString() + ")");
    }
  }

  /**
   * Listens on the socket for data, and calls {@link #handleClientData()} when
   * it's available.
   *
   * @throws Exception
   */
  public void listenForever(boolean disableAndroidWatchers, boolean acceptSSLCerts) throws Exception {
	  Logger.debug("UiAutoMator Socket Server is Ready Now");
    if (disableAndroidWatchers) {
      Logger.debug("Skipped registering crash watchers.");
    } else {
      dismissCrashAlerts();

      final TimerTask updateWatchers = new TimerTask() {
        @Override
        public void run() {
          try {
            watchers.check();
          } catch (final Exception e) {
          }
        }
      };
      timer.scheduleAtFixedRate(updateWatchers, 100, 100);
    }

    if (acceptSSLCerts) {
      Logger.debug("Accepting SSL certificate errors.");
      acceptSSLCertificates();
    }

    try {
      client = server.accept();
      Logger.debug("Client connected");
      in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
      out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
      while (keepListening) {
    	  handleClientData();
      }
      in.close();
      out.close();
      client.close();
      Logger.debug("Closed client connection");
    } catch (final IOException e) {
      throw new Exception("Error when client was trying to connect");
    }
  }

  public void dismissCrashAlerts() {
    try {
      new UiWatchers().registerAnrAndCrashWatchers();
      Logger.debug("Registered crash watchers.");
    } catch (Exception e) {
      Logger.debug("Unable to register crash watchers.");
    }
  }

  public void acceptSSLCertificates() {
    try {
      new UiWatchers().registerAcceptSSLCertWatcher();
      Logger.debug("Registered SSL certificate error watcher.");
    } catch (Exception e) {
      Logger.debug("Unable to register SSL certificate error watcher.");
    }
  }

  /**
   * When {@link #handleClientData()} has valid data, this method delegates the
   * command.
   *
   * @param cmd
   *     AndroidCommand
   * @return Result
   */
  private String runCommand(final PraseCommand cmd) {
    ActionResult res;
      try {
        res = executor.execute(cmd);
      } catch (final NoSuchElementException e) {
         res = new ActionResult(WDStatus.NO_SUCH_ELEMENT, e.getMessage());
      } catch (final Exception e) {
        Logger.debug("Command returned error:" + e);
        res = new ActionResult(WDStatus.UNKNOWN_ERROR, e.getMessage());
      }
      return res.toString();
    }
   
}
