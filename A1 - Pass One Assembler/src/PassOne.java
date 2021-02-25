import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.*;

public class PassOne {
   public OpcodeTable OPTAB;
   public ArrayList<OperandEntry> LITTAB;
   public LinkedHashMap<String, OperandEntry> SYMTAB;
   public ArrayList<Integer> POOLTAB;
   public String currentLine;
   public StringBuilder code;   // stores the final o/p code

    int LC = 0;
	int pooltab_ptr = 0;
	int littab_ptr = 0;
	int symtab_ptr = 0;
   
    PassOne()
    {
        SYMTAB = new LinkedHashMap<>();
        POOLTAB = new ArrayList<>();
        LITTAB = new ArrayList<>();
        OPTAB = new OpcodeTable();
        code = new StringBuilder();
   }

   public String parseCode(BufferedReader br) throws IOException {
    POOLTAB.add(pooltab_ptr);
    while((currentLine = br.readLine() ) != null)
    {
        String[] words = currentLine.split("\\s+");    // this is used to split a sentence into an array of space separated words
        
        // if a label is present at the start of the statement
        if(!words[0].isEmpty())
        {
            if (SYMTAB.containsKey(words[0])) {
                if (SYMTAB.get(words[0]).getAddress() == -1) {
                    SYMTAB.put(words[0], SYMTAB.get(words[0]).setAddress(LC));
                } else {
                    System.err.println("Same symbol declared multiple times");
                }
            } else {
                SYMTAB.put(words[0], new OperandEntry(words[0], LC, symtab_ptr++));
            }
        }

            // If LTORG statement
            if(words[1].equals("LTORG") || words[1].equals("END"))
            {
                int ptr = POOLTAB.get(pooltab_ptr);

                if (words[1].equals("END")) {
					code.append(LC + "  ");
					code.append(OPTAB.getEntry("END")).append("\n");
				}

				for (int j = ptr; j < littab_ptr; j++) {
					LITTAB.set(j, LITTAB.get(j).setAddress(LC));
					code.append(LC + "  ");
					code.append(OPTAB.getEntry("DC")).append("  "); 			// (DL,02)
					code.append(constantPair(LITTAB.get(j).getLiteral()) + "\n"); 	// (C,5)
					LC++;
				}
                
				
				if (!words[1].equals("END")) {
					pooltab_ptr++;
					POOLTAB.add(littab_ptr);	
				}
            }
            // If START or ORIGIN statement
            else if(words[1].equals("START") || words[1].equals("ORIGIN"))
            {
                LC = expr(words[2]);
				code.append((!OPTAB.getEntry(words[1]).isAssemblyDirective()  ) ? (LC + "  ") : ("---  "));
				code.append(OPTAB.getEntry(words[1])).append("  "); 	// (AD,01/03)
				code.append(constantPair(LC) + "\n"); 
            }
            // If EQU statement
            else if(words[1].equals("EQU"))
            {
                int addr = expr(words[2]);
				SYMTAB.put(words[0], SYMTAB.get(words[0]).setAddress(addr));

				code.append((!OPTAB.getEntry(words[1]).isAssemblyDirective()) ? (LC + "  ") : ("---  "));
				code.append(OPTAB.getEntry("EQU")).append("  "); 	// (AD,04)
				code.append(constantPair(addr) + "\n"); 				// (C,202)
				LC++;

            }
            // If DL statement
            else if(OPTAB.getEntry(words[1]).isDeclarative())
            {
                code.append((!OPTAB.getEntry(words[1]).isAssemblyDirective()) ? (LC + "  ") : ("---  "));
				SYMTAB.put(words[0], SYMTAB.get(words[0]).setAddress(LC));
				int size = Integer.parseInt(words[2].replace("'", ""));
				if (words[1].equals("DS")) {
					LC += size;
				} else {
					LC++;
				}
				code.append(OPTAB.getEntry(words[1])).append("  ");
				code.append(constantPair(size) + "\n");
            }
            // If Imperative statement
            else if(OPTAB.getEntry(words[1]).isImperative())
            {
                if(words[1].equals("STOP"))
                {
                    code.append((!OPTAB.getEntry(words[1]).isAssemblyDirective()) ? (LC + "  ") : ("---  "));
                    code.append(OPTAB.getEntry(words[1])).append("\n");
                    LC++;
                }
                else{
                    String operands[] = words[2].split(",");
                    
                    code.append((!OPTAB.getEntry(words[1]).isAssemblyDirective()) ? (LC + "  ") : ("---  "));
					code.append(OPTAB.getEntry(words[1])).append("  ");
					String operand;
					
					if (operands.length == 1) {
						operand = operands[0];
					} else {
						code.append(OPTAB.getEntry(operands[0])).append("  ");
						operand = operands[1];
					}
					OperandEntry entry;
					if (operand.contains("=")) {
						// Literals in same pool should be reused
						boolean isPresent = false;
						int idx = 0;
						for (int i = POOLTAB.get(pooltab_ptr); i < littab_ptr; i++) {
							if (LITTAB.get(i).getLiteral().equals(operand)) {
								isPresent = true;
								idx = i;
								break;
							}
						}
						if (!isPresent) {
							entry = new OperandEntry(operand, -1, littab_ptr++);
							LITTAB.add(entry);
						} else {
							entry = LITTAB.get(idx);
						}
						code.append(entry).append("\n");
					} else if (operand.matches("\\d+")) {
						code.append(constantPair(operand)).append("\n");
					} else if (!SYMTAB.containsKey(operand)) {
						entry = new OperandEntry(operand, -1, symtab_ptr++);
						SYMTAB.put(operand, entry);
						code.append(entry).append("\n");
					} else {
						entry = SYMTAB.get(operand);
						code.append(entry).append("\n");
					}
					LC++;
                }
            }
        
    }
    return code.toString();
    
   }

