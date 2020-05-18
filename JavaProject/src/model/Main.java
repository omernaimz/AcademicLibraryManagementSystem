package model;
public class Main {

	public static void main(String[] args) {

		Error error = new Error("test error 1 2 3");
		Error error2 = new Error("shit");
		Error error3 = new Error("Oh No!");
		error.NewError();
		error2.NewError();
		error3.NewError();

	}

}
