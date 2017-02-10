public class CaesarCipher {
	public static void main(String[] args) {
		String input = "abc!@#$DEFzZ";
		String indexInput = "26";
		String output = "";
		if (getIndex(indexInput) != -1) 
			output = caesar(input, getIndex(indexInput));
		System.out.println(output);
	}
	
	public static int getIndex(String input) {
		if (isInteger(input)) {
			int index = Integer.parseInt(input);
			if (index < 0)
				return -1;
			else if (index >= 0 && index <= 25)
				return index;
			else
				return (index % 26);
		}
		else {
			if (input.length() == 1) {
				int charInt = (int)input.charAt(0);
				if (charInt >= 65 && charInt <= 90)
					return (charInt - 65);
				else if (charInt >= 97 && charInt <= 122)
					return (charInt - 97);
				else 
					return -1;
			}
			else 
				return -1;
		}
	}
	
	private static boolean isInteger(String str) {
		return str.matches("^-?\\d+$");
	}
	
	public static String caesar(String input, int index) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			int charInt = (int)input.charAt(i);
			if ((charInt >= 97 && charInt <= 122)) {
				if ((charInt + index) > 122) {
					int tempIndex = charInt + index - 123;
					output.append((char)(97 + tempIndex));
				}
				else 
					output.append((char)(charInt + index));
			}
			else if ((charInt >= 65 && charInt <= 90)) {
				if ((charInt + index) > 90) {
					int tempIndex = charInt + index - 91;
					output.append((char)(65 + tempIndex));	
				}
				else
					output.append((char)(charInt + index));
			}
			else {
				output.append(' ');
			}
		}
		return output.toString();
	}
}