   /*
    Reusables
   */

  public String getPoolTable() {
    StringBuilder sb = new StringBuilder();
    for(int idx: POOLTAB) {
        sb.append("#").append(idx).append("\n");
    }
    return sb.toString();
}

public String getLiteralTable() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < LITTAB.size(); i++) {
        OperandEntry entry = LITTAB.get(i);
        sb.append(i).append("\t")
          .append(entry.getLiteral()).append("\t")
          .append(entry.getAddress())
          .append("\n");
    }
    return sb.toString();
}

  public String getSymbolTable() {
    //		System.out.println("\n\n\tSymbol\tAddress");
            StringBuilder sb = new StringBuilder();
            for (Entry<String, OperandEntry> entry : SYMTAB.entrySet()) {
    //			System.out.println(
    //					entry.getValue().getIndex() + "\t" + entry.getKey() + "\t" + entry.getValue().getAddress());
                sb.append(entry.getValue().getIndex()).append("\t")
                  .append(entry.getKey()).append("\t")
                  .append(entry.getValue().getAddress())
                  .append("\n");
            }
            return sb.toString();
        }
    
    public int expr(String str) {
        int temp = 0;
        if (str.contains("+")) {
            String splits[] = str.split("\\+");
            //System.out.println(splits[1]);
            temp = SYMTAB.get(splits[0]).getAddress() + Integer.parseInt(splits[1]);
        } else if (str.contains("-")) {
            String splits[] = str.split("\\-");
            temp = SYMTAB.get(splits[0]).getAddress() - (Integer.parseInt(splits[1]));
        } else if (SYMTAB.containsKey(str)) {
            temp = SYMTAB.get(str).getAddress();
        } else {
            temp = Integer.parseInt(str);
        }
        return temp;
    }
    
        public String constantPair(String literal) {
            String value = literal.replace("=", "").replace("'", "");
            return "(C," + value + ")";
        }
    
        public String constantPair(int value) {
            return "(C," + value + ")";
        }
    
        public String literalPair(OperandEntry entry) {
            int idx = LITTAB.indexOf(entry);
            return "(L," + idx + ")";
        }




}
