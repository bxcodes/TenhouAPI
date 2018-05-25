package com.bxcodes.java.tenhouapi;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TenhouClient {

	public static TenhouClient getClient() {
		try {
			TenhouClient client = new TenhouClient();
			return client;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void sendString(String s) throws IOException {
		osw.write(s);
		osw.flush();
	}

	private TenhouClient() throws IOException {
		socket = new Socket("133.242.10.78", 10080);
		reader = new TenhouStreamReader(socket.getInputStream());

		osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println(reader.nextLine());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
				}

			}
		}).start();
	}

	private Socket socket;
	private OutputStreamWriter osw;
	private TenhouStreamReader reader;
}
