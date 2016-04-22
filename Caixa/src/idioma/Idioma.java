package idioma;

import java.util.ResourceBundle;

public class Idioma {

	private static ResourceBundle rb = null;

	public Idioma(ResourceBundle x) {
		rb = x;
	}

	public static ResourceBundle getRb() {
		return rb;
	}

	public static void setRb(ResourceBundle rb) {
		Idioma.rb = rb;
	}

}
