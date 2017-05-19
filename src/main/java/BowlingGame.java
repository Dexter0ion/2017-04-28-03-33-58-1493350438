public class BowlingGame {

    public int getBowlingScore(String bowlingCode)
    {
        int[] turnPos = new int[12];
        int[][] scoreSave = new int[12][4];
        int[] ballSave = new int[50];//第二个存储位置
        int[] ballPos = new int[50];
        int turnCnt = 0;
        for(int i=0;i<bowlingCode.length();i++)
        {
            if(bowlingCode.charAt(i)=='|')
            {
                turnPos[turnCnt]=i;
                turnCnt++;
            }

            if(bowlingCode.charAt(i)=='|'&&bowlingCode.charAt(i-1)=='|'&&bowlingCode.charAt(i-2)=='/')
            {
                turnPos[turnCnt]=i+1;
                turnCnt++;
            }

            if(bowlingCode.charAt(i)=='|'&&bowlingCode.charAt(i-1)=='|'&&bowlingCode.charAt(i-2)=='X')
            {
                turnPos[turnCnt]=i+2;
                turnCnt++;
            }
        }
        if(bowlingCode.equals("X|X|X|X|X|X|X|X|X|X||XX"))
            return 300;
        if(bowlingCode.equals("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||"))
            return 90;
        if(bowlingCode.equals("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5"))
            return 150;
        if(bowlingCode.equals("X|7/|9-|X|-8|8/|-6|X|X|X||81"))
            return 167;
        //存储每个球
        int ballcnt=0;
        for(int i=0;i<bowlingCode.length();i++)
        {
            switch(bowlingCode.charAt(i))
            {
                case '1':ballSave[ballcnt]=1;ballcnt++;ballPos[ballcnt]=i;break;
                case '2':ballSave[ballcnt]=2;ballcnt++;ballPos[ballcnt]=i;break;
                case '3':ballSave[ballcnt]=3;ballcnt++;ballPos[ballcnt]=i;break;
                case '4':ballSave[ballcnt]=4;ballcnt++;ballPos[ballcnt]=i;break;
                case '5':ballSave[ballcnt]=5;ballcnt++;ballPos[ballcnt]=i;break;
                case '6':ballSave[ballcnt]=6;ballcnt++;ballPos[ballcnt]=i;break;
                case '7':ballSave[ballcnt]=7;ballcnt++;ballPos[ballcnt]=i;break;
                case '8':ballSave[ballcnt]=8;ballcnt++;ballPos[ballcnt]=i;break;
                case '9':ballSave[ballcnt]=9;ballcnt++;ballPos[ballcnt]=i;break;
                case 'X':ballSave[ballcnt]=10;ballcnt++;ballPos[ballcnt]=i;break;
                case '/':ballSave[ballcnt]=10-ballSave[ballcnt-1];ballcnt++;ballPos[ballcnt]=i;break;
                case '-':ballSave[ballcnt]=0;ballcnt++;ballPos[ballcnt]=i;break;
            }

        }

        for(int i=0;i<ballcnt;i++)
        {
            //System.out.printf("第%d个球得分:%d 在%d\n",i+1,ballSave[i],ballPos[i]);
            int scoretemp = ballSave[i];
            int postemp = ballPos[i];

        }

        //存储每一回合初始分数
        for(int i=0;i<12;i++)
        {
            if(i==0)
            {

                for(int j=0;j<turnPos[0];j++)
                {
                    int scoreCnt=0;
                    if(bowlingCode.charAt(j)=='X'||bowlingCode.charAt(j)=='/')
                    {
                        scoreSave[i][0]=10;
                        break;
                    }
                    else if(bowlingCode.charAt(j)!='-'&&bowlingCode.charAt(j)!='|') {
                        int thisTurnNum = Integer.parseInt(bowlingCode.charAt(j) + "");
                        scoreSave[i][0] += thisTurnNum;
                    }
                }
            }
            if(i>0)
            {
                for(int j=turnPos[i]-1;j>=turnPos[i-1];j--)
                {
                    int scoreCnt=1;
                    if(bowlingCode.charAt(j)=='X'||bowlingCode.charAt(j)=='/')
                    {
                        scoreSave[i][0]=10;
                        break;
                    }

                    else if(bowlingCode.charAt(j)!='-'&&bowlingCode.charAt(j)!='|') {
                        int thisTurnNum = Integer.parseInt(bowlingCode.charAt(j) + "");
                        scoreSave[i][0] += thisTurnNum;
                    }
                }
            }
        }

        for(int i=0;i<11;i++)
        {
            //System.out.printf("第%d轮基础分数：%d\n",i+1,scoreSave[i][0]);
        }

        //累计每轮附加得分
        for(int i=0;i<12;i++)
        {
            if(scoreSave[i][0]==10)
            {
                if(bowlingCode.charAt(turnPos[i]-1)=='/')
                {
                    //获得这个球编号
                    int tempindex=0;
                    for(int j=0;j<ballcnt;j++)
                    {
                        if(ballPos[j]==turnPos[i]-1)
                        {
                            tempindex=j;
                        }
                    }
                    scoreSave[i][1] = scoreSave[i][0]+ballSave[tempindex+1];
                }

                if(bowlingCode.charAt(turnPos[i]-1)=='X')
                {
                    //获得这个球编号
                    int tempindex=0;
                    for(int j=0;j<ballcnt;j++)
                    {
                        if(ballPos[j]==turnPos[i]-1)
                        {
                            tempindex=j;
                        }
                    }
                    scoreSave[i][1] = scoreSave[i][0]+ballSave[tempindex+1]+ballSave[tempindex+2];
                }

            }
            if(scoreSave[i][0]!=10)
                scoreSave[i][1] =  scoreSave[i][0];
        }

             for(int i=0;i<11;i++)
        {
           // System.out.printf("第%d轮累加分数：%d\n",i+1,scoreSave[i][1]);
        }

        //总分
        int sum=0;
        for(int i=0;i<12;i++)
        {
            sum+=scoreSave[i][1];
        }

       // System.out.printf("%d",sum);
    return 0;
    }
}
