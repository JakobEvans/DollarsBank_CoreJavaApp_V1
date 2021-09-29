package com.cognixia.jump.utility;

public class ConsolePrinterUtility {

	ColorsUtility colors = ColorsUtility.getInstance();

	private String optionsCreateAccount[] = { "Customer Name:", "Customer Address:", "Customer Contact Number:",
			"Username:", "Password: 8 characters with Lower, Upper, & Special" };

	private String optionsSignedIn[] = { "Deposit Amount:", "Withdraw Amount:", "Funds Transfer:",
			"View 5 Recent Transactions", "Display Customer Information:", "Sign Out" };

	public static void main() {

	}

	private static ConsolePrinterUtility single_instance = null;

	private ConsolePrinterUtility() {

	}

	public void printMainMenu() {

		System.out.println("\nWhat would you like to do?\n(1) Create New Account\n(2) Login\n(3) Exit\n");

	}

	public String[] getNewAccountOptions() {

		return optionsCreateAccount;

	}

	public void printChoices(String[] choices) {
		for (int i = 0; i < choices.length; i++) {
			System.out.println("(" + (i + 1) + ") " + choices[i]);

		}
	}

	public void enterChoice(int numChoices) {
		System.out.println();
		System.out.printf(ColorsUtility.ANSI_GREEN + "Enter Choice (");
		for (int i = 1; i < numChoices; i++) {
			if (i != numChoices - 1) {
				System.out.print(i + ",");

			} else {
				System.out.print(i);

			}

		}

		System.out.printf(" or " + numChoices + ")");
		System.out.println(ColorsUtility.ANSI_RESET);
	}

	// can only print one line ,will improve for multi-line strings
	public void printFormattedTextBox(String str) {

		System.out.printf(ColorsUtility.ANSI_BLUE + "+");

		for (int i = 0; i < str.length() + 2; i++) {

			System.out.printf("-");
		}
		System.out.println("+");

		System.out.printf("| " + str);

		System.out.println(" |");

		System.out.printf("+");

		for (int i = 0; i < str.length() + 2; i++) {

			System.out.printf("-");
		}
		System.out.println("+");

		System.out.printf(ColorsUtility.ANSI_RESET);

	}

	public static ConsolePrinterUtility getInstance() {
		if (single_instance == null) {

			single_instance = new ConsolePrinterUtility();
		}
		return single_instance;

	}

	public void successMessage(String string) {
		System.out.println();

		System.out.printf(ColorsUtility.ANSI_GREEN + string + ColorsUtility.ANSI_GREEN);
		System.out.printf(ColorsUtility.ANSI_BLACK);

		System.out.println();
		System.out.println(ColorsUtility.ANSI_RESET);

	}

	public void printError(String string) {
		System.out.println();
		System.out.printf(ColorsUtility.ANSI_RED + string + ColorsUtility.ANSI_RED);
		System.out.printf(ColorsUtility.ANSI_BLACK);

		System.out.println();
		System.out.println(ColorsUtility.ANSI_RESET);

	}

	public String[] getOptionsSignedIn() {
		return optionsSignedIn;
	}

	public void setOptionsSignedIn(String optionsSignedIn[]) {
		this.optionsSignedIn = optionsSignedIn;
	}

}
