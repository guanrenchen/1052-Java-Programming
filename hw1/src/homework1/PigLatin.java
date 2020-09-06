package homework1;

public class PigLatin {

	public static void main(String[] args) {
		for(int i=0; i<args.length; ++i){
			switch(args[i].charAt(0)){
			case 'a': case 'e': case 'i': case 'o': case 'u':
				System.out.print(args[i].substring(0,1).toUpperCase() + args[i].substring(1) + "ay");
				break;
			default:
				System.out.print(args[i].substring(1,2).toUpperCase() + args[i].substring(2) + args[i].charAt(0) + "ay");
				break;
			}
			if(i!=args.length-1) System.out.print(" ");
		}
	}

}
