package algo;

import java.io.*;

public class RockPaperScissor {

    /*
     * Complete the 'handFormationChange' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER a
     *  3. STRING formations
     */

    /*
        Logic here is to iterate over input string and eliminate half of the character
        after each iteration, keeping POI (X) as winner in each iteration
        Maintain counter (numOfChanges) in each iteration to record transition of X
        Increment counter only if value of X needs to be changed (therefore lastChar is needed to track last value of X)
        isX boolean flag is used to replace POI value with X (for next iterations)
     */
    public static int handFormationChange(int n, int a, String formations) {
        // Write your code here
        int numOfChanges = -1;
        StringBuilder newFormations = new StringBuilder();
        String temp = formations.substring(0, a);
        temp = temp + "X" + formations.substring(a,formations.length());
        formations = temp;
        boolean isX=false;
        char lastChar = ' ';

        while(formations.length() != 1){
            newFormations = new StringBuilder("");
            for(int i=0; i<formations.length(); i+=2){
                isX = false;

                // Competition should be  skipped for last member if its odd
                if(i == formations.length() - 1) {
                    newFormations.append(formations.charAt(i));
                    continue;
                }

                char first = formations.charAt(i);
                char second = formations.charAt(i+1);
                if(first == 'X'){
                    if(second == 'R') first = 'P';
                    else if(second == 'P') first = 'S';
                    else first = 'R';
                    if(first != lastChar)
                        numOfChanges++;
                    isX = true;
                }else if(second == 'X'){
                    if (first == 'R') second = 'P';
                    else if (first == 'P') second = 'S';
                    else second = 'R';
                    if(second != lastChar)
                        numOfChanges++;
                    isX = true;
                }

                // Winners moved to new formations
                if(first != second){
                    if(first == 'P'){
                        if(second == 'R') newFormations.append(first);
                        else if(second == 'S') newFormations.append(second);
                    }else if(first == 'R'){
                        if(second == 'S') newFormations.append(first);
                        else if(second == 'P') newFormations.append(second);
                    }else if(first == 'S'){
                        if(second == 'P') newFormations.append(first);
                        else if(second == 'R') newFormations.append(second);
                    }
                }

                //Replace POI with X
                if(isX){
                    lastChar = newFormations.charAt(newFormations.length() - 1);
                    newFormations.replace(newFormations.length()-1, newFormations.length(), "X");
                }
            }
            formations = newFormations.toString();
        }

        // If POI wins without competing then numOfChanges would be -1
        return numOfChanges == -1 ? 0 : numOfChanges;
    }

    public static void main(String[] args) throws IOException {
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int a = Integer.parseInt(bufferedReader.readLine().trim());

        String formations = bufferedReader.readLine();

        int result = RockPaperScissor.handFormationChange(n, a, formations);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();*/


        //Test cases
        int result = RockPaperScissor.handFormationChange(3, 2, "RP");
        System.out.println(result);

        result = RockPaperScissor.handFormationChange(7, 2, "PPSRPR");
        System.out.println(result);

        result = RockPaperScissor.handFormationChange(735, 177, "RRRPRSRRSPRRSRSSSSSSPPSPPRPRPRSSRSRPSPRPRSPRPRSRSSPRSPRSSSSRSRPSSRSRPRSRRPPRPPPSPRRPPRSRSPPPRPPSSRPSPRSRRPPRPRRRRPPPSSSSPRSPSRSPRPRRPSPRRPSRSRPRSSRSRPPRPPSPRSPPRSRPSRPRPRPRPPRPSSPSRSPSSSSPPRRPSPRPPRPPPPSPRSRSRRSSPRRRPRPSRRRRPPRRSSPPRPPSRPPSSPSPSSPPRSPSRSPPRSRPRRRSSPSSRRPSRPRRPSPSPSPPPPRPSPRSRSPSPRPRSPPSRRSSRRRPPSPPSSPPPPRRSPRRPSSSPRSRPSSSPPPRPRPPRSSRSSPSRRRPPSSPPSPSSSPSRPRRRPRRPPPRRRSRSSRRRSRPSPSPRSPRPRPPSPSPSSPRSRSSSPRPSPSSSSRRPRRPSPPSPRRPRRPPSRPPRSRSRSSSPPSPPPSSPPPPPRRRRSSRRRRRSRSRPRSRSRPSRRSSSRRSSSRPSRPPRPRPPRSSPPRRSPSPPRSPRSPSRRSRRSSSSRRRSPRSRPRRSRPRRRRPRRPSRRPPRRPSSSPPSSRRSRSRPRPRSPRSRRSRSPPSPRSSRPRSRRSRSSRRPRPPRRPRPSPSPRSSSRPPRRSSRPRPRRRRPRRPPPRSSPRRPSPSRRPPPSPSPSSPSSPSPRSRRRRPPPRRPPSRPRPRRPPRRRRRPPRSPPSPSSPPPRSPPSRRPP");
        System.out.println(result);

        //POI wins without competing
        result = RockPaperScissor.handFormationChange(7, 4, "RRRR");
        System.out.println(result);

        result = RockPaperScissor.handFormationChange(2, 0, "P");
        System.out.println(result);

        result = RockPaperScissor.handFormationChange(2, 1, "P");
        System.out.println(result);

        result = RockPaperScissor.handFormationChange(10, 4, "RPSRPSRPS");
        System.out.println(result);
    }

}
