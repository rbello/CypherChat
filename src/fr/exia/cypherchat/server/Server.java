package fr.exia.cypherchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

	private int port;
	private ServerSocket socket;
	private Thread acceptThread;
	
	private List<Client> connectedClients;

	public Server(int port) {
		if (port < 1 || port > 65535) {
			throw new IllegalArgumentException("Invalid port");
		}
		this.port = port;
		this.connectedClients = new ArrayList<>();
	}
	
	public void start() throws IOException {
		// On ouvre le socket sur le port donnée
		this.socket = new ServerSocket(this.port);
		// On fabrique un thread qui va boucler en permanence
		// et accepter les nouvelles connexions.
		this.acceptThread = new Thread(this);
		this.acceptThread.start();
		// Log
		System.out.println("[Server] Listening at port " + this.port);
	}

	@Override
	public void run() {
		// On boucle indéfiniement
		while (true) {
			try {
				// Cette méthode sert à attendre la connexion d'un
				// nouveau client. Elle bloquera jusqu'à l'arrivée
				// d'une connexion. Quand un client se connectera,
				// la méthode renverra le socket de connexion au
				// client.
				Socket s = socket.accept();
				// Arrivé ici, cela signifie qu'une connexion a été
				// reçue sur le port du serveur.
				System.out.println("[Server] Connection received from "
						+ s.getInetAddress());
				// Créer un objet pour réprésenter le client
				Client c = new Client(this, s);
				// On lance le thread qui se charge de lire les
				// données qui arrivent sur le socket.
				c.startPollingThread();
				// Je sauvegarde mon client maintenant qu'il est
				// bien initialisé.
				this.connectedClients.add(c);
			}
			catch (IOException e) {
				System.err.println("[Server] Client initialization error");
				e.printStackTrace();
			}
		}
	}

	public void onClientDisconnected(Client client) {
		// Log
		System.out.println("[Server][" + client.getSocket().getInetAddress()
			+ "] Client has just been disconnected");
	}

	public void onClientMessage(Client client, String message) {
		// Log
		System.out.println("[Server][" + client.getSocket().getInetAddress()
			+ "] Received message: " + message);
	}
	
	
	
	
	
}
