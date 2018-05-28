package client.host;

import Models.Question;
import Models.User;
import interfaces.IGame;

import java.util.ArrayList;

public class HostGame implements IGame {

	private ArrayList<Question> questions;
	private ArrayList<User> users;

}