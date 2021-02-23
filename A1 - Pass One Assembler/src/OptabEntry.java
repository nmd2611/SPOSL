public class OptabEntry {
    
    public String type;     // stores the instruction type i.e. IS/AD/DL
    public int opcode;

    OptabEntry(String t, int o)
    {
        type = t;
        opcode= o;
    }

    public int getOpcode()
    { 
        return opcode;
    }

    public String getType()
    { 
        return type;
    }

    public boolean isImperative()
    {
        return type.equals("IS");
    }

    public boolean isDeclarative()
    {
        return type.equals("DL");
    }

    public boolean isAssemblyDirective()
    {
        return type.equals("AD");
    }

    @Override
	public String toString() {
		if(type.equals("RG")) {
			return "(" + opcode + ")";
		}
		return "(" + type + "," + String.format("%02d", opcode) + ")";
	}

}
