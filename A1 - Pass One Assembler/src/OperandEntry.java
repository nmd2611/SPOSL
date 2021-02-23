public class OperandEntry{

    private String literal;
    private int address, index;

    OperandEntry(String l, int a, int idx )
    {
        literal = l;
        address = a;
        index = idx;
    }

    public String getLiteral() {
		return literal;
	}

	public int getAddress() {
		return address;
	}

	public int getIndex() {
		return index;
	}

	public OperandEntry setAddress(int address) {
		this.address = address;
		return this;
	}

	public OperandEntry setIndex(int index) {
		this.index = index;
		return this;
	}

	@Override
	public String toString() {
		if (literal.contains("="))
			return "(L," + index + ")";
		else
			return "(S," + index + ")";
	}
}