package interfaces;


import Models.*;
public interface IToohakGame {

	/**
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	void registerPlayer(String username, String password, String email);

	/**
	 * 
	 * @param useremail
	 * @param password
	 */
	boolean login(String useremail, String password);

	/**
	 * 
	 * @param host
	 */
	void chooseHostOrClient(boolean host);

	/**
	 * 
	 * @param lobby
	 */
	void selectLobby(Lobby lobby);

	void refreshLobbies();


	void createLobby();

	/**
	 * 
	 * @param question
	 */
	void answerQuestion(Question question);

}