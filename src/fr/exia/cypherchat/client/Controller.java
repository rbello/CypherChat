package fr.exia.cypherchat.client;

public class Controller implements ModelListener, ViewListener {

	private Model model;
	private ClientWindow view;

	public Controller(Model model, ClientWindow view) {
		this.model = model;
		this.view = view;
		
		model.addListener(this);
		view.addListener(this);
	}

	@Override
	public void onNicknameChanged(String newNickname) {
		// TODO Auto-generated method stub
		System.out.println("Nickname changed: " + newNickname);
	}

	@Override
	public void onMessageSent(String message) {
		// TODO Auto-generated method stub
		System.out.println("On envoie le message " + message);
	}

	@Override
	public void onCypherMethodChanged(String cypherMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onServerConnectionChanged(boolean status) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUserConnected(String nickname, String ip, boolean newConnection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUserDisconnected(String nickname, String ip) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMessageReceived(String nickname, String ip, String message) {
		// TODO Auto-generated method stub
		view.messageField.setText(view.messageField.getText() + "\n" + message);
	}

}
