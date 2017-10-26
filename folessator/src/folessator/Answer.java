package folessator;

public enum Answer {
	YES, NO, MAYBE,ABORT,UNKNOWN;
	
	public static Answer convert(String str) {
		str=str.toLowerCase();
		switch (str) {
		case "yes":
			return YES;
		case "no":
			return NO;
		case "maybe":
			return MAYBE;
		case "abort":
			return ABORT;
		default:
			return UNKNOWN;
		}
	}
}
