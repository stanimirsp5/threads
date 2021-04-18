package ExamWorkshop.example;

public class Theatre {
    /* *
     * theatre class - here a ticket is picked
    /* *
     *  seats run 101 - 128 but they're divided by house right, middle and left.
     *  Therefore it is necessary to find an open seat and convert it from java
     *  to something the customer would like to look at
     * */
    private boolean houseRight[][];
    private boolean houseLeft[][];
    private boolean houseMiddle[][];

    public Theatre()
    {
        /* *
         *  Iniitializes a theatre according
         *  All seats are set to false iniitally to indicate that they have not yet
         *  been filled. Setting a seat to "true" indicates that it is reserved
         * */
        //[how far back (row)][how far from stage right(seat)]
        houseMiddle = new boolean[24][14];
        houseRight = new boolean[28][7];
        houseLeft = new boolean[25][7];
        for(int i =0; i< 14; i++ )
        {
            for(int j =0; j < 24; j++)
            {
                houseMiddle[j][i] = false;
            }
        }
        for(int i =0; i< 7; i++ )
        {
            for(int j =0; j < 28; j++)
            {
                houseRight[j][i] = false;
            }
        }
        for(int i =0; i< 7; i++ )
        {
            for(int j =0; j < 25; j++)
            {
                houseLeft[j][i] = false;
            }
        }
    }//constructor
    public int convertSeatNumber(String side, int num)
    {
        if(side.equals("middle")){
            return 108 + num;
        }
        else if(side.equals("right")){
            return num + 122;
        }
        else{
            return 101 + num;
        }
    }
    public String convertSeatLetter(String side, int num)
    {
        if(side.equals("middle"))
        {
            return getCharForNumber(num);
        }
        else if(side.equals("right"))
        {
            if(num == 26){ return "AA";}
            else{return getCharForNumber(num);}
        }
        else{
            if(num == 24){ return "AA";}
            else{return getCharForNumber(num+2);}

        }
    }
    private String getCharForNumber(int i) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        if (i > 25) {
            return null;
        }
        return Character.toString(alphabet[i]);
    }
    /* *
     * We want to return the best seat. So the seat can be anywhere in three 2D arrays
     * that make up the left right and middle sections. We want a seat close to middle
     * and close to the front of the stage. It makes sense to search each of these 2D
     * arrays for a seat seperately. If middle finds something, it gets returned. Else
     * left and right, depending on which is closer. I can see an O(nm) way to do this
     * where n is the total width and m is total length.
     *
     * */
    //-----------------------------------------------------------------

    public String findBestSeat()
    {
        String  ret = "";
        /* *
         * diligently search for the middle seat, if it exists
         * seat is the column number in the resepective section
         * row is the row that it exists in the respective section
         * Algorithm will essentially search middle -> left -> right
         * returning the best seat available. If middle isn't full it's obviously
         * the best around. Else just look at both left and right and return the better one
         * */
        int lrow =0;
        boolean lowFlag = true;
        boolean hiFlag = true;
        int mSeat =0;
        int row =0;
        int lSeat =0;
        int rSeat =0;
        boolean flag = true;
        /* *
         *
         * */
        while(lowFlag)
        {
            int bestSeat= findBestMiddleSeatRecusively(7,row);
            if(bestSeat != -1)
            {
                ret = "M"+Integer.toString(convertSeatNumber("middle",bestSeat))+convertSeatLetter("middle",row);
                lowFlag = false;
                hiFlag = false;
                flag = false;
                houseMiddle[row][bestSeat] = true;

            }
            else if(bestSeat == -1){

                bestSeat = findRightSeat(row);
                if(bestSeat != -1)
                {
                    ret = "R"+Integer.toString(convertSeatNumber("right",bestSeat))+convertSeatLetter("right",row);
                    lowFlag = false;
                    hiFlag = false;
                    flag = false;
                    houseRight[row][bestSeat] = true;

                }
            }
            row++;
            if(row == 2)
            {
                lowFlag = false;
            }

        }
        /* *
         * This next section searches rows c-x
         * */
        while(flag){
            mSeat = findBestMiddleSeatRecusively(7,row);
            if(mSeat != -1){
                ret = "M"+Integer.toString(convertSeatNumber("middle",mSeat))+convertSeatLetter("middle",row);
                hiFlag = false;
                flag = false;
                houseMiddle[row][mSeat] = true;}
            /* *
             * Having past the place where the middle is search, this is where we search left
             * and right. It's much easier since the best seats are located next to each other
             * */
            else
            {
                lSeat = findLeftSeat(lrow);
                rSeat = findRightSeat(row);
                int lRank = 6 - lSeat;
                if(row == 2){lRank = 5 - lSeat;}
                if(rSeat != -1 && lSeat != -1)
                {
                    if(rSeat < lRank)
                    {
                        ret = "R"+Integer.toString(convertSeatNumber("right",rSeat))
                                +convertSeatLetter("right",row);
                        hiFlag = false;
                        flag = false;
                        houseRight[row][rSeat]=true;
                    }
                    else{
                        ret = "L"+Integer.toString(convertSeatNumber("left",lSeat))
                                +convertSeatLetter("left",lrow);
                        flag = false;
                        hiFlag = false;
                        houseLeft[lrow][lSeat] = true;
                    }
                }
                else if(rSeat != -1)
                {
                    ret = "R"+Integer.toString(convertSeatNumber("right",rSeat))
                            +convertSeatLetter("right",row);
                    hiFlag = false;
                    flag = false;
                    houseRight[row][rSeat] = true;

                }
                else if(lSeat != -1)
                {
                    ret = "L"+Integer.toString(convertSeatNumber("left",lSeat))
                            +convertSeatLetter("left",row);
                    hiFlag = false;
                    flag = false;
                    houseLeft[lSeat][row] = true;
                }
                else{
                    lrow++;
                    row++;
                }
                if(row ==24){flag = false;}
            }
        }
        /*
         * TODO write hiFlag routine
         * */
        while(hiFlag)
        {
            if(row == 26)
            {
                lSeat = 9;
            }
            lSeat = findLeftSeat(lrow);
            rSeat = findRightSeat(row);
            int lRank = 6 - lSeat;
            if(row == 2){lRank = 5 - lSeat;}
            if(rSeat != -1 && lSeat != -1)
            {
                if(rSeat < lRank)
                {
                    ret = "R"+Integer.toString(convertSeatNumber("right",rSeat))
                            +convertSeatLetter("right",row);
                    hiFlag = false;
                    flag = false;
                    houseRight[row][rSeat]=true;
                }
                else{
                    ret = "L"+Integer.toString(convertSeatNumber("left",lSeat))
                            +convertSeatLetter("left",lrow);
                    flag = false;
                    hiFlag = false;
                    houseLeft[lrow][lSeat] = true;
                }
            }
            else if(rSeat != -1)
            {
                ret = "R"+Integer.toString(convertSeatNumber("right",rSeat))
                        +convertSeatLetter("right",row);
                hiFlag = false;
                flag = false;
                houseRight[row][rSeat] = true;

            }
            else if(lSeat != -1)
            {
                ret = "L"+Integer.toString(convertSeatNumber("left",lSeat))
                        +convertSeatLetter("left",row);
                hiFlag = false;
                flag = false;
                houseLeft[lSeat][row] = true;
            }
            else{
                lrow++;
                row++;
            }
            if(row == 26){hiFlag = false;}
        }
        //weird AA edge case
//    	boolean finalCheck = true;
        //--------------------


        if(ret.equals("")){ret = "NO TICKETS AVAILABLE";}
        return ret;
    }
    public int findRightSeat(int row)
    {
        for(int i =0; i < 7; i++)
        {
            if(houseRight[row][i] == false)
                return i;
        }
        return -1;
    }
    public int findLeftSeat(int row)
    {
        for(int i = 6; i > -1; i--)
        {
            //edge case for row A
            if(row == 0 && i == 6){houseLeft[row][i] = true;}
            if(houseLeft[row][i] == false)
            {
                return i;
            }
        }
        return -1;
    }
    public int findBestMiddleSeatRecusively(int best, int row)
    {
        return findMiddle(row);
    }
    public int findMiddle(int row)
    {
        int ret = 0;
        int spot = 7;
        int add = 1;
        boolean flip = false;
        boolean go = true;
        while(go)
        {

            if(spot == 14)
            {
                if(houseMiddle[row][0] == false){return 0;}
                else{return -1;}
            }
            if(houseMiddle[row][spot] == false){return spot;}

            if(!flip){
                spot += add;
                add+=1;}
            else if(flip)
            {
                add = add * -1;
                spot += add;
                add--;
                add=add*-1;

            }
            flip = !flip;
        }
        return ret;
    }
    public void setTaken(int row, int seat, char section)
    {
        switch(section)
        {
            case 'm':
                houseMiddle[row][seat] = false;
                break;
            case 'l':
                houseLeft[row][seat]=false;
                break;
            case 'r':
                houseRight[row][seat] = false;
                break;
        }
    }

}//class
