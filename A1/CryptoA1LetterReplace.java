import java.util.*;

public class CryptoA1LetterReplace {
	
	public static final String STACK_POP = "pop";

	public static void main(String [] args) {
		 
		String s = "SFGESUAXXVUASVOOHKASUEGEOHDXNR"+
		"COCORMSHKGEOPARSHGOLGXFGEOFSCU"+
		"GRUUSKHWOHGSHXNCFRAAUOWOUGOCBC"+
		"DPGXKCRPEDBXNCUOGESURUUSKHWOHG"+
		"QRUPXUGOMXHGNOUMRDXHGEOOSKEGEW"+
		"XHGERHMXHGEOGESCGSOGEMRDSHGEOD"+
		"ORCGQXGEXNURHMRHMUSLGOOHRUDXNE"+
		"RZOMSUBXZOCOMFCXWBCRBVSHKGESUR"+
		"UUSKHWOHGSGQRUOHBCDPGOMNUSHKRU"+
		"SWPAOWXHXRAPERJOGSBUNJUGSGNGSX"+
		"HBSPEOCFXCGESURUUSKHWOHGUNJWSG"+
		"GEOFXAAXQSHKSGOWUJDOWRSAQSGESH"+
		"RWXHGEXFGEOCOAORUOMRGOXFGESURU"+
		"USKHWOHGFSCUGPCXZSMOGEOUNJUGSG"+
		"NGSXHGRJAONUOMFXCGEOAOGGOCUPCO"+
		"UOHGSHGEOPARSHGOLGUOBXHMMOUBCS"+
		"JORHMSWPAOWOHGDXNCXQHBSPEOCUDU"+
		"GOWFXCOHBCDPGSHKRHMMOBCDPGSHKG"+
		"OLGFSAOUDXNWRDNUOUXWOXFGEOXGEO"+
		"CUNJUGSGNGSXHBSPEOCUVHXQHSHGEO"+
		"ASGOCRGNCODXNWRDQXCVSHKCXNPUXF"+
		"GQXRHMWRDNUORHDPCXKCRWWSHKARHK"+
		"NRKOXFDXNCBEXSBO";

		Stack<String> stack = new Stack<String>();
		stack.push(s);
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 'pop' if you'd like to undo a replacement");
		while (true) {
			System.out.println();
			System.out.println(stack.peek());
			System.out.println();
			System.out.print("Enter the letter that you want replaced: ");
			String command = in.next();
			if (command.equalsIgnoreCase(STACK_POP)) {
				if (!(stack.size() <= 1)) {
					stack.pop();
				}
				continue;
			}
			char letterToReplace = command.charAt(0);
			System.out.print("Enter the letter to use as a replacement: ");
			command = in.next();
			if (command.equalsIgnoreCase(STACK_POP)) {
				if (!(stack.size() <= 1)) {
					stack.pop();
				}
				continue;
			}
			char replacement = command.charAt(0);
			System.out.println();
			stack.push(stack.peek().replace(letterToReplace, replacement));
		}
	}
}
