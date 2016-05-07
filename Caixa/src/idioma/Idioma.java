package idioma;

import java.util.ResourceBundle;

public class Idioma {

	private static ResourceBundle rb;

	public Idioma(ResourceBundle rb) {
		Idioma.rb = rb;
	}

	public static ResourceBundle getRb() {
		return rb;
	}

	public static void setRb(ResourceBundle rb) {
		Idioma.rb = rb;
	}

}
