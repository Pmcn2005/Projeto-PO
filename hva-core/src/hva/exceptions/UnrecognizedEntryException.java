package hva.exceptions;

import java.io.Serial;

public class UnrecognizedEntryException extends Exception {

	@Serial
	private static final long serialVersionUID = 202407081733L;

	/** Unrecognized entry specification. */
	private final String _entrySpecification;

	public UnrecognizedEntryException(String entrySpecification) {
		_entrySpecification = entrySpecification;
	}

	public UnrecognizedEntryException(String entrySpecification, Exception cause) {
		super(cause);
		_entrySpecification = entrySpecification;
	}

	public String getEntrySpecification() {
		return _entrySpecification;
	}

}
