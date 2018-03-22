package controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Integer> num = Arrays.asList(7,4,9,4,5,1,3,8);
		Collections.sort(num); 
		
		num.forEach(item->System.out.println(item));
		String entrada = "level";
		
		String palim = new StringBuilder(entrada).reverse().toString();

		if(palim.equals(entrada)){
			System.out.println("Es palindromo");
		}else{
			System.out.println("No es palindromo");
		}
		
		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Es palindromo");
		}
		
		System.out.println("fasdbaiufhbiasufdiauo");
	}
}
