package hva.exceptions;

import java.io.Serial;

/**
 * 
 */
public class UnavailableFileException extends Exception {

	@Serial
	private static final long serialVersionUID = 202407081733L;

	/** The requested filename. */
	String _filename;

	public UnavailableFileException(String filename) {
	  super("Erro a processar ficheiro " + filename);
	  _filename = filename;
	}

	public String getFilename() {
		return _filename;
	}

}
