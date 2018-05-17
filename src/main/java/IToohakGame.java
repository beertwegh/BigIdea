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
	void login(String useremail, String password);

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

	/**
	 * 
	 * @param level
	 */
	void createLobby(Level level);

	/**
	 * 
	 * @param question
	 */
	void answerQuestion(Question question);

